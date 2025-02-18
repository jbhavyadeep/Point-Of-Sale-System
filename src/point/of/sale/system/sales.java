/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package point.of.sale.system;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Objects;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteComboBoxEditor;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author jbhav
 */
public class sales extends javax.swing.JPanel {

    private final Statement s;

    public static String barcode_c = null;
    public static String cus_id = "1";
    public static String pro_qty = "0";
    public static String sale_qty = "1";
    public static String Select_product_name = null;
    public Double Cus_balance = 0.0;
    public Double Stock_qty = 0.0;
    public Double Total_amt = 0.0;
    public Double Settle_amt = 0.0;
    public Double paid = 0.0;
    public Double recieved = 0.0;
    public Double change = 0.0;

    public sales(Statement s) {
        this.s = s;
        initComponents();
        data_load();

        AutoCompleteDecorator.decorate(com_cus);
        AutoCompleteDecorator.decorate(com_pro);
        Customer_balance_check();

    }

    public void data_load() {
        //load customer

        try {
            //Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM customer");
            Vector<String> v = new Vector<>();
           

            while (rs.next()) {
                v.add(rs.getString("customer_name"));

            }
            DefaultComboBoxModel com = new DefaultComboBoxModel(v);

            com_cus.setModel(com);
            com_cus.setRenderer(new CustomComboBoxRenderer("Guest"));

        } catch (SQLException e) {
            System.out.println(e);
        }

        //load Product
        try {
            //Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM product");

            Vector<String> v = new Vector<>();
            v.add("Select a Product"); // Default prompt            
            while (rs.next()) {
                v.add(rs.getString("product_name"));

            }
            DefaultComboBoxModel com = new DefaultComboBoxModel(v);

            com_pro.setModel(com);
            com_pro.setRenderer(new CustomComboBoxRenderer("Select a Product"));

        } catch (SQLException e) {
            System.out.println(e);
        }
        //load latest invoice number
        try {
            // Statement s = db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT * FROM extra WHERE exid = 1");

            if (rs.next()) {
                inid.setText(rs.getString("val"));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        //plus new invoice
        int i = Integer.valueOf(inid.getText());
        i++;
        inid.setText(String.valueOf(i));

    }

    public void pro_tot_cal() {
        //product price calculator
        try {
            Double qt = Double.valueOf(p_qty.getText());
            Double price = Double.valueOf(u_price.getText());
            Double total;

            total = qt * price;

            tot_price.setText(String.valueOf(total));
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

    }

    public void cart_total() {

        int numOfRow = jTable1.getRowCount();

        double total = 0;

        for (int i = 0; i < numOfRow; i++) {
            double value = Double.valueOf(jTable1.getValueAt(i, 5).toString());
            total += value;
        }

        bill_tot.setText(Double.toString(total));

        //total quantity count
        int numOfRows = jTable1.getRowCount();

        double totals = 0;

        for (int i = 0; i < numOfRows; i++) {
            double values = Double.valueOf(jTable1.getValueAt(i, 3).toString());
            totals += values;
        }

        tot_qty.setText(Double.toString(totals));

    }

    public void tot() {
        try {
            DecimalFormat df = new DecimalFormat("00.00");
            paid = Double.valueOf(paid_amt.getText());
            Double tot = Double.valueOf(bill_tot.getText());
            Double discount = Double.valueOf(disc_per.getText());
            Double due = 0.0;

            if (Settle_amt <= 0) {
                due = paid + Settle_amt - (discount * tot / 100.00);
                System.out.println("x");

            } //       else if(Cus_balance>=0){
            //          due =  paid + Cus_balance - Total_amt;
            //        }
            else {
                due = paid - Settle_amt - (discount * tot / 100.00);
                System.out.println("y");

            }
            /// else if(Cus_balance>0){
            //       
            //           due = paid-(tot-(discount*tot/100.00));

            //  }
            bln_due.setText(String.valueOf(df.format(due)));

        } catch (NumberFormatException e) {

        }

    }

    public void changeCal() {
        if (recieved >= (-Settle_amt) && Settle_amt < 0) {
            paid = -Settle_amt;
            change = recieved - paid;
            System.out.println("a");
        } else if (recieved < (-Settle_amt) && recieved >= Total_amt && Settle_amt < 0) {
            paid = Total_amt;
            change = recieved - paid;
            System.out.println("b");

        } else if (recieved == 0 && Settle_amt < 0) {
            paid = recieved;
            change = 0.0;
            System.out.println("d");
        } else {
            paid = Total_amt;
            change = 0.0;
            System.out.println("c");

        }
        paid_amt.setText(paid.toString());
        System.out.println(paid);
        System.out.println(change);
        System.out.println(recieved);
        tot();

    }

    public void stckup() {
        //get all table product id and sell qty
        DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();

        int rc = dt.getRowCount();
        for (int i = 0; i < rc; i++) {
            String bcode = dt.getValueAt(i, 2).toString();//id or barcode
            String sell_qty = dt.getValueAt(i, 3).toString();

            System.out.println(bcode);
            System.out.println(sell_qty);

            try {
                //Statement s = db.mycon().createStatement();
                ResultSet rs = s.executeQuery("SELECT quantity FROM product WHERE Bar_code ='" + bcode + "'");
                if (rs.next()) {
                    Stock_qty = Double.valueOf(rs.getString("quantity"));
                }

            } catch (Exception e) {
                System.out.print(e);
            }
            Double st_qty = Stock_qty;
            Double sel_qty = Double.valueOf(sell_qty);
            Double new_qty = st_qty - sel_qty;
            String nqty = String.valueOf(new_qty);
            try {
                //Statement ss = db.mycon().createStatement();
                s.executeUpdate("UPDATE product SET quantity = '" + nqty + "' WHERE Bar_code='" + bcode + "' ");

            } catch (Exception e) {
                System.out.print(e);
            }

        }

    }

    public void clearcart() {
        p_barcode.setText("");
        com_cus.setSelectedItem("Guest");
        cus_id = "1";
        paid_amt.setText("0");
        disc_per.setText("0");
        bln_due.setText("00.00");
        bill_tot.setText("00.00");
        cus_settleamt.setText("00.00");
        DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
        dt.setRowCount(0);
        cart_total();
        l_stqty.setText("0");
        u_price.setText("00.00");
        tot_price.setText("00.00");
        p_qty.setText("1");
    }

    public void clearfield() {
        l_stqty.setText("0");
        u_price.setText("00.00");
        tot_price.setText("00.00");
        p_qty.setText("1");
    }

    public void add_to_cart() {
        if (Double.parseDouble(sale_qty) > Double.parseDouble(pro_qty)) {
            JOptionPane.showMessageDialog(null, "Quantity left in Stock: " + pro_qty);
            clearfield();
        } //         else if(p_barcode.getText()!= barcode_c){
        //            JOptionPane.showMessageDialog(null,"Enter a valid Barcode: "+pro_qty);
        //
        //         }
        else {
            DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
            Vector v = new Vector();

            v.add(inid.getText());//invoice id
            //v.add(com_pro.getSelectedItem().toString());//product name
            v.add(Select_product_name);

            v.add(barcode_c);//barcode
            v.add(p_qty.getText());// product qty
            v.add(u_price.getText());// unit price

            v.add(tot_price.getText());// total price

            p_barcode.setText("");

            dt.addRow(v);
            cart_total();
            // pro_tot_cal();
            settleAmt();
            tot();

        }

//          if(Double.parseDouble(sale_qty)<=Double.parseDouble(pro_qty)){
//             DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
//        Vector v = new Vector();
//        
//        v.add(inid.getText());//invoice id
//        //v.add(com_pro.getSelectedItem().toString());//product name
//        v.add(Select_product_name);
//       
//        v.add(barcode_c);//barcode
//        v.add(p_qty.getText());// product qty
//        v.add(u_price.getText());// unit price
//       
//        v.add(tot_price.getText());// total price
//        
//        p_barcode.setText("");
//        dt.addRow(v);
//        cart_total();
//        tot();
//        
//        
//        }
//        else{
//            JOptionPane.showMessageDialog(null,"Quantity left in Stock: "+pro_qty);
//            
//
//        }
    }

    public void Customer_balance_check() {

        //String name = com_cus.getSelectedItem().toString();
        try {
            String query = "SELECT Tp_Number FROM customer WHERE cid = '" + cus_id + "'";
            //PreparedStatement ps = db.mycon().prepareStatement(query);
            //ps.setString(1, cus_id);

            ResultSet rs = s.executeQuery(query);

            if (rs.next()) {
                Cus_balance = Double.valueOf(rs.getString("Tp_Number"));
                if (Cus_balance != null) {
                    Cus_Balance.setText(Cus_balance.toString());
                } else {
                    Cus_Balance.setText("0.0");
                }
            } else {
                // No rows found for the given Cid
                Cus_Balance.setText("0.0");
            }

            if (Double.parseDouble(Cus_Balance.getText()) < 0) {
                Cus_Balance.setForeground(Color.red);
            } else {
                Cus_Balance.setForeground(new java.awt.Color(0, 150, 100));
            }
            pro_tot_cal();
            tot();

        } catch (NumberFormatException | SQLException e) {
            System.out.println(e);
            Cus_Balance.setText(Cus_balance.toString());
            Cus_Balance.setForeground(new java.awt.Color(0, 150, 100));
        }

    }

    public void settleAmt() {
        Total_amt = Double.valueOf(bill_tot.getText());

        Settle_amt = Cus_balance - Total_amt;
        cus_settleamt.setText(Settle_amt.toString());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        inid = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        p_barcode = new javax.swing.JTextField();
        com_cus = new javax.swing.JComboBox<>();
        jPanel9 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        l_stqty = new javax.swing.JLabel();
        Cus_Balance = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        u_price = new javax.swing.JLabel();
        com_pro = new javax.swing.JComboBox<>();
        jPanel11 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        p_qty = new javax.swing.JTextField();
        tot_price = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
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
        jLabel16 = new javax.swing.JLabel();
        cus_settleamt = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        cal_chn = new javax.swing.JLabel();

        jPanel2.setBackground(new java.awt.Color(167, 178, 194));
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
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(inid))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(211, 216, 222));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel8.setBackground(new java.awt.Color(211, 216, 222));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Barcode :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Customer : ");

        p_barcode.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
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

        com_cus.setEditable(true);
        com_cus.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        com_cus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select" }));
        com_cus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                com_cusActionPerformed(evt);
            }
        });
        com_cus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                com_cusKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(com_cus, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(p_barcode, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(com_cus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(p_barcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel8Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {com_cus, jLabel11, jLabel3, p_barcode});

        jPanel9.setBackground(new java.awt.Color(211, 216, 222));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Stock Qty :");

        l_stqty.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        l_stqty.setText("0");

        Cus_Balance.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        Cus_Balance.setText("0");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Balance :");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(l_stqty, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Cus_Balance, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                .addGap(49, 49, 49))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cus_Balance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(l_stqty)
                    .addComponent(jLabel10))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBackground(new java.awt.Color(211, 216, 222));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Unit Price : ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Product : ");

        u_price.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        u_price.setText("00.00");

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

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(com_pro, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(11, 11, 11)
                        .addComponent(u_price, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(19, 19, 19)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(com_pro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(u_price)
                    .addComponent(jLabel7))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(211, 216, 222));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Total Price :");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Qty:");

        p_qty.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        p_qty.setText("1");
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

        tot_price.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tot_price.setText("00.00");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(p_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tot_price, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(p_qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tot_price)
                    .addComponent(jLabel13))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
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
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(167, 178, 194));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setBackground(new java.awt.Color(0, 11, 126));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(242, 242, 242));
        jButton1.setText("Add to Cart");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(133, 23, 11));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(242, 242, 242));
        jButton2.setText("Remove");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(133, 23, 11));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(242, 242, 242));
        jButton3.setText("Remove All");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 102, 102));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(242, 242, 242));
        jButton4.setText("Payment Recieved");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(0, 102, 102));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(242, 242, 242));
        jButton5.setText("Print Reciept");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(18, 27, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(18, 27, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 20, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(167, 178, 194));
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

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("Settlement Amount :");

        cus_settleamt.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cus_settleamt.setText("00.00");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setText("Change :");

        cal_chn.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cal_chn.setForeground(new java.awt.Color(0, 51, 255));
        cal_chn.setText("00");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(paid_amt, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(disc_per, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cus_settleamt, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cal_chn, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tot_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel16)
                                .addComponent(cus_settleamt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(tot_qty))))
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(paid_amt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(disc_per, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14)))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(cal_chn)))))
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // add cart to product details;
        add_to_cart();
        settleAmt();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // remove product entry cart
        try {
            DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();

            int rw = jTable1.getSelectedRow();
            dt.removeRow(rw);
            cart_total();
            settleAmt();
            tot();

        } catch (Exception e) {
            System.out.println(e);
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // remove all products

        DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
        dt.setRowCount(0);
        cart_total();
        settleAmt();
        tot();

    }//GEN-LAST:event_jButton3ActionPerformed

    private void paid_amtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paid_amtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_paid_amtActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // data send to databse
        // `cartid`, `INID`, `product_name`, `Bar_code`, `qty`, `Unit_price`, `Total_Price`

        try {
            DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
            int rc = dt.getRowCount();

            for (int i = 0; i < rc; i++) {
                String inid = dt.getValueAt(i, 0).toString();
                String P_name = dt.getValueAt(i, 1).toString();
                String bar_code = dt.getValueAt(i, 2).toString();
                String qty = dt.getValueAt(i, 3).toString();
                String un_price = dt.getValueAt(i, 4).toString();
                String tot_price = dt.getValueAt(i, 5).toString();

                //cart DB
                //Statement s = db.mycon().createStatement();
                s.executeUpdate("INSERT INTO cart (INID, product_name, Bar_code, qty, Unit_price, Total_Price) VALUES ('" + inid + "', '" + P_name + "','" + bar_code + "','" + qty + "','" + un_price + "', '" + tot_price + "') ");

            }

            JOptionPane.showMessageDialog(null, "Data Saved");

        } catch (HeadlessException | SQLException e) {
            System.out.println(e);
        }
        try {
            //sales DB
            // `saleid`, `INID`, `Cid`, `customer_name`, `Total_Qty`, `Total_Bill`, `Status`, `Balance`
            String inv_id = inid.getText();
            String cname = com_cus.getSelectedItem().toString();
            String totqty = tot_qty.getText();
            String tot_bil = bill_tot.getText();
            String blnc = bln_due.getText();
            String paid = paid_amt.getText();
            //paid check

            Double tot = Double.valueOf(bill_tot.getText());
            Double pid = Double.valueOf(paid_amt.getText());
            //String Status ="none";
//               
//               if(pid.equals(0.0) && Cus_balance<tot ){
//                   Status = "UnPaid";
//                   
//               }else if(tot>pid && Cus_balance<tot ){
//                  Status = "Partial"; 
//                  
//               }else if(tot<pid && Cus_balance>=tot){
//                   Status = "Paid MORE";
//               }else if(pid.equals(tot) && Cus_balance>=tot){
//                   Status = "Paid";
//               }

            //Statement ss = db.mycon().createStatement();
            s.executeUpdate("INSERT INTO sales (INID, Cid, customer_name, Total_Qty, Total_Bill, paid_amt) VALUES ('" + inv_id + "', '" + cus_id + "', '" + cname + "', '" + totqty + "', '" + tot_bil + "', '" + paid + "')");
            s.executeUpdate("UPDATE customer SET Tp_Number = '" + blnc + "' WHERE cid = '" + cus_id + "'");

        } catch (NumberFormatException | SQLException e) {
            System.out.println(e);
        }

        try {
            //Statement s = db.mycon().createStatement();

            String id = inid.getText();
            System.out.println("INVOICE id: " + id);
            s.executeUpdate("UPDATE extra SET val='" + id + "' WHERE exid = 1");
        } catch (SQLException e) {
            System.out.println(e);
        }

        //print or view bill
//        try{
//            
//        HashMap para = new HashMap();
//        para.put("inv_id", inid.getText()); // inv_id is ireport parameter
//        
//        ReportView r = new ReportView("src\\reports\\print.jasper",para);
//        r.setVisible(true);
//            
//        }catch(Exception e){
//            System.out.println(e);
//        }
        stckup();// Stock update;
        data_load();

        p_barcode.requestFocus();

        clearcart();


    }//GEN-LAST:event_jButton4ActionPerformed

    private void com_proActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_com_proActionPerformed
        // load unit price when product selected

        String name = com_pro.getSelectedItem().toString();

        try {
            //Statement s = db.mycon().createStatement();

            ResultSet rs = s.executeQuery("SELECT Bar_code, Sell_price, quantity, product_name FROM product WHERE product_name ='" + name + "'");

            if (rs.next()) {
                u_price.setText(rs.getString("Sell_price"));
                barcode_c = rs.getString("Bar_code");
                pro_qty = rs.getString("quantity");
                l_stqty.setText(pro_qty);
                p_barcode.setText(barcode_c);

                Select_product_name = rs.getString("product_name");

            }

            pro_tot_cal();
            //tot();

        } catch (SQLException e) {
            System.out.println(e);
        }
        if (!com_pro.isPopupVisible()) {
            p_qty.setText("");
            p_qty.requestFocus();
        }


    }//GEN-LAST:event_com_proActionPerformed

    private void p_qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_p_qtyKeyReleased
        // Perform total calculation
        pro_tot_cal();

        sale_qty = p_qty.getText(); // Get the input text

        if (evt.getKeyCode() == KeyEvent.VK_ENTER && !"".equals(p_barcode.getText()) && p_qty.getText() != "") {
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
                        p_barcode.requestFocus();

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

    private void p_qtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_qtyActionPerformed
    }//GEN-LAST:event_p_qtyActionPerformed

    private void p_barcodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p_barcodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p_barcodeActionPerformed

    private void p_barcodeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_p_barcodeKeyReleased
        // 
        String bcode = p_barcode.getText();

        try {
            //Statement s = db.mycon().createStatement();

            ResultSet rs = s.executeQuery("SELECT Bar_code, Sell_price, quantity,product_name FROM product WHERE Bar_code ='" + bcode + "'");

            if (rs.next()) {
                u_price.setText(rs.getString("Sell_price"));
                barcode_c = rs.getString("Bar_code");
                pro_qty = rs.getString("quantity");
                l_stqty.setText(pro_qty);
                Select_product_name = rs.getString("product_name");
                //com_pro.setSelectedItem(rs.getString("product_name"));
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (barcode_c == null || !barcode_c.equals(p_barcode.getText()) || barcode_c == "") {

                barcode_c = "";

            } else {
                add_to_cart();
                pro_tot_cal();
                tot();
                //barcode_c = null;

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


    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        try {
            jTable1.print();
        } catch (Exception e) {

        }

        changecal chn = new changecal(new javax.swing.JFrame(), true);
        chn.setLocationRelativeTo(null);
        chn.setVisible(true);
        recieved = chn.getrcvAmount();
        changeCal();
        cal_chn.setText(change.toString());

    }//GEN-LAST:event_jButton5ActionPerformed

    private void com_cusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_com_cusKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_com_cusKeyPressed

    private void com_cusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_com_cusActionPerformed
        // load customr info

        String name = com_cus.getSelectedItem().toString();

        try {
            //Statement s = db.mycon().createStatement();

            ResultSet rs = s.executeQuery("SELECT cid,customer_name FROM customer WHERE customer_name ='" + name + "'");

            if (rs.next()) {
                cus_id = rs.getString("cid");
            }

            pro_tot_cal();
            tot();
            Customer_balance_check();
            settleAmt();
        } catch (SQLException e) {
            System.out.println(e);
        }

        if (!com_cus.isPopupVisible()) {
            p_barcode.requestFocus();
        }

    }//GEN-LAST:event_com_cusActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Cus_Balance;
    private javax.swing.JLabel bill_tot;
    private javax.swing.JLabel bln_due;
    private javax.swing.JLabel cal_chn;
    private javax.swing.JComboBox<String> com_cus;
    private javax.swing.JComboBox<String> com_pro;
    private javax.swing.JLabel cus_settleamt;
    private javax.swing.JTextField disc_per;
    private javax.swing.JLabel inid;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
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
