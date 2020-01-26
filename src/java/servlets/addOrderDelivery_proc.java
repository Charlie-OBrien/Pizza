/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DataLayer_Beans.Customer_DataLayer;
import DataLayer_Beans.Order_DataLayer;
import DataLayer_Beans.Pizza_DataLayer;
import java.io.IOException;
import java.io.PrintWriter;


import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pojos.Customer;
import pojos.Order;
import pojos.Pizza;

/**
 *
 * @author charl
 */
@WebServlet(name = "addOrderDelivery_proc", urlPatterns = {"/addOrderDelivery_proc"})
public class addOrderDelivery_proc extends HttpServlet {

    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addOrderDelivery_proc</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addOrderDelivery_proc at " + request.getContextPath() + "</h1>");
            
            // params 
            String firstName = request.getParameter("firstname");
            String lastName = request.getParameter("lastname");
         
            String address = request.getParameter("address");
            String h = request.getParameter("house");
            
            int house = Integer.parseInt(h);
            //do not change DB type keep as int
            
            String postalcode = request.getParameter("postalcode");
            String province = request.getParameter("province");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
     
            
            // 
            Customer c = new Customer(firstName, lastName, address, house, postalcode, province, phone, email);
            
            // get customer by phone number to see if it exists already in DB
            int cust = Customer_DataLayer.FetchCustomerByPhoneNumber(phone);
            
            // Check customer by phone number
            //was part of functionality that I could not get around to implementing
            
            if(cust > 0){
                //if customer phone number already exists it will be greater than ZERO
                response.sendRedirect("Checkout.jsp?msgPhone=Sorry!. Phone number already exists, try another one");
            }
            
            // Insert the customer into the DB
            boolean add = Customer_DataLayer.InsertCustomer(c);
            
            // customer inserted
            if(add){
                
                HttpSession session = request.getSession();
                // Prepare Order Object
                double OrderTotal = (double)session.getAttribute("OrderTotal");
                LocalDateTime placedDate = LocalDateTime.now();
                String date = placedDate.toString();
                
                //all pizzas will be ready in 30 minutes from time of ordering
                LocalDateTime deliveryTime = placedDate.plusMinutes(30);
                String delivery = deliveryTime.toString();
                int custID = Customer_DataLayer.FetchCustomerByPhoneNumber(phone);
                String orderStatus = "pending";
                
                // Create Order Object
                Order o = new Order(OrderTotal, date, delivery, custID, orderStatus);
                
                // Get Order ID back from DB
                int orderAdded = Order_DataLayer.InsertOrder(o);
                
                // if order was inserted
                if(orderAdded > 0){
                    // Get list of all the pizzas in the session
                    ArrayList<Pizza> pizzas = (ArrayList<Pizza>)session.getAttribute("pizzas");
                    int numPizzas = pizzas.size();
                    
                    System.out.println(numPizzas + "");
                    
                   
                    //for Pizza pizza in pizzas
                    for(Pizza pizza:pizzas){
                       // 
                       double price = Pizza_DataLayer.CalculatePrice(pizza, pizza.getTopPrice());
                       double priceRound = Math.round(price * 100.0)/100.0; //rounds up or down as needed 
                       pizza.setPizzaPrice(priceRound);
                       
                       // Insert Pizza
                       int pizzaID = Pizza_DataLayer.InsertPizzas(pizza, orderAdded);
                       
                    
                       if(pizzaID>0){
                           ArrayList<String> toppings = pizza.getToID();
                           boolean toppingsAdded = Pizza_DataLayer.InsertPizzaToppings(toppings, pizzaID);
                           if(toppingsAdded){
                               
                            //add pizza into DB
                           }
                       }
                       
                    }
                   response.sendRedirect("Checkout.jsp?order=" + orderAdded + "");                                        
                }else{
                    // If it gets here there was an error inserting the order
                    System.out.println("Error inserting into DB");
                }
                
            }
            out.println("</body>");
            out.println("</html>");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(addOrderDelivery_proc.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(addOrderDelivery_proc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //LEAVE ALONE
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
