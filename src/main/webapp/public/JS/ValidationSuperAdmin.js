const phone_regex = /^(?:\+94|0)(?:7\d{8}|11\d{7}|3\d{9})$/;
const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
const lowercaseRegex = /[a-z]/;
const uppercaseRegex = /[A-Z]/;
const numberRegex = /[0-9]/;
const specialCharRegex = /[!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?]/;
const minLength = 8;
console.log("Context Path: ",contextPath);

function initializeValidationWater() {
    console.log("Water Validation called");
    const form = document.getElementById('waddForm');

    if (form) {
        const passwordInput = document.getElementById('wpassword');
        console.log("Password:", passwordInput);
        const rePasswordInput = document.getElementById('re-wpassword');
        console.log("RePassword:", rePasswordInput);
        const mobileInput = document.getElementById('wmobile');
        console.log("Mobile:", mobileInput);
        const contactInput = document.getElementById('wcontact');
        console.log("Contact:", contactInput);
        const emailInput = document.getElementById('wemail');
        console.log("Password:", passwordInput);
        const passwordCheckElement = document.getElementById('wpassword-check');
        console.log("Password Check:", passwordCheckElement);

        const empidInput = document.getElementById('wempid');
        console.log("EmpID:", empidInput);
        const regionInput = document.getElementById('wregion');
        console.log("Region:", regionInput);

        empidInput.addEventListener('input', async (event) => {
            const empid = empidInput.value.trim();
            if(empid) {
                event.preventDefault();
                fetch(contextPath + `/super-admin/water/validation?empid=` + empid
                ).then(response => {
                    return response.json();
                }).then(data => {
                    const isEmpIdExists = data.EmpIdExists;
                    if (isEmpIdExists) {
                        alert('EmpId already exists');
                    }
                }).catch(error => {
                    console.error('Error validating empid:', error);
                });
            }
        });

        emailInput.addEventListener('input', async (event) => {
            const email = emailInput.value.trim();
            if(email) {
                event.preventDefault();
                fetch(contextPath + `/super-admin/water/validation?email=` + email
                ).then(response => {
                    return response.json();
                })
                    .then(data => {
                        const isEmailExists = data.EmailExists;
                        if (isEmailExists) {
                            alert('Email already exists');
                        }
                    }).catch(error => {
                    console.error('Error validating email:', error);
                })
            }
        });

        regionInput.addEventListener('input', async (event) => {
            const region = regionInput.value.trim();
            if(region) {
                event.preventDefault();
                fetch(contextPath + `/super-admin/water/validation?region=` + region
                ).then(response => {
                    return response.json(); // Parse response JSON
                })
                    .then(data => {
                        const isRegionExists = data.RegionExists;
                        if (isRegionExists) {
                            alert('Region already added');
                        }
                    }).catch(error => {
                    console.error('Error validating region:', error);
                });
            }
        });


        form.addEventListener('submit', (event) => {
            let isValid = true;

            if (passwordInput.value !== rePasswordInput.value) {
                alert('Passwords do not match');
                console.log("Password validation called");
                isValid = false;
            }
            if (passwordInput.length < minLength) {
                alert('Password must be at least ' + minLength + ' characters long');
                isValid = false;
            }
            if (!lowercaseRegex.test(passwordInput.value)) {
                alert('Password must contain at least one lowercase letter');
                isValid = false;
            }
            if (!uppercaseRegex.test(passwordInput.value)) {
                alert('Password must contain at least one uppercase letter');
                isValid = false;
            }
            if (!numberRegex.test(passwordInput.value)) {
                alert('Password must contain at least one number');
                isValid = false;
            }
            if (!specialCharRegex.test(passwordInput.value)) {
                alert('Password must contain at least one special character');
                isValid = false;
            }

            if (!phone_regex.test(mobileInput.value) || mobileInput.value.length !== 10) {
                alert('Invalid mobile number');
                console.log("Mobile number validation called");
                isValid = false;
            }

            if (!phone_regex.test(contactInput.value) || contactInput.value.length !== 10) {
                alert('Invalid contact number');
                console.log("Contact number validation called");
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
            }

        });

    }
}

function initializeValidationElectricity() {
    console.log("ElectricityValidation called");
    const form = document.getElementById('addForm');

    if (form) {
        const passwordInput = document.getElementById('password');
        console.log("Password:", passwordInput);
        const rePasswordInput = document.getElementById('re-password');
        console.log("RePassword:", rePasswordInput);
        const mobileInput = document.getElementById('mobile');
        console.log("Mobile:", mobileInput);
        const contactInput = document.getElementById('contact');
        console.log("Contact:", contactInput);
        const emailInput = document.getElementById('email');
        console.log("Password:", passwordInput);
        const passwordCheckElement = document.getElementById('password-check');
        console.log("Password Check:", passwordCheckElement);

        const empidInput = document.getElementById('empid');
        console.log("EmpID:", empidInput);
        const regionInput = document.getElementById('region');
        console.log("Region:", regionInput);

        empidInput.addEventListener('input', async (event) => {
            const empid = empidInput.value.trim();
            if(empid) {
                event.preventDefault();
                fetch(contextPath + `/super-admin/electricity/validation?empid=` + empid
                ).then(response => {
                    return response.json();
                }).then(data => {
                    const isEmpIdExists = data.EmpIdExists;
                    if (isEmpIdExists) {
                        alert('EmpId already exists');
                    }
                }).catch(error => {
                    console.error('Error validating empid:', error);
                });
            }
        });

        emailInput.addEventListener('input', async (event) => {
            const email = emailInput.value.trim();
            if(email) {
                event.preventDefault();
                fetch(contextPath + `/super-admin/electricity/validation?email=` + email
                ).then(response => {
                    return response.json();
                })
                    .then(data => {
                        const isEmailExists = data.EmailExists;
                        if (isEmailExists) {
                            alert('Email already exists');
                        }
                    }).catch(error => {
                    console.error('Error validating email:', error);
                })
            }
        });

        regionInput.addEventListener('input', async (event) => {
            const region = regionInput.value.trim();
            if(region) {
                event.preventDefault();
                fetch(contextPath + `/super-admin/electricity/validation?region=` + region
                ).then(response => {
                    return response.json(); // Parse response JSON
                })
                    .then(data => {
                        const isRegionExists = data.RegionExists;
                        if (isRegionExists) {
                            alert('Region already added');
                        }
                    }).catch(error => {
                    console.error('Error validating region:', error);
                });
            }
        });


        form.addEventListener('submit', (event) => {
            let isValid = true;

            if (passwordInput.value !== rePasswordInput.value) {
                alert('Passwords do not match');
                console.log("Password validation called");
                isValid = false;
            }

            if (!phone_regex.test(mobileInput.value) || mobileInput.value.length !== 10) {
                alert('Invalid mobile number');
                console.log("Mobile number validation called");
                isValid = false;
            }

            if (!phone_regex.test(contactInput.value) || contactInput.value.length !== 10) {
                alert('Invalid contact number');
                console.log("Contact number validation called");
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
            }

        });

    }
}
