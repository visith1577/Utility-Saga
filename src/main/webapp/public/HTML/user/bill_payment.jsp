<%--
  Created by IntelliJ IDEA.
  User: NETHMI LIYANAGE
  Date: 4/22/2024
  Time: 1:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="model.UserAccountsModel" %>
<%
    System.out.println(request.getAttribute("electricity_account_list"));
    System.out.println(request.getAttribute("water_account_list"));
%>
<html>
<head>
    <title>Payment</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/navbar.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/dashboard.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/user.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/skeleton.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/bill_payment.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
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
                <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/pages/aboutUs.jsp">About Us</a></li>
                <li class="nxt-page water"><button class="button-17" type="button" onclick="toggle()">Dashboards</button></li>
                <script>
                    const electricity = "<%=session.getAttribute("electricity") != null%>"
                    const water = "<%=session.getAttribute("water") != null%>"
                    function toggle() {
                        if (electricity === 'true'){
                            window.location.href = "<%= request.getContextPath() %>/user/electricity-dashboard";
                        } else if(water === 'true') {
                            window.location.href = "<%= request.getContextPath() %>/user/water-dashboard";
                        }
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
<div class="payment_container">
    <h1>Bill Payments</h1>
    <div class="utility_selection">
        <div class="form-box">
            <form>
                <label class="topic">Select the Utility:</label>
                <div class="radio-btn-container">
                    <div class="radio-btn">
                        <input type="radio" hidden id="ELECTRICITY" name="utility" value="ELECTRICITY"
                               onclick="toggleForm('ELECTRICITY')">
                        <label for="ELECTRICITY">Electricity</label>
                    </div>
                    <div class="radio-btn">
                        <input type="radio" hidden id="WATER" name="utility" value="WATER"
                               onclick="toggleForm('WATER')">
                        <label for="WATER">Water</label>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="account_numinput" id="account_numinput">
        <div class="inputbox" id="electricity_box" style="display: none;">
            <h3>Electricity Accounts</h3>
            <% List<UserAccountsModel> electricityAccountList = (List<UserAccountsModel>) request.getAttribute("electricity_account_list"); %>
            <% if (electricityAccountList != null && !electricityAccountList.isEmpty()) { %>
            <% for (UserAccountsModel account : electricityAccountList) { %>
            <div class="bill-info">
                <p>Account Number: <%= account.getAccount_number() %></p>
                <p>Balance: <%= account.getBalance() %></p>
                <div class="btncls">
                    <button class="epay-now-button" onclick="redirectToPaymentGateway('<%= account.getAccount_number() %>')">Pay Now</button>
                </div>
            </div>
            <% } %>
            <% } else { %>
            <p>No electricity accounts available.</p>
            <% } %>
        </div>

        <div class="inputbox" id="water_box" style="display: none;">
            <% List<UserAccountsModel> waterAccountList = (List<UserAccountsModel>) request.getAttribute("water_account_list"); %>
            <% if (waterAccountList != null && !waterAccountList.isEmpty()) { %>
            <% for (UserAccountsModel account : waterAccountList) { %>
            <div class="bill-info">
                <p>Account Number: <%= account.getAccount_number() %></p>
                <p>Balance: <%= account.getBalance() %></p>
                <div class="btncls">
                    <button class="wpay-now-button" onclick="redirectToPaymentGateway('<%= account.getAccount_number() %>')">Pay Now</button>
                </div>
            </div>
            <% } %>
            <% } else { %>
            <p>No water accounts available.</p>
            <% } %>
        </div>
    </div>

</div>

<script>
    function toggleForm(utility) {
        var electricityBox = document.getElementById('electricity_box');
        var waterBox = document.getElementById('water_box');

        if (utility === 'ELECTRICITY') {
            electricityBox.style.display = 'block';
            waterBox.style.display = 'none';
        } else if (utility === 'WATER') {
            electricityBox.style.display = 'none';
            waterBox.style.display = 'block';
        }
        document.getElementById('bill_detailsbox').style.display = 'none';
    }

    function select_account(account) {

        fetch("<%= request.getContextPath() %>/user/billpayment?account=" + encodeURIComponent(account))
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                console.log(data);
            })
            .catch(error => {
                console.error('There has been a problem with your fetch operation:', error);
            })
    }

        function redirectToPaymentGateway(accountNumber) {
        var baseUrl = "http://localhost:8080/UtilitySaga_war_exploded/public/HTML/user/connect_paymentgateway.jsp";
        // Append the account number as a query parameter
        var url = baseUrl + "?accountNumber=" + encodeURIComponent(accountNumber);
        window.location.href = url;
    }


</script>

</body>
</html>