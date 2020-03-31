<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Game</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/_view/stylesheet.css" />
	</head>

	<body>
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
		
		<div>${log}</div>
		
		<textarea disabled id="myTextarea" style="resize:none;">${log}&#10;</textarea>
		
		<form action="${pageContext.servletContext.contextPath}/game" method="post">
			<input type="text" name="first" placeholder="Command" style="width:10%;"/>
		</form>
	</body>
	
	<script>

	</script>
</html>
