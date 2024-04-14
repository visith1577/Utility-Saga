<%@ page import="model.UserModel" %>
<%@ page import="java.util.List" %>
<%@ page import="DAO.dao.UserDetailsDao" %>
<% List<UserModel> users = new UserDetailsDao().getUserDetailsRegionalAdmin();%>

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
    <script>
        document.querySelector('table').addEventListener('click', function(event) {
            if (event.target.classList.contains('change-status-btn')) {
                changeUserStatus(event);
            }
        });


        function changeUserStatus(event) {
            console.log('changeUserStatus function called');

            const row = event.target.closest('tr');
            console.log('Row:', row);

            const accountNumber = row.querySelector('td:nth-child(1)').textContent;
            console.log('Account Number:', accountNumber);

            const currentStatus = row.querySelector('td:nth-child(7)').textContent;
            console.log('Current Status:', currentStatus);

            const newStatus = currentStatus === 'active' ? 'inactive' : 'active';
            console.log('New Status:', newStatus);

            fetch('/user-status', {
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
        <div class="tablediv" style="width: 100% !important; max-width: 100% !important;">
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
                            <% for (UserModel user : users) { %>
                            <tr>
                                <td><%= user.getAccount_number() %></td>
                                <td><%= user.getNic() %></td>
                                <td><%= user.getFirstName() %> <%= user.getLastName() %></td>
                                <td><%= user.getMobile() %></td>
                                <td><%= user.getEmail() %></td>
                                <td><%= user.getAddress() %></td>
                                <td><%= user.getConnectionStatus()%></td>
                                <td><button class="change-status-btn">Change Status</button></td>
                            </tr>
                            <% } %>

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