/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fintechuas;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author andriancimnen
 */
public final class MenuUtama extends javax.swing.JFrame {

    Connection con1;
    PreparedStatement insert;
    //  kirimUang uang = new kirimUang();

    /**
     * Creates new form MenuUtama
     */
    public MenuUtama() {
        initComponents();
        login s = new login();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation(
                (screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2);
        tfsaldo.setEditable(false);
        tftanggal.setEditable(false);
        tanggal();
        tfuser.setEditable(false);
        tfuser.setText(s.user);
        tfsaldo1.setEditable(false);

        saldo();
    }

    String temp;

    public void tanggal() {
        java.util.Date dt = new java.util.Date();
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd '--' hh:mm ");
        tftanggal.setText(simple.format(dt));
        tftanggal.setEditable(false);
    }

    public void saldo() {
        int CC;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con1 = DriverManager.getConnection("jdbc:mysql://localhost/javacrud1", "root", "");
            insert = con1.prepareStatement("SELECT * FROM transaksi");
            ResultSet Rs = insert.executeQuery();

            ResultSetMetaData RSMD = Rs.getMetaData();
            CC = RSMD.getColumnCount();
            DefaultTableModel DFT = (DefaultTableModel) jTable1.getModel();
            DFT.setRowCount(0);

            while (Rs.next()) {
                Vector v2 = new Vector();

                for (int ii = 1; ii <= CC; ii++) {
                    v2.add(Rs.getString("id"));
                    v2.add(Rs.getString("jenis"));
                    v2.add(Rs.getString("nominal"));
                    v2.add(Rs.getString("saldo"));
                    v2.add(Rs.getString("waktu"));

                }
                temp = Rs.getString("saldo");
                tfsaldo.setText(temp);
//                uang.tftranf.setText(tfsaldo.getText());

                DFT.addRow(v2);
            }
        } catch (Exception e) {
        }

    }

    String nama;
    JTextField Rekening = new JTextField(10);
    JTextField Nominal = new JTextField(10);
    String ID[] = {
        "1901050024", "1901050002"
    };

    String jenis, nominal, saldo, tanggal;

    String token;

    public void listrik() {
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Nomor Token" + "\n"));
        myPanel.add(Rekening);

        int pilih = JOptionPane.showConfirmDialog(null, myPanel, "Masukkan No. Token", JOptionPane.OK_CANCEL_OPTION);
        if (pilih == JOptionPane.OK_OPTION) {
            token = Rekening.getText();
            int a = Integer.valueOf(token);

            if (token.equals(ID[0])) {
                nama = "Andrian";
                hitung();
            } else if (token.equals(ID[1])) {
                nama = "Alfih Atqia";
                hitung();
            } else {
                JOptionPane.showMessageDialog(null, "ID Tidak Valid");
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "Transaksi DiBatalkan");
        }
        Rekening.setText("");

    }
    int f;
    JTextField Nama = new JTextField(10);

    public void hitung() {

        tfsaldo1.setText("Pengisian Listrik");
        String pesan = "Pengisian Listrik Prepaid \n"
                + "Pilih    :   \n"
                + "1. Token Listrik Rp. 20.000 \n"
                + "2. Token Listrik Rp. 50.000 \n"
                + "3. Token Listrik Rp. 100.000 \n"
                + "4. Token Listrik Rp. 200.000 \n";
        String choose = JOptionPane.showInputDialog(rootPane, pesan);

        if (choose.equals("1")) {
            f = 20000;
            Nama.setText(String.valueOf(f));
            prosesToken();
        } else if (choose.equals("2")) {
            f = 50000;
            Nama.setText(String.valueOf(f));
            prosesToken();
        } else if (choose.equals("3")) {
            f = 100000;
            Nama.setText(String.valueOf(f));
            prosesToken();
        } else if (choose.equals("4")) {
            f = 200000;
            Nama.setText(String.valueOf(f));
            prosesToken();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Tranksai Tidak Valid");
            Rekening.setText("");
        }

    }

    private void prosesToken() {
        int temp = f;
        String c = tfsaldo.getText();
        int d = Integer.valueOf(c);
        //String b = String.valueOf(f);
        JTextField Saldo = new JTextField(10);
        int e = d - temp;
        String g = String.valueOf(e);
        String msg = "Anda Akan Mengisi Token Listrik Atas nama   : " + nama + "\n"
                + "Dengan Nominal   :   " + String.valueOf(f);
        int pilihan = JOptionPane.NO_OPTION;
        pilihan = JOptionPane.showConfirmDialog(rootPane, msg, "Informasi", JOptionPane.YES_NO_OPTION);
        if (pilihan == JOptionPane.YES_OPTION) {
            if (temp > d) {
                JOptionPane.showMessageDialog(null, "Saldo Ando Tidak Cukup");
            } else {
                Saldo.setText(g);
                tfsaldo.setText(g);
                JOptionPane.showMessageDialog(rootPane, "Pengiriman Sukses");
                nominal = String.valueOf(f);
                jenis = tfsaldo1.getText();
                saldo = tfsaldo.getText();
                tanggal = tftanggal.getText();
                inputan();
                saldo();

            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Transaksi DiBatalkan");
        }
    }

    public void inputan() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con1 = DriverManager.getConnection("jdbc:mysql://localhost/javacrud1", "root", "");
            insert = con1.prepareStatement("insert into transaksi (jenis, nominal, saldo, waktu)values(?,?,?,?)");
            insert.setString(1, jenis);
            insert.setString(2, nominal);
            insert.setString(3, saldo);
            insert.setString(4, tanggal);

            insert.executeUpdate();
            JOptionPane.showMessageDialog(this, "Record Saved");

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ISIsaldo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void pulsa() {

        tfsaldo1.setText("Pengisian Voucher Pulsa");
        String msg1 = "Pilih Jenis Operator \n"
                + "1. Telkomsel \n"
                + "2. XL | Axis \n"
                + "3. Three | Indosat";
        String no = "Pilih Nominal Voucher Pulsa \n "
                + "1. Rp. 10.000 \n"
                + "2. Rp. 20.000 \n"
                + "3. Rp. 50.000 \n"
                + "4. Rp. 100.000";
        String jenisOperator = JOptionPane.showInputDialog(null, msg1);
        String value = JOptionPane.showInputDialog(null, no);
        String list;
        if (jenisOperator.equals("1")) {
            list = "Telkomsel";
        } else if (jenisOperator.equals("2")) {
            list = "XL / Axis";
        } else {
            list = "Three / Indosat";
        }
        int nilai = 0;
        if (value.equals("1")) {
            nilai = 10000;
        } else if (value.equals("2")) {
            nilai = 20000;
        } else if (value.equals("3")) {
            nilai = 50000;
        } else {
            nilai = 100000;
        }
        String c = tfsaldo.getText();
        int saldoAwal = Integer.valueOf(c);
        JTextField Nama = new JTextField(10);
        JTextField Saldo = new JTextField(10);
        int admin = 2000 + nilai;
        int e = saldoAwal - admin;
        Nama.setText(String.valueOf(admin));
        String msg = "Anda Akan mengisi Pulsa Pada Provider : " + list + "\n"
                + "Dengan Nominal : " + String.valueOf(nilai) + "\n"
                + "Biaya Andmin :   Rp. 2000 \n"
                + "Total    :   " + admin;
        int pilihan = JOptionPane.NO_OPTION;
        pilihan = JOptionPane.showConfirmDialog(rootPane, msg, "Informasi", JOptionPane.YES_NO_OPTION);
        if (pilihan == JOptionPane.YES_OPTION) {
            if (saldoAwal < admin) {
                JOptionPane.showMessageDialog(null, "Saldo Ando Tidak Cukup");
            } else {
                tfsaldo.setText(String.valueOf(e));
                JOptionPane.showMessageDialog(rootPane, "Pengisian Voucher Pulsa Sukses");
                nominal = String.valueOf(admin);
                jenis = tfsaldo1.getText();
                saldo = tfsaldo.getText();
                tanggal = tftanggal.getText();
                inputan();
                saldo();

            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Transaksi DiBatalkan");
        }
    }

    public void delete() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int selectedIndex = jTable1.getSelectedRow();
        try {

            int id = Integer.parseInt(model.getValueAt(selectedIndex, 0).toString());

            int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to Delete the transaksi", "Warning", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                // Saving code here

                Class.forName("com.mysql.jdbc.Driver");
                con1 = DriverManager.getConnection("jdbc:mysql://localhost/javacrud1", "root", "");
                insert = con1.prepareStatement("delete from transaksi where id = ?");

                insert.setInt(1, id);
                insert.executeUpdate();
                JOptionPane.showMessageDialog(this, "transaksi Delete");

            }

        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

        }
    }

    public void keluar() {
        int confirmasi = JOptionPane.NO_OPTION;
        confirmasi = JOptionPane.showConfirmDialog(rootPane,
                "Apakah Anda Ingin Keluar Dari Aplikasi ZeroCash ?  ", "Informasi",
                JOptionPane.YES_NO_OPTION);
        if (confirmasi == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfsaldo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tftanggal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfuser = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        btnkeluar = new javax.swing.JButton();
        btnPulsa = new javax.swing.JButton();
        btnListrik = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        tfsaldo1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnKirim = new javax.swing.JButton();
        btnisisaldo = new javax.swing.JButton();
        btnhistori = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        jTextField1.setText("jTextField1");

        jButton6.setText("jButton1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 0, 102));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(255, 51, 51));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ZeroCash");

        tfsaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfsaldoActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Selamat Datang");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tgl");

        tfuser.setBackground(new java.awt.Color(255, 51, 51));
        tfuser.setForeground(new java.awt.Color(255, 255, 255));
        tfuser.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        tfuser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfuserActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Saldo");

        btnkeluar.setText("Exit");
        btnkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnkeluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfsaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfuser, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(125, 125, 125)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tftanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnkeluar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                        .addComponent(tfuser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfsaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tftanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnkeluar)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        btnPulsa.setText("Pulsa/Data");
        btnPulsa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPulsaActionPerformed(evt);
            }
        });

        btnListrik.setText("Listrk");
        btnListrik.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListrikActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "NO", "Jenis Transaksi", "Nominal", "Saldo", "Waktu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setText("History Transaksi");

        tfsaldo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfsaldo1ActionPerformed(evt);
            }
        });

        jLabel8.setBackground(new java.awt.Color(255, 0, 102));
        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("Jenis Transaksi");

        btnKirim.setText("Kirim Uang");
        btnKirim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKirimActionPerformed(evt);
            }
        });

        btnisisaldo.setText("ISI SALDO");
        btnisisaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnisisaldoActionPerformed(evt);
            }
        });

        btnhistori.setText("Histori");

        jButton1.setText("Delete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfsaldo1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnKirim, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(btnListrik, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(btnPulsa, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnisisaldo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnhistori, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfsaldo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnisisaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnKirim, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnListrik, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPulsa, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addComponent(jLabel6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnhistori, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfsaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfsaldoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfsaldoActionPerformed

    private void btnPulsaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPulsaActionPerformed
        // TODO add your handling code here:
        tanggal();
        pulsa();

    }//GEN-LAST:event_btnPulsaActionPerformed

    private void btnListrikActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListrikActionPerformed
        // TODO add your handling code here:
        tanggal();
        listrik();

    }//GEN-LAST:event_btnListrikActionPerformed

    private void btnkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnkeluarActionPerformed
        // TODO add your handling code here:
        keluar();
    }//GEN-LAST:event_btnkeluarActionPerformed

    private void tfsaldo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfsaldo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfsaldo1ActionPerformed

    private void tfuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfuserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfuserActionPerformed

    private void btnKirimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKirimActionPerformed
        // TODO add your handling code here:
        kirimUang uang = new kirimUang();
        uang.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnKirimActionPerformed

    private void btnisisaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnisisaldoActionPerformed
        // TODO add your handling code here:
        ISIsaldo isi = new ISIsaldo();
        isi.tfsaldoawal.setText(tfsaldo.getText());
        isi.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnisisaldoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int selectedIndex = jTable1.getSelectedRow();
        try {

            int id = Integer.parseInt(model.getValueAt(selectedIndex, 0).toString());

            int dialogResult = JOptionPane.showConfirmDialog(null, "Do you want to Delete the transaksi", "Warning", JOptionPane.YES_NO_OPTION);
            if (dialogResult == JOptionPane.YES_OPTION) {
                // Saving code here

                Class.forName("com.mysql.jdbc.Driver");
                con1 = DriverManager.getConnection("jdbc:mysql://localhost/javacrud1", "root", "");
                insert = con1.prepareStatement("delete from transaksi where id = ?");

                insert.setInt(1, id);
                insert.executeUpdate();
                JOptionPane.showMessageDialog(this, "transaksi Delete");
                saldo();

            }

        } catch (ClassNotFoundException ex) {

        } catch (SQLException ex) {

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int selectedIndex = jTable1.getSelectedRow();

        tfsaldo1.setText(model.getValueAt(selectedIndex, 1).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuUtama.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuUtama().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKirim;
    private javax.swing.JButton btnListrik;
    private javax.swing.JButton btnPulsa;
    private javax.swing.JButton btnhistori;
    private javax.swing.JButton btnisisaldo;
    private javax.swing.JButton btnkeluar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    public javax.swing.JTextField tfsaldo;
    private javax.swing.JTextField tfsaldo1;
    private javax.swing.JTextField tftanggal;
    public javax.swing.JTextField tfuser;
    // End of variables declaration//GEN-END:variables

}
