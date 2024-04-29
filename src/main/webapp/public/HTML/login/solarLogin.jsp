<%--
  Created by IntelliJ IDEA.
  User: visit
  Date: 9/28/2023
  Time: 1:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Solar Company Login</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/login/login.css">
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
        <img src="<%= request.getContextPath() %>/public/resources/solar_login.png" width="400px" alt="utility-saga">
    </aside>
    <%--            <div class="empty-div">--%>
    <%--            </div>--%>
    <section class="login">
        <form action="${pageContext.request.contextPath}/company-login" method="post" class="login-form">
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

            <label for="Uname" class="login-field">
                <input type="text" placeholder="Enter Region id:" id="Uname" name="userName"
                       class="login-field__name">
            </label>
            <label for="Pwd" class="login-field">
                <input type="password" placeholder="Enter password" id="Pwd" name="password"
                       class="login-field__pwd">
            </label>
            <a href="<%= request.getContextPath() %>/public/HTML/login/forget-password-electricity.jsp" class="fgt-pwd" style="color: black">
                Forget Password
            </a>

              <button type="submit" class="login-btn">
                Login
            </button>

        </form>
    </section>
</div>


<li><a href="<%=request.getContextPath()%>/item/all" class="nav-link">Show All Items</a></li>
</body>
</html>
