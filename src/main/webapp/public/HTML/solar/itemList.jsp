<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="DAO.dao.ItemDetailsDAO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <p> Test.com${companyName}
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
        <tr>
            <td>Item Name</td>
            <td>Description</td>
            <td>Cost</td>
            <td>Profit Margin</td>
            <td>Price</td>
            <td>Warranty Period</td>
            <td>Quantity</td>
            <td>Supplier ID</td>
            <td class="btn-group">
                <button class="btn edit-btn"><i class="fa fa-pencil-square-o"></i></button>
                <button class="btn delete-btn"><i class="fa fa-trash"></i></button>
            </td>

        </tr>
        <tr>
            <td>Item Name</td>
            <td>Description</td>
            <td>Cost</td>
            <td>Profit Margin</td>
            <td>Price</td>
            <td>Warranty Period</td>
            <td>Quantity</td>
            <td>Supplier ID</td>
            <td class="btn-group">
                <button class="btn edit-btn"><i class="fa fa-pencil-square-o"></i></button>
                <button class="btn delete-btn"><i class="fa fa-trash"></i></button>
            </td>

        </tr>
        <tr>
            <td>Item Name</td>
            <td>Description</td>
            <td>Cost</td>
            <td>Profit Margin</td>
            <td>Price</td>
            <td>Warranty Period</td>
            <td>Quantity</td>
            <td>Supplier ID</td>
            <td class="btn-group">
                <button class="btn edit-btn"><i class="fa fa-pencil-square-o"></i></button>
                <button class="btn delete-btn"><i class="fa fa-trash"></i></button>
            </td>

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
                    <button href="<%= contextPath %>/item?itemID=<c:out value='${item.itemID}'/>" class="btn edit-btn">
                        <i class="fa fa-pencil-square-o"></i></button>
                    <button href="<%= contextPath %>/public/HTML/solar/editItem.jsp"
                        <c:out value="${item.itemID}" escapeXml="false"/>
                        class="btn delete-btn">
                        <i class="fa fa-trash"></i></button>
                        <%--Make a popup--%>
                </td>
            </tr>
        </c:forEach>

    </table>
</div>


</body>
</html>