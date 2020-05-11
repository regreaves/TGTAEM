<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
  <head>
    <title>Login</title>
    <link
      rel="stylesheet"
      href="${pageContext.request.contextPath}/_view/stylesheet.css"
    />
  </head>

  <body>
      <div id="output">
        <br>${log}<br>

        <form
          action="${pageContext.servletContext.contextPath}/user"
          method="post"
        >
        	<div class="split">
				<div class="prompt">
					&gt;
				</div>
          <input name="input" placeholder="command?" autofocus />
          <button type="submit" style="visibility: hidden;"></button>
          </div>
        </form>
		<c:if test="${! empty errorMessage}">
          <div class="error">${errorMessage}</div>
        </c:if>
      </div>
  </body>
</html>