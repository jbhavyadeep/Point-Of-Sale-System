/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package point.of.sale.system;
import java.awt.HeadlessException;
import java.sql.*;
import java.text.MessageFormat;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author jbhav
 */
public class product extends javax.swing.JPanel {
    private final Statement s;
    String cat_id;
    private final int flag;
    
    
    public product(Statement s, int flag)throws SQLException {
        this.s = s;
        this.flag = flag;
        initComponents();
        tb_load();
        supplier_load();
        loadProduct();
        comboLoadCategory();// load category to save product
        
        
        hideField();
       
    }

    public void hideField(){
         if(flag==2){
        product_type.setVisible(true);
        p_type.setVisible(true);
         }else{
              product_type.setVisible(false);
        p_type.setVisible(false);
         }
         
    }
    public void comboLoadCategory(){
        try{
            
            //Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery(" SELECT * FROM category");
            Vector v = new Vector();
            while(rs.next()){
                v.add(rs.getString("Name"));
                DefaultComboBoxModel dcom = new DefaultComboBoxModel(v);
                com_Cat.setModel(dcom);
                
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        
    }
    public void tb_load(){
        
        
        
        try{
            DefaultTableModel dt = (DefaultTableModel)jTable1.getModel();
            dt.setRowCount(0);
            
           // Statement s = db.mycon().createStatement();
           ResultSet rs;
           if(flag==2){
              rs = s.executeQuery("SELECT * FROM product");
           }else{
                rs = s.executeQuery(" SELECT * FROM product where Default_Type = 1");
               
           }
            
            while (rs.next()){
                Vector v = new Vector();
                
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getString(5));
                v.add(rs.getString(6));
                v.add(rs.getString(7));
                v.add(rs.getString(8));
                v.add(rs.getString(9));
                v.add(rs.getString(10));
                v.add(rs.getString(11));
                v.add(rs.getString(12));
                v.add(rs.getString(13));
                v.add(rs.getString(14));
                v.add(rs.getString(15));

                dt.addRow(v);
                
            }
            
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void productSearch(){
         String name = p_search_tb1.getText();
         String bcode = ps_barcode.getText();
         

         String supName = ps_supname.getText();
         
        
        try{
            DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
            //Statement s = db.mycon().createStatement();
            dt.setRowCount(0);

            ResultSet rs = s.executeQuery(" SELECT * FROM product WHERE product_name LIKE '%"+name+"%' AND Bar_code LIKE '%"+bcode+"%' AND supplier_name LIKE '%"+supName+"%'");
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                v.add(rs.getString(3));
                v.add(rs.getString(4));
                v.add(rs.getString(5));
                v.add(rs.getString(6));
                v.add(rs.getString(7));
                v.add(rs.getString(8));
                v.add(rs.getString(9));
                v.add(rs.getString(10));
                v.add(rs.getString(11));
                v.add(rs.getString(12));
                v.add(rs.getString(13));
                v.add(rs.getString(14));
                v.add(rs.getString(15));

                dt.addRow(v);

            }

        }catch (SQLException e){
            tb_load();
            System.out.println(e);
        }
    }
    
    
    public void supplier_load(){
        //all supplier load to com_sup combo box
        try{
            //Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM supplier");
            Vector v = new Vector();

        while(rs.next()){
        v.add(rs.getString("supplier_name"));
        DefaultComboBoxModel cm = new DefaultComboBoxModel(v);
        com_sup.setModel(cm);
        }
        
        } catch(Exception e){
            System.out.print(e);
        }
       
        
    }
    
    public void loadProduct(){
        try{
           
            DefaultTableModel dt = (DefaultTableModel) jTable2.getModel();
            dt.setRowCount(0);
            
            //Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM category");
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString(1));
                
                v.add(rs.getString(2));
                dt.addRow(v);
                
                       
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jMenu1 = new javax.swing.JMenu();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        p_search = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        p_search_tb1 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        ps_barcode = new javax.swing.JTextField();
        ps_supname = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        p_bcode = new javax.swing.JTextField();
        p_name = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        p_price = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        p_qty = new javax.swing.JTextField();
        p_sid = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        p_Seprice = new javax.swing.JTextField();
        com_sup = new javax.swing.JComboBox<>();
        p_cat = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        com_Cat = new javax.swing.JComboBox<>();
        mf_date = new com.toedter.calendar.JDateChooser();
        jLabel14 = new javax.swing.JLabel();
        exp_date = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        p_unit = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        product_type = new javax.swing.JLabel();
        p_type = new javax.swing.JTextField();
        p_brand = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        p_des = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        t_id = new javax.swing.JTextField();
        t_name = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jScrollPane4.setViewportView(jTextPane1);

        jMenu1.setText("jMenu1");

        jPanel4.setBackground(new java.awt.Color(167, 178, 194));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        p_search.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        p_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_searchActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Product ID : ");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Product Info :");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Name :");

        p_search_tb1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        p_search_tb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_search_tb1ActionPerformed(evt);
            }
        });
        p_search_tb1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                p_search_tb1KeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                p_search_tb1KeyReleased(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel19.setText("Barcode :");

        ps_barcode.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ps_barcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ps_barcodeActionPerformed(evt);
            }
        });
        ps_barcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ps_barcodeKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ps_barcodeKeyReleased(evt);
            }
        });

        ps_supname.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ps_supname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ps_supnameActionPerformed(evt);
            }
        });
        ps_supname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ps_supnameKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ps_supnameKeyReleased(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel21.setText("Supplier :");

        jTable1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Product Name", "Bar Code", "Cost Price", "Sell Price", "Quantity", "Supplier ID", "Supplier Name", "Default Unit", "Default Type", "Manufacture Date", "Expiration Date", "Category", "Brand Name", "Description"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(25, 25, 25)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(ps_barcode, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                                    .addComponent(ps_supname, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                                    .addComponent(p_search_tb1, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)))
                            .addComponent(jLabel3)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(p_search, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addGap(14, 14, 14))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(p_search_tb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ps_barcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ps_supname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(p_search, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(188, 195, 205));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Name :");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Bar Code :");

        p_bcode.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        p_bcode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_bcodeActionPerformed(evt);
            }
        });

        p_name.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        p_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_nameActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(70, 147, 176));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Update");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(175, 19, 20));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Cost Price :");

        p_price.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        p_price.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_priceActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Quantity :");

        p_qty.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        p_qty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_qtyActionPerformed(evt);
            }
        });

        p_sid.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        p_sid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_sidActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Supplier ID :");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Sell Price :");

        p_Seprice.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        p_Seprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_SepriceActionPerformed(evt);
            }
        });

        com_sup.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Supplier" }));
        com_sup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                com_supActionPerformed(evt);
            }
        });

        p_cat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        p_cat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_catActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Category :");

        com_Cat.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Category", " " }));
        com_Cat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                com_CatActionPerformed(evt);
            }
        });

        mf_date.setDateFormatString("dd-MM-yyyy");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("MFD Date :");

        exp_date.setDateFormatString("dd-MM-yyyy");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("EXP Date :");

        p_unit.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        p_unit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_unitActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Default Unit :");

        product_type.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        product_type.setText("Default Type :");

        p_type.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        p_type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_typeActionPerformed(evt);
            }
        });

        p_brand.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        p_brand.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p_brandActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel17.setText("Brand Name :");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel18.setText("Description :");

        p_des.setColumns(20);
        p_des.setRows(3);
        jScrollPane5.setViewportView(p_des);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(product_type, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(p_Seprice, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(p_bcode, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                                .addComponent(p_name)
                                .addComponent(p_price))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(p_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(p_unit, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(p_sid, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(p_cat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(com_Cat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(com_sup, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(p_brand, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(exp_date, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(mf_date, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(119, 119, 119))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(p_type, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(415, 415, 415)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(35, 35, 35)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(46, 46, 46)
                        .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(418, 418, 418))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(com_Cat, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(p_cat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(p_sid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(com_sup, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(p_brand, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(mf_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(exp_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(p_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(p_bcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(p_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(p_Seprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(p_qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(p_unit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(product_type)
                            .addComponent(p_type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(47, 47, 47)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Add Product", jPanel5);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Search Name :");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("New Name :");

        t_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_idActionPerformed(evt);
            }
        });
        t_id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t_idKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                t_idKeyTyped(evt);
            }
        });

        t_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                t_nameKeyReleased(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(151, 177, 49));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Save");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(new java.awt.Color(70, 147, 176));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Update");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(175, 19, 20));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Delete");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jTable2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Name"
            }
        ));
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane3)
                .addContainerGap())
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel11)
                .addGap(35, 35, 35)
                .addComponent(t_id, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(t_name, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(jButton5)
                .addGap(18, 18, 18)
                .addComponent(jButton6)
                .addGap(25, 25, 25)
                .addComponent(jButton7)
                .addGap(39, 39, 39))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(t_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jLabel12)
                    .addComponent(t_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Add Category", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1156, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // click pon item table
        int r = jTable2.getSelectedRow();

        cat_id = jTable2.getValueAt(r, 0).toString();
        String name = jTable2.getValueAt(r, 1).toString();
        t_name.setText(name);

    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // Delete category button
        try{
           // Statement s = db.mycon().createStatement();
            s.executeUpdate("DELETE FROM category WHERE id = '"+cat_id+"'");
            JOptionPane.showMessageDialog(null, "Data Deleteted");
            t_name.setText("");
            loadProduct();
        }catch(Exception e){

        }

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // Update category button
        String name = t_name.getText();
        try{
           // Statement s = db.mycon().createStatement();
            s.executeUpdate("UPDATE category SET Name = '"+name+"' WHERE id = '"+cat_id+"'");
            JOptionPane.showMessageDialog(null, "Data Updated");
            t_name.setText("");
            loadProduct();
        }catch(Exception e){
            System.out.println(e);
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        //save  category button

        String id  = t_id.getText();
        String name  = t_name.getText();

        try{
            //Statement s = db.mycon().createStatement();
            s.executeUpdate("INSERT INTO category (Name) VALUES ('"+name+"')");
            JOptionPane.showMessageDialog(null, "Data Saved");
            t_name.setText("");
            loadProduct();
        }catch(SQLException e){
            System.out.println(e);
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void t_nameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_nameKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_t_nameKeyReleased

    private void t_idKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_idKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_t_idKeyTyped

    private void t_idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_t_idKeyReleased
        //SEARCH
        String search = t_id.getText();
        try{
            DefaultTableModel dt = (DefaultTableModel) jTable2.getModel();
            dt.setRowCount(0);
           // Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM category WHERE name LIKE  '%"+search+"%'");
            while(rs.next()){
                Vector v = new Vector();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                dt.addRow(v);
            }

        }catch(SQLException e){
            System.out.println(e);
        }
    }//GEN-LAST:event_t_idKeyReleased

    private void t_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t_idActionPerformed

    private void p_brandActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_brandActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p_brandActionPerformed

    private void p_unitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_unitActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p_unitActionPerformed

    private void com_CatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_com_CatActionPerformed
        //
        p_cat.setText(com_Cat.getSelectedItem().toString());
    }//GEN-LAST:event_com_CatActionPerformed

    private void p_catActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_catActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p_catActionPerformed

    private void com_supActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_com_supActionPerformed
        // TODO add your handling code here:

        try{
            //Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM  supplier WHERE supplier_name = '"+com_sup.getSelectedItem().toString()+"'");
            if(rs.next())
            {
                p_sid.setText(rs.getString("sid"));
            }
        }catch(SQLException e){
            System.out.println("e");
        }
    }//GEN-LAST:event_com_supActionPerformed

    private void p_SepriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_SepriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p_SepriceActionPerformed

    private void p_sidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_sidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p_sidActionPerformed

    private void p_qtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_qtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p_qtyActionPerformed

    private void p_priceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_priceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p_priceActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // Delete button

        String id = p_search.getText();
        try{
           // Statement s = db.mycon().createStatement();
            s.executeUpdate("DELETE FROM product WHERE pid ='"+id+"'");

            JOptionPane.showMessageDialog(null, "Data Deleted");
        }catch(SQLException e){
            System.out.println(e);
        }
        tb_load();

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // Update button
        // `pid`, `product_name`, `Bar_code`, `price`, `quantity`, `Sid`
        String id = p_search.getText();
        String name = p_name.getText();
        String bcode = p_bcode.getText();
        String price = p_price.getText();
        String qty = p_qty.getText();
        String sid = p_sid.getText();
        String sprice = p_Seprice.getText();
        String unit = p_unit.getText();
        String type = p_type.getText();
        //SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        //  String mfdate = sdf.format(mf_date.getDate());
        //String exdate = sdf.format(exp_date.getDate());
        LocalDate mfLocalDate = mf_date.getDate().toInstant()
        .atZone(java.time.ZoneId.systemDefault())
        .toLocalDate();
        LocalDate exLocalDate = exp_date.getDate().toInstant()
        .atZone(java.time.ZoneId.systemDefault())
        .toLocalDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String mfdate = mfLocalDate.format(formatter);
        String exdate = exLocalDate.format(formatter);
        String category = p_cat.getText();
        String brand = p_brand.getText();
        String descript = p_des.getText();

        String sname = com_sup.getSelectedItem().toString();

        try{
           // Statement s = db.mycon().createStatement();
            s.executeUpdate("UPDATE product SET product_name='"+name+"',Bar_code ='"+bcode+"', price ='"+sprice+"', Sell_price ='"+price+"', quantity ='"+qty+"', sid ='"+sid+"', supplier_name ='"+sname+"',default_unit= '"+unit+"', default_type= '"+type+"', mf_date =  '"+mfdate+"',  exp_date = '"+exdate+"',  category = '"+category+"',  brand_name = '"+brand+"',  description ='"+descript+"' WHERE pid='"+id+"'");

            JOptionPane.showMessageDialog(null, "Data Updated");

        }catch(Exception e){
            System.out.println(e);
        }
        tb_load();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // save button

        String name = p_name.getText();
        String bcode = p_bcode.getText();
        String price = p_price.getText();
        String qty = p_qty.getText();
        String sid = p_sid.getText();
        String sprice = p_Seprice.getText();
        String unit = p_unit.getText();
        String type = p_type.getText();
        // SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        //String mfdate = sdf.format(mf_date.getDate());
        // String exdate = sdf.format(exp_date.getDate());
        LocalDate mfLocalDate = mf_date.getDate().toInstant()
        .atZone(java.time.ZoneId.systemDefault())
        .toLocalDate();
        LocalDate exLocalDate = exp_date.getDate().toInstant()
        .atZone(java.time.ZoneId.systemDefault())
        .toLocalDate();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String mfdate = mfLocalDate.format(formatter);
        String exdate = exLocalDate.format(formatter);
        String category = p_cat.getText();
        String brand = p_brand.getText();
        String descript = p_des.getText();

        String sname = com_sup.getSelectedItem().toString();

        // `pid`, `product_name`, `Bar_code`, `price`, `quantity`, `Sid`
        try{
           // Statement s = db.mycon().createStatement();
            s.executeUpdate("INSERT INTO product (product_name, Bar_code, price, Sell_price, quantity, Sid, supplier_name, default_unit, default_type, mf_date, exp_date, category, brand_name, description) "
                + "VALUES ('"+name+"','"+bcode+"','"+price+"','"+sprice+"','"+qty+"','"+sid+"', '"+sname+"', '"+unit+"', '"+type+"', '"+mfdate+"', '"+exdate+"', '"+category+"', '"+brand+"', '"+descript+"')");
            JOptionPane.showMessageDialog(null,"Data Saved");

        }catch(HeadlessException | SQLException e){
            System.out.println(e);
        }

        tb_load();
        
//        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
//        MessageFormat header = new MessageFormat("Header: Default Printer Example");
//        MessageFormat footer = new MessageFormat("Page {0}");
//        PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
//
//
//        try {
//            p_des.print(header,footer,false,defaultService, attributes,false);
//
//        } catch (Exception e) {
//        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void p_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p_nameActionPerformed

    private void p_bcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_bcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p_bcodeActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        // Ttable clicked on copntent

    }//GEN-LAST:event_jTable1KeyPressed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // mouse clicked

        int r = jTable1.getSelectedRow();

        String id = jTable1.getValueAt(r, 0).toString();
        String name = jTable1.getValueAt(r, 1).toString();
        String bcode = jTable1.getValueAt(r, 2).toString();
        String price = jTable1.getValueAt(r, 3).toString();
        String sprice = jTable1.getValueAt(r, 4).toString();
        String qty = jTable1.getValueAt(r, 5).toString();
        String sid = jTable1.getValueAt(r, 6).toString();
        String sname = jTable1.getValueAt(r, 7).toString();
        String dunit = jTable1.getValueAt(r, 8).toString();
        String dtype = jTable1.getValueAt(r, 9).toString();
        String mfDate = jTable1.getValueAt(r, 10).toString();
        String ExpDate = jTable1.getValueAt(r, 11).toString();
        String cate = jTable1.getValueAt(r, 12).toString();
        String bname = jTable1.getValueAt(r, 13).toString();
        String desc = jTable1.getValueAt(r, 14).toString();

        p_search.setText(id);
        p_name.setText(name);
        p_bcode.setText(bcode);
        p_price.setText(price);
        p_qty.setText(qty);
        p_sid.setText(sid);
        p_Seprice.setText(sprice);
        p_unit.setText(dunit);
        p_type.setText(dtype);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try {
            mf_date.setDate(sdf.parse(mfDate));
            exp_date.setDate(sdf.parse(ExpDate));
        } catch (ParseException ex) {
            System.out.println(ex);
        }

        p_cat.setText(cate);
        p_brand.setText(bname);
        p_des.setText(desc);
        //p_sname.setText(rs.getString("supplier_name"));

        //p_sname.setText(sname);
        com_sup.setSelectedItem(sname);

    }//GEN-LAST:event_jTable1MouseClicked

    private void ps_supnameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ps_supnameKeyReleased
        // TODO add your handling code here:
        productSearch();
    }//GEN-LAST:event_ps_supnameKeyReleased

    private void ps_supnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ps_supnameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ps_supnameKeyPressed

    private void ps_supnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ps_supnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ps_supnameActionPerformed

    private void ps_barcodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ps_barcodeKeyReleased
        // TODO add your handling code here:
        productSearch();
    }//GEN-LAST:event_ps_barcodeKeyReleased

    private void ps_barcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ps_barcodeKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_ps_barcodeKeyPressed

    private void ps_barcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ps_barcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ps_barcodeActionPerformed

    private void p_search_tb1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_p_search_tb1KeyReleased
        // search table content
        productSearch();
    }//GEN-LAST:event_p_search_tb1KeyReleased

    private void p_search_tb1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_p_search_tb1KeyPressed
        //
    }//GEN-LAST:event_p_search_tb1KeyPressed

    private void p_search_tb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_search_tb1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p_search_tb1ActionPerformed

    private void p_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p_searchActionPerformed

    private void p_typeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_typeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p_typeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> com_Cat;
    private javax.swing.JComboBox<String> com_sup;
    private com.toedter.calendar.JDateChooser exp_date;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextPane jTextPane1;
    private com.toedter.calendar.JDateChooser mf_date;
    private javax.swing.JTextField p_Seprice;
    private javax.swing.JTextField p_bcode;
    private javax.swing.JTextField p_brand;
    private javax.swing.JTextField p_cat;
    private javax.swing.JTextArea p_des;
    private javax.swing.JTextField p_name;
    private javax.swing.JTextField p_price;
    private javax.swing.JTextField p_qty;
    private javax.swing.JTextField p_search;
    private javax.swing.JTextField p_search_tb1;
    private javax.swing.JTextField p_sid;
    private javax.swing.JTextField p_type;
    private javax.swing.JTextField p_unit;
    private javax.swing.JLabel product_type;
    private javax.swing.JTextField ps_barcode;
    private javax.swing.JTextField ps_supname;
    private javax.swing.JTextField t_id;
    private javax.swing.JTextField t_name;
    // End of variables declaration//GEN-END:variables
}
