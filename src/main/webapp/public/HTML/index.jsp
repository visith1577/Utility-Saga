<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP - Hello World</title>
        <link rel="stylesheet" href="../CSS/login/user/login.css">
    </head>
    <body>
        <div class="container">
            <aside class="side-panel">
                <h1 class="side-panel__title side-panel__main">
                    UTILITY SAGA
                </h1>
                <h2 class="side-panel__title side-panel__sub">
                    Centralized platform that offers a combination of water, fuel, <br>
                    and electricity services in one place.
                </h2>
                <img src="../resources/login_img.png" width="400px" alt="utility-saga">
            </aside>
            <section class="login">
                <h3 class="login-panel__title login-panel__main">
                    Welcome To Utility Saga
                </h3>

                <h3 class="login-panel__title login-panel__main">
                    login
                </h3>

                <label for="Uname">
                    <input type="text" placeholder="Enter username" id="Uname" name="Uname" class="login-field">
                </label>
                <label for="Pwd">
                    <input type="password" placeholder="Enter password" id="Pwd" name="Pwd" class="login-field">
                </label>
                <button>
                    Login
                </button>
                <a>
                    New user: Register
                </a>
            </section>
        </div>
    </body>
</html>