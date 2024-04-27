<%--
  Created by IntelliJ IDEA.
  User: Liviru Weerasinghe
  Date: 4/10/2024
  Time: 7:45 AM
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
    <title>Super Admin- Dashboard</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="../../JS/SuperAdminElectricityWater.js"></script>
    <script src="<%= request.getContextPath() %>/public/JS/dashboard.js"></script>
    <script src="<%= request.getContextPath() %>/public/JS/SuperAdminElectricityWater.js"></script>
    <link href="<%= request.getContextPath() %>/public/CSS/superadmin/Superadmin-editadmins.css" rel="stylesheet">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/dashboard.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/forms.css">

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
                        <li class="menu-items-li"><a href="<%= request.getContextPath() %>/super-admin/main-electricity-accounts">Electricity/Water</a></li>
                        <li class="menu-items-li"><a href="<%= request.getContextPath() %>/super-admin/solar-accounts">Solar</a></li>
                        <li class="menu-items-li"><a id="logout" href="<%= request.getContextPath() %>/logout">LogOut</a></li>
                    </ul>
                    <img src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt="Utility Saga" class="logo">
                </div>
            </header>
        </div>

        <div class="middle" id="middle">
            <h2 class="title">Electricity Admins</h2>

            <div class="search-container">
                <form id="searchForm" method="get" action="<%= request.getContextPath() %>/super-admin/main-electricity-accounts">
                    <label for="nic"></label>
                    <input name="id" type="text" id="nic" class="search-input" placeholder="Enter EMPID">

                    <button type="submit" name="search" class="=searchbtn">Search</button>
                    <button type="button" id="resetButton" class="=searchbtn">Reset</button>
                </form>
            </div>
            <script>
                document.getElementById('resetButton').addEventListener('click', function() {
                    document.getElementById('nic').value = '';
                    document.getElementById('searchForm').submit();
                });
            </script>
            <div class="buttons">
                <button class="button" onclick="openPopup('popupForm')">Add Electricity Admins</button>
              </div>

            <div id="results"></div>

            <div class="popup-form" id="popupForm" style="display: none;">
                <div id="popupContainer" class="popup-container">
                    <h2 class="popup-title">Add Electricity Admin</h2>
                    <form id="addForm" method="POST" action="${pageContext.request.contextPath}/elecAdmin">
                        <label for="region">Region:</label>
                        <input type="text" name="region" id="region"
                               placeholder="Enter the region"
                               required>

                        <label for="contact">Contact Number:</label>
                        <input type="text" name="contact" id="contact"
                               placeholder="Enter the contact number of office"
                               oninput="this.value = this.value.replace(/[^0-9]/g, '');"
                               required>

                        <label for="email">Email:</label>
                        <input type="email" name="email" id="email"
                               placeholder="Enter new email"
                               required>

                        <label for="password">Password:</label>
                        <input type="password" name="password" id="password"
                               placeholder="Enter Password"
                               required>

                        <label for="re-password">Reenter Password:</label>
                        <input type="password" name="re-password" id="re-password"
                               placeholder="Re- enter the password"
                               required>

                        <label for="utility">Utility:</label>
                        <select name="utility" id="utility" required>
                            <option value="CEB">CEB</option>
                            <option value="LECO">LECO</option>
                        </select>

                        <label for="empid">Employee ID:</label>
                        <input type="text" name="empid" id="empid"
                               placeholder="Assigned Employee ID"
                               required>

                        <label for="uname">Username:</label>
                        <input type="text" name="uname" id="uname"
                               placeholder="Enter new username"
                               required>

                        <label for="fname">First Name:</label>
                        <input type="text" name="fname" id="fname"
                               placeholder="Enter firstname"
                               required>

                        <label for="lname">Last Name:</label>
                        <input type="text" name="lname" id="lname"
                               placeholder="Enter lastname"
                               required>

                        <label for="role">Admin Type:</label>
                        <select name="role" id="role" required>
                            <option value="MAIN">Main</option>
                        </select>

                        <label for="mobile">Mobile:</label>
                        <input type="text" name="mobile" id="mobile"
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

            <table class="table">
                <tr>
                    <th>Region</th>
                    <th>Contact Number</th>
                    <th>Email</th>
                    <th>CEB/LECO</th>
                    <th>Employee ID</th>
                    <th>Username</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Main/Regional</th>
                    <th>Mobile</th>
                </tr>
                <c:if test="${empty requestScope.electricityMainAdmins}">
                    <tr>
                        <td colspan="12">No admins found</td>
                    </tr>
                </c:if>
                <c:if test="${not empty requestScope.electricityMainAdmins}">
                <c:forEach items="${requestScope.electricityMainAdmins}" var="admin">
                    <tr>
                    <td>${admin.region}</td>
                    <td>${admin.contactNumber}</td>
                    <td>${admin.email}</td>
                    <td> ${admin.utilityType} </td>
                    <td> ${admin.empId} </td>
                    <td> ${admin.username}</td>
                    <td> ${admin.firstName} </td>
                    <td> ${admin.lastName}</td>
                    <td> ${admin.role} </td>
                    <td> ${admin.mobile}</td>
                </tr>
                </c:forEach>
                </c:if>
            </table>

            <h2 class="title">Water Admins</h2>

            <div class="search-container">
                <form id="searchForm2" method="get" action="<%= request.getContextPath() %>/super-admin/main-electricity-accounts">
                    <label for="nic"></label>
                    <input name="id2" type="text" class="search-input" id="nic2" placeholder="Enter EMPID">

                    <button type="submit" name="search" class="=searchbtn">Search</button>
                    <button type="button" id="resetButton2" class="=searchbtn">Reset</button>
                </form>
            </div>
            <script>
                document.getElementById('resetButton2').addEventListener('click', function() {
                    document.getElementById('nic2').value = '';
                    document.getElementById('searchForm2').submit();
                });
            </script>
            <div id="results2"></div>

            <div class="buttons">
                <button class="button" onclick="openPopup('wpopupForm')">Add Water Admins</button>
            </div>
            <div class="popup-form" id="wpopupForm" style="display: none;">
                <div id="wpopupContainer" class="popup-container">
                    <h2 class="popup-title">Add Electricity Admin</h2>
                    <form id="waddForm" method="POST" action="${pageContext.request.contextPath}/waterAdmin">
                        <label for="wregion">Region:</label>
                        <input type="text" name="wregion" id="wregion"
                               placeholder="Enter the region"
                               required>

                        <label for="wcontact">Contact Number:</label>
                        <input type="text" name="wcontact" id="wcontact"
                               placeholder="Enter the contact number of office"
                               oninput="this.value = this.value.replace(/[^0-9]/g, '');"
                               required>

                        <label for="wemail">Email:</label>
                        <input type="email" name="wemail" id="wemail"
                               placeholder="Enter new email"
                               required>

                        <label for="wpassword">Password:</label>
                        <input type="password" name="wpassword" id="wpassword"
                               placeholder="Enter Password"
                               required>

                        <label for="re-wpassword">Reenter Password:</label>
                        <input type="password" name="re-wpassword" id="re-wpassword"
                               placeholder="Re- enter the password"
                               required>

                        <label for="wempid">Employee ID:</label>
                        <input type="text" name="wempid" id="wempid"
                               placeholder="Assigned Employee ID"
                               required>

                        <label for="wuname">Username:</label>
                        <input type="text" name="wuname" id="wuname"
                               placeholder="Enter new username"
                               required>

                        <label for="wfname">First Name:</label>
                        <input type="text" name="wfname" id="wfname"
                               placeholder="Enter firstname"
                               required>

                        <label for="wlname">Last Name:</label>
                        <input type="text" name="wlname" id="wlname"
                               placeholder="Enter lastname"
                               required>

                        <label for="wrole">Admin Type:</label>
                        <select name="wrole" id="wrole" required>
                            <option value="MAIN">Main</option>
                        </select>

                        <label for="wmobile">Mobile:</label>
                        <input type="text" name="wmobile" id="wmobile"
                               placeholder="Enter mobile number"
                               oninput="this.value = this.value.replace(/[^0-9]/g, '');"
                               required>

                        <div class="form-button">
                            <button type="submit" class="buttons">Add Admin</button>
                            <button  onclick="closePopup('wpopupForm')" class="buttons">Close</button>
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
                </tr>

                <c:if test="${empty requestScope.waterMainAdmins}">
                    <tr>
                        <td colspan="12">No admins found</td>
                    </tr>
                </c:if>
                <c:if test="${not empty requestScope.waterMainAdmins}">
                <c:forEach items="${requestScope.waterMainAdmins}" var="admin">
                <tr>
                    <td>${admin.region}</td>
                    <td>${admin.contactNumber}</td>
                    <td>${admin.email}</td>
                    <td> ${admin.empId} </td>
                    <td> ${admin.username}</td>
                    <td> ${admin.firstName} </td>
                    <td> ${admin.lastName}</td>
                    <td> ${admin.role} </td>
                    <td> ${admin.mobile}</td>
                </tr>
                </c:forEach>
                </c:if>


            </table>

            </div>

            </div>

</body>
<script src="../../JS/dashboard.js"></script>
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

    function openPopup(popUpId) {
        var popup= document.getElementById(popUpId);
        if(popup){
            popup.style.display = "block";
            initializeValidationElectricity();
            initializeValidationWater();
            }


    }

    function closePopup(popUpId) {
        var popup = document.getElementById(popUpId);
        if (popup) {
            popup.style.display = "none";
        }
    }
</script>
<script src="<%= request.getContextPath() %>/public/JS/ValidationSuperAdmin.js"></script>
</html>