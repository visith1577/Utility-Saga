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
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/main-admin/water-accounts">Regional Admins</a></li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/water/admin/settings.jsp">Settings</a></li>
                    <li class="menu-items-li"><a id="logout" href="<%= request.getContextPath() %>/logout">LogOut</a></li>
                </ul>
                <img src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt="Utility Saga" class="logo">
            </div>
        </header>
    </div>

    <section style="margin-top: 12.5vh ; margin-left: 2vh">
        <h2>Account  Settings</h2>

        <form id="admin-details-form" method="post" action="<%= request.getContextPath() %>/water/main-admin/update-details">
            <label for="empid">Employee ID:</label>
            <input type="text" id="empid" name="empid" value="${sessionScope.EMPID}" disabled>
            <div class="error" style="margin-top: 2vh"></div>
            <label for="uname">Username:</label>
            <input type="text" id="uname" name="uname" value="${sessionScope.USERNAME}" disabled>
            <div class="error" style="margin-top: 2vh"></div>
            <label for="firstname">First Name:</label>
            <input type="text" id="firstname" name="firstname" value="${sessionScope.FNAME}" disabled>
            <div class="error" style="margin-top: 2vh"></div>
            <label for="lastname">Last Name:</label>
            <input type="text" id="lastname" name="lastname" value="${sessionScope.LNAME}" disabled>
            <div class="error" style="margin-top: 2vh"></div>
            <label for="mobile">Mobile:</label>
            <input type="text" id="mobile" name="mobile" value="${sessionScope.MOBILE}" disabled>
            <div class="error" style="margin-top: 2vh"></div>

            <div id="error-message-details" class="error"></div>
            <div id="buttons">
                <button class="content-btn" id="edit-btn">Edit</button>
                <button class="content-btn" type="submit" id="submit-btn" style="display: none;">Save</button>
                <button class="content-btn" id="cancel-btn" style="display: none;">Cancel</button>
            </div>
        </form>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                const editBtn = document.getElementById("edit-btn");
                const submitBtn = document.getElementById("submit-btn");
                const cancelBtn = document.getElementById("cancel-btn");
                const formFields = document.querySelectorAll("form input[type='text']");

                function toggleFormFields(state) {
                    formFields.forEach(field => {
                        field.disabled = !state;
                    });
                }

                editBtn.addEventListener("click", function (event) {
                    event.preventDefault();
                    toggleFormFields(true);
                    editBtn.style.display = "none";
                    submitBtn.style.display = "block";
                    cancelBtn.style.display = "block";
                });

                cancelBtn.addEventListener("click", function (event) {
                    event.preventDefault();
                    toggleFormFields(false);
                    editBtn.style.display = "block";
                    submitBtn.style.display = "none";
                    cancelBtn.style.display = "none";
                });
            });
        </script>


        <form id="password-reset" method="post" action="<%= request.getContextPath() %>/water/main-admin/pwd-reset">
            <label for="password">Old Password:</label>
            <input type="password" id="password" name="password" placeholder="Enter new password" required>
            <div id="old-pwd" class="error"></div>
            <label for="new-password">New Password:</label>
            <input type="password" id="new-password" name="new-password" placeholder="Enter new password" required>
            <div id="new-pwd" class="error"></div>
            <label for="re-new-password">Confirm Password:</label>
            <input type="password" id="re-new-password" name="re-new-password" placeholder="Enter new password" required>
            <div id="re-pwd" class="error"></div>


            <div id="error-message" class="error"></div>
            <button class="content-btn" type="submit">Save Account Settings</button>
        </form>

    </section>
<body>

</body>
</div>
</html>
