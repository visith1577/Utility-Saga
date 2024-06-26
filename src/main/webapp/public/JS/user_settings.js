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

function resetErrorDetails() {
    document.getElementById('error-message-details').textContent = '';
}

function showDetails(message) {
    document.getElementById('error-message-details').textContent = message;
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
const water_add = document.getElementById('water-add-btn');
const electricity_add = document.getElementById('electricity-add-btn');


function openPopup1() {
    console.log('btn pressed')
      popup.classList.add("open-popup");
      popupcontainer.classList.add("open-popupcontainer");
}

const edit_btn_water = document.getElementById('edit-btn_water');
if(edit_btn_water !== null) {
    edit_btn_water.addEventListener('click', () => {
        openPopup1();
        document.getElementById('water-add_btn').textContent = 'Deactivate';
        document.getElementById('water-add_btn').value = 'INACTIVE';
    });
}


const add_btn_water = document.getElementById('add-btn_water');
if(add_btn_water !== null) {
    add_btn_water.addEventListener('click', () => {
        openPopup1();
        document.getElementById('water-add_btn').textContent = 'Activate';
        document.getElementById('water-add_btn').value = 'ACTIVE';
    });
}

document.getElementById('water-add_btn').addEventListener('click', (event) => {
    event.preventDefault();
    let value = document.getElementById('accountActive-Number').value;

    if (document.getElementById(value) === null) {
        document.getElementById('popup-header_water').textContent = "Account not found";
        document.getElementById('popup-header_water').style.color = "red";
    } else {
        document.getElementById('action-water').value =  document.getElementById('water-add_btn').value;
        const form = document.getElementById('accountForm_water');
        form.submit();
    }
});

function closePopup1() {
      popup.classList.remove("open-popup");
      popupcontainer.classList.remove("open-popupcontainer");
}

document.getElementById('close-btn_water').addEventListener('click', closePopup1);
document.getElementById('close-btn2_water').addEventListener('click', closePopup1);

function openPopup2() {
    popup2.classList.add("open-popup2");
    popupcontainer2.classList.add("open-popupcontainer2");
}

const edit_btn_electricity = document.getElementById('edit-btn_electricity');
if (edit_btn_electricity !== null) {
    edit_btn_electricity.addEventListener('click', () => {
        openPopup2();
        document.getElementById('electricity-add_btn').textContent = 'Deactivate';
        document.getElementById('electricity-add_btn').value = 'INACTIVE';
    });
}


const add_btn_electricity = document.getElementById('add-btn_electricity');
if (add_btn_electricity !== null) {
    document.getElementById('add-btn_electricity').addEventListener('click', () => {
        openPopup2();
        document.getElementById('electricity-add_btn').textContent = 'Activate';
        document.getElementById('electricity-add_btn').value = 'ACTIVE'
    });
}

document.getElementById('electricity-add_btn').addEventListener('click', (event) => {
    event.preventDefault();
    let value = document.getElementById('accountNumber').value;

    if (document.getElementById(value) === null) {
        document.getElementById('popup-header_electricity').textContent = "Account not found";
        document.getElementById('popup-header_electricity').style.color = "red";
    } else {
        document.getElementById('action-electricity').value =  document.getElementById('electricity-add_btn').value
        const form = document.getElementById('accountForm_electricity');
        form.submit();
    }
});

function closePopup2() {
    popup2.classList.remove("open-popup2");
    popupcontainer2.classList.remove("open-popupcontainer2");
}

document.getElementById('close-btn_electricity').addEventListener('click', closePopup2);
document.getElementById('close-btn2_electricity').addEventListener('click', closePopup2);


const sub_electricity = document.getElementById('subscribe-electricity');
if (sub_electricity !== null) {
    sub_electricity.addEventListener('click', function (event) {
        event.preventDefault();
        Swal.fire({
            title: 'Are you sure?',
            text: "You are about to subscribe to electricity updates.",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, subscribe!'
        }).then((result) => {
            if (result.isConfirmed) {
                document.getElementById('subscribe-electricity').form.submit();
            }
        })
    });
}


const unsub_electricity = document.getElementById('unsubscribe-electricity');
if (unsub_electricity !== null) {
    unsub_electricity.addEventListener('click', function (event) {
        event.preventDefault();
        Swal.fire({
            title: 'Are you sure?',
            text: "You are about to unsubscribe from electricity updates.",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, unsubscribe!'
        }).then((result) => {
            if (result.isConfirmed) {
                document.getElementById('unsubscribe-electricity').form.submit();
            }
        })
    });
}

const sub_water = document.getElementById('subscribe-water')
if (sub_water !== null) {
    sub_water.addEventListener('click', function (event) {
        event.preventDefault();
        Swal.fire({
            title: 'Are you sure?',
            text: "You are about to subscribe to water updates.",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, subscribe!'
        }).then((result) => {
            if (result.isConfirmed) {
                document.getElementById('subscribe-water').form.submit();
            }
        })
    });
}


const  unsub_water = document.getElementById('unsubscribe-water')
if (unsub_water !== null) {
    unsub_water.addEventListener('click', function (event) {
        event.preventDefault();
        Swal.fire({
            title: 'Are you sure?',
            text: "You are about to unsubscribe from water updates.",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, unsubscribe!'
        }).then((result) => {
            if (result.isConfirmed) {
                document.getElementById('unsubscribe-water').form.submit();
            }
        })
    });
}
