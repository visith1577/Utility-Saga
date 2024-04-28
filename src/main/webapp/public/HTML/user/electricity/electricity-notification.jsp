<%--
  Created by IntelliJ IDEA.
  User: NETHMI LIYANAGE
  Date: 4/20/2024
  Time: 6:55 PM
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
    <title>User-Notification</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/dashboard.css">
    <link href="<%= request.getContextPath() %>/public/CSS/dashboards/Admin/adminnotification.css" rel="stylesheet">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

</head>
<body>
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
                <li class="menu-items-li"><a href="#">Home</a></li>
                <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/pages/aboutUs.jsp">About Us</a></li>
                <li class="menu-items-li"><a
                        href="<%= request.getContextPath() %>/public/HTML/user/water/water-contact.jsp">Contact Us</a>
                </li>
                <li class="menu-items-li">
                    <a href="<%= request.getContextPath() %>/user/electricity-notification">
                        <span class="material-icons">notifications</span>
                    </a>
                </li>
                <li class="nxt-page water"><button class="button-17" type="button" onclick="toggle()">Dashboards</button></li>
                <script>
                    function toggle() {
                        window.location.href = "<%= request.getContextPath() %>/user/electricity-dashboard"
                    }
                </script>
                <li class="img_user dropdown">
                    <a href="<%= request.getContextPath() %>/user/user-profile">
                        <button class="user-profile">
                            <%
                                // Retrieve the Image attribute from the session
                                Object image = session.getAttribute("IMAGE");

                                if (image == null) {
                            %>
                            <img alt="User" src="<%= request.getContextPath() %>/public/images/user.svg"
                                 style="width: 4vh; height: 4vh">
                            <%
                            } else {
                            %>
                            <img class="image-profile" src="data:image/jpeg;base64,<%= image %>" alt="image"
                                 style="width: 5vh; height: 5vh">
                            <%
                                }
                            %>
                        </button>
                        <div class="dropdown-content">
                            <a href="<%= request.getContextPath() %>/user/user-settings"><c:out value="${'<b> Settings </b>'}" escapeXml="false"/></a>
                            <a href="<%= request.getContextPath() %>/public/HTML/user/payments.jsp"><c:out value="${'<b> Payments </b>'}" escapeXml="false"/></a>
                            <a id="logout" href="<%= request.getContextPath() %>/logout">LogOut</a>
                        </div>
                    </a>
                </li>
            </ul>
            <img src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt="Utility Saga" class="logo">
        </div>
    </header>
</div>
<div class="GetNotification-box">
    <h2>Notifications from the Electricity Board</h2>
    <table>
        <thead>
        <tr>
            <th>Title</th>
            <th>Date</th>
            <th>Subject</th>
            <th>Message</th>
        </tr>
        </thead>
        <tbody>
        <c:if test="${empty requestScope.notifications}">
            <tr>
                <td colspan="4">No notifications found</td>
            </tr>
        </c:if>
        <c:if test="${not empty requestScope.notifications}">
            <c:forEach items="${requestScope.notifications}" var="notification">
                <tr>
                    <td>${notification.title}</td>
                    <td>${notification.date}</td>
                    <td>${notification.subject}</td>
                    <td>${notification.message}</td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

</div>

</body>
</html>
