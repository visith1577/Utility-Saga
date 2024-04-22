<!--<%@ page contentType="text/html;charset=UTF-8" language="java" %>-->
<html>
<head>
    <title>Store</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link rel="stylesheet" href="../../CSS/solar/company.css">

</head>
<body>
<%--<section id="header"> </section>--%>

<%--<section id="editPro">--%>
<%--    <form action="">--%>
<%--        <lable> </lable>--%>
<%--    </form>--%>
<%--</section>--%>

<%--<section id="storeItem">--%>
<%--    <div class="itemS">--%>
<%--        <img src="" alt="">--%>
<%--        <p>Name</p>--%>
<%--        <p>Price</p>--%>
<%--        <p>Quantity</p>--%>
<%--        <i class="fas fa-plus"></i>--%>
<%--        <i class="fas fa-minus"></i>--%>
<%--    </div>--%>
<%--</section>--%>

<%--<section id="crud">--%>
    <button> <i class="fas fa-plus"></i> </button>
    <button> <i class="fas fa-minus"></i> </button>
    <button> <i class="fas fa-edit"></i> </button>
    <button> <i class="fas fa-trash"></i> </button>

<%--</section>--%>

<%--<section id="addItem">--%>
<%--    <form action="<%= request.getContextPath() %>/story" >--%>
<%--        <label for="iName"> Name </label>--%>
<%--        <input type="text" name="iName" id="iName">--%>

<%--        <label for="itemID"> Item ID</label>--%>
<%--        <input type="number" name="I=itemID" id="itemID">--%>

<%--        <label for="price"> Price </label>--%>
<%--        <input type="number" name="price"  id="price">--%>

<%--        <label for="qnty"> Quantity </label>--%>
<%--        <input type="number" name="qnty" id="qnty">--%>

<%--        <button type="submit">Submit</button>--%>
<%--        <button type="reset">Reset</button>--%>
<%--    </form>--%>
<%--</section>--%>

<%--<section>--%>
<%--    <form action="<%= request.getContextPath() %>/story">--%>
<%--        <label> Items to Add </label>--%>
<%--        <input type="number" name="" >--%>
<%--        <button type="submit">Submit</button>--%>
<%--        <button type="reset">Reset</button>--%>
<%--    </form>--%>
<%--</section>--%>
<%--<section id="footer">--%>

<%--<% String iNameParam = request.getParameter("iName");%>--%>
<%--    <% if (iNameParam == "1") { %>--%>
<%--    <p> Item Added Successfully </p>--%>
<%--    <% }--%>
<%--     else{ %>--%>
<%--    <p> Item Not Added </p>--%>
<%--    <% } %>--%>
<%--<%=iNameParam%>--%>
<%--</section>--%>

<section id="add">
    <form action="<%= request.getContextPath() %>/story" >
        <label for="iName"> Name </label>
        <input type="text" name="iName" id="iName">

        <label for="itemID"> Item ID</label>
        <input type="number" name="I=itemID" id="itemID">

        <label for="price"> Price </label>
        <input type="number" name="price"  id="price">

        <label for="qnty"> Quantity </label>
        <input type="number" name="qnty" id="qnty">

        <label for="ct"> Quantity </label>
        <input type="number" name="ct" id="ct">

        <button type="submit">Submit</button>
        <button type="reset">Reset</button>
    </form>
</section>

<section id="remove">
    <form action="<%= request.getContextPath() %>/story">
        <label for="rm"> Items to Remove </label>
        <input type="number" name="rm" id="rm" >
        <button type="submit">Submit</button>
        <button type="reset">Reset</button>
    </form>
</section>

<section id="listI">
    <form action="<%= request.getContextPath() %>/story">
        <label for="sr"> Items to search </label>
        <input type="number" name="sr" id="sr" >
        <button type="submit">Submit</button>
        <button type="reset">Reset</button>
    </form>

</section>

</body>
</html>
