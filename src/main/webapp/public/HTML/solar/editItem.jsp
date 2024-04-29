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
            <label for="cost">Cost (Rs)</label>
            <input type="number" id="cost" name="cost" placeholder="Cost" value="${item.cost}" required>
        </div>
        <div class="form-group">
            <label for="profitMargin">Profit Margin (Rs)</label>
            <input type="number" id="profitMargin" name="profit_margin" value="${item.profitMargin}"
                   placeholder="Profit Margin" required>
        </div>
        <div class="form-group">
            <label for="price">Price (Rs)</label>
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

        <div class="form-group">
            <button type="submit">Submit</button>
        </div>
    </form>
</div>
</body>
</html>
