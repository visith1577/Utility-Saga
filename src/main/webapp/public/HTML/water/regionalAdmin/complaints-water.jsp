<%--
  Created by IntelliJ IDEA.
  User: Liviru Weerasinghe
  Date: 4/13/2024
  Time: 2:52 PM
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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-jicI5S7PZg7NtOWKp6hv3zokYkaw9fdL3+M5uHyXr+1XNMe5W4/zJ3uiz5zgI5Fp9Pwe5VXvBsYHpma/8ZkC9w==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link href="<%= request.getContextPath() %>/public/CSS/popup.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/dashboard.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/forms.css">
    <script src="<%= request.getContextPath() %>/public/JS/dashboard.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script src="<%= request.getContextPath() %>/public/JS/ElectricityAdminDashboard.js"></script>
    <script src="<%= request.getContextPath() %>/public/JS/WaterRegionalComplaintSearch.js"></script>
    <link href="<%= request.getContextPath() %>/public/CSS/dashboards/Admin/admintable.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/public/CSS/dashboards/Admin/admincards.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/public/CSS/dashboards/Admin/regionaladmin.css" rel="stylesheet">
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
                    const bNum = row.cells[0].textContent;
                    console.log(bNum)
                    const statusSelect = row.querySelector('select[name="complaintStatus"]');
                    const status = statusSelect.value;
                    console.log(status)
                    updateApprovalStatus(bNum, status)
                });
            });

            function updateApprovalStatus(bNum, status) {
                const contextPath = '<%= contextPath %>';
                fetch(contextPath + '/UpdateWaterComplaintStatus?companyId=' + encodeURIComponent(bNum) + '&status=' + encodeURIComponent(status), {
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
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/water/regional-admin/user-accounts">Customers</a></li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/water/regional-admin/complaints">Complaints</a></li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/water/regionalAdmin/water-paymenthandle.jsp">Payment</a></li>
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


    <div style="margin-top: 12.5vh">
        <form id="searchForm" method="get" action="<%= request.getContextPath() %>/water/regional-admin/complaints">
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
                            <th>Complaint Number</th>
                            <th>Category</th>
                            <th>Complaint Type</th>
                            <th>NIC</th>
                            <th>Account Number</th>
                            <th>Mobile</th>
                            <th>Complaint Status</th>
                            <th>Select</th>
                            <th>Description</th>
                        </tr>
                        </thead>
                        <tbody>

                    <c:if test="${empty requestScope.waterRegionalComplaints}">
                        <tr>
                            <td colspan="12">No complaints found</td>
                        </tr>
                    </c:if>
                    <c:if test="${not empty requestScope.waterRegionalComplaints}">
                        <c:forEach items="${requestScope.waterRegionalComplaints}" var="complaint">

                        <tr>
                            <td>${complaint.complaintNo}</td>
                            <td>${complaint.complaintCategory}</td>
                            <td>${complaint.complaintType}</td>
                            <td>${complaint.nic}</td>
                            <td>${complaint.accountNumber}</td>
                            <td>${complaint.mobile}</td>
                            <td>${complaint.complaintStatus}</td>
                            <td><select name="complaintStatus">
                                <option  value="ACTIVE" ${ complaint.complaintStatus == ComplaintModel.ComplaintStatus.ACTIVE ? "selected" : "" }>Active</option>
                                <option  value="PENDING" ${ complaint.complaintStatus == ComplaintModel.ComplaintStatus.PENDING ? "selected" : "" }>Pending</option>
                                <option  value="DONE" ${ complaint.complaintStatus == ComplaintModel.ComplaintStatus.DONE ? "selected" : "" }>Done</option>
                            </select></td>
                            <td>${complaint.complaintDescription}</td>
                            <td><button class="submit-btn" id="${complaint.complaintNo}" >Submit</button></td>
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

</body>
</html>
