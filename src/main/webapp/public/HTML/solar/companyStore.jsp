<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Store</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href='https://fonts.googleapis.com/css?family=Spartan' rel='stylesheet'>
    <link link rel="stylesheet" href="../../CSS/solar/styleMain.css">
    <link link rel="stylesheet" href="../../CSS/solar/store.css">
    <link link rel="stylesheet" href="../../CSS/solar/companyStoreMain.css">


</head>
<body>
<section id="header" style="background-color: #FFF5E0;">
    <a href="#"><img id="logo" src="../../images/solar/logo.png" alt=""></a>
    <p id="date-container" style="font-size: 25px"></p>
</section>

<section id="hero" style="background-image: url(../../images/solar/hero.jpg); color: black">
    <h4 style="color: black">Trade-in-Offer</h4>
    <h2 style="color: black">Super Value deals</h2>
    <h1 style="color: black">On all products</h1>
    <p style="color: black">Save more Energy more Money!</p>
    <form action="/item/all" method="get">

        <button type="submit"> Shop Now</button>
    </form>
</section>

<section id="product1" class="section-p1">
    <h2>Arrivels</h2>
    <p>Suggestet For you</p>
    <div class="proContainer">
        <div class="pro">
            <img src="../../images/solar/solar_panal.png">
            <div class="dis">
                <span>addidas</span>
                <h5>Solar Panel</h5>
                <h4>$</h4>
            </div>
            <a href="#" class="cart-link" ><i class="fas fa-shopping-cart cart"></i></a>
        </div>

    </div>
</section>

<footer class="section-p1">
    <div class="footerCol">
        <img class="logo" src="img/logo.png" alt="">
        <h4>Contact</h4>
        <p><strong>Address</strong>: 1234 Street Adress City Address, 1234</p>
        <p><strong>Phone</strong>: (077) 456-7890</p>
        <p><strong>Fax</strong>: (077) 456-7890</p>
        <p><strong>Email</strong>: utilitySaga@gmail.com </p>

    </div>
    <div class="footerCol">
        <h4>About</h4>
        <a href="<%= request.getContextPath()%>/public/HTML/pages/aboutUs.jsp">About us</a>
        <a href="#">Privacy and Policy</a>
        <a href="#">Terms and Condition</a>
        <a href="#">Contact Us</a>
    </div>
    <div class="footerCol">

    </div>

</footer>

<section id="view" class="section-p1">
    <i class="fas fa-x" id="cut" ></i>
    <div id="imgView">
        <img src="../../images/solar/solar_panal.png" >
    </div>
    <div id="details">
        <table >
            <tr>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            <tr>
                <td>Name</td>
                <td>:</td>
                <td>${name}</td>
            </tr>
            <tr>
                <td>Price</td>
                <td>:</td>
                <td>${price}</td>
            </tr>
            <tr>
                <td>Company</td>
                <td>:</td>
                <td>${company}</td>
            </tr>
            <tr>
                <td>Category</td>
                <td>:</td>
                <td>${category}</td>
            </tr>

        </table>

    </div>
</section>



<script scr="../../JS/greeting.js"> </script>
</body>


</html>