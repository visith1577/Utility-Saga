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
                    <h2 class="heading">Bill Calculation</h2>
                    <div class="billcalculation">
                        <div class="a_row">
                            <label for="Category">Select your Category</label>
                            <select name="Category" id="Category">
                                <option value="Domestic">Domestic</option>
                                <option value="Industrial">Industrial</option>
                                <option value="General">General</option>
                                <option value="Government">Government</option>
                                <option value="Hotel">Hotel</option>
                                <option value="Educational">Educational</option>
                                <option value="Religious">Religious</option>
                            </select>
                        </div>
                        <div class="Period">
                            <div class="a_row">
                                <label for="lastReadingDate">Last Reading Date:</label>
                                <input type="date" id="lastReadingDate" name="lastReadingDate">
                        
                                <label for="currentReadingDate">Current Reading Date:</label>
                                <input type="date" id="currentReadingDate" name="currentReadingDate">
                            </div>
                            <div class="a_row">
                                <label for="lastReading">Last Reading:</label>
                                <input type="number" id="lastReading" name="lastReading">
                        
                                <label for="currentReading">Current Reading:</label>
                                <input type="number" id="currentReading" name="currentReading">
                            </div>
                        </div>
                        <div class="calculation">
                            <div class="btn-a_row">
                                <button type="button" name="calculate">Calculate the amounts</button>
                                <button type="button" name="reset">Reset</button>
                            </div>
                            <div class="a_row">
                                <label for="result1">Bill Amount(Normal Tariff):</label>
                                <input type="number" id="result1" name="result" readonly>
                            </div>
                            <div class="a_row">
                                <label for="result2">Bill Amount(Variable Tariff):</label>
                                <input type="number" id="result2" name="result" readonly>
                            </div>

                            <div class="a_row">
                                <p>For more details on Tariffs,  <a href="">click here</a></p>
                            </div>
                                <label for="t_result">The most suitable tariff for you:</label>
                                <input type="text" id="t_result" name="t_result" readonly>
                        </div>
                        
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
                            <th>Power Consumption</th>
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
