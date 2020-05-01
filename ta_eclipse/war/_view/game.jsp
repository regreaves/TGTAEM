<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<title>Game</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/_view/stylesheet.css" />
</head>

<body>
	<form id="myForm" class="myForm"
		action="${pageContext.servletContext.contextPath}/game" method="post">
		<div class="myForm myForm-terminal">${log}
			<input type="text" class="myForm myForm-input" name="command"
				placeholder="Command?" autofocus /> <input type="submit"
				style="visibility: hidden;">
		</div>
	</form>
</body>

</html>
