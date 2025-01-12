/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package point.of.sale.system;

import com.lowagie.text.pdf.Barcode;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Objects;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jbhav
 */
public class sales extends javax.swing.JPanel {

    public static String barcode_c =null;
    public static String cus_id = "0";
    public static String pro_qty = "0";
    public static String sale_qty = "0";
    public static String Select_product_name =null;
    
    public Double Stock_qty = 0.0;
    
    
    public sales() {
        initComponents();
        data_load();
        Customer_balance_check();

    }
    
    
    public void data_load(){
        //load customer
        
        try{
            Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM customer");
            
            Vector v = new Vector();
            
            while(rs.next()){
                v.add(rs.getString("customer_name"));
                DefaultComboBoxModel com = new DefaultComboBoxModel(v);
                
                com_cus.setModel(com);
            
            }
            
        }
        catch(SQLException e){
            System.out.println(e);
        }
        
         //load Product
        
        try{
            Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM product");
            
            Vector v = new Vector();
            
            while(rs.next()){
                v.add(rs.getString("product_name"));
                DefaultComboBoxModel com = new DefaultComboBoxModel(v);
                
                com_pro.setModel(com);
            
            }
            
        }
        catch(SQLException e){
            System.out.println(e);
        }
        //load latest invoice number
        try{
            Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM extra WHERE exid = 1");
            
            if(rs.next()){
                inid.setText(rs.getString("val"));
            }
            
        }catch(SQLException e){
            System.out.println(e);
        }
        //plus new invoice
        int i = Integer.valueOf(inid.getText());
        i++;
        inid.setText(String.valueOf(i));
        
    }
    public void pro_tot_cal(){
        //product price calculator
        try{
        Double qt = Double.valueOf(p_qty.getText());
        Double price = Double.valueOf(u_price.getText());
        Double total;
        
        total = qt * price;
        
        tot_price.setText(String.valueOf(total));
        }catch(NumberFormatException e){
            System.out.println(e);
        }    
        
        
    }
   
    public void cart_total(){
        
        int numOfRow = jTable1.getRowCount();
        
        double total = 0;
        
        for(int i =0;i<numOfRow;i++){
            double value = Double.valueOf(jTable1.getValueAt(i, 5).toString());
            total += value;
        }
        
        bill_tot.setText(Double.toString(total));
        
        //total quantity count
        int numOfRows = jTable1.getRowCount();
        
        double totals = 0;
        
        for(int i = 0; i<numOfRows; i++){
            double values = Double.valueOf(jTable1.getValueAt(i, 3).toString());
            totals += values;
        }
        
        tot_qty.setText(Double.toString(totals));
        
    }
    
    
    public void tot(){
       DecimalFormat df = new DecimalFormat("00.00");
       Double paid = Double.valueOf(paid_amt.getText());
       Double tot = Double.valueOf(bill_tot.getText());
       Double discount = Double.valueOf(disc_per.getText());
       
       
        
       Double due = paid-(tot-(discount*tot/100.00));
       
       bln_due.setText(String.valueOf(df.format(due)));
        
     
        
    }
    
    public void stckup(){
        //get all table product id and sell qty
        DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
        
        int rc = dt.getRowCount();
        for(int i = 0;i<rc;i++){
            String bcode = dt.getValueAt(i, 2).toString();//id or barcode
            String sell_qty  = dt.getValueAt(i, 3).toString();
            
            
            System.out.println(bcode);
            System.out.println(sell_qty);
            
            try{
                Statement s = db.mycon().createStatement();
                ResultSet rs = s.executeQuery("SELECT quantity FROM product WHERE Bar_code ='"+bcode+"'");
                if (rs.next()) {
                   Stock_qty = Double.valueOf(rs.getString("quantity"));
                }
                
            }catch(Exception e){
                System.out.print(e);
            }
            Double st_qty = Stock_qty;
            Double sel_qty = Double.valueOf(sell_qty);
            Double new_qty = st_qty - sel_qty;
            String nqty = String.valueOf(new_qty);
            try{
                Statement ss = db.mycon().createStatement();
                ss.executeUpdate("UPDATE product SET quantity = '"+nqty+"' WHERE Bar_code='"+bcode+"' ");
                

            }catch(Exception e){
                System.out.print(e);
            }
            
            
        }
        
    }
  
    
     public void add_to_cart(){
          if(Double.parseDouble(sale_qty)<=Double.parseDouble(pro_qty)){
             DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
        Vector v = new Vector();
        
        v.add(inid.getText());//invoice id
        v.add(com_pro.getSelectedItem().toString());//product name
       // v.add(Select_product_name);
       
        v.add(barcode_c);//barcode
        v.add(p_qty.getText());// product qty
        v.add(u_price.getText());// unit price
       
        v.add(tot_price.getText());// total price
        
        
        dt.addRow(v);
        cart_total();
        tot();
        }
        else{
            JOptionPane.showMessageDialog(null,"Quantity left in Stock: "+pro_qty);
            

        }
     }
     
     public void Customer_balance_check(){
        
         
         //String name = com_cus.getSelectedItem().toString();
                
        try{
            Statement s = db.mycon().createStatement();
            
            ResultSet rs = s.executeQuery("SELECT SUM(Balance) FROM sales WHERE Cid ='"+cus_id+"'");
            
            if(rs.next()){
                Cus_Balance.setText(rs.getString("SUM(Balance)"));
            }
            
        if(Double.parseDouble(Cus_Balance.getText()) < 0){
            Cus_Balance.setForeground(Color.red);
        }else{
                Cus_Balance.setForeground(new java.awt.Color(0, 150, 100));
                }
        pro_tot_cal();
        tot();
            
        }catch(SQLException e){
            System.out.println(e);
        }
         
     }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        inid = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        u_price = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        com_cus = new javax.swing.JComboBox<>();
        com_pro = new javax.swing.JComboBox<>();
        p_qty = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        tot_price = new javax.swing.JLabel();
        l_stqty = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        Cus_Balance = new javax.swing.JLabel();
        p_barcode = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        paid_amt = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        bill_tot = new javax.swing.JLabel();
        bln_due = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tot_qty = new javax.swing.JLabel();
        disc_per = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("INVOICE NO : ");

        inid.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        inid.setText("01");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inid, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(inid))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Customer : ");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Qty:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Product : ");

        u_price.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        u_price.setText("00.00");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Unit Price : ");

        com_cus.setEditable(true);
        com_cus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        com_cus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select" }));
        com_cus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                com_cusActionPerformed(evt);
            }
        });

        com_pro.setEditable(true);
        com_pro.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        com_pro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select" }));
        com_pro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                com_proActionPerformed(evt);
            }
        });
        com_pro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                com_proKeyReleased(evt);
            }
        });

        p_qty.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        p_qty.setText("0");
        p_qty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_qtyActionPerformed(evt);
            }
        });
        p_qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                p_qtyKeyReleased(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Total Price");

        tot_price.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tot_price.setText("00.00");

        l_stqty.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        l_stqty.setText("0");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Stock Qty :");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Balance :");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Barcode :");

        Cus_Balance.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Cus_Balance.setText("0");

        p_barcode.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        p_barcode.setText("0");
        p_barcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_barcodeActionPerformed(evt);
            }
        });
        p_barcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                p_barcodeKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                p_barcodeKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(p_barcode, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114)
                        .addComponent(jLabel10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(com_cus, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Cus_Balance, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(com_pro, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(l_stqty, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(65, 65, 65)
                        .addComponent(jLabel7))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(p_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(u_price, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(tot_price, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(p_qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(com_pro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(com_cus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Cus_Balance, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tot_price)
                        .addComponent(jLabel13))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(u_price)
                        .addComponent(jLabel7))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(p_barcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)
                        .addComponent(l_stqty)
                        .addComponent(jLabel11)))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "INID", "Name", "Barcode", "Qty", "Unit Price", "Total Price"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Add to Cart");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Remove");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("Remove All");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 102, 102));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(242, 242, 242));
        jButton4.setText("Pay & Print");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jButton1)
                .addGap(29, 29, 29)
                .addComponent(jButton2)
                .addGap(30, 30, 30)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        paid_amt.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        paid_amt.setText("0");
        paid_amt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paid_amtActionPerformed(evt);
            }
        });
        paid_amt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paid_amtKeyReleased(evt);
            }
        });

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Total Amount :");

        bill_tot.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bill_tot.setText("00.00");
        bill_tot.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        bln_due.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bln_due.setText("00.00");
        bln_due.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Balance/Due :");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bln_due, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(bill_tot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(bill_tot))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(bln_due))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Paid Amount :");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Total Qty :");

        tot_qty.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tot_qty.setText("00");

        disc_per.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        disc_per.setText("0");
        disc_per.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disc_perActionPerformed(evt);
            }
        });
        disc_per.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                disc_perKeyReleased(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Discount :");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("Payment Type :");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Gpay/ UPI", "Check" }));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(paid_amt, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(disc_per, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(tot_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tot_qty))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(paid_amt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(disc_per, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14)))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // add cart to product details;
       add_to_cart();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // remove product entry cart
        try{
            DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
            
            int rw = jTable1.getSelectedRow();
            dt.removeRow(rw);
            
        }catch(Exception e){
            System.out.println(e);
        }

        cart_total(); 
        tot();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // remove all products
        
        DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
        dt.setRowCount(0);
        cart_total(); 
        tot();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void paid_amtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paid_amtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paid_amtActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // data send to databse
        // `cartid`, `INID`, `product_name`, `Bar_code`, `qty`, `Unit_price`, `Total_Price`
        
        try{
            DefaultTableModel dt = (DefaultTableModel)jTable1.getModel();
            int rc = dt.getRowCount();
            
            for(int i =0;i<rc;i++){
                String inid = dt.getValueAt(i, 0).toString();
                String P_name = dt.getValueAt(i, 1).toString();
                String bar_code = dt.getValueAt(i, 2).toString();
                String qty = dt.getValueAt(i, 3).toString();
                String un_price = dt.getValueAt(i, 4).toString();
                String tot_price = dt.getValueAt(i, 5).toString();

                
                //cart DB
                Statement s = db.mycon().createStatement();
                s.executeUpdate("INSERT INTO cart (INID, product_name, Bar_code, qty, Unit_price, Total_Price) VALUES ('"+inid+"', '"+P_name+"','"+bar_code+"','"+qty+"','"+un_price+"', '"+tot_price+"') ");
                
            }
            
            
            JOptionPane.showMessageDialog(null,"Data Saved");
            
            
            
        }catch(HeadlessException | SQLException e){
            System.out.println(e);
        }
        try{
             //sales DB
                // `saleid`, `INID`, `Cid`, `customer_name`, `Total_Qty`, `Total_Bill`, `Status`, `Balance`
                String inv_id = inid.getText();
                String cname = com_cus.getSelectedItem().toString();
                String totqty = tot_qty.getText();
                String tot_bil = bill_tot.getText();
                String blnc = bln_due.getText();
                
                //paid check
                
               Double tot = Double.valueOf(bill_tot.getText());
               Double pid = Double.valueOf(paid_amt.getText());
                
               String Status =null;
               if(pid.equals(0.0)){
                   Status = "UnPaid";
                   
               }else if(tot>pid){
                  Status = "Partial"; 
                  
               }else if(tot<pid){
                   Status = "Paid MORE";
               }else if(pid.equals(tot)){
                   Status = "Paid";
               }
                
                Statement ss = db.mycon().createStatement();
                
                ss.executeUpdate("INSERT INTO sales (INID, Cid, customer_name, Total_Qty, Total_Bill, Status, Balance) VALUES ('"+inv_id+"', '"+cus_id+"', '"+cname+"', '"+totqty+"', '"+tot_bil+"', '"+Status+"', '"+blnc+"')");
                
        }catch(NumberFormatException | SQLException e){
            System.out.println(e);
        }
        
        try{
            Statement s = db.mycon().createStatement();
            
            String id = inid.getText();
            s.executeUpdate("UPDATE extra SET val='"+id+"' WHERE exid = 1");
        }catch(SQLException e){
            System.out.println(e);
        }
        
        //print or view bill
        
        try{
            
        HashMap para = new HashMap();
        para.put("inv_id", inid.getText()); // inv_id is ireport parameter
        
        ReportView r = new ReportView("src\\reports\\print.jasper",para);
        r.setVisible(true);
            
        }catch(Exception e){
            System.out.println(e);
        }
        stckup();// Stock update;
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void com_proActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_com_proActionPerformed
        // load unit price when product selected
        
        String name = com_pro.getSelectedItem().toString();
                
        try{
            Statement s = db.mycon().createStatement();
            
            ResultSet rs = s.executeQuery("SELECT Bar_code, Sell_price, quantity FROM product WHERE product_name ='"+name+"'");
            
            if(rs.next()){
                u_price.setText(rs.getString("Sell_price"));
                barcode_c= rs.getString("Bar_code");
                pro_qty = rs.getString("quantity");
                l_stqty.setText(pro_qty);
                p_barcode.setText(barcode_c);
               // Select_product_name = rs.getString("product_name");

                
            }
            
        
        pro_tot_cal();
        tot();
            
        }catch(SQLException e){
            System.out.println(e);
        }
        p_qty.setText("1");
        p_qty.requestFocus();
    }//GEN-LAST:event_com_proActionPerformed

    private void p_qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_p_qtyKeyReleased
         pro_tot_cal(); // Perform total calculation
    sale_qty = p_qty.getText(); // Get the input text

    if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        try {
            // Validate the input for p_qty
            Double qt = Double.valueOf(p_qty.getText());
            
            // If valid, proceed to add the item to the cart
            add_to_cart();

            // Reset the barcode field and focus for the next entry
            p_barcode.setText("");
            p_barcode.requestFocus();
            barcode_c = null;
        } catch (NumberFormatException e) {
            // Prompt the user to enter the quantity again
            while (true) {
                String newQty = JOptionPane.showInputDialog(
                    null, 
                    "Invalid quantity. Please enter a valid number:", 
                    "Input Error", 
                    JOptionPane.ERROR_MESSAGE
                );

                if (newQty != null) { // Check if user did not cancel
                    try {
                        Double qt = Double.valueOf(newQty); // Validate new input
                        p_qty.setText(newQty); // Update the input field
                        break; // Exit the loop when valid input is provided
                    } catch (NumberFormatException ex) {
                        // Loop again if the input is invalid
                        JOptionPane.showMessageDialog(
                            null, 
                            "Invalid input. Please enter a valid number.", 
                            "Input Error", 
                            JOptionPane.ERROR_MESSAGE
                        );
                    }
                } else {
                    // If user cancels, reset to default value
                    p_qty.setText("1");
                    break;
                }
            }
        }
    }
    }//GEN-LAST:event_p_qtyKeyReleased

    private void paid_amtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paid_amtKeyReleased
        tot();
        
    }//GEN-LAST:event_paid_amtKeyReleased

    private void com_proKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_com_proKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_com_proKeyReleased

    private void com_cusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_com_cusActionPerformed
        // load customr info 
        
           String name = com_cus.getSelectedItem().toString();
                
        try{
            Statement s = db.mycon().createStatement();
            
            ResultSet rs = s.executeQuery("SELECT cid,customer_name FROM customer WHERE customer_name ='"+name+"'");
            
            if(rs.next()){
                cus_id = rs.getString("cid");
            }
            
        
        pro_tot_cal();
        tot();
        Customer_balance_check();
            
        }catch(SQLException e){
            System.out.println(e);
        }
    }//GEN-LAST:event_com_cusActionPerformed

    private void p_qtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_qtyActionPerformed
    }//GEN-LAST:event_p_qtyActionPerformed

    private void p_barcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_barcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p_barcodeActionPerformed

    private void p_barcodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_p_barcodeKeyReleased
        // 
        String bcode = p_barcode.getText();
                
        try{
            Statement s = db.mycon().createStatement();
            
            ResultSet rs = s.executeQuery("SELECT Bar_code, Sell_price, quantity,product_name FROM product WHERE Bar_code ='"+bcode+"'");
            
            if(rs.next()){
                u_price.setText(rs.getString("Sell_price"));
                barcode_c= rs.getString("Bar_code");
                pro_qty = rs.getString("quantity");
                l_stqty.setText(pro_qty);
                //Select_product_name = rs.getString("product_name");
                com_pro.setSelectedItem(rs.getString("product_name"));
            }
            
        
        pro_tot_cal();
        tot();
        
      
        }catch(SQLException e){
            System.out.println(e);
        }
         if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            if(barcode_c==null){
                JOptionPane.showMessageDialog(null,"No product barcode entered");
            } 
            else{
            add_to_cart();
            barcode_c = null;  
            }
            
            //p_qty.setText("1");
           // p_qty.requestFocus();
                   
        }
        
    }//GEN-LAST:event_p_barcodeKeyReleased

    private void disc_perActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disc_perActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_disc_perActionPerformed

    private void disc_perKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_disc_perKeyReleased
        // TODO add your handling code here:
        tot();
    }//GEN-LAST:event_disc_perKeyReleased

    private void p_barcodeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_p_barcodeKeyTyped
        // TODO add your handling code here:
     

    }//GEN-LAST:event_p_barcodeKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cus_Balance;
    private javax.swing.JLabel bill_tot;
    private javax.swing.JLabel bln_due;
    private javax.swing.JComboBox<String> com_cus;
    private javax.swing.JComboBox<String> com_pro;
    private javax.swing.JTextField disc_per;
    private javax.swing.JLabel inid;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel l_stqty;
    private javax.swing.JTextField p_barcode;
    private javax.swing.JTextField p_qty;
    private javax.swing.JTextField paid_amt;
    private javax.swing.JLabel tot_price;
    private javax.swing.JLabel tot_qty;
    private javax.swing.JLabel u_price;
    // End of variables declaration//GEN-END:variables
}
