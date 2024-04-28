<%--
  Created by IntelliJ IDEA.
  User: visithkumarapperuma
  Date: 2023-10-28
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Registration page</title>
  <link rel="stylesheet" href="<%= request.getContextPath() %>/public/CSS/login/register.css">
  <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
  <script src="<%= request.getContextPath() %>/public/JS/register.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
  <img src="<%= request.getContextPath() %>/public/resources/purple_bar.png" alt="" class="style-bar">
    <div class="container">
      <div class="error-message-container">
        <p id="error-message">err</p>
      </div>
      <section class="register">
        <form method="post" action="${pageContext.request.contextPath}/register" class="reg-form signup-form-container">
          <h1 class="reg-panel__title reg-panel__main">
            Welcome To Utility Saga
          </h1>

          <h1 class="reg-panel__title reg-panel__sub">
            Sign In
          </h1>

          <div class="progress-bar">
            <div class="stage">
              <p class="tool-tip">Personal info</p>
              <p class="stageno stageno-1">1</p>
            </div>
            <div class="stage">
              <p class="tool-tip">Contact info</p>
              <p class="stageno stageno-2">2</p>
            </div>
            <div class="stage">
              <p class="tool-tip">Final Submit</p>
              <p class="stageno stageno-3">3</p>
            </div>
          </div>

          <div class="signup-form-content">
            <div class="stage1-content">
              <div class="button-container">
                <div class="text-fields fname">
                  <label for="Fname" class="reg-field"><i class='bx bx-user'></i></label>
                  <input type="text" placeholder="Enter firstname" id="Fname" name="Fname"
                         class="reg-field__name stage1-input" required>
                </div>
                <div class="text-fields lname">
                  <label for="Lname" class="reg-field"><i class='bx bx-user' ></i></label>
                  <input type="text" placeholder="Enter lastname" id="Lname" name="Lname"
                         class="reg-field__name stage1-input" required>
                </div>
              </div>
              <div class="button-container">
                <div class="text-fields uname">
                  <label for="Uname" class="reg-field"><i class='bx bx-card' ></i></label>
                  <input type="text" placeholder="Enter username" id="Uname" name="Uname"
                         class="reg-field__name stage1-input" required>
                </div>
                <div class="text-fields nic">
                  <label for="nic" class="reg-field"><i class='bx bx-id-card' ></i></label>
                  <input type="text" placeholder="Enter NIC" id="nic" name="nic"
                         class="reg-field__name stage1-input" required>
                </div>
              </div>
              <div class="button-container">
                <div class="text-fields password">
                  <label for="Pwd" class="reg-field"><i class='bx bx-lock-alt' ></i></label>
                  <input type="password" placeholder="Enter password" id="Pwd" name="Pwd"
                           class="reg-field__pwd stage1-input" required>
                  <div id="password-check" class="error"></div>
                </div>
                <div class="text-fields password">
                  <label for="Re" class="reg-field"><i class='bx bx-lock-alt' ></i></label>
                  <input type="password" placeholder="Confirm password" id="Re" name="Re"
                         class="reg-field__pwd stage1-input" required>
                  <div id="password-recheck" class="error"></div>
                </div>
              </div>
                <div class="pagination-btns">
                  <input id="stage1-btn" type="button" value="Next" class="nextPage stagebtn1b">
                </div>
            </div>
            <div class="stage2-content">
              <div class="button-container">
                <div class="text-fields phone">
                  <label for="phone"><i class='bx bx-mobile' ></i></label>
                  <input
                          type="text"
                          name="phone"
                          id="phone"
                          placeholder="phone number"
                          class="stage2-input"
                          oninput="this.value = this.value.replace(/[^0-9]/g, '');"
                          required
                  >
                </div>
                <div class="text-fields phone">
                  <label for="home-phone"><i class='bx bx-phone' ></i></label>
                  <input
                          type="text"
                          name="home-phone"
                          id="home-phone"
                          placeholder="home phone number"
                          oninput="this.value = this.value.replace(/[^0-9]/g, '');"
                  >
                </div>
              </div>
              <div class="button-container">
                <div class="text-fields email">
                  <label for="email"><i class='bx bx-envelope' ></i></label>
                  <input type="email" name="email" class="stage2-input" id="email" placeholder="Enter your email id" required>
                </div>
                <div class="text-fields region">
                  <label for="region"><i class='bx bx-area' ></i></label>
                  <input type="text" placeholder="Enter Region" class="stage2-input" id="region" name="region" required>
                </div>
              </div>
              <div class="button-container">
                <div class="text-fields address">
                  <label for="address"><i class='bx bx-home-alt-2'></i></label>
                  <input type="text" name="address" class="stage2-input" id="address" placeholder="Enter your address" required>
                </div>
              </div>
              <div class="pagination-btns">
                <input type="button" value="Previous" class="previousPage stagebtn2a" onclick="stage2to1()">
                <input type="button" value="Next" class="nextPage stagebtn2b">
              </div>
            </div>
            <div class="stage3-content">
              <div class="list-container">
                <p>Service that you require</p>
                <div class="list-container">
                    <label for="electricity">
                      <input class="electricity-chk" type="checkbox" name="service" id="electricity" value="electricity">
                      Electricity
                    </label>
                </div>
                <div class="list-container">
                    <label for="water">
                      <input type="checkbox" name="service" id="water" value="water" class="water-chk">
                      Water
                    </label>
                </div>
              </div>
              <div class="list-container">
                <p style="margin: 20px;">Electricity services</p>
                <div class="text-fields bill">
                  <label for="eBill"><i class='bx bx-power-off'></i></label>
                  <input type="text" name="eBill" id="eBill" placeholder="electricity bill no." class="electricity-chk">
                </div>
                <div class="provider-selection">
                </div>
              </div>
              <div class="list-container">
                <p style="margin: 20px">Water Services</p>
                <div class="text-fields bill">
                  <label for="wBill"><i class='bx bx-water' ></i></label>
                  <input type="text" name="wBill" id="wBill" placeholder="water bill no." class="water-chk">
                </div>
              </div>
              <div class="tc-container">
                <label for="tc" class="tc">
                  <input type="checkbox" name="tc" id="tc" required>
                  By submitting your details, you agree to the <a href="#"> terms and conditions. </a>
                </label>
              </div>
              <div class="pagination-btns">
                <input type="button" value="Previous" class="previousPage stagebtn3a" onclick="stage3to2()">
                <input type="submit" value="Submit" class="nextPage stagebtn3b">
              </div>
            </div>
          </div>
        </form>
      </section>
    </div>
  </body>
<script>
  function checkPasswordStrength(password) {
    // Define regex patterns for different character types
    const lowercaseRegex = /[a-z]/;
    const uppercaseRegex = /[A-Z]/;
    const numberRegex = /[0-9]/;
    const specialCharRegex = /[!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?]/;

    // Check if password meets minimum length requirement
    const minLength = 8;
    if (password.length < minLength) {
      return ['Password must be at least ' + minLength + ' characters long', 'red'];
    }

    // Check for presence of different character types
    if (!lowercaseRegex.test(password)) {
      return ['Password must contain at least one lowercase letter', 'orange'];
    }
    if (!uppercaseRegex.test(password)) {
      return ['Password must contain at least one uppercase letter', 'orange'];
    }
    if (!numberRegex.test(password)) {
      return ['Password must contain at least one number', 'orange'];
    }
    if (!specialCharRegex.test(password)) {
      return ['Password must contain at least one special character', 'orange'];
    }

    // Password meets all criteria, return success
    return ['Strong Password', 'green'];
  }

  let contextPath = "<%= request.getContextPath() %>";
  let signupContent = document.querySelector(".signup-form-container"),
          stagebtn1b = document.querySelector(".stagebtn1b"),
          stagebtn2a = document.querySelector(".stagebtn2a"),
          stagebtn2b = document.querySelector(".stagebtn2b"),
          stagebtn3a = document.querySelector(".stagebtn3a"),
          stagebtn3b = document.querySelector(".stagebtn3b"),
          signupContent1 = document.querySelector(".stage1-content"),
          signupContent2 = document.querySelector(".stage2-content"),
          head = document.querySelector(".reg-panel__main"),
          sub = document.querySelector(".reg-panel__sub")
  signupContent3 = document.querySelector(".stage3-content");
  let nic = document.getElementById('nic');
  let username = document.getElementById('Uname');
  let password = document.getElementById('Pwd');
  let confirm_password = document.getElementById('Re');
  let spinner = document.getElementById('loading-spinner');


  signupContent2.style.display = "none";
  signupContent3.style.display = "none";

  function stage1to2(){
    signupContent1.style.display = "none";
    signupContent3.style.display = "none";
    signupContent2.style.display = "block";
    document.querySelector(".stageno-1").innerText = "✔";
    document.querySelector(".stageno-1").style.backgroundColor = "#52ec61";
    document.querySelector(".stageno-1").style.color = "#fff";
  }
  const inputs_1 = document.getElementsByClassName("stage1-input");

  function check_empty(input_list_1) {
    for (let i = 0; i < input_list_1.length; i++) {
      if (input_list_1[i].value.trim() === '') {
        return true;
      }
    }
    return false
  }

  function password_handle(event) {
    let chk_password = event.target.value;
    let item = document.getElementById("password-check");
    let [txt, col] = checkPasswordStrength(chk_password);
    item.textContent = txt;
    item.style.color = col;
    item.parentElement.style.border = "1px solid " + col;
  }

  function isValidNic(nicNumber) {
    let result;
    if (nicNumber.length === 10 && !isNaN(nicNumber.substring(0, 9)) && isNaN(nicNumber.substring(9, 1)) && ['x', 'v'].includes(nicNumber.substring(9, 1).toLowerCase())) {
      result = true;
    } else result = nicNumber.length === 12 && !isNaN(nicNumber);
    return result;
  }

  password.addEventListener('change', password_handle);
  confirm_password.addEventListener('change', _ => document.getElementById("password-recheck").textContent = "");


  stagebtn1b.addEventListener("click", function (e) {
    e.preventDefault();
    if (check_empty(inputs_1)) {
      alert("Please fill out all fields");
      return;
    }

    if (password.value === confirm_password.value &&
            isValidNic(nic.value.trim())
    ) {
      validateInputsStage1(nic, username, password);
      // stagebtn1b.click();
    } else if (password.value !== confirm_password.value){
      document.getElementById("password-recheck").textContent = "Password do not match";
    } else if (!isValidNic(nic.value.trim())) {
      Swal.fire({
        icon: "error",
        title: "NIC invalid",
        text: "Provide proper NIC",
        footer: ''
      });
      nic.parentElement.style.border = "1px solid red";
    }
  })


  function validateInputsStage1(nic, username) {
    const nic_value = nic.value.trim();
    const username_value = username.value.trim();

    // spinner.style.display = "block";


    fetch(contextPath + '/api/validate-one?username=' + username_value + '&nic=' + nic_value
    ).then(response => {
        console.log(response)
        return response.json(); // Parse response JSON
    })
            .then(data => {
              const parsedData = data;
              const isUsernameValid = parsedData.UsernameExists;
              const isNICExists = parsedData.NICExists;

              if (isUsernameValid && !isNICExists) {
                Swal.fire({
                  icon: "error",
                  title: "user name already taken",
                  text: "Change username",
                  footer: ''
                });
              } else if (!isUsernameValid && isNICExists){
                Swal.fire({
                  icon: "error",
                  title: "NIC already taken",
                  text: "account already exists",
                  footer: '<a href="#" style="color: red;">forgot password</a>'
                });
              } else if (!isUsernameValid && !isNICExists) {
                Swal.fire({
                  icon: "success",
                  title: "Validation Successful",
                  text: "You can proceed to the next step",
                  footer: ''
                });
                stage1to2();
              } else {
                Swal.fire({
                  icon: "error",
                  title: "NIC already taken",
                  text: "account already exists",
                  footer: '<a href="#" style="color: red;">forgot password</a>'
                });
              }

            }).catch(error => {
      console.error('Error validating username:', error);
    }).finally(() => {
      // spinner.style.display = "none";
        nic.parentElement.style.border = "1px solid #ccc";
        username.parentElement.style.border = "1px solid #ccc";
    });
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


  const address = document.getElementById('address');

  address.addEventListener('change', _ => {
    if (address.value.trim() === '') {
      address.style.border = "1px solid red";
    } else {
      address.style.border = "1px solid #ccc";
    }
  })

  stagebtn2b.addEventListener('click', function (e) {
    e.preventDefault();
    const inputs_2 = document.getElementsByClassName("stage2-input");
    if (check_empty(inputs_2)) {
      alert("Please fill out all fields");
      return;
    }
    const phone = document.getElementById('phone');
    const home_phone = document.getElementById('home-phone');
    const email = document.getElementById('email');

    const region = document.getElementById('region').value;

    if (!isValidPhoneNumber(phone.value)) {
      Swal.fire({
        icon: "error",
        title: "Invalid phone number",
        text: "Please provide a valid phone number",
        footer: ''
      });
      phone.parentElement.style.border = "1px solid red";
    } else if (!isValidPhoneNumber(home_phone.value) && home_phone.value.trim() !== '') {
      Swal.fire({
        icon: "error",
        title: "Invalid home phone number",
        text: "Please provide a valid home phone number",
        footer: ''
      });
      home_phone.parentElement.style.border = "1px solid red";
    } else if (!isValidEmail(email.value)) {
      Swal.fire({
        icon: "error",
        title: "Invalid email",
        text: "Please provide a valid email",
        footer: ''
      });
      email.parentElement.style.border = "1px solid red";
    } else if (isValidPhoneNumber(phone.value)
            && (home_phone.value.trim() === '' || home_phone.value.trim() !== '' && isValidPhoneNumber(home_phone.value) )
                    && isValidEmail(email.value)) {
      fetch(contextPath + '/api/validate-one?email=' + email.value.trim()
      ).then(response => {
        return response.json(); // Parse response JSON
      })
              .then(data => {
                const isEmailExists = data.EmailExists;

                if (isEmailExists) {
                  Swal.fire({
                    icon: "error",
                    title: "Email already taken",
                    text: "account already exists",
                    footer: '<a href="#" style="color: red;">forgot password</a>'
                  });
                  email.parent.style = "1px solid red";
                } else {
                  Swal.fire({
                    icon: "success",
                    title: "Validation Successful",
                    text: "You can proceed to the next step",
                    footer: ''
                  });
                  stage2to3();
                }
              }).catch(error => {
        console.error('Error validating email:', error);
      }).finally(() => {
        email.parentElement.style.border = "1px solid #ccc";
        phone.parentElement.style.border = "1px solid #ccc";
        home_phone.parentElement.style.border = "1px solid #ccc";
      });
    } else {
        Swal.fire({
            icon: "error",
            title: "Invalid inputs",
            text: "Please provide valid inputs",
            footer: ''
        });
    }
  })


  function stage2to1(){
    signupContent1.style.display = "block"
    signupContent3.style.display = "none"
    signupContent2.style.display = "none"
  }

  stagebtn3b.addEventListener('click', function (e) {
    e.preventDefault();
    const eBill = document.getElementById('eBill');
    const wBill = document.getElementById('wBill');
    const tc = document.getElementById('tc');
    // const ceb = document.getElementById('ceb');
    // const leco = document.getElementById('leco');
    const electricity = document.getElementsByClassName('electricity-chk');
    const water = document.getElementsByClassName('water-chk');
    const service = document.getElementsByName('service');
    const provider = document.getElementsByName('provider');
    const tc_value = tc.checked;
    const eBill_value = eBill.value.trim();
    const wBill_value = wBill.value.trim();
    const service_value = Array.from(service).filter(item => item.checked).map(item => item.value);

    fetch(contextPath + '/api/validate-one?accounts=' +  document.getElementById('nic').value)
            .then(response => {
              return response.json(); // Parse response JSON
            }).then((data) => {
              let accountList = data.accountList;
              console.log(accountList);
              if (service_value.includes('water') && !accountList.includes(wBill_value) || service_value.includes('water') && (accountList === [])) {
                Swal.fire({
                  icon: "error",
                  title: "Water account not supported",
                  text: "Please provide a valid water bill number",
                  footer: ''
                });
              } else if (service_value.includes('electricity') && !accountList.includes(eBill_value) || service_value.includes('electricity') && (accountList === [])) {
                Swal.fire({
                  icon: "error",
                  title: "Electricity account not supported",
                  text: "Please provide a valid electricity bill number",
                  footer: ''
                });
              }else if (service_value.length === 0) {
                Swal.fire({
                  icon: "error",
                  title: "Service not selected",
                  text: "Please select a service",
                  footer: ''
                });
              } else if (service_value.includes('electricity') && eBill_value === '') {
                Swal.fire({
                  icon: "error",
                  title: "Electricity bill not provided",
                  text: "Please provide electricity bill number",
                  footer: ''
                });
              } else if (service_value.includes('water') && wBill_value === '') {
                Swal.fire({
                  icon: "error",
                  title: "Water bill not provided",
                  text: "Please provide water bill number",
                  footer: ''
                });
              } else if (!tc_value) {
                Swal.fire({
                  icon: "error",
                  title: "Terms and conditions not accepted",
                  text: "Please accept terms and conditions",
                  footer: ''
                });
              } else {
                Swal.fire({
                  icon: "success",
                  title: "Validation Successful",
                  text: "You can proceed to the next step",
                  footer: ''
                });
                signupContent.submit();
              }
    }).catch(error => {
      console.error('Error validating accounts:', error);
    });
  })


  function stage2to3(){
    signupContent1.style.display = "none"
    signupContent3.style.display = "block"
    signupContent2.style.display = "none"
    head.style.display = "none"
    sub.style.display = "none"
    document.querySelector(".stageno-2").innerText = "✔"
    document.querySelector(".stageno-2").style.backgroundColor = "#52ec61"
    document.querySelector(".stageno-2").style.color = "#fff"
  }

  function stage3to2(){
    head.style.display = "block"
    sub.style.display = "block"
    signupContent1.style.display = "none"
    signupContent3.style.display = "none"
    signupContent2.style.display = "block"
  }
</script>
</html>
