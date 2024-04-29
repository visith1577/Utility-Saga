<%--
  Created by IntelliJ IDEA.
  User: Liviru Weerasinghe
  Date: 4/28/2024
  Time: 11:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%
    String contextPath = request.getContextPath();
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Water Regional admin Due Payments</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-jicI5S7PZg7NtOWKp6hv3zokYkaw9fdL3+M5uHyXr+1XNMe5W4/zJ3uiz5zgI5Fp9Pwe5VXvBsYHpma/8ZkC9w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="<%= request.getContextPath() %>/public/CSS/popup.css" rel="stylesheet">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/navbar.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/dashboard.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/forms.css">
    <link href="<%= request.getContextPath() %>/public/CSS/dashboards/Admin/admintable.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/public/CSS/dashboards/Admin/admincards.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/public/CSS/dashboards/Admin/regionaladmin.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="<%= request.getContextPath() %>/public/JS/dashboard.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="<%= request.getContextPath() %>/public/JS/ElectricityAdminDashboard.js"></script>
    <script>
        let contextPath = '<%= contextPath %>';
        window.onscroll = function () {
            scrollFunction()
        }

        function scrollFunction() {
            if (document.body.scrollTop > 70 || document.documentElement.scrollTop > 70) {
                document.getElementById("sidebar").style.height = "80%";
            } else {
                document.getElementById("sidebar").style.height = "85%";
            }
        }
    </script>
    <style>
        .search-option{
            justify-content: space-between;
        }

    </style>

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
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/water/regional-admin/user-accounts">Customers</a></li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/water/regional-admin/complaints">Complaints</a></li>
                    <li class="menu-items-li dropdown">
                        <a href="#" class="payment-dropdown">Payment</a>
                        <div class="dropdown-content">
                            <a href="<%= request.getContextPath() %>/public/HTML/water/regionalAdmin/electricity-paymenthandle.jsp">Payment</a>
                            <a href="<%= request.getContextPath() %>/water/regional-admin/due-payment">Due Payment</a>
                        </div>
                    </li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/water/regional-admin/connections">New Connection</a></li>
                    <li class="menu-items-li dropdown">
                        <a href="#" class="profile-icon"><i class="fa-solid fa-circle-user"></i></a>
                        <div class="dropdown-content">
                            <a href="<%= request.getContextPath() %>/public/HTML/water/regionalAdmin/settings.jsp">Settings</a>
                            <a href="<%= request.getContextPath() %>/logout">LogOut</a>
                        </div>
                    </li>
                </ul>
                <img src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt="Utility Saga" class="logo">
            </div>
        </header>
    </div>
</header>

<div class="search-option">
    <div class="bar-search-button">
        <div class="searchbar" style="margin-top: 12.5vh">
            <form id="searchForm" method="get" action="<%= request.getContextPath() %>/water/regional-admin/due-payment">
                <label for="nic"></label>
                <input name="id" type="text" id="nic" class="Comsearch-bar" placeholder="Enter Account Number or Bill ID" style="margin-left: 20px">

                <button type="submit" name="search" class="btn">Search</button>
                <button type="button" id="resetButton" class="btn">Reset</button>
            </form>
        </div>
        <script>
            document.getElementById('resetButton').addEventListener('click', function() {
                document.getElementById('nic').value = '';
                document.getElementById('searchForm').submit();
            });
        </script>
    </div>
</div>

<div class="complaints">
    <div class="tablediv">
        <div class="customerdetails">
            <div class="titlebar">
                <h2>Complaint Details</h2>
                <a href="#" class="btn">View All</a>
            </div>

            <div id="results"></div>

            <div class="table-container">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Account Number</th>
                        <th>Bill ID</th>
                        <th>Amount</th>
                        <th>Billed Date</th>
                        <th>Due Date</th>
                        <th>Status</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:if test="${empty requestScope.waterOverdueBills}">
                        <tr>
                            <td colspan="12">No complaints found</td>
                        </tr>
                    </c:if>
                    <c:if test="${not empty requestScope.waterOverdueBills}">
                        <c:forEach items="${requestScope.waterOverdueBills}" var="bill">

                            <tr>
                                <td>${bill.accountNumber}</td>
                                <td>${bill.billId}</td>
                                <td>${bill.amount}</td>
                                <td>${bill.billedDate}</td>
                                <td>${bill.dueDate}</td>
                                <td>${bill.status}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</body>
</html>
