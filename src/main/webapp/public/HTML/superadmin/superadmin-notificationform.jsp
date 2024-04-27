<%--
  Created by IntelliJ IDEA.
  User: NETHMI LIYANAGE
  Date: 4/16/2024
  Time: 6:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Document</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="../../CSS/superadmin/superadmin-notifelecwater.css" rel="stylesheet">
    <link rel="stylesheet" href="../../CSS/dashboards/dashboard.css">
    <link rel="stylesheet" href="../../CSS/forms.css">

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
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/super-admin/region">Electricity Regions</a></li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/super-admin/wregion">Water Regions</a></li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/superadmin/Superadmin-electricity-water.jsp">Electricity/Water</a></li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/superadmin/Superadmin-solar.jsp">Solar</a></li>
                    <li class="menu-items-li"><a id="logout" href="<%= request.getContextPath() %>/logout">LogOut</a></li>
                </ul>
                <img src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt="Utility Saga" class="logo">
            </div>
        </header>
    </div>
    <div class="fcontainer">
        <h2>Notification Handling | To Main admins</h2>
        <div class="form-box">
            <form id="addnotif" method="POST" action="${pageContext.request.contextPath}/elecNotif">
                <%--@declare id="type"--%>
                <label for="title">Notification Title:</label>
                <input type="text" name="title" id="title" required>
                <label for="type">Service Type:</label>
                <select id="type-dropdown" name="type">
                    <option value="WATER" name="WATER">WATER</option>
                    <option value="ELECTRICITY" name="ELECTRICITY">ELECTRICITY</option>
                    <option value="SOLAR" name="SOLAR">SOLAR</option>
                </select>
                <label for="subject">Subject</label>
                <input type="text" name="subject" id="subject" required>
                <label for="message">Message</label>
                <textarea name="message" id="message" required></textarea>
                <div class="btn-container">
                    <button class="btn" id="send">Send</button>
                    <button class="btn" id="cancel">Cancel</button>
                    <button class="btn" id="reset">Reset</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
