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
                            <a href="<%= request.getContextPath() %>/public/HTML/user/user-settings"><c:out value="${'<b> Settings </b>'}" escapeXml="false"/></a>
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
            <section class="search component electricity">
                    <h2 class="select-heading">Account Number</h2>
                    <div class="select-box">
                        <div class="options-container">
                            <div class="option">
                                <input
                                        type="radio"
                                        class="radio"
                                        id="automobiles"
                                        name="category"
                                />
                                <label for="automobiles">Automobiles</label>
                            </div>

                            <div class="option">
                                <input type="radio" class="radio" id="film" name="category" />
                                <label for="film">oreoAcc12</label>
                            </div>

                            <div class="option">
                                <input type="radio" class="radio" id="science" name="category" />
                                <label for="science">oreoAcc12</label>
                            </div>

                            <div class="option">
                                <input type="radio" class="radio" id="art" name="category" />
                                <label for="art">oreoAcc12</label>
                            </div>

                            <div class="option">
                                <input type="radio" class="radio" id="music" name="category" />
                                <label for="music">oreoAcc12</label>
                            </div>

                            <div class="option">
                                <input type="radio" class="radio" id="travel" name="category" />
                                <label for="travel">oreoAcc12</label>
                            </div>

                            <div class="option">
                                <input type="radio" class="radio" id="sports" name="category" />
                                <label for="sports">oreoAcc12</label>
                            </div>

                            <div class="option">
                                <input type="radio" class="radio" id="news" name="category" />
                                <label for="news">oreoAcc12</label>
                            </div>

                            <div class="option">
                                <input type="radio" class="radio" id="tutorials" name="category" />
                                <label for="tutorials">Tutorials</label>
                            </div>
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

            <div class="stats flex">
                <div class="stats-box sales">
                    <h2 class="heading">Daily Consumptions</h2>
                    <canvas id="dailycons"></canvas>
                </div>

                <div class="stats-box earning">
                    <h2 class="heading">Fluctuation</h2>
                    <canvas id="monthcons"></canvas>
                </div>
            </div>

            <div class="stats bills">
                <div class="stats-box calc-box">
                    <h1 class="heading">Calender</h1>
                    <div class="calendar">
                        <header>
                            <h3 class="month"></h3>
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
                    <div class="stats-box budget">
                        <h2 class="heading">Budgeted Value Calculation</h2>
                        <div class="billcalculation">
                            <div class="a_row">
                                <label for="month">Select Month:</label>
                                <select name="month" id="month">
                                    <option value="January">January</option>
                                    <option value="February">February</option>
                                    <option value="March">March</option>
                                    <option value="April">April</option>
                                    <option value="May">May</option>
                                    <option value="June">June</option>
                                    <option value="July">July</option>
                                    <option value="August">August</option>
                                    <option value="September">September</option>
                                    <option value="October">October</option>
                                    <option value="November">November</option>
                                    <option value="December">December</option>
                                </select>
                            </div>
                            <div class="a_row">
                                <label for="expectedUnits">Expected Units:</label>
                                <input type="number" id="expectedUnits" name="expectedUnits">
                            </div>
                            <div class="a_row">
                                <label for="fixedTariff">Fixed Tariff Amount:</label>
                                <input type="number" id="fixedTariff" name="fixedTariff">
                            </div>
                            <div class="a_row">
                                <label for="variableTariff">Variable Tariff Amount:</label>
                                <input type="number" id="variableTariff" name="variableTariff">
                            </div>
                            <div class="btn-a_row">
                                <button type="button" name="add_row">Add to Table</button>
                            </div>
                        </div>
                    </div>
                    <table class="top-selling-products2">
                        <tr>
                            <th>Month</th>
                            <th>Expected Units</th>
                            <th>Expected Price(Fixed T.)</th>
                            <th>Expected Price(Variable T.)</th>

                        </tr>
                        <tr>
                            <td>January</td>
                            <td>150</td>
                            <td>5000</td>
                            <td>4750</td>
                        </tr>
                        <tr>
                            <td>February</td>
                            <td>140</td>
                            <td>4000</td>
                            <td>3800</td>
                        </tr>
                        <tr>
                            <td>March</td>
                            <td>150</td>
                            <td>5200</td>
                            <td>4850</td>
                        </tr>
                    </table>
                </div>
            </div>

            <div class="stats flex">
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
<script
        type="module"
        src="https://cdn.jsdelivr.net/npm/@ionic/core/dist/ionic/ionic.esm.js"
></script>
<script
        nomodule
        src="https://cdn.jsdelivr.net/npm/@ionic/core/dist/ionic/ionic.js"
></script>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script src="<%= request.getContextPath() %>/public/JS/electricityAnalysis.js"></script>
</body>
</html>
