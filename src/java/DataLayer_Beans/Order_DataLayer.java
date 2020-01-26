/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer_Beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.ejb.Stateless;
import pojos.Conn;
import pojos.Customer;
import pojos.Order;
import pojos.OrderDetails;
import pojos.Pizza;


/**
 *
 * @author charl
 */
@Stateless
public class Order_DataLayer {

    public Order_DataLayer() {
        //keep as is
    }

    public static int InsertOrder(Order o) throws ClassNotFoundException{

        String sql = "insert into orders(totalPrice, deliveryDateTime, placedDateTime, customerId, orderStatus)"+
                " values(?, ?, ?, ?, ?);";      //5 params 5 question marks
        PreparedStatement statement;
        ResultSet rs = null;
        
        try {
            
            statement = Conn.GetConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setDouble(1, o.getTotalPrice());
            statement.setString(2, o.getPlacedDateTime());           
            statement.setString(3, o.getDeliveryDateTime());
            statement.setInt(4, o.getCustomerID());
            statement.setString(5, o.getOrderStatus());

            
            //execute statement
            int execute = statement.executeUpdate();
            
            if(execute>0){
                rs = statement.getGeneratedKeys();
                rs.next();
                
              return rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            System.out.println("Error inserting order " + ex.getMessage());
        }
                
        return 0;
    }
    
    
    public static OrderDetails FetchOrderDetails(int OrderID) throws ClassNotFoundException{
        Connection con = Conn.GetConnection();
        
        
        //select from tables customer, order, pizza
         String sql = "select c.firstName, c.lastName, " +
                        "o.orderId, o.totalPrice, o.deliveryDateTime, " +
                        "o.placedDateTime, o.customerid, " +
                         "p.pizzaId, p.price, " +
                        "(select name from sizes where sizeid = p.sizeId) as size, " +
                        "(select name from crusttypes where crustTypeId = p.crustTypeId) as crust " +
                        "from customer c " +
                        "inner join orders o " +
                        "on o.customerId = c.customerid " +
                        "inner join pizza p " +
                        "on o.orderId = p.orderId " +
                        "where o.orderId = ?;";
         
        PreparedStatement statement;
        ResultSet rs = null;
        
        //old but keep in case
        //ArrayList<OrderDetails> orders = new ArrayList<>();
        
        OrderDetails orderDetails = new OrderDetails();
        try{
            
            statement = con.prepareStatement(sql);
            statement.setInt(1, OrderID);
            rs = statement.executeQuery();
            rs.first();
            
            int first = 1;
            
            Customer c = new Customer();
            Order o = new Order();
            
            ArrayList<Pizza> pizzas = new ArrayList<>();
            
            do{
                if(first == 1){
                    
                    c.setFirstName(rs.getString(1));
                    c.setLastName(rs.getString(2));

                    o.setTotalPrice(rs.getDouble(4));
                    
                    orderDetails.setCustomer(c);
                    
                }
                   Pizza p = new Pizza();
                   p.setCrust(rs.getString(11));
                   p.setSize(rs.getString(10));
                   p.setPizzaPrice(rs.getDouble(9));
                   
                   String sql2 = "select t.name from toppings t " +
                    "inner join pizza_toppings_map pm " +
                    "on t.toppingId = pm.toppingId " +
                    "inner join pizza p " +
                    "on pm.pizzaId = p.pizzaId " +
                    "where p.pizzaId = ?;";
                   
                   PreparedStatement ps;
                   ResultSet res = null;
                   ps = con.prepareStatement(sql2);
                   ps.setInt(1, rs.getInt(8));
                   res = ps.executeQuery();
                   res.first();
                   String tops = "";
                   do{
                       tops += res.getString(1) + " | ";
                   }while(res.next());
                   
                   p.setToppings(tops);
                   
                   pizzas.add(p);
                   
                first++;
            }while(rs.next());
            
            o.setPizzas(pizzas);
            
            orderDetails.setOrder(o);
            
            
            
            return orderDetails;
            
        }catch(Exception ex){
            System.out.println("Error fetching order details " + ex.getMessage());
        }
        
        return orderDetails;
    }
    
    
    
    public static ArrayList<Integer> FetchOrders() throws ClassNotFoundException, SQLException{
        ArrayList<Integer> orderIDs = new ArrayList<>();
        
        String sql = "select orderId from orders where orderStatus = ?;";
        PreparedStatement ps;
        ResultSet rs = null;
        ps = Conn.GetConnection().prepareStatement(sql);
        ps.setString(1, "pending");
        rs = ps.executeQuery();
        rs.first();
        
        do{
            orderIDs.add(rs.getInt(1));
        }while(rs.next());
        
        return orderIDs;
    }
    public static void UpdateOrderStatus(int orderID) throws ClassNotFoundException{
        String sql = "update orders set orderStatus = 'filled' where orderId = ?; ";
        PreparedStatement statement;
        
        //keep in case
        //ResultSet rs = null;
        
        try {
            
            statement = Conn.GetConnection().prepareStatement(sql);
            statement.setInt(1, orderID);
            
            int executeStatement = statement.executeUpdate();
            
            if(executeStatement>0){
                System.out.println("Updated");
            }
            
        } catch (SQLException ex) {
            System.out.println("Error updating order " + ex.getMessage());
        }

    }//end method
}//end 
