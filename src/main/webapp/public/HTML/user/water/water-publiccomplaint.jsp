<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/navbar.css">
  <link  type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/forms.css">
  <link type="text/css" rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/dashboard.css">
  <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.js"></script>
  <script type="module" src="<%= request.getContextPath() %>/public/JS/complaints.js" defer></script>
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

  <title>Form</title>
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
    <form id="complaint_form" method="post" action="${pageContext.request.contextPath}/user/water-public-complaint">
      <h2>Water Services - Public complains</h2>
      <div class="formbody">
        <div class="forminput">
          <select name="Category" id="Category" required>
              <option value="Breakdown">Breakdown</option>
              <option value="Service Request">Service Request</option>
          </select>
          <label for="Category">Complain Category</label>
        </div>

        <div class="forminput">
          <select name="complaint_type" id="complaint_type" required>
              <option value="Main Leak">Main Leak</option>
              <option value="Connection Leak">Connection Leak</option>
              <option value="No Water">No Water</option>
              <option value="Low Pressure">Low Pressure</option>
              <option value="Leak Near Meter">Leak Near Meter</option>
              <option value="Quality Problem">Quality Problem</option>
              <option value="Others">Others</option>
          </select>
          <label for="complaint_type">Complaint Type</label>
        </div>
        
        <div class="forminput">
          <input id="CustomerName" name="CustomerName" type="text" value="<%=request.getAttribute("fullName")%>" required>
          <label for="CustomerName">Customer Name</label>
          <div class="error"></div>
        </div>

        <div class="forminput">

          <select name="accountNum" id="accountNum" required>
            <c:forEach items="${requestScope.water_account_list}" var="account">
              <option value="${account}">${account}</option>
            </c:forEach>
          </select>
          <label for="accountNum">Account Number</label>
          <div class="error"></div>
        </div>

        <div class="forminput">
          <input id="CusNIC" name="CusNIC" type="text" value="<%= session.getAttribute("NIC") %>" required readonly>
          <!-- <div class="underline"></div> -->
          <label for="CusNIC">NIC</label>
          <div class="error"></div>
        </div>

        <div class="forminput">
          <input id="Email" name="Email" type="email" value="<%= session.getAttribute("EMAIL") %>" required readonly>
          <!-- <div class="underline"></div> -->
          <label for="Email">Email Address</label>
          <div class="error"></div>
        </div>
        
        <div class="forminput">
          <input id="Telnum" name="Telnum" type="tel" value="<%= session.getAttribute("TELEPHONE") %>" required oninput="this.value = this.value.replace(/[^0-9]/g, '');">
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
        <button type="submit" id="submit-btn" class="submitbtn waterbtn">Submit</button>
      </div>
    </form>
  </div>
</body>
</html>