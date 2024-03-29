<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 1/17/2024
  Time: 5:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>About Us</title>
  <link rel="stylesheet" href="../../CSS/dashboards/aboutUs.css">
  <link rel="stylesheet" href="../../CSS/dashboards/dashboard.css">
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
        <li class="menu-items-li"><a href="../dashboard/dashboard.jsp">Home</a></li>
        <li class="menu-items-li"><a href="#">About</a></li>
        <li class="menu-items-li"><a href="#">Contact Us</a></li>

        <li class="img_user dropdown">
          <a href="">
            <button>
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
      <img src="../../images/utility_saga.svg" alt="Utility Saga" class="logo">
    </div>
  </header>
</div>
<section class="about-section">
  <div class="container">
    <div class="row">
      <div class="content-column col-lg-6 col-md-12 col-sm-12 order-2">
        <div class="inner-column">
          <div class="sec-title">
            <span class="title">About Utility Saga</span>
            <h2>We are a hub which collaborates utility services in Sri Lanka</h2>
          </div>
          <div class="text">We utility Saga, is a hub which centralize the main Electricity and Water utility services in Sri Lanka and we also promote renewable energy in the form of solar power by providing a platform for a store.</div>
          <div class="text">
            This is a whole new concept in Sri Lanka which is an unique idea. Customers can pay their bills and manage their utility services through our product.
          </div>
        </div>
      </div>

      <div class="image-column col-lg-6 col-md-12 col-sm-12">
        <div class="inner-column wow fadeInLeft">
          <figure class="image-1"><a href="#" class="lightbox-image" data-fancybox="images"><img title="Utility Services" src="../../images/about-us-utility.jpg" alt=""></a></figure>
        </div>
      </div>

    </div>
    <div class="sec-title">
      <h2>We want to drive innovation</h2>
    </div>
    <div class="text">
      Our idea is an whole new concept which is unique.
    </div>
    <div class="text">
      Less time consuming as they can manage utilities through our hub.
    </div>
    <div class="text">
      Promoting renewable energy for a green future.
    </div>
  </div>
</section>
</body>
</html>
