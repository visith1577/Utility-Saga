<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link  type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/forms.css">
  <link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/dashboard.css">
  <script type="module" src="<%= request.getContextPath() %>/public/JS/connection.js" defer></script>
  <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.js"></script>
  <title>Form</title>
</head>
<body>
  <!-- nav bar -->
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
          <li class="menu-items-li"><a href="#">Contact Us</a></li>
          <li class="nxt-page water"><button class="button-17" type="button" onclick="toggle()">Dashboards</button></li>
          <script>
            function toggle() {
              window.location.href = "../dashboard/userDashboard.jsp"
            }
          </script>
          <li class="img_user dropdown">
            <a href="<%= request.getContextPath() %>/public/HTML/user/user.jsp">
              <button class="user-profile">
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
        <img src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt="Utility Saga" class="logo">
      </div>
    </header>
  </div>
  <!-- main content -->
  <div class="formbox electricity_form--complain">
    <form id="connection-form" method="post" action="">
      <h2>Electricity Services - New Connection Request</h2>
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
          <input id="Name" name="Name" type="text" required>
          <label for="Name">Name</label>
          <div class="error"></div>
        </div>

        <div class="forminput">
          <input id="Address" name="Address" type="text" required>
          <label for="Address">Current Address</label>
          <div class="error"></div>
        </div>

        <div class="forminput">
          <input id="CusNIC" name="CusNIC" type="text" required>
          <label for="CusNIC">NIC</label>
          <div class="error"></div>
        </div>

        <div class="forminput">
          <input id="Email" name="Email" type="email" required>
          <label for="Email">Email Address</label>
          <div class="error"></div>
        </div>

        <div class="forminput">
          <input name="Telnum" id="Telnum" type="tel" required oninput="this.value = this.value.replace(/[^0-9]/g, '');">
          <label for="Telnum">Telephone Number(Mobile)</label>
          <div class="error"></div>
        </div>

        <div class="forminput">
          <input id="NearestAccNum" name="NearestAccNum" type="text">
          <label for="NearestAccNum">Nearest Account Number (optional)</label>
          <div class="error"></div>
        </div>

        <div>
          <label for="connection" class="l-radio electricity-radio">
            <input type="radio" id="connection" name="connection-status" value="connection" tabindex="1">
            <span class="con-item">Connection</span>
          </label>
          <label for="disconnection" class="l-radio electricity-radio">
            <input type="radio" id="disconnection" name="connection-status" value="disconnection" tabindex="2">
            <span class="con-item">Disconnection</span>
          </label>
        </div>

        <div class="hidden" id="type-div">
          <select name="RequiredType" id="RequiredType">
            <option value="Single phase 30A">Single phase 30A</option>
            <option value="Three phase 30A">Three phase 30A</option>
            <option value="Three Phase 60A">Three Phase 60A</option>
            <option value="Single phase 15A">Single Phase 15A(Only Colombo & Kandy)</option>
            <option value="Three Phase 15A">Three Phase 15A(Only Colombo & Kandy)</option>
        </select>
        <label for="RequiredType">Required electricity supply</label>
        </div>
        <div class="hidden" id="acc-div">
          <input id="account-num" type="text">
          <label for="account-num">Account Number</label>
          <div class="error"></div>
        </div>

        <div class="hidden" id="new_addr-div">
          <input id="NewConnectionAddress" name="NewConnectionAddress" type="text">
          <label for="NewConnectionAddress">New Connection Address</label>
          <div class="error"></div>
        </div>

        <div class="forminput" id="new_reg-div">
          <input id="region" name="region" type="text" required>
          <label for="region">New Connection Region</label>
          <div class="error"></div>
        </div>

      </div>
      <div class="formbtn">
        <button type="submit" id="submit-btn" class="submitbtn electricitybtn">Submit</button>
      </div>
    </form>
  </div>
</body>
</html>