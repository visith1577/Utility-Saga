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
        <link rel="stylesheet" href="../../CSS/dashboards/dashboard.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
    </head>
    <body>
    <nav class="navbar">
        <div class="navbar-container container">
            <label for="hamburger"></label>
            <input type="checkbox" name="hamburger" id="hamburger">
            <div class="hamburger-lines">
                <span class="line line1"></span>
                <span class="line line2"></span>
                <span class="line line3"></span>
            </div>
            <ul class="menu-items">
                <li class="menu-items-li"><a href="#">Home</a></li>
                <li class="menu-items-li"><a href="#">About</a></li>
                <li class="menu-items-li"><a href="#">Contact Us</a></li>
                <li class="nxt-page water"><button class="button-17" type="button" onclick="toggle()">Electricity</button></li>
                <li class="nxt-page electricity"><button class="button-17" type="button" onclick="toggle()">Water</button></li>
                <li class="img_user dropdown">
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
            <img src="../../images/utility_saga.svg" alt="Utility Saga" class="logo">
        </div>
    </nav>

    <main class="component-container">
        <header class="water-dashboard__header">
            <div class="component header-component">
            </div>
        </header>

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
        <section class="plan2 component">
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
        <section class="plan2 component water">
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
            <div class="graph">

            </div>
        </section>
        <section class="plan2 component electricity">
            <h1 class="plan2__heading">Your Usage</h1>
            <div class="element">
                <h3 class="plan2__heading3">Select Your Account</h3>

                <div class="dropdown">
                    <button class="dropbtn">Account Number</button>
                    <div class="dropdown-content">
                        <a href="#">Link elec</a>
                        <a href="#">Link 2</a>
                        <a href="#">Link 3</a>
                    </div>
                </div>
            </div>
            <div class="graph">

            </div>
        </section>
        <section class="suggestion-component component">
            <table class="wrapper">
                <tbody>
                <tr>
                    <td class="td">
                        <h1 class="suggestions-head">Suggestions</h1>
                    </td>
                </tr>
                <tr>
                    <td class="td-item">
                        <div class="suggestion-1-parent">
                            <h3 class="suggestion-head">Suggestion 1</h3>
                            <div class="description-the-water-container">
                                <span class="description">Description: </span>
                                <span class="the-water-coming"
                                >The water coming out of our taps is consistently
                        discolored, appearing brown and murky. This is concerning as
                        it affects the usability and safety of the water for
                        drinking, cooking, and general household use.</span
                                >
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="td-item">
                        <div class="suggestion-1-parent">
                            <h3 class="suggestion-head">Suggestion 1</h3>
                            <div class="description-the-water-container">
                                <span class="description">Description: </span>
                                <span class="the-water-coming"
                                >The water coming out of our taps is consistently
                        discolored, appearing brown and murky. This is concerning as
                        it affects the usability and safety of the water for
                        drinking, cooking, and general household use.</span
                                >
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="td-item">
                        <div class="suggestion-1-parent">
                            <h3 class="suggestion-head">Suggestion 1</h3>
                            <div class="description-the-water-container">
                                <span class="description">Description: </span>
                                <span class="the-water-coming"
                                >The water coming out of our taps is consistently
                        discolored, appearing brown and murky. This is concerning as
                        it affects the usability and safety of the water for
                        drinking, cooking, and general household use.</span
                                >
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="td-btn">
                        <button class="see-more">See more</button>
                    </td>
                </tr>
                </tbody>
            </table>
        </section>
        <footer>

        </footer>
    </main>
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
