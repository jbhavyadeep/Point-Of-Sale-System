/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package point.of.sale.system;

import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import static point.of.sale.system.sales.Select_product_name;
import static point.of.sale.system.sales.barcode_c;
import static point.of.sale.system.sales.pro_qty;

/**
 *
 * @author jbhav
 */
public class Grn extends javax.swing.JPanel {

    public static String SelectProductName = null;
    private final Statement s;

    public Grn(Statement s) {
        this.s = s;
        initComponents();
        data_load();
        cart_total();
        AutoCompleteDecorator.decorate(com_sup);
        AutoCompleteDecorator.decorate(com_pro);

    }

    
    public void clearFiled() {
        com_sup.setSelectedIndex(0);
        com_pro.setSelectedIndex(0);
        com_sup.setSelectedItem("Select a Product");
        com_pro.setSelectedItem("Select a supplier");
        I_Sid.setText("00");
        p_qty.setText("0");
        txt_bar.setText("");
        add_qty.setText("0");
        txt_cost.setText("");
        txt_sell.setText("");
        bill_tot.setText("00.00");
        bill_dts_rs.setText("00.00");
        bln_due.setText("00.00");
        bill_dts.setText("0");
        tot_qty.setText("00");
        pur_date.setDate(null);
        DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
        dt.setRowCount(0);
        cart_total();
    }

    
    
    public void data_load() {
        //load Supplier
        ResultSet rs1 = null;
        try {
             rs1 = s.executeQuery("SELECT * FROM supplier");
            Vector<String> v = new Vector<>();
            v.add("Select a supplier"); // Default prompt
            while (rs1.next()) {
                v.add(rs1.getString("supplier_name"));
            }
            DefaultComboBoxModel com = new DefaultComboBoxModel(v);
            com_sup.setModel(com);
            com_sup.setRenderer(new CustomComboBoxRenderer("Select a supplier"));
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (rs1 != null) {
                    rs1.close();
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

        //load Product
        try {
            // Statement s = db.mycon().createStatement();
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

    public void cart_total() {

        int numOfRow = jTable1.getRowCount();

        double total = 0;
        double tqty = 0;

        for (int i = 0; i < numOfRow; i++) {
            double value = Double.valueOf(jTable1.getValueAt(i, 7).toString());
            double value2 = Double.valueOf(jTable1.getValueAt(i, 3).toString());

            total += value;
            tqty += value2;
        }

        bill_tot.setText(Double.toString(total));
        tot_qty.setText(Double.toString(tqty));

    }

    public void discount() {
        try {
            Double dts_percentage = Double.valueOf(bill_dts.getText());

            Double dts_price = 0.0;

            Double Tot_bill = Double.valueOf(bill_tot.getText());

            dts_price = Tot_bill * dts_percentage / 100;

            bill_dts_rs.setText(String.valueOf(dts_price));

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void totcall() {
        Double sub_t = Double.valueOf(bill_tot.getText());
        Double dist = Double.valueOf(bill_dts_rs.getText());
        Double net = sub_t - dist;

        bln_due.setText(String.valueOf(net));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        inid = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        com_sup = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        com_pro = new javax.swing.JComboBox<>();
        add_qty = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txt_bar = new javax.swing.JTextField();
        txt_cost = new javax.swing.JTextField();
        txt_sell = new javax.swing.JTextField();
        I_Sid = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        bill_tot = new javax.swing.JLabel();
        bln_due = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        bill_dts_rs = new javax.swing.JLabel();
        bill_dts = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tot_qty = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        p_qty = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        pur_date = new com.toedter.calendar.JDateChooser();

        jPanel1.setBackground(new java.awt.Color(188, 195, 205));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        inid.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        inid.setText("01");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("GRN NO : ");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Supplier Name : ");

        com_sup.setEditable(true);
        com_sup.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        com_sup.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select" }));
        com_sup.setToolTipText("select");
        com_sup.setAutoscrolls(true);
        com_sup.setName("Select Supplier"); // NOI18N
        com_sup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                com_supActionPerformed(evt);
            }
        });
        com_sup.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                com_supKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setText("Product : ");

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

        add_qty.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        add_qty.setText("0");
        add_qty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_qtyActionPerformed(evt);
            }
        });
        add_qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                add_qtyKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Available Qty :");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Sell Price :");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("Cost Price :");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Purchase Date :");

        jButton1.setBackground(new java.awt.Color(0, 102, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(242, 242, 242));
        jButton1.setText("Add to Table");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Barcode : ");

        txt_bar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_barActionPerformed(evt);
            }
        });
        txt_bar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_barKeyReleased(evt);
            }
        });

        txt_cost.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txt_cost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_costActionPerformed(evt);
            }
        });

        txt_sell.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        I_Sid.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        I_Sid.setText("00");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("Supplier ID :");

        jTable1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "GRNID", "Name", "Barcode", "Added Qty", "Cost Price", "Sell Price", "Purchase Date", "Total Price", "Stock"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel6.setBackground(new java.awt.Color(167, 178, 194));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel7.setBackground(new java.awt.Color(188, 195, 205));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("SUB TOTAL :");

        bill_tot.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bill_tot.setText("00.00");
        bill_tot.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        bill_tot.setOpaque(true);

        bln_due.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bln_due.setText("00.00");
        bln_due.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        bln_due.setOpaque(true);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("NET TOTAL :");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("DISCOUNT");

        bill_dts_rs.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bill_dts_rs.setText("00.00");
        bill_dts_rs.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        bill_dts_rs.setOpaque(true);

        bill_dts.setText("0");
        bill_dts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bill_dtsActionPerformed(evt);
            }
        });
        bill_dts.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bill_dtsKeyReleased(evt);
            }
        });

        jLabel9.setText("% :");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel8)
                        .addComponent(jLabel12))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bill_dts, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bill_dts_rs, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                    .addComponent(bill_tot, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bln_due, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(bill_tot))
                .addGap(9, 9, 9)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(bill_dts_rs)
                    .addComponent(bill_dts, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(bln_due))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Total Qty :");

        tot_qty.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tot_qty.setText("00");

        jButton3.setBackground(new java.awt.Color(153, 0, 51));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(242, 242, 242));
        jButton3.setText("Remove All");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(153, 0, 51));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(242, 242, 242));
        jButton2.setText("Remove");
        jButton2.setPreferredSize(new java.awt.Dimension(107, 26));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(0, 102, 102));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(242, 242, 242));
        jButton4.setText("Save");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tot_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(44, 44, 44))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tot_qty))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        p_qty.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        p_qty.setText("0");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Add Qty :");

        pur_date.setDateFormatString("dd-MM-yyyy");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(com_sup, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(com_pro, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txt_bar, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inid, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(add_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(I_Sid, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(p_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(pur_date, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_sell)
                            .addComponent(txt_cost, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(6, 6, 6))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(inid)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(I_Sid)
                                .addComponent(jLabel15))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(com_sup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel13)
                                    .addComponent(txt_cost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(p_qty))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pur_date, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(com_pro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel14)
                                        .addComponent(jLabel7)
                                        .addComponent(txt_sell, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_bar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(add_qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        com_sup.getAccessibleContext().setAccessibleName("Select Supplier");

        jTabbedPane1.addTab("Add GRN", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // GRN data send to databse
        // Id,GRN_NO,Sid,Barcode,Itm_Name,Qty,Cost_Price,Sell_Price,Exp_Date,Sub_Total,Discount,Net_Total

        try {
            DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
            int rc = dt.getRowCount();

            for (int i = 0; i < rc; i++) {
                String inid = dt.getValueAt(i, 0).toString();
                String bar_code = dt.getValueAt(i, 1).toString();
                String P_name = dt.getValueAt(i, 2).toString();
                String qty = dt.getValueAt(i, 3).toString();//get product quantity
                String cost_price = dt.getValueAt(i, 4).toString();
                String sell_price = dt.getValueAt(i, 5).toString();
                String exp_date = dt.getValueAt(i, 6).toString();
                String tot_price = dt.getValueAt(i, 7).toString();//total price of one product
                String stock = dt.getValueAt(i, 8).toString();

                String Sub_tot = bill_tot.getText();
                String Disc = bill_dts_rs.getText();//discount
                String Net_tot = bln_due.getText();
                String Sup_id = I_Sid.getText();

                //insert in GRN DB
                // Statement s = db.mycon().createStatement();
                s.executeUpdate("INSERT INTO grn (GRN_NO,Sid,Barcode,Itm_Name,Qty,Cost_Price,Sell_Price,Exp_Date,Sub_Total,Discount,Net_Total) VALUES ('" + inid + "','" + Sup_id + "','" + bar_code + "' ,'" + P_name + "','" + qty + "','" + cost_price + "' ,'" + sell_price + "','" + exp_date + "', '" + Sub_tot + "','" + Disc + "','" + Net_tot + "') ");
                s.executeUpdate("UPDATE product SET quantity  = '" + stock + "', price = '" + cost_price + "', Sell_price = '" + sell_price + "'  where Bar_code = '" + bar_code + "'");

            }

            JOptionPane.showMessageDialog(null, "Data Saved");

        } catch (HeadlessException | SQLException e) {
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
        data_load();
        clearFiled();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //
        try {
            DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();

            int rw = jTable1.getSelectedRow();
            dt.removeRow(rw);

        } catch (Exception e) {
            System.out.println(e);
        }
        cart_total();
        totcall();
        discount();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //
        DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
        dt.setRowCount(0);
        cart_total();
        totcall();
        discount();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void bill_dtsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bill_dtsKeyReleased
        // TODO add your handling code here:
        discount();
        totcall();
    }//GEN-LAST:event_bill_dtsKeyReleased

    private void bill_dtsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bill_dtsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bill_dtsActionPerformed

    private void txt_costActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_costActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_costActionPerformed

    private void txt_barKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_barKeyReleased
        // TODO add your handling code here:
        String bcode = txt_bar.getText();
        try {
            //Statement s = db.mycon().createStatement();

            ResultSet rs = s.executeQuery("SELECT product_name, price, Sell_price, quantity, supplier_name FROM product WHERE Bar_code ='" + bcode + "'");

            if (rs.next()) {
                txt_cost.setText(rs.getString("price"));
                txt_sell.setText(rs.getString("Sell_price"));
                SelectProductName = rs.getString("product_name");
                p_qty.setText(rs.getString("quantity"));
                com_sup.setSelectedItem(rs.getString("supplier_name"));
                com_pro.setSelectedItem(SelectProductName);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_txt_barKeyReleased

    private void txt_barActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_barActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_barActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Total price calcluate
        String name = com_sup.getSelectedItem().toString();
        if (name.equals("Select a supplier")) {
            JOptionPane.showMessageDialog(this, "Please select a supplier from the list.", "Selection Required", JOptionPane.WARNING_MESSAGE);
            com_sup.requestFocus();
        }
        
        // Proceed to load supplier information
        Double qty = Double.valueOf(add_qty.getText());
        Double price = Double.valueOf(txt_cost.getText());
        Double sum = qty * price;
        Double stk = qty + Double.valueOf(p_qty.getText());
        Date mfLocalDate = pur_date.getDate();

       
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Timestamp sqlFromDate = new java.sql.Timestamp(mfLocalDate.getTime());
        DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
        Vector v = new Vector();

        v.add(inid.getText());//invoice id
        v.add(txt_bar.getText());//invoice id
        v.add(SelectProductName);//product name
        v.add(add_qty.getText());// product qty
        v.add(txt_cost.getText());// unit price
        v.add(txt_sell.getText());// total price
        v.add(sqlFromDate);// total price
        v.add(String.format("%.2f", sum));//total price
        v.add(stk);

        dt.addRow(v);
        cart_total();
        discount();
        totcall();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void add_qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_add_qtyKeyReleased

    }//GEN-LAST:event_add_qtyKeyReleased

    private void add_qtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_qtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_add_qtyActionPerformed

    private void com_proKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_com_proKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_com_proKeyReleased

    private void com_proActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_com_proActionPerformed
        // product unit pice

        String name = com_pro.getSelectedItem().toString();

        try {
            //Statement s = db.mycon().createStatement();

            ResultSet rs = s.executeQuery("SELECT Bar_code, price, Sell_price, quantity, product_name,Sid  FROM product WHERE product_name ='" + name + "'");

            if (rs.next()) {
                txt_cost.setText(rs.getString("price"));
                txt_sell.setText(rs.getString("Sell_price"));
                txt_bar.setText(rs.getString("Bar_code"));
                p_qty.setText(rs.getString("quantity"));
                SelectProductName = rs.getString("product_name");
                
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_com_proActionPerformed

    private void com_supActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_com_supActionPerformed
        // load supplier name

        String name = com_sup.getSelectedItem().toString();
        ResultSet rs = null;

        try {
            rs = s.executeQuery("SELECT * FROM supplier WHERE supplier_name ='" + name + "'");
            if (rs.next()) {
                I_Sid.setText(rs.getString("sid"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        if (!com_sup.isPopupVisible()) {
            txt_bar.requestFocus();
        }

    }//GEN-LAST:event_com_supActionPerformed

    private void com_supKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_com_supKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_com_supKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel I_Sid;
    private javax.swing.JTextField add_qty;
    private javax.swing.JTextField bill_dts;
    private javax.swing.JLabel bill_dts_rs;
    private javax.swing.JLabel bill_tot;
    private javax.swing.JLabel bln_due;
    private javax.swing.JComboBox<String> com_pro;
    private javax.swing.JComboBox<String> com_sup;
    private javax.swing.JLabel inid;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
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
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel p_qty;
    private com.toedter.calendar.JDateChooser pur_date;
    private javax.swing.JLabel tot_qty;
    private javax.swing.JTextField txt_bar;
    private javax.swing.JTextField txt_cost;
    private javax.swing.JTextField txt_sell;
    // End of variables declaration//GEN-END:variables
}
