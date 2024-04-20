<%--
  Created by IntelliJ IDEA.
  User: NETHMI LIYANAGE
  Date: 4/19/2024
  Time: 5:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contextPath = request.getContextPath();
%>
<%@ page import="DAO.dao.sendNotificationDao" %>
<%@ page import="java.util.List" %>
<%@ page import="model.SendNotificationModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="<%= request.getContextPath() %>/public/JS/ElectricityMainAdmin.js"></script>
    <link href="<%= request.getContextPath() %>/public/CSS/dashboards/Admin/adminnotification.css" rel="stylesheet">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/dashboard.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/forms.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <title>Electricity Main Admin Notifications</title>
</head>
<body>
<div class="wrapper">
    <div class="navv">
        <header class="navbar">
            <div class="navbar-container container">
                <label for="hamburger"></label>
                <input type="checkbox" name="hamburger" id="hamburger">
                <div class="hamburger-lines">
                    <span class="line line1"></span>
                    <span class="line line2"></span>
                    <span class="line line3"></span>
                </div>
                <ul class="menu-items">
                    <li class="menu-items-li">
                        <a href="<%= request.getContextPath() %>/public/HTML/electricity/admin/AdminNotificationPg-electricity.jsp">
                            <span class="material-icons">notifications</span>
                        </a>
                    </li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/main-admin/electricity-accounts">Regional Admins</a></li>
                    <li class="menu-items-li"><a id="logout" href="<%= request.getContextPath() %>/logout">LogOut</a></li>
                </ul>
                <img src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt="Utility Saga" class="logo">
            </div>
        </header>
    </div>

<div class="GetNotification-box">
    <table>
        <thead>
        <tr>
            <th>Title</th>
            <th>Subject</th>
            <th>Message</th>
        </tr>
        </thead>
        <tbody>
        <%
            sendNotificationDao dao = new sendNotificationDao();
            List<SendNotificationModel> notifications = dao.getNotifications(SendNotificationModel.Type.ELECTRICITY);
            for (SendNotificationModel notification : notifications) {
        %>
        <tr>
            <td><%= notification.getTitle() %></td>
            <td><%= notification.getSubject() %></td>
            <td><%= notification.getMessage() %></td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</div>
</body>
</html>
