<%--
  Created by IntelliJ IDEA.
  User: NETHMI LIYANAGE
  Date: 4/20/2024
  Time: 12:48 AM
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
    <link href="<%= request.getContextPath() %>/public/CSS/dashboards/Admin/eadsendnotification.css" rel="stylesheet">
    <link href="../../CSS/superadmin/superadmin-notifelecwater.css" rel="stylesheet">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/dashboard.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <title>Electricity Regional Admin Notifications</title>
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
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/electricity/regional-admin/user-accounts">Customers</a></li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/electricity/regional-admin/complaints">Complaints</a></li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/electricity/regionalAdmin/electricity-paymenthandle.jsp">Payment</a></li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/electricity/regional-admin/connections">New Connection</a></li>
                    <li class="menu-items-li dropdown">
                        <a href="#" class="profile-icon"><i class="fa-solid fa-circle-user"></i></a>
                        <div class="dropdown-content">
                            <a href="<%= request.getContextPath() %>/public/HTML/electricity/regionalAdmin/settings.jsp">Settings</a>
                            <a href="<%= request.getContextPath() %>/logout">LogOut</a>
                        </div>
                    </li>
                </ul>
                <img src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt="Utility Saga" class="logo">
            </div>
        </header>
    </div>
</div>
<div class="fcontainer">
    <h2 class="heading">Notification Handling | To Users</h2>
    <div class="form-box">
        <form id="addnotif" method="POST" action="${pageContext.request.contextPath}/raelecNotif">
            <%--@declare id="startdate"--%><label for="title">Notification Title:</label>
            <input type="text" name="title" id="title" required>
            <br>

            <label>Recipient Type:</label>
            <div class="radio-btn-class">
                <input type="radio" id="ALL" name="recipientType" value="ALL" checked>
                <label for="ALL">All Users</label>
                <input type="radio" id="SPECIFIC" name="recipientType" value="SPECIFIC">
                <label for="SPECIFIC">Specific User</label>
                <input type="text" id="recipientId" name="recipientId" placeholder="User ID" disabled>
            </div>
                <br>


                <label for="subject">Subject</label>
                <input type="text" id="subject" name="subject" required>
                <label for="message">Message</label>
                <textarea id="message" name="message" required></textarea>

            <div class="btn-container">
                <button class="btn" id="send">Send</button>
                <button class="btn" id="cancel">Cancel</button>
                <button class="btn" id="reset">Reset</button>
            </div>
        </form>
    </div>
</div>
<script>
    const recipientType = document.querySelectorAll('input[name="recipientType"]');
    const recipientId = document.getElementById('recipientId');

    recipientType.forEach(radio => {
        radio.addEventListener('change', () => {
            if (radio.value === 'SPECIFIC') {
                recipientId.disabled = false;
            } else {
                recipientId.disabled = true;
                recipientId.value = '';
            }
        });
    });
</script>
</body>
</html>
