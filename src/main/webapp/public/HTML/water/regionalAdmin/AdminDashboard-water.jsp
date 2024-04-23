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
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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

            const row = event.target.closest('tr');

            const accountNumber = row.querySelector('td:nth-child(1)').textContent;

            const currentStatus = row.querySelector('td:nth-child(7)').textContent;

            const hasIot = row.querySelector('td:nth-child(8) input').value;
            const iotId = row.querySelector('td:nth-child(9) input').value;

            let changeStatus = true;

            const newStatus = currentStatus === 'ACTIVE' ? 'INACTIVE' : 'ACTIVE';

            Swal.fire({
                title: 'Are you sure?',
                text: "Are you sure you want to change the status of account " + accountNumber + " to " + newStatus + " ?",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: 'Yes, change it!',
                cancelButtonText: 'No, keep it',
                footer: hasIot === 'YES' ? "User has an Iot meter installed proceeding changes will immediately take place" : "Normal meter detected, change status manually"
            }).then((result) => {
                if (result.isConfirmed) {
                    if (hasIot === 'YES'){
                        fetch(contextPath + '/water/regional-admin/iot-control?iotId=' + iotId + '&newStatus=' + newStatus)
                            .then(response => response.text())
                            .then(data => {
                                if (data === 'Failed to publish action') {
                                    Swal.fire({
                                        title: 'Failed to change status!',
                                        text: 'Failed to change the status of the IOT meter.',
                                        icon: 'error',
                                        footer: data
                                    });
                                    changeStatus = false;
                                } else {
                                    Swal.fire({
                                        title: 'Status Changed!',
                                        text: 'The status of the IOT meter has been changed successfully.',
                                        icon: 'success',
                                        footer: data
                                    });

                                    if (changeStatus) {
                                        fetch(contextPath+ '/water/regional-admin/user-status', {
                                            method: 'POST',
                                            headers: {
                                                'Content-Type': 'application/json'
                                            },
                                            body: JSON.stringify({
                                                accountNumber: accountNumber,
                                                newStatus: newStatus,
                                                changeStatus: changeStatus
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
                                }
                            })
                            .catch(error => {
                                console.error('Error updating IOT meter status:', error);
                            });
                    } else {
                        fetch(contextPath+ '/water/regional-admin/user-status', {
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify({
                                accountNumber: accountNumber,
                                newStatus: newStatus,
                                changeStatus: changeStatus
                            })
                        })
                            .then(response => response.json())
                            .then(data => {
                                console.log('Response from server:', data);

                                if (changeStatus) {
                                    row.querySelector('td:nth-child(7)').textContent = data.status;
                                }
                            })
                            .catch(error => {
                                console.error('Error updating user status:', error);
                            });
                    }
                } else if (result.dismiss === Swal.DismissReason.cancel) {
                    console.log('Status change cancelled by user.');
                }
            });
        }
    </script>
    <style>
        .popupdetails {
            display: flex;
            flex-direction: column;
        }

        .popupdetails .detail {
            flex: 1;
            display: flex;
            justify-content: space-between;
        }

        #open-popup-btn {
            background-color: #4CAF50; /* Green background */
            border: none; /* Remove border */
            color: white; /* White text */
            padding: 15px 32px; /* Some padding */
            text-align: center; /* Centered text */
            text-decoration: none; /* Remove underline */
            display: inline-block;
            font-size: 16px;
            margin: 4px 2px;
            cursor: pointer; /* Mouse pointer on hover */
            transition-duration: 0.4s; /* Transition effect */
        }

        #open-popup-btn:hover {
            background-color: #45a049; /* Darker green on hover */
        }

        table tr.selected {
            background-color: #ddd; /* Light gray background */
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
                <div class="numbers"><%= user.getTotalWaterAccountsRegion(region)%></div>
                <div class="cardName">Water Service Accounts</div>
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
                                <th></th>
                                <th></th>
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
                                <td><input type="hidden" value="${user.iotMeter}" readonly></td>
                                <td><input type="hidden" value="${user.iotId}" readonly></td>
                                <td><button class="change-status-btn">Change Status</button></td>
                            </tr>
                        </c:forEach>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <button id="open-popup-btn" type="button">Edit Device</button>
    </div>
</section>

<!-- popup -->
<div class="popupcontainer" id="popupcontainer">
    <div class="popup" id="popup">
        <span class="close" onclick="closePopup()"><i class="fa-solid fa-xmark"></i></span>
        <h2>User Details</h2>
        <form method="POST" action="${pageContext.request.contextPath}/water/regional-admin/api/update-device" id="user-details-form">
            <div class="popupdetails">
                <div class="detail">
                    <label for="account-number">Account No.:</label>
                    <input type="hidden" name="accountNo" id="hiddenAcc">
                    <span id="account-number" class="editable-text">loading ...</span>
                </div>
                <div class="detail">
                    <label for="customer-nic">Customer NIC:</label>
                    <span id="customer-nic" class="editable-text">loading ...</span>
                </div>
                <div class="detail">
                    <label for="connection-status">Connection Status:</label>
                    <span id="connection-status" class="editable-text">loading ...</span>
                </div>
                <div class="detail">
                    <label for="device-id-input">Device ID:</label>
                    <div id="device-id">
                        <input id="device-id-input" name="deviceId" type="text" readonly>
                    </div>
                </div>
            </div>
        </form>
        <div class="btns">
            <button id="update-device-btn" type="button" style="margin-left: 0; background: #023e8a">Update device</button>
            <form action="${pageContext.request.contextPath}/water/regional-admin/api/delete-device" method="post" id="delete-form">
                <input type="hidden" name="deviceId" id="delete-device-id">
                <input type="hidden" name="accountNo" id="delete-account-no">
                <button id="delete-device-btn" type="button" style="background: #023e8a">Delete device</button>
            </form>
        </div>
    </div>
</div>
<script>
    let contextPath = '<%= contextPath %>';
</script>

<script src="<%= request.getContextPath() %>/public/JS/WaterAdminDashboard.js"></script>
</body>
</html>