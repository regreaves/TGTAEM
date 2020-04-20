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
    <div id="outer">
      <div id="output">
        <p>${log}</p>
        <form
          id="myForm"
          action="${pageContext.servletContext.contextPath}/login"
          method="post"
        >
          <input name="username" placeholder="Username:" value="student" autofocus />
          <input name="password" placeholder="Password:" value="ycp" />
          <button type="submit" style="visibility: hidden;"></button>
        </form>

				<c:if test="${! empty errorMessage}">
          <div class="error">${errorMessage}</div>
        </c:if>

      </div>
    </div>
  </body>
</html>
