<%-- 
    Document   : Cart
    Created on : 7-Dec-2019, 11:50:31 PM
    Author     : charl  
--%>

<%@page import="DataLayer_Beans.Pizza_DataLayer"%>
<%@page import="java.io.IOException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="pojos.Pizza"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Charlie's Wood Fired Pizzas - Cart</title>
  <meta charset="utf-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="styles.css"/>
  <script>
      $(document).ready(function() {
		
                
		
		$("#deliveryForm").hide();		
	});
      function displayChoice(){
          
          
          if($("#option").val() == "1"){
              
              $("#deliveryForm").show();              
          }
          
          //keep this for testing and debug
          if($("#option").val() == "0"){
              
              $("#deliveryForm").hide();
          }
      }
      
 
      
  </script>
</head>

<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
        <a class="navbar-brand" href="order.jsp">Charlie's Wood Fired Pizzas</a>
    </div>
    <ul class="nav navbar-nav">
        <li><a href="order.jsp">Order</a></li>
        <li><a href="Cart.jsp">Cart</a></li>

    </ul>
      <ul class="nav navbar-nav navbar-right">
      
          <li><a href="Emp_Login.xhtml"><span class="glyphicon glyphicon-log-in"></span> Employee Login</a></li>
    </ul>
  </div>
</nav>
    <body>
        <div class="containerD">
            <div class="col-md-4 displayBG">
                                                    
                <form action="addOrderDelivery_proc" id="deliveryForm">
                    <h1 style="color: black">Enter Address For Delivery</h1>
                                                 
                                                <div class="form-group">
							<label for="fname" class="cols-sm-2 control-label">First Name</label>
							<div class="cols-sm-10">
								<div class="input-group">
                                                                    <input type="text" class="form-control" required name="firstname" id="firstname"  placeholder="Enter your first name"/>
								</div>
							</div>
						</div>
                                                <div class="form-group">
							<label for="lname" class="cols-sm-2 control-label">Last Name</label>
							<div class="cols-sm-10">
								<div class="input-group">
                                                                    <input type="text" class="form-control" required name="lastname" id="lastname"  placeholder="Enter your last name"/>
								</div>
							</div>
						</div>
                                                
						<div class="form-group">
							<label for="address" class="cols-sm-2 control-label">Street</label>
							<div class="cols-sm-10">
								<div class="input-group">
                                                                    <input type="text" class="form-control" required name="address" id="address"  placeholder="Enter your street"/>									
								</div>
							</div>
						</div>
                                                <div class="form-group">
							<label for="house" class="cols-sm-2 control-label">House Number</label>
							<div class="cols-sm-10">
								<div class="input-group">
                                                                    <input type="text" class="form-control" required name="house" id="house"  placeholder="Enter your house number"/>									
								</div>
							</div>
						</div>
                                                <div class="form-group">
							<label for="postalcode" class="cols-sm-2 control-label">Postal Code</label>
							<div class="cols-sm-10">
								<div class="input-group">
                                                                    <input type="text" class="form-control" required name="postalcode" id="postalcode"  placeholder="Enter your postal code"/>									
								</div>
							</div>
						</div>
                                                <div class="form-group">
							<label for="province" class="cols-sm-2 control-label">Province</label>
							<div class="cols-sm-10">
								<div class="input-group">
									
                                                                    <select name="province" id="province" maxlength="50" class="textfield1" required> 
										<option> </option>
                                                                                <option value="AB">Alberta</option>
										<option value="BC">British Columbia</option>
										<option value="MB">Manitoba</option>
                                                                                <option value="NB">New Brunswick</option>
                                                                                <option value="NL">Newfoundland and Labrador</option>
                                                                                <option value="NT">Northwest Territories</option>
                                                                                <option value="NS">Nova Scotia</option>
                                                                                <option value="NU">Nunavut</option>
                                                                                <option value="ON">Ontario</option>
                                                                                <option value="PE">Prince Edward Island</option>
                                                                                <option value="QC">Quebec</option>
										<option value="SK">Saskatchewan</option>
																	
										<option value="YT">Yukon</option>
									  </select>
								</div>
							</div>
						</div>
                                                <div class="form-group">
							<label for="phone" class="cols-sm-2 control-label">Phone number</label>
							<div class="cols-sm-10">
								<div class="input-group">
                                                                 <input type="text" class="form-control" required name="phone" id="phone"  placeholder="Enter your phone number"/>           
								</div>
							</div>
						</div>
                                                <div class="form-group">
							<label for="email" class="cols-sm-2 control-label">Email</label>
							<div class="cols-sm-10">
								<div class="input-group">
                                                                    <input type="text" class="form-control" required name="email" id="email"  placeholder="Enter your email"/>									
								</div>
							</div>
						</div>
						
						<div class="form-group ">
							<input type="submit" name="button" id="button" value="Pay At Delivery" class="btnDis btn btn-primary btn-block"/>
							
						</div>
                                                <div>
                                                    <script>
                                                        
                                                        </script>
							<input type="button" onclick="window.location.href = 'https://www.paypal.com';" value="PayPal" class="btn btn-primary btnDis btn-block"/>
							
						</div>
						
					</form>
            </div>
        <div class="col-md-4 displayBG">
            <form>
        <h1 style="color: black">Your Order</h1>
        <%

            
            ArrayList<Pizza> pizzas = (ArrayList<Pizza>)session.getAttribute("pizzas");
            if(pizzas != null){
              
            double TotalPrice = 0.0;
            int numberOfPizzas = 1; %>
                                                    <div id="options">
							<label for="option" class="cols-sm-2 control-label">Select Delivery</label>
							<div class="cols-sm-10">
								<div class="input-group">
                                                                    <select name="option" id="option" required onchange="displayChoice()">
                                                                        <option value="0"></option>
                                                                        <option value="1">Delivery</option>
                                                                        
                                                                    </select>									
								</div>
							</div>
                                                    </div>
        <%                   
            //for Pizza pizza in pizzas
            for(Pizza pizza:pizzas){
                out.println("<h3>Pizza "+numberOfPizzas+"</h3>");
                
                    out.println("<div class=\"form-group\">");
                        out.println("<label for=\"crust\" class=\"cols-sm-2 control-label\">Crust Choice</label>");
                        out.println("<div class=\"cols-sm-10\">");
                            out.println("<div class=\"input-group\">");
                            out.println(pizza.getCrust());
                            out.println("</div>");
                        out.println("</div>");
                    out.println("</div>");
                    out.println("<div class=\"form-group\">");
                        out.println("<label for=\"size\" class=\"cols-sm-2 control-label\">Size Choice</label>");
                        out.println("<div class=\"cols-sm-10\">");
                            out.println("<div class=\"input-group\">");
                            out.println(pizza.getSize());
                            out.println("</div>");
                        out.println("</div>");
                    out.println("</div>");
                    out.println("<div class=\"form-group\">");
                        out.println("<label for=\"toppings\" class=\"cols-sm-2 control-label\">Toppings Choice</label>");
                        out.println("<div class=\"cols-sm-10\">");
                            out.println("<div class=\"input-group\">");
                            
                            ArrayList<String> tested = pizza.getToTest();
                            out.println("<ul>");
                            for(String test:tested){
                                if(!test.contains("0")){
                                    out.println("<li>" + test + "</li>");
                                }
                            }
                            out.println("</ul>");
                            
                            out.println("</div>");
                        out.println("</div>");
                    out.println("</div>");
                    out.println("<div class=\"form-group\">");
                        out.println("<label for=\"price\" class=\"cols-sm-2 control-label\">Individual Pre Tax Price </label>");
                        out.println("<div class=\"cols-sm-10\">");
                            out.println("<div class=\"input-group\">");
                            double price = Pizza_DataLayer.CalculatePrice(pizza, pizza.getTopPrice());
                            double priceRound = Math.round(price * 100.0)/100.0;
                            String priceToDisplay = Double.toString(priceRound);                            
                            pizza.setPizzaPrice(priceRound);
                            TotalPrice += priceRound;
                            out.println("$" + priceToDisplay);
                            out.println("</div>");
                        out.println("</div>");
                    out.println("</div>");                   
                numberOfPizzas++;
            }
            
            out.println("<h2>Total</h2>");
            out.println("<div class=\"form-group\">");
                        out.println("<label for=\"total\" class=\"cols-sm-2 control-label\">Total Including Sales Tax (15%)</label>");
                        out.println("<div class=\"cols-sm-10\">");
                            out.println("<div class=\"input-group\">");
                            double TotalPriceRound = Math.round((TotalPrice*1.15)*100.0)/100.0;
                            session.setAttribute("OrderTotal", TotalPriceRound);
                            out.println("$"+TotalPriceRound);
                            out.println("</div>");
                        out.println("</div>");
            out.println("</div>");
          }else{
                out.println("<h3>There are no pizzas in your cart!</h3>");
          }   
        %>
        </form>
        </div>
        </div>
        
    </body>
</html>
