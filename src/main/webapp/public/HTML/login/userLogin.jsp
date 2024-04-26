<%--
  Created by IntelliJ IDEA.
  User: visit
  Date: 9/28/2023
  Time: 1:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>User Login</title>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/login/login.css">
    </head>
    <body>
        <img src="<%= request.getContextPath() %>/public/resources/purple_bar.png" alt="" class="style-bar">
        <div class="container">
            <input type="hidden" id="status" value="<%= request.getAttribute("status") %>">
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
            <section class="login">
                <form method="post" action="${pageContext.request.contextPath}/login" class="login-form" id="login-form">
                    <h1 class="login-panel__title login-panel__main">
                        Welcome To Utility Saga
                    </h1>

                    <h1 class="login-panel__title login-panel__sub">
                        <c:if test="${ not empty requestScope.errorMessage }">
                            <span id="error-message" style="color: #ff1010">
                                ${ requestScope.errorMessage }
                            </span>
                        </c:if>
                        <c:if test="${empty requestScope.errorMessage}">
                                Login
                        </c:if>
                    </h1>

                    <label for="nic" class="login-field">
                        <input type="text" placeholder="${requestScope.nic != null ? requestScope.nic : 'Enter NIC'}" id="nic" name="nic"
                               class="login-field__name">
                    </label>
                    <label for="Pwd" class="login-field">
                        <input type="password" placeholder="Enter password" id="Pwd" name="Pwd"
                               class="login-field__pwd">
                    </label>
                    <a href="./forget-password.jsp" class="fgt-pwd">
                        Forget Password
                    </a>
                    <button name="login-btn" class="login-btn">
                        Log In
                    </button>
                    <a href="<%= request.getContextPath() %>/public/HTML/login/registerForm.jsp" class="register">
                        Register
                    </a>
                </form>
            </section>
        </div>
    </body>
<script type="text/javascript">
    let status = document.getElementById("status").value;
    if (status === "success") {
        swal("Congrats", "Account created successfully", "success");
    }
</script>
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
</html>
