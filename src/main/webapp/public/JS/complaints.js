const form = document.getElementById('complaint_form');
const tel_num = document.getElementById('Telnum');
const email = document.getElementById('Email');
const nic = document.getElementById('CusNIC');
const button = document.getElementById('submit-btn')

form.addEventListener('submit', e => {
    e.preventDefault();

    let result  = validateInputs();
    let btn_type = 'waterbtn';

    if (result) {
        button.disabled = true;
        if (button.classList.contains('waterbtn')) {
            button.classList.replace('waterbtn','button-success');
        } else {
            button.classList.replace('electricitybtn','button-success');
            btn_type = 'electricitybtn'
        }

        setTimeout(() => {
            button.disabled = false;
            if (btn_type === 'waterbtn') {
                button.classList.replace('button-success', 'waterbtn');
            } else {
                button.classList.replace('button-success', 'electricitybtn');
            }
        }, 3205);
        send_data();
        this.submit;
    } else {
        button.disabled = true;
        if (button.classList.contains('waterbtn')) {
            button.classList.replace('waterbtn','button-error');
        } else {
            button.classList.replace('electricitybtn','button-error');
            btn_type = 'electricitybtn';
        }

        setTimeout(() => {
            button.disabled = false;
            if (btn_type === 'waterbtn') {
                button.classList.replace('button-error', 'waterbtn');
            } else {
                button.classList.replace('button-error', 'electricitybtn');
            }
        }, 3205);
        send_err();
    }
});

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

function send_data() {
    Toastify({
        text: "form submitted successfully",
        duration: 3000,
        newWindow: true,
        close: true,
        gravity: "top",
        positionLeft: true,
        stopOnFocus: true,
        style: {
            background: "linear-gradient(to right, #00b09b, #96c93d)",
        },
    }).showToast();
}

function  send_err() {
    Toastify({
        text: "form submission unsuccessful",
        className: 'toast-error',
        duration: 3000,
        newWindow: true,
        close: true,
        gravity: "top", // `top` or `bottom`
        position: "left", // `left`, `center` or `right`
        stopOnFocus: true, // Prevents dismissing of toast on hover
        style: {
            background: "linear-gradient(to right, #f00 0%, #c00 100%)",
        },
    }).showToast();
}

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

function validateInputs() {
    const tel_num_value = tel_num.value.trim();
    const email_value = email.value.trim();
    const nic_value = nic.value.trim();

    let tel_val = validate_telnum(tel_num_value);
    let nic_valid = validate_nic(nic_value);
    let email_valid = validate_email(email_value);

    return (tel_val && nic_value && email_valid)
}


function validate_telnum(tel_num_value) {
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

function validate_email(email_value) {
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

function validate_nic(nic_value) {
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
    let result = false;
    if (nicNumber.length === 10 && !isNaN(nicNumber.substring(0, 9)) && isNaN(nicNumber.substring(9, 1)) && ['x', 'v'].includes(nicNumber.substring(9, 1).toLowerCase())) {
        result = true;
    } else result = nicNumber.length === 12 && !isNaN(nicNumber);
    return result;
}




// test cases
// validatePhoneNumber('0777586061')
// console.log(validatePhoneNumber('+94771234567'))
// console.log(validatePhoneNumber('0712345678'))
// console.log(validatePhoneNumber('0111234567'))
// console.log(validatePhoneNumber('03123456789'))
