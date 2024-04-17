<%--
  Created by IntelliJ IDEA.
  User: Liviru Weerasinghe
  Date: 4/13/2024
  Time: 2:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page import="DAO.dao.ElectricityComplaintDao" %>
<%@ page import="model.ComplaintModel" %>
<%@ page import="java.util.List" %>
<% List<ComplaintModel> complaints = new ElectricityComplaintDao().getComplaints();%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Electricity Regional admin dashboard</title>
    <link href="<%= request.getContextPath() %>/public/CSS/dashboards/Admin/regionalAdminElectricity.css" rel="stylesheet">
    <link href="<%= request.getContextPath() %>/public/CSS/popup.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css">
    <link rel="stylesheet" href="../../../CSS/dashboards/dashboard.css">
    <link rel="stylesheet" href="../../../CSS/forms.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

</head>
<body>
<header>
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
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/electricity/regional-admin/user-accounts">Customers</a></li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/electricity/regionalAdmin/complaints-electricity.jsp">Complaints</a></li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/electricity/regionalAdmin/electricity-payment.jsp">Payment</a></li>
                    <li class="menu-items-li"><a id="logout" href="<%= request.getContextPath() %>/logout">LogOut</a></li>
                </ul>
                <img src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt="Utility Saga" class="logo">
            </div>
        </header>
    </div>
</header>

    <div class="complaints">
        <div class="tablediv">
            <div class="customerdetails">
                <div class="titlebar">
                    <h2>Complaint Details</h2>
                    <a href="#" class="btn">View All</a>
                </div>
                <div class="table-container">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Complaint_Number</th>
                            <th>Category</th>
                            <th>Complaint_type</th>
                            <th>NIC</th>
                            <th>Account Number</th>
                            <th>Mobile</th>
                            <th>Complaint Status</th>
                            <th>Description</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                                <% for (ComplaintModel complaint : complaints) { %>
                        <tr>
                            <td><%= complaint.getComplaint_no() %></td>
                            <td><%= complaint.getComplaint_category() %></td>
                            <td><%= complaint.getComplaint_type() %></td>
                            <td><%= complaint.getNic() %></td>
                            <td><%= complaint.getAccount_number() %></td>
                            <td><%= complaint.getPhoneNumber() %></td>
                            <td><select name="complaintStatus<%= complaint.getComplaint_no() %>">
                                <option  value="ACTIVE" <%= complaint.getComplaintStatus() == ComplaintModel.ComplaintStatus.ACTIVE ? "selected" : "" %>>Active</option>
                                <option  value="PENDING" <%= complaint.getComplaintStatus() == ComplaintModel.ComplaintStatus.PENDING ? "selected" : "" %>>Pending</option>
                                <option  value="DONE" <%= complaint.getComplaintStatus() == ComplaintModel.ComplaintStatus.DONE ? "selected" : "" %>>Done</option>
                            </select></td>
                            <td><%= complaint.getComplaint_description()%></td>
                            <td><button class="submit-btn" data-bnum="<%= complaint.getComplaint_no() %>" >Submit</button></td>
                        </tr>
                        <% } %>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</section>

<script src="<%= request.getContextPath() %>/public/JS/ElectricityAdminDashboard.js"></script>
</body>
</html>
