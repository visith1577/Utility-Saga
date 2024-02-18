<%--
  Created by IntelliJ IDEA.
  User: visithkumarapperuma
  Date: 2024-02-18
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error Submitting Data</title>
</head>
<body>
<h1>An error occurred!</h1>
<p>We encountered an issue while processing your data. Please try again later.</p>

<% String errorMessage = (String) request.getAttribute("errorMessage"); %>
<% if (errorMessage != null) { %>
<p><b>Specific error message:</b> <%= errorMessage %></p>
<% } %>

<p>You can:</p>
<ul>
    <li>Refresh this page and try again.</li>
    <li>Contact us for assistance.</li>
</ul>
</body>
</html>

