<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link  type="text/css" rel="stylesheet" href="../../CSS/forms.css">
  <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
  <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/parsley.js/2.9.2/parsley.min.js" integrity="sha512-eyHL1atYNycXNXZMDndxrDhNAegH2BDWt1TmkXJPoGf1WLlNYt08CSjkqF5lnCRmdm3IrkHid8s2jOUY4NIZVQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
  <script type="module" src="<%= request.getContextPath() %>/public/JS/water_complaint.js" defer></script>
  <title>Form</title>
</head>
<body>
  <div class="formbox water">
    <form>
      <h2>Water Services - Public complains</h2>
      <div class="formbody">
        <div class="forminput">
          <select name="Catogery" id="Cateogry" required>
              <option value="Operational">Operational</option>
              <option value="Commercial">Commercial</option>
          </select>
          <label for="ComplainCategory">Complain Cateogry</label>
        </div>

        <div class="forminput">
          <select name="complaint_type" id="complaint_type" required>
              <option value="MainLeak">Main Leak</option>
              <option value="ConnectionLeak">Connection Leak</option>
              <option value="NoWater">No Water</option>
              <option value="LowPressure">Low Pressure</option>
              <option value="LeakNearMeter">Leak Near Meter</option>
              <option value="WeakQuality">Weak Weak Quality Problem</option>
              <option value="Others">Others</option>
          </select>
          <label for="complaint_type">Complaint Type</label>
        </div>
        
        <div class="forminput">
          <input type="text" required>
          <label for="CustomerName">Customer Name</label>
        </div>

        <div class="forminput">
          <input type="text" required>
          <!-- <div class="underline"></div> -->
          <label for="AccountNum">Account Number</label>
        </div>

        <div class="forminput">
          <input type="text" required>
          <!-- <div class="underline"></div> -->
          <label for="CusNIC">NIC</label>
        </div>

        <div class="forminput">
          <input type="email" required>
          <!-- <div class="underline"></div> -->
          <label for="Email">Email Address</label>
        </div>
        
        <div class="forminput">
          <input type="tel" required>
          <!-- <div class="underline"></div> -->
          <label for="Telnum">Telephone Number</label>
        </div>

        <div class="forminput">
          <textarea rows="1" cols="1" required></textarea>
          <label for="">Complaint Description</label>
          <br />
        </div>
      </div>
      <div class="formbtn">
        <button type="submit" class="submitbtn waterbtn" onclick="send_data()">Submit</button>
      </div>
    </form>
  </div>
</body>
<script>
  function send_data() {
    Toastify({
      text: "form submitted successfully",
      duration: 3000,
      newWindow: true,
      close: true,
      gravity: "top", // `top` or `bottom`
      position: "left", // `left`, `center` or `right`
      stopOnFocus: true, // Prevents dismissing of toast on hover
      style: {
        background: "linear-gradient(to right, #00b09b, #96c93d)",
      },
      onClick: function(){} // Callback after click
    }).showToast();
  }
</script>
</html>