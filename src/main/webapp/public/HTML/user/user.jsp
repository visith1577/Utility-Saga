<%@ page import="java.util.Enumeration" %><%--
  Created by IntelliJ IDEA.
  User: visithkumarapperuma
  Date: 2024-01-08
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<% String name = (String) session.getAttribute("UNAME"); %>
<%--<%--%>
<%--    // Get the HttpSession object--%>
<%--    HttpSession ses = request.getSession();--%>

<%--// Get all attribute names in sessionScope--%>
<%--    Enumeration<String> attributeNames = ses.getAttributeNames();--%>
<%--    while (attributeNames.hasMoreElements()) {--%>
<%--        String attributeName = attributeNames.nextElement();--%>
<%--        Object attributeValue = session.getAttribute(attributeName);--%>
<%--        System.out.println(attributeName + " : " + attributeValue);--%>
<%--    }--%>
<%--%>--%>
<html>
<head>
    <title>user Dashboard</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/dashboard.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/user.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/skeleton.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
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
                <li id="prof-id" class="menu-items-li"><a href="#"><%= "Welcome " + name%></a></li>
                <li class="menu-items-li"><a href="#">Home</a></li>
                <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/pages/aboutUs.jsp">About Us</a></li>
                <li class="nxt-page water"><button class="button-17" type="button" onclick="toggle()">Dashboards</button></li>
                <script>
                    const electricity = "<%=session.getAttribute("electricity") != null%>"
                    const water = "<%=session.getAttribute("water") != null%>"
                    function toggle() {
                        if (electricity === 'true'){
                            window.location.href = "<%= request.getContextPath() %>/user/electricity-dashboard";
                        } else if(water === 'true') {
                            window.location.href = "<%= request.getContextPath() %>/user/water-dashboard";
                        }
                    }
                </script>
                <li class="img_user dropdown">
                    <a href="<%= request.getContextPath() %>/user/user-profile">
                        <button class="user-profile">
                            <%
                                // Retrieve the Image attribute from the session
                                Object image = session.getAttribute("IMAGE");

                                if (image == null) {
                            %>
                            <img alt="User" src="<%= request.getContextPath() %>/public/images/user.svg" style="width: 4vh; height: 4vh">
                            <%
                            } else {
                            %>
                            <img class="image-profile" src="data:image/jpeg;base64,<%= image %>" alt="image" style="width: 5vh; height: 5vh">
                            <%
                                }
                            %>
                        </button>
                        <div class="dropdown-content">
                            <a href="<%= request.getContextPath() %>/user/user-settings"><c:out value="${'<b> Settings </b>'}" escapeXml="false"/></a>
                            <a href="<%= request.getContextPath() %>/public/HTML/user/payments.jsp"><c:out value="${'<b> Payments </b>'}" escapeXml="false"/></a>
                            <a id="logout" href="<%= request.getContextPath() %>/logout">LogOut</a>
                        </div>
                    </a>
                </li>
            </ul>
            <img loading="lazy" src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt="Utility Saga" class="logo">
        </div>
    </header>
</div>
<main class="component-container profile-component__main">
    <section class="user-profile__main card">
        <form action="${pageContext.request.contextPath}/user/api/user-profile" method="post" id="user-profile" class="edit-profile" enctype='multipart/form-data'>
            <div id="canvasContainer">
                <canvas id="previewCanvas" class="image-canvas" width="300" height="300"></canvas>
            </div>
            <input type="file" name="image" id="imageInput" accept="image/*">
            <script>
                document.addEventListener("DOMContentLoaded", function() {
                    const canvas = document.getElementById('previewCanvas');
                    const context = canvas.getContext('2d');
                    let fetchedImage = '<%= session.getAttribute("IMAGE") %>';

                    function draw_image_on_canvas(imageSrc) {
                        const image = new Image();
                        image.src = 'data:image/jpeg;base64,' + imageSrc;
                        image.onload = function() {
                            context.clearRect(0, 0, canvas.width, canvas.height);
                            context.drawImage(image, 0, 0, canvas.width, canvas.height);
                        };
                    }


                    function draw_default_image() {
                        const defaultImage = new Image();
                        // Set the source of the default image
                        defaultImage.src = "<%=request.getContextPath()%>/public/images/profile_alt.jpg";

                        // Draw the default image onto the canvas when it's loaded
                        defaultImage.onload = function() {
                            context.drawImage(defaultImage, 0, 0, canvas.width, canvas.height);
                        };
                    }


                    if (fetchedImage) {
                        draw_image_on_canvas(fetchedImage);
                    } else {
                        draw_default_image();
                    }

                    function draw_selected_image(file) {
                        const reader = new FileReader();
                        reader.onload = function(event) {
                            const selectedImage = new Image();
                            selectedImage.src = event.target.result;
                            selectedImage.onload = function() {
                                context.clearRect(0, 0, canvas.width, canvas.height);
                                context.drawImage(selectedImage, 0, 0, canvas.width, canvas.height);
                            };
                        };
                        reader.readAsDataURL(file);
                    }

                    document.getElementById('imageInput').addEventListener('change', function(event) {
                        const file = event.target.files[0];
                        if (file) {
                            draw_selected_image(file);
                        } else {
                            if (fetchedImage) {
                                draw_image_on_canvas(fetchedImage);
                            } else {
                                context.clearRect(0, 0, canvas.width, canvas.height);
                                draw_default_image();
                            }
                        }
                    });
                });
            </script>
            <input type="hidden" name="id" value="1234569uid">
            <br/>
            <label class="id" for="nic">NIC</label>
            <input type="text" name="nic" value="<%= session.getAttribute("NIC") %>" class="form__field" id="nic" readonly required>
            <div>
                <label class="form__label" for="username"> Username: </label>
                <input type="text" name="user_name" value="<%= name %>" class="form__field" id="username" readonly required>
                <div class="error"></div>
            </div>
            <div>
                <label class="form-label" for="tel">Telephone:</label>
                <input type="text" name="telephone" value="<%= session.getAttribute("TELEPHONE") %>" class="form__field" id="tel" readonly required>
                <div class="error"></div>
            </div>
            <div>
                <label class="form-label" for="email"> Email: </label>
                <input type="email" name="email" value="<%= session.getAttribute("EMAIL") %>" class="form__field" id="email" readonly required>
                <div class="error"></div>
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
                    <tbody id="dataTable">
                    <c:if test="${not empty sessionScope.electricity}">
                        <c:forEach items="${requestScope.electricity_account_list}" var="account">
                            <tr>
                                <td>${account.account_number}</td>
                                <td>${account.amount}</td>
                                <td>${account.dueDate}</td>
                                <td>${account.status}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${not empty sessionScope.water}">
                        <c:forEach items="${requestScope.water_account_list}" var="account">
                            <tr>
                                <td>${account.account_number}</td>
                                <td>${account.amount}</td>
                                <td>${account.dueDate}</td>
                                <td>${account.status}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="user-profile__bottom card">
            <div class="box">
                <form action="#"  >
                    <div class="input_box">

                        <label>
                            <input type="text" placeholder="Search...">
                        </label>
                        <div class="selection"><p>Status</p></div>

                        <div class="categories" id="bill-status">
                            <p class="option">PAID</p>
                            <p class="option">PENDING</p>
                            <p class="option">OVERDUE</p>
                        </div>

                        <input type="hidden" name="status" id="status" value="none">

                        <button type="reset" class="resetButton"><i class="fas fa-sync-alt"></i></button>
                        <button id="get" type="button" class="resetButton"><i class="fas fa-paper-plane"></i></button>

                    </div>
                </form>
                <script>

                    // Get the status options elements
                    const statusOptions = document.querySelectorAll('#bill-status .option');

                    // Add an event listener to each status option
                    statusOptions.forEach(function(option) {
                        option.addEventListener('click', function() {
                            // Get the selected status
                            const selectedStatus = option.textContent.toLowerCase();

                            // Get all the rows in the table
                            const rows = document.querySelectorAll('#dataTable tr');

                            // Loop through all the rows
                            rows.forEach(function(row) {
                                // Get the status in the row
                                const status = row.cells[3].textContent.toLowerCase();

                                // Check if the status matches the selected status
                                if (status === selectedStatus) {
                                    // If it does, display the row
                                    row.style.display = '';
                                } else {
                                    // If it doesn't, hide the row
                                    row.style.display = 'none';
                                }
                            });
                        });
                    });

                    // Get the #get button element
                    const getButton = document.querySelector('#get');

                    // Get the table element
                    const table = document.querySelector('#dataTable');

                    // Add an event listener to the #get button
                    getButton.addEventListener('click', function() {
                        // Scroll to the bottom of the table
                        table.lastElementChild.scrollIntoView();
                    });

                </script>
            </div>
        </div>
    </section>
</main>
<%
    // Get the context path dynamically
    String contextPath = request.getContextPath();
%>
<script>
    // Set the context path as a JavaScript variable
    const contextPath = '<%= contextPath %>';

    let selection = document.querySelector(".selection");
    let selected_text = document.querySelector(".selection p");
    let categories = document.querySelector(".categories");
    let options = document.querySelectorAll(".categories p");


    let dropdown = document.querySelector(".selection p");
    let resetButton = document.querySelector(".resetButton");

    // Create a function to reset the search bar and dropdown
    function resetElements() {
        dropdown.innerHTML = "Status";
        document.getElementById('status').value = "none";

        const rows = document.querySelectorAll('#dataTable tr');

        rows.forEach(function(row) {
            row.style.display = '';
        });
    }

    // Add an event listener to the reset button
    resetButton.addEventListener("click", resetElements);


    selection.onclick = function(){
        categories.classList.toggle("active");
    }

    options.forEach(option => {
        option.onclick = function(){
            selected_text.innerHTML = option.innerHTML;
            categories.classList.toggle("active");

            document.getElementById('status').value = option.innerHTML;
        }
    });
</script>
<script type="module" src="<%= request.getContextPath() %>/public/JS/user.js" defer></script>
<script type="module" src="<%= request.getContextPath() %>/public/JS/fetch_bills.js" defer></script>
</body>
</html>

