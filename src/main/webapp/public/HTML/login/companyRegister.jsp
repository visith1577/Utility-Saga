<%@ page import="model.CompanyModel" %>
<%@ page import="utils.CompanyModelDTO" %><%--
  Created by IntelliJ IDEA.
  User: ShyamalHAD
  Date: 4/23/2024
  Time: 4:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String pointerEvent = "all"; %>
<%
    String errorMessage = (String) request.getAttribute("errorMessage");
    String companyName = "";
    String userName = "";
    String email = "";
    String street = "";
    String city = "";
    String state = "";
    String country = "";
    String zipCode = "";
    String addressContact = "";
    String companyContact = "";
    if(request.getSession().getAttribute("companyModelDTO") != null){
       CompanyModelDTO company = (CompanyModelDTO) request.getSession().getAttribute("companyModelDTO");
         companyName = company.getName();
            userName = company.getUserName();
            email = company.getEmail();
            street = company.getStreet();
            city = company.getCity();
            state = company.getState();
            country = company.getCountry();
            zipCode = company.getZipCode().toString();
            addressContact = company.getAddressContact();
            companyContact = company.getCompanyContact();
    }
%>



<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
    <link href='https://fonts.googleapis.com/css?family=Spartan' rel='stylesheet'>
    <link rel="stylesheet" href="../../CSS/solar/company.css">
    <title>Company Registration</title>
</head>
<body>
<section id="header" >
    <a href="index.html"><img id="logo" src="../../images/solar/logo.png" alt=""></a>
    <div id="proDe">
        <p>Register Your Company</p>
        <a href="#"><img id="proImg" src="../../images/solar/companyLogo.jpeg" alt=""></a>

    </div>
</section>

<div class="container">
    <h2>Company</h2>
    <form action="<%= request.getContextPath() %>/company/" method="post">
        <div class="form-group">
            <label for="Company_name">Company Name</label>
            <input type="text" id="Company_name" name="name" placeholder="Suite.com" value='<%= companyName%>' required>
        </div>
        <div class="form-group">
            <label for="uName" >User Name</label>
            <input type="text" id="uName" name="userName"  placeholder="User" value='<%= userName%>' required>
<%--            <button type="button" id="check">--%>
<%--                <a href="<%= request.getContextPath() %>/company/check-username?username=${document.getElementById('check')}> Check Availability</a>--%>
            </button>
        </div>
        <div class="form-group" >
            <label for="password" >Enter Password</label>
            <input type="password" id="password" name="password" placeholder="Password" style="pointer-events: <%=pointerEvent%>;" >
        </div>
        <div class="form-group">
            <label for="address_contact" >Re-Enter Password</label>
            <input type="password" id="re-password" name="re-pass" placeholder="re-enter" required>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="text" id="email" name="email" placeholder="abc@gmail.com" value='<%= email%>' required>
        </div>
        <div class="form-group">
            <label for="company_contact">Company Contact</label>
            <input type="number" id="company_contact" name="company_contact" placeholder="091XXXXXXXX" value='<%= companyContact%>' required>
        </div>
        <div>
        </div>
        <div class="form-group">
            <label for="street">Street</label>
            <input type="text" id="street" name="street" placeholder="Colombo Rd" value='<%= street%>' required>
        </div>
        <div class="form-group">
            <label for="city">City</label>
            <input type="text" id="city" name="city" placeholder="Ambalangoda" value='<%= city%>' required>
        </div>
        <div class="form-group">
            <label for="state">State</label>
            <input type="text" id="state" name="state" placeholder="Galle" value='<%= state%>' required>
        </div>
        <div class="form-group">
            <label for="country">Country</label>
            <input type="text" id="country" name="country" placeholder="Sri Lanka" value='<%= country%>' required>
        </div>
        <div class="form-group">
            <label for="zipCode">Zip Code</label>
            <input type="number" id="zipCode" name="zipCode" placeholder="803645" value='<%= zipCode%>' required>
        </div>
        <div class="form-group">
            <label for="address_contact">Address Contact</label>
            <input type="number" id="address_contact" name="address_contact" placeholder="07XXXXXXXXX" value='<%= addressContact%>' required>
        </div>

        <div class="form-group">
            <button type="submit">Register Company</button>
        </div>
    </form>
</div>
</body>
</html>
