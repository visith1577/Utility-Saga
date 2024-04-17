<%--
  Created by IntelliJ IDEA.
  User: Liviru Weerasinghe
  Date: 4/17/2024
  Time: 10:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/setting_profile.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/dashboard.css">
    <script type="module" src="<%= request.getContextPath() %>/public/JS/user_settings.js" defer></script>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/dashboard.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/forms.css">
    <script type="module" src="<%= request.getContextPath() %>/public/JS/user_settings.js" defer></script>
</head>

    <title>Settings- Main Admin</title>
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
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/main-admin/electricity-accounts">Regional Admins</a></li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/electricity/admin/settings.jsp">Settings</a></li>
                    <li class="menu-items-li"><a id="logout" href="<%= request.getContextPath() %>/logout">LogOut</a></li>
                </ul>
                <img src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt="Utility Saga" class="logo">
            </div>
        </header>
    </div>

    <section style="margin-top: 12.5vh">
        <h2>Account  Settings</h2>
        <form id="password-reset" method="post" action="<%= request.getContextPath() %>/electricity/admin/pwd-reset">
            <label for="password">Old Password:</label>
            <input type="password" id="password" name="password" placeholder="Enter new password" required>
            <div id="old-pwd" class="error"></div>
            <label for="new-password">New Password:</label>
            <input type="password" id="new-password" name="password" placeholder="Enter new password" required>
            <div id="new-pwd" class="error"></div>
            <label for="re-new-password">Confirm Password:</label>
            <input type="password" id="re-new-password" name="password" placeholder="Enter new password" required>
            <div id="re-pwd" class="error"></div>


            <div id="error-message" class="error"></div>
            <button class="content-btn" type="submit">Save Account Settings</button>
            <button class="content-btn" type="button">Edit Account Settings</button>
        </form>

    </section>
<body>

</body>
</div>
</html>
