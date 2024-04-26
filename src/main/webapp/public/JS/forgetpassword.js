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

    fetch('${pageContext.request.contextPath}/user/forget-password', {
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
        const response = await fetch('${pageContext.request.contextPath}/user/validate-otp', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: 'email=' + encodeURIComponent(email) + '&otp=' + encodeURIComponent(otp)
        });

        const result = await response.text();
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