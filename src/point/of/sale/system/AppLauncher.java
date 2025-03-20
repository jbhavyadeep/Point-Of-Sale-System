/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package point.of.sale.system;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class AppLauncher {

    private static File lockFile;
    
    private static FileLock lock;
    private static FileChannel lockChannel;

    public static void main(String[] args) {

        if (isAppAlreadyRunning()) {
            // Application is already running
            JOptionPane.showMessageDialog(null, "The application is already running.", "Instance Running", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        } else {
            // Proceed with application startup
            try {
                // Add a shutdown hook to delete the lock file on exit
                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    if (lockFile != null && lockFile.exists()) {
                        lockFile.delete();
                    }
                }));
                
                // Launch the application
                java.awt.EventQueue.invokeLater(() -> {
                    PointOfSaleSystem.updateSalesTable();
                    new Authenticate().setVisible(true);
                    //new Home().setVisible(true);
                });

            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    private static boolean isAppAlreadyRunning() {
        
        try {
            String userHome = System.getProperty("APPDATA");
            lockFile = new File(userHome, ".POS.lock");
            
            // Open the file in read-write mode and try to acquire a lock
            lockChannel = new RandomAccessFile(lockFile, "rw").getChannel();
            lock = lockChannel.tryLock();
            
            if (lock == null) {
                // Another instance has already locked the file, meaning the app is running
                return true;
            }
            lockFile.deleteOnExit();



            // Add a shutdown hook to release the lock and clean up properly
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    if (lock != null) lock.release();
                    if (lockChannel != null) lockChannel.close();
                    if (lockFile.exists()) lockFile.delete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }));
            return false; // App is not already running
       } catch (FileNotFoundException e) {
            System.err.println("Lock file could not be created: " + e.getMessage());
            return true;
        } catch (IOException e) {
            System.err.println("Error while trying to check application instance: " + e.getMessage());
            return true;
        }
    }
}
