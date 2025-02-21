/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package point.of.sale.system;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class AppLauncher {

    private static File lockFile;

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
                    try {
                        PointOfSaleSystem.updateSalesTable();
                        new Home().setVisible(true);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }

    private static boolean isAppAlreadyRunning() {
        try {
            String userHome = System.getenv("APPDATA");
            lockFile = new File(userHome, ".POS.lock");

            // Try to create the lock file
            boolean created = lockFile.createNewFile();
            if (created) {
                // Lock file created successfully; app is not already running
                lockFile.deleteOnExit();
                return false;
            } else {
                // Lock file already exists; app is running
                return true;
            }
        } catch (IOException e) {
            // Unable to create lock file; assume app is already running
            e.printStackTrace();
            return true;
        }
    }
}
