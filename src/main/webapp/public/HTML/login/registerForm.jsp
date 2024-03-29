<%--
  Created by IntelliJ IDEA.
  User: visithkumarapperuma
  Date: 2023-10-28
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Registration page</title>
  <link rel="stylesheet" href="../../CSS/login/register.css">
  <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/parsley.js/2.9.2/parsley.min.js" integrity="sha512-eyHL1atYNycXNXZMDndxrDhNAegH2BDWt1TmkXJPoGf1WLlNYt08CSjkqF5lnCRmdm3IrkHid8s2jOUY4NIZVQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
  <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
</head>
<body>
  <img src="../../resources/purple_bar.png" alt="" class="style-bar">
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
                  <label for="Fname" class="reg-field"><i class='bx bx-user' ></i></label>
                  <input type="text" placeholder="Enter firstname" id="Fname" name="Fname"
                         class="reg-field__name">
                </div>
                <div class="text-fields lname">
                  <label for="Lname" class="reg-field"><i class='bx bx-user' ></i></label>
                  <input type="text" placeholder="Enter lastname" id="Lname" name="Lname"
                         class="reg-field__name">
                </div>
              </div>
              <div class="button-container">
                <div class="text-fields uname">
                  <label for="Uname" class="reg-field"><i class='bx bx-card' ></i></label>
                  <input type="text" placeholder="Enter username" id="Uname" name="Uname"
                         class="reg-field__name">
                </div>
                <div class="text-fields nic">
                  <label for="nic" class="reg-field"><i class='bx bx-id-card' ></i></label>
                  <input type="text" placeholder="Enter NIC" id="nic" name="nic"
                         class="reg-field__name">
                </div>
              </div>
              <div class="button-container">
                <div class="text-fields password">
                  <label for="Pwd" class="reg-field"><i class='bx bx-lock-alt' ></i></label>
                  <input type="password" placeholder="Enter password" id="Pwd" name="Pwd"
                           class="reg-field__pwd">
                </div>

                <div class="text-fields password">
                  <label for="Re" class="reg-field"><i class='bx bx-lock-alt' ></i></label>
                  <input type="password" placeholder="Confirm password" id="Re" name="Re"
                         class="reg-field__pwd">
                </div>
              </div>
                <div class="pagination-btns">
                  <input type="button" value="Next" class="nextPage stagebtn1b" onclick="stage1to2()">
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
<%--                          pattern="[0-9]{3}-[0-9]{2}-[0-9]{3}"--%>
                  >
                </div>
                <div class="text-fields phone">
                  <label for="home-phone"><i class='bx bx-phone' ></i></label>
                  <input
                          type="text"
                          name="home-phone"
                          id="home-phone"
                          placeholder="home phone number"
<%--                          pattern="[0-9]{3}-[0-9]{2}-[0-9]{3}"--%>
                  >
                </div>
              </div>
              <div class="button-container">
                <div class="text-fields email">
                  <label for="email"><i class='bx bx-envelope' ></i></label>
                  <input type="email" name="email" id="email" placeholder="Enter your email id">
                </div>
                <div class="text-fields region">
                  <label for="region"><i class='bx bx-area' ></i></label>
                  <input type="text" placeholder="Enter Region" id="region" name="region">
                </div>
              </div>
              <div class="button-container">
                <div class="text-fields address">
                  <label for="address"><i class='bx bx-home-alt-2'></i></label>
                  <input type="text" name="address" id="address" placeholder="Enter your address">
                </div>
              </div>
              <div class="pagination-btns">
                <input type="button" value="Previous" class="previousPage stagebtn2a" onclick="stage2to1()">
                <input type="button" value="Next" class="nextPage stagebtn2b" onclick="stage2to3()">
              </div>
            </div>
            <div class="stage3-content">
              <div class="list-container">
                <p>Service that you require</p>
                <div class="list-container">
                    <label for="electricity">
                      <input type="checkbox" name="service" id="electricity" value="electricity">
                      Electricity
                    </label>
                </div>
                <div class="list-container">
                    <label for="water">
                      <input type="checkbox" name="service" id="water" value="water">
                      Water
                    </label>
                </div>
              </div>
              <div class="list-container">
                <p style="margin: 20px;">Electricity services</p>
                <div class="text-fields bill">
                  <label for="eBill"><i class='bx bx-power-off'></i></label>
                  <input type="text" name="eBill" id="eBill" placeholder="electricity bill no.">
                </div>
                <div class="provider-selection">
                  <p class="field-heading">Provider : </p>
                  <label for="ceb">
                    <input type="radio" name="provider" id="ceb" value="ceb">CEB
                  </label>
                  <label for="leco"><input type="radio" name="provider" id="leco" value="leco">LECO</label>
                </div>
              </div>
              <div class="list-container">
                <p style="margin: 20px">Water Services</p>
                <div class="text-fields bill">
                  <label for="wBill"><i class='bx bx-water' ></i></label>
                  <input type="text" name="wBill" id="wBill" placeholder="water bill no.">
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
  function stage2to1(){
    signupContent1.style.display = "block"
    signupContent3.style.display = "none"
    signupContent2.style.display = "none"
  }
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

  function showErrorMessage(message, duration) {
    let errorMessage = document.getElementById("error-message");
    let errorMessageContainer = document.querySelector(".error-message-container");
    errorMessage.innerText = message;
    errorMessage.style.display = "block";
    errorMessageContainer.style.display = "block";
    setTimeout(function() {
      errorMessage.style.display = "none";
      errorMessageContainer.style.display = "none";
    }, duration);
  }

  // Check if an error message should be displayed (you can set this using JSP)
  let error = "<%= request.getParameter("error") %>";
  if (error === "passwordMismatch") {
    showErrorMessage("Passwords do not match", 5000); // Display for 5 seconds
  }
</script>
</html>
