const sidebar = document.querySelector(".sidebar");
const menu = document.querySelector("#menu");


const menu_container = document.querySelector(".sidebar-container");


const home = document.querySelector("#home");
const service = document.querySelector("#service");
const bills = document.querySelector("#bills");
const analysis = document.querySelector("#analysis");
const report = document.querySelector("#report");
const settings = document.querySelector("#settings");
const help = document.querySelector("#help");

let previousToggled = null;
let opened = false

menu.addEventListener("click", () => {
    opened ? closeMenu() : openMenu()
});

// service.addEventListener("click", (e) => {
//     toggleMenu(service);
// });
//
// bills.addEventListener("click", (e) => {
//     toggleMenu(bills);
// });
//
// analysis.addEventListener("click", (e) => {
//     toggleMenu(analysis);
// });
//
// report.addEventListener("click", (e) => {
//     toggleMenu(report);
// });
//
// settings.addEventListener("click", (e) => {
//     toggleMenu(settings);
// });
// help.addEventListener("click", (e) => {
//     toggleMenu(help);
// });

const toggleMenu = (button) => {
    if (previousToggled && button !== menu) {
        untoggleMenu(previousToggled);
    }

    button.classList.add("toggled");
    button.style.backgroundColor = "#E5E4EC";//

    if (button !== menu) {
        previousToggled = button;
    }
};

const untoggleMenu = (button) => {
    button.classList.remove("toggled");
    button.style.backgroundColor = "#F5F4F4";
};

menu.addEventListener("click", () => {
    sidebar.classList.contains("active") ? closeMenu() : openMenu();
});

const openMenu = () => {
    opened = true
    sidebar.classList.add("active");

    toggleMenu(menu);

    // let menu_logo = document.createElement("img");
    // menu_logo.classList.add("menu-logo");
    // menu_logo.src = "../images/round-menu.svg";
    // menu_logo.style.width = "60px";
    // menu_container.style.paddingLeft = "15px";
    // menu_container.insertBefore(menu_logo, menu_container.childNodes[0]);

    let p_home = document.createElement("p");
    home.classList.add("nav-bar__exp");
    p_home.innerHTML = "Home";
    home.appendChild(p_home);

    let p_service = document.createElement("p");
    service.classList.add("nav-bar__exp");
    p_service.innerHTML = "Service";
    service.appendChild(p_service);

    let p_bills = document.createElement("p");
    bills.classList.add("nav-bar__exp");
    p_bills.innerHTML = "Bills";
    bills.appendChild(p_bills);

    let p_analysis = document.createElement("p");
    analysis.classList.add("nav-bar__exp");
    p_analysis.innerHTML = "Analysis";
    analysis.appendChild(p_analysis);

    let p_report = document.createElement("p");
    report.classList.add("nav-bar__exp");
    p_report.innerHTML = "Reports";
    report.appendChild(p_report);

    let p_settings = document.createElement("p");
    settings.classList.add("nav-bar__exp");
    p_settings.innerHTML = "Settings";
    settings.appendChild(p_settings);

    let p_help = document.createElement("p");
    help.classList.add("nav-bar__exp");
    p_help.innerHTML = "Help";
    help.appendChild(p_help);


    menu_container.style.paddingLeft = "0";
};

const closeMenu = () => {
    menu_container.style.paddingLeft = "0px";

    let elements = document.getElementsByClassName("nav-bar__exp");

    Array.from(elements).forEach(
        function (element) {
            element?.classList.remove("nav-bar__exp")
            let elem = element.getElementsByTagName('p')
            Array.from(elem).forEach(
                function (el) {
                    element.removeChild(el)
                }
            )
        }
    );

    sidebar.classList.remove("active");

    untoggleMenu(menu);
};
