<%--
  Created by IntelliJ IDEA.
  User: NETHMI LIYANAGE
  Date: 4/15/2024
  Time: 10:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Electricity Regional admin payment handling</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-jicI5S7PZg7NtOWKp6hv3zokYkaw9fdL3+M5uHyXr+1XNMe5W4/zJ3uiz5zgI5Fp9Pwe5VXvBsYHpma/8ZkC9w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="<%= request.getContextPath() %>/public/CSS/dashboards/Admin/regionaladminElecpayment.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/public/CSS/dashboards/Admin/regionalAdminElectricity.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/public/CSS/popup.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css">
    <link rel="stylesheet" href="../../../CSS/dashboards/dashboard.css">
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
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/electricity/regional-admin/user-accounts">Customers</a></li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/electricity/regional-admin/complaints">Complaints</a></li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/electricity/regionalAdmin/electricity-paymenthandle.jsp">Payment</a></li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/electricity/regional-admin/connections">New Connection</a></li>
                    <li class="menu-items-li dropdown">
                        <a href="#" class="profile-icon"><i class="fa-solid fa-circle-user"></i></a>
                        <div class="dropdown-content">
                            <a href="<%= request.getContextPath() %>/public/HTML/electricity/regionalAdmin/settings.jsp">Settings</a>
                            <a href="<%= request.getContextPath() %>/logout">LogOut</a>
                        </div>
                    </li>
                </ul>
                <img src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt="Utility Saga" class="logo">
            </div>
        </header>
    </div>
</header>
<div class="payment-handle">
    <div class="top-bar">
        <h2>Payment Handling</h2>
        <div class="card-bar">
            <div class="card">
                <p>Payments via the application</p>
            </div>
            <div class="card">
                <p>Manual Bill Payments</p>
            </div>
        </div>
    </div>
    <div class="bottom-bar">
        <div class="paymetbar">
            <h3>Payment via the application</h3>
            <p>Payments made via the application will be automatically updated in the system</p>
            <table>
                <tr>
                    <th>Account Number</th>
                    <th>NIC</th>
                    <th>Amount</th>
                    <th>Payment Date</th>
                    <th>Payment Method</th>
                    <th>Payment Status</th>
                </tr>
                <tr>
                    <td>1</td>
                    <td>123456789V</td>
                    <td>1000</td>
                    <td>2020-10-10</td>
                    <td>Card</td>
                    <td>Success</td>
                </tr>
            </table>
        </div>
        <div class="paymetbar2">
            <h3>Manual Bill Payments</h3>
            <p>Payments made manually have update into the system</p>
            <form class="formbox">
                <label for="account-number">Account Number</label>
                <input type="text" id="account-number" name="account-number" required>
                <label for="nic">NIC</label>
                <input type="text" id="nic" name="nic" required>
                <label for="amount">Amount</label>
                <input type="text" id="amount" name="amount" required>
                <label for="payment-date">Payment Date</label>
                <input type="date" id="payment-date" name="payment-date" required>

                <div class="btnbar">
                    <button class="btn submit">Add to table</button>
                    <button class="btn1">Close</button>
                </div>
            </form>

            <table>
                <tr>
                    <th>Account Number</th>
                    <th>NIC</th>
                    <th>Payment Amount</th>
                    <th>Payment Date</th>
                </tr>
                <tr>
                    <td>1</td>
                    <td>123456789V</td>
                    <td>1000</td>
                    <td>2020-10-10</td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>