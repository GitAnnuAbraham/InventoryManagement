<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>Welcome to Reports</h3>
	<input type ="hidden"  action = "reports"/>
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