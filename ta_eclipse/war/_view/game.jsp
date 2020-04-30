<!DOCTYPE html>
<html>
<head>
<title>Game</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/_view/stylesheet.css" />
</head>

<body>
	<form id="myForm"
		action="${pageContext.servletContext.contextPath}/game" method="post">
		<div>${log}<br><input id="input" class="input" name="command"
			placeholder="command?" autofocus autocomplete="off">
		<input type="submit" value=""></div>
	</form>
</body>

</html>
