<%--
  Created by IntelliJ IDEA.
  User: Liviru Weerasinghe
  Date: 4/13/2024
  Time: 2:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Electricity Regional admin dashboard</title>
    <link href="<%= request.getContextPath() %>/public/CSS/dashboards/Admin/regionalAdminElectricity.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/public/CSS/popup.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css">
    <link rel="stylesheet" href="../../../CSS/dashboards/dashboard.css">
    <link rel="stylesheet" href="../../../CSS/forms.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

</head>
<body>
<header>
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
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/electricity/admin/AdminDashboard-electricity.jsp">Customers</a></li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/electricity/regionalAdmin/complaints-electricity.jsp">Complaints</a></li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/electricity/regionalAdmin/electricity-payment.jsp">Payment</a></li>
                    <li class="menu-items-li"><a href="#">Logout</a></li>
                </ul>
                <img src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt="Utility Saga" class="logo">
            </div>
        </header>
    </div>
</header>
<div class="topbar" style="margin-top: 12.5vh">
    <div class="lefttop">
        <h2>Hello (Name)</h2>
    </div>
    <div class="righttop">
        <ul>
            <li>Region:Biyagama</li>
            <li>Areas:Dompe, Biyagama, Malwana</li>
        </ul>
    </div>
</div>
<section class="home">
    <!-- Cards -->
    <div class="cardBox">

        <a class="card" href="#">
            <div class="infor">
                <div class="numbers">15 000</div>
                <div class="cardName">Water Service Users</div>
            </div>
            <div class="iconBx">
                <i class="fa-solid fa-users"></i>
            </div>
        </a>

        <a class="card" href="#">
            <div class="infor">
                <div class="numbers">5 000</div>
                <div class="cardName">New Water Connections</div>
            </div>
            <div class="iconBx">
                <i class="fa-solid fa-faucet"></i>
            </div>
        </a>

        <a class="card" href="#">
            <div class="infor">
                <div class="numbers">1 000</div>
                <div class="cardName">No. of Complaints</div>
            </div>
            <div class="iconBx">
                <i class="fa-solid fa-user-group"></i>
            </div>
        </a>

        <a class="card" href="#">
            <div class="infor">
                <div class="numbers">15 000</div>
                <div class="cardName">Water Service Users</div>
            </div>
            <div class="iconBx">
                <i class="fa-solid fa-user-group"></i>
            </div>
        </a>
    </div>

    <!-- Users/Connections -->
    <div class="details">
        <!-- Table 1 -->
        <div class="tablediv">
            <div class="customerdetails">
                <div class="titlebar">
                    <h2>Customer Details</h2>
                    <!-- view all button -->
                    <a href="#" class="btn">View All</a>
                </div>

                <!-- table body -->
                <div class="table-container">
                    <table class="table">
                        <!-- table header -->
                        <thead>
                        <tr>
                            <th>Customer ID</th>
                            <th>Customer Name</th>
                            <th>Address</th>
                            <th>Telephone</th>
                            <th>Connection Type</th>
                            <th>Connection Status</th>
                        </tr>
                        </thead>
                        <!-- table body -->
                        <tbody>
                        <tr onclick="openPopup()">
                            <td>1</td>
                            <td>John Doe</td>
                            <td>Colombo</td>
                            <td>0771234567</td>
                            <td>Domestic</td>
                            <td>Active</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>Jane Doe</td>
                            <td>Kandy</td>
                            <td>0771234567</td>
                            <td>Domestic</td>
                            <td>Active</td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>John Doe</td>
                            <td>Colombo</td>
                            <td>0771234567</td>
                            <td>Domestic</td>
                            <td>Active</td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td>Jane Doe</td>
                            <td>Kandy</td>
                            <td>0771234567</td>
                            <td>Domestic</td>
                            <td>Active</td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td>John Doe</td>
                            <td>Colombo</td>
                            <td>0771234567</td>
                            <td>Domestic</td>
                            <td>Active</td>
                        </tr>
                        <tr>
                            <td>6</td>
                            <td>Jane Doe</td>
                            <td>Kandy</td>
                            <td>0771234567</td>
                            <td>Domestic</td>
                            <td>Active</td>
                        </tr>
                        </tbody>
                    </table>
                </div>

            </div>
        </div>
        <!-- right div -->
        <div class="connection-analyse">
            <h2>New Users Analyse</h2>
            <canvas id="lineChart"></canvas>
            <div class="details-chart">
                <ul style="list-style-type: circle;">
                    <li>    All Users are in K (multiply by 1000) form</li>
                    <li>    Domestic Users are in K (multiply by 1000) form</li>
                    <li>    Commercial Users are in K (multiply by 1000) form</li>
                    <li>    Industrial Users are in K (multiply by 1000) form</li>
                </ul>
            </div>

        </div>
    </div>

    <!-- Complaints -->
    <div class="complaints">
        <div class="tablediv">
            <div class="customerdetails">
                <div class="titlebar">
                    <h2>Complaint Details</h2>
                    <!-- view all button -->
                    <a href="#" class="btn">View All</a>
                </div>
                <!-- table body -->
                <div class="table-container">
                    <table class="table">
                        <!-- table header -->
                        <thead>
                        <tr>
                            <th>Complaint_Number</th>
                            <th>Cateogry</th>
                            <th>Complaint_type</th>
                            <th>Customer Name</th>
                            <th>Account Number</th>
                            <th>Telephone</th>
                            <th>Complaint Status</th>
                            <th>More</th>
                        </tr>
                        </thead>
                        <!-- table body -->
                        <tbody>
                        <tr>
                            <td>1</td>
                            <td>Operational</td>
                            <td>Main Leak</td>
                            <td>K.P.Perera</td>
                            <td>21400564</td>
                            <td>0771234567</td>
                            <td>Active</td>
                            <td><input type="button" class="tablebtn" value="View More"></td>
                        </tr>
                        <tr>
                            <td>1</td>
                            <td>Operational</td>
                            <td>Main Leak</td>
                            <td>K.P.Perera</td>
                            <td>21400564</td>
                            <td>0771234567</td>
                            <td>Active</td>
                            <td><input type="button" class="tablebtn" value="View More"></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>

<!-- popup -->
<div class="popupcontainer" id="popupcontainer">
    <div class="popup" id="popup">
        <span class="close" onclick="closePopup()"><i class="fa-solid fa-xmark"></i></span>
        <h2>User Details</h2>
        <div class="popupdetails">
            <div class="detail">
                <h2 class="topic">Customer ID : </h2>
                <h2>1</h2>
            </div>
            <div class="detail">
                <h2 class="topic">Customer Name: </h2>
                <h2>John Doe</h2>
            </div>
            <div class="detail">
                <h2 class="topic">Address : </h2>
                <h2>Colombo</h2>
            </div>
            <div class="detail">
                <h2 class="topic">Telephone : </h2>
                <h2>0771234567</h2>
            </div>
            <div class="detail">
                <h2 class="topic">Connection Type : </h2>
                <h2>Domestic</h2>
            </div>
            <div class="detail">
                <h2 class="topic">Connection Status : </h2>
                <h2>Active</h2>
            </div>
        </div>

        <div class="btns">
            <button type="button">Edit</button>
            <button class="button1">Delete the user</button>
        </div>
    </div>
</div>

<!-- <script src="..JS/admindashboard.js"></script> -->
<script src="<%= request.getContextPath() %>/public/JS/ElectricityAdminDashboard.js"></script>
</body>
</html>
