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
import pojos.Crust;
import pojos.Pizza;
import pojos.Size;

/**
 *
 * @author charl
 */
@Stateless
public class Pizza_DataLayer {

    public static ArrayList<Crust> FecthCrustTypes(){
        ArrayList<Crust> crustTypes = new ArrayList<>();
        try {
            
            String sql = "select crustTypeId, name from crusttypes";
            Statement s = Conn.GetConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs =  s.executeQuery(sql);

            rs.first();
            
            do{ 
                Crust c = new Crust(Integer.toString(rs.getInt(1)), rs.getString(2));

                crustTypes.add(c);
                
            }while(rs.next());
            
            return crustTypes;
        }catch(Exception ex){
            System.out.println("Error fetching crust types " + ex.getMessage());
        }    
        
        return crustTypes;
    }
    public static ArrayList<Size> FecthSizes(){
        ArrayList<Size> sizes = new ArrayList<>();
        try {
            
            String sql = "select sizeid, name from sizes";
            Statement s = Conn.GetConnection().createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs =  s.executeQuery(sql);

            rs.first();
            
            do{ 
                Size size = new Size(Integer.toString(rs.getInt(1)), rs.getString(2));
                
                sizes.add(size);
                
            }while(rs.next());
            
            return sizes;
        }catch(Exception ex){
            System.out.println("Error fetching sizes " + ex.getMessage());
        }    
        
        return sizes;
    }
    public static double CalculatePrice(Pizza p, double toppingPrice){
        
        double crustPrice = 4.0;
        double sizePrice;
        
        
        String size = p.getSize();
        
        
        if(size.equalsIgnoreCase("Small")){
            sizePrice = 9.99;
        }else if(size.equalsIgnoreCase("Medium")){
            sizePrice = 14.99;
        }else{
            sizePrice = 19.99;
        }
        
        double totalPizza = crustPrice+sizePrice+toppingPrice;
        
        return totalPizza;
    }
    public static int InsertPizzas(Pizza p, int orderID) throws ClassNotFoundException, SQLException{
        Connection con = Conn.GetConnection();
        
            //5 params 5 question marks
            String sql = "insert into pizza(sizeId, isFinished, crustTypeId, price, orderId) "
                    + "values(?, ?, ?, ?, ?);";
            
            PreparedStatement statement;
            statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            statement.setInt(1, p.getSizeID());
            statement.setInt(2, 1);
            statement.setInt(3, p.getSizeID());
            
            //pizza price
            String pp = Double.toString(p.getPizzaPrice());
            statement.setFloat(4, Float.parseFloat(pp));
            statement.setInt(5, orderID);
            
            int x = statement.executeUpdate();
            
            if(x>0){
                ResultSet rs = statement.getGeneratedKeys();
                rs.next();
                return rs.getInt(1);
            }
        return 1;
    }
    
    
    public static boolean InsertPizzaToppings(ArrayList<String> tID, int pizzaID) throws ClassNotFoundException, SQLException{
        for(String toppingID:tID){
            int topID = Integer.parseInt(toppingID);
            
            //2 params 2 question marks
            String sql = "insert into pizza_toppings_map(toppingId, pizzaId) values(?, ?)";
            PreparedStatement statement;
            statement = Conn.GetConnection().prepareStatement(sql);
            
            statement.setInt(1, topID);
            statement.setInt(2, pizzaID);
            
            statement.executeUpdate();
        }
        return true;
    }
}
