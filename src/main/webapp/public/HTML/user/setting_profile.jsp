<%@ page import="java.util.List" %>
<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<% String name = (String) session.getAttribute("UNAME"); %>
<%--<%--%>

<%--    Enumeration<String> attributeNames = session.getAttributeNames();--%>
<%--    while (attributeNames.hasMoreElements()) {--%>
<%--        String attributeName = attributeNames.nextElement();--%>
<%--        Object attributeValue = session.getAttribute(attributeName);--%>
<%--        System.out.println(attributeName + ": " + attributeValue);--%>
<%--    }--%>
<%--%>--%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/setting_profile.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/dashboard.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/navbar.css">
    <script type="module" src="<%= request.getContextPath() %>/public/JS/user_settings.js" defer></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <title>Account Setting</title>
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
                <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/pages/aboutUs.jsp">About Us</a></li>
                <li class="nxt-page water"><button class="button-17" type="button" onclick="toggle()">Dashboards</button></li>
                <script>
                    const electricity = "<%=session.getAttribute("electricity") != null%>"
                    const water = "<%=session.getAttribute("water") != null%>"
                    function toggle() {
                        if (electricity === 'true'){
                            window.location.href = "<%= request.getContextPath() %>/user/electricity-dashboard";
                        } else if(water === 'true') {
                            window.location.href = "<%= request.getContextPath() %>/user/water-dashboard";
                        }
                    }
                </script>
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
                            <a href="<%= request.getContextPath() %>/user/billpayment"><c:out value="${'<b> Payments </b>'}" escapeXml="false"/></a>
                            <a id="logout" href="<%= request.getContextPath() %>/logout">LogOut</a>
                        </div>
                    </a>
                </li>
            </ul>
            <img src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt="Utility Saga" class="logo">
        </div>
    </header>
</div>
<div class="profile-container">
    <section>
        <form method="post" class="edit-profile">
            <section>
                <h2>User Setting</h2>

                <label for="Name">First Name:</label>
                <input id="Name" type="text" value="${requestScope.firstname}">

                <label for="email">Last Name:</label>
                <input id="email" type="text" value="${requestScope.lastname}">

                <label for="phone">Phone Number (Home):</label>
                <input id="phone" type="text" value="${requestScope.home}">


                <button class="content-btn" type="submit" >Save Profile</button>
            </section>
        </form>
    </section>

    <section>
        <h2>Account  Settings</h2>
        <form id="password-reset" method="post" action="<%= request.getContextPath() %>/user/pwd-reset">
            <label for="password">Old Password:</label>
            <input type="password" id="password" name="password" placeholder="Enter new password" required>
            <div id="old-pwd" class="error"></div>
            <label for="new-password">New Password:</label>
            <input type="password" id="new-password" name="new-password" placeholder="Enter new password" required>
            <div id="new-pwd" class="error"></div>
            <label for="re-new-password">Confirm Password:</label>
            <input type="password" id="re-new-password" name="re-new-password" placeholder="Enter new password" required>
            <div id="re-pwd" class="error"></div>

            <div id="error-message" class="error"></div>
            <button class="content-btn" type="submit">Save Account Settings</button>
            <button class="content-btn" type="button">Edit Account Settings</button>
        </form>

    </section>

    <div class="electwatersetting">
        <section class="esection">
            <h2>Service Settings</h2>
            <h3>Electricity Settings</h3>
            <c:if test="${not empty sessionScope.electricity}">
                <div>
                    <label for="account-details__electricity">Your Current Electricity Accounts:</label>
                    <ul id="account-details__electricity">
                        <c:forEach items="${requestScope.electricity_account_list}" var="account">
                            <li id="${account.account_number}" style="color: ${account.userStatus == 'ACTIVE' ? 'green' : 'red'}">${account.account_number}</li>
                        </c:forEach>
                    </ul>

                    <button id="edit-btn_electricity" class="content-btn Delete" type="button">Deactivate</button>
                    <button id="add-btn_electricity" class="content-btn Edit" type="button">Activate</button>

                    <label for="electricityNotifications">Receive Notifications:</label>
                    <select id="electricityNotifications" name="electricityNotifications">
                        <option value="yes">Yes</option>
                        <option value="no">No</option>
                    </select>
                    <label for="electricityE-Notifications">Email Notifications:</label>
                    <select id="electricityE-Notifications" name="electricityE-Notifications">
                        <option value="yes">Enable</option>
                        <option value="no">Disable</option>
                    </select>
                    <c:if test="${not empty sessionScope.water}">
                        <form method="post" action="${pageContext.request.contextPath}/user/control-subscriptions/electricity">
                            <input type="hidden" name="action" value="unsubscribe">
                            <button class="content-btn" type="button" id="unsubscribe-electricity" style="background-color: darkred; color: white; font-weight: bold;">UnSubscribe</button>
                        </form>
                    </c:if>
                </div>
            </c:if>
            <c:if test="${empty sessionScope.electricity}">
                <label for="account-details__water">Subscribe to view accounts.</label>
                <form method="post" action="${pageContext.request.contextPath}/user/control-subscriptions/electricity">
                    <input type="hidden" name="action" value="subscribe">
                    <button class="content-btn" type="button" id="subscribe-electricity" style="background-color: green; color: white; font-weight: bold;">Subscribe</button>
                </form>
            </c:if>
        </section>
        <section class="wsection">
            <h3>Water Settings</h3>
            <c:if test="${not empty sessionScope.water}">
                <div>
                    <label for="account-details__water">Your Current Water Accounts:</label>
                    <ul id="account-details__water">
                        <c:forEach items="${requestScope.water_account_list}" var="account">
                            <li id="${account.account_number}" style="color: ${account.userStatus == 'ACTIVE' ? 'green' : 'red'}">${account.account_number}</li>
                        </c:forEach>
                    </ul>
                    <button id="edit-btn_water" type="button" class="content-btn Delete">Deactivate</button>
                    <button id="add-btn_water" type="button" class="content-btn Edit">Activate</button>
                    <label for="waterNotifications">Receive Notifications:</label>
                    <select id="waterNotifications" name="waterNotifications">
                        <option value="yes">Yes</option>
                        <option value="no">No</option>
                    </select>
                    <label for="waterE-Notifications">Email Notifications:</label>
                    <select id="waterE-Notifications" name="waterE-Notifications">
                        <option value="yes">Enable</option>
                        <option value="no">Disable</option>
                    </select>
                    <c:if test="${not empty sessionScope.electricity}">
                        <form method="post" action="${pageContext.request.contextPath}/user/control-subscriptions/water">
                            <input type="hidden" name="action" value="unsubscribe">
                            <button class="content-btn" type="button" name="action" id="unsubscribe-water" style="background-color: darkred; color: white; font-weight: bold;">UnSubscribe</button>
                        </form>
                    </c:if>
                </div>
            </c:if>
            <c:if test="${empty sessionScope.water}">
                <label for="account-details__water">Subscribe to view accounts.</label>
                <form method="post" action="${pageContext.request.contextPath}/user/control-subscriptions/water">
                    <input type="hidden" name="action" value="subscribe">
                    <button class="content-btn" type="button" id="subscribe-water" style="background-color: green; color: white; font-weight: bold;">Subscribe</button>
                </form>
            </c:if>
        </section>
    </div>

</div>
<div class="popupcontainer" id="popupcontainer">
    <div class="popup" id="popup">
        <span id="close-btn_water" class="close"><i class="fa-solid fa-xmark"></i></span>
        <h2 id="popup-header_water">Add Water Account</h2>
        <hr>
        <form method="post" action="${pageContext.request.contextPath}/user/account-activity/water" id="accountForm_water">
            <label for="accountNumber">Account Number:</label><br>
            <input type="text" id="accountActive-Number" name="accountNumber" required><br><br>
            <input type="hidden" id="action-water" name="action" required>
            <div class="btn-div">
                <button type="submit" class="wateradd-btn" id="water-add_btn"></button>
                <button type="button" class="close-btn" id="close-btn2_water">Close</button>
            </div>
        </form>
    </div>
</div>

<div class="popupcontainer2" id="popupcontainer2">
    <div class="popup2" id="popup2">
        <span class="close" id="close-btn_electricity"><i class="fa-solid fa-xmark"></i></span>
        <h2 id="popup-header_electricity">Add Electricity Account</h2>
        <hr>
        <form method="post" action="${pageContext.request.contextPath}/user/account-activity/electricity" id="accountForm_electricity">
            <label for="accountType">Select Account Type:</label><br>
            <select id="accountType" name="accountType" class="accountType">
                <option value="ceb">CEB</option>
                <option value="leco">LECO</option>
            </select><br><br>
            <label for="accountNumber">Account Number:</label><br>
            <input type="text" id="accountNumber" name="accountNumber" required><br><br>
            <input type="hidden" id="action-electricity" name="action" required>
            <div class="btn-div">
                <button type="submit" class="add-btn" id="electricity-add_btn"></button>
                <button type="button" class="close-btn" id="close-btn2_electricity">Close</button>
            </div>
        </form>
    </div>
</div>
</body>
<script>
    document.addEventListener('DOMContentLoaded', function() {

        const queryString = window.location.search;

        const urlParams = new URLSearchParams(queryString);

        if (urlParams.has('success')) {
            const successMessage = urlParams.get('success');
            if (successMessage === 'true') {
                Swal.fire({
                    icon: 'success',
                    title: 'Success',
                    text: "Your account settings have been updated successfully"
                });
            } else {
                Swal.fire({
                    icon: 'error',
                    title: 'Error',
                    text: "An error occurred while updating your account settings"
                });
            }
        }
    });
</script>
</html>