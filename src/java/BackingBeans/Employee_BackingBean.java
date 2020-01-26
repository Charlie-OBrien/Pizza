/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackingBeans;

import DataLayer_Beans.Employee_DataLayer;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author charl
 */
@Named(value = "eBB")
@ViewScoped
public class Employee_BackingBean implements Serializable{

    /**
     * Creates a new instance of Employee_BackingBean
 backing bean will work with Elogin.xhtml  
     */
    @EJB
    private Employee_DataLayer e;
    private String username;
    private String password;
    
    
    public Employee_BackingBean() {
        //default keep as is
    }

    
    public String Login(){
        int login = e.LoginEmployee(username, password);
        if(login>0){
            //keep as is DO NOT ADD /faces
            return "Emp_MGMT.jsp";
        }else{
            return "Emp_Login.xhtml";
        }
        
    }

    //getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Employee_DataLayer getE() {
        return e;
    }

    public void setE(Employee_DataLayer e) {
        this.e = e;
    }
}
