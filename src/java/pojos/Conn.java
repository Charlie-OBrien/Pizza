/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author charl
 * 
 * connecting to DB
 */

public class Conn {
    public static Connection GetConnection() throws ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        
        Connection conn = null;
        
        String dbUrl = "jdbc:mysql://localhost:3306/pizzadb";
        String username = "root";
        String password = "";
      
        try {
            
            conn = DriverManager.getConnection(dbUrl, username, password);
            
        } catch (SQLException ex) {
            
            System.out.println(ex);
            
        }
        return conn;
    }
}
