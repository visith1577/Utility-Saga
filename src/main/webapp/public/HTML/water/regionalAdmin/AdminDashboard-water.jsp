<%@ page import="DAO.dao.UserDetailsDao" %>
<%
    // Get the context path dynamically
    String contextPath = request.getContextPath();
%>
<% String firstname = (String) session.getAttribute("FNAME"); %>
<% String lastname = (String) session.getAttribute("LNAME"); %>
<% String region = (String) session.getAttribute("REGION"); %>
<% UserDetailsDao user = new UserDetailsDao(); %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Water Regional admin dashboard</title>
    <link rel="stylesheet" href="path/to/font-awesome/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-jicI5S7PZg7NtOWKp6hv3zokYkaw9fdL3+M5uHyXr+1XNMe5W4/zJ3uiz5zgI5Fp9Pwe5VXvBsYHpma/8ZkC9w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<link href="<%= request.getContextPath() %>/public/CSS/popup.css" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/dashboard.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/forms.css">
<%--    <link href="<%= request.getContextPath() %>/public/CSS/dashboards/Admin/regionalAdminElectricity.css" rel="stylesheet">--%>
    <script src="<%= request.getContextPath() %>/public/JS/dashboard.js"></script>
    <script src="<%= request.getContextPath() %>/public/JS/RegionalAdminWaterUsers.js"></script>
    <link href="<%= request.getContextPath() %>/public/CSS/dashboards/Admin/admintable.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/public/CSS/dashboards/Admin/admincards.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/public/CSS/dashboards/Admin/regionaladmin.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            let contextPath = '<%= contextPath %>';
            document.querySelector('table').addEventListener('click', function(event) {
                if (event.target.classList.contains('change-status-btn')) {
                    changeUserStatus(event);
                }
            });
        });


        function changeUserStatus(event) {
            let contextPath = '<%= contextPath %>';
            console.log('changeUserStatus function called');

            const row = event.target.closest('tr');
            console.log('Row:', row);

            const accountNumber = row.querySelector('td:nth-child(1)').textContent;
            console.log('Account Number:', accountNumber);

            const currentStatus = row.querySelector('td:nth-child(7)').textContent;
            console.log('Current Status:', currentStatus);

            const newStatus = currentStatus === 'ACTIVE' ? 'INACTIVE' : 'ACTIVE';
            console.log('New Status:', newStatus);

            fetch(contextPath+ '/user-status-water', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    accountNumber: accountNumber,
                    newStatus: newStatus
                })
            })
                .then(response => response.json())
                .then(data => {
                    console.log('Response from server:', data);

                    row.querySelector('td:nth-child(7)').textContent = data.status;
                })
                .catch(error => {
                    console.error('Error updating user status:', error);
                });
        }
    </script>

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
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/water/regionalAdmin/electricity-paymenthandle.jsp">Payment</a></li>
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
<div class="topbar" style="margin-top: 12.5vh">
    <div class="lefttop" style="font-size: larger">
        <h2>Hello <%=firstname%> <%=lastname%> </h2>
    </div>
    <div class="righttop" style="font-size: x-large">
        <ul>
            <li style="list-style-type: none">Region: <%=region%></li>
        </ul>
    </div>
</div>
<section class="home">
    <!-- Cards -->
    <div class="cardBox">

        <a class="card" href="#">
            <div class="infor">
                <div class="numbers"><%= user.getTotalWaterUsersRegion(region)%></div>
                <div class="cardName">Water Service Users</div>
            </div>
            <div class="iconBx">
                <i class="fa-solid fa-users"></i>
            </div>
        </a>

        <a class="card" href="#">
            <div class="infor">
                <div class="numbers"><%= user.getNewWaterConnections(region)%></div>
                <div class="cardName">New Active Connection Requests</div>
            </div>
            <div class="iconBx">
                <i class="fa-solid fa-faucet"></i>
            </div>
        </a>

        <a class="card" href="#">
            <div class="infor">
                <div class="numbers"><%= user.getNewWaterComplaints(region)%></div>
                <div class="cardName">Unresolved Complaints</div>
            </div>
            <div class="iconBx">
                <i class="fa-solid fa-user-group"></i>
            </div>
        </a>

        <a class="card" href="#">
            <div class="infor">
                <div class="numbers"><%= user.getTotalElectricityAccountsRegion(region)%></div>
                <div class="cardName">Electricity Service Accounts</div>
            </div>
            <div class="iconBx">
                <i class="fa-solid fa-user-group"></i>
            </div>
        </a>
    </div>

    <div class="searchbar">
        <form id="searchForm" method="get" action="<%= request.getContextPath() %>/water/regional-admin/user-accounts">
            <label for="nic"></label>
            <input name="id" type="text" id="nic" placeholder="Enter Keyword" style="margin-left: 20px">

            <button type="submit" name="search" class="btn" onclick="search(); return false;">Search</button>
            <button type="button" id="resetButton" class="btn">Reset</button>
        </form>
    </div>
    <script>
        document.getElementById('resetButton').addEventListener('click', function() {
            document.getElementById('nic').value = '';
            document.getElementById('searchForm').submit();
        });
    </script>

    <div class="details">
        <div class="tablediv">
            <div class="customerdetails">
                <div class="titlebar">
                    <h2>Customer Details</h2>
                    <a href="#" class="btn">View All</a>
                </div>

                <div id="results"></div>
                <div class="table-container">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Account Number</th>
                                <th>Customer NIC</th>
                                <th>Customer Name</th>
                                <th>Mobile Number</th>
                                <th>Email</th>
                                <th>Address</th>
                                <th>Status</th>
                                <th>Change Status</th>
                            </tr>
                        </thead>
                        <tbody>

                        <c:if test="${empty requestScope.waterRegionalUsers}">
                            <tr>
                                <td colspan="12">No customers found</td>
                            </tr>
                        </c:if>
                        <c:if test="${not empty requestScope.waterRegionalUsers}">
                        <c:forEach items="${requestScope.waterRegionalUsers}" var="user">

                            <tr>
                                <td>${user.accountNumber}</td>
                                <td>${user.nic}</td>
                                <td>${user.firstName} ${user.lastName}</td>
                                <td>${user.mobile}</td>
                                <td>${user.email}</td>
                                <td>${user.address}</td>
                                <td>${user.connectionStatus}</td>
                                <td><button class="change-status-btn">Change Status</button></td>
                            </tr>
                        </c:forEach>
                        </c:if>

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

<script src="<%= request.getContextPath() %>/public/JS/ElectricityAdminDashboard.js"></script>
</body>
</html>