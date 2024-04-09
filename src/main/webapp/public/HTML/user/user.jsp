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
                            window.location.href = "<%= request.getContextPath() %>/user/electricity-dashboard";
                        } else if(water === 'true') {
                            window.location.href = "<%= request.getContextPath() %>/user/water-dashboard";
                        }
                    }
                </script>
                <li class="img_user dropdown">
                    <a href="<%= request.getContextPath() %>/user/user-profile">
                        <button class="user-profile">
                            <img alt="User" src="<%= request.getContextPath() %>/public/images/user.svg" style="width: 4vh; height: 4vh">
                        </button>
                        <div class="dropdown-content">
                            <a href="<%= request.getContextPath() %>/user/user-settings"><c:out value="${'<b> Settings </b>'}" escapeXml="false"/></a>
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
        <form action="${pageContext.request.contextPath}/user-profile" method="post" id="user-profile" class="edit-profile" enctype='multipart/form-data'>
            <div id="canvasContainer">
                <canvas id="previewCanvas" class="image-canvas" width="300" height="300"></canvas>
            </div>
            <input type="file" name="image" id="imageInput" accept="image/*">
            <script>
                document.addEventListener("DOMContentLoaded", function() {
                    const canvas = document.getElementById('previewCanvas');
                    const context = canvas.getContext('2d');


                    function draw_default_image() {
                        const defaultImage = new Image();
                        // Set the source of the default image
                        defaultImage.src = "<%=request.getContextPath()%>/public/images/profile_alt.jpg";

                        // Draw the default image onto the canvas when it's loaded
                        defaultImage.onload = function() {
                            context.drawImage(defaultImage, 0, 0, canvas.width, canvas.height);
                        };
                    }

                    draw_default_image();

                    function draw_selected_image(file) {
                        const reader = new FileReader();
                        reader.onload = function(event) {
                            const selectedImage = new Image();
                            selectedImage.src = event.target.result;
                            selectedImage.onload = function() {
                                context.clearRect(0, 0, canvas.width, canvas.height);
                                context.drawImage(selectedImage, 0, 0, canvas.width, canvas.height);
                            };
                        };
                        reader.readAsDataURL(file);
                    }

                    document.getElementById('imageInput').addEventListener('change', function(event) {
                        const file = event.target.files[0];
                        if (file) {
                            draw_selected_image(file);
                        } else {
                            context.clearRect(0, 0, canvas.width, canvas.height);
                            draw_default_image();
                        }
                    });
                });
            </script>
            <input type="hidden" name="id" value="1234569uid">
            <br/>
            <label class="id" for="nic">NIC</label>
            <input type="text" name="nic" value="<%= session.getAttribute("NIC") %>" class="form__field" id="nic" readonly required>
            <label class="form__label" for="username"> Username: </label>
            <input type="text" name="user_name" value="<%= name %>" class="form__field" id="username" readonly required>
            <div class="error"></div>
            <label class="form-label" for="tel">Telephone:</label>
            <input type="text" name="telephone" value="<%= session.getAttribute("TELEPHONE") %>" class="form__field" id="tel" readonly required>
            <div class="error"></div>
            <label class="form-label" for="email"> Email: </label>
            <input type="email" name="email" value="<%= session.getAttribute("EMAIL") %>" class="form__field" id="email" readonly required>
            <div class="error"></div>
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
                    <c:if test="${not empty sessionScope.electricity}">
                        <c:forEach items="${requestScope.electricity_account_list}" var="account">
                            <tr>
                                <td>${account.account_number}</td>
                                <td>${account.amount}</td>
                                <td>${account.dueDate}</td>
                                <td>${account.status}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${not empty sessionScope.water}">
                        <c:forEach items="${requestScope.water_account_list}" var="account">
                            <tr>
                                <td>${account.account_number}</td>
                                <td>${account.amount}</td>
                                <td>${account.dueDate}</td>
                                <td>${account.status}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="user-profile__bottom card">
        </div>
    </section>
</main>
<script type="module" src="<%= request.getContextPath() %>/public/JS/user.js" defer></script>
</body>
</html>

