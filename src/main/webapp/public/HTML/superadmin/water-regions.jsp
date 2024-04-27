<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Liviru Weerasinghe
  Date: 4/26/2024
  Time: 9:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <title>Water Regions</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
<%--    <link href="<%= request.getContextPath() %>/public/CSS/superadmin/Superadmin-editadmins.css" rel="stylesheet">--%>
    <link href="<%= request.getContextPath() %>/public/CSS/dashboards/Admin/WSuperadmin-editadmins.css" rel="stylesheet">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/dashboard.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/forms.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.all.min.js"></script>
</head>

<style>
    table{
        width: auto;
        float: left;
    }
</style>

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
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/super-admin/region">Electricity Regions</a></li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/super-admin/wregion">Water Regions</a></li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/super-admin/main-electricity-accounts">Electricity/Water</a></li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/super-admin/solar-accounts">Solar</a></li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/logout">Logout</a></li>
                </ul>
                <img src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt="Utility Saga" class="logo">
            </div>
        </header>
    </div>
    <div class="madminmiddle" id="middle">
            <div id="popupContainer" class="addregionscontainer">
                <div class="formdiv">
                <h2 class="popup-title">Add Regions</h2>
                <form id="addForm" method="POST" action="${pageContext.request.contextPath}/water/main-admin/region">
                    <label for="region">Region:</label>
                    <input type="text" name="region" id="region"
                           placeholder="Enter the region"
                           required>
                    <div class="form-button">
                        <button type="submit" class="buttons">Add Region</button>
                    </div>
                </form>
                </div>
    </div>

    <table class="table">
        <tr>
            <th>Region</th>
        </tr>

        <%
            List<String> waterRegion = (List<String>) request.getAttribute("waterRegion");
            if (waterRegion != null) {
                System.out.println("Contents of requestScope.electricityRegion in JSP: " + waterRegion);
            } else {
                System.out.println("requestScope.waterRegion is null in JSP");
            }
        %>

        <c:if test="${not empty requestScope.waterRegion}">
            <c:forEach items="${requestScope.waterRegion}" var="region">
                <tr>
                    <td style="cursor: pointer">${region}</td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${empty requestScope.waterRegion}">
            <tr>
                <td colspan="12"> No admins found </td>
            </tr>
        </c:if>

    </table>
    </div>
    </div>
</body>
</html>
