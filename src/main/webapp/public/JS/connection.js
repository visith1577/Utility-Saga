export const setError = (element, message) => {
    const input_control = element.parentElement;
    const error_display = input_control.querySelector(".error");

    error_display.innerText = message;
    if (input_control.classList.contains('success')) {
        input_control.classList.replace('success', 'error');
    } else {
        input_control.classList.add('error');
    }
}

export  function send_data() {
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

export function  send_err() {
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

export const setSuccess = (element, message) => {
    const input_control = element.parentElement;
    const error_display = input_control.querySelector(".error");

    error_display.innerText = message;
    if (input_control.classList.contains('error')) {
        input_control.classList.replace('error', 'success');
    } else {
        input_control.classList.add('success')
    }
}

export function validateInputs(tel_num, email, nic) {
    const tel_num_value = tel_num.value.trim();
    const email_value = email.value.trim();
    const nic_value = nic.value.trim();


    let tel_val = validate_telnum(tel_num_value, tel_num);
    let nic_valid = validate_nic(nic_value, nic);
    let email_valid = validate_email(email_value, email);

    return (tel_val && nic_valid && email_valid)
}


export function validate_telnum(tel_num_value, tel_num) {
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

export function validate_email(email_value, email) {
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

export function validate_nic(nic_value, nic) {
    if (nic_value === '' || !isValidNic(nic_value)) {
        setError(nic, 'please enter a valid nic number')
        return false;
    } else {
        setSuccess(nic, '');
        return true;
    }
}


export function isValidPhoneNumber(value) {
    let phoneNumber = value.replace(/\s/g, '');
    const phone_regex =  /^(?:\+94|0)(?:7\d{8}|11\d{7}|3\d{9})$/;
    return phone_regex.test(phoneNumber)
}

export function isValidEmail(email) {
    const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}

export function isValidNic(nicNumber) {
    let result;
    if (nicNumber.length === 10 && !isNaN(nicNumber.substring(0, 9)) && isNaN(nicNumber.substring(9, 1)) && ['x', 'v'].includes(nicNumber.substring(9, 1).toLowerCase())) {
        result = true;
    } else result = nicNumber.length === 12 && !isNaN(nicNumber);
    return result;
}

export function toast_handle(result, btn_type) {
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
        connection_form.submit();
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
}

// handling radio selector

const connection = document.getElementById("connection");
const disconnection = document.getElementById("disconnection");
const type_div = document.getElementById("type-div");
const acc_div = document.getElementById("acc-div");
const new_addr = document.getElementById("NewConnectionAddress");
const new_reg = document.getElementById("region");
const new_addr_div = document.getElementById("new_addr-div");
const new_reg_div = document.getElementById("new_reg-div");
const w_connection = document.getElementById("water-connection");
const w_disconnection = document.getElementById("water-disconnection");
const reg_elem = document.getElementById("region");
const reg_label = document.querySelector(`label[for="${reg_elem.id}"]`);


if (connection != null) {
    connection.addEventListener('change', () => {
        if (acc_div.classList.contains("forminput")) {
            acc_div.classList.remove("forminput");
        }

        reg_label.textContent = "New Connection Region"

        acc_div.required ? acc_div.required = false : acc_div.required = false;

        !new_addr.required ? new_addr.required = true : new_addr.required = true;
        !type_div.required ? type_div.required = true : type_div.required = true;
        new_addr_div.classList.add("forminput");
        type_div.classList.add("forminput");
    });
}

if (disconnection != null) {
    disconnection.addEventListener('change', () => {
        if (type_div.classList.contains("forminput")) {
            type_div.classList.remove("forminput");
        }

        type_div.required ? type_div.required = false : type_div.required = false;

        if (new_addr_div.classList.contains("forminput")) {
            new_addr_div.classList.remove("forminput");
        }

        reg_label.textContent = "Connection Region"

        new_addr.required ? new_addr.required = false : new_addr.required = false;
        !acc_div.required ? acc_div.required = true : acc_div.required = true
        acc_div.classList.add("forminput")
    });
}

if (w_connection != null) {
    w_connection.addEventListener('change', () => {
        reg_label.textContent = "New Connection Region"
        new_addr_div.classList.add("forminput")
    });
}

if (w_disconnection != null) {
    w_disconnection.addEventListener('change', () => {
        reg_label.textContent = "Connection Region"
        if (new_addr_div.classList.contains("forminput")) {
            new_addr_div.classList.remove("forminput");
        }
    });
}


// validating inputs

let connection_form = document.getElementById("connection-form");
const tel_num = document.getElementById('Telnum');
const email = document.getElementById('Email');
const nic = document.getElementById('CusNIC');
const button = document.getElementById('submit-btn')

connection_form.addEventListener('submit', e => {
    e.preventDefault();

    let result  = validateInputs(tel_num, email, nic);
    let btn_type = 'waterbtn';

    toast_handle(result, btn_type);
});


