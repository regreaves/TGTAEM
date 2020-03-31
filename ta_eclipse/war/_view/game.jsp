<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Game</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/_view/stylesheet.css" />
	</head>

	<body>
		<div style="width:30%; height:30%; margin:auto;">
			<form action="${pageContext.servletContext.contextPath}/game" method="post">
				<div style="font-family:Helvetica; font-size:0.75em;
					color:#a9a9a9; width:100%; padding:0.25em; margin:auto;">${log}</div>
				<input type="text" name="command" placeholder="Command..." style="width:100%; margin:auto;" autofocus />
			</form>
		</div>
	</body>

	<script>

	</script>
</html>
