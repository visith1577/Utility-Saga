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
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/navbar.css">
    <style>
        #navv{
            display: flex;
            width: 100%;
            padding: 0px 10px;

        }
        #navbar{
            display: flex;
            width: 100%;
            margin: 0px;


        }
        #navCon{
            display: flex;
            width: 100%;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
            margin: 0px;
            max-width: 2000px;
        }
        #hero{
            margin-top:70px;
        }
        #hero a{
            text-decoration: none;
        }

        #navLi  {
            display: flex;
            align-items: center;
            margin-top: 0px ;
        }
        #navLi li {

            margin-top: 0px ;
        }



    </style>
</head>
<body>


<%--<section id="header" >--%>
<%--    <a href="index.html"><img id="logo" src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt=""></a>--%>
<%--    <div id="proDe">--%>
<%--        <p> ${sessionScope.companyName}--%>
<%--        </p>--%>
<%--        <a href="#"><img id="proImg" src="<%= request.getContextPath() %>/public/images/solar/companyLogo.jpeg" alt=""></a>--%>
<%--    </div>--%>
<%--</section>--%>

<div class="navv" id="navv">
    <header class="navbar"  id="navbar">
        <div class="navbar-container container" id="navCon" >
            <ul class="menu-items" id="navLi">
                <li class="menu-items-li">${companyName}</li>
                <li class="menu-items-li"><a id="logout" href="<%= request.getContextPath() %>/logout"><i class="fas fa-power-off"></i>
                </a></li>
            </ul>
            <img src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt="Utility Saga" class="logo">
        </div>
    </header>
</div>


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
        <a href="<%= request.getContextPath()%>/item/all"> Utility Saga Store </a>
    </div>
</section>

<div id="addItemLink">
    <a href="<%= request.getContextPath()%>/public/HTML/solar/addItem.jsp"><i class="fas fa-plus"> </i></a>
</div>


</body>
</html>
