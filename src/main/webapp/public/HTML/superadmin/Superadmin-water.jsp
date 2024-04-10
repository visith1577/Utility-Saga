<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <title>Document</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="../../CSS/superadmin/Superadmin-editadmins.css" rel="stylesheet">
    <link rel="stylesheet" href="../../CSS/dashboards/dashboard.css">

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
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/superadmin/Superadmin-electricity.jsp">Electricity</a></li>
                    <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/superadmin/Superadmin-water.jsp">Water</a></li>
                    <li class="menu-items-li"><a href="#">Logout</a></li>
                </ul>
                <img src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt="Utility Saga" class="logo">
            </div>
        </header>
    </div>

    <div class="middle" id="middle">
        <h4  class="title">Electricity Admins</h4>
        <div class="buttons">
            <button class="button">Update</button>
            <button class="button">View</button>
            <button class="button">Add</button>
            <button class="button">Delete</button>
        </div>
        <table class="table">
            <tr>
                <th>Name</th>
                <th>Job title</th>
                <th>Employee ID</th>
                <th>Password</th>
            </tr>
            <tr>
                <td>A.R.P. Perera</td>
                <td>IT Specialist</td>
                <td>E12344557</td>
                <td>2sadnfj5241</td>
            </tr>
            <tr>
                <td>A.R.P. Perera</td>
                <td>IT Specialist</td>
                <td>E12344557</td>
                <td>2sadnfj5241</td>
            </tr>

        </table>

    </div>

</div>
</div>
</div>

</body>
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
</script>

</html>