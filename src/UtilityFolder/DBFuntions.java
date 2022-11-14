package UtilityFolder;

import com.mysql.cj.xdevapi.Session;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import sun.rmi.transport.Transport;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author siddh
 */
public class DBFuntions {

    private Connection con;

    public DBFuntions() throws ClassNotFoundException {
        try {
            String className = "com.mysql.cj.jdbc.Driver";
            Class.forName(className);
            System.out.println("Drivers Loaded Successfully");
            //String url = "jdbc:mysql://127.0.0.1:3306/la_Pizza";
            //String username = "root";
            //String password = "CSE360!";
            System.out.println("Connecting database...");
            con = DriverManager.getConnection(
                    "jdbc:mysql://us-east.connect.psdb.cloud/la_pizza?sslMode=VERIFY_IDENTITY",
                    "o5cuxlejvq6xsyfu8m7h",
                    "pscale_pw_59Nsugbf172mCSBm7hlFii0I5AmriHmw0OjvCJEF1A8");
            System.out.println("Database connected!");

        } catch (SQLException e) {
            System.out.println("Drivers Failed To Load Successfully");
            System.out.println(e);
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    public boolean checkUser(int userID, String password) {

        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM user where userID =" + userID + " and Password = '" + password + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.next()) {
                return false;
            } else {

                return true;
            }
            //return rs;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean checkAdmin(int userID, String password) {

        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM user where userID =" + userID + " and Password = '" + password + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.next()) {
                return false;
            } else {
                if (rs.getBoolean(8)) {
                    return true;
                }
                return false;
            }
            //return rs;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public userDetails createUser(int userID) {
        userDetails user = new userDetails();
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM user where userID =" + userID;
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                user.setUserID(rs.getInt(1));
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.seteMail(rs.getString(4));
                user.setPhoneNumber(rs.getLong(5));
                user.setAddress(rs.getString(6));
                user.setDOB(rs.getString(7));
                user.setAdmin(rs.getBoolean(8));
                user.setBalance(rs.getDouble(9));
            }

            return user;

        } catch (SQLException e) {
            System.out.println(e);
        }

        return user;
    }

    public void transaction(double amount, int userID) {
        try {
            Statement stmt = con.createStatement();
            String sql = "UPDATE user SET balance = " + amount + " WHERE userID =" + userID;
            stmt.executeUpdate(sql);
            return;

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void pushOrder(OrderClass order, int userID, String inputTime) {
        String orderString = order.printOrder();
        orderString += inputTime + ";";
        try {
            Statement stmt = con.createStatement();
            String sql = "INSERT into orders(userID , orderDetail, orderStatus) values (" + userID + ", '" + orderString + "' , '" + "PLC" + "');"; // PLC = Placed
            stmt.executeUpdate(sql);
            return;
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<OrderClass> getCurrentOrder(int userID) {
        List<OrderClass> orders = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM orders where userID =" + userID + " and orderStatus != 'PKD'";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                OrderClass order = new OrderClass();
                order.setOrderID(rs.getInt(1));
                getOrderObject(order, rs.getString(3));
                order.setOrderStat(rs.getString(4));
                orders.add(order);
            }

            return orders;

        } catch (SQLException e) {
            System.out.println(e);
        }

        return orders;
    }

    public List<OrderClass> getUserPastOrder(int userID) {
        List<OrderClass> orders = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM orders where userID =" + userID + " and orderStatus = 'PKD'";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                OrderClass order = new OrderClass();
                order.setOrderID(rs.getInt(1));
                getOrderObject(order, rs.getString(3));
                order.setOrderStat(rs.getString(4));
                orders.add(order);
            }

            return orders;

        } catch (SQLException e) {
            System.out.println(e);
        }

        return orders;
    }

    public List<OrderClass> getPlacedOrder(String orderType) {
        List<OrderClass> orders = new ArrayList<>();

        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM orders where orderStatus = '" + orderType + "';";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                OrderClass order = new OrderClass();
                order.setOrderID(rs.getInt(1));
                int user = rs.getInt(2);
                order.setUserName(findUserName(user));
                getOrderObject(order, rs.getString(3));
                order.setOrderStat(rs.getString(4));
                orders.add(order);
            }

            return orders;

        } catch (SQLException e) {
            System.out.println(e);
        }

        return orders;
    }
    
    public String findUserName(int user) throws SQLException {
        Statement stmt = con.createStatement();
        String uName = "";
        String find = "SELECT * from user where userID=" + user + ";";
        ResultSet r = stmt.executeQuery(find);
        while(r.next()) {
            uName = r.getString(2);
        }
        
        return uName;
    }

    public void setToCook(int orderID) {
        try {
            Statement stmt = con.createStatement();
            String sql = "UPDATE orders SET orderStatus = 'COOK' where orderID =" + orderID + ";";
            stmt.executeUpdate(sql);
            return;
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void setToPickUp(int orderID) {
        try {
            Statement stmt = con.createStatement();
            String sql = "UPDATE orders SET orderStatus = 'RDP' where orderID =" + orderID + ";";
            stmt.executeUpdate(sql);
            stmt = con.createStatement();
            sql = "SELECT * from orders where orderID = " + 7;
            ResultSet rs = stmt.executeQuery(sql);
            int userId = 0;
            while (rs.next()) {
                userId = rs.getInt(2);
            }

            sql = "SELECT * from user where userID = " + userId;
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String email = rs.getString(4);
                sendConfirmation(email);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void setToPickedUp() {
        try {
            Statement stmt = con.createStatement();
            String sql = "UPDATE orders SET orderStatus = 'PKD' where orderStatus = 'RDP'";
            stmt.executeUpdate(sql);
            return;
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void setToPickedUp(int userID) {
        try {
            Statement stmt = con.createStatement();
            String sql = "UPDATE orders SET orderStatus = 'PKD' where orderID =" + userID + ";";
            stmt.executeUpdate(sql);
            return;
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void getOrderObject(OrderClass order, String currOrder) {

        String[] items = currOrder.split(";");

        order.setPizzaQty(Integer.parseInt(items[0]));

        order.setPizzaSize(items[1]);

        order.setPizzaName(items[2]);

        String[] v = items[3].split(",");
        List<String> veggies = new ArrayList<>();
        if (v.length == 0) {
            order.setVeggies(veggies);
        } else {
            veggies = Arrays.asList(v);
            order.setVeggies(veggies);
        }

        order.setCrust(items[4]);

        order.setCookStyle(items[5]);

        String[] m = items[6].split(",");
        List<String> meat = new ArrayList<>();
        if (v.length == 0) {
            order.setMeat(meat);
        } else {
            meat = Arrays.asList(m);
            order.setMeat(meat);
        }

        order.setCheeseType(items[7]);

        order.setCheeseQty(items[8]);

        order.setSide1(items[9]);

        order.setSide2(items[10]);

        order.setTime(items[11]);

    }

    public void sendConfirmation(String email) {

        final String username = "devillapizza@outlook.com";
        final String password = "Qwerty@12345";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "outlook.office365.com");
        props.put("mail.smtp.port", "587");

        javax.mail.Session session = javax.mail.Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("devillapizza@outlook.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email));
            message.setSubject("Your Order is Ready !!!!");
            message.setText("Hi , Please pick up your food from our store.");

            javax.mail.Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean sendOTP(int OTP, String email) {
        if (!checkUserWithEmail(email)) {
            return false;
        }

        final String username = "devillapizza@outlook.com";
        final String password = "Qwerty@12345";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "outlook.office365.com");
        props.put("mail.smtp.port", "587");

        javax.mail.Session session = javax.mail.Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("devillapizza@outlook.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(email.trim()));
            message.setSubject("Here is the OTP to reset your Password");
            message.setText(OTP + "");

            javax.mail.Transport.send(message);

            System.out.println("Done");
            return true;

        } catch (MessagingException e) {

            throw new RuntimeException(e);

        }
    }

    public boolean checkUserWithEmail(String email) {
        try {
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM user where emailID = '" + email + "'";
            ResultSet rs = stmt.executeQuery(sql);
            if (!rs.next()) {
                return false;
            } else {

                return true;
            }
            //return rs;

        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean changePassword(String newPassword, String email) {
        try {
            Statement stmt = con.createStatement();
            String sql = "UPDATE user SET password = '" + newPassword + "' where emailID ='" + email + "';";
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }

        return false;
    }

}
