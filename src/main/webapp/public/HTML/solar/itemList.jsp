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
            <th>Cost</th>
            <th>Profit Margin</th>
            <th>Price</th>
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
                <td class="btn-group">
                        <%--                    <button type="button" >Update</button>--%>
                    <form action="<%= contextPath %>/item/delete">
                        <input type="hidden" name="itemId" value="<c:out value='${item.itemID}'/>">
                        <input type="hidden" name="supplierId" value="<c:out value='${item.supplierID}'/>">

                        <button type="submit"><i class="fa fa-trash">
                        </i></button>
                    </form>
                    <form action="<%= contextPath %>/item/edit" method="get">
                        <input type="hidden" name="itemId" value="<c:out value='${item.itemID}'/>">
                        <button type="submit"><i class="fa fa-trash">
                        </i></button>
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
    <i class="fa fa-pencil-square-o">
        <a href="<%= contextPath %>/item/edit?itemID=<c:out value='${item.itemID}'/>"></a>
    </i>
</button>
<form action=">
                    <%= contextPath %>/item/delete?itemID=<c:out value='${item.itemID}'/>" method="delete">
    <button class="btn edit-btn" onclick="submitForm('DELETE')">

    </button>
</form>