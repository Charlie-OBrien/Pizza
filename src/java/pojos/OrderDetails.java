/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

/**
 *
 * @author charl
 */
public class OrderDetails {
    //
    private Customer customer;
    private Order order;
    private Pizza pizza;
    
    
    

    public OrderDetails() {
        //keep as is
    }

    public OrderDetails(Customer customer, Order order, Pizza pizza) {
        this.customer = customer;
        this.order = order;
        this.pizza = pizza;
    }
    

    //getters and setters
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
    
    
}
