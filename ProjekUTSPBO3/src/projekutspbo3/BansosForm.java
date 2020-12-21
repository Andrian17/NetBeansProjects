/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projekutspbo3;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

/**
 *
 * @author andriancimnen
 */
public class BansosForm extends javax.swing.JFrame {

    private final BansosInterface mi;
    private boolean TAG = true;
    /**
     * Creates new form BansosForm
     */
    public BansosForm() {
        initComponents();
        mi = new BansosImp();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = getSize();
        setLocation(
                (screenSize.width - frameSize.width) / 2,
                (screenSize.height - frameSize.height) / 2);

    }

    private void clear() {
        TAG = true;
        tfNama.setText("");
        jdtanggal.setDate(null);
        tfAlamat.setText("");
        tfPekerjaan.setText("");
        cbBansos.setSelectedIndex(0);
        buttonGroup1.clearSelection();
        buttonGroup1.clearSelection();

        read();
    }

    private void read() {
        mi.read(tblBansos);
    }

    private boolean validasi() {
        if (tfNama.getText().isEmpty()
                || jdtanggal.getDate().toString().isEmpty()
                || (!rbPria.isSelected() && !rbWanita.isShowing())
                || cbBansos.getSelectedIndex() == 0) {
            return false;
        }
        return true;
    }

    private void save() {

        if (validasi()) {
            if (TAG) {
                create();

            } else {
                update();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Inputan belum diisi!");
        }
    }
    int ID2= 1;
    public void create() {

//        String se = "yyyy-MM-dd";
        
        Bansos m = new Bansos();
        m.setID(Integer.valueOf(ID2));
        m.setNama(tfNama.getText());
        m.setTanggal(jdtanggal.getDate().toString());

        if (rbPria.isSelected()) {
            m.setJenis_Kelamin("Pria");
        } else {
            m.setJenis_Kelamin("Wanita");
        }
        m.setPekerjaan(tfPekerjaan.getText());
        m.setAlamat(tfAlamat.getText());
        m.setJenisBansos(cbBansos.getSelectedItem().toString());
        clear();
        
    }
    
    private void update() {
        Bansos m = new Bansos();
        //m.setNisn(Integer.valueOf(tfNisn.getText()));
        m.setNama(tfNama.getText());
        m.setTanggal(jdtanggal.getDate().toString());

        if (rbPria.isSelected()) {
            m.setJenis_Kelamin("Pria");
        } else {
            m.setJenis_Kelamin("Wanita");
        }

        m.setJenisBansos(cbBansos.getSelectedItem().toString());
        mi.update(m);
        clear();
    }
    int row;

    void loadData() {
        int select = tblBansos.getSelectedRowCount();
        if (select > 0) {
            int pilihan = JOptionPane.NO_OPTION;
            pilihan = JOptionPane.showConfirmDialog(rootPane, "Yakin ingin ubah!", "Informasi", JOptionPane.YES_NO_OPTION);
            if (pilihan == JOptionPane.YES_OPTION) {

                row = tblBansos.getSelectedRow();
                //ID2.setText(tblBansos.getValueAt(row, 0).toString());
                tfNama.setText(tblBansos.getValueAt(row, 1).toString());

                jdtanggal.setToolTipText(tblBansos.getValueAt(row, 2).toString());

                if (tblBansos.getValueAt(row, 3).toString().equals("Pria")) {
                    rbPria.setSelected(true);
                } else {
                    rbWanita.setSelected(true);
                }
                cbBansos.setSelectedItem(tblBansos.getValueAt(row, 4).toString());
                tfPekerjaan.setText(tblBansos.getValueAt(row, 5).toString());
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Data belum dipilih!");
        }
    }

    private void delete() {
        int select = tblBansos.getSelectedRowCount();
        if (select > 0) {
            int pilihan = JOptionPane.NO_OPTION;
            pilihan = JOptionPane.showConfirmDialog(rootPane, "Yakin ingin hapus!", "Informasi", JOptionPane.YES_NO_OPTION);
            if (pilihan == JOptionPane.YES_OPTION) {
                int row = tblBansos.getSelectedRow();

                int nisn = Integer.valueOf(tblBansos.getValueAt(row, 0).toString());
                mi.delete(nisn);
                clear();
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Data belum dipilih!");
        }
    }

    private void search() {
        if (tfPencarian.getText().isEmpty()) {
            clear();
        } else {
            mi.search(tblBansos, Integer.valueOf(tfPencarian.getText()));
        }
    }

    public void bansos() {
        if (cbBansos.getSelectedIndex() == 1) {
            deskripsi.setText("Bantuan Pangan Non Tunai (BPNT) adalah \n"
                    + " bantuan sosial pangan dalam bentuk non tunai dari pemerintah \n"
                    + " yang diberikan kepada KPM setiap bulannya melalui mekanisme \n"
                    + " akun elektronik yang digunakan hanya untuk membeli bahan pangan \n"
                    + " di pedagang bahan pangan/e-warong yang bekerjasama dengan bank.");
        } else if (cbBansos.getSelectedIndex() == 2) {
            deskripsi.setText("Bantuan sosial PKH pada tahun 2019 terbagi menjadi dua jenis yaitu \n"
                    + "Bantuan Tetap dan Bantuan Komponen yang diberikan dengan ketentuan sebagai berikut:\n"
                    + "\n"
                    + "A. Bantuan Tetap untuk Setiap Keluarga\n"
                    + "Reguler          : Rp.     550.000,- / keluarga / tahun\n"
                    + "PKH AKSES  : Rp. 1.000.000,- / keluarga / tahun\n"
                    + "B. Bantuan Komponen untuk Setiap Jiwa dalam Keluarga PKH\n"
                    + "Ibu hamil                  : Rp. 2.400.000,-\n"
                    + "Anak usia dini          : Rp. 2.400.000,-\n"
                    + "SD                            : Rp. 900.000,-\n"
                    + "SMP                         : Rp. 1.500.000,-\n"
                    + "SMA                         : Rp. 2.000.000,-\n"
                    + "Disabilitas berat       : Rp. 2.400.000,-\n"
                    + "Lanjut usia               : Rp. 2.400.000,-");
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

        dateChooserDialog1 = new datechooser.beans.DateChooserDialog();
        dateChooserDialog2 = new datechooser.beans.DateChooserDialog();
        dateChooserDialog3 = new datechooser.beans.DateChooserDialog();
        buttonGroup1 = new javax.swing.ButtonGroup();
        menuBar2 = new java.awt.MenuBar();
        menu3 = new java.awt.Menu();
        menu4 = new java.awt.Menu();
        popupMenu1 = new java.awt.PopupMenu();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfNama = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        rbPria = new javax.swing.JRadioButton();
        rbWanita = new javax.swing.JRadioButton();
        tfPekerjaan = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        cbBansos = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        tfAlamat = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblBansos = new javax.swing.JTable();
        BtnSiimpan = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        tfPencarian = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        deskripsi = new javax.swing.JTextPane();
        jdtanggal = new com.toedter.calendar.JDateChooser();

        menu3.setLabel("File");
        menuBar2.add(menu3);

        menu4.setLabel("Edit");
        menuBar2.add(menu4);

        popupMenu1.setLabel("popupMenu1");

        jTextField1.setText("jTextField1");

        jTextField2.setText("jTextField2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(602, 563));

        jPanel1.setBackground(new java.awt.Color(255, 0, 204));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(240, 240, 240));
        jLabel1.setText("Pendaftaran Bantuan Sosial");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setText("Nama");

        jLabel4.setText("TTL");

        jLabel6.setText("Pekerjaan");

        jLabel7.setText("Jenis Kelamin");

        buttonGroup1.add(rbPria);
        rbPria.setText("Laki-Laki");
        rbPria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPriaActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbWanita);
        rbWanita.setText("Perempuan");
        rbWanita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbWanitaActionPerformed(evt);
            }
        });

        tfPekerjaan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPekerjaanActionPerformed(evt);
            }
        });

        jLabel8.setText("Jenis Bansos");

        cbBansos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "- Pilih -", "Bantuan Pangan Non Tunai (BPNT)", "Program Keluarga Harapan (PKH)", "Bantuan Sosial Tunai (BST) bagi yang terdampak pandemi COVID-19" }));

        jLabel5.setText("Deskripsi");

        jLabel9.setText("Alamat");

        tblBansos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        jScrollPane3.setViewportView(tblBansos);

        BtnSiimpan.setText("SIMPAN");
        BtnSiimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSiimpanActionPerformed(evt);
            }
        });

        jButton2.setText("UBAH");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("HAPUS");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel10.setText("Search");

        tfPencarian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfPencarianActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(deskripsi);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(9, 9, 9))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(tfPekerjaan, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfAlamat, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbBansos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tfNama)
                                    .addComponent(jdtanggal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(30, 30, 30)
                                .addComponent(rbPria)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbWanita))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(BtnSiimpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(178, 178, 178))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2)))))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jdtanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rbPria)
                            .addComponent(rbWanita)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(tfPekerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbBansos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtnSiimpan)
                            .addComponent(jButton3)
                            .addComponent(jButton2))
                        .addGap(0, 54, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfPencarian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbPriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbPriaActionPerformed

    private void rbWanitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbWanitaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbWanitaActionPerformed

    private void tfPekerjaanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPekerjaanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPekerjaanActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void BtnSiimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSiimpanActionPerformed
        // TODO add your handling code here:
        save();
        create();
    }//GEN-LAST:event_BtnSiimpanActionPerformed

    private void tfPencarianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfPencarianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfPencarianActionPerformed

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
            java.util.logging.Logger.getLogger(BansosForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BansosForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BansosForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BansosForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BansosForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnSiimpan;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbBansos;
    private datechooser.beans.DateChooserDialog dateChooserDialog1;
    private datechooser.beans.DateChooserDialog dateChooserDialog2;
    private datechooser.beans.DateChooserDialog dateChooserDialog3;
    private javax.swing.JTextPane deskripsi;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private com.toedter.calendar.JDateChooser jdtanggal;
    private java.awt.Menu menu3;
    private java.awt.Menu menu4;
    private java.awt.MenuBar menuBar2;
    private java.awt.PopupMenu popupMenu1;
    private javax.swing.JRadioButton rbPria;
    private javax.swing.JRadioButton rbWanita;
    public javax.swing.JTable tblBansos;
    private javax.swing.JTextField tfAlamat;
    private javax.swing.JTextField tfNama;
    private javax.swing.JTextField tfPekerjaan;
    private javax.swing.JTextField tfPencarian;
    // End of variables declaration//GEN-END:variables
}
