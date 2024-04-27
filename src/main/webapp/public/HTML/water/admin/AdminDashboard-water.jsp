<%--
  Created by IntelliJ IDEA.
  User: Liviru Weerasinghe
  Date: 4/16/2024
  Time: 12:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contextPath = request.getContextPath();
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Water- Main Admin</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="<%= request.getContextPath() %>/public/JS/ElectricityMainAdmin.js"></script>
<%--    <link href="<%= request.getContextPath() %>/public/CSS/superadmin/Superadmin-editadmins.css" rel="stylesheet">--%>
    <link href="<%= request.getContextPath() %>/public/CSS/dashboards/Admin/EWSuperadmin-editadmins.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/dashboard.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/forms.css">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.all.min.js"></script>
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

            const region = row.querySelector('td:nth-child(1)').textContent;
            console.log("region: " ,region);

            const currentStatus = row.querySelector('td:nth-child(10)').textContent;
            console.log("current sttaus: " ,currentStatus);

            const newStatus = currentStatus === 'ACTIVE' ? 'INACTIVE' : 'ACTIVE';
            console.log("New Status: ", newStatus);

            const changeStatus = true;

            Swal.fire({
                title: 'Are you sure?',
                text: "Are you sure you want to change the status of region " + region + " to " + newStatus + " ?",
                icon: 'warning',
                showCancelButton: true,
                confirmButtonText: 'Yes, change it!',
                cancelButtonText: 'No, keep it',
            }).then((result) => {
                if (result.isConfirmed) {
                    const region = row.querySelector('td:nth-child(1)').textContent;
                    if (region) {
                        fetch(contextPath + '/water/main-admin/region-status', {
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/json'
                            },
                            body: JSON.stringify({
                                region: region,
                                newStatus: newStatus,
                                changeStatus: changeStatus
                            })
                        })
                            .then(response => response.json())
                            .then(data => {
                                console.log('Response from server:', data);

                                if (data && data.status) {
                                    row.querySelector('td:nth-child(10)').textContent = data.status;
                                }
                            })
                            .catch(error => {
                                console.error('Error updating user status:', error);
                            });
                        console.log("Data successfully passed");
                    } else {
                        console.error('Region not found.');
                    }
                } else if (result.dismiss === Swal.DismissReason.cancel) {
                    console.log('Status change cancelled.');
                }
            }).catch(error => {
                console.error('Error updating Region status:', error);
            });
        }


    </script>

</head>

<body>
<div class="wrapper">
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
                    <li class="menu-items-li">
                        <a href="<%= request.getContextPath() %>/public/HTML/water/admin/AdminNotificationPg-water.jsp">
                            <span class="material-icons">notifications</span>
                        </a>
                    </li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/main-admin/water-accounts">Regional Admins</a></li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/water/admin/settings.jsp">Settings</a></li>
                    <li class="menu-items-li"><a id="logout" href="<%= request.getContextPath() %>/logout">LogOut</a></li>
                </ul>
                <img src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt="Utility Saga" class="logo">
            </div>
        </header>
    </div>



<%--            <button type="submit" name="search">Search</button>--%>
<%--            <button type="button" id="resetButton">Reset</button>--%>
<%--        </form>--%>
<%--    </div>--%>
<%--    <script>--%>
<%--        document.getElementById('resetButton').addEventListener('click', function() {--%>
<%--            document.getElementById('nic').value = '';--%>
<%--            document.getElementById('searchForm').submit();--%>
<%--        });--%>
<%--    </script>--%>


    <div class="middle" id="middle">
        <div id="results"></div>
        <h4 class="title">Water Admins</h4>
        <div>
            <form id="searchForm" method="get" class="search-container" action="<%= request.getContextPath() %>/main-admin/water-accounts">
                <label for="nic"></label>
                <input name="id" type="text" id="nic" class="search-input" placeholder="Enter EmpID">

                <button type="submit" name="search">Search</button>
                <button type="button" id="resetButton">Reset</button>
            </form>
        </div>
        <script>
            document.getElementById('resetButton').addEventListener('click', function() {
                document.getElementById('nic').value = '';
                document.getElementById('searchForm').submit();
            });
        </script>
        <div class="buttons">
            <button class="button" onclick="openPopup('popupForm')">Add Water Admins</button>
        </div>
        <div class="popup-form" id="popupForm" style="display: none;">
            <div id="popupContainer" class="popup-container">
                <h3 class="popup-title">Add Water Admin</h3>
                <form id="addForm" method="POST" action="${pageContext.request.contextPath}/waterAdmin">
                    <label for="region">Region:</label>
                    <input type="text" name="wregion" id="region"
                           placeholder="Enter the region"
                           required>

                    <label for="contact">Contact Number:</label>
                    <input type="text" name="wcontact" id="contact"
                           placeholder="Enter the contact number of office"
                           oninput="this.value = this.value.replace(/[^0-9]/g, '');"
                           required>

                    <label for="email">Email:</label>
                    <input type="email" name="wemail" id="email"
                           placeholder="Enter new email"
                           required>

                    <label for="password">Password:</label>
                    <input type="password" name="wpassword" id="password"
                           placeholder="Enter the password"
                           required>

                    <label for="re-password">Reenter Password:</label>
                    <input type="password" name="re-wpassword" id="re-password"
                           placeholder="Re- enter the password"
                           required>

                    <label for="empid">Employee ID:</label>
                    <input type="text" name="wempid" id="empid"
                           placeholder="Assigned Employee ID"
                           required>

                    <label for="uname">UserName:</label>
                    <input type="text" name="wuname" id="uname"
                           placeholder="Enter new username"
                           required>

                    <label for="fname">First Name:</label>
                    <input type="text" name="wfname" id="fname"
                           placeholder="Enter firstname"
                           required>

                    <label for="lname">Last Name:</label>
                    <input type="text" name="wlname" id="lname"
                           placeholder="Enter lastname"
                           required>

                    <label for="role">Admin Type:</label>
                    <select name="wrole" id="role" required>
                        <option value="REGIONAL">Regional</option>
                    </select>

                    <label for="mobile">Mobile:</label>
                    <input type="text" name="wmobile" id="mobile"
                           placeholder="Enter mobile number"
                           oninput="this.value = this.value.replace(/[^0-9]/g, '');"
                           required>

                    <div class="form-button">
                        <button type="submit" class="buttons">Add Admin</button>
                        <button  onclick="closePopup('popupForm')" class="buttons">Close</button>
                    </div>
                </form>

            </div>
        </div>
        <div class="popup-form" id="editPopupForm" style="display: none;">
            <div id="popupContainer2" class="popup-container">
                <h3 class="popup-title">Edit Contact Information</h3>
                <form id="editForm" method="POST" action="${pageContext.request.contextPath}/water/main-admin/update-regional-details">
                    <label for="editRegion">Region:</label>
                    <input type="text" name="editRegion" id="editRegion" readonly>

                    <label for="editContact">Contact Number:</label>
                    <input type="text" name="editContact" id="editContact" required>

                    <label for="editEmail">Email:</label>
                    <input type="email" name="editEmail" id="editEmail" required>

                    <div class="form-button">
                        <button type="submit" class="buttons">Save Changes</button>
                        <button type="button" onclick="cancelEdit()" class="buttons">Cancel</button>
                    </div>
                </form>
            </div>
        </div>


        <table class="table">
            <tr>
                <th>Region</th>
                <th>Contact Number</th>
                <th>Email</th>
                <th>Employee ID</th>
                <th>Username</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Main/Regional</th>
                <th>Mobile</th>
                <th>Activate/Deactivate</th>
                <th>Change Status</th>
            </tr>

            <c:if test="${empty requestScope.waterRegionalAdmins}">
                <tr>
                    <td colspan="12"> No admins found </td>
                </tr>
            </c:if>
            <c:if test="${not empty requestScope.waterRegionalAdmins}">
                <c:forEach items="${requestScope.waterRegionalAdmins}" var="admin">
                    <tr>
                        <td onclick="openEditPopup('${admin.region}' ,'${admin.contactNumber}', '${admin.email}')" style="cursor: pointer">${admin.region}</td>
                        <td>${admin.contactNumber}</td>
                        <td>${admin.email}</td>
                        <td> ${admin.empId} </td>
                        <td> ${admin.username}</td>
                        <td> ${admin.firstName} </td>
                        <td> ${admin.lastName}</td>
                        <td> ${admin.role} </td>
                        <td> ${admin.mobile}</td>
                        <td>${admin.activeStatus}</td>
                        <td><button class="change-status-btn">Change Status</button></td>
                    </tr>
                </c:forEach>
            </c:if>

        </table>

</div>
</div>
</div>

</body>
<script src="../../../JS/dashboard.js"></script>
<script>
    var contextPath = '<%= contextPath %>';
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

    function openPopup(popUpId) {
        var popup= document.getElementById(popUpId);
        if(popup){
            popup.style.display = "block";
            initializeValidation();
        }

    }

    function closePopup(popUpId) {
        var popup = document.getElementById(popUpId);
        if (popup) {
            popup.style.display = "none";
        }
    }

    function openEditPopup(region,contactNumber, email) {
        document.getElementById('editRegion').value = region;
        document.getElementById('editContact').value = contactNumber;
        document.getElementById('editEmail').value = email;
        openPopup('editPopupForm');
    }

    function cancelEdit() {
        document.getElementById('editPopupForm').style.display = 'none';
    }
</script>
<script src="<%= request.getContextPath() %>/public/JS/ValidationMainAdminWater.js"></script>

</html>
