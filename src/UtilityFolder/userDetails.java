/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UtilityFolder;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author siddh
 */
public class userDetails {
    
   private int userID;
   private String firstName;
   private String lastName;
   private String eMail;
   private long phoneNumber;
   private String address;
   private String DOB;
   private boolean admin;
   private double balance;
   
   List<OrderClass> orderList = new ArrayList<>();
   
   
   //
   
   public userDetails(){
        this.userID = 00000;
        this.firstName = "";
        this.lastName = "";
        this.eMail = "";
        this.phoneNumber = 00000;
        this.address = "";
        this.DOB = "";    
   };
   
   public userDetails(int userID, String firstName, String lastName,String eMail,long phoneNumber,String address,String DOB){
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.DOB = DOB;    
   }

    // Getter Setter Function
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }
    
    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    //Getter Setter Function

    public List<OrderClass> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderClass> orderList) {
        this.orderList = orderList;
    }
    
    
    
    
    
    //Utility Functions
    public void addOrder(OrderClass order){
        orderList.add(order);
        System.out.println(orderList.size());
    }
    
    
    
    
    
    
   
   
   
   
   
   
    
}
