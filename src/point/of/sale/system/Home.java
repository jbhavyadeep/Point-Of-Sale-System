package point.of.sale.system;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;


import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;


/**
 *
 * @author jbhav
 */
public class Home extends javax.swing.JFrame {
    public Statement s;
    JpanelLoader jpload = new JpanelLoader();
    public int flag;
                
    public Home(int flag) throws SQLException {
        initComponents();
        this.s = db.mycon().createStatement();
        this.flag = flag;
        this.setExtendedState(Home.MAXIMIZED_BOTH);
        load();
        new JFXPanel(); 
        initClock();
        if(flag == 1){
            user_change.setText("User 1");
            
            //flag = 2;
        }
        else {
            user_change.setText("Admin");
           // flag = 1;
        }
    }


public void initClock() {
    Platform.runLater(() -> { // Ensure JavaFX is initialized
        Thread thread = new Thread(() -> {
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy"); // Added AM/PM
            SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss a"); // 
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    Thread.sleep(1000); // Sleep for 1 second
                    String datenow = sdf1.format(new Date());
                    String timenow = sdf2.format(new Date());

                    Platform.runLater(() -> curr_date.setText(datenow));
                    Platform.runLater(() -> curr_time.setText(timenow));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Preserve the interrupt status
                    break; // Exit loop if interrupted
                }
            }
        });

        thread.setDaemon(true); // Ensures the thread stops when the application closes
        thread.start();
    });
}
   
    public void load(){  
        sales sl = new sales(s);
        jpload.jPanelLoader(jScrollPane1, sl);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton6 = new javax.swing.JToggleButton();
        home_btn_grp = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton7 = new javax.swing.JToggleButton();
        jToggleButton8 = new javax.swing.JToggleButton();
        jToggleButton9 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jToggleButton10 = new javax.swing.JToggleButton();
        stk = new javax.swing.JToggleButton();
        barcode_gen = new javax.swing.JToggleButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        curr_date = new javax.swing.JLabel();
        curr_time = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        user_change = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();

        jToggleButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jToggleButton6.setText("Customers");
        jToggleButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton6ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(0, 0));

        jPanel1.setBackground(new java.awt.Color(167, 178, 194));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jToggleButton1.setBackground(new java.awt.Color(94, 59, 66));
        home_btn_grp.add(jToggleButton1);
        jToggleButton1.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jToggleButton1.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton1.setText("Customer");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        jToggleButton4.setBackground(new java.awt.Color(94, 59, 66));
        home_btn_grp.add(jToggleButton4);
        jToggleButton4.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jToggleButton4.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton4.setText("Product");
        jToggleButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton4ActionPerformed(evt);
            }
        });

        jToggleButton2.setBackground(new java.awt.Color(94, 59, 66));
        home_btn_grp.add(jToggleButton2);
        jToggleButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jToggleButton2.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton2.setText("Sales");
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });

        jToggleButton7.setBackground(new java.awt.Color(94, 59, 66));
        home_btn_grp.add(jToggleButton7);
        jToggleButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jToggleButton7.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton7.setText("Invoice");
        jToggleButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton7ActionPerformed(evt);
            }
        });

        jToggleButton8.setBackground(new java.awt.Color(94, 59, 66));
        home_btn_grp.add(jToggleButton8);
        jToggleButton8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jToggleButton8.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton8.setText("Reports");
        jToggleButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton8ActionPerformed(evt);
            }
        });

        jToggleButton9.setBackground(new java.awt.Color(94, 59, 66));
        home_btn_grp.add(jToggleButton9);
        jToggleButton9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jToggleButton9.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton9.setText("Employee");
        jToggleButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton9ActionPerformed(evt);
            }
        });

        jToggleButton3.setBackground(new java.awt.Color(94, 59, 66));
        home_btn_grp.add(jToggleButton3);
        jToggleButton3.setFont(new java.awt.Font("Nirmala UI", 1, 14)); // NOI18N
        jToggleButton3.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton3.setText("Supplier");
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });

        jToggleButton10.setBackground(new java.awt.Color(94, 59, 66));
        home_btn_grp.add(jToggleButton10);
        jToggleButton10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jToggleButton10.setForeground(new java.awt.Color(255, 255, 255));
        jToggleButton10.setText("GRN");
        jToggleButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton10ActionPerformed(evt);
            }
        });

        stk.setBackground(new java.awt.Color(94, 59, 66));
        home_btn_grp.add(stk);
        stk.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        stk.setForeground(new java.awt.Color(255, 255, 255));
        stk.setText("Stock");
        stk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stkActionPerformed(evt);
            }
        });

        barcode_gen.setBackground(new java.awt.Color(94, 59, 66));
        home_btn_grp.add(barcode_gen);
        barcode_gen.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        barcode_gen.setForeground(new java.awt.Color(255, 255, 255));
        barcode_gen.setText("Barcode ");
        barcode_gen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barcode_genActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(stk, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                    .addComponent(barcode_gen, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jToggleButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jToggleButton2)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jToggleButton1)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jToggleButton3)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jToggleButton4)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jToggleButton10)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jToggleButton8)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jToggleButton7)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(stk)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jToggleButton9)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(barcode_gen)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(90, 106, 130));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/banner logo1.png"))); // NOI18N

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Date: ");

        curr_date.setBackground(new java.awt.Color(255, 255, 255));
        curr_date.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        curr_date.setForeground(new java.awt.Color(255, 255, 255));

        curr_time.setBackground(new java.awt.Color(255, 255, 255));
        curr_time.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        curr_time.setForeground(new java.awt.Color(255, 255, 255));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Time: ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(177, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(curr_date, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(curr_time, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(curr_date))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(curr_time))
                .addContainerGap())
        );

        jScrollPane1.setBackground(new java.awt.Color(167, 178, 194));
        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jMenu1.setText("File");

        user_change.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        user_change.setText("User");
        user_change.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                user_changeStateChanged(evt);
            }
        });
        user_change.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_changeActionPerformed(evt);
            }
        });
        jMenu1.add(user_change);

        jMenuItem2.setText("Exit");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("View");

        jMenuItem3.setText("language");
        jMenu2.add(jMenuItem3);

        jMenuItem4.setText("Theme");
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        // Customer Load
        customer cus;
        try {
            cus = new customer(s);
            jpload.jPanelLoader(jScrollPane1, cus);
        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        // Sales
        
        sales sl = new sales(s);
        jpload.jPanelLoader(jScrollPane1, sl);
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jToggleButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton4ActionPerformed
        // Product Load
        product pro;
        try {
            pro = new product(s,flag);
            jpload.jPanelLoader(jScrollPane1, pro);

        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jToggleButton4ActionPerformed

    private void jToggleButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jToggleButton6ActionPerformed

    private void jToggleButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton7ActionPerformed
        // Invoice load
        invoice inv = new invoice(s);
        jpload.jPanelLoader((jScrollPane1), inv);
    }//GEN-LAST:event_jToggleButton7ActionPerformed

    private void jToggleButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton8ActionPerformed
        // load report
        reports rpt = new reports();
        jpload.jPanelLoader(jScrollPane1, rpt);
    }//GEN-LAST:event_jToggleButton8ActionPerformed

    private void jToggleButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton9ActionPerformed
        // employee
        employee emp = new employee(s);
        jpload.jPanelLoader(jScrollPane1, emp);
    }//GEN-LAST:event_jToggleButton9ActionPerformed

    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton3ActionPerformed
        // supplier
        supplier sup;
        try {
            sup = new supplier(s);
            jpload.jPanelLoader(jScrollPane1, sup);

        } catch (SQLException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jToggleButton3ActionPerformed

    private void jToggleButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton10ActionPerformed
        // GRN
        Grn grn = new Grn(s);
        jpload.jPanelLoader(jScrollPane1, grn);
    }//GEN-LAST:event_jToggleButton10ActionPerformed

    private void stkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stkActionPerformed
        // Stock
        Stock stock = new Stock(s);
        jpload.jPanelLoader(jScrollPane1, stock);
    }//GEN-LAST:event_stkActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void user_changeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_changeActionPerformed
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_user_changeActionPerformed

    private void user_changeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_user_changeStateChanged
        // TODO add your handling code here:
        //user_change.setText("User 1");
    }//GEN-LAST:event_user_changeStateChanged

    private void barcode_genActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barcode_genActionPerformed
        // TODO add your handling code here:
        Barcode bar = new Barcode();
        jpload.jPanelLoader(jScrollPane1, bar);
    }//GEN-LAST:event_barcode_genActionPerformed

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
//            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    new Home().setVisible(true);
//                } catch (SQLException ex) {
//                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton barcode_gen;
    private javax.swing.JLabel curr_date;
    private javax.swing.JLabel curr_time;
    private javax.swing.ButtonGroup home_btn_grp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton10;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton6;
    private javax.swing.JToggleButton jToggleButton7;
    private javax.swing.JToggleButton jToggleButton8;
    private javax.swing.JToggleButton jToggleButton9;
    private javax.swing.JToggleButton stk;
    private javax.swing.JMenuItem user_change;
    // End of variables declaration//GEN-END:variables
}
