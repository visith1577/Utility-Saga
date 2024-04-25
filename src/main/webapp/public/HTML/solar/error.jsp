<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ShyamalHAD
  Date: 4/23/2024
  Time: 12:30 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="DAO.dao.ItemDetailsDAO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            text-align: center;
        }

        .container {
            margin-top: 100px;
        }

        h1 {
            color: #333;
        }

        #errorMessage {
            margin-bottom: 20px;
        }

        #errorLabel {
            color: red;
            font-size: 18px;
        }

        #buttonContainer {
            margin-top: 20px;
        }

        button {
            padding: 10px 20px;
            font-size: 16px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Error Page</h1>

    <div id="errorMessage">
        <h1><%= request.getAttribute("errorMessage")%> </h1>
        <label id="errorLabel">An error occurred!</label>
    </div>

    <div id="buttonContainer">
        <%-- Button to handle error --%>
        <button><a href="<%= request.getContextPath()%>/public/HTML/login/companyRegister.jsp" >OK</a> </button>
    </div>
</div>

</body>
</html>


