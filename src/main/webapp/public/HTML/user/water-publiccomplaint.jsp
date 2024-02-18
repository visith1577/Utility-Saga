<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link  type="text/css" rel="stylesheet" href="../../CSS/forms.css">
  <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.1/jquery.js"></script>
  <script type="module" src="<%= request.getContextPath() %>/public/JS/complaints.js" defer></script>
  <title>Form</title>
</head>
<body>
  <div class="formbox water">
    <form id="complaint_form" method="post" action="">
      <h2>Water Services - Public complains</h2>
      <div class="formbody">
        <div class="forminput">
          <select name="Category" id="Category" required>
              <option value="Operational">Operational</option>
              <option value="Commercial">Commercial</option>
          </select>
          <label for="Category">Complain Category</label>
        </div>

        <div class="forminput">
          <select name="complaint_type" id="complaint_type" required>
              <option value="MainLeak">Main Leak</option>
              <option value="ConnectionLeak">Connection Leak</option>
              <option value="NoWater">No Water</option>
              <option value="LowPressure">Low Pressure</option>
              <option value="LeakNearMeter">Leak Near Meter</option>
              <option value="WeakQuality">Quality Problem</option>
              <option value="Others">Others</option>
          </select>
          <label for="complaint_type">Complaint Type</label>
        </div>
        
        <div class="forminput">
          <input id="CustomerName" name="CustomerName" type="text" required>
          <label for="CustomerName">Customer Name</label>
          <div class="error"></div>
        </div>

        <div class="forminput">
          <input id="AccountNum" name="AccountNum" type="text" required>
          <!-- <div class="underline"></div> -->
          <label for="AccountNum">Account Number</label>
          <div class="error"></div>
        </div>

        <div class="forminput">
          <input id="CusNIC" name="CusNIC" type="text" required>
          <!-- <div class="underline"></div> -->
          <label for="CusNIC">NIC</label>
          <div class="error"></div>
        </div>

        <div class="forminput">
          <input id="Email" name="Email" type="email" required>
          <!-- <div class="underline"></div> -->
          <label for="Email">Email Address</label>
          <div class="error"></div>
        </div>
        
        <div class="forminput">
          <input id="Telnum" name="Telnum" type="tel" required oninput="this.value = this.value.replace(/[^0-9]/g, '');">
          <!-- <div class="underline"></div> -->
          <label for="Telnum">Phone Number</label>
          <div class="error"></div>
        </div>

        <div class="forminput">
          <textarea id="txtArea" name="txtArea" rows="1" cols="1" required maxlength="50"></textarea>
          <label for="txtArea">Complaint Description</label>
          <br />
        </div>
      </div>
      <div class="formbtn">
        <button type="submit" id="submit-btn" class="submitbtn waterbtn">Submit</button>
      </div>
    </form>
  </div>
</body>
</html>