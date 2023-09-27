const sidebar = document.querySelector(".sidebar");
const menu = document.querySelector("#menu");


const menu_container = document.querySelector(".menu-container");


const home = document.querySelector("#home");
const service = document.querySelector("#service");
const bills = document.querySelector("#bills");
const analysis = document.querySelector("#analysis");
const report = document.querySelector("#report");
const settings = document.querySelector("#settings");
const help = document.querySelector("#help");

let previousToggled = null;
let currentToggled = null;



home.addEventListener("click", (e) => {
  toggleMenu(home);
});

service.addEventListener("click", (e) => {
  toggleMenu(service);
});

bills.addEventListener("click", (e) => {
  toggleMenu(bills);
});

analysis.addEventListener("click", (e) => {
  toggleMenu(analysis);
});

report.addEventListener("click", (e) => {
  toggleMenu(report);
});

settings.addEventListener("click", (e) => {
  toggleMenu(settings);
});

help.addEventListener("click", (e) => {
  toggleMenu(help);
});

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
  button.style.backgroundColor = "#F5F4F4";//
};

menu.addEventListener("click", (e) => {
  sidebar.classList.contains("active") ? closeMenu() : openMenu();
});

const openMenu = () => {
  sidebar.classList.add("active");
  sidebar.style.width = "250px";

  toggleMenu(menu);

  let menu_logo = document.createElement("img");
  menu_logo.id = "menu-logo";
  menu_logo.src = "";
  menu_logo.style.width = "60px";
  menu_container.style.paddingLeft = "15px";
  menu_container.insertBefore(menu_logo, menu_container.childNodes[0]);

  let p_home = document.createElement("p");
  p_home.id = "p-home";
  p_home.innerHTML = "Home";
  home.style.width = "220px";
  home.style.justifyContent = "left";
  home.appendChild(p_home);

  let p_service = document.createElement("p");
  p_service.id = "p-service";
  p_service.innerHTML = "Service";
  service.style.width = "220px";
  service.style.justifyContent = "left";
  service.appendChild(p_service);

  let p_bills = document.createElement("p");
  p_bills.id = "p-bills";
  p_bills.innerHTML = "Bills";
  bills.style.width = "220px";
  bills.style.justifyContent = "left";
  bills.appendChild(p_bills);

  let p_analysis = document.createElement("p");
  p_analysis.id = "p-analysis";
  p_analysis.innerHTML = "Analysis";
  analysis.style.width = "220px";
  analysis.style.justifyContent = "left";
  analysis.appendChild(p_analysis);

  let p_report = document.createElement("p");
  p_report.id = "p-report";
  p_report.innerHTML = "Reports";
  report.style.width = "220px";
  report.style.justifyContent = "left";
  report.appendChild(p_report);

  let p_settings = document.createElement("p");
  p_settings.id = "p-settings";
  p_settings.innerHTML = "Settings";
  settings.style.width = "220px";
  settings.style.justifyContent = "left";
  settings.appendChild(p_settings);

  let p_help = document.createElement("p");
  p_help.id = "p-help";
  p_help.innerHTML = "Help";
  help.style.width = "220px";
  help.style.justifyContent = "left";
  help.appendChild(p_help);


  icon_logout.style.width = "25%";
};

const closeMenu = () => {
  menu_container.removeChild(document.getElementById("menu-logo"));
  menu_container.style.paddingLeft = "0px";

  untoggleMenu(menu);

  search.removeChild(document.getElementById("p-home"));
  search.style.width = "50px";
  search.style.justifyContent = "center";

  dashboard.removeChild(document.getElementById("p-service"));
  dashboard.style.width = "50px";
  dashboard.style.justifyContent = "center";

  pets.removeChild(document.getElementById("p-bills"));
  pets.style.width = "50px";
  pets.style.justifyContent = "center";

  clients.removeChild(document.getElementById("p-analysis"));
  clients.style.width = "50px";
  clients.style.justifyContent = "center";

  vets.removeChild(document.getElementById("p-report"));
  vets.style.width = "50px";
  vets.style.justifyContent = "center";

  settings.removeChild(document.getElementById("p-settings"));
  settings.style.width = "50px";
  settings.style.justifyContent = "center";

  settings.removeChild(document.getElementById("p-help"));
  settings.style.width = "50px";
  settings.style.justifyContent = "center";


  sidebar.classList.remove("active");
  sidebar.style.width = "78px";

};
