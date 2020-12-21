package projekutspbo3;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BansosImp implements BansosInterface {

    private String[] kolom = {"ID", "NAMA", "TANGGAL LAHIR", "JENIS KELAMIN", "Pekerjaan", "Alamat", "Jenis Bansos"};

    private List<Bansos> list = new ArrayList<>();

    @Override
    public void read(JTable jt) {
        DefaultTableModel dtm = new DefaultTableModel(null, kolom);
        for (int i = 0; i < list.size(); i++) {
            Object[] os = new Object[7];
            os[0] = list.get(i).getID();
            os[1] = list.get(i).getNama();
            os[2] = list.get(i).getTanggal();
            os[3] = list.get(i).getJenis_Kelamin();
            os[4] = list.get(i).getPekerjaan();
            os[5] = list.get(i).getAlamat();
            os[6] = list.get(i).getJenisBansos();
            dtm.addRow(os);
        }
        jt.setModel(dtm);
    }

    @Override
    public void create(Bansos m) {
        list.add(m);
        JOptionPane.showMessageDialog(null, "Berhasil disimpan!");
    }

    @Override
    public void update(Bansos m) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getID() == m.getID()) {
                list.set(i, m);
            }
        }
        JOptionPane.showMessageDialog(null, "Berhasil diubah!");
    }

    @Override
    public void delete(int ID) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getID() == ID) {
                list.remove(i);
            }
        }
        JOptionPane.showMessageDialog(null, "Berhasil dihapus!");
    }

    @Override
    public void search(JTable jt, int ID) {
        DefaultTableModel dtm = new DefaultTableModel(null, kolom);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getID() == ID) {
                Object[] os = new Object[7];
                os[0] = list.get(i).getID();
                os[1] = list.get(i).getNama();
                os[2] = list.get(i).getTanggal();
                os[3] = list.get(i).getJenis_Kelamin();
                os[4] = list.get(i).getPekerjaan();
                os[5] = list.get(i).getAlamat();
                os[6] = list.get(i).getJenisBansos();
                dtm.addRow(os);
            }
        }
        jt.setModel(dtm);
    }
}
