<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Product Management</title>
</head>
<body>
    <%@ include file="navMenu.jsp" %>

    <h2>Product Management</h2>

    <form action="products" method="post">
        <!-- Add Product Section -->
        <h3>Add Product:</h3>
        Product Name: <input type="text" name="productName" required><br><br>
       Description  <input type="text" name="Description" required><br><br>
        Category  <input type="text" name="Category" required><br><br>
         Price  <input type="text" name="Price" required><br><br>
         Stock Quantity   <input type="text" name="stockQuantity" required><br><br>
        <!-- Add other input fields for product details -->
        <input type="hidden" name="action" value="add">
        <input type="submit" value="Add Product">
 </form>
        <!-- Product List Section -->
        <h3>Product List:</h3>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Product Name</th>
                <th>Description</th>
                <th>Category</th>
                <th>Price</th>
                <th>Stock Quantity</th>
                <!-- Add other table headers for product details -->
            </tr>
           	<c:forEach var="product" items="${products}">
           		<tr>
           			<td>${product.productName}</td>
           			<td>${product.description}</td>
           			<td>${product.category}</td>
           			<td>${product.price}</td>
           			<td>${product.stockQuantity}</td>
           		</tr>
           	</c:forEach>
        </table>
   
</body>
</html>
