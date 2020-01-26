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
public class Order {
    private int orderID;
    private double totalPrice;
    private String deliveryDateTime;
    private String placedDateTime;
    private int customerID;
    private String orderStatus;
    private ArrayList<Pizza> pizzas;


    

    public Order() {
        //keep as is
    }

    public Order(double totalPrice, String deliveryDateTime, String placedDateTime, int customerID, String orderStatus) {
        
        this.totalPrice = totalPrice;
        this.deliveryDateTime = deliveryDateTime;
        this.placedDateTime = placedDateTime;
        this.customerID = customerID;
        this.orderStatus = orderStatus;
    }

    //getters and setters
    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDeliveryDateTime() {
        return deliveryDateTime;
    }

    public void setDeliveryDateTime(String deliveryDateTime) {
        this.deliveryDateTime = deliveryDateTime;
    }

    public String getPlacedDateTime() {
        return placedDateTime;
    }

    public void setPlacedDateTime(String placedDateTime) {
        this.placedDateTime = placedDateTime;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    
    //getter setter for ArrayList
        public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(ArrayList<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
    
    
}
