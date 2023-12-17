<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
	function createOrder() {
		// Get the selected value from the dropdown
		var selectedOption = document.getElementById('supplierId').value;
		// Construct the URL with the selected value
		var url = '/Inventory_App/purchaseorder?supplierId=' + selectedOption;
		// Redirect to the constructed URL
		window.location.href = url;
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Purchase Order</title>
</head>
<body>
	<%@ include file="navMenu.jsp"%>
	<input type="submit" value="Create Order" onClick="createOrder()" />
	Supplier List:
	<select name="supplierId" id="supplierId">
		<c:forEach var="supplier" items="${supplierMap.entrySet()}">
			<option value="${supplier.key}">${supplier.value}</option>
		</c:forEach>
	</select>
	<table border="1">
		<tr>
			<th>Order Id</th>
			<th>Supplier Id</th>
			<th>Order Date</th>
			<th>Expect Delivery Date</th>
			<th>Status</th>
		</tr>
		<c:forEach var="purchase" items="${purchases}">
			<tr>
				<td>${purchase.orderId}</td>
				<td>${purchase.supplierId}</td>
				<td>${purchase.orderDate}</td>
				<td>${purchase.expDeliveryDate}</td>
				<td>${purchase.status}</td>

			</tr>
		</c:forEach>

	</table>

</body>
</html>