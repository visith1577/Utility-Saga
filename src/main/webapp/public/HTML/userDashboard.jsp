<%--
  Created by IntelliJ IDEA.
  User: visithkumarapperuma
  Date: 2023-10-31
  Time: 17:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Document</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="<%= request.getContextPath() %>/public/CSS/login/user/userdashboard.css" rel="stylesheet">
</head>

<body>
<div class="wrapper">

    <div class="top_navbar">
        <div class="top_menu">
            <div class="logo">
                <img alt="Utility Saga" height="50px" src="<%= request.getContextPath() %>/public/images/utility_saga.svg">
            </div>
            <ul>
                <li><a href="#">
                    <img alt="Cart" src="<%= request.getContextPath() %>/public/images/cart.svg" class="item1">
                </a></li>
                <li><a href="#">
                    <img alt="Notification" src="<%= request.getContextPath() %>/public/images/notification-line.svg" class="item2">
                </a></li>
                <li><a href="#">
                    <img alt="User" src="<%= request.getContextPath() %>/public/images/user.svg" class="item3">
                </a></li>
            </ul>
        </div>
    </div>
    <div class="content-middle">
        <div class="sidebar" id="sidebar">
            <h3>Select the Service</h3><br><br>
            <ul class="b-list">
                <li class="b-item1"><a href="electricityDashboard.jsp">
                    <div>Electricity Service</div>
                </a></li>
                <li class="b-item2"><a href="./dashboard/water.html">
                    <div>Water Service</div>
                </a></li>
                <li class="b-item3"><a href="">
                    <div>Fuel Service</div>
                </a></li>
                <li class="b-item4"><a href="">
                    <div>Solar Panel Store</div>
                </a></li>
                <li class="b-item5">
                    <div class="dropdown">
                        <button class="dropbtn">New Connection</button>
                        <div class="dropdown-content">
                            <a href="./electricity-newconnection.html">Electricity Connection</a>
                            <a href="#">Water Connection</a>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
        <div class="user-details">
            <img src="<%= request.getContextPath() %>/public/images/userImage.jpg" alt="User">
            <ul>
                <li><b>Name: </b></li>
                <li><b>Email:</b></li>
                <li><b>Subscribed Services:</b></li>
                <li><b>Address:</b></li><br><br>
            </ul>
            <div class="bills">
                <div class="bal"><b>Electricity Account Balance:</b><br> Rs. 1500.00</div>
                <div class="bal"><b>Account Balance:</b><br> Rs. 1500.00</div>
            </div>
        </div>
    </div>
</div>

</body>
<script src="<%= request.getContextPath() %>/public/JS/dashboard.js"></script>

</html>
