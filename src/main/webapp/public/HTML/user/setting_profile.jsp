<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<% String name = (String) session.getAttribute("UNAME"); %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/setting_profile.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/dashboard.css">
    <script type="module" src="<%= request.getContextPath() %>/public/JS/user_profile.js" defer></script>
    <title>Account Setting</title>
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
                <li id="prof-id" class="menu-items-li"><a href="#"><%= "Welcome " + name%></a></li>
                <li class="menu-items-li"><a href="#">Home</a></li>
                <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/pages/aboutUs.jsp">About Us</a></li>
                <li class="nxt-page water"><button class="button-17" type="button" onclick="toggle()">Dashboards</button></li>
                <script>
                    const electricity = "<%=session.getAttribute("electricity") != null%>"
                    const water = "<%=session.getAttribute("water") != null%>"
                    function toggle() {
                        if (electricity === 'true'){
                            window.location.href = "<%= request.getContextPath() %>/public/HTML/user/electricity/userDashboardElectricity.jsp";
                        } else if(water === 'true') {
                            window.location.href = "<%= request.getContextPath() %>/public/HTML/user/water/userDashboardWater.jsp";
                        }
                    }
                </script>
                <li class="img_user dropdown">
                    <a href="<%= request.getContextPath() %>/public/HTML/user/user.jsp">
                        <button class="user-profile">
                            <img alt="User" src="<%= request.getContextPath() %>/public/images/user.svg" style="width: 4vh; height: 4vh">
                        </button>
                        <div class="dropdown-content">
                            <a href="<%= request.getContextPath() %>/public/HTML/user/setting_profile.jsp"><c:out value="${'<b> Settings </b>'}" escapeXml="false"/></a>
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
<div class="profile-container">
        <form method="post" class="edit-profile">
            <section>
                <h2>User Setting</h2>

                <label for="Name">Name:</label>
                <input id="Name" type="text" value="<%= name %>">

                <label for="email">Email:</label>
                <input id="email" type="text">

                <label for="phone">Phone Number:</label>
                <input id="phone" type="text">


                <button class="content-btn" type="submit" >Save Profile</button>
            </section>
        </form>

        <section>
            <h2>Account  Settings</h2>
            <form>
                <label for="password">Old Password:</label>
                <input type="password" id="password" name="password" placeholder="Enter new password">
                <label for="new-password">New Password:</label>
                <input type="password" id="new-password" name="password" placeholder="Enter new password">
                <label for="language">Preferred Language:</label>
                <select id="language" name="language">
                    <option value="english">English</option>
                    <option value="sinhala">Sinhala</option>
                    <option value="tamil">Tamil</option>
                </select>

                <button class="content-btn" type="submit">Save Account Settings</button>
                <button class="content-btn" type="button">Edit Account Settings</button>
            </form>

        </section>

        <section>
            <h2>Appearance Settings</h2>

            <label for="theme">Theme:</label>
            <select id="theme" name="theme">
                <option value="light">Light</option>
                <option value="dark">Dark</option>
            </select>

            <label for="fontSize">Font Size:</label>
            <select id="fontSize" name="fontSize">
                <option value="small">Small</option>
                <option value="medium">Medium</option>
                <option value="large">Large</option>
            </select>

            <button class="content-btn" type="button">Save Appearance Settings</button>
        </section>

        <section>
            <h2>Service Settings</h2>
            <h3>Electricity Settings</h3>

            <form>
                <label for="account-details__electricity">Your Current Electricity Accounts:</label>
                <ul id="account-details__electricity">
                    <c:forEach items="${requestScope.electricity_account_list}" var="account">
                        <li>${account}</li>
                    </c:forEach>
                </ul>

                <button class="content-btn Edit" type="button">Edit Accounts</button>
                <button type="button" class="content-btn Delete">Delete Accounts</button>
                <button type="button" class="content-btn Add">Add Accounts</button>


                <label for="electricityNotifications">Receive Notifications:</label>
                <select id="electricityNotifications" name="electricityNotifications">
                    <option value="yes">Yes</option>
                    <option value="no">No</option>
                </select>
                <label for="electricityE-Notifications">Email Notifications:</label>
                <select id="electricityE-Notifications" name="electricityE-Notifications">
                    <option value="yes">Enable</option>
                    <option value="no">Disable</option>
                </select>

                <label for="electricityThreshold">Usage Threshold (kWh):</label>
                <input class="content-btn" type="number" id="electricityThreshold" name="electricityThreshold" placeholder="Set usage threshold" min="0" inputmode="numeric">

                <button class="content-btn" type="button">Save Electricity Settings</button>
            </form>
        </section>

        <section>
            <h3>Water Settings</h3>
            <form>
                <label for="account-details__water">Your Current Water Accounts:</label>
                <ul id="account-details__water">
                    <c:forEach items="${requestScope.water_account_list}" var="account">
                        <li>${account}</li>
                    </c:forEach>
                </ul>
                <button id="edit-btn_water" type="button" class="content-btn Edit">Edit Accounts</button>
                <button id="del-btn_water" type="button" class="content-btn Delete">Delete Accounts</button>
                <button id="add-btn_water" type="button" class="content-btn Add">Add Accounts</button>


                <label for="waterNotifications">Receive Notifications:</label>
                <select id="waterNotifications" name="waterNotifications">
                    <option value="yes">Yes</option>
                    <option value="no">No</option>
                </select>
                <label for="waterE-Notifications">Email Notifications:</label>
                <select id="waterE-Notifications" name="waterE-Notifications">
                    <option value="yes">Enable</option>
                    <option value="no">Disable</option>
                </select>

                <label for="waterThreshold">Usage Threshold (liters):</label>
                <input class="content-btn" type="number" id="waterThreshold" name="waterThreshold" placeholder="Set usage threshold" min="0" inputmode="numeric">
                <button class="content-btn" type="button">Save Water Settings</button>
            </form>
        </section>
    </div>
</body>
<script>
    fetch("<%= request.getContextPath() %>" + "/user-profile", {
        method: 'GET'
    }).then(response => response.json())
        .then(userDetails => {
            console.log(userDetails);
            const email = document.getElementById("email");
            const tel = document.getElementById("phone");

            email.value = userDetails.email;
            tel.value = userDetails.mobile;
        })
        .catch(error => console.error('Error fetching data:', error));
</script>
</html>