<%--
Created by IntelliJ IDEA.
User: visithkumarapperuma
Date: 2023-10-31
Time: 19:52
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Document</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="<%= request.getContextPath() %>/public/CSS/login/user/electricitydashbaord.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/public/CSS/login/user/electricity_navbar_sidebar.css" rel="stylesheet">
</head>

<body>
    <div class="wrapper">

        <div class="top_navbar">
            <div class="hamburger">
                <div class="one"></div>
                <div class="two"></div>
                <div class="three"></div>
            </div>
            <div class="top_menu">
                <div class="logo">
                    <img alt="Utility Saga" height="50px" src="<%= request.getContextPath() %>/public/images/utility_saga.svg">
                </div>
                <ul>
                    <li><a href="#">
                            <img alt="Cart" src="<%= request.getContextPath() %>/public/images/cart.svg" class="item1">
                        </a></li>
                    <li><a href="#">
                            <img alt="Notification" src="<%= request.getContextPath() %>/public/images/notification-line.svg" class="item2">
                        </a></li>
                    <li><a href="#">
                            <img alt="User" src="<%= request.getContextPath() %>/public/images/user.svg" class="item3">
                        </a></li>
                </ul>
            </div>
        </div>

        <div class="content-middle">
            <div class="sidebar" id="sidebar">
                <ul>
                    <li class="list_button"><a href="electricityDashboard.jsp">
                            <span class="icon"><img alt="home" src="<%= request.getContextPath() %>/public/images/home-outline.svg" /></span>
                            <span class="title">Home<img alt="service" class="right_arrow" height="20px"
                                                         src="../images/arrow-right-s-line.svg" width="20px" /></span>
                        </a></li>
                    <li class="list_button"><a href="electricity-service.html">
                            <span class="icon"><img alt="service" src="<%= request.getContextPath() %>/public/images/service-plan.svg" /></span>
                            <span class="title">Service<img alt="service" class="right_arrow" height="20px"
                                                            src="../images/arrow-right-s-line.svg" width="20px" /></span>
                        </a></li>
                    <li class="list_button"><a href="electricity-service.html">
                            <span class="icon"><img alt="bills" src="<%= request.getContextPath() %>/public/images/bill.svg" /></span>
                            <span class="title">Bills<img alt="service" class="right_arrow" height="20px"
                                                          src="../images/arrow-right-s-line.svg" width="20px" /></span>
                        </a></li>
                    <li class="list_button"><a href="electricity-analysis.html">
                            <span class="icon"><img alt="analysis" src="<%= request.getContextPath() %>/public/images/report.svg" /></span>
                            <span class="title">Analysis<img alt="service" class="right_arrow" height="20px"
                                                             src="../images/arrow-right-s-line.svg" width="20px" /></span>
                        </a></li>
                    <li class="list_button"><a href="electricity-iot.html">
                            <span class="icon"><img alt="report" src="<%= request.getContextPath() %>/public/images/iot.svg" /></span>
                            <span class="title">IOT Devices<img alt="service" class="right_arrow" height="20px"
                                                                src="../images/arrow-right-s-line.svg" width="20px" /></span>
                        </a></li>
                    <li class="list_button"><a href="settings.html">
                            <span class="icon"> <img alt="settings" src="<%= request.getContextPath() %>/public/images/settings-outline.svg" /></span>
                            <span class="title">Settings<img alt="service" class="right_arrow" height="20px"
                                                             src="../images/arrow-right-s-line.svg" width="20px" /></span>
                        </a></li>
                </ul>
            </div>

            <div class="intro">
                <h2>Ceylon Electricity Board</h2><br>
                <img alt="Electricity Board" src="<%= request.getContextPath() %>/public/images/electricity_board.png"><br><br>
                <p>
                    The Ceylon Electricity Board - CEB,
                    is the largest electricity company in Sri Lanka.
                    With a market share of nearly 100%, it controls
                    all major functions of electricity generation,
                    transmission, distribution and retailing in Sri Lanka.
                </p>
            </div>
            <div class="about">
                <h2><b>About Electricity Companies</b></h2>
                <img src="<%= request.getContextPath() %>/public/images/MinistryofPower&Energy.png" alt="Ministry of Power & Energy">
                <img src="<%= request.getContextPath() %>/public/images/LECO.png" alt="LECO">
                <img src="<%= request.getContextPath() %>/public/images/LTL.png" alt="LTL">
            </div>
            <div class="contact-us">
                <div class="hotline">
                    <img src="<%= request.getContextPath() %>/public/images/baseline-call.svg" alt="Call">
                    <div style="text-align: center;">
                        <h2><b>Hotline:&nbsp;&nbsp;&nbsp;&nbsp;1987</b></h2>
                    </div>
                </div><br>
                <div class="notification">
                    <h2>Notifications</h2>
                    <div class="powercut">
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Inventore sapiente, repellat fugiat
                            iure veniam assumenda distincti</p><br>
                        <p>No scheduled power cuts this week.</p>
                        <div style="text-align: center;"><button class="b1">Schedule</button></div><br>
                        <p>Your electricity bill for the month: </p>
                        <h3>BALANCE: Rs.1500.00</h3>
                        <div style="text-align: center;"><button class="b1">View Bill</button></div><br>
                    </div>
                </div>
                <div class="elec-usage">
                    <h3>Electricity Usage</h3>
                    <div class="dropdown">
                        <button onmouseover="Appear()" class="dropbtn" id="selectedItem">Choose</button>
                        <div id="myDropdown" class="dropdown-content">
                            <a href="#" onclick="selectItem('September 2023')">September 2023</a>
                            <a href="#" onclick="selectItem('August 2023')">August 2023</a>
                            <a href="#" onclick="selectItem('July 2023')">July 2023</a>
                            <a href="#" onclick="selectItem('June 2023')">June 2023</a>
                            <a href="#" onclick="selectItem('May 2023')">May 2023</a>
                        </div>
                    </div>
                </div>
                <div class="suggestions">
                    <h3>View Suggestions:</h3>
                </div>
            </div>
        </div>
    </div>

</body>
<script src="<%= request.getContextPath() %>/public/JS/dashboard.js"></script>
<script>
    window.onscroll = function () {
        scrollFunction()
    }


    window.onclick = function (event) {
        if (!event.target.matches('.dropbtn')) {
            const dropdowns = document.getElementsByClassName("dropdown-content");
            let i;
            for (i = 0; i < dropdowns.length; i++) {
                const openDropdown = dropdowns[i];
                if (openDropdown.classList.contains('show')) {
                    openDropdown.classList.remove('show');
                }
            }
        }
    }

    function scrollFunction() {
        if (document.body.scrollTop > 70 || document.documentElement.scrollTop > 70) {
            document.getElementById("sidebar").style.height = "80%";
        } else {
            document.getElementById("sidebar").style.height = "85%";
        }
    }

    function Appear() {
        document.getElementById("myDropdown").classList.toggle("show");
    }

    function selectItem(itemText) {
        document.getElementById("selectedItem").innerHTML = itemText;
        document.getElementById("myDropdown").classList.remove("show");
    }
</script>

</html>