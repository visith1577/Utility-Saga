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
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/main-admin/water-accounts">Regional Admins</a></li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/water/admin/settings.jsp">Settings</a></li>
                    <li class="menu-items-li"><a id="logout" href="<%= request.getContextPath() %>/logout">LogOut</a></li>
                </ul>
                <img src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt="Utility Saga" class="logo">
            </div>
        </header>
    </div>

    <div style="margin-top: 100px">
        <form id="searchForm" method="get" action="<%= request.getContextPath() %>/main-admin/water-accounts">
            <label for="nic"></label>
            <input name="id" type="text" id="nic" placeholder="Enter EmpID">

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


    <div class="middle" id="middle">
        <div id="results"></div>
        <h4 class="title">Water Admins</h4>
        <div class="buttons">
            <button class="button" onclick="openPopup('popupForm')">Add</button>
        </div>
        <div class="popup-form" id="popupForm" style="display: none;">
            <div id="popupContainer" class="popup-container">
                <h3 class="popup-title">Add Electricity Admin</h3>
                <form id="addForm" method="POST" action="${pageContext.request.contextPath}/waterAdmin">
                    <label for="region">Region:</label>
                    <input type="text" name="wregion" id="region" required>

                    <label for="contact">Contact Number:</label>
                    <input type="text" name="wcontact" id="contact" required>

                    <label for="email">Email:</label>
                    <input type="email" name="wemail" id="email" required>

                    <label for="password">Password:</label>
                    <input type="password" name="wpassword" id="password" required>

                    <label for="empid">Employee ID:</label>
                    <input type="text" name="wempid" id="empid" required>

                    <label for="uname">UserName:</label>
                    <input type="text" name="wuname" id="uname" required>

                    <label for="fname">First Name:</label>
                    <input type="text" name="wfname" id="fname" required>

                    <label for="lname">Last Name:</label>
                    <input type="text" name="wlname" id="lname" required>

                    <label for="role">Admin Type:</label>
                    <select name="wrole" id="role" required>
                        <option value="REGIONAL">Regional</option>
                    </select>

                    <label for="mobile">Mobile:</label>
                    <input type="text" name="wmobile" id="mobile" required>

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
                <th>Password</th>
                <th>Employee ID</th>
                <th>Username</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Main/Regional</th>
                <th>Mobile</th>
            </tr>

            <c:if test="${empty requestScope.waterRegionalAdmins}">
                <tr>
                    <td colspan="12"> No admins found </td>
                </tr>
            </c:if>
            <c:if test="${not empty requestScope.waterRegionalAdmins}">
                <c:forEach items="${requestScope.waterRegionalAdmins}" var="admin">
                    <tr>
                        <td>${admin.region}</td>
                        <td>${admin.contactNumber}</td>
                        <td>${admin.email}</td>
                        <td>${admin.password}</td>
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

</div>
</div>
</div>

</body>
<script src="../../../JS/dashboard.js"></script>
<script>
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
        }

    }

    function closePopup(popUpId) {
        var popup = document.getElementById(popUpId);
        if (popup) {
            popup.style.display = "none";
        }
    }
</script>

</html>
