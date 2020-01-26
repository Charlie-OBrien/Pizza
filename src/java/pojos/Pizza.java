/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.util.ArrayList;

/**
 *
 * @author charl
 */
public class Pizza {
    private String crust;
    private String size;
    private String toppings;
    private double topPrice;
    private ArrayList<String> toTest;
    private ArrayList<String> toID;
    private int crustID;
    private int sizeID;
    private double pizzaPrice;
    
    public Pizza() {
        //keep as is
    }

    public Pizza(String crust, String size, String toppings, double price) {
        this.crust = crust;
        this.size = size;
        this.toppings = toppings;
        this.topPrice = price;
    }

    //getters and setters
    public double getPizzaPrice() {
        return pizzaPrice;
    }

    public void setPizzaPrice(double pizzaPrice) {
        this.pizzaPrice = pizzaPrice;
    }
    
    public int getCrustID() {
        return crustID;
    }

    public void setCrustID(int crustID) {
        this.crustID = crustID;
    }

    public int getSizeID() {
        return sizeID;
    }

    public void setSizeID(int sizeID) {
        this.sizeID = sizeID;
    }

    
    public String getCrust() {
        return crust;
    }

    public void setCrust(String crust) {
        this.crust = crust;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getToppings() {
        return toppings;
    }

    public void setToppings(String toppings) {
        this.toppings = toppings;
    }
    public ArrayList<String> getToID() {
        return toID;
    }

    public void setToID(ArrayList<String> toID) {
        this.toID = toID;
    } 

    public ArrayList<String> getToTest() {
        return toTest;
    }

    public void settTest(ArrayList<String> tTest) {
        this.toTest = tTest;
    }
    

    public double getTopPrice() {
        return topPrice;
    }

    public void setTopPrice(double topPrice) {
        this.topPrice = topPrice;
    }
    
}
