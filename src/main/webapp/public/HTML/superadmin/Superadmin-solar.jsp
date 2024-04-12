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

<%List<SolarCompanyModel> companies = new RegisterSolarDAO().getRegisteredCompanies();%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">
  <title>Document</title>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <link href="../../CSS/superadmin/Superadmin-editadmins.css" rel="stylesheet">
  <link rel="stylesheet" href="../../CSS/dashboards/dashboard.css">
  <link rel="stylesheet" href="../../CSS/forms.css">
  <script src="../../JS/dashboard.js"></script>
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

    document.addEventListener("DOMContentLoaded", function() {
      var buttons = document.querySelectorAll('.submit-btn');
      console.log(buttons);
      buttons.forEach(function(button) {
        button.addEventListener('click', function() {
          console.log('Button clicked');
          var bNum = button.dataset.bnum;
          console.log('BNum:', bNum);
          var dropdown = document.querySelector('select[name="approvalStatus' + bNum + '"]');
          console.log('Dropdown:', dropdown);
          var status = dropdown.value;
          console.log("abc:", dropdown.name);
          updateApprovalStatus(bNum, status);
        });
      });
    });

    function updateApprovalStatus(bNum, status){
      console.log("Updating approval status for companyId:", bNum, "with status:", status);
      fetch('/UtilitySaga_war_exploded/UpdateApprovalStatus',{
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: 'companyId=' + encodeURIComponent(bNum) + '&status=' + encodeURIComponent(status)
      }).then(response => {
        if(!response.ok){
          throw new Error('Response was not ok');
        }
        return response.json();
      }).then(data => {
        console.log("Approval Status updated successfuly: ",data);
      }).catch(error => {
        console.error("Problem updating approval statrus: ", error);
      })
    }
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

  <div class="middle" id="middle">
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
      <tr>
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
          <select name="approvalStatus<%= company.getBNum() %>">
            <option  value="PENDING" <%= company.getApprovalStatus() == SolarCompanyModel.ApprovalStatus.PENDING ? "selected" : "" %>>Pending</option>
            <option  value="APPROVED" <%= company.getApprovalStatus() == SolarCompanyModel.ApprovalStatus.APPROVED ? "selected" : "" %>>Approved</option>
            <option  value="REJECTED" <%= company.getApprovalStatus() == SolarCompanyModel.ApprovalStatus.REJECTED ? "selected" : "" %>>Rejected</option>
          </select>
        </td>
        <td><button class="submit-btn" data-bnum="<%= company.getBNum() %>" >Submit</button></td>
      </tr>
      <% } %>

    </table>

  </div>

</div>
</div>
</div>

</body>

</html>
