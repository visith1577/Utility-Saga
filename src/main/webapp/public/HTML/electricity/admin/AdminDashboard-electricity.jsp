<%--
  Created by IntelliJ IDEA.
  User: Liviru Weerasinghe
  Date: 4/13/2024
  Time: 10:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contextPath = request.getContextPath();
%>
<%@ page import="DAO.dao.sendNotificationDao" %>
<%@ page import="java.util.List" %>
<%@ page import="model.SendNotificationModel" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Document</title>
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
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/main-admin/electricity-accounts">Regional Admins</a></li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/electricity/admin/settings.jsp">Settings</a></li>
                    <li class="menu-items-li"><a id="logout" href="<%= request.getContextPath() %>/logout">LogOut</a></li>
                </ul>
                <img src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt="Utility Saga" class="logo">
            </div>
        </header>
    </div>

    <div style="margin-top: 100px">
        <form id="searchForm" method="get" action="<%= request.getContextPath() %>/main-admin/electricity-accounts">
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
        <h4 class="title">Electricity Admins</h4>
        <div class="buttons">
            <button class="button" onclick="openPopup('popupForm')">Add</button>
        </div>
        <div class="popup-form" id="popupForm" style="display: none;">
            <div id="popupContainer" class="popup-container">
                <h3 class="popup-title">Add Electricity Admin</h3>
                <form id="addForm" method="POST" action="${pageContext.request.contextPath}/elecAdmin">
                    <label for="region">Region:</label>
                    <input type="text" name="region" id="region" required>

                    <label for="contact">Contact Number:</label>
                    <input type="text" name="contact" id="contact" required>

                    <label for="email">Email:</label>
                    <input type="email" name="email" id="email" required>

                    <label for="password">Password:</label>
                    <input type="password" name="password" id="password" required>

                    <label for="utility">Utility:</label>
                    <select name="utility" id="utility" required>
                        <option value="CEB">CEB</option>
                        <option value="LECO">LECO</option>
                    </select>

                    <label for="empid">Employee ID:</label>
                    <input type="text" name="empid" id="empid" required>

                    <label for="uname">UserName:</label>
                    <input type="text" name="uname" id="uname" required>

                    <label for="fname">First Name:</label>
                    <input type="text" name="fname" id="fname" required>

                    <label for="lname">Last Name:</label>
                    <input type="text" name="lname" id="lname" required>

                    <label for="role">Admin Type:</label>
                    <select name="role" id="role" required>
                        <option value="REGIONAL">Regional</option>
                    </select>

                    <label for="mobile">Mobile:</label>
                    <input type="text" name="mobile" id="mobile" required>

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
                <form id="editForm" method="POST" action="${pageContext.request.contextPath}/electricity/main-admin/update-regional-details">
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
                <th>Contact Number </th>
                <th>Email</th>
                <th>CEB/LECO</th>
                <th>Employee ID</th>
                <th>Username</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Main/Regional</th>
                <th>Mobile</th>
            </tr>

            <c:if test="${empty requestScope.electricityRegionalAdmins}">
                <tr>
                    <td colspan="12"> No admins found </td>
                </tr>
            </c:if>
            <c:if test="${not empty requestScope.electricityRegionalAdmins}">
                <c:forEach items="${requestScope.electricityRegionalAdmins}" var="admin">
                    <tr>
                        <td onclick="openEditPopup('${admin.region}' ,'${admin.contactNumber}', '${admin.email}')" style="cursor: pointer">${admin.region}</td>
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

</div>
</div>
<%--<div class="GetNotification-box">--%>
<%--    <table>--%>
<%--        <thead>--%>
<%--        <tr>--%>
<%--            <th>Title</th>--%>
<%--            <th>Subject</th>--%>
<%--            <th>Message</th>--%>
<%--        </tr>--%>
<%--        </thead>--%>
<%--        <tbody>--%>
<%--        <%--%>
<%--            sendNotificationDao dao = new sendNotificationDao();--%>
<%--            List<SendNotificationModel> notifications = dao.getNotifications(SendNotificationModel.Type.ELECTRICITY);--%>
<%--            for (SendNotificationModel notification : notifications) {--%>
<%--        %>--%>
<%--        <tr>--%>
<%--            <td><%= notification.getTitle() %></td>--%>
<%--            <td><%= notification.getSubject() %></td>--%>
<%--            <td><%= notification.getMessage() %></td>--%>
<%--        </tr>--%>
<%--        <%--%>
<%--            }--%>
<%--        %>--%>
<%--        </tbody>--%>
<%--    </table>--%>
<%--</div>--%>
</body>
<script src="../../../JS/dashboard.js"></script>
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

</html>
