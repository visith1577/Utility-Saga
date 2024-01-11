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
    <link rel="stylesheet" href="../../CSS/dashboards/dashboard.css">
    <link rel="stylesheet" href="../../CSS/ElectricityServices/electricityAnalysis.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
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
                <li class="menu-items-li"><a href="#">About</a></li>
                <li class="menu-items-li"><a href="#">Contact Us</a></li>
                <li class="nxt-page water"><button class="button-17" type="button" onclick="toggle()">Electricity</button></li>
                <li class="nxt-page electricity"><button class="button-17" type="button" onclick="toggle()">Water</button></li>

                <li class="img_user dropdown">
                    <a href="">
                        <button>
                            <img alt="User" src="<%= request.getContextPath() %>/public/images/user.svg" style="width: 4vh; height: 4vh">
                        </button>
                        <div class="dropdown-content">
                            <a href="#">Link 1</a>
                            <a href="#">Link 2</a>
                            <a href="#">Link 3</a>
                        </div>
                    </a>
                </li>
            </ul>
            <img src="../../images/utility_saga.svg" alt="Utility Saga" class="logo">
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
                        <div class="earning-icon flex">
                            <ion-icon name="logo-paypal"></ion-icon>
                        </div>
                        <p>$9125.33</p>
                    </div>
                    <canvas id="fluct"></canvas>
                </div>
            </div>

            <!-- Product Stats -->
            <div class="product-stats flex">
                <div class="product-sales stats-box">
                    <h2 class="heading">Sales</h2>
                    <canvas id="iot"></canvas>
                </div>

                <div class="top-selling stats-box">
                    <h2 class="heading">Top Selling Product</h2>
                    <table class="top-selling-products" height="100%" width="100%">
                        <tr>
                            <th>ID</th>
                            <th>Product</th>
                            <th>Price</th>
                            <th>Sold</th>
                        </tr>

                        <tr>
                            <td>68231</td>
                            <td>
                                <div class="product-name flex">
                                    <div class="icon">
                                        <ion-icon name="logo-amazon"></ion-icon>
                                    </div>
                                    <p>Amazon Kindle 4th Gen</p>
                                </div>
                            </td>
                            <td>$1900</td>
                            <td>351</td>
                        </tr>

                        <tr>
                            <td>68231</td>
                            <td>
                                <div class="product-name iphone flex">
                                    <div class="icon">
                                        <ion-icon name="logo-apple"></ion-icon>
                                    </div>
                                    <p>Iphone 11 Pro</p>
                                </div>
                            </td>
                            <td>$1300</td>
                            <td>121</td>
                        </tr>

                        <tr>
                            <td>68231</td>
                            <td>
                                <div class="product-name windows flex">
                                    <div class="icon">
                                        <ion-icon name="logo-microsoft"></ion-icon>
                                    </div>
                                    <p>Windows 11</p>
                                </div>
                            </td>
                            <td>$180</td>
                            <td>195</td>
                        </tr>
                    </table>
                </div>
            </div>

            <div class="product-stats flex">
                <div class="product-sales stats-box">
                    <h2 class="heading">Sales</h2>
                    <canvas id="products"></canvas>
                </div>

                <div class="top-selling stats-box">
                    <h2 class="heading">Top Selling Product</h2>
                    <table class="top-selling-products" height="100%" width="100%">
                        <tr>
                            <th>ID</th>
                            <th>Product</th>
                            <th>Price</th>
                            <th>Sold</th>
                        </tr>

                        <tr>
                            <td>68231</td>
                            <td>
                                <div class="product-name flex">
                                    <div class="icon">
                                        <ion-icon name="logo-amazon"></ion-icon>
                                    </div>
                                    <p>Amazon Kindle 4th Gen</p>
                                </div>
                            </td>
                            <td>$1900</td>
                            <td>351</td>
                        </tr>

                        <tr>
                            <td>68231</td>
                            <td>
                                <div class="product-name iphone flex">
                                    <div class="icon">
                                        <ion-icon name="logo-apple"></ion-icon>
                                    </div>
                                    <p>Iphone 11 Pro</p>
                                </div>
                            </td>
                            <td>$1300</td>
                            <td>121</td>
                        </tr>

                        <tr>
                            <td>68231</td>
                            <td>
                                <div class="product-name windows flex">
                                    <div class="icon">
                                        <ion-icon name="logo-microsoft"></ion-icon>
                                    </div>
                                    <p>Windows 11</p>
                                </div>
                            </td>
                            <td>$180</td>
                            <td>195</td>
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

<script src="../../JS/electricityAnalysis.js"></script>

</body>
</html>
