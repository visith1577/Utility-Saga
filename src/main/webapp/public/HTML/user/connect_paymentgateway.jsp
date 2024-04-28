<%--
  Created by IntelliJ IDEA.
  User: NETHMI LIYANAGE
  Date: 4/26/2024
  Time: 10:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment Portal</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/connect_paymentgateway.css">
</head>
<body>
<h2>Bill Payment Portal</h2>
<p>Enter your details and amounts that you want to pay</p>
<div class="formbox">
    <form id="payhereForm" method="POST" action="https://sandbox.payhere.lk/pay/checkout">
        <input type="hidden" name="merchant_id" value="1226570">
        <input type="hidden" name="return_url"
               value="http://localhost:8080/UtilitySaga_war_exploded/public/HTML/user/user.jsp">
        <input type="hidden" name="cancel_url"
               value="http://localhost:8080/UtilitySaga_war_exploded/public/HTML/user/connect_paymentgateway.jsp">
        <input type="hidden" name="notify_url"
               value="http://localhost:8080/UtilitySaga_war_exploded/public/HTML/user/connect_paymentgateway.jsp">

        <label>Account Number</label>
        <input type="text" name="order_id" id="order_id">
        <label>NIC: </label>
        <input type="text" name="items">
        <label>Currency: </label>
        <input type="text" name="currency" id="currency" value="LKR" required>
        <label>Value: </label>
        <input type="text" name="amount" id="amount" required>
        <hr>
        <label>Your Name: </label>
        <input type="text" name="first_name">
        <label>Last Name: </label>
        <input type="text" name="last_name">
        <label>Email: </label>
        <input type="email" name="email" id="email" required><br>
        <label>Telephone Number: </label>
        <input type="text" name="phone">
        <input type="hidden" name="address" value="No.1, Galle Road">
        <input type="hidden" name="city" value="Colombo">
        <input type="hidden" name="country" value="Sri Lanka">
        <input type="hidden" name="hash" id="hashInput"> <!-- HashValue from js  -->
        <div class="btnbox">
            <button type="button" onclick="submitPayHereForm()">Pay Now</button>
        </div>
    </form>
</div>
<script>

    window.onload = function() {
        function getQueryParam(name) {
            const urlParams = new URLSearchParams(window.location.search);
            return urlParams.get(name);
        }

        var accountNumber = getQueryParam("accountNumber");

        document.getElementById('order_id').value = accountNumber;
    };


    async function submitPayHereForm() {

        var orderId = document.getElementById('order_id').value;
        var amount = document.getElementById('amount').value;
        var currency = document.getElementById('currency').value;
        // console.log(orderId,amount,currency);

        // Generate hash value
        try {
            const hash = await generateHash(orderId, amount, currency);
            document.getElementById('hashInput').value = hash;
            console.log(hash);
            document.getElementById('payhereForm').submit();
        } catch (error) {
            console.error("Error generating and setting hash:", error);
        }
    }

    async function generateHash(orderId, amount, currency) {
        return new Promise((resolve, reject) => {

            const requestOptions = {
                method: "GET",
                redirect: "follow"
            };

            fetch("http://localhost:8080/UtilitySaga_war_exploded/payment?orderId=" + orderId + "&amount=" + amount + "&currency=" + currency, requestOptions)
                .then((response) => {
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }
                    return response.text();
                }).then((result) => {
                console.log(result);
                resolve(result);
            })
                .catch((error) =>
                {
                    console.error("Failed to fetch hash:", error);
                    reject(error);
                });
        });
    }
</script>
</body>
</html>
