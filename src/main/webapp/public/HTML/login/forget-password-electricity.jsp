<%--
  Created by IntelliJ IDEA.
  User: Liviru Weerasinghe
  Date: 4/26/2024
  Time: 08:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Password Reset</title>
    <script>
        function sendEmail(event) {
            event.preventDefault();
            const email = document.getElementById("email").value;
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

            if (!emailRegex.test(email)) {
                console.log("Email is invalid");
                alert("Please enter a valid email address.");
                return;
            }

            const spinner = document.getElementById("spinner");
            spinner.style.display = "block";

            fetch('${pageContext.request.contextPath}/forget-password', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                body: 'email=' + encodeURIComponent(email)
            })
                .then(response => {
                    spinner.style.display = "none";
                    if (response.ok) {
                        console.log("Email sent successfully");
                        document.getElementById("emailForm").style.display = "none";
                        document.getElementById("otpForm").style.display = "block";
                    } else {
                        console.error("Error sending email");
                        alert("An error occurred while sending the email.");
                    }
                })
                .catch(error => {
                    spinner.style.display = "none";
                    console.error('Error:', error);
                    alert("An error occurred while sending the email.");
                });
        }

        async function validateOtp() {
            var otp = document.getElementById("otp").value;
            var email = document.getElementById("email").value;

            try {
                const response = await fetch('${pageContext.request.contextPath}/validate-otp', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    },
                    body: 'email=' + encodeURIComponent(email) + '&otp=' + encodeURIComponent(otp)
                });

                const result = await response.text();
                console.log(result)
                if (result === 'valid') {
                    console.log("valid");
                    document.getElementById("otpForm").style.display = "none";
                    document.getElementById("passwordForm").style.display = "block";
                } else {
                    console.log("invalid");
                    alert("Invalid OTP. Please try again.");
                }
            } catch (error) {
                console.error('Error:', error);
                alert("An error occurred while validating the OTP.");
            }
        }

        function validatePassword() {
            var newPassword = document.getElementById("newPassword").value;
            console.log("newPassword: ", newPassword);
            var confirmPassword = document.getElementById("confirmPassword").value;
            console.log("confirmPassword: ", confirmPassword);

            if (newPassword !== confirmPassword) {
                alert("Passwords do not match. Please try again.");
                return false;
            }

            if (newPassword.length < 8) {
                alert("Password should be at least 8 characters long.");
                return false;
            }
            return true;
        }

        window.addEventListener('DOMContentLoaded', function() {
            var resetPasswordForm = document.getElementById("resetPasswordForm");
            resetPasswordForm.addEventListener("submit", function(event) {
                if (!validatePassword()) {
                    event.preventDefault();
                }
            });
        });

    </script>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        .container {
            max-width: 400px;
            margin: 50px auto;
            padding: 20px;
            background-color: #f4f4f4;
            border-radius: 8px;
            box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            font-weight: bold;
        }

        input[type="email"],
        input[type="text"] {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button {
            width: 100%;
            padding: 10px;
            font-size: 16px;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }

        .spinner {
            border: 4px solid #f3f3f3;
            border-top: 4px solid #3498db;
            border-radius: 50%;
            width: 30px;
            height: 30px;
            animation: spin 2s linear infinite;
            margin: 0 auto;
        }

        @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
        }
    </style>
</head>
<body>
<div class="container" id="emailForm">
    <h2>Password Reset</h2>
    <form id="resetForm" onsubmit="sendEmail(event)">
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email"
                   placeholder="Enter the email"
                   required>
        </div>
        <button type="submit">Send OTP</button>
    </form>
    <div id="spinner" class="spinner" style="display: none;"></div>
</div>

<div class="container" id="otpForm" style="display: none">
    <h2>Enter OTP</h2>
    <form onsubmit="validateOtp(); return false;">
        <div class="form-group">
            <label for="otp">OTP:</label>
            <input type="text" id="otp" name="otp"
                   placeholder="Enter OTP"
                   required>
        </div>
        <button type="submit">Verify OTP</button>
    </form>
</div>

<div class="container" id="passwordForm" style="display: none;">
    <h2>Reset Password</h2>
    <form action="${pageContext.request.contextPath}/electricity/reset-password" id="resetPasswordForm" method="post">
        <div class="form-group">
            <label for="newPassword">New Password:</label>
            <input type="password" id="newPassword" name="newPassword" placeholder="Enter new password" required>
        </div>
        <div class="form-group">
            <label for="confirmPassword">Confirm Password:</label>
            <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm new password" required>
        </div>
        <button type="submit">Reset Password</button>
    </form>
</div>

</body>
</html>
