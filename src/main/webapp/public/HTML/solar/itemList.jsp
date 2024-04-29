<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="DAO.dao.ItemDetailsDAO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="jakarta.mail.Session" %>
<%
    String contextPath = request.getContextPath();
%>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
    <link href='https://fonts.googleapis.com/css?family=Spartan' rel='stylesheet'>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/solar/styleMain.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/solar/form.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/solar/itemList.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/navbar.css">

    <style>
        #navv {
            display: flex;
            width: 100%;
            padding: 0px 10px;

        }

        #navbar {
            display: flex;
            width: 100%;
            margin: 0px;


        }

        #navCon {
            display: flex;
            width: 100%;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
            margin: 0px;
            max-width: 2000px;
        }

        #itemList {
            margin-top: 70px;
        }

        #itemList a {
            text-decoration: none;
        }

        #navLi {
            display: flex;
            align-items: center;
            margin-top: 0px;
        }

        #navLi li {

            margin-top: 0px;
        }


    </style>
</head>
<body>

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


<div id="itemList" class="section-p1" >

    <table>
        <tr>
            <th>Item Name</th>
            <th>Description</th>
            <th>Cost (Rs)</th>
            <th>Profit Margin (Rs)</th>
            <th>Price (Rs)</th>
            <th>Warranty Period</th>
            <th>Quantity</th>
            <th>Supplier ID</th>
            <th>Action</th>
        </tr>
        <c:forEach items="${listItems}" var="item">
            <tr>
                <td>
                    <c:out value="${item.itemName} "/>
                </td>
                <td>
                    <c:out value="${item.description} "/>
                </td>
                <td>
                    <p>Rs </p><c:out value="${item.cost} "/>
                </td>
                <td>
                    <p>Rs </p><c:out value="${item.profitMargin} "/>
                </td>
                <td>
                    <p>Rs </p><c:out value="${item.price} "/>
                </td>
                <td>
                    <c:out value="${item.warrantyPeriod} "/> <p> Months</p>
                </td>
                <td>
                    <c:out value="${item.quantity} "/>
                </td>
                <td>
                    <c:out value="${item.supplierID} "/>
                </td>
                <td id="actionBtn">

                    <form action="<%= contextPath %>/item/delete">
                        <input type="hidden" name="itemId" value="<c:out value='${item.itemID}'/>">
                        <input type="hidden" name="supplierId" value="<c:out value='${item.supplierID}'/>">
                        <button type="submit"><i class="fa fa-trash" id="delete"></i></button>
                    </form>
                    <form action="<%= contextPath %>/item/edit" method="get">
                        <input type="hidden" name="itemId" value="<c:out value='${item.itemID}'/>">
                        <button type="submit"><i class="fa fa-pencil-square-o" id="edit"></i></button>
                    </form>
                </td>
            </tr>
        </c:forEach>

    </table>
</div>

<div id="addItemLink">
    <a href="<%= request.getContextPath()%>/public/HTML/solar/addItem.jsp"><i class="fas fa-plus"> </i></a>
</div>

<script>
    function submitForm(method) {
        var form = document.getElementById('myForm');
        var formData = new FormData(form);
        var xhr = new XMLHttpRequest();
        xhr.open(method, '/items', true);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === XMLHttpRequest.DONE) {
                if (xhr.status === 200) {
                    // Handle successful response
                } else {
                    // Handle error response
                }
            }
        };
        xhr.send(formData);
    }
</script>
</body>
</html>


<button class="btn edit-btn" onclick="submitForm('PUT')">

</button>
<form action=">
                    <%= contextPath %>/item/delete?itemID=<c:out value='${item.itemID}'/>" method="delete">
    <button class="btn edit-btn" onclick="submitForm('DELETE')">

    </button>
</form>