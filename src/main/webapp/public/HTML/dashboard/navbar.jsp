<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 1/4/2024
  Time: 4:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>NavBar</title>
    <link rel="stylesheet" href="../../CSS/dashboards/navbar.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
</head>
<body>
<header class="main-header">
    <div>
        <a href="#" class="main-header__brand">
<%--            <img src="../../images/utility_saga.svg" alt="Utility Saga" class="logo">--%>
        </a>
    </div>
    <nav class="main-nav">
        <ul class="main-nav__items">
            <li class="main-nav__item">
                <a href="#">Home</a>
            </li>
            <li class="main-nav__item">
                <a href="#">About</a>
            </li>
            <li class="main-nav__item">
                <a href="#">Contact Us</a>
            </li>
            <li class="nxt-page water"><button class="button-17" type="button" onclick="toggle()">Electricity</button></li>
            <li class="nxt-page electricity"><button class="button-17" type="button" onclick="toggle()">Water</button></li>
            <li class="main-nav__item img_user dropdown">
                <a href="">
                    <button>
                        <img alt="User" src="<%= request.getContextPath() %>/public/images/user.svg" style="width: 4vh; height: 4vh">
                    </button>
                    <div class="dropdown-content">
                        <a href="#">Link 1</a>
                        <a href="#">Link 2</a>
                        <a href="#">Link 3</a>
                    </div>
                </a>
            </li>
        </ul>
    </nav>
</header>
</body>

<script>
    let nxt = document.querySelector(".nxt-page").textContent.trim();

    function toggle() {
        const water_list = Array.from(document.getElementsByClassName("water"));
        const electricity_list = Array.from(document.getElementsByClassName("electricity"));

        if (nxt === "Electricity") {
            water_list.forEach(element => element.style.display = 'none');
            electricity_list.forEach(element => {
                if (element.style.display === 'none' || element.style.display === '') {
                    element.style.display = 'block';
                }
            });
            nxt = "Water"
        } else {
            water_list.forEach(element => {
                if (element.style.display === 'none' || element.style.display === '') {
                    element.style.display = 'block';
                }
            });
            electricity_list.forEach(element => element.style.display = 'none');
            nxt = "Electricity"
        }
    }
</script>
</html>
