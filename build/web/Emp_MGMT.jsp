<%-- 
    Document   : Emp_MGMT
    Created on : 10-Dec-2019, 6:20:26 PM
    Author     : charl
--%>  

<%@page import="DataLayer_Beans.Toppings_DataLayer"%>
<%@page import="pojos.Topping"%>
<%@page import="pojos.Pizza"%>
<%@page import="pojos.OrderDetails"%>
<%@page import="DataLayer_Beans.Order_DataLayer"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title> Charlie's Wood Fired Pizzas - Employee Manager</title>
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
            <div class="row">
                
                </div>
                <div class="col-md-4 displayBG">
                    			<h3>Add A Topping</h3>
					<form action="addTopping_proc">
						
						<div class="form-group">
							<label for="ToppingName" class="cols-sm-2 control-label">Topping</label>
							<div class="cols-sm-10">
								<div class="input-group">
									
									<input type="text" class="form-control" required name="topping" id="topping"  placeholder="Enter name of the topping"/>
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="price" class="cols-sm-2 control-label">Price</label>
							<div class="cols-sm-10">
								<div class="input-group">
									
									<input type="text" class="form-control" required name="price" id="price"  placeholder="Enter price of the topping"/>
								</div>
							</div>
						</div>
                                                
                                                <div class="form-group">
							<label for="Employee" class="cols-sm-2 control-label">Employee ID</label>
							<div class="cols-sm-10">
								<div class="input-group">
									
									<input type="text" class="form-control" required name="emp" readonly id="emp" value="1"/>
								</div>
							</div>
						</div>
						
						<div class="form-group ">
							<input type="submit" name="button" id="button" value="Add New Topping" class="btnDis btn btn-primary"/>
							
						</div>
						
					</form>
            <div>
                <h1>
            <%
                
                String messageAdd = request.getParameter("msgAdd");
                if(messageAdd != null){
                out.println("<h2>" + messageAdd + "</h2>");
                }
            %>
            
                </h1>
                
            </div>
                </div>
                    <div class="col-md-4 displayBG">
                        <form action="updateOrders_proc">
                         <h3>Check Orders</h3>
                         <%
                             ArrayList<Integer> orders = Order_DataLayer.FetchOrders();
                              if(orders == null){
                                  out.println("No orders at this time");
                              }else{
                                  int numOfOrders = orders.size();
                                  int itr = 1;
                                for(Integer o:orders){  
                                    OrderDetails od = Order_DataLayer.FetchOrderDetails(o);
                                    
                                    out.println("<div class=\"form-group\">");
                                    out.println("<h3> Order for " + od.getCustomer().getFirstName() + " " + od.getCustomer().getLastName() +  "</h3>");
                                        out.println("<label for=\"details\" class=\"cols-sm-2 control-label\">Order Details</label>");
                                        out.println("<div class=\"cols-sm-10\">");
                                            out.println("<div class=\"input-group\">");
                                            ArrayList<Pizza> pizzas = od.getOrder().getPizzas();
                                            
                                            int piz = 1; 
                                                
                                            out.println("<ul>");
                                            for(Pizza p:pizzas){
                                                    out.println("<li>Pizza " + piz + "</li>");
                                                    out.println("<li> Size: " + p.getSize() + "</li>");
                                                    out.println("<li> Crust:" + p.getCrust() + "</li>");
                                                    out.println("<li> Toppings: " + p.getToppings() + "</li>");
                                                    piz++;
                                            }
                                            out.println("</ul>");
                                            out.println("</div>");
                                            out.println("<h4>Order Total: $"+ od.getOrder().getTotalPrice() + "</h4>");
                                            out.println("<input type=\"checkbox\" name='update"+itr+"' value='"+o+"'>Check as filled</input><br>");
                                            itr++;
                                        out.println("</div>");
                                    out.println("</div>");
                                    
                                }
                              out.println("<input type=\"hidden\" name=\"numOfOrders\" value='"+numOfOrders+"' />");
                            }
                         %>
                                                <div class="form-group ">
							<input type="submit" name="button" id="button" value="Update Order" class="btnDis btn btn-primary"/>
							
						</div>                         
                    </form>
            <div>
                <h3>
            <%
                
                String messageUpdate = request.getParameter("msgUpdate");
                if(messageUpdate != null){
                    out.println("<h1>" + messageUpdate + "</h1>");
                }
            %>
                </h3>
                
            </div>
                
                    </div>
                <div class="col-md-4 displayBG">
                    <h3>Delete Topping</h3>
                    <form action="deleteTopping_proc">
                        
                        <div class="form-group">
                            <label for="toppinToDelete" class="cols-sm-2 control-label">Select Topping</label>
                            <div class="cols-sm-10">
                                <div class="input-group">
                                    <select name="topping">
                                        <option></option>
                                            <%
                                                ArrayList<Topping> topps = Toppings_DataLayer.FetchAllToppings();

                                                for(Topping t:topps){
                                                        out.println("<option value='"+t.getToppingID() + "'>"+ t.getToppingName()+"</option>");
                                                }
                                            %>	
                                    </select>
                                </div>
                            </div>
                        </div>
            <input type="hidden" name="emp" value="1" />
                <input type="checkbox" name="accept" value="yes">Delete Selected Topping</input><br>
                
            <input type="submit" name="button" id="button" value="Delete The Topping" class="btnDis btn btn-primary"/>
            <h6>
            <%
                
                String messageDelete = request.getParameter("msgDelete");
                if(messageDelete != null){
                out.println("<h2>" + messageDelete + "</h2>");
                }
            %>
                </h6>
            </form>
            </div>
                
        </div>
        
    </body>
</html>
