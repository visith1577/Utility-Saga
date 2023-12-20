<%--
  Created by IntelliJ IDEA.
  User: visit
  Date: 9/28/2023
  Time: 1:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP - Hello World</title>
        <link rel="stylesheet" href="../CSS/login/login.css">
    </head>
    <body>
        <img src="../resources/purple_bar.png" alt="" class="style-bar">
        <div class="container">
            <aside class="side-panel">
                <h1 class="side-panel__title side-panel__main">
                    UTILITY SAGA
                </h1>
                <h2 class="side-panel__title side-panel__sub">
                    Centralized platform that offers a combination of water, fuel, <br>
                    and electricity services in one place.
                </h2>
                <img src="../resources/solar_login.png" width="400px" alt="utility-saga">
            </aside>
            <%--            <div class="empty-div">--%>
            <%--            </div>--%>
            <section class="login">
                <div class="login-form">
                    <h1 class="login-panel__title login-panel__main">
                        Welcome To Utility Saga
                    </h1>

                    <h1 class="login-panel__title login-panel__sub">
                        Login
                    </h1>

                    <label for="Uname" class="login-field">
                        <input type="text" placeholder="Enter username" id="Uname" name="Uname"
                               class="login-field__name">
                    </label>
                    <label for="Pwd" class="login-field">
                        <input type="password" placeholder="Enter password" id="Pwd" name="Pwd"
                               class="login-field__pwd">
                    </label>
                    <a href="#" class="fgt-pwd">
                        Forget Password
                    </a>
                    <button class="login-btn">
                        Log In
                    </button>
                    <a href="#" class="register">
                        Register
                    </a>
                </div>
            </section>
        </div>
    </body>
</html>
