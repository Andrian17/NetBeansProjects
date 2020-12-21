package projekutspbo3;

import javax.swing.JTable;


public interface BansosInterface {
    
   public void read(JTable jt);
   
   public void create(Bansos m);
   
   public void update(Bansos m);
   
   public void delete(int ID);
  
   public void search(JTable jt, int ID);
   
}
