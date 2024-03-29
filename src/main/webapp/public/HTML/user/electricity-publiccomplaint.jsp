<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link  type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/forms.css">
  <link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/dashboard.css">
  <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.js"></script>
  <script type="module" src="<%= request.getContextPath() %>/public/JS/complaints.js" defer></script>
  <title>Electricity-Complaints</title>
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
<div class="formbox electricity_form--complaint">
    <form id="complaint_form" method="post" action="${pageContext.request.contextPath}/ePub-complaint">
      <h2>Electricity Services - Public complaints</h2>
      <div class="formbody">
        <div class="forminput">
          <select name="Category" id="Category" required>
              <option value="Breakdown">Breakdown</option>
              <option value="Service Request">Service Request</option>
          </select>
          <label for="Category">Complaint Category</label>
        </div>

        <div class="forminput">
          <select name="complaint_type" id="complaint_type" required>
            <option value="Billing issues">Billing issues</option>
            <option value="Connection & Disconnection issues">Connection & Disconnection issues</option>
            <option value="Power Outages">Power Outages</option>
            <option value="Voltage & frequency problems">Voltage & frequency problems</option>
            <option value="Smart meter problems">Smart meter problems</option>
            <option value="Quality Problem">Quality Problem</option>
            <option value="Others">Others</option>
          </select>
          <label for="complaint_type">Complaint Type</label>
        </div>

        <div class="forminput">
          <input id="CustomerName" name="CustomerName" type="text" required>
          <label for="CustomerName">Customer Name</label>
          <div class="error"></div>
        </div>

        <div class="forminput">
          <input id="AccountNum" name="AccountNum" type="text" required>
          <!-- <div class="underline"></div> -->
          <label for="AccountNum">Account Number</label>
          <div class="error"></div>
        </div>

        <div class="forminput">
          <input id="CusNIC" name="CusNIC" type="text" required>
          <!-- <div class="underline"></div> -->
          <label for="CusNIC">NIC</label>
          <div class="error"></div>
        </div>

        <div class="forminput">
          <input id="Email" name="Email" type="email" required>
          <!-- <div class="underline"></div> -->
          <label for="Email">Email Address</label>
          <div class="error"></div>
        </div>

        <div class="forminput">
          <input id="Telnum" name="Telnum" type="tel" required oninput="this.value = this.value.replace(/[^0-9]/g, '');">
          <!-- <div class="underline"></div> -->
          <label for="Telnum">Phone Number</label>
          <div class="error"></div>
        </div>

        <div class="forminput">
          <textarea id="txtArea" name="txtArea" rows="1" cols="1" required maxlength="50"></textarea>
          <label for="txtArea">Complaint Description</label>
          <br />
        </div>
      </div>
      <div class="formbtn">
        <button type="submit" id="submit-btn" class="submitbtn electricitybtn">Submit</button>
      </div>
    </form>
  </div>
</body>
</html>