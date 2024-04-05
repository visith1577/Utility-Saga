const setSuccess = (element, message) => {
    const input_control = element.parentElement;
    const error_display = input_control.querySelector(".error");

    error_display.innerText = message;
    if (input_control.classList.contains('error')) {
        input_control.classList.replace('error', 'success');
    } else {
        input_control.classList.add('success')
    }
}

const setError = (element, message) => {
    const input_control = element.parentElement;
    const error_display = input_control.querySelector(".error");

    error_display.innerText = message;
    if (input_control.classList.contains('success')) {
        input_control.classList.replace('success', 'error');
    } else {
        input_control.classList.add('error');
    }
}

window.addEventListener("load", () => {
    const adjustHeaderPadding = () => {
        const tblContentWidth = document.querySelector('.tbl-content').offsetWidth;
        const tblTableWidth = document.querySelector('.tbl-content table').offsetWidth;
        let scrollWidth = tblContentWidth - tblTableWidth;

        document.querySelector('.tbl-header').style.paddingRight = `${scrollWidth}px`;
    };

    // Initial adjustment on load
    adjustHeaderPadding();

    // Bind the adjustment function to the window resize event
    window.addEventListener("resize", adjustHeaderPadding);
});


const edit_button = document.getElementById("editButton");

edit_button.addEventListener('click', onToggle);

function onToggle() {
    let form = document.getElementById("user-profile");
    let inputs = form.getElementsByTagName('input');
    let file = document.getElementById("imageInput");
    let editButton = document.getElementById('editButton');
    let saveButton = document.getElementById('saveButton');
    let labels = form.querySelectorAll('#user-profile > label');

    for (let i = 0; i < inputs.length; i++) {
        if (inputs[i].name !== 'nic') {
            inputs[i].readOnly = !inputs[i].readOnly;
        }
    }

    if (editButton.textContent === 'Edit') {
        editButton.textContent = 'Cancel';
        file.style.display = 'block';
        saveButton.style.display = 'inline-block';

        for (let i = 0; i < labels.length; i++) {
            labels[i].style.display = 'block';
        }
    } else {
        editButton.textContent = 'Edit';
        file.style.display = 'none';
        saveButton.style.display = 'none';
        for (let i = 0; i < labels.length; i++) {
            labels[i].style.display = 'none';
        }
        window.location.reload();
    }
}

function validateInputs(tel_num, email, nic, uname) {
    console.log(tel_num)
    const tel_num_value = tel_num.value.trim();
    const email_value = email.value.trim();
    const nic_value = nic.value.trim();
    const uname_value = uname.value.trim();


    let tel_val = validate_telnum(tel_num_value, tel_num);
    let nic_valid = validate_nic(nic_value, nic);
    let email_valid = validate_email(email_value, email);
    let uname_valid = validate_uname(uname_value, uname);

    return (tel_val && nic_valid && email_valid && uname_valid)
}


function validate_telnum(tel_num_value, tel_num) {
    if (tel_num_value === '') {
        setError(tel_num, 'Mobile phone number is required');
        return false;
    } else if (isValidPhoneNumber(tel_num_value)) {
        setSuccess(tel_num, '')
        return true;
    } else {
        setError(tel_num, 'Invalid phone number')
        return false;
    }
}

function validate_email(email_value, email) {
    if (email_value === '') {
        setError(email, 'please enter a email')
        return false;
    } else if (isValidEmail(email_value)) {
        setSuccess(email, '')
        return true;
    } else {
        setError(email, 'Invalid email address')
        return false;
    }
}

function validate_nic(nic_value, nic) {
    if (nic_value === '' || !isValidNic(nic_value)) {
        setError(nic, 'please enter a valid nic number')
        return false;
    } else {
        setSuccess(nic, '');
        return true;
    }
}


function isValidPhoneNumber(value) {
    let phoneNumber = value.replace(/\s/g, '');
    const phone_regex =  /^(?:\+94|0)(?:7\d{8}|11\d{7}|3\d{9})$/;
    return phone_regex.test(phoneNumber)
}

function isValidEmail(email) {
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}

function isValidNic(nicNumber) {
    let result;
    if (nicNumber.length === 10 && !isNaN(nicNumber.substring(0, 9)) && isNaN(nicNumber.substring(9, 1)) && ['x', 'v'].includes(nicNumber.substring(9, 1).toLowerCase())) {
        result = true;
    } else result = nicNumber.length === 12 && !isNaN(nicNumber);
    return result;
}

function validate_uname(name_value, name) {
    if (name_value === '') {
        setError(name, 'please enter user name')
        return false;
    } else if (name_value.length > 15) {
        setError(name, 'name should be shorter (max=15)')
        return false;
    } else {
        setSuccess(name, '');
        return true;
    }
}

const nic = document.getElementById("nic");
const email = document.getElementById("email");
const tel_num = document.getElementById("tel");
const username = document.getElementById("username");
const user_profile = document.getElementById("user-profile");


// temp function
document.getElementById('user-profile').addEventListener('submit', (event) => {
    event.preventDefault();

    let result  = validateInputs(tel_num, email, nic, username);
    if (result) {
        user_profile.submit();
        console.log('Data Saved.');
    }
});


const list_item = document.getElementById("prof-id");

function hideListItem() {
    list_item.classList.add("hidden");
}

// Use setTimeout to delay hiding
setTimeout(hideListItem, 5000);
