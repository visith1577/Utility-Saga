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
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/solar/styleMain.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/solar/form.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/navbar.css">
    <title>Add item</title>
    <style>
        #navv{
            display: flex;
            width: 100%;
            padding: 0px 10px;

        }
        #navbar{
            display: flex;
            width: 100%;
            margin: 0px;


        }
        #navCon{
            display: flex;
            width: 100%;
            justify-content: space-between;
            align-items: center;
            padding: 10px;
            margin: 0px;
            max-width: 2000px;
        }
        #hero{
            margin-top:70px;
        }
        #hero a{
            text-decoration: none;
        }

        #navLi  {
            display: flex;
            align-items: center;
            margin-top: 0px ;
        }
        #navLi li {

            margin-top: 0px ;
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

<div class="container-1" style="margin-top: 70px">
    <h2>Add Item</h2>
    <form action="<%= request.getContextPath()%>/item/add" method="post" enctype="multipart/form-data">


        <div class="form-group">
            <label for="itemName">Item Name</label>
            <input type="text" id="itemName" name="itemName" placeholder="Item Name" required>
        </div>
        <div class="form-group">
            <label for="description">Description</label>
            <input type="text" id="description" name="description" placeholder="Description" required>
        </div>
        <div class="form-group">
            <label for="cost">Cost (Rs)</label>
            <input type="number" id="cost" name="cost" placeholder="Rs 450.00" step="any" min="0.00" required>
        </div>
        <div class="form-group">
            <label for="profitMargin">Profit Margin (Rs)</label>
            <input type="number" id="profitMargin" name="profit_margin" placeholder="Rs 150.00" step="any" min="0.00" required>
        </div>
        <div class="form-group">
            <label for="price">Price (Rs)</label>
            <input type="number" id="price" name="price" placeholder="Rs 600.00" step="any" min="0.00" required>
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
            <input type="number" id="supplierID" name="supplierId" placeholder="Supplier ID" required>
        </div>
        <div class="form-group">
            <label for="imageFile">Select image to upload (Max 2MB):</label>
            <input type="file" name="imageFile" id="imageFile" accept="image/*" required>
        </div>
        <div class="form-group">
            <button type="submit">Add Item</button>
        </div>
    </form>
</div>


<script>
    document.getElementById('imageFile').addEventListener('change', function() {
        const file = this.files[0];
        const fileSize = file.size; // in bytes
        const maxSize = 2 * 1024 * 1024; // 2 MB

        if (fileSize > maxSize) {
            alert('File size exceeds 2MB. Please select a smaller file.');
            this.value = ''; // Clear the file input
        }
    });
</script>
</body>


</html>
