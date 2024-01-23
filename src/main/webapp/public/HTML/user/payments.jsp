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
    <nav class="navbar">
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
                <li class="menu-items-li"><a href="#">About</a></li>
                <li class="menu-items-li"><a href="#">Contact Us</a></li>
                <li class="nxt-page water"><button class="button-17" type="button" onclick="toggle()">Electricity</button></li>
                <li class="nxt-page electricity"><button class="button-17" type="button" onclick="toggle()">Water</button></li>
                <li class="img_user dropdown">
                    <a href="">
                        <button>
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
            <img src="../../images/utility_saga.svg" alt="Utility Saga" class="logo">
        </div>
    </nav>
    <main class="app">
        <div class="app-body">
            <div class="app-body-main-content">
                <section class="service-section">
                    <h2>Service</h2>
                    <div class="service-section-header">
                        <div class="search-field">
                            <i class="ph-magnifying-glass"></i>
                            <input type="text" placeholder="Account number">
                        </div>
                        <div class="dropdown-field">
                            <select>
                                <option>Home</option>
                                <option>Work</option>
                            </select>
                            <i class="ph-caret-down"></i>
                        </div>
                        <button class="flat-button">
                            Search
                        </button>
                    </div>
                    <div class="mobile-only">
                        <button class="flat-button">
                            Toggle search
                        </button>
                    </div>
                    <div class="tiles">
                        <article class="tile">
                            <div class="tile-header">
                                <i class="ph-lightning-light"></i>
                                <h3>
                                    <span>Electricity</span>
                                    <span>UrkEnergo LTD.</span>
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
                                    <span>Heating Gas</span>
                                    <span>Gazprom UA</span>
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
                                    <span>Tax online</span>
                                    <span>Kharkov 62 str.</span>
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
                </section>
                <section class="transfer-section">
                    <div class="transfer-section-header">
                        <h2>Latest transfers</h2>
                        <div class="filter-options">
                            <p>Filter selected: more than 100 $</p>
                            <button class="icon-button">
                                <i class="ph-funnel"></i>
                            </button>
                            <button class="icon-button">
                                <i class="ph-plus"></i>
                            </button>
                        </div>
                    </div>
                    <div class="transfers">
                        <div class="transfer">
                            <div class="transfer-logo">
                                <img src="https://assets.codepen.io/285131/apple.svg" />
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
                    <h2>New Payment</h2>
                    <div class="payment-section-header">
                        <p>Choose a card to transfer money</p>
                    </div>
                    <div class="payments">
                        <div class="payment">
                            <div class="card green">
                                <span>01/22</span>
                                <span>
								•••• 4012
							</span>
                            </div>
                            <div class="payment-details">
                                <h3>Internet</h3>
                                <div>
                                    <span>$ 2,110</span>
                                    <button class="icon-button">
                                        <i class="ph-caret-right-bold"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="payment">
                            <div class="card olive">
                                <span>12/23</span>
                                <span>
								•••• 2228
							</span>
                            </div>
                            <div class="payment-details">
                                <h3>Universal</h3>
                                <div>
                                    <span>$ 5,621</span>
                                    <button class="icon-button">
                                        <i class="ph-caret-right-bold"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                        <div class="payment">
                            <div class="card gray">
                                <span>03/22</span>
                                <span>
								•••• 5214
							</span>
                            </div>
                            <div class="payment-details">
                                <h3>Gold</h3>
                                <div>
                                    <span>$ 3,473</span>
                                    <button class="icon-button">
                                        <i class="ph-caret-right-bold"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="faq">
                        <p>Most frequently asked questions</p>
                        <div>
                            <label>Question</label>
                            <input type="text" placeholder="Type here">
                        </div>
                    </div>
                    <div class="payment-section-footer">
                        <button class="save-button">
                            Save
                        </button>
                        <button class="settings-button">
                            <i class="ph-gear"></i>
                            <span>More settings</span>
                        </button>
                    </div>
                </section>
            </div>
        </div>
    </main>
</body>
</html>
