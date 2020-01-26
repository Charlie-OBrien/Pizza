<%-- 
    Document   : Checkout
    Created on : 9-Dec-2019, 10:01:29 PM
    Author     : charl
--%>  

<%@page import="pojos.Customer"%>
<%@page import="pojos.Pizza"%>
<%@page import="java.util.ArrayList"%>
<%@page import="pojos.OrderDetails"%>
<%@page import="DataLayer_Beans.Order_DataLayer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Charlie's Wood Fired Pizzas - Checkout</title>
  <meta charset="utf-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="styles.css"/>
</head>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
        <a class="navbar-brand" href="order.jsp">Charlie's Wood Fired Pizzas</a>
    </div>
    <ul class="nav navbar-nav">
        <li ><a href="order.jsp">Order</a></li>
        <li><a href="Cart.jsp">Cart</a></li>

    </ul>
      <ul class="nav navbar-nav navbar-right">
      
          <li><a href="Emp_Login.xhtml"><span class="glyphicon glyphicon-log-in"></span> Employee Login</a></li>
    </ul>
  </div>
</nav>
    <body>
        <div class="container">
            <div class="col-md-3"></div>
            <div class="col-md-6 displayBG">
        
        <form>
        <%
            
            int orderID = Integer.parseInt(request.getParameter("order"));
            
            OrderDetails od = Order_DataLayer.FetchOrderDetails(orderID);
                
                out.println("<h2>" + od.getCustomer().getFirstName() + ", we have started your order</h2>");
                
                    out.println("<div class=\"form-group\">");
                        out.println("<label for=\"order\" class=\"cols-sm-2 control-label\">Order Total</label>");
                        out.println("<div class=\"cols-sm-10\">");
                            out.println("<div class=\"input-group\">");
                            out.println("$"+od.getOrder().getTotalPrice());
                            out.println("</div>");
                        out.println("</div>");
                    out.println("</div>");
                    out.println("<div class=\"form-group\">");
                        out.println("<label for=\"timeDelivey\" class=\"cols-sm-2 control-label\">Your order is going to be ready in</label>");
                        out.println("<div class=\"cols-sm-10\">");
                            out.println("<div class=\"input-group\">");
                            out.println("30 minutes");
                            out.println("</div>");
                        out.println("</div>");
                    out.println("</div>");
                    out.println("<div class=\"form-group\">");
                        out.println("<label for=\"details\" class=\"cols-sm-2 control-label\">Order Details</label>");
                        out.println("<div class=\"cols-sm-10\">");
                            out.println("<div class=\"input-group\">");
                            ArrayList<Pizza> pizzas = od.getOrder().getPizzas();
                            
//int numpi = pizzas.size();

int pizza = 1; 
                            
                            out.println("<ul>");
                            
                            
                            /**
                             * Not ideal to have a variable pis 
                             * as a stand in for pizzas
                             * could not use pizza or pizzas 
                             * pies stand in for "pizzas"
                             */
                            
                            //for Pizza pis in pizzas
                            for(Pizza pies:pizzas){
                                    out.println("<li>Pizza " + pizza + "</li>");
                                    out.println("<li> Size: " + pies.getSize() + "</li>");
                                    out.println("<li> Crust:" + pies.getCrust() + "</li>");
                                    out.println("<li> Toppings: " + pies.getToppings() + "</li>");
                                    out.println("<li> Price Per Pizza Pre Tax: $" + pies.getPizzaPrice() + "</li>");
                                    pizza++;
                                    out.println("<BR/>");
                            }
                            out.println("</ul>");
                            out.println("</div>");
                        out.println("</div>");
                    out.println("</div>");                   
                
        %>
        </form>
            </div>
        </div>
    </body>
</html>
