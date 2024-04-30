<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact us-Electricity</title>
    <link  type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/forms.css">
    <link  type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/contact.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/navbar.css">
    <link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/dashboard.css">
    <script type="module" src="<%= request.getContextPath() %>/public/JS/complaints.js" defer></script>
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
                <li class="menu-items-li"><a href="<%=request.getContextPath()%>/user/electricity-dashboard">Home</a></li>
                <li class="menu-items-li"><a
                        href="<%= request.getContextPath() %>/public/HTML/user/electricity/electricity-contact.jsp">Contact Us</a>
                </li>
                <li class="menu-items-li">
                    <a href="<%= request.getContextPath() %>/user/electricity-notification">
                        <span class="material-icons">notifications</span>
                    </a>
                </li>
                <li class="nxt-page water"><button class="button-17" type="button" onclick="toggle()">Dashboards</button></li>
                <script>
                    function toggle() {
                        window.location.href = "<%= request.getContextPath() %>/user/electricity-dashboard"
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
                            <img alt="User" src="<%= request.getContextPath() %>/public/images/user.svg"
                                 style="width: 4vh; height: 4vh">
                            <%
                            } else {
                            %>
                            <img class="image-profile" src="data:image/jpeg;base64,<%= image %>" alt="image"
                                 style="width: 5vh; height: 5vh">
                            <%
                                }
                            %>
                        </button>
                        <div class="dropdown-content">
                            <a href="<%= request.getContextPath() %>/user/user-settings"><c:out value="${'<b> Settings </b>'}" escapeXml="false"/></a>
                            <a href="<%= request.getContextPath() %>/user/user-settings"><c:out value="${'<b> Payments </b>'}" escapeXml="false"/></a>
                            <a id="logout" href="<%= request.getContextPath() %>/logout">LogOut</a>
                        </div>
                    </a>
                </li>
            </ul>
            <img src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt="Utility Saga" class="logo">
        </div>
    </header>
</div>
    <section>
        <div class="contact-contaiter">
            <div class="detail-container">
                <h2>Contact us</h2>
                <p>Visit our office or simply email us anytime you want.
                    If you have any questions, please feel free to contact us.
                </p>
            </br><div class="Detail"><strong>Address:</strong></br>50, Sir Chittampalam A Gardiner Mawatha,</br> Ratmalana,</br>
                    Sri Lanka 
                </div>
            </br><div class="Detail">
                    <strong>Telephone number:</strong> </br>+94 112 324 472
                </br>+94 11 2611589
                </div>
            </br><div class="Detail">
                <strong>Fax number:</strong></br>+94 11 2636449
            </div>
        </br><div class="Detail">
            <strong>Website: </strong> </br>info@ceb.lk
        </div>
            </div>
            
            <div class="form-container">
            <h3>Message us</h3>
            <form method="post" action="${pageContext.request.contextPath}/user/electricity-contact-us" class="contact-form">
                <input name="Sender-name" type="text" placeholder="Name" required>
                <input name="Sender-email" type="email" placeholder="Email" required>
                <input name="Sender-telephone" type="tel" placeholder="Telephone Number" required>
                <textarea name="Sender-message" id="" cols="30" rows="10" placeholder="Write message here" required></textarea>
                <input type="submit" value="Send" class="esend-button">
            </form>
            </div>
            <div class="map-container">
                <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d15843.302658560217!2d79.85996919775368!3d6.911437889407523!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3ae2599c3537e903%3A0xfc72042010747ed3!2sCeylon%20Electricity%20Board!5e0!3m2!1sen!2slk!4v1714393795050!5m2!1sen!2slk" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
            </div>
        </div>
    </section>
</body>
</html>