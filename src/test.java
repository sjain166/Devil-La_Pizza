
import UtilityFolder.DBFuntions;
import UtilityFolder.OrderClass;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author siddh
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        
        DBFuntions db = new DBFuntions();
        List<OrderClass> orders = db.getCurrentOrder(123);
        for(OrderClass o : orders){
            
            System.out.println(o.printOrder());
            System.out.println(o.getTime());
        }
    }
    
}
