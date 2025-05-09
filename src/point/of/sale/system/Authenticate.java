/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package point.of.sale.system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author jbhav
 */
public class Authenticate extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public Connection con;

    public Authenticate() {
        initComponents();
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/payment.png"));
        this.setIconImage(icon.getImage());
        this.setTitle("POS Patel Provision Store");
        jProgressBar1.setVisible(false);
        connectDb();
    }
    
    public void connectDb(){
        new Thread(()->{
           try {
            con = db.mycon();
        } catch (Exception e) {
            e.printStackTrace(); 

        } 
           if (con == null) {
        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null,
                "Failed to connect to the database.\nPlease check your internet or database configuration.",
                "Connection Error", JOptionPane.ERROR_MESSAGE);
        });
        return;
    }
        }).start();
        
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
        jLabel1 = new javax.swing.JLabel();
        user_name = new javax.swing.JTextField();
        user_login = new javax.swing.JButton();
        user_password = new javax.swing.JPasswordField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Authenticate User");

        user_name.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        user_name.setForeground(new java.awt.Color(63, 62, 62));
        user_name.setToolTipText("Enter User Name");
        user_name.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        user_name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                user_nameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                user_nameFocusLost(evt);
            }
        });
        user_name.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                user_nameMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                user_nameMouseExited(evt);
            }
        });
        user_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_nameActionPerformed(evt);
            }
        });
        user_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                user_nameKeyPressed(evt);
            }
        });

        user_login.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        user_login.setText("LOGIN");
        user_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_loginActionPerformed(evt);
            }
        });

        user_password.setToolTipText("Enter Password");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Enter User Name :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Enter Password :");

        jProgressBar1.setBackground(new java.awt.Color(255, 255, 255));
        jProgressBar1.setForeground(new java.awt.Color(0, 153, 204));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(204, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(user_login, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(user_password, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(user_name, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(278, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(user_name, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(55, 55, 55)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(user_password, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(56, 56, 56)
                .addComponent(user_login, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void user_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_nameActionPerformed

    private void user_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_loginActionPerformed
        // TODO add your handling code here:
        jProgressBar1.setVisible(true);
        
        jProgressBar1.setIndeterminate(true); // Start progress animation

    new Thread(() -> {
        String user_Name = user_name.getText().trim();
        char[] pass = user_password.getPassword();
        String password = String.valueOf(pass);
        String uid = "";

        try (
             PreparedStatement ps = con.prepareStatement(
                 "SELECT uid FROM authenticate WHERE user_name = ? AND password = ?")) {

            ps.setString(1, user_Name);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                uid = rs.getString("uid");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            SwingUtilities.invokeLater(() -> {
                jProgressBar1.setIndeterminate(false);
                JOptionPane.showMessageDialog(null, "Database error: " + ex.getMessage());
            });
            return;
        }

        final String resultUid = uid;

        SwingUtilities.invokeLater(() -> {
            jProgressBar1.setIndeterminate(false); // Stop progress animation

            if (resultUid.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Wrong ID or Password");
            } else {
                switch (resultUid) {
                    case "1":
                    {
                        try {
                            new Home(1,con).setVisible(true);
                        } catch (SQLException ex) {
                            Logger.getLogger(Authenticate.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                        Authenticate.this.setVisible(false);
                        break;

                    case "2":
                    {
                        try {
                            new Home(2,con).setVisible(true);
                        } catch (SQLException ex) {
                            Logger.getLogger(Authenticate.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                        Authenticate.this.setVisible(false);
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Unknown UID");
                        break;
                }
            }
        });

    }).start();

    }//GEN-LAST:event_user_loginActionPerformed

    private void user_nameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_user_nameKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_user_nameKeyPressed

    private void user_nameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_nameMouseClicked
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_user_nameMouseClicked

    private void user_nameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_user_nameFocusGained
        // TODO add your handling code here:
       
    }//GEN-LAST:event_user_nameFocusGained

    private void user_nameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_user_nameFocusLost
        // TODO add your handling code here:
        
    }//GEN-LAST:event_user_nameFocusLost

    private void user_nameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_user_nameMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_user_nameMouseExited

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Authenticate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Authenticate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Authenticate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Authenticate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        
//        /* Create and display the form */
//        Authenticate ac = new Authenticate();
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                ac.setVisible(true);
//            }
//            
//        });
//        
//        
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JButton user_login;
    private javax.swing.JTextField user_name;
    private javax.swing.JPasswordField user_password;
    // End of variables declaration//GEN-END:variables
}
