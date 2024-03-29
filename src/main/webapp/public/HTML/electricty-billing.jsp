<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Document</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="../CSS/login/user/electrcity-billing.css" rel="stylesheet">
    <link href="../CSS/login/user/electricity_navbar_sidebar.css" rel="stylesheet">
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
                    <img alt="Utility Saga" height="50px" src="../images/utility_saga.svg">
                </div>
                <ul>
                    <li><a href="#">
                            <img alt="Cart" src="../images/cart.svg" class="item1">
                        </a></li>
                    <li><a href="#">
                            <img alt="Notification" src="../images/notification-line.svg" class="item2">
                        </a></li>
                    <li><a href="#">
                            <img alt="User" src="../images/user.svg" class="item3">
                        </a></li>
                </ul>
            </div>
        </div>

        <div class="content-middle" id="content-middle">
            <div class="sidebar" id="sidebar">
                <ul>
                    <li class="list_button"><a href="electricityDashboard.jsp">
                            <span class="icon"><img alt="home" src="../images/home-outline.svg" /></span>
                            <span class="title">Home<img alt="service" class="right_arrow" height="20px"
                                                         src="../images/arrow-right-s-line.svg" width="20px" /></span>
                        </a></li>
                    <li class="list_button"><a href="./electricity-service.html">
                            <span class="icon"><img alt="service" src="../images/service-plan.svg" /></span>
                            <span class="title">Service<img alt="service" class="right_arrow" height="20px"
                                                            src="../images/arrow-right-s-line.svg" width="20px" /></span>
                        </a></li>
                    <li class="list_button"><a href="./electricty-billing.html">
                            <span class="icon"><img alt="bills" src="../images/bill.svg" /></span>
                            <span class="title">Bills<img alt="service" class="right_arrow" height="20px"
                                                          src="../images/arrow-right-s-line.svg" width="20px" /></span>
                        </a></li>
                    <li class="list_button"><a href="./electricity-analysis.html">
                            <span class="icon"><img alt="analysis" src="../images/report.svg" /></span>
                            <span class="title">Analysis<img alt="service" class="right_arrow" height="20px"
                                                             src="../images/arrow-right-s-line.svg" width="20px" /></span>
                        </a></li>
                    <li class="list_button"><a href="./electricity-iot.html">
                            <span class="icon"><img alt="report" src="../images/iot.svg" /></span>
                            <span class="title">IOT Devices<img alt="service" class="right_arrow" height="20px"
                                                                src="../images/arrow-right-s-line.svg" width="20px" /></span>
                        </a></li>
                    <li class="list_button"><a href="./electricity-contact.html">
                            <span class="icon"> <img alt="settings" src="../images/call.svg" /></span>
                            <span class="title">Contact Us<img alt="service" class="right_arrow" height="20px"
                                                               src="../images/arrow-right-s-line.svg" width="20px" /></span>
                        </a></li>
                    <li class="list_button"><a href="../pages/settings.html">
                            <span class="icon"> <img alt="settings" src="../images/settings-outline.svg" /></span>
                            <span class="title">Settings<img alt="service" class="right_arrow" height="20px"
                                                             src="../images/arrow-right-s-line.svg" width="20px" /></span>
                        </a></li>
                </ul>
            </div>

            <div class="intro">
                <h3>Tariff System</h3><br>
                <p>You can now select your Bill Calcualtion method <br>
                    Fixed Tariff &nbsp;&nbsp;|&nbsp;&nbsp;Variable Tariff <br><br>
                    <a href="#">Learn More about them</a><br><br>
                <div class="tariff">
                    <a href="">
                        <div class="tar" id="tar1">Fixed Tariff</div>
                    </a>
                    <a href="">
                        <div class="tar" id="tar2">Variable Tariff</div>
                    </a>
                </div>
            </div>
            <div class="calculator">
                <div class="prices">
                    <div class="price">
                        <h3>Bill Calcualtor</h3><br>
                        <form action="">
                            <label for="units">Units: </label>
                            <input type="text" name=""><br><br>
                            <label for="days">Days: </label>
                            <input type="text" name=""><br><br>
                            <button class="btn">Generate</button>
                        </form>
                    </div>
                    <div class="price" id="pr">
                        <h3>Estimated Price will be: </h3>
                    </div>
                </div>

                <div class="acc-balance">
                    <h3>Your account balance is:</h3><br><br>
                    <a href="">
                        <div class="pay">Pay</div>
                    </a>
                </div>
            </div>

            <div class="monthly">
                <h3>Monthly Bills</h3><br>
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
                <br><br>
                <div class="buttons">
                    <a href="">
                        <div class="button-child">Download</div>
                    </a>
                    <a href="">
                        <div class="button-child">Pay</div>
                    </a>
                </div>
            </div>

            <div class="summary">

            </div>

            <div class="status">
                <h3>Payment Status</h3><br>
                <table>
                    <tr>
                        <th>Month</th>
                        <th>Bill Amount</th>
                        <th>Status</th>
                    </tr>
                    <tr>
                        <td>September</td>
                        <td>1500.00</td>
                        <td>Paid</td>
                    </tr>
                    <tr>
                        <td>August</td>
                        <td>1500.00</td>
                        <td>Paid</td>
                    </tr>
                    <tr>
                        <td>July</td>
                        <td>1500.00</td>
                        <td>Paid</td>
                    </tr>
                    <tr>
                        <td>June</td>
                        <td>1500.00</td>
                        <td>Paid</td>
                    </tr>
                </table>
            </div>

        </div>
    </div>

</body>
<script src="../JS/dashboard.js"></script>
<script>
    window.onscroll = function () {
        scrollFunction()
    }


    window.onclick = function (event) {
        if (!event.target.matches('.dropbtn')) {
            var dropdowns = document.getElementsByClassName("dropdown-content");
            var i;
            for (i = 0; i < dropdowns.length; i++) {
                var openDropdown = dropdowns[i];
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