<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/public/CSS/dashboards/dashboard.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/navbar.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/public/CSS/ElectricityServices/electricityAnalysis.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
    <link rel="stylesheet" href=".<%=request.getContextPath()%>/public/CSS/forms.css">
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    <style>
        .item {
            display: none;
        }
    </style>
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
                <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/user/electricity/electricity-contact.jsp">Contact Us</a></li>
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
                            <img alt="User" src="<%= request.getContextPath() %>/public/images/user.svg" style="width: 4vh; height: 4vh">
                            <%
                            } else {
                            %>
                            <img class="image-profile" src="data:image/jpeg;base64,<%= image %>" alt="image" style="width: 5vh; height: 5vh">
                            <%
                                }
                            %>
                        </button>
                        <div class="dropdown-content">
                            <a href="<%= request.getContextPath() %>/user/user-settings"><c:out value="${'<b> Settings </b>'}" escapeXml="false"/></a>
                            <a href="<%= request.getContextPath() %>/user/billpayment"><c:out value="${'<b> Payments </b>'}" escapeXml="false"/></a>
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
            <section class="search component electricity">
                    <h2 class="select-heading">Account Number</h2>
                    <div class="select-box">
                        <div class="options-container">
                            <c:forEach var="entry" items="${requestScope.electricity_account_list}">
                                <div class="option">
                                    <input
                                            type="radio"
                                            class="radio"
                                            id="${entry.key}"
                                            name="category"
                                    />
                                    <label for="${entry.key}" class="${entry.value}">${entry.key}</label>
                                </div>
                            </c:forEach>
                        </div>

                        <div class="selected">
                            Select Account Number
                        </div>
                        <div class="search-box">
                            <label>
                                <input type="text" placeholder="search account">
                            </label>
                        </div>
                    </div>
            </section>
                <div id="navigation" class="navigation item">
                    <div id="toggle" class="toggle-p">
                        <ul>
                            <li>
                                <a href="#">
                                    <span class="icon"><i class="fa fa-calculator" aria-hidden="true"></i></span>
                                    <span id="bill-val" class="title-nav visible">Current bill: </span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="icon"><i class="fa fa-lightbulb" aria-hidden="true"></i></span>
                                    <span id="kwh-val" class="title-nav visible">Kwh consumed: </span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="icon"><i class="fa fa-link" aria-hidden="true"></i></span>
                                    <span class="title-nav visible">Device Status: Active</span>
                                </a>
                            </li>
                            <li>
                                <a href="#">
                                    <span class="icon"><i class="fa fa-receipt" aria-hidden="true"></i></span>
                                    <span id="reading-val" class="title-nav visible">last Reading: </span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <script>
                    const navigation = document.getElementById('navigation');
                    document.getElementById('toggle').ondblclick = function () {
                        this.classList.toggle('active');
                        navigation.classList.toggle('active');
                    }
                </script>
                <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
                <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
                <script>
                    $( function() {
                        $( "#navigation" ).draggable();
                    } );
                </script>
                <div class="stats flex item">
                    <div class="stats-box sales">
                        <h2 class="heading">Daily Consumptions</h2>
                        <canvas id="dailycons"></canvas>
                    </div>

                    <div class="stats-box earning">
                        <h2 class="heading">Fluctuation</h2>
                        <canvas id="monthcons"></canvas>
                    </div>
                </div>

                <div class="stats bills item">
                    <div class="stats-box calc-box">
                        <h1 class="heading">Calender</h1>
                        <div class="calendar">
                        <header>
                            <h3 class="month"  style="color: #8c7400"></h3>
                            <nav>
                                <button id="prev"></button>
                                <button id="next"></button>
                            </nav>
                        </header>
                        <section>
                            <ul class="days">
                                <li>Sun</li>
                                <li>Mon</li>
                                <li>Tue</li>
                                <li>Wed</li>
                                <li>Thu</li>
                                <li>Fri</li>
                                <li>Sat</li>
                            </ul>
                            <ul class="dates"></ul>
                        </section>
                        </div>
                    </div>
            

                    <div class="top-selling stats-box">
                        <form action="<%=request.getContextPath()%>/user/electricity-analytics" method="post" class="stats-box budget">
                            <h2 class="heading">Budgeted Value Calculation</h2>
                            <div class="billcalculation">
                                <div class="a_row">
                                    <label for="month">Select Month:</label>
                                    <input id="month" type="text" name="month" readonly required>
                                </div>
                                <div class="a_row">
                                    <label for="expectedUnits">Set Budget :</label>
                                    <input type="text" id="expectedUnits" name="expectedUnits" pattern="^[0-9]*$" required>
                                </div>
                                <div class="btn-a_row">
                                    <button type="submit" name="add_row">Add to Table</button>
                                </div>
                            </div>
                        </form>
                        <script>
                            document.getElementById('expectedUnits').addEventListener('keypress', function (e) {
                                    if (e.key < '0' || e.key > '9') {
                                        e.preventDefault();
                                    }
                            });
                        </script>
                        <table id="budget-table" class="top-selling-products2">
                            <tr>
                                <th>Month</th>
                                <th>Budget</th>
                                <th>Date Added</th>
                            </tr>
                        </table>
                    </div>
                </div>

                <div class="stats flex item">
                    <div class="stats-box bud_act">
                        <h2 class="heading">Budgeted Vs. Actual Consumption</h2>
                        <canvas id="bud_act"></canvas>
                    </div>

                    <div class="stats-box sales">
                        <h2 class="heading">Bill amount - Monthly</h2>
                        <canvas id="bills"></canvas>
                        </div>
                    </div>
                </div>
        </div>
    </div>
<div style="height: 80px; width: 100%">

</div>
<script>
    function getCurrentMonth() {
        const date = new Date();
        return date.getMonth() + 1;
    }

    function getCurrentMonthName() {
        const date = new Date();
        const monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
        return monthNames[date.getMonth()];
    }

    window.onload = function() {
        const monthInput = document.getElementById('month');
        monthInput.value = getCurrentMonthName();
    }
</script>
<script
        type="module"
        src="https://cdn.jsdelivr.net/npm/@ionic/core/dist/ionic/ionic.esm.js"
></script>
<script
        nomodule
        src="https://cdn.jsdelivr.net/npm/@ionic/core/dist/ionic/ionic.js"
></script>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
    let contextPath = '<%=request.getContextPath()%>';
</script>
<script src="<%= request.getContextPath() %>/public/JS/electricityAnalysis.js"></script>
</body>
</html>
