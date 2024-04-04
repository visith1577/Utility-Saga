    <%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP - Hello World</title>
        <link rel="stylesheet" href="../CSS/start.css">
    </head>
    <body>
      <form action="${pageContext.request.contextPath}/initialize" method="post">
          <button type="submit">initialize</button>
      </form>
    </body>
</html>