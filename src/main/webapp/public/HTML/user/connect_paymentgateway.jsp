<%@ page import="model.BillModel" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: NETHMI LIYANAGE
  Date: 4/26/2024
  Time: 10:11 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String nic = (String) session.getAttribute("NIC"); %>
<%
    String contextPath = request.getContextPath();
%>
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
               value="http://localhost:8080/UtilitySaga_war_exploded/user/user-profile">
        <input type="hidden" name="cancel_url"
               value="http://localhost:8080/UtilitySaga_war_exploded/public/HTML/user/connect_paymentgateway.jsp">
        <input type="hidden" name="notify_url"
               value="http://localhost:8080/UtilitySaga_war_exploded/public/HTML/user/connect_paymentgateway.jsp">

<%--        <label>Bill Number</label>--%>
<%--        <input>--%>
<%--        <div class="dropdownclass">--%>
<%--            <select id="electricity_account">--%>
<%--                <% List<BillModel> electricityAccountList = (List<BillModel>) request.getAttribute("billId"); %>--%>
<%--                <% if (electricityAccountList != null && !electricityAccountList.isEmpty()) { %>--%>
<%--                <option value="" disabled selected>Select your Account Number</option>--%>
<%--                <% for (BillModel account : electricityAccountList) { %>--%>
<%--                <option value="<%= account.getBillId() %>"><%= account.getBillId() %>--%>
<%--                </option>--%>
<%--                <% } %>--%>
<%--                <% } else { %>--%>
<%--                <p>No electricity accounts available.</p>--%>
<%--                <% } %>--%>
<%--            </select>--%>
<%--        </div>--%>


        <label>Account Number</label>
        <input type="text" name="order_id" id="order_id" readonly>
        <label>NIC: </label>
        <input type="text" name="items" value="<%=nic%>" readonly>
        <label>Currency: </label>
        <input type="text" name="currency" id="currency" value="LKR" required>
        <label>Value: </label>
        <input type="text" name="amount" id="amount" required>
        <hr>
        <label>First Name: </label>
        <input type="text" name="first_name">
        <label>Last Name: </label>
        <input type="text" name="last_name">
        <label>Email: </label>
        <input type="email" name="email" id="email" required><br>
        <label>Telephone Number: </label>
        <input type="text" name="phone" id="phone"
               placeholder="Enter mobile number"
               oninput="this.value = this.value.replace(/[^0-9]/g, '');"
               required>


        <input type="hidden" name="address" value="No.1, Galle Road">
        <input type="hidden" name="city" value="Colombo">
        <input type="hidden" name="country" value="Sri Lanka">
        <input type="hidden" name="hash" id="hashInput">
        <div class="btnbox">
            <button type="button" onclick="submitPayHereForm()">Pay Now</button>
        </div>
    </form>
</div>


<script>

    const phone_regex = /^(?:\+94|0)(?:7\d{8}|11\d{7}|3\d{9})$/;
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const lowercaseRegex = /[a-z]/;
    const uppercaseRegex = /[A-Z]/;
    const numberRegex = /[0-9]/;
    const specialCharRegex = /[!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?]/;
    const minLength = 8;
    window.onload = function() {
        function getQueryParam(param) {
            const urlParams = new URLSearchParams(window.location.search);
            return urlParams.get(param);
        }


        var accountNumber = getQueryParam("accountNumber");

        document.getElementById('order_id').value = accountNumber;
    };


    async function submitPayHereForm() {

        var orderId = document.getElementById('order_id').value;
        var amount = document.getElementById('amount').value;
        var currency = document.getElementById('currency').value;
        var accountNumber = document.getElementById('order_id').value;
        console.log(accountNumber);
        // var billId = document.getElementById('billId').value;
        // console.log(orderId,amount,currency);

        var mobileInput=document.getElementById('phone');
        var emailInput=document.getElementById('email');

        // Generate hash value
        try {
            const hash = await generateHash(orderId, amount, currency);
            document.getElementById('hashInput').value = hash;
            console.log(hash);

            if(accountNumber.length===10){
                console.log(accountNumber);
                fetch('${pageContext.request.contextPath}/user/electricity/payment', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        accountNumber: accountNumber,
                        // billId: billId,
                        amount: amount
                    })
                }).then(response =>{
                    if(!response.ok){
                        throw new Error('Response was not ok');
                    }
                }).catch(error => {
                    console.error('Error:', error);
                    alert("Error while posting data");
                });
            }

            else if(accountNumber.length===12){
                fetch('${pageContext.request.contextPath}/user/water/payment', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        accountNumber: accountNumber,
                        // billId: billId,
                        amount: amount
                    })
                }).then(response =>{
                    if(!response.ok){
                        throw new Error('Response was not ok');
                    }
                }).catch(error => {
                    console.error('Error:', error);
                    alert("Error while posting data");
                });
            }

            let isValid = true;

            if (!phone_regex.test(mobileInput.value) || mobileInput.value.length !== 10) {
                alert('Invalid mobile number');
                console.log("Mobile number validation called");
                isValid = false;
            }

            if (!emailRegex.test(emailInput.value)) {
                alert('Invalid email address');
                console.log("Contact number validation called");
                isValid = false;
            }

            // Form submission based on validation
            if (!isValid) {
                console.log("Invalidated");
                return; // Prevent form submission if validation fails
            }

            // If validation passes, submit the form
            document.getElementById('payhereForm').submit();
        } catch (error) {
            console.error("Error generating and setting hash:", error);
        }
        form.addEventListener('submit', (event) => {
            event.preventDefault();
            let isValid = true;

            if (!phone_regex.test(mobileInput.value) || mobileInput.value.length !== 10) {
                alert('Invalid mobile number');
                console.log("Mobile number validation called");
                isValid = false;
            }

            if (!emailRegex.test(emailInput.value)) {
                alert('Invalid email address');
                console.log("Contact number validation called");
                isValid = false;
            }

            if (!isValid) {
                console.log("Invalidated");
                event.preventDefault();
            } else {
                form.submit();
            }
        });

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
