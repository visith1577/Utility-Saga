<%--
  Created by IntelliJ IDEA.
  User: Liviru Weerasinghe
  Date: 4/10/2024
  Time: 8:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="model.SolarCompanyModel" %>
<%@ page import="DAO.dao.RegisterSolarDAO" %>
<%@ page import="java.util.List" %>
<%
  // Get the context path dynamically
  String contextPath = request.getContextPath();
%>
<%
    List<SolarCompanyModel> companies = null;
    try {
        companies = new RegisterSolarDAO().getRegisteredCompanies();
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <title>Document</title>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <link href="<%= request.getContextPath() %>/public/CSS/superadmin/Superadmin-editadmins.css" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/dashboard.css">
  <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/forms.css">
  <script src="<%= request.getContextPath() %>/public/JS/dashboard.js"></script>
  <script src="<%= request.getContextPath() %>/public/JS/SolarSearch.js"></script>
  <script>
    window.onscroll = function () {
      scrollFunction()
    }

    function scrollFunction() {
      if (document.body.scrollTop > 70 || document.documentElement.scrollTop > 70) {
        document.getElementById("sidebar").style.height = "80%";
      } else {
        document.getElementById("sidebar").style.height = "85%";
      }
    }

    document.addEventListener('DOMContentLoaded', function() {

      const submitButtons = document.querySelectorAll('.submit-btn');

      console.log(submitButtons);

      submitButtons.forEach(button => {
        button.addEventListener('click', () => {
          const row = button.parentNode.parentNode;
          const bNum = row.dataset.bnum;
          const statusSelect = row.querySelector('select[name="approvalStatus"]');
          const status = statusSelect.value;
          console.log(status)
          updateApprovalStatus(bNum, status)
        });
      });

      function updateApprovalStatus(bNum, status) {
        const contextPath = '<%= contextPath %>';
        fetch(contextPath + '/UpdateApprovalStatus?companyId=' + encodeURIComponent(bNum) + '&status=' + encodeURIComponent(status), {
          method: "POST"
        })
                .then(response => {
                  if (!response.ok) {
                    throw new Error('Response was not ok');
                  }
                  return response.json();
                }).then(_ => {
          toastr.success("Status of " + bNum + " updated to " + status + " successfully.");
        }).catch(error => {
          console.error("Problem updating approval status: ", error);
        })
      }
    });
  </script>

</head>

<body>
<div class="wrapper">
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
          <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/superadmin/Superadmin-electricity-water.jsp">Electricity/Water</a></li>
          <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/superadmin/Superadmin-solar.jsp">Solar</a></li>
          <li class="menu-items-li"><a href="#">Logout</a></li>
        </ul>
        <img src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt="Utility Saga" class="logo">
      </div>
    </header>
  </div>

  <div style="margin-top: 100px">
  <input type="text" id="nic" placeholder="Enter Owner NIC">
  <button onclick="search()">Search</button>

  </div>

  <div class="middle" id="middle">
    <div id="results"></div>

    <h4 class="title">Solar Companies</h4>

    <table class="table">
      <tr>
        <th>Company Name</th>
        <th>BRN</th>
        <th>Owner-NIC</th>
        <th>Username</th>
        <th>Password</th>
        <th>Mobile</th>
        <th>Company Phone</th>
        <th>Email</th>
        <th>Address</th>
        <th>District</th>
        <th>Remarks</th>
        <th>Approved Status</th>
        <th>Submit</th>
      </tr>
      <% for (SolarCompanyModel company : companies) { %>
      <tr data-bnum="<%= company.getBNum() %>">
        <td><%= company.getCompanyName() %></td>
        <td><%= company.getBNum() %></td>
        <td><%= company.getOwnerNIC() %></td>
        <td><%= company.getUsername() %></td>
        <td><%= company.getPwd() %></td>
        <td><%= company.getMobile() %></td>
        <td><%= company.getLandNo() %></td>
        <td><%= company.getEmail() %></td>
        <td><%= company.getAddress() %></td>
        <td><%= company.getDistrict() %></td>
        <td><%= company.getRemarks() %></td>
        <td>
          <select name="approvalStatus">
            <option  value="PENDING" <%= company.getApprovalStatus() == SolarCompanyModel.ApprovalStatus.PENDING ? "selected" : "" %>>Pending</option>
            <option  value="APPROVED" <%= company.getApprovalStatus() == SolarCompanyModel.ApprovalStatus.APPROVED ? "selected" : "" %>>Approved</option>
            <option  value="REJECTED" <%= company.getApprovalStatus() == SolarCompanyModel.ApprovalStatus.REJECTED ? "selected" : "" %>>Rejected</option>
          </select>
        </td>
        <td><button class="submit-btn" id="<%= company.getBNum() %>">Submit</button></td>
      </tr>
      <% } %>
    </table>
  </div>
</div>
</body>
</html>
