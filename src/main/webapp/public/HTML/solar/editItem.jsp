<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
    <link href='https://fonts.googleapis.com/css?family=Spartan' rel='stylesheet'>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/solar/styleMain.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/solar/form.css">
    <title>Add item</title>
</head>
<body>
<section id="header">
    <a href="index.html"><img id="logo" src="<%= request.getContextPath() %>/public/images/solar/logo.png" alt=""></a>
    <div id="proDe">
        <p>${sessionScope.companyName}</p>
        <a href="#"><img id="proImg" src=".<%= request.getContextPath() %>/public/images/solar/companyLogo.jpeg" alt=""></a>

    </div>
</section>

<p><%=request.getAttribute("item")%>
</p>

<div class="container">
    <h2>Edit Item</h2>
    <form action="<%= request.getContextPath()%>/item/edit" method="post">
        <div class="form-group" style="display: none;">
            <label for="itemName">Item Id</label>
            <input type="text" id="itemId" name="itemId" value="${item.itemID}" required>
        </div>
        <div class="form-group">
            <label for="itemName">Item Name</label>
            <input type="text" id="itemName" name="itemName" placeholder="Item Name" value="${item.itemName}" required>
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <input type="text" id="description" name="description" value="${item.description}" placeholder="Description"
                   required>
        </div>
        <div class="form-group">
            <label for="cost">Cost</label>
            <input type="number" id="cost" name="cost" placeholder="Cost" value="${item.cost}" required>
        </div>
        <div class="form-group">
            <label for="profitMargin">Profit Margin</label>
            <input type="number" id="profitMargin" name="profit_margin" value="${item.profitMargin}"
                   placeholder="Profit Margin" required>
        </div>
        <div class="form-group">
            <label for="price">Price</label>
            <input type="number" id="price" name="price" placeholder="Price" value="${item.price}" required>
        </div>
        <div class="form-group">
            <label for="warrantyPeriod">Warranty Period</label>
            <input type="number" id="warrantyPeriod" name="warranty_period" value="${item.warrantyPeriod}"
                   placeholder="Warranty Period" required>
        </div>
        <div class="form-group">
            <label for="quantity">Quantity</label>
            <input type="number" id="quantity" name="quantity" value="${item.quantity}" placeholder="Quantity" required>
        </div>
        <div class="form-group" style="display: none;">
            <label for="supplierID">Supplier ID</label>
            <input type="number" id="supplierID" name="supplierId" value="${item.supplierID}" placeholder="Supplier ID"
                   required>
        </div>
<%--        <div class="form-group">--%>
<%--            <label for="imageFile">Select image to upload (Max 2MB):</label>--%>
<%--            <input type="file" name="imageFile" id="imageFile" accept="image/*" >--%>
<%--        </div>--%>
        <div class="form-group">
            <button type="submit">Submit</button>
        </div>
    </form>
</div>
</body>
</html>
