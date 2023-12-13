<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sales Management</title>
</head>
<body>
	 <%@ include file="navMenu.jsp" %>
	 <h2>Sales Management</h2>
	 <form action="sales" method="post">
        <!-- Add Product Section -->
        <h3>Add Sales:</h3>
        Sale Id: <input type="text" name="saleId" required><br><br>
       Sale Product  <input type="text" name="saleProduct" required><br><br>
        Sale Price  <input type="text" name="salePrice" required><br><br>
         Sale Quantity  <input type="text" name="saleQuantity" required><br><br>
        <!-- Add other input fields for product details -->
        <input type="hidden" name="action" value="add">
        <input type="submit" value="Add Sales">
 </form>
        <!-- Sales List Section -->
        <h3>Sales List:</h3>
        <table border="1">
            <tr>
                <th>saleId</th>
                <th>Sale Product</th>
                 <th>Sale Price</th>
                  <th>Sale Quantity</th>
                <!-- Add other table headers for product details -->
            </tr>
            <c:forEach var="sale" items="${sales}">
                <tr>
                   <td>${sale.saleId}</td>
                    <td>${sale.saleProduct}</td>
                    <td>${sale.salePrice}</td>
                    <td>${sale.saleQuantity}</td>
                    <!-- Add other table cells for product details -->
                </tr>
            </c:forEach>
        </table>
   
</body>
</html>