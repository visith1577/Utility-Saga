<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 1/10/2024
  Time: 9:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>IoT - Electricity</title>
    <link rel="stylesheet" href="../../../CSS/dashboards/dashboard.css">
    <link rel="stylesheet" href="../../../CSS/ElectricityServices/iot.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
    <link rel="stylesheet" href="../../../CSS/forms.css">
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
                <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/pages/aboutUs.jsp">About</a></li>
                <li class="menu-items-li"><a href="#">Contact Us</a></li>
                <li class="nxt-page water"><button class="button-17" type="button" onclick="toggle()">Dashboards</button></li>
                <script>
                    function toggle() {
                        window.location.href = "userDashboardElectricity.jsp"
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

<div class="dashboard">
    <div class="content">
        <div class="main-content">
            <div class="stats flex">
                <div class="stats-box sales">
                    <h2 class="heading">Add Devices</h2>
                    <form action="" id="iotform">
                        <label for="serialNo">SerialNo</label>
                        <input type="text" id="serialNo" name="serialNo" required><br>
                        <label for="name">Name</label>
                        <input type="text" id="name" name="name" required><br>
                        <label for="pConsumption">Power Consumption</label>
                        <input type="text" id="pConsumption" name="pConsumption" required><br>
                        <button type="submit">Add Device</button>
                    </form>
                </div>

                <div class="stats-box earning">
                    <h2 class="heading">Fluctuation</h2>
                    <div class="earning-amount flex-c">
                        <p>$9125.33</p>
                    </div>
                    <canvas id="fluct"></canvas>
                </div>
            </div>

            <div class="product-stats flex">
                <div class="product-sales stats-box">
                    <h2 class="heading">IOT Devices</h2>
                    <canvas id="iot"></canvas>
                </div>

                <div class="top-selling stats-box1">
                    <h2 class="heading">Usages of iOT Devices</h2>
                    <table class="iotdevices" id="iottable">
                        <thead>
                        <tr>
                            <th>Serial No</th>
                            <th>Name</th>
                            <th>Power Consumption(W)</th>
                            <th>Status</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <label class="switch">
                                    <input type="checkbox" id="statusToggle">
                                    <span class="slider round"></span>
                                </label>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>
</div>
<script
        type="module"
        src="https://cdn.jsdelivr.net/npm/@ionic/core/dist/ionic/ionic.esm.js"
></script>
<script
        nomodule
        src="https://cdn.jsdelivr.net/npm/@ionic/core/dist/ionic/ionic.js"
></script>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script src="../../../JS/electricityAnalysis.js"></script>
<script src="../../../JS/iot.js"></script>

</body>
</html>
