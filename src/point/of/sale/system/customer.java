/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package point.of.sale.system;

import java.sql.*;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jbhav
 */
public class customer extends javax.swing.JPanel {

    /**
     * Creates new form customer
     */
    public customer() {
        initComponents();
        tb_load();
    }

    public void tb_load(){
        
        
        
        try{
            DefaultTableModel dt = (DefaultTableModel)jTable1.getModel();
            dt.setRowCount(0);
            
            Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery(" SELECT * FROM customer");
            
            while (rs.next()){
                Vector v = new Vector();
                
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getString(5));
                v.add(rs.getString(6));
                v.add(rs.getString(7));

                dt.addRow(v);
                
            }
            
        }catch (Exception e){
            System.out.println(e);
        }
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        c_tp = new javax.swing.JTextField();
        c_name = new javax.swing.JTextField();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        c_adr = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        c_typ = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        c_cper = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        c_pmob = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        c_search = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        c_search_tb1 = new javax.swing.JTextField();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Name :");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("T.P Number :");

        c_tp.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        c_tp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        c_name.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        c_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jButton21.setBackground(new java.awt.Color(67, 219, 0));
        jButton21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton21.setForeground(new java.awt.Color(238, 238, 238));
        jButton21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/point/of/sale/system/img/save.png"))); // NOI18N
        jButton21.setText("Save");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton22.setBackground(new java.awt.Color(100, 180, 245));
        jButton22.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton22.setForeground(new java.awt.Color(255, 255, 255));
        jButton22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/point/of/sale/system/img/search x30.png"))); // NOI18N
        jButton22.setText("Search");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        jButton23.setBackground(new java.awt.Color(70, 174, 186));
        jButton23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton23.setForeground(new java.awt.Color(255, 255, 255));
        jButton23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/point/of/sale/system/img/update.png"))); // NOI18N
        jButton23.setText("Update");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        jButton24.setBackground(new java.awt.Color(222, 40, 14));
        jButton24.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton24.setForeground(new java.awt.Color(255, 255, 255));
        jButton24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/point/of/sale/system/img/delete.png"))); // NOI18N
        jButton24.setText("Delete");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        c_adr.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        c_adr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_adrjTextField1ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("Address : ");

        c_typ.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        c_typ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_typjTextField1ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("Type : ");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Contect Person :");

        c_cper.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        c_cper.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_cperjTextField1ActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("Mobile : ");

        c_pmob.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        c_pmob.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_pmobjTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(c_tp, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(c_name, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(c_adr, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(c_typ, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(c_cper, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(c_pmob, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jButton21)
                        .addGap(27, 27, 27)
                        .addComponent(jButton22)
                        .addGap(27, 27, 27)
                        .addComponent(jButton23)
                        .addGap(36, 36, 36)
                        .addComponent(jButton24)))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(c_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(c_adr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(c_pmob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(c_cper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(c_typ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(c_tp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton21)
                    .addComponent(jButton22)
                    .addComponent(jButton23)
                    .addComponent(jButton24))
                .addGap(47, 47, 47))
        );

        jTable1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Customer Name", "T.P Number", "Address", "Type", "Contact Person", "Mobile"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        c_search.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        c_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_searchActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Name or ID :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Customer Info : ");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(416, 416, 416))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(c_search, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addGap(41, 41, 41)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(c_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Name or ID :");

        c_search_tb1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        c_search_tb1.setToolTipText("Search");
        c_search_tb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c_search_tb1ActionPerformed(evt);
            }
        });
        c_search_tb1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                c_search_tb1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(c_search_tb1))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(c_search_tb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(357, 357, 357))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void c_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_searchActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        //save
        String name = c_name.getText();
        String tp = c_tp.getText();
        String addr = c_adr.getText();
        String typ = c_typ.getText();
        String conp = c_cper.getText();
        String mob = c_pmob.getText();
        
        
        try{
            Statement s = db.mycon().createStatement();
            s.executeUpdate(" INSERT INTO customer (customer_name, Tp_Number, Address, Type, Contact_Person, Mobile) "
                    + "VALUES ('"+name+"','"+tp+"','"+addr+"','"+typ+"','"+conp+"','"+mob+"')");
            JOptionPane.showMessageDialog(null,"Data Saved");
            
        }catch (Exception e){
            System.out.println(e);
        }
        tb_load();


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        // search button
        String search = c_search.getText();
        try{
            Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery(" SELECT * FROM customer WHERE customer_name ='"+search+"' OR cid ='"+search+"' ");
            if(rs.next()){
                c_name.setText(rs.getString("customer_name"));
                c_tp.setText(rs.getString("Tp_Number"));
                c_adr.setText(rs.getString("Address"));
                c_typ.setText(rs.getString("Type"));
                c_cper.setText(rs.getString("Contact_Person"));
                c_pmob.setText(rs.getString("Mobile"));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        tb_load();

    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        // update buton
        
        String name = c_name.getText();
        String tp = c_tp.getText();
        String id = c_search.getText();
        String addr = c_adr.getText();
        String typ = c_typ.getText();
        String conp = c_cper.getText();
        String mob = c_pmob.getText();

        
        try{
            Statement s = db.mycon().createStatement();
            s.executeUpdate(" UPDATE customer SET customer_name ='"+name+"' , Tp_Number ='"+tp+"' , Address ='"+addr+"' , Type ='"+typ+"' , Contact_Person ='"+conp+"' , Mobile ='"+mob+"' "
                    + "WHERE cid = '"+id+"'");
            JOptionPane.showMessageDialog(null,"Data Updated");

            
            
        }catch (Exception e){
            System.out.println(e);
        }
        tb_load();

    }//GEN-LAST:event_jButton23ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        //Delete button
        String id = c_search.getText();
        try{
            Statement s = db.mycon().createStatement();
            
           s.executeUpdate(" DELETE FROM customer WHERE cid ='"+id+"' or customer_name ='"+id+"'");
            JOptionPane.showMessageDialog(null,"Data Deleted");

           
        }catch (Exception e){
            System.out.println(e);
        }
        tb_load();

        
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // table clicked by mouse get data to textfield
        int r = jTable1.getSelectedRow();
        String id = jTable1.getValueAt(r,0).toString();
        String name = jTable1.getValueAt(r,1).toString();
        String tp = jTable1.getValueAt(r,2).toString();
        String ad = jTable1.getValueAt(r,3).toString();
        String ty = jTable1.getValueAt(r,4).toString();
        String cp = jTable1.getValueAt(r,5).toString();
        String cpm = jTable1.getValueAt(r,6).toString();

        c_search.setText(id);
        c_name.setText(name);
        c_tp.setText(tp);
        c_adr.setText(ad);
        c_typ.setText(ty);
        c_cper.setText(cp);
        c_pmob.setText(cpm);
    }//GEN-LAST:event_jTable1MouseClicked

    private void c_search_tb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_search_tb1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_search_tb1ActionPerformed

    private void c_search_tb1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_c_search_tb1KeyReleased
        // search table content
        String name = c_search_tb1.getText();
        try{
            DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
            Statement s = db.mycon().createStatement();
            dt.setRowCount(0);

            ResultSet rs = s.executeQuery(" SELECT * FROM CUSTOMER WHERE cid ='"+name+"' or customer_name LIKE '%"+name+"%'");
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getString(5));
                v.add(rs.getString(6));
                v.add(rs.getString(7));
                
                dt.addRow(v);
                
                
                
            }
            
        }catch (Exception e){
            tb_load();
            System.out.println(e);
        }
    }//GEN-LAST:event_c_search_tb1KeyReleased

    private void c_adrjTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_adrjTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_adrjTextField1ActionPerformed

    private void c_typjTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_typjTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_typjTextField1ActionPerformed

    private void c_cperjTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_cperjTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_cperjTextField1ActionPerformed

    private void c_pmobjTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c_pmobjTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c_pmobjTextField1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField c_adr;
    private javax.swing.JTextField c_cper;
    private javax.swing.JTextField c_name;
    private javax.swing.JTextField c_pmob;
    private javax.swing.JTextField c_search;
    private javax.swing.JTextField c_search_tb1;
    private javax.swing.JTextField c_tp;
    private javax.swing.JTextField c_typ;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
