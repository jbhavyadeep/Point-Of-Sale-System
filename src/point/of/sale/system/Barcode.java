/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package point.of.sale.system;

import com.barcodelib.barcode.Linear;
import java.io.File;
import java.util.prefs.Preferences;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author jbhav
 */
public class Barcode extends javax.swing.JPanel {

    /**
     * Creates new form Barcode
     */
    public Barcode() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        b_data = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        b_data.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        b_data.setText("0");
        b_data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_dataActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Save Barcode");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addComponent(b_data, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(374, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_data, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(293, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Write Barcode
            try {
        Linear barcode = new Linear();
        barcode.setType(Linear.CODE128B);
        barcode.setData(b_data.getText());
        barcode.setI(11.0f);

        // Retrieve the user preferences node for this class
        Preferences prefs = Preferences.userNodeForPackage(this.getClass());

        // Get the last used directory from preferences
        String lastDir = prefs.get("LAST_USED_DIR", System.getProperty("user.home"));

        // Create a file chooser starting at the last used directory
        JFileChooser fileChooser = new JFileChooser(lastDir);
        fileChooser.setDialogTitle("Save Barcode");

        // Set the default file name
        String defaultFileName = b_data.getText() + ".png";
        fileChooser.setSelectedFile(new File(defaultFileName));

        // Set file filter for PNG files
        FileNameExtensionFilter pngFilter = new FileNameExtensionFilter("PNG Images", "png");
        fileChooser.setFileFilter(pngFilter);
        fileChooser.setAcceptAllFileFilterUsed(false);

        // Show save dialog
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();

            // Save the directory path to preferences
            prefs.put("LAST_USED_DIR", fileToSave.getParent());

            // Ensure the file has a .png extension
            if (!filePath.toLowerCase().endsWith(".png")) {
                filePath += ".png";
            }

            // Render barcode to the specified file
            barcode.renderBarcode(filePath);

            // Inform the user that the barcode was saved successfully
            JOptionPane.showMessageDialog(this, "Barcode saved successfully to:\n" + filePath);
        } else {
            // User canceled the save operation
            JOptionPane.showMessageDialog(this, "Save operation canceled.");
        }
    } catch (Exception e) {
        // Handle exceptions gracefully
        JOptionPane.showMessageDialog(this, "An error occurred while saving the barcode:\n" + e.getMessage());
        
        
    }//GEN-LAST:event_jButton1ActionPerformed
    }
    private void b_dataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_dataActionPerformed
        // 
    }//GEN-LAST:event_b_dataActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField b_data;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
