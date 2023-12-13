<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="navMenu.jsp" %>
	 <h2>Product Management</h2>

    <form action="supplier" method="post">
        
        <h3>Supplier Details:</h3>
        SupplierId: <input type="text" name="supplierId" required><br><br>
       SupplierName  <input type="text" name="supplierName" required><br><br>
        Place  <input type="text" name="place" required><br><br>
         <input type="hidden" name="action" value="add">
        <input type="submit" value="Add Supplier">

        <!-- Product List Section -->
        <h3>Product List:</h3>
        <table border="1">
            <tr>
                <th>SupplierId</th>
                <th>SupplierName</th>
                 <th>Description</th>
                  <th>Place</th>
                <!-- Add other table headers for product details -->
            </tr>
            <c:forEach var="suppliers" items="${supplier}">
                <tr>
                    <td>${suppliers.SupplierId}</td>
                    <td>${suppliers.SupplierName}</td>
                    <td>${suppliers.Place}</td>
                    <!-- Add other table cells for product details -->
                </tr>
            </c:forEach>
        </table>
    </form>
</body>
</html>