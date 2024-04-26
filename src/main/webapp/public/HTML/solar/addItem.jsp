<%--
  Created by IntelliJ IDEA.
  User: ShyamalHAD
  Date: 4/23/2024
  Time: 8:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css">
    <link href='https://fonts.googleapis.com/css?family=Spartan' rel='stylesheet'>
    <link rel="stylesheet" href="../../CSS/solar/styleMain.css">
    <link rel="stylesheet" href="../../CSS/solar/form.css">
    <title>Add item</title>
</head>
<body>
<section id="header" >
    <a href="index.html"><img id="logo" src="../../images/solar/logo.png" alt=""></a>
    <div id="proDe">
        <p>Company Name</p>
        <a href="#"><img id="proImg" src="../../images/solar/companyLogo.jpeg" alt=""></a>

    </div>
</section>

<div class="container">
    <h2>Add Item</h2>
    <form action="<%= request.getContextPath()%>/item/add" method="post" enctype="multipart/form-data">

        <div class="form-group">
            <label for="imageFile">Select image to upload:</label>
            <input type="file" name="imageFile" id="imageFile">
        </div>
        <div class="form-group">
            <label for="itemName">Item Name</label>
            <input type="text" id="itemName" name="itemName" placeholder="Item Name" required>
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <input type="text" id="description" name="description" placeholder="Description" required>
        </div>
        <div class="form-group">
            <label for="cost">Cost $</label>
            <input type="number" id="cost" name="cost" placeholder="$Cost" step="any" min="0.00" required>
        </div>
        <div class="form-group">
            <label for="profitMargin">Profit Margin</label>
            <input type="number" id="profitMargin" name="profit_margin" placeholder="$Profit Margin" step="any" min="0.00" required>
        </div>
        <div class="form-group">
            <label for="price">Price </label>
            <input type="number" id="price" name="price" placeholder="$Price" step="any" min="0.00" required>
        </div>
        <div class="form-group">
            <label for="warrantyPeriod">Warranty Period (Months)</label>
            <input type="number" id="warrantyPeriod" name="warranty_period" placeholder="Warranty Period" required>
        </div>
        <div class="form-group">
            <label for="quantity">Quantity</label>
            <input type="number" id="quantity" name="quantity" placeholder="Quantity" required>
        </div>
        <div class="form-group">
            <label for="supplierID">Supplier ID</label>
            <input type="number" id="supplierID" name="supplier_id" placeholder="Supplier ID" required>
        </div>

        <div class="form-group">
            <button type="submit">Add Item</button>
        </div>
    </form>
</div>
</body>
</html>
