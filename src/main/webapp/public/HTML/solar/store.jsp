<%@ page import="jakarta.mail.Session" %>
<!--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
-->
<% String contextPath = request.getContextPath();%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Store</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
    <link href='https://fonts.googleapis.com/css?family=Spartan' rel='stylesheet'>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/solar/styleMain.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/solar/form.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/solar/store.css">
    <style>




    </style>
</head>
<body>


<section id="header" >
    <a href="index.html"><img id="logo" src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt=""></a>
    <div id="proDe">
        <p> ${sessionScope.companyName}
        </p>
        <a href="#"><img id="proImg" src="<%= request.getContextPath() %>/public/images/solar/companyLogo.jpeg" alt=""></a>
    </div>
</section>


<section id="hero">
    <h4>Utility Saga</h4>
    <h2>Manage your Trends</h2>
    <h1>On all products</h1>
    <p> Make an E-Day </p>
    <button><a href="<%= request.getContextPath() %>/item/supplierItems?supplierId=${sessionScope.supplierId}"> See
        your
        Items</a></button>

</section>

<section id="mainBody" class="section-p1">
    <div id="welcomeNote">
        <h2> Welcome ${sessionScope.companyName} !</h2>
    </div>
    <div id="btn">
        <a href="<%= request.getContextPath()%>/public/HTML/solar/addItem.jsp">Add Item</a>
        <a href="<%= request.getContextPath()%>/item/all"> Utility Saga Store </a>
    </div>
</section>



</body>
</html>
