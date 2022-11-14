/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UtilityFolder;

import java.util.List;

/**
 *
 * @author siddh
 */
public class OrderClass {

    private String userName;
    private String pizzaSize;
    private int pizzaQty;
    private String pizzaName;
    private List<String> veggies;
    private String crust;
    private String cookStyle;
    private List<String> meat;
    private String cheeseType;
    private String CheeseQty;
    private String side1;
    private String side2;
    private int allowedVeggies;
    private int allowedmeat;
    private double basePrice;
    private double sidePrice;
    private double grandTotal;
    private int orderID;
    private String orderStat;
    private String time;

    
    
    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }
 
    public String getPizzaSize() {
        return pizzaSize;
    }

    public void setPizzaSize(String pizzaSize) {
        this.pizzaSize = pizzaSize;
    }

    public int getPizzaQty() {
        return pizzaQty;
    }

    public void setPizzaQty(int pizzaQty) {
        this.pizzaQty = pizzaQty;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public List<String> getVeggies() {
        return veggies;
    }

    public void setVeggies(List<String> veggies) {
        this.veggies = veggies;
    }

    public String getCrust() {
        return crust;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    public String getCookStyle() {
        return cookStyle;
    }

    public void setCookStyle(String cookStyle) {
        this.cookStyle = cookStyle;
    }

    public List<String> getMeat() {
        return meat;
    }

    public void setMeat(List<String> meat) {
        this.meat = meat;
    }

    public String getCheeseType() {
        return cheeseType;
    }

    public void setCheeseType(String cheeseType) {
        this.cheeseType = cheeseType;
    }

    public String getCheeseQty() {
        return CheeseQty;
    }

    public void setCheeseQty(String CheeseQty) {
        this.CheeseQty = CheeseQty;
    }

    public String getSide1() {
        return side1;
    }

    public void setSide1(String side1) {
        this.side1 = side1;
    }

    public String getSide2() {
        return side2;
    }

    public void setSide2(String side2) {
        this.side2 = side2;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAllowedVeggies() {
        return allowedVeggies;
    }

    public void setAllowedVeggies(int allowedVeggies) {
        this.allowedVeggies = allowedVeggies;
    }

    public int getAllowedmeat() {
        return allowedmeat;
    }

    public void setAllowedmeat(int allowedmeat) {
        this.allowedmeat = allowedmeat;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getSidePrice() {
        return sidePrice;
    }

    public void setSidePrice(double sidePrice) {
        this.sidePrice = sidePrice;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getOrderStat() {
        return orderStat;
    }

    public void setOrderStat(String orderStat) {
        this.orderStat = orderStat;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    
    public OrderClass(){
        userName = "";
        pizzaSize = "";
        pizzaQty = 0;
        pizzaName = "";
        veggies = null;
        crust = "";
        cookStyle = "";
        this.meat = null;
        cheeseType = "";
        CheeseQty = "";
        this.side1 = "";
        this.side2 = "";
        this.allowedVeggies = 0;
        this.allowedmeat = 0;
        this.basePrice = 0;
        this.sidePrice = 0;
    }
    public OrderClass(String username, String size, int quantity, String pName, List<String> veg, String c, String cs, List<String> meat, String cType, String cQty, String side1, String side2, int aVeggies, int aMeat , double basePrice , double sidePrice) {

        userName = username;
        pizzaSize = size;
        pizzaQty = quantity;
        pizzaName = pName;
        veggies = veg;
        crust = c;
        cookStyle = cs;
        this.meat = meat;
        cheeseType = cType;
        CheeseQty = cQty;
        this.side1 = side1;
        this.side2 = side2;
        this.allowedVeggies = aVeggies;
        this.allowedmeat = aMeat;
        this.basePrice = basePrice;
        this.sidePrice = sidePrice;

    }

    public String printOrder() {
        String order = pizzaQty + ";" + pizzaSize + ";" + pizzaName + ";";

        for (int i = 0; i < veggies.size(); i++) {
            if (i == veggies.size() - 1) {
                order += veggies.get(i);
            } else {
                order += veggies.get(i) + ",";
            }
        }

        order += ";" + crust + ";" + cookStyle + ";";

        for (int i = 0; i < meat.size(); i++) {
            if (i == meat.size() - 1) {
                order += meat.get(i);
            } else {
                order += meat.get(i) + ",";
            }
        }

        order += ";" + cheeseType + ";" + CheeseQty + ";" + side1 + ";" + side2 + ";";

        return order;

    }

}
