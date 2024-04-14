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
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/electricity/regionalAdmin/AdminDashboard-electricity.jsp">Customers</a></li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/electricity/regionalAdmin/complaints-electricity.jsp">Complaints</a></li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/electricity/regionalAdmin/electricity-payment.jsp">Payment</a></li>
                    <li class="menu-items-li"><a id="logout" href="<%= request.getContextPath() %>/logout">LogOut</a></li>
                </ul>
                <img src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt="Utility Saga" class="logo">
            </div>
        </header>
    </div>
</header>

    <div class="complaints">
        <div class="tablediv">
            <div class="customerdetails">
                <div class="titlebar">
                    <h2>Complaint Details</h2>
                    <a href="#" class="btn">View All</a>
                </div>
                <div class="table-container">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Complaint_Number</th>
                            <th>Category</th>
                            <th>Complaint_type</th>
                            <th>NIC</th>
                            <th>Account Number</th>
                            <th>Mobile</th>
                            <th>Complaint Status</th>
                            <th>Description</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>

                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>

<script src="<%= request.getContextPath() %>/public/JS/ElectricityAdminDashboard.js"></script>
</body>
</html>
