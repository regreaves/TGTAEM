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
		<div id="output">
			<br>${log}
				<form
					action="${pageContext.servletContext.contextPath}/game"
					method="post">
					<div class="split">
						<div class="prompt">
						&gt;
						</div>
						<input id="input" class="input" name="command"
							placeholder="command?" autofocus autocomplete="off"/>
						<button type="submit" style="visibility: hidden;"></button>
					</div>
				</form>
		</div>
</body>
</html>