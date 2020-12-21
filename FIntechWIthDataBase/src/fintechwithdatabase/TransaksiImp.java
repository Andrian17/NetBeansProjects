/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fintechuas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author andriancimnen
 */
public class TransaksiImp implements TransaksiInterface {

    Connection con1;
    PreparedStatement insert;

    private final String transaksi[] = {
        "Jenis Transaksi", "Nominal", "Saldo", "Waktu"
    };

    private final List<historiTransaksi> list = new ArrayList<>();
    String Jenis, nominal, saldo, waktu;

    @Override
    public void read(JTable jt) {
        DefaultTableModel tbl = new DefaultTableModel(null, transaksi);
        for (int i = 0; i < list.size(); i++) {
            Object[] os = new Object[4];
            os[0] = list.get(i).getJenisTransaksi();
            os[1] = list.get(i).getNominal();
            os[2] = list.get(i).getSaldo();
            os[3] = list.get(i).getWaktu();

            tbl.addRow(os);
        }
        jt.setModel(tbl);

    }

    @Override
    public void create(historiTransaksi m) {
        list.add(m);

    }

}
