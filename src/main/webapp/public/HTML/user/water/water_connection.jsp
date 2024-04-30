<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Water - New Connection Form</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/navbar.css">
  <link  type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/forms.css">
  <link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/dashboard.css">
  <script type="module" src="<%= request.getContextPath() %>/public/JS/connection.js" defer></script>
  <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.js"></script>
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
        <li class="menu-items-li"><a href="<%=request.getContextPath()%>/user/water-dashboard">Home</a></li>
        <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/user/water/water-contact.jsp">Contact Us</a></li>
        <li class="menu-items-li">
          <a href="<%= request.getContextPath() %>/user/water-notification">
            <span class="material-icons">notifications</span>
          </a>
        </li>
        <li class="nxt-page water"><button class="button-17" type="button" onclick="toggle()">Dashboard</button></li>
        <script>
          function toggle() {
            window.location.href = "<%= request.getContextPath() %>/user/water-dashboard"
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
  <div class="formbox water-form__complaint">
    <form id="connection-form" method="post" action="<%= request.getContextPath() %>/user/water-connection">
      <h2>Water Services - New Connection Request</h2>
      <div class="formbody">
        <div class="forminput">
          <select name="Title" id="Title" required>
              <option value="Mr">Mr.</option>
              <option value="Mrs">Mrs.</option>
              <option value="Miss">Miss</option>
          </select>
          <label for="Title">Title</label>
        </div>
        
        <div class="forminput">
          <input id="Initial" name="Initial" type="text" required>
          <label for="Initial">Initial</label>
          <div class="error"></div>
        </div>

        <div class="forminput">
          <input id="Name" name="Name" type="text" value="<%= request.getAttribute("fullName") %>" required>
          <label for="Name">Name</label>
          <div class="error"></div>
        </div>

        <div class="forminput">
          <input id="Address" name="Address" type="text" value=" <%= request.getAttribute("ADDRESS") %>" required>
          <label for="Address">Address</label>
          <div class="error"></div>
        </div>

        <div class="forminput">
          <input id="CusNIC" name="CusNIC" type="text" value="<%= session.getAttribute("NIC") %>" required readonly>
          <label for="CusNIC">NIC</label>
          <div class="error"></div>
        </div>

        <div class="forminput">
          <input id="Email" name="Email" type="email" value="<%=session.getAttribute("EMAIL")%>" required readonly>
          <label for="Email">Email Address</label>
          <div class="error"></div>
        </div>

        <div class="forminput">
          <input id="Telnum" name="Telnum" type="tel" value="<%=session.getAttribute("TELEPHONE")%>" required readonly oninput="this.value = this.value.replace(/[^0-9]/g, '');">
          <label for="Telnum">Telephone Number(Mobile)</label>
          <div class="error"></div>
        </div>

        <div class="forminput">
          <input id="NearestAccNum" name="NearestAccNum" type="text">
          <label for="NearestAccNum">Nearest Account Number</label>
        </div>

        <div>
          <label for="water-connection" class="l-radio water-radio">
            <input type="radio" id="water-connection" name="connection-status" value="CONNECTION" tabindex="1">
            <span class="con-item">Connection</span>
          </label>
          <label for="water-disconnection" class="l-radio water-radio">
            <input type="radio" id="water-disconnection" name="connection-status" value="DISCONNECTION" tabindex="2">
            <span class="con-item">Disconnection</span>
          </label>
        </div>

        <div class="hidden" id="new_addr-div">
          <input id="NewConnectionAddress" name="NewConnectionAddress" type="text" required>
          <label for="NewConnectionAddress">New Connection Address</label>
          <div class="error"></div>
        </div>

        <div class="forminput" id="new_reg-div">
          <input id="region" name="region" type="text">
          <label for="region">New Connection Region</label>
          <div class="error"></div>
        </div>

        <div class="hidden" id="acc-div">
          <select name="account-num" id="account-num" required>
            <c:forEach items="${requestScope.water_account_list}" var="account">
              <option value="${account}">${account}</option>
            </c:forEach>
          </select>
          <label for="account-num">Account Number</label>
          <div class="error"></div>
        </div>
      </div>
      <div class="formbtn">
        <button type="submit" id="submit-btn" class="submitbtn waterbtn">Submit</button>
      </div>
    </form>
  </div>
</body>
<script>
  let regions = [<c:forEach items="${requestScope.regions}" var="region" varStatus="loop">"${region}"<c:if test="${!loop.last}">,</c:if></c:forEach>];

  const regionInput = document.getElementById('region');

  const errorDiv = regionInput.nextElementSibling;

  regionInput.addEventListener('input', function() {

    if (!regions.includes(regionInput.value.toUpperCase())) {

      errorDiv.textContent = 'Region not supported';
      errorDiv.style.color = 'red';
    } else {
      errorDiv.textContent = '';
    }
  });
</script>
</html>