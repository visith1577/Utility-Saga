<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Water - New Connection Form</title>
  <link  type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/forms.css">
  <link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/dashboard.css">
  <script type="module" src="<%= request.getContextPath() %>/public/JS/connection.js" defer></script>
  <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.js"></script>
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
        <li class="menu-items-li"><a href="#">Contact Us</a></li>
        <li class="nxt-page water"><button class="button-17" type="button" onclick="toggle()">Dashboards</button></li>
        <script>
          function toggle() {
            window.location.href = "userDashboardElectricity.jsp"
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
  <div class="formbox water-form__complaint">
    <form id="complaint_form" method="post" action="<%= request.getContextPath() %>/water-connection">
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
        </div>

        <div class="forminput">
          <input id="Name" name="Name" type="text" required>
          <label for="Name">Name</label>
        </div>

        <div class="forminput">
          <input id="Address" name="Address" type="text" required>
          <label for="Address">Address</label>
        </div>

        <div class="forminput">
          <input id="CusNIC" name="CusNIC" type="text" required>
          <label for="CusNIC">NIC</label>
        </div>

        <div class="forminput">
          <input id="Email" name="Email" type="email" required>
          <label for="Email">Email Address</label>
        </div>

        <div class="forminput">
          <input id="Telnum" name="Telnum" type="tel" required>
          <label for="Telnum">Telephone Number(Mobile)</label>
        </div>

        <div class="forminput">
          <input id="NearestAccNum" name="NearestAccNum" type="text" required>
          <label for="NearestAccNum">Nearest Account Number</label>
        </div>

        <div>
          <label for="water-connection" class="l-radio water-radio">
            <input type="radio" id="water-connection" name="connection-status" value="connection" tabindex="1">
            <span class="con-item">Connection</span>
          </label>
          <label for="water-disconnection" class="l-radio water-radio">
            <input type="radio" id="water-disconnection" name="connection-status" value="disconnection" tabindex="2">
            <span class="con-item">Disconnection</span>
          </label>
        </div>

        <div class="forminput" id="new_reg-div">
          <input id="region" name="region" type="text">
          <label for="region">New Connection Region</label>
          <div class="error"></div>
        </div>

        <div class="hidden" id="new_addr-div">
          <input id="NewConnectionAddress" name="NewConnectionAddress" type="text" required>
          <label for="NewConnectionAddress">New Connection Address</label>
        </div>
      </div>
      <div class="formbtn">
        <button type="submit" id="submit-btn" class="submitbtn waterbtn">Submit</button>
      </div>
    </form>
  </div>
</body>
</html>