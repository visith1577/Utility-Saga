<%--
  Created by IntelliJ IDEA.
  User: visit
  Date: 9/11/2023
  Time: 10:58 AM
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<!DOCTYPE html>
<html>
    <head>
        <title>Registration page</title>
        <meta charset="utf-8"/>
        <meta name="viewport" content="initial-scale=1, width=device-width"/>

        <link rel="stylesheet" href="../CSS/global.css"/>
        <link rel="stylesheet" href="../CSS/login/styles.css"/>
    </head>
    <body>
        <main class="container">
            <section></section>
            <section class="reg-form">
                <div class="right-head">
                    <h1>
                        Welcome To Utility Saga
                    </h1>
                </div>
                <form method="POST">
                    <label for="f-name" class="form-label">First Name:</label><br>
                    <input type="text" id="f-name" name="name" class="form-in"><br>

                    <label for="l-name" class="form-label">last Name:</label><br>
                    <input type="text" id="l-name" name="name" class="form-in"><br>

                    <label for="email" class="form-label">Email:</label><br>
                    <input type="email" id="email" name="email" class="form-in"><br>

                    <label for="password" class="form-label">Password:</label><br>
                    <input type="password" id="password" name="password" class="form-in"><br>

                    <label for="re-password" class="form-label">Re-enter Password:</label><br>
                    <input type="password" id="re-password" name="password" class="form-in"><br>

                    <label for="telephone" class="form-label">Telephone Number:</label><br>
                    <input type="tel" id="telephone" name="telephone" class="form-in"
                           pattern="[0-9]{3}-[0-9]{2}-[0-9]{3}"><br>

                    <label for="address" class="form-label">Address:</label><br>
                    <input type="text" id="address" name="address" class="form-in addr"><br>
                    <div class="submit-btn">
                        <input type="submit" value="Register">
                    </div>
                </form>

            </section>
            <script>
                let nxtBtn = document.getElementsByClassName("nxt-btn")
                if (nxtBtn) {
                    nxtBtn.addEventListener("click", function (e) {
                        // Please sync "Login" to the project
                    });
                }
            </script>
        </main>
    </body>
</html>

