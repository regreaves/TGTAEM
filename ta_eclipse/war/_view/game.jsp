<!DOCTYPE html>
<!--
    [!] Are the following two taglibs necessary for game.jsp?
        Removing doesn't seem to affect operations. -R
 -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<head>
<title>Game</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/_view/stylesheet.css" />
</head>

<body>
	<div id="outer">
	<div id="output">
			<p>${log}</p>
			<form id="myForm"
				action="${pageContext.servletContext.contextPath}/game"
				method="post">
				<input id="input" class="input" name="command"
					placeholder="command?" autofocus autocomplete="off"/>
					<button type="submit" style="visibility: hidden;"></button>
			</form>

		</div>
	</div>
</body>

</html>
