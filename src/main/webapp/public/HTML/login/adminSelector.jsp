<%--
  Created by IntelliJ IDEA.
  User: visit
  Date: 9/28/2023
  Time: 2:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>JSP - Hello World</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/login/login.css">
        <style>
            .login-selector__btn {
                height: 60px;
                width: 280px;
                border: none;
                border-radius: 24px;
                background-color: #121D80;
                font-weight: bold;
                font-size: 24px;
                color: #E9F0FF;
                padding: 10px;
                margin-bottom: 40px;
            }
        </style>
    </head>
    <body>
        <img src="<%= request.getContextPath() %>/public/resources/purple_bar.png" alt="" class="style-bar">
        <div class="container">
            <aside class="side-panel">
                <h1 class="side-panel__title side-panel__main">
                    UTILITY SAGA
                </h1>
                <h2 class="side-panel__title side-panel__sub">
                    Centralized platform that offers a combination of water, fuel, <br>
                    and electricity services in one place.
                </h2>
                <img src="<%= request.getContextPath() %>/public/resources/login_img.png" width="400px" alt="utility-saga">
            </aside>
            <%--            <div class="empty-div">--%>
            <%--            </div>--%>
            <section class="login-selector">
                <div class="login-form" style="position: absolute; top: 50px; width: 600px">
                    <h1 class="login-panel__title login-panel__sub" style="margin-bottom: 15px;">
                        Select Admin Space
                    </h1>
                    <button class="login-selector__btn super_admin-btn">
                        <a href="administratorLogin.jsp"> Main Administrator </a>
                    </button>

                    <button class="login-selector__btn admin-btn">
                        <a href="electricityLogin.jsp"> Electricity </a>
                    </button>

                    <button class="login-selector__btn cst-btn">
                        <a href="waterLogin.jsp">Water</a>
                    </button>

                    <button class="login-selector__btn cmp-btn">
                        <a href="fuelLogin.jsp">Fuel</a>
                    </button>
                </div>
            </section>
        </div>
    </body>
</html>
