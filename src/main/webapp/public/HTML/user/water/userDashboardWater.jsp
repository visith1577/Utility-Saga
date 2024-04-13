<%--
  Created by IntelliJ IDEA.
  User: visithkumarapperuma
  Date: 2024-03-05
  Time: 22:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/dashboard.css">
    <script type="module" src="<%= request.getContextPath() %>/public/JS/dashboard.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<div class="navv">
    <header class="navbar">
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
                <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/pages/aboutUs.jsp">About</a></li>
                <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/user/water/water-contact.jsp">Contact Us</a></li>
                <li class="nxt-page water"><button class="button-17" type="button" onclick="toggle()">Electricity</button></li>

                <li class="img_user dropdown">
                    <a href="<%= request.getContextPath() %>/user/user-profile">
                        <button class="user-profile">
                            <%
                                // Retrieve the Image attribute from the session
                                Object image = session.getAttribute("IMAGE");

                                if (image == null) {
                            %>
                            <img alt="User" src="<%= request.getContextPath() %>/public/images/user.svg" style="width: 4vh; height: 4vh">
                            <%
                            } else {
                            %>
                            <img class="image-profile" src="data:image/jpeg;base64,<%= image %>" alt="image" style="width: 5vh; height: 5vh">
                            <%
                                }
                            %>
                        </button>
                        <div class="dropdown-content">
                            <a href="<%= request.getContextPath() %>/public/HTML/user/setting_profile.jsp"><c:out value="${'<b> Settings </b>'}" escapeXml="false"/></a>
                            <a href="<%= request.getContextPath() %>/public/HTML/user/payments.jsp"><c:out value="${'<b> Payments </b>'}" escapeXml="false"/></a>
                            <a id="logout" href="<%= request.getContextPath() %>/logout">LogOut</a>
                        </div>
                    </a>
                </li>
            </ul>
            <img src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt="Utility Saga" class="logo">
        </div>
    </header>
</div>

<main class="component-container">
    <div class="water-dashboard__header water">
        <div class="component header-component-water">
        </div>
    </div>

    <section class="plan2 water" id="plan2">
        <ul class="plan2__items">
            <li>
                <div class="plan2__item">
                    <a href="<%= request.getContextPath() %>/user/water-connection"><img src="<%= request.getContextPath() %>/public/images/four-point-connection.svg" alt="New Connection"></a>
                </div>
                <p class="plan2__para">New Connections</p>
            </li>
            <li>
                <div class="plan2__item">
                    <a href="<%= request.getContextPath() %>/user/water-public-complaint"><img src="<%= request.getContextPath() %>/public/images/history-query.svg" alt="Public Complaints"></a>
                </div>
                <p class="plan2__para">Public Complaints</p>
            </li>
            <li>
                <div class="plan2__item">
                    <a href="<%= request.getContextPath() %>/public/HTML/user/water/waterAnalysis.jsp"><img src="<%= request.getContextPath() %>/public/images/analysis.svg" alt="Analysis"></a>
                </div>
                <p class="plan2__para">Analysis</p>
            </li>
        </ul>
    </section>

    <section class="plan2 component water" style="background: lightblue">
        <h1 class="plan2__heading">Your Usage</h1>
        <div class="element">
            <h3 class="plan2__heading3">Select Your Account</h3>

            <div class="dropdown">
                <button id="dropbtn" class="dropbtn">Account Number</button>
                <div class="dropdown-content">
                    <c:forEach items="${requestScope.water_account_list}" var="account">
                        <a onclick="select_account('${account}')">${account}</a>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="graph">
            <canvas id="w-graph" class="main-graph water-graph">

            </canvas>
        </div>
    </section>

    <section class="plan2 component water" style="background: lightblue">
        <h1 class="plan2__heading">Bill Details</h1>
        <div class="element">
            <h3 class="plan2__heading3">Your Total Balance</h3>
            <p id="billAmount" class="plan2__price"></p>
            <h3 class="plan2__heading3">Due Date</h3>
            <p id="billDue" class="plan2__price"></p>
            <h3 class="plan2__heading3">Status</h3>
            <p id="billStatus" class="plan2__price"></p>
        </div>
        <div class="element">
            <button class="btn__plan2" style="background: darkblue">Pay Now</button>
        </div>
        <div class="element">
            <button class="btn__plan2" style="background: darkblue">View Bill</button>
        </div>
    </section>

    <section class="suggestion-component component water" style="background: lightblue">
        <table class="wrapper">
            <tbody>
            <tr>
                <td class="td">
                    <h1 class="suggestions-head">Daily Summary</h1>
                </td>
            </tr>
            <tr>
                <td class="td-item">
                    <div class="suggestion-1-parent">
                        <h3 class="suggestion-head" id="header1">Suggestion 1</h3>
                        <div class="description-the-water-container">
                            <span class="the-water-coming" id="text1"
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
                        <h3 class="suggestion-head" id="header2">Suggestion 1</h3>
                        <div class="description-the-water-container">

                            <span class="the-water-coming" id="text2"
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
                        <h3 class="suggestion-head" id="header3">Suggestion 1</h3>
                        <div class="description-the-water-container">
                            <span class="the-water-coming" id="text3"
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
                    <button class="see-more" style="color: darkblue; background: lightblue">See more</button>
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
    const electricity = "<%=session.getAttribute("electricity") != null%>"
    function toggle() {
        if (electricity === 'true'){
            window.location.href = "<%= request.getContextPath() %>/user/electricity-dashboard";
        } else {
            Swal.fire({
                icon: "error",
                title: "Not Subscribed",
                text: "You have not activated this service",
                footer: '<a href="#">Set up service</a>'
            });
        }
    }

    function select_account(account) {
        document.getElementById('dropbtn').textContent = account;

        fetch("<%= request.getContextPath() %>/user/my-bills?currDash=water&account=" + encodeURIComponent(account))
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {

                // Do something with the data
                document.getElementById('billAmount').textContent = data.bill['amount'];
                document.getElementById('billDue').textContent = data.bill['dueDate'];
                document.getElementById('billStatus').textContent = data.bill['status'];

                // console.log("Data:", data);
                // console.log(data.bill['amount'])
            })
            .catch(error => {
                // Handle error
                console.error('Error:', error.message);
            });
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</html>

