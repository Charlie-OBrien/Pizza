/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import DataLayer_Beans.Toppings_DataLayer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author charl
 */
@WebServlet(name = "deleteTopping_proc", urlPatterns = {"/deleteTopping_proc"})
public class deleteTopping_proc extends HttpServlet {

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
            out.println("<title>Servlet deleteTopping_proc</title>");            
            out.println("</head>");
            out.println("<body>");
            String Topping = request.getParameter("topping");
            String accept;
            
            try{
                accept = request.getParameter("accept");
            }catch(Exception ex){
                accept = "no";
                System.out.println("Error " + ex.getMessage());
            }
            
            out.println(Topping);
            boolean delete = false;
            
            if(accept !=null && accept.equalsIgnoreCase("yes")){
               delete = Toppings_DataLayer.deleteTopping(Topping);
            }
            
            if(delete == true){
                response.sendRedirect("Emp_MGMT.jsp?msgDelete=Topping Deleted");
            }else{
                //forgot to select check box
                response.sendRedirect("Emp_MGMT.jsp?msgDelete=Topping Coudn't be deleted. Check the check box");
            }
            out.println("<h1>Servlet deleteTopping_proc at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(deleteTopping_proc.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
