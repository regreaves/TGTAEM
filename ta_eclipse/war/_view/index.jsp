<!DOCTYPE html>

<html>
<head>
<title>Index</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/_view/stylesheet.css" />

<script>
	window.onload = function() {

		input.onkeydown = function(ev) {
			var k = (ev.which || ev.keyCode);

			if (k == 13) {
				document.getElementById("myForm").submit();
			}
		}
	};
</script>
</head>

<body>
	<div id="myUsername">${username}</div>
	<!-- <p>${password}</p> -->

	<form id="myForm"
		action="${pageContext.servletContext.contextPath}/index" method="post">
		<textarea id="input" class="input" name="terminalInput" rows="1"
			placeholder="terminalInput" autofocus></textarea>
	</form>
</body>
</html>
