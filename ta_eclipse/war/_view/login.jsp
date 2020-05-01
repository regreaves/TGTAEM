<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<title>Login</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/_view/stylesheet.css" />
</head>

<body>
	<form id="myForm" class="myForm"
		action="${pageContext.servletContext.contextPath}/login" method="post">
		<div class="myForm myForm-terminal">${log}
			<input type="text" class="myForm myForm-input" name="username"
				placeholder="Username:" value="Arthur Dent" autofocus /><br> <input
				type="password" class="myForm myForm-input" name="password"
				placeholder="Password:" value="hitchhiker" /> <input type="submit"
				style="visibility: hidden;">
		</div>
	</form>
</body>
</html>
