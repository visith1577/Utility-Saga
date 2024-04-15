<%--
  Created by IntelliJ IDEA.
  User: Liviru Weerasinghe
  Date: 4/10/2024
  Time: 7:45 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="model.ElectricityAdminModel" %>
<%@ page import="DAO.dao.ElectricityAdminDAO" %>
<%@ page import="DAO.dao.WaterAdminDAO" %>
<%@ page import="java.util.List" %>

<%List<ElectricityAdminModel> admins = new ElectricityAdminDAO().getElectricityAdmins(ElectricityAdminModel.Role.MAIN);%>
<%List<ElectricityAdminModel> wadmins = new WaterAdminDAO().getWaterAdmins(ElectricityAdminModel.Role.MAIN);%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Document</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="../../CSS/superadmin/Superadmin-editadmins.css" rel="stylesheet">
    <link rel="stylesheet" href="../../CSS/dashboards/dashboard.css">
    <link rel="stylesheet" href="../../CSS/forms.css">

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
                        <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/superadmin/Superadmin-electricity-water.jsp">Electricity/Water</a></li>
                        <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/superadmin/Superadmin-solar.jsp">Solar</a></li>
                        <li class="menu-items-li"><a id="logout" href="<%= request.getContextPath() %>/logout">LogOut</a></li>
                    </ul>
                    <img src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt="Utility Saga" class="logo">
                </div>
            </header>
        </div>

        <div class="middle" id="middle">
            <h2 class="title">Electricity Admins</h2>
            <div class="buttons">
                <button class="button" onclick="openPopup('popupForm')">Add Electricity Admins</button>
              </div>
            <div class="popup-form" id="popupForm" style="display: none;">
                <div id="popupContainer" class="popup-container">
                    <h2 class="popup-title">Add Electricity Admin</h2>
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

                        <label for="uname">User Name:</label>
                        <input type="text" name="uname" id="uname" required>

                        <label for="fname">First Name:</label>
                        <input type="text" name="fname" id="fname" required>

                        <label for="lname">Last Name:</label>
                        <input type="text" name="lname" id="lname" required>

                        <label for="role">Admin Type:</label>
                        <select name="role" id="role" required>
                            <option value="MAIN">Main</option>
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

            <table class="table">
                <tr>
                    <th>Region</th>
                    <th>Contact Number</th>
                    <th>Email</th>
                    <th>Password</th>
                    <th>CEB/LECO</th>
                    <th>Employee ID</th>
                    <th>Username</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Main/Regional</th>
                    <th>Mobile</th>
                </tr>
                <% for (ElectricityAdminModel admin : admins) { %>
                <tr>
                    <td><%= admin.getRegion() %></td>
                    <td><%= admin.getContactNumber() %></td>
                    <td><%= admin.getEmail() %></td>
                    <td><%= admin.getPassword() %></td>
                    <td><%= admin.getUtilityType() %></td>
                    <td><%= admin.getEmpId() %></td>
                    <td><%= admin.getUname() %></td>
                    <td><%= admin.getFirstname() %></td>
                    <td><%= admin.getLastname() %></td>
                    <td><%= admin.getRole() %></td>
                    <td><%= admin.getMobile() %></td>
                </tr>

                <% } %>
        
            </table>

            <h2 class="title">Water Admins</h2>
            <div class="buttons">
                <button class="button" onclick="openPopup('wpopupForm')">Add Water Admins</button>
            </div>
            <div class="popup-form" id="wpopupForm" style="display: none;">
                <div id="wpopupContainer" class="popup-container">
                    <h2 class="popup-title">Add Electricity Admin</h2>
                    <form id="waddForm" method="POST" action="${pageContext.request.contextPath}/waterAdmin">
                        <label for="wregion">Region:</label>
                        <input type="text" name="wregion" id="wregion" required>

                        <label for="wcontact">Contact Number:</label>
                        <input type="text" name="wcontact" id="wcontact" required>

                        <label for="wemail">Email:</label>
                        <input type="email" name="wemail" id="wemail" required>

                        <label for="wpassword">Password:</label>
                        <input type="password" name="wpassword" id="wpassword" required>

                        <label for="wempid">Employee ID:</label>
                        <input type="text" name="wempid" id="wempid" required>

                        <label for="wuname">User Name:</label>
                        <input type="text" name="wuname" id="wuname" required>

                        <label for="wfname">First Name:</label>
                        <input type="text" name="wfname" id="wfname" required>

                        <label for="wlname">Last Name:</label>
                        <input type="text" name="wlname" id="wlname" required>

                        <label for="wrole">Admin Type:</label>
                        <select name="wrole" id="wrole" required>
                            <option value="MAIN">Main</option>
                        </select>

                        <label for="wmobile">Mobile:</label>
                        <input type="text" name="wmobile" id="wmobile" required>

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
                    <th>Password</th>
                    <th>Employee ID</th>
                    <th>Username</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Main/Regional</th>
                    <th>Mobile</th>
                </tr>
                <% for (ElectricityAdminModel admin : wadmins) { %>
                <tr>
                    <td><%= admin.getRegion() %></td>
                    <td><%= admin.getContactNumber() %></td>
                    <td><%= admin.getEmail() %></td>
                    <td><%= admin.getPassword() %></td>
                    <td><%= admin.getEmpId() %></td>
                    <td><%= admin.getUname() %></td>
                    <td><%= admin.getFirstname() %></td>
                    <td><%= admin.getLastname() %></td>
                    <td><%= admin.getRole() %></td>
                    <td><%= admin.getMobile() %></td>
                </tr>

                <% } %>

            </table>

            </div>

            </div>
        </div>
    </div>

</body>
<script src="../../JS/dashboard.js"></script>
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