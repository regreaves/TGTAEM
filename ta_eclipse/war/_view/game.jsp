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
				<textarea id="input" class="input" name="command" rows="1"
					placeholder="command?" autofocus></textarea>
			</form>
		</div>
	</div>
</body>

<script>
	window.onload = function() {
		var myDiv = document.getElementById('output');
		var myInput = document.getElementById('input');
		myDiv.scrollTop = myDiv.scrollHeight;
		input.scrollTop = myDiv.scrollHeight;

		input.onkeydown = function(ev) {

			var k = ev.which || ev.keyCode;
			if (k == 13) {
				document.getElementById("myForm").submit();
			}
		}
	};
</script>
</html>
