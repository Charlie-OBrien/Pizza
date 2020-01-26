/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer_Beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.Stateless;
import pojos.Conn;
import pojos.Customer;

/**
 *
 * @author charl
 */
@Stateless
public class Customer_DataLayer {

    public Customer_DataLayer() {
    }
    
    public static boolean InsertCustomer(Customer c) throws ClassNotFoundException{

        String sql = "insert into customer(firstName, lastName, phoneNumber, email, houseNumber, street, province, postalCode)"+
                " values(?, ?, ?, ?, ?, ?, ?, ?);";     //8 params 8 question marks
        PreparedStatement statement;
        
        //not needed at moment
        //ResultSet rs = null;
        
        try {
            
            statement = Conn.GetConnection().prepareStatement(sql);
            statement.setString(1, c.getFirstName());
            statement.setString(2, c.getLastName());
            
            statement.setString(3, c.getPhoneNumber());
            statement.setString(4, c.getEmail());
            
            statement.setInt(5, c.getHouse());
            statement.setString(6, c.getAddress());
            
            statement.setString(7, c.getProvince());
            statement.setString(8, c.getPostalCode());
            
            int x = statement.executeUpdate();
            
            if(x>0){
              return true;
            }
            
        } catch (SQLException ex) {
            System.out.println("Error inserting customer " + ex.getMessage());
        }
                
        return false;
    }
    
    //phone number is a unique identifier 
    public static int FetchCustomerByPhoneNumber(String phoneNumber){
        String sql = "select customerid from customer where phoneNumber = ?;";
        PreparedStatement statement;
        ResultSet rs = null;
        int CustomerId = 0;
        try{
            //should prevent SQL injection
            statement = Conn.GetConnection().prepareStatement(sql);
            statement.setString(1, phoneNumber);
            rs = statement.executeQuery();
            
            rs.first();
            
            CustomerId = rs.getInt(1);
            
        }catch(Exception es){
            System.out.println("Error fetch by phone " + es.getMessage());
        }
        return CustomerId;
    }
}
