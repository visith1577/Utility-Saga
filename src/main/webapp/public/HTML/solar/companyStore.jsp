<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="java.util.Base64" %>
<%@ page import="model.ItemModel" %>
<%@ page import="java.util.ArrayList" %>

<%
    // Assuming you have retrieved the list of ItemModel objects from the backend
    ArrayList<ItemModel> items = (ArrayList<ItemModel>) request.getAttribute("listItems");

    // Loop through each item in the list
    for (ItemModel item : items) {
        String imageName = item.getImageName();
        InputStream imageStream = item.getImageStream();

        // Convert imageStream to byte array
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = imageStream.read(buffer)) != -1) {
            baos.write(buffer, 0, bytesRead);
        }
        byte[] imageData = baos.toByteArray();

        // Encode the image data to Base64
        String base64Image = Base64.getEncoder().encodeToString(imageData);

        // Set the Base64-encoded image data back to the item object
        item.setBase64Image(base64Image);
    }
%>


<html>
<head>
    <title>Store</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link href='https://fonts.googleapis.com/css?family=Spartan' rel='stylesheet'>
    <link link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/solar/styleMain.css">
    <link link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/solar/store.css">
    <link link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/solar/companyStoreMain.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/solar/companyStoreMain.css">

</head>
<body>
<section id="header" style="background-color: #FFF5E0">
    <a href="#"><img id="logo" src="<%= request.getContextPath() %>/public/images/solar/logo.png" alt=""></a>
    <p id="date-container" style="font-size: 25px"></p>
</section>

<section id="hero"
         style="background-image: url(<%= request.getContextPath() %>/public/images/solar/hero.jpg); color: black">
    <h4 style="color: black">Trade-in-Offer</h4>
    <h2 style="color: black">Super Value deals</h2>
    <h1 style="color: black">On all products</h1>
    <p style="color: black">Save more Energy more Money!</p>
    <form action="#product1" method="get">

        <button type="submit"> Shop Now</button>
    </form>
</section>

<section id="product1" class="section-p1" >
    <h2>Arrivels</h2>
    <p>Suggestet For you</p>
    <div class="proContainer">


        <c:forEach items="${listItems}" var="item" varStatus="loop">
            <div class="pro">
                <img src="data:image/jpeg;base64,${item.base64Image}" alt="Item Image">
                <div class="dis">
                    <span><c:out value="${item.itemName}" /></span>
                    <h5><c:out value="${item.cost}" /></h5>
                    <h4><c:out value="${item.quantity}" /></h4>
                </div>
                <a href="#" class="cart-link" data-view-id="view-${loop.index}"><i class="fas fa-shopping-cart cart"></i></a>
            </div>
            <section id="view-${loop.index}" class="section-p1" style="display: none;">
                <i class="fas fa-x" id="cut"></i>
                <div id="imgView">
                    <img src="../../images/solar/solar_panal.png">
                </div>
                <div id="details">

                    <h4><c:out value="${item.quantity}" /></h4>
                </div>
            </section>
        </c:forEach>

    </div>


</section>


<%--<section id="view" class="section-p1">--%>

<%--</section>--%>




<footer class="section-p1">
    <div class="footerCol">
        <img class="logo" src="img/logo.png" alt="">
        <h4>Contact</h4>
        <p><strong>Address</strong>: 1234 Street Adress City Address, 1234</p>
        <p><strong>Phone</strong>: (077) 456-7890</p>
        <p><strong>Fax</strong>: (077) 456-7890</p>
        <p><strong>Email</strong>: utilitySaga@gmail.com </p>

    </div>
    <div class="footerCol">
        <h4>About</h4>
        <a href="<%= request.getContextPath()%>/public/HTML/pages/aboutUs.jsp">About us</a>
        <a href="#">Privacy and Policy</a>
        <a href="#">Terms and Condition</a>
        <a href="#">Contact Us</a>
    </div>
    <div class="footerCol">

    </div>

</footer>



<script>
    function getCurrentDateAndTime() {
        const dateTime = new Date();
        const hour = dateTime.getHours();
        let greeting;

        if (hour >= 0 && hour < 12) {
            greeting = "Good morning !";
        } else if (hour >= 12 && hour < 17) {
            greeting = "Good afternoon !";
        } else {
            greeting = "Good evening !";
        }

        return greeting;
    }

    // Target an HTML element to display the current date and time
    const dateDisplay = document.getElementById("date-container");

    // Set the innerHTML of the element to the current date and time returned by the function
    dateDisplay.innerHTML = getCurrentDateAndTime();


    document.addEventListener("DOMContentLoaded", function () {
        // Use event delegation to handle click events for dynamically generated cart links
        document.addEventListener("click", function (event) {
            const cartLink = event.target.closest(".cart-link");
            if (cartLink) {
                event.preventDefault(); // Prevent the default behavior of the link

                // Get the ID of the corresponding view section
                const viewId = cartLink.dataset.viewId;
                const viewSection = document.getElementById(viewId);

                // Show the view section
                if (viewSection) {
                    viewSection.style.display = "flex";
                }
            }

            // Handle click events for the cut icon to close the view section
            const cutIcon = event.target.closest("#cut");
            if (cutIcon) {
                const viewSection = cutIcon.closest("#view");
                if (viewSection) {
                    viewSection.style.display = "none";
                }
            }
        });
    });
</script>
</body>


</html>