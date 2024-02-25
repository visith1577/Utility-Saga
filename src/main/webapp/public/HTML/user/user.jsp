<%--
  Created by IntelliJ IDEA.
  User: visithkumarapperuma
  Date: 2024-01-08
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>user Dashboard</title>
    <link rel="stylesheet" href="../../CSS/dashboards/dashboard.css">
    <link rel="stylesheet" href="../../CSS/dashboards/user.css">
    <script type="module" src="<%= request.getContextPath() %>/public/JS/user.js" defer></script>
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
                <li class="menu-items-li"><a href="#">Home</a></li>
                <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/pages/aboutUs.jsp">About</a></li>
                <li class="menu-items-li"><a href="#">Contact Us</a></li>
                <li class="nxt-page water"><button class="button-17" type="button" onclick="toggle()">Dashboards</button></li>
                <script>
                    function toggle() {
                        window.location.href = "../dashboard/userDashboard.jsp"
                    }
                </script>
                <li class="img_user dropdown">
                    <a href="<%= request.getContextPath() %>/public/HTML/user/user.jsp">
                        <button class="user-profile">
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
            <img src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt="Utility Saga" class="logo">
        </div>
    </header>
</div>
<main class="component-container profile-component__main">
    <section class="user-profile__main card">
        <form action="" method="post" id="user-profile">
            <img id="preview" src="<%=request.getContextPath()%>/public/images/profile_alt.jpg" alt="Profile Image">
            <input type="file" name="image" id="imageInput" accept="image/*">
            <input type="hidden" name="id" value="1234569uid">
            <br/>
            <label class="form__label" for="username"> Username: </label>
            <input type="text" name="user_name" value="clone" class="form__field" id="username" readonly>
            <label class="form-label" for="tel">Telephone:</label>
            <input type="text" name="telephone" value="123-344-5434" class="form__field" id="tel" readonly>
            <label class="form-label" for="email"> Email: </label>
            <input type="email" name="email" value="nicolas@email.com" class="form__field" id="email" readonly>
            <div class="checkbox-wrapper-4">
                <input class="inp-cbx" id="morning" type="checkbox"/>
                <label class="cbx" for="morning"><span>
                <svg width="12px" height="10px">
                <use xlink:href="#check-4"></use>
                </svg></span><span>SMS Alert Activation</span></label>
                <svg class="inline-svg">
                    <symbol id="check-4" viewBox="0 0 12 10">
                        <polyline points="1.5 6 4.5 9 10.5 1"></polyline>
                    </symbol>
                </svg>
            </div>
            <div class="button-row">
                <button type="button" id="editButton" class="button-70" onclick="onToggle()">Edit</button>
                <button type="submit" id="saveButton" class="button-70">Save</button>
            </div>
        </form>
    </section>

    <section class="user-profile__sub">
        <div class="user-profile__top card" style="padding: 0">
            <div class="tbl-header">
                <table>
                    <thead>
                    <tr>
                        <th>account No.</th>
                        <th>amount</th>
                        <th>due date</th>
                        <th>status</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div class="tbl-content">
                <table>
                    <tbody>
                        <tr>
                            <td>1223456779</td>
                            <td>2500.00</td>
                            <td>2024/2/13</td>
                            <td>pending</td>
                        </tr>
                        <tr>
                            <td>1223456779</td>
                            <td>2500.00</td>
                            <td>2024/2/13</td>
                            <td>pending</td>
                        </tr>
                        <tr>
                            <td>1223456779</td>
                            <td>2500.00</td>
                            <td>2024/2/13</td>
                            <td>pending</td>
                        </tr>
                        <tr>
                            <td>1223456779</td>
                            <td>2500.00</td>
                            <td>2024/2/13</td>
                            <td>pending</td>
                        </tr>
                        <tr>
                            <td>1223456779</td>
                            <td>2500.00</td>
                            <td>2024/2/13</td>
                            <td>pending</td>
                        </tr>
                        <tr>
                            <td>1223456779</td>
                            <td>2500.00</td>
                            <td>2024/2/13</td>
                            <td>pending</td>
                        </tr>
                        <tr>
                            <td>1223456779</td>
                            <td>2500.00</td>
                            <td>2024/2/13</td>
                            <td>pending</td>
                        </tr>
                        <tr>
                            <td>1223456779</td>
                            <td>2500.00</td>
                            <td>2024/2/13</td>
                            <td>pending</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>

        <div class="user-profile__bottom card">

        </div>
    </section>
</main>
</body>
<script>
    function onToggle() {
        let form = document.getElementById("user-profile");
        let inputs = form.getElementsByTagName('input');
        let file = document.getElementById("imageInput");
        let editButton = document.getElementById('editButton');
        let saveButton = document.getElementById('saveButton');
        let labels = form.querySelectorAll('#user-profile > label');

        for (let i = 0; i < inputs.length; i++) {
            inputs[i].readOnly = !inputs[i].readOnly;
        }



        if (editButton.textContent === 'Edit') {
            editButton.textContent = 'Cancel';
            file.style.display = 'block';
            saveButton.style.display = 'inline-block';

            for (let i = 0; i < labels.length; i++) {
                labels[i].style.display = 'block';
            }
        } else {
            editButton.textContent = 'Edit';
            file.style.display = 'none';
            saveButton.style.display = 'none';
            for (let i = 0; i < labels.length; i++) {
                labels[i].style.display = 'none';
            }
        }
    }

    // temp function
    document.getElementById('user-profile').addEventListener('submit', (event) => {
        event.preventDefault();

        console.log('Data Saved.');
    });
</script>
</html>

