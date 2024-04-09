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
    <title>Analysis - Electricity</title>
    <link rel="stylesheet" href="../../../CSS/dashboards/dashboard.css">
    <link rel="stylesheet" href="../../../CSS/ElectricityServices/electricityAnalysis.css">
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
                    <h2 class="heading">Bills</h2>
                    <canvas id="bills"></canvas>
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
                    <table class="top-selling-products1">
                        <tr>
                            <th>Device</th>
                            <th>Usage</th>
                            <th>Power Consumption </th>
                        </tr>
                        <tr>
                            <td>AC</td>
                            <td>25</td>
                            <td>3000W</td>
                        </tr>
                        <tr>
                            <td>Fan 1</td>
                            <td>5</td>
                            <td>250 W</td>
                        </tr>

                        <tr>
                            <td>Fan 2</td>
                            <td>10</td>
                            <td>250 W</td>
                        </tr>
                    </table>
                    <div class="iOTconnection">
                        <h2 class="heading">Manage IOT Device Connections</h2>
                        <div class="iOTconnection1">
                            <p>Above are the current devices, to connect new devices:</p>
                            <button type="button" name="goto-devicepage">Go to devices to connect</button>
                        </div>
                    </div>
                </div>
            </div>

            <div class="product-stats1 flex">
                <div class="product-sales stats-box">
                    <h2 class="heading">Budget</h2>
                </div>

                <div class="top-selling stats-box">
                    <table class="top-selling-products2" height="100%" width="100%">
                        <tr>
                            <th>Month</th>
                            <th>Expected Units</th>
                            <th>Expected Price</th>
                        </tr>
                        <tr>
                            <td>January</td>
                            <td>150</td>
                            <td>5000</td>
                        </tr>
                        <tr>
                            <td>February</td>
                            <td>140</td>
                            <td>4000</td>
                        </tr>
                        <tr>
                            <td>March</td>
                            <td>150</td>
                            <td>5000</td>
                        </tr>
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

</body>
</html>
