<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file = "navMenu.jsp" %>
	
	<form action="purchaseorder" method = "post">
		<input type = "hidden" name = "supplierId" value = "${supplierId}">
		Add Item: 
		Product<select name="product">
			<c:forEach var="product" items="${products}">
				<option value = "${product.productID}">${product.productName}</option>
			</c:forEach>
		</select><br><br>
		Quantity<input type = "text" name = "quantity"/><br><br>
		<input type = "submit" value = "Add Item"/>
	</form>
	<table border = 1>
		<tr>
			<th>Product Name</th>
			<th>Quantity</th>
		</tr>
		<c:forEach var = "purchases" items = "${purchase}">
		<tr>
			
			<td>${purchases.productName}</td>
			<td>${purchases.stockQuantity}</td>
		</tr>
		</c:forEach>
	</table>
</body>
</html>