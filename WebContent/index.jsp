<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<body>
	<c:if test="${not empty error}">
		<p style="color: red;">Invalid username or password. Please try again.</p>
	</c:if>
	<form action="login" method="post">
		<div>
			<label for="username">Username:</label> 
			<input type="text" id="username" name="username">
		</div>
		<div>
			<label for="password">Password:</label> 
			<input type="password" id="password" name="password">
		</div>
		<input type="submit" value="Login">
	</form>
</body>
</html>
