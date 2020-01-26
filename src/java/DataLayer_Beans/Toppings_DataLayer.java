/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataLayer_Beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.ejb.Stateless;
import pojos.Conn;
import pojos.Topping;

/**
 *
 * @author charl
 */
@Stateless
public class Toppings_DataLayer {
    
public static ArrayList<Topping> FetchAllToppings() throws ClassNotFoundException{
        
        ArrayList<Topping> toppingsName = new ArrayList<>();
        try {
            
            String sql = "select toppingId, name, price from toppings where isActive = 1";
            Statement s = Conn.GetConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs =  s.executeQuery(sql);

            rs.first();
            
            do{ 
                Topping t = new Topping(Integer.toString(rs.getInt(1)),rs.getString(2),Double.toString(rs.getDouble(3)));
                
                toppingsName.add(t);
                
            }while(rs.next());
            
            return toppingsName;
            
        } catch (SQLException ex) {
            System.out.println("Fault getting toppings");
        }      
        return toppingsName;        
    }
public static boolean InsertTopping(String name, double price, int EmpID) throws ClassNotFoundException{

        
        String sql = "insert into toppings(name, price, createdate, empAddedBy, isActive) values(?, ?, NOW(), ?, ?);";
        PreparedStatement statement;
        
        //not needed at moment
        ResultSet rs = null;
        
        try {
            
            statement = Conn.GetConnection().prepareStatement(sql);
            statement.setString(1, name);
            statement.setDouble(2, price);
            
            statement.setInt(3, EmpID);
            statement.setInt(4, 1);
            
            
            //ex for execute
            int ex = statement.executeUpdate();
            
            if(ex>0){
              return true;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                
        return false;
    }
public static Topping FetchToppingByName(String name) throws ClassNotFoundException{
       
        String sql = "SELECT toppingId, name FROM toppings WHERE name = ?";
        PreparedStatement s;
        ResultSet rs = null;
        Topping t = new Topping();
        
        try {
            
            s = Conn.GetConnection().prepareStatement(sql);
            s.setString(1, name);            
            rs = s.executeQuery();
            rs.first();
            t.setToppingID(Integer.toString(rs.getInt(1)));
            t.setToppingName(rs.getString(2));
            
            return t;
            
        } catch (SQLException ex) {
            System.out.println("Topping not found" + ex);
        }
                
        return t;
    }
public static boolean deleteTopping(String topping) throws ClassNotFoundException{
        String sql = "update toppings set isActive = 0 where toppingId = ?; ";
        PreparedStatement statement;
        
        //not needed at moment
        ResultSet rs = null;
        
        try {
            
            statement = Conn.GetConnection().prepareStatement(sql);
            statement.setString(1, topping);
            
            //ex for execute
            int ex = statement.executeUpdate();
            
            if(ex>0){
              return true;
            }
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
                
        return false;
    }
}