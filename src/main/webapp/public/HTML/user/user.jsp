<%--
  Created by IntelliJ IDEA.
  User: visithkumarapperuma
  Date: 2024-01-08
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<% String name = (String) session.getAttribute("UNAME"); %>
<html>
<head>
    <title>user Dashboard</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/dashboard.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/user.css">
    <script type="module" src="<%= request.getContextPath() %>/public/JS/user.js" defer></script>
</head>
<body>
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
                <li id="prof-id" class="menu-items-li"><a href="#"><%= "Welcome " + name%></a></li>
                <li class="menu-items-li"><a href="#">Home</a></li>
                <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/pages/aboutUs.jsp">About Us</a></li>
                <li class="nxt-page water"><button class="button-17" type="button" onclick="toggle()">Dashboards</button></li>
                <script>
                    const electricity = "<%=session.getAttribute("electricity") != null%>"
                    const water = "<%=session.getAttribute("water") != null%>"
                    function toggle() {
                        if (electricity === 'true'){
                            window.location.href = "<%= request.getContextPath() %>/public/HTML/user/electricity/userDashboardElectricity.jsp";
                        } else if(water === 'true') {
                            window.location.href = "<%= request.getContextPath() %>/public/HTML/user/water/userDashboardWater.jsp";
                        }
                    }
                </script>
                <li class="img_user dropdown">
                    <a href="<%= request.getContextPath() %>/public/HTML/user/user.jsp">
                        <button class="user-profile">
                            <img alt="User" src="<%= request.getContextPath() %>/public/images/user.svg" style="width: 4vh; height: 4vh">
                        </button>
                        <div class="dropdown-content">
                            <a href="<%= request.getContextPath() %>/public/HTML/user/setting_profile.jsp"><c:out value="${'<b> Settings </b>'}" escapeXml="false"/></a>
                            <a href="<%= request.getContextPath() %>/public/HTML/user/payments.jsp"><c:out value="${'<b> Payments </b>'}" escapeXml="false"/></a>
                            <a id="logout" href="<%= request.getContextPath() %>/logout">LogOut</a>
                        </div>
                    </a>
                </li>
            </ul>
            <img src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt="Utility Saga" class="logo">
        </div>
    </header>
</div>
<main class="component-container profile-component__main">
    <section class="user-profile__main card">
        <form action="" method="post" id="user-profile" class="edit-profile">
            <img id="preview" class="user-image" src="<%=request.getContextPath()%>/public/images/profile_alt.jpg" alt="Profile Image">
            <input type="file" name="image" id="imageInput" accept="image/*">
            <input type="hidden" name="id" value="1234569uid">
            <br/>
            <label class="id" for="nic">NIC</label>
            <input type="text" name="nic" value="<%= session.getAttribute("NIC") %>" class="form__field" id="nic" readonly>
            <label class="form__label" for="username"> Username: </label>
            <input type="text" name="user_name" value="<%= name %>" class="form__field" id="username" readonly>
            <label class="form-label" for="tel">Telephone:</label>
            <input type="text" name="telephone" value="" class="form__field" id="tel" readonly>
            <label class="form-label" for="email"> Email: </label>
            <input type="email" name="email" value="" class="form__field" id="email" readonly>
            <div class="button-row">
                <button type="button" id="editButton" class="button-70" onclick="onToggle()">Edit</button>
                <button type="submit" id="saveButton" class="button-70">Save</button>
            </div>
        </form>
    </section>

    <section class="user-profile__sub">
        <div class="user-profile__top card" style="padding: 0">
            <div class="tbl-header">
                <table>
                    <thead>
                    <tr>
                        <th>account No.</th>
                        <th>amount</th>
                        <th>due date</th>
                        <th>status</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div class="tbl-content">
                <table>
                    <tbody>
                        <tr>
                            <td>1223456779</td>
                            <td>2500.00</td>
                            <td>2024/2/13</td>
                            <td>pending</td>
                        </tr>
                        <tr>
                            <td>1223456779</td>
                            <td>2500.00</td>
                            <td>2024/2/13</td>
                            <td>pending</td>
                        </tr>
                        <tr>
                            <td>1223456779</td>
                            <td>2500.00</td>
                            <td>2024/2/13</td>
                            <td>pending</td>
                        </tr>
                        <tr>
                            <td>1223456779</td>
                            <td>2500.00</td>
                            <td>2024/2/13</td>
                            <td>pending</td>
                        </tr>
                        <tr>
                            <td>1223456779</td>
                            <td>2500.00</td>
                            <td>2024/2/13</td>
                            <td>pending</td>
                        </tr>
                        <tr>
                            <td>1223456779</td>
                            <td>2500.00</td>
                            <td>2024/2/13</td>
                            <td>pending</td>
                        </tr>
                        <tr>
                            <td>1223456779</td>
                            <td>2500.00</td>
                            <td>2024/2/13</td>
                            <td>pending</td>
                        </tr>
                        <tr>
                            <td>1223456779</td>
                            <td>2500.00</td>
                            <td>2024/2/13</td>
                            <td>pending</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="user-profile__bottom card">

        </div>
    </section>
</main>
<script>
    fetch("<%= request.getContextPath() %>" + "/user-profile", {
        method: 'GET'
    }).then(response => response.json())
        .then(userDetails => {
            console.log(userDetails);
            const email = document.getElementById("email");
            const tel = document.getElementById("tel");

            email.value = userDetails.email;
            tel.value = userDetails.mobile;
        })
        .catch(error => console.error('Error fetching data:', error));
</script>
</body>
</html>

