<%@ page import="DAO.dao.ElectricityManualPaymentDao" %>
<%@ page import="model.ManualPaymentsModel" %>
<%@ page import="DAO.dao.WaterAdminDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="DAO.dao.WaterManualPaymentDao" %><%--
  Created by IntelliJ IDEA.
  User: NETHMI LIYANAGE
  Date: 4/25/2024
  Time: 6:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Water Regional admin payment handling</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-jicI5S7PZg7NtOWKp6hv3zokYkaw9fdL3+M5uHyXr+1XNMe5W4/zJ3uiz5zgI5Fp9Pwe5VXvBsYHpma/8ZkC9w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="<%= request.getContextPath() %>/public/CSS/dashboards/Admin/regionaladminWaterpayment.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css">
    <link rel="stylesheet" href="../../../CSS/dashboards/dashboard.css">
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
    <div class="payment-handle">
        <div class="bottom-bar">
            <div class="paymentbar2">
                <h3>Water - Handle Manual Bill Payments</h3>
                <p>Payments made manually have update into the system</p>
                <form id="manualpaymentform" method="POST" class="formbox" action="${pageContext.request.contextPath}/watermanualpayment">
                    <label for="account_number">Account Number</label>
                    <input type="text" id="account_number" name="account_number" required>
                    <label for="nic">NIC</label>
                    <input type="text" id="nic" name="nic" required>
                    <label for="amount">Amount</label>
                    <input type="text" id="amount" name="amount" required>
                    <label for="date">Payment Date</label>
                    <input type="date" id="date" name="date" required>

                    <div class="btnbar">
                        <button class="btn submit">Add to table</button>
                    </div>
                </form>
                <table>
                    <thead>
                    <tr>
                        <th>Account Number</th>
                        <th>NIC</th>
                        <th>Payment Amount</th>
                        <th>Payment Date</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        WaterManualPaymentDao dao = new WaterManualPaymentDao();
                        List<ManualPaymentsModel> manualpayements = dao.getManualPayments();
                        for (ManualPaymentsModel manualpayement : manualpayements) {
                    %>
                    <tr>
                        <td><%= manualpayement.getAccount_number() %></td>
                        <td><%= manualpayement.getNic() %></td>
                        <td><%= manualpayement.getAmount() %></td>
                        <td><%= manualpayement.getDate() %></td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
