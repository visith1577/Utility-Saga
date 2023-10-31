<!DOCTYPE html>
<html lang="en">

<head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>WaterDashboard</title>
      <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
      <link href="../../CSS/dashboards/water.css" rel="stylesheet" type="text/css">
      <link href="../../CSS/login/user/electricity_navbar_sidebar.css" rel="stylesheet" type="text/css">

<body>
      <div class="dashboard">
            <div class="wrapper">
                  <div class="top_navbar">
                        <div class="hamburger">
                              <div class="one"></div>
                              <div class="two"></div>
                              <div class="three"></div>
                        </div>
                        <div class="top_menu">
                              <div class="logo">
                                    <img alt="Utility Saga" height="50px" src="../../images/utility_saga.svg">
                              </div>
                              <ul>
                                    <li><a href="#">
                                                <img alt="Cart" src="../../images/cart.svg" class="item1">
                                          </a></li>
                                    <li><a href="#">
                                                <img alt="Notification" src="../../images/notification-line.svg"
                                                      class="item2">
                                          </a></li>
                                    <li><a href="#">
                                                <img alt="User" src="../../images/user.svg" class="item3">
                                          </a></li>
                              </ul>
                        </div>
                  </div>


                  <div class="content-middle">
                        <div class="sidebar" id="sidebar">
                              <ul>
                                  <li class="list_button"><a href="./electricityDashboard.jsp">
                                          <span class="icon"><img alt="home" src="../../images/home-outline.svg" /></span>
                                          <span class="title">Home<img alt="service" class="right_arrow" height="20px"
                                                  src="../../images/arrow-right-s-line.svg" width="20px" /></span>
                                      </a></li>
                                  <li class="list_button"><a href="#">
                                          <span class="icon"><img alt="service" src="../../images/service-plan.svg" /></span>
                                          <span class="title">Service<img alt="service" class="right_arrow" height="20px"
                                                  src="../../images/arrow-right-s-line.svg" width="20px" /></span>
                                      </a></li>
                                  <li class="list_button"><a href="#">
                                          <span class="icon"><img alt="bills" src="../../images/bill.svg" /></span>
                                          <span class="title">Bills<img alt="service" class="right_arrow" height="20px"
                                                  src="../../images/arrow-right-s-line.svg" width="20px" /></span>
                                      </a></li>
                                  <li class="list_button"><a  href="#">
                                          <span class="icon"><img alt="analysis" src="../../images/report.svg" /></span>
                                          <span class="title">Analysis<img alt="service" class="right_arrow" height="20px"
                                                  src="../../images/arrow-right-s-line.svg" width="20px" /></span>
                                      </a></li>
                                  <li class="list_button"><a href="#">
                                          <span class="icon"><img alt="report" src="../../images/event-schedule.svg" /></span>
                                          <span class="title">Report<img alt="service" class="right_arrow" height="20px"
                                                  src="../../images/arrow-right-s-line.svg" width="20px" /></span>
                                      </a></li>
                                  <li class="list_button"><a href="#">
                                          <span class="icon"> <img alt="settings" src="../../images/settings-outline.svg" /></span>
                                          <span class="title">Settings<img alt="service" class="right_arrow" height="20px"
                                                  src="../../images/arrow-right-s-line.svg" width="20px" /></span>
                                      </a></li>
                                  <li class="list_button"><a href="#">
                                          <span class="icon"><img alt="help" src="../../images/help-outline.svg" /></span>
                                          <span class="title">Help<img alt="service" class="right_arrow" height="20px"
                                                  src="../../images/arrow-right-s-line.svg" width="20px" /></span>
                                      </a></li>
                              </ul>
                          </div>
                        <div class="call-button">
                              <div class="hotline-1939">Hotline: 1939 </div>
                        </div>

                        <div class="introduction">
                              <div class="national-water-supply-and-drainage-board">
                                    National Water Supply and Drainage Board </div>
                              <img class="water-logo" src="<%= request.getContextPath() %>/public/images/Waterboardlogo.png" />

                              <div class="text">
                                    The NWSDB, initially a sub-department, now operates under the Ministry of Water
                                    Supply &amp; Drainage. Established in 1975, it manages 334 water supply schemes,
                                    providing safe drinking water to over 50% of the population, along with sewerage
                                    systems in multiple areas, serving 2.8 million connections. </div>
                        </div>


                        <div class="usage">
                              <div class="usagetitle">
                                    <h3>Your current water usage:</h3>
                              </div>
                              <div class="dropdown">
                                    <label for="TimeDuration">Time duration:</label>
                                    <select id="TimeDuration" name="TimeDuration">
                                          <option value="Annually">Annually</option>
                                          <option value="Monthly">Monthly</option>
                                          <option value="Weekly">Weekly</option>
                                          <option value="Daily">Daily</option>

                                    </select>
                              </div>

                        </div>

                        <div class="notifications">
                              <h3>Notifications</h3>
                              <ul>
                                    <li>We want to inform you about an upcoming maintenance activity that will affect
                                          your water supply.

                                          Maintenance Details:
                                          <ul>
                                                <li>Date and Time: [Date] from [Start Time] to [End Time]</li>
                                                <li>Location: [Affected Area/Neighborhood]</li>
                                                <li>Reason: Routine maintenance to improve water quality and
                                                      infrastructure</li>
                                          </ul>
                                    </li>
                              </ul>
                              <ul>
                              <li>Due to emergency repairs, water service in your area will be temporarily interrupted
                                    from 10 AM to 2 PM today; we apologize for any inconvenience</li>
                                    <li>
                                          <div class="buttons">
                                                <button class="button2">Your Bill: Rs. 5000</button>
                                          </div>
                                    </li>
                              </ul>
                        </div>
                        <div class="select-services">
                              <div class="servicetitle">
                                    <h3>Select the service you need:</h3>
                              </div>
                              <div class="buttons">
                                    <button class="button">Public Complaints</button>
                                    <button class="button">Online Bill Payment</button>
                                    <button class="button">New Connection</button>
                              </div>
                        </div>
                        <div class="suggestions">
                              <div class="content-suggesions">
                                    <div class="suggestion-text"> View Suggestions: </div>

                                    <div class="suggesion">
                                          <div class="text-1">
                                                <span>
                                                      <span class="text-1-span">You have high water usage during
                                                            nighttime :<br />
                                                      </span>
                                                      <span class="text-1-span2">Check for leaks or drips in your
                                                            plumbing and promptly repair them to save water.</span>
                                                </span>
                                          </div>
                                    </div>


                                    <div class="suggesion">
                                          <div class="text-1">
                                                <span>
                                                      <span class="text-1-span">Try to use rain water effectively:<br />
                                                      </span>
                                                      <span class="text-1-span2">Install rain barrels to capture
                                                            rainwater from your roof, which can be used for watering
                                                            plants and gardens</span>
                                                </span>
                                          </div>
                                    </div>

                                    <div class="suggesion">
                                          <div class="text-1">
                                                <span>
                                                      <span class="text-1-span">Reconsider Nighttime Water
                                                            Activities:<br />
                                                      </span>
                                                      <span class="text-1-span2">Save water by doing activities like
                                                            laundry during the day when water demand is lower.</span>
                                                </span>
                                          </div>
                                    </div>
                              </div>


                        </div>
                  </div>
            </div>
      </div>
</body>
<script src="../../JS/dashboard.js"></script>
<script>
    window.onscroll = function () {
        scrollFunction()
    }


    window.onclick = function (event) {
        if (!event.target.matches('.dropbtn')) {
              const dropdowns = document.getElementsByClassName("dropdown-content");
              let i;
              for (i = 0; i < dropdowns.length; i++) {
                    const openDropdown = dropdowns[i];
                    if (openDropdown.classList.contains('show')) {
                    openDropdown.classList.remove('show');
                }
            }
        }
    }

    function scrollFunction() {
        if (document.body.scrollTop > 70 || document.documentElement.scrollTop > 70) {
            document.getElementById("sidebar").style.height = "80%";
        } else {
            document.getElementById("sidebar").style.height = "85%";
        }
    }

    function Appear() {
        document.getElementById("myDropdown").classList.toggle("show");
    }

    function selectItem(itemText) {
        document.getElementById("selectedItem").innerHTML = itemText;
        document.getElementById("myDropdown").classList.remove("show");
    }
</script>

</html>