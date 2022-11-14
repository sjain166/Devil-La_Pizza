
import UtilityFolder.DBFuntions;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.joda.time.DateTime;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 *
 * @author siddh
 */
public class dbTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        String className = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(className);
            System.out.println("Drivers Loaded Successfully");
        } catch (Exception e) {
            System.out.println("Drivers Failed To Load Successfully");
            System.out.println(e);
        }

//        String url = "jdbc:mysql://127.0.0.1:3306/la_Pizza";
//        String username = "root";
//        String password = "CSE360!";
        System.out.println("Connecting database...");
        Connection con;

        try {
            con = DriverManager.getConnection(
                    "jdbc:mysql://us-east.connect.psdb.cloud/la_pizza?sslMode=VERIFY_IDENTITY",
                    "o5cuxlejvq6xsyfu8m7h",
                    "pscale_pw_59Nsugbf172mCSBm7hlFii0I5AmriHmw0OjvCJEF1A8");
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }

        UtilityFolder.DBFuntions db = new DBFuntions();
        Statement stmt = con.createStatement();
        String sql = "SELECT * from orders where orderID = " + 7;
        ResultSet rs = stmt.executeQuery(sql);
        int userId = 0;
        while (rs.next()) {
            userId = rs.getInt(2);
        }
        
        sql = "SELECT * from user where userID = " + userId;
        rs = stmt.executeQuery(sql);
        while (rs.next()) {
            String email = rs.getString(4);
            db.sendConfirmation(email);
            System.out.println("done");
        }
        

    }

}
