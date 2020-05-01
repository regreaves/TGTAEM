<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<title>User</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/_view/stylesheet.css" />
</head>

<body>
	<form id="myForm" class="myForm"
		action="${pageContext.servletContext.contextPath}/user" method="post">
		<div class="myForm myForm-terminal">${log}
			<input type="text" class="myForm myForm-input" name="input"
				placeholder="command?" autofocus /><br> <input type="submit"
				style="visibility: hidden;">
		</div>
	</form>
</body>
</html>
