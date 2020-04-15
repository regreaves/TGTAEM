<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Index</title>
	</head>

	<body>
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
	
		<form action="${pageContext.servletContext.contextPath}/index" method="post">
			<table>
				<tr>
					<td class="label">Game:</td>
					<td><input type="text" name="game" size="12" value="${game}" /></td>
				</tr>
			</table>
			<input type="Submit" name="submit" value="Index">
		</form>
	</body>
</html>
