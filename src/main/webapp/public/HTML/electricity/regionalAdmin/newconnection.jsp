<%--
  Created by IntelliJ IDEA.
  User: Liviru Weerasinghe
  Date: 4/18/2024
  Time: 12:28 PM
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
    <title>Electricity Regional admin dashboard</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="<%= request.getContextPath() %>/public/CSS/dashboards/Admin/regionalAdminElecForm.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-jicI5S7PZg7NtOWKp6hv3zokYkaw9fdL3+M5uHyXr+1XNMe5W4/zJ3uiz5zgI5Fp9Pwe5VXvBsYHpma/8ZkC9w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="<%= request.getContextPath() %>/public/CSS/popup.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/dashboard.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/forms.css">
    <link href="<%= request.getContextPath() %>/public/CSS/dashboards/Admin/regionalAdminElectricity.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="<%= request.getContextPath() %>/public/JS/ElectricityAdminDashboard.js"></script>
    <script src="<%= request.getContextPath() %>/public/JS/ElectricityRegionalConnectionSearch.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
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

        document.addEventListener('DOMContentLoaded', function() {

            const submitButtons = document.querySelectorAll('.submit-btn');

            console.log(submitButtons);

            submitButtons.forEach(button => {
                button.addEventListener('click', () => {
                    const row = button.parentNode.parentNode;
                    const bNum = row.cells[1].textContent;
                    console.log(bNum)
                    const statusSelect = row.querySelector('select[name="accountStatus"]');
                    const status = statusSelect.value;
                    console.log(status)
                    updateApprovalStatus(bNum, status)
                });
            });

            function updateApprovalStatus(bNum, status) {
                const contextPath = '<%= contextPath %>';
                fetch(contextPath + '/UpdateRegionalConnectionStatus?companyId=' + encodeURIComponent(bNum) + '&status=' + encodeURIComponent(status), {
                    method: "POST"
                })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('Response was not ok');
                        }
                        return response.json();
                    }).then(_ => {
                    toastr.success("Status of " + bNum + " updated to " + status + " successfully.");
                }).catch(error => {
                    console.error("Problem updating approval status: ", error);
                })
            }
        });
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


<div style="margin-top: 12.5vh">
    <form id="searchForm" method="get" action="<%= request.getContextPath() %>/electricity/regional-admin/connections">
        <label for="nic"></label>
        <input name="id" type="text" id="nic" placeholder="Enter Keyword" style="margin-left: 20px">

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

<div class="complaints">
    <div class="tablediv">
        <div class="customerdetails">
            <div class="titlebar">
                <h2>Complaint Details</h2>
                <a href="#" class="btn">View All</a>
            </div>


            <div id="results"></div>

            <div class="popup-form" id="popupForm" style="display: none;">
                <div id="popupContainer" class="popup-container">
                    <h2 class="popup-title">Add Electricity Admin</h2>
                    <form id="addForm" method="POST" action="${pageContext.request.contextPath}/electricity/regional-admin/create-account">
                        <table>
                            <tr>
                                <td><label for="region">Region </label></td>
                                <td><input type="text" name="region" id="region" required></td>
                            </tr>
                            <tr>
                                <td><label for="subregion">Sub Region</label></td>
                                <td><input type="text" name="subregion" id="subregion" required></td>
                            </tr>
                            <tr>
                                <td><label for="accountno">Account Number </label></td>
                                <td><input type="text" name="accountno" id="accountno" required></td>
                            </tr>
                            <tr>
                                <td><label for="requestid">Request ID </label></td>
                                <td><input type="text" name="requestid" id="requestid" oninput="this.value = this.value.replace(/[^0-9]/g, '');"></td>
                            </tr>
                            <tr>
                                <td><label for="nicc">NIC</label></td>
                                <td><input type="text" name="nicc" id="nicc" required></td>
                            </tr>
                            <tr>
                                <td><label for="iotId">IoT device ID</label></td>
                                <td><input type="text" name="iotId" id="iotId" required></td>
                            </tr>
                            <tr>
                                <td colspan="2" class="form-button">
                                    <button type="submit" class="buttons">Add Admin</button>
                                    <button onclick="closePopup('popupForm')" class="buttons">Close</button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>

            <div class="table-container">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Requester Name</th>
                        <th>Request ID</th>
                        <th>NIC</th>
                        <th>Email</th>
                        <th>Mobile</th>
                        <th>Region</th>
                        <th>Current Address</th>
                        <th>New Address</th>
                        <th>Nearest Account</th>
                        <th>Connection Requirement</th>
                        <th>Connection Type</th>
                        <th>Select Account Status</th>
                        <th>Account Status</th>
                        <th>Submit</th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:if test="${empty requestScope.electricityConnectionRequests}">
                        <tr>
                            <td colspan="12">No new connections found</td>
                        </tr>
                    </c:if>
                    <c:if test="${not empty requestScope.electricityConnectionRequests}">
                        <c:forEach items="${requestScope.electricityConnectionRequests}" var="connection">

                            <tr>
                                <td>${connection.requesterName}</td>
                                <td>${connection.requestId}</td>
                                <td>${connection.nic}</td>
                                <td>${connection.email}</td>
                                <td>${connection.mobile}</td>
                                <td>${connection.region}</td>
                                <td>${connection.currentAddress}</td>
                                <td>${connection.newAddress}</td>
                                <td>${connection.nearestAccount}</td>
                                <td>${connection.connectionRequirements}</td>
                                <td>${connection.connectionType}</td>
                                <td><select name="accountStatus">
                                    <option  value="REJECTED" ${ connection.accountStatus == ConnectionModel.accountStatus.REJECTED ? "selected" : "" }>Rejected</option>
                                    <option  value="PENDING" ${ connection.accountStatus == ConnectionModel.accountStatus.PENDING ? "selected" : "" }>Pending</option>
                                </select></td>
                                <td>${connection.accountStatus}</td>
                                <td><button class="submit-btn" id="${connection.accountNumber}" >Submit</button></td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="buttons">
    <button class="button" onclick="openPopup('popupForm')">Add New Account</button>
</div>

<script>
    function openPopup(popUpId) {
        const popup= document.getElementById(popUpId);
        if(popup){
            popup.style.display = "block";
        }
    }

    function closePopup(popUpId) {
        var popup = document.getElementById(popUpId);
        if (popup) {
            popup.style.display = "none";
        }
    }

    function isValidNic(nicNumber) {
        let result;
        if (nicNumber.length === 10 && !isNaN(nicNumber.substring(0, 9)) && isNaN(nicNumber.substring(9, 1)) && ['x', 'v'].includes(nicNumber.substring(9, 1).toLowerCase())) {
            result = true;
        } else result = nicNumber.length === 12 && !isNaN(nicNumber);
        return result;
    }

    document.getElementById('addForm').addEventListener('submit', function(event) {
        event.preventDefault();
        const nic = document.getElementById('nicc').value;
        if (!isValidNic(nic)) {
            Swal.fire({
                icon: "error",
                title: "NIC Invalid",
                text: "Check NIC number and try again."
            });
        } else {
            this.submit();
        }

        // fetch from backend if account number is already in use || if request id has been fulfilled already || iot device is already owned

    });
</script>

</body>
</html>
