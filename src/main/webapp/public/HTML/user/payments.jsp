<%--
  Created by IntelliJ IDEA.
  User: visithkumarapperuma
  Date: 2024-01-02
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Payment</title>
    <link rel="stylesheet" href="../../CSS/dashboards/dashboard.css">
    <link rel="stylesheet" href="../../CSS/payments.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css">
</head>
<body>
<div class="navv">
    <header class="navbar">
        <div class="navbar-container container">
            <label for="hamburger"></label>
            <input type="checkbox" name="hamburger" id="hamburger">
            <div class="hamburger-lines">
                <span class="line line1"></span>
                <span class="line line2"></span>
                <span class="line line3"></span>
            </div>
            <ul class="menu-items">
                <li class="menu-items-li"><a href="#">Home</a></li>
                <li class="menu-items-li"><a href="<%= request.getContextPath() %>/public/HTML/pages/aboutUs.jsp">About</a></li>
                <li class="menu-items-li"><a href="#">Contact Us</a></li>
                <li class="nxt-page water"><button class="button-17" type="button" onclick="toggle()">Dashboards</button></li>
                <script>
                    function toggle() {
                        window.location.href = "userDashboardElectricity.jsp"
                    }
                </script>
                <li class="img_user dropdown">
                    <a href="<%= request.getContextPath() %>/user/user-profile">
                        <button class="user-profile">
                            <img alt="User" src="<%= request.getContextPath() %>/public/images/user.svg" style="width: 4vh; height: 4vh">
                        </button>
                        <div class="dropdown-content">
                            <a href="#">Link 1</a>
                            <a href="#">Link 2</a>
                            <a href="#">Link 3</a>
                        </div>
                    </a>
                </li>
            </ul>
            <img src="<%= request.getContextPath() %>/public/images/utility_saga.svg" alt="Utility Saga" class="logo">
        </div>
    </header>
</div>
    <main class="app">
        <div class="app-body">
            <div class="app-body-main-content">
                <form class="service-section" method="post" action="">
                    <h2>Service</h2>
                    <div class="service-section-header">
                        <div class="search-field">
                            <i class="ph-magnifying-glass"></i>
                            <label for="acc_no"></label><input id="acc_no" type="text" placeholder="Account number">
                        </div>
                        <div class="dropdown-field">
                            <label for="category"></label><select id="category">
                                    <option>Home</option>
                                    <option>Work</option>
                                </select>
                            <i class="ph-caret-down"></i>
                        </div>
                        <button class="flat-button" type="submit">
                            Search
                        </button>
                    </div>
                    <div class="mobile-only">
                        <button class="flat-button" type="submit">
                            Search
                        </button>
                    </div>
                    <div class="tiles">
                        <article class="tile">
                            <div class="tile-header">
                                <i class="ph-lightning-light"></i>
                                <h3>
                                    <span>Electricity</span>
                                    <span>CEB</span>
                                </h3>
                            </div>
                            <a href="#">
                                <span>Go to service</span>
                                <span class="icon-button">
								<i class="ph-caret-right-bold"></i>
							</span>
                            </a>
                        </article>
                        <article class="tile">
                            <div class="tile-header">
                                <i class="ph-fire-simple-light"></i>
                                <h3>
                                    <span>Water</span>
                                    <span>National water board</span>
                                </h3>
                            </div>
                            <a href="#">
                                <span>Go to service</span>
                                <span class="icon-button">
								<i class="ph-caret-right-bold"></i>
							</span>
                            </a>
                        </article>
                        <article class="tile">
                            <div class="tile-header">
                                <i class="ph-file-light"></i>
                                <h3>
                                    <span>Shop</span>
                                    <span>Solar store</span>
                                </h3>
                            </div>
                            <a href="#">
                                <span>Go to service</span>
                                <span class="icon-button">
								<i class="ph-caret-right-bold"></i>
							</span>
                            </a>
                        </article>
                    </div>
                    <div class="service-section-footer">
                        <p>Services are paid according to the current state of the currency and tariff.</p>
                    </div>
                </form>
                <section class="transfer-section">
                    <div class="transfer-section-header">
                        <h2>Latest transfers</h2>
                    </div>
                    <div class="transfers">
                        <div class="transfer">
                            <div class="transfer-logo">
                                <img src="https://assets.codepen.io/285131/apple.svg"/>
                            </div>
                            <dl class="transfer-details">
                                <div>
                                    <dt>Apple Inc.</dt>
                                    <dd>Apple ID Payment</dd>
                                </div>
                                <div>
                                    <dt>4012</dt>
                                    <dd>Last four digits</dd>
                                </div>
                                <div>
                                    <dt>28 Oct. 21</dt>
                                    <dd>Date payment</dd>
                                </div>
                            </dl>
                            <div class="transfer-number">
                                - $ 550
                            </div>
                        </div>
                        <div class="transfer">
                            <div class="transfer-logo">
                                <img src="https://assets.codepen.io/285131/pinterest.svg" />
                            </div>
                            <dl class="transfer-details">
                                <div>
                                    <dt>Pinterest</dt>
                                    <dd>2 year subscription</dd>
                                </div>
                                <div>
                                    <dt>5214</dt>
                                    <dd>Last four digits</dd>
                                </div>
                                <div>
                                    <dt>26 Oct. 21</dt>
                                    <dd>Date payment</dd>
                                </div>
                            </dl>
                            <div class="transfer-number">
                                - $ 120
                            </div>
                        </div>
                        <div class="transfer">
                            <div class="transfer-logo">
                                <img src="https://assets.codepen.io/285131/warner-bros.svg" />
                            </div>
                            <dl class="transfer-details">
                                <div>
                                    <dt>Warner Bros.</dt>
                                    <dd>Cinema</dd>
                                </div>
                                <div>
                                    <dt>2228</dt>
                                    <dd>Last four digits</dd>
                                </div>
                                <div>
                                    <dt>22 Oct. 21</dt>
                                    <dd>Date payment</dd>
                                </div>
                            </dl>
                            <div class="transfer-number">
                                - $ 70
                            </div>
                        </div>
                    </div>
                </section>
            </div>
            <div class="app-body-sidebar">
                <section class="payment-section">
                    <h2>Quick Pay</h2>
                    <div class="payment-section-header">
                        <p>Payments powered by stripe</p>
                    </div>
                    <div class="payments">
                        <div class="payment">
                            <div class="card green">
                                <span>01/22</span>
                            </div>
                            <div class="payment-details">
                                <h3>Water</h3>
                                <div>
                                    <span>$ 2,110</span>
                                    <button class="icon-button water-btn">
                                        <i class="ph-caret-right-bold"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="payment">
                            <div class="card olive">
                                <span>12/23</span>
                            </div>
                            <div class="payment-details">
                                <h3>Electricity</h3>
                                <div>
                                    <span>$ 5,621</span>
                                    <button class="icon-button elec-btn">
                                        <i class="ph-caret-right-bold"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="payment">
                            <div class="card gray">
                                <span>03/22</span>
                            </div>
                            <div class="payment-details">
                                <h3>Shop</h3>
                                <div>
                                    <span>$ 3,473</span>
                                    <button class="icon-button shop-btn">
                                        <i class="ph-caret-right-bold"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="payment-section-footer">
                        <button class="save-button">
                            Pay now
                        </button>
                    </div>
                </section>
            </div>
        </div>
    </main>
</body>
</html>
