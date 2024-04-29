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

    <style>
        #itemList table {
            border: none;
            border-collapse: collapse;
            width: 100%;
            color: #333;
            font-family: Arial, sans-serif;
            font-size: 12px;
            text-align: left;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            margin: auto;
            margin-top: 20px;
            margin-bottom: 0px;
            border: 1px solid #ffffff;
        }

        #itemList table th {
            background-color: #86498b;
            color: #fff;
            font-weight: bold;
            padding: 10px;
            letter-spacing: 1px;
            border-top: 1px solid #fff;
            border-bottom: 1px solid #ccc;
        }

        #itemList table tr:nth-child(even) td {
            background-color: #f2f2f2;
        }

        #itemList table tr:hover td {
            background-color: #f6ccff;
        }

        #itemList table td {
            background-color: #fff;
            padding: 10px;
            border-bottom: 1px solid #ccc;
            font-weight: bold;
        }
        #actionBtn {
            display: flex;
            justify-content: center;
            align-items: center;
        }
        #actionBtn i{
            border: none;
            color: white;
            padding: 12px 16px;
            font-size: 16px;
            cursor: pointer;
        }
        #delete {
            background-color: #ff6633;
        }
        #edit {
            background-color:  #ffb84d;
        }


    </style>
</head>
<body>

<section id="header" style="background-color: #5755FE;">
    <a href="index.html"><img id="logo" src="<%= request.getContextPath() %>/public/images/solar/logo.png" alt=""></a>
    <div id="proDe">
        <p> ${sessionScope.companyName}
        </p>
        <a href="#"><img id="proImg" src="<%= request.getContextPath() %>/public/images/solar/companyLogo.jpeg" alt=""></a>
    </div>
</section>


<div id="itemList" class="section-p1">

    <div id="btn">
        <a href="<%= request.getContextPath()%>/public/HTML/solar/addItem.jsp">Add Item</a>
    </div>

    <table>
        <tr>
            <th>Item Name</th>
            <th>Description</th>
            <th>Cost (Rs)</th>
            <th>Profit Margin (Rs)</th>
            <th>Price (Rs)</th>
            <th>Warranty Period (Months)</th>
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
                    <c:out value="${item.cost} "/>
                </td>
                <td>
                    <c:out value="${item.profitMargin} "/>
                </td>
                <td>
                    <c:out value="${item.price} "/>
                </td>
                <td>
                    <c:out value="${item.warrantyPeriod} "/>
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