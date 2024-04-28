<%--
  Created by IntelliJ IDEA.
  User: ShyamalHAD
  Date: 4/26/2024
  Time: 9:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<% String name = (String) session.getAttribute("UNAME"); %>
<html>
<head>
    <title>user Dashboard</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/dashboard.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/dashboards/user.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/skeleton.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/global.css">
</head>
<body>
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
<script type="module" src="<%= request.getContextPath() %>/public/JS/loader.js" defer></script>
<script type="module" src="<%= request.getContextPath() %>/public/JS/user.js" defer></script>
<script type="module" src="<%= request.getContextPath() %>/public/JS/fetch_bills.js" defer></script>
</body>
</html>