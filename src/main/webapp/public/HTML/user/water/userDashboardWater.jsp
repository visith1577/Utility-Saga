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
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
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
                <li class="menu-items-li">
                    <a href="<%= request.getContextPath() %>/user/water-notification">
                        <span class="material-icons">notifications</span>
                    </a>
                </li>
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
                            <a href="<%= request.getContextPath() %>/user/user-settings"><c:out value="${'<b> Settings </b>'}" escapeXml="false"/></a>
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
                    <a href="<%= request.getContextPath() %>/user/water-analytics"><img src="<%= request.getContextPath() %>/public/images/analysis.svg" alt="Analysis"></a>
                </div>
                <p class="plan2__para">Analysis</p>
            </li>
        </ul>
    </section>

    <section class="plan2 component water" style="background: lightblue">
        <h1 id="graph-head" class="plan2__heading">Your Usage</h1>
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
        <table class="info-table">
            <tr>
                <td><h3 class="plan2__heading3">Address</h3></td>
                <td><p id="Address" class="plan2__price"></p></td>
            </tr>
            <tr>
                <td><h3 class="plan2__heading3">Your Total Balance</h3></td>
                <td><p id="billAmount" class="plan2__price"></p></td>
            </tr>
            <tr>
                <td><h3 class="plan2__heading3">Due Date</h3></td>
                <td><p id="billDue" class="plan2__price"></p></td>
            </tr>
            <tr>
                <td><h3 class="plan2__heading3">Monthly Bill Status</h3></td>
                <td><p id="billStatus" class="plan2__price"></p></td>
            </tr>
        </table>
        <div class="element-btn">
            <button id="payNowButton" class="btn__plan2 element-pay" style="background: darkblue" disabled>Pay Now</button>
        </div>
    </section>

    <section class="suggestion-component component water" style="background: lightblue; display: none">
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
                        <h3 class="suggestion-head" id="header1">Daily Consumption Analysis</h3>
                        <div class="description-the-water-container">
                            <span class="the-water-coming" id="report1"></span>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="td-item">
                    <div class="suggestion-1-parent">
                        <h3 class="suggestion-head" id="header2">Monthly Consumption Forecast</h3>
                        <div class="description-the-water-container">
                            <span class="the-water-coming" id="report2"></span>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="td-item">
                    <div class="suggestion-1-parent">
                        <h3 class="suggestion-head" id="header3">Water-saving Recommendations</h3>
                        <div class="description-the-water-container">
                            <span class="the-water-coming" id="report3"></span>
                        </div>
                    </div>
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
    let ch = null;
    let payNowButton = document.getElementById('payNowButton');
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

    const w_ctx = document.getElementById('w-graph');

    function select_account(account) {
        document.getElementById('dropbtn').textContent = account;
        if (ch != null) {
            ch.destroy()
        }

        fetch("<%= request.getContextPath() %>/user/my-bills?currDash=water&account=" + encodeURIComponent(account))
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {

                document.getElementById('Address').textContent = data.bill.region
                document.getElementById('billAmount').textContent = data.bill.amount
                document.getElementById('billDue').textContent = data.bill.dueDate
                document.getElementById('billStatus').textContent = data.bill.status

                if (account) {
                    if (data.bill.status === "PAID") {
                        document.getElementById('billStatus').style.color = "green";
                        payNowButton.style.background = "grey";
                        payNowButton.disabled = true;
                    } else if (data.bill.status === "PENDING"){
                        document.getElementById('billStatus').style.color = "orange";
                        payNowButton.style.background = "green";
                        payNowButton.disabled = false;
                    } else {
                        document.getElementById('billStatus').style.color = "red";
                        payNowButton.style.background = "green";
                        payNowButton.disabled = false;
                    }
                } else {
                    let payNowButton = document.getElementById('payNowButton');
                    payNowButton.style.background = "grey";
                    payNowButton.disabled = true;
                }

                if (data.report !== null) {
                    document.getElementById('report1').textContent = data.report["Daily Consumption Analysis"];
                    document.getElementById('report2').textContent = data.report["Monthly Consumption Forecast"];
                    document.getElementById('report3').textContent = data.report["Energy-saving Recommendations"];
                }

                const suggestionComponent = document.querySelector('.suggestion-component');


                if (data.data_list_daily === null) {
                    document.getElementById('graph-head').textContent = "No data available meter not installed or not active";
                    suggestionComponent.style.display = "none";
                    return;
                } else {
                    document.getElementById('graph-head').textContent = "Your usage pattern for " + data.data_list_daily[0].date;
                    suggestionComponent.style.display = "block";
                }

                const dataset = {
                    label: 'Hourly Fluctuation',
                    data: data.data_list_daily.slice(1).map((d, i) => d.data - data.data_list_daily[i].data),
                    borderColor: 'rgb(255, 99, 132)',
                    backgroundColor: 'rgba(255, 99, 132, 0.2)',
                };


                const data_graph = {
                    labels: data.data_list_daily.slice(1).map(d => d.time),
                    datasets: [dataset],
                };

                const config = {
                    type: 'line',
                    data: data_graph,
                    options: {
                        responsive: true, // Makes the chart responsive
                        title: { // Add a title
                            display: true,
                            text: 'water meter reading fluctuations today',
                        },
                        plugins: {
                            // Add legend labels for each dataset
                            legend: {
                                display: true,
                                labels: {
                                    // Customize legend text color
                                    fontColor: 'black',
                                }
                            }
                        }
                    },
                };
                ch = new Chart(w_ctx, config);
            })
            .catch(error => {
                // Handle error
                console.error('Error:', error.message);
            });
    }
</script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</html>

