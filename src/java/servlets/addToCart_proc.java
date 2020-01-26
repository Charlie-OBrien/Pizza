/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pojos.Pizza;
import pojos.Topping;

/**
 *
 * @author charl
 */
@WebServlet(name = "addToCart_proc", urlPatterns = {"/addToCart_proc"})
public class addToCart_proc extends HttpServlet {

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
            out.println("<title>Servlet addToCart_proc</title>");            
            out.println("</head>");
            out.println("<body>");
            String crust = request.getParameter("crust");
            String size = request.getParameter("size");
            String toppings = request.getParameter("toppings");
            String quantity = request.getParameter("quantity");
            
            
            
          
            
            int iend = Integer.parseInt(toppings);
            ArrayList<String> toppingsTest = new ArrayList<>();
            ArrayList<String> ids = new ArrayList<>();
            String topping = "";
            double prices = 0.0;
            
            int crustDivide = crust.indexOf("|");
            StringBuilder cID = new StringBuilder();
            int crustID = Integer.parseInt(cID.append(crust, crustDivide+1, crust.length()).toString());
            StringBuilder cN = new StringBuilder();
            String CrustName = cN.append(crust, 0, crustDivide).toString();
            crust = CrustName;
            
            
            int sizeDivide = size.indexOf("|");
            StringBuilder sID = new StringBuilder();
            int sizeID = Integer.parseInt(sID.append(size, sizeDivide+1, size.length()).toString());
            StringBuilder sN = new StringBuilder();
            String SizeName = sN.append(size, 0, sizeDivide).toString();
            size = SizeName;
            
            for(int i = 1; i < iend; i++){
                try{
                    
                   String top = request.getParameter("topping"+i+"");
                   if(top != null){
                       int divide = top.indexOf("|");
                       int divide2 = top.lastIndexOf("|");
                       int start = divide+1;
                       int conti = divide2+1;
                       int end = top.length();
                       StringBuilder id = new StringBuilder();
                       String idFinal = id.append(top,0,divide).toString();
                       ids.add(idFinal);
                       StringBuilder pri = new StringBuilder();
                       prices += Double.parseDouble(pri.append(top, conti, end).toString());
                       StringBuilder to = new StringBuilder();
                       String tfinal = to.append(top, start, divide2).toString();
                       toppingsTest.add(tfinal);
                       topping += tfinal + "|";
                   }
                }catch(Exception ex){
                    System.out.println("Error " + ex.getMessage());
                }
            }
            topping += "0"; 
            out.println(topping);
            out.println(crust);
            out.println(size);
            out.println(quantity);
            out.println(prices);
            
            for(int numPizzas = 1; numPizzas <= Integer.parseInt(quantity); numPizzas++){
                
            Pizza pizza = new Pizza(crust,size,topping,prices);
            pizza.settTest(toppingsTest);
            pizza.setToID(ids);
            pizza.setCrustID(crustID);
            pizza.setSizeID(sizeID);
                        
            HttpSession session = request.getSession();
            
            
            if(session.getAttribute("pizzas") == null){
                ArrayList<Pizza> pizzas = new ArrayList<>();
                pizzas.add(pizza);
                session.setAttribute("pizzas", pizzas);
            }else{
                ArrayList<Pizza> pizzas = (ArrayList<Pizza>)session.getAttribute("pizzas");
                pizzas.add(pizza);
            }
            }
            
            int quant = Integer.parseInt(quantity);
            
            if(quant>1){
                //more than one
                response.sendRedirect("order.jsp?msg=Your pizzas were added to your cart");
            }else{
                //only ordering one
               response.sendRedirect("order.jsp?msg=Your pizza was added to your cart"); 
            }
            
            out.println("<h1>Servlet addToCart_proc at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }catch (Exception ex) {
            Logger.getLogger(deleteTopping_proc.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
//leave alone
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
