<%--
  Created by IntelliJ IDEA.
  User: visit
  Date: 9/16/2023
  Time: 9:16 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <link rel="stylesheet" href="../../CSS/dashboards/dashboard.css">
        <script>

        </script>
    </head>
    <body>
    <nav class="navbar">
        <div class="navbar-container container">
            <input type="checkbox" name="" id="">
            <div class="hamburger-lines">
                <span class="line line1"></span>
                <span class="line line2"></span>
                <span class="line line3"></span>
            </div>
            <ul class="menu-items">
                <li><a href="#">Home</a></li>
                <li><a href="#">About</a></li>
                <li><a href="#">Category</a></li>
                <li><a href="#">Menu</a></li>
                <li><a href="#">Testimonial</a></li>
                <li><a href="#">Contact</a></li>
            </ul>
            <h1 class="logo">Navbar</h1>
        </div>
    </nav>
    <table class="suggestions">
        <tr class="suggestion-header">
            <th class="suggestion-data">Suggestions</th>
        </tr>
        <tr class="suggestion-row">
            <td class="suggestion-text"></td>
        </tr>
    </table>
    <div style="width: 800px;"><canvas id="acquisitions"></canvas></div>

    <!-- <script type="module" src="dimensions.js"></script> -->
    <script type="module" src="acquisitions.js"></script>
    </body>
</html>
