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
	
		<form action="${pageContext.servletContext.contextPath}/game" method="post">
			<table>
				<tr>
					<td class="label">Input:</td>
					<td><input type="text" name="first" size="12" value="${surprise.first}" /></td>
				</tr>
				<tr>
					<td class="label">Result:</td>
					<td>${surprise.result}</td>
				</tr>
			</table>
			<input type="Submit" name="submit" value="Game!">
		</form>
	</body>
</html>
