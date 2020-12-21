/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fintechuas;

import java.awt.Dimension;
import java.awt.Panel;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author andriancimnen
 */
public final class MenuUtama extends javax.swing.JFrame {

    private final TransaksiInterface mi;

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
        mi = new TransaksiImp();
        tanggal();
        tfsaldo.setText("0");
        tfsaldo.setEditable(false);
        tftanggal.setEditable(false);
        tfsaldo1.setEditable(false);
        tfuser.setEditable(false);
        tfID.requestFocus();
        tfuser.setText(s.user);
        jTable1.setEnabled(false);
        read();
    }

    private void read() {
        mi.read(jTable1);
    }

    public void tanggal() {
        java.util.Date dt = new java.util.Date();
        SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd '--' hh:mm ");
        tftanggal.setText(simple.format(dt));
    }

    String ID[] = {
        "1901050024", "1901050002"
    };
    String nama;
    int jumlah;
    String jenis = "Isi Saldo";
    boolean TAG = true;
    String a = "";

    public void varivikasi() {
        tfsaldo1.setText("Pengisian Saldo");

        if (tfID.getText().equalsIgnoreCase(ID[0])) {
            nama = "Andrian";
            create();
        } else if (tfID.getText().equalsIgnoreCase(ID[1])) {
            nama = "Alfi Atqia";
            create();
        } else {
            JOptionPane.showMessageDialog(rootPane, "ID Tidak Valid");

        }

    }

    public void validasi() {
        if (zerosaldo.getText().isEmpty() && cbBank.getSelectedIndex()==0) {
            JOptionPane.showMessageDialog(null, "Inputan Belum Diisi");
        } else {
            varivikasi();
        }
    }

    public void create() {
        
        String Bang = null;
        switch (cbBank.getSelectedIndex()) {
            case 1:
                Bang = "BNI";
                break;
            case 2:
                Bang = "BRI";
                break;
            case 3:
                Bang = "BCA";
                break;
            default:
                break;
        }
        String cov = zerosaldo.getText();
        jumlah = Integer.valueOf(cov);
        int temp = jumlah;
        String q = tfsaldo.getText();
        int b = Integer.valueOf(q);
        int bin = 0;
        bin = b + jumlah;
        String backup = String.valueOf(bin);
        JTextField v = new JTextField();
        v.setText(backup);
        JTextField k = new JTextField();
        k.setText(String.valueOf(jumlah));
        historiTransaksi m = new historiTransaksi();
        String msg = "Anda Akan Mengirimkan Saldo Pada Username  : " + nama + "\n"
                + "Pada Bank    :   " +Bang + "\n"
                + "Dengan Nominal   :   " + jumlah;
        int pilihan = JOptionPane.NO_OPTION;
        pilihan = JOptionPane.showConfirmDialog(rootPane, msg, "Informasi", JOptionPane.YES_NO_OPTION);
        if (pilihan == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(rootPane, "Pengisian Saldo Sukses");
            m.setJenisTransaksi(tfsaldo1.getText());
            m.setNominal(k.getText());
            m.setSaldo(v.getText());
            m.setWaktu(tftanggal.getText());
            tfsaldo.setText(backup);
            mi.create(m);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Transaksi DiBatalkan");
        }
        read();
    }

    public void clear() {
        tfID.setText("");
        zerosaldo.setText("");
        cbBank.setSelectedIndex(0);
    }

    public void hitung2() {
        historiTransaksi m = new historiTransaksi();
        tfsaldo1.setText("Kirim Uang");
        String b = Nominal.getText();

        String c = tfsaldo.getText();
        int saldoAwal = Integer.valueOf(c);
        int nominal = Integer.valueOf(b);
        //JTextField Nama = new JTextField(10);
        JTextField Saldo = new JTextField(10);
        int e = saldoAwal - nominal;
        //Nama.setText(String.valueOf(b));
        String msg = "Anda Akan Mengirimkan Uang Pada Username  : " + nama + "\n"
                + "Dengan Nominal   :   " + b;
        int pilihan = JOptionPane.NO_OPTION;
        pilihan = JOptionPane.showConfirmDialog(rootPane, msg, "Informasi", JOptionPane.YES_NO_OPTION);
        if (pilihan == JOptionPane.YES_OPTION) {
            if (saldoAwal < nominal) {
                JOptionPane.showMessageDialog(null, "Saldo Ando Tidak Cukup");
            } else {
                tfsaldo.setText(String.valueOf(e));
                JOptionPane.showMessageDialog(rootPane, "Pengiriman Sukses");
                m.setJenisTransaksi(tfsaldo1.getText());
                m.setNominal(Nominal.getText());
                m.setSaldo(tfsaldo.getText());
                m.setWaktu(tftanggal.getText());
                mi.create(m);
                read();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Transaksi DiBatalkan");
        }
    }
    JTextField Rekening = new JTextField(10);
    JTextField Nominal = new JTextField(10);

    public void kirimUang() {
        JPanel myPanel = new JPanel();
        myPanel.add(new JLabel("Rekening" + "\n"));
        myPanel.add(Rekening);
        myPanel.add(new JLabel("Nominal "));
        myPanel.add(Nominal);

        int result = JOptionPane.showConfirmDialog(null, myPanel, "Masukkan No. Rekening dan Nominal", JOptionPane.OK_CANCEL_OPTION);

        String a = Rekening.getText();
        if (result == JOptionPane.OK_OPTION) {
            if (a.equals(ID[0])) {
                nama = "Andrian";
                hitung2();
            } else if (a.equals(ID[1])) {
                nama = "Alfih Atqia";
                hitung2();
            } else {
                JOptionPane.showMessageDialog(null, "ID Tidak Valid");
            }

        } else {
            JOptionPane.showMessageDialog(rootPane, "Transaksi Di Batalkan");
        }
        Rekening.setText("");
        Nominal.setText("");

    }
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
    int f = 0;
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
        historiTransaksi m = new historiTransaksi();
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
                m.setJenisTransaksi(tfsaldo1.getText());
                m.setNominal(Nama.getText());
                m.setSaldo(Saldo.getText());
                m.setWaktu(tftanggal.getText());
                mi.create(m);
                read();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Transaksi DiBatalkan");
        }
    }

    public void pulsa() {
        historiTransaksi m = new historiTransaksi();
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
                JOptionPane.showMessageDialog(rootPane, "Pengisian Vouche Pulsa Sukses");
                m.setJenisTransaksi(tfsaldo1.getText());
                m.setNominal(Nama.getText());
                m.setSaldo(tfsaldo.getText());
                m.setWaktu(tftanggal.getText());
                mi.create(m);
                read();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Transaksi DiBatalkan");
        }
    }

    public void keluar() {
        int confirmasi = JOptionPane.NO_OPTION;
        confirmasi = JOptionPane.showConfirmDialog(rootPane,
                "Apakah Anda Ingin Keluar Dari Aplikasi ZeroCash ?  ", "Informasi",
                JOptionPane.YES_NO_OPTION);
        if (confirmasi == JOptionPane.YES_OPTION) {
            System.exit(0);
        } else {
            tfID.requestFocus();
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
        jInternalFrame2 = new javax.swing.JInternalFrame();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        tfID = new javax.swing.JTextField();
        btnverifikasi = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbBank = new javax.swing.JComboBox<>();
        zerosaldo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        tfsaldo1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        btnKirim = new javax.swing.JButton();

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
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                            .addComponent(tfuser, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(125, 125, 125)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
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

        jInternalFrame2.setVisible(true);

        jPanel2.setBackground(new java.awt.Color(255, 153, 153));

        jLabel7.setBackground(new java.awt.Color(255, 0, 102));
        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setText("Pengisian Saldo ZeroCash");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        tfID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfIDActionPerformed(evt);
            }
        });

        btnverifikasi.setText("Verifikasi");
        btnverifikasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnverifikasiActionPerformed(evt);
            }
        });

        jLabel4.setText("Masukkan ID");

        jLabel5.setText("Nominal");

        jLabel10.setText("Bank");

        cbBank.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih-", "BNI", "BRI", "BCA" }));

        javax.swing.GroupLayout jInternalFrame2Layout = new javax.swing.GroupLayout(jInternalFrame2.getContentPane());
        jInternalFrame2.getContentPane().setLayout(jInternalFrame2Layout);
        jInternalFrame2Layout.setHorizontalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jInternalFrame2Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnverifikasi, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jInternalFrame2Layout.createSequentialGroup()
                        .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(32, 32, 32)
                        .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfID)
                            .addComponent(cbBank, 0, 125, Short.MAX_VALUE)
                            .addComponent(zerosaldo))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jInternalFrame2Layout.setVerticalGroup(
            jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame2Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfID))
                .addGap(14, 14, 14)
                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(zerosaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jInternalFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(cbBank, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(btnverifikasi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfsaldo1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jInternalFrame2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(107, 107, 107)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnListrik, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPulsa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnKirim, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(btnKirim, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnListrik, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPulsa, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfsaldo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jInternalFrame2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

    private void tfIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfIDActionPerformed

    private void btnverifikasiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnverifikasiActionPerformed
        // TODO add your handling code here:
        tanggal();
        validasi();
        clear();
    }//GEN-LAST:event_btnverifikasiActionPerformed

    private void tfsaldo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfsaldo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfsaldo1ActionPerformed

    private void tfuserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfuserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfuserActionPerformed

    private void btnKirimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKirimActionPerformed
        // TODO add your handling code here:
        kirimUang();
    }//GEN-LAST:event_btnKirimActionPerformed

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
    private javax.swing.JButton btnkeluar;
    private javax.swing.JButton btnverifikasi;
    private javax.swing.JComboBox<String> cbBank;
    private javax.swing.JButton jButton6;
    private javax.swing.JInternalFrame jInternalFrame2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField tfID;
    private javax.swing.JTextField tfsaldo;
    private javax.swing.JTextField tfsaldo1;
    private javax.swing.JTextField tftanggal;
    public javax.swing.JTextField tfuser;
    private javax.swing.JTextField zerosaldo;
    // End of variables declaration//GEN-END:variables

}
