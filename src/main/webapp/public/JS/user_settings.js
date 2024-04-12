const water_thresh = document.getElementById("waterThreshold");
const elec_thresh = document.getElementById("electricityThreshold");

function prevent_non_numeric(event) {
    const allowedKeys = [8, 9, 13, 27, 46, 37, 38, 39, 40];
    const decimalAllowed = event.target.value.includes(".") && event.key === ".";

    if (!allowedKeys.includes(event.keyCode) && !/^[0-9.]$/.test(event.key) || decimalAllowed) {
        event.preventDefault();
    }
}

if (water_thresh !== null) {
    water_thresh.addEventListener("keydown", prevent_non_numeric);
}

if (elec_thresh !== null) {
    elec_thresh.addEventListener("keydown", prevent_non_numeric);
}


const add_wacc = document.getElementById("add-btn_water");
const edit_wacc = document.getElementById("edit-btn_water");
const del_wacc = document.getElementById("del-btn_water");

// Function to handle form submission
function handleFormSubmit(event) {
    event.preventDefault();

    // Reset error messages
    resetErrorMessages();

    const old_password = document.getElementById("password").value;
    const new_password = document.getElementById("new-password").value;
    const confirm_new_password = document.getElementById("re-new-password").value;

    // Perform validation
    if (old_password.trim() === '') {
        showError('Old password is required');
        return;
    }

    if (new_password.trim() === '') {
        showError('New password is required');
        return;
    }

    if (confirm_new_password.trim() === '') {
        showError('Please confirm your new password');
        return;
    }

    if (new_password !== confirm_new_password) {
        showError('Passwords do not match');
        return;
    }

    document.getElementById('password-reset').submit();
}

function resetErrorMessages() {
    document.getElementById('error-message').textContent = '';
}

function showError(message) {
    document.getElementById('error-message').textContent = message;
}


export function checkPasswordStrength(password) {
    // Define regex patterns for different character types
    const lowercaseRegex = /[a-z]/;
    const uppercaseRegex = /[A-Z]/;
    const numberRegex = /[0-9]/;
    const specialCharRegex = /[!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?]/;

    // Check if password meets minimum length requirement
    const minLength = 8;
    if (password.length < minLength) {
        return 'Password must be at least ' + minLength + ' characters long';
    }

    // Check for presence of different character types
    if (!lowercaseRegex.test(password)) {
        return 'Password must contain at least one lowercase letter';
    }
    if (!uppercaseRegex.test(password)) {
        return 'Password must contain at least one uppercase letter';
    }
    if (!numberRegex.test(password)) {
        return 'Password must contain at least one number';
    }
    if (!specialCharRegex.test(password)) {
        return 'Password must contain at least one special character';
    }

    // Password meets all criteria, return success
    return 'Password strength: Strong';
}

// Function to handle input event
function handleInputEvent(event) {
    const password = event.target.value;
    document.getElementById("new-pwd").textContent = checkPasswordStrength(password)
}


const passwordInput = document.getElementById('new-password');
passwordInput.addEventListener('change', handleInputEvent);


const form = document.getElementById('password-reset');
form.addEventListener('submit', handleFormSubmit);

let popup = document.getElementById('popup');
let popupcontainer = document.getElementById('popupcontainer');
let popup2 = document.getElementById('popup2');
let popupcontainer2 = document.getElementById('popupcontainer2');

function openPopup1() {
      popup.classList.add("open-popup");
      popupcontainer.classList.add("open-popupcontainer");
}

function closePopup1() {
      popup.classList.remove("open-popup");
      popupcontainer.classList.remove("open-popupcontainer");
}

function openPopup2() {
    popup2.classList.add("open-popup2");
    popupcontainer2.classList.add("open-popupcontainer2");
}

function closePopup2() {
    popup2.classList.remove("open-popup2");
    popupcontainer2.classList.remove("open-popupcontainer2");
}