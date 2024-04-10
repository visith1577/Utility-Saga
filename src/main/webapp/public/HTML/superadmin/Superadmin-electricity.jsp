<%--
  Created by IntelliJ IDEA.
  User: Liviru Weerasinghe
  Date: 4/10/2024
  Time: 7:45 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="model.ElectricityAdminModel" %>
<%@ page import="DAO.dao.ElectricityAdminDAO" %>
<%@ page import="java.util.List" %>

<%List<ElectricityAdminModel> admins = new ElectricityAdminDAO().getElectricityAdmins();%>

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
                        <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/superadmin/Superadmin-electricity.jsp">Electricity</a></li>
                        <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/superadmin/Superadmin-water.jsp">Water</a></li>
                        <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/superadmin/Superadmin-solar.jsp">Solar</a></li>
                        <li class="menu-items-li"><a href="#">Logout</a></li>
                    </ul>
                    <img src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt="Utility Saga" class="logo">
                </div>
            </header>
        </div>

        <div class="middle" id="middle">
            <h4 class="title">Electricity Admins</h4>
            <div class="buttons">
                <button class="button" onclick="openPopup()">Add</button>
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
                        <div class="form-button">
                            <button type="submit" class="buttons">Add Admin</button>
                            <button  onclick="closePopup()" class="buttons">Close</button>
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
                </tr>
                <% for (ElectricityAdminModel admin : admins) { %>
                <tr>
                    <td><%= admin.getRegion() %></td>
                    <td><%= admin.getContactNumber() %></td>
                    <td><%= admin.getEmail() %></td>
                    <td><%= admin.getPassword() %></td>
                    <td><%= admin.getUtilityType() %></td></tr>
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

    function openPopup() {
        document.getElementById("popupForm").style.display = "block";
    }

    function closePopup() {
        document.getElementById("popupForm").style.display = "none";
    }
</script>

</html>