<%--
  Created by IntelliJ IDEA.
  User: visit
  Date: 9/16/2023
  Time: 9:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <link rel="stylesheet" href="../../CSS/dashboards/dashbaord.css">
    </head>
    <body>
    <nav class="navbar">
        <div class="navbar-container container">
            <input type="checkbox" name="" id="">
            <div class="hamburger-lines">
                <span class="line line1"></span>
                <span class="line line2"></span>
                <span class="line line3"></span>
            </div>
            <ul class="menu-items">
                <li><a href="#">Home</a></li>
                <li><a href="#">About</a></li>
                <li><a href="#">Contact Us</a></li>
            </ul>
            <img src="../../images/utility_saga.svg" alt="Utility Saga" class="logo">
        </div>
    </nav>

    <section class="plan2" id="plan2">
        <ul class="plan2__items">
            <li>
                <div class="plan2__item">

                </div>
                <p class="plan2__para">New Connections</p>
            </li>
            <li>
                <div class="plan2__item">

                </div>
                <p class="plan2__para">Public Complaints</p>
            </li>
            <li>
                <div class="plan2__item">

                </div>
                <p class="plan2__para">Analysis</p>
            </li>
        </ul>
    </section>
    <section class="plan2">
        <h1 class="plan2__heading">Bill Details</h1>
        <div class="element">
            <h3 class="plan2__heading3">Your Total Balance</h3>
            <p class="plan2__price">1500/=</p>
        </div>
        <div class="element">
            <button class="btn__plan2">Pay Now</button>
        </div>
        <div class="element">
            <button class="btn__plan2">View Bill</button>
        </div>
    </section>
    <section class="plan2">
        <h1 class="plan2__heading">Your Usage</h1>
        <div class="element">
            <h3 class="plan2__heading3">Select Your Account</h3>

            <div class="dropdown">
                <button class="dropbtn">Account Number</button>
                <div class="dropdown-content">
                    <a href="#">Link 1</a>
                    <a href="#">Link 2</a>
                    <a href="#">Link 3</a>
                </div>
            </div>
        </div>
    </section>
    </body>
</html>
