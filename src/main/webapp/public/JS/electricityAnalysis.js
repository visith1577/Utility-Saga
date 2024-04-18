//  Graphs

const bills = document.getElementById("bills");
const fluct = document.getElementById("fluct");
Chart.defaults.color = "black";
Chart.defaults.borderColor = "rgba(255, 196, 0, 1)";

new Chart(bills, {
    type: "bar",
    data: {
        labels: ["Jan", "Feb", "Mar", "Apr", "May", "June", "July"],
        datasets: [
            {
                label: "Usage",
                data: [380, 200, 500, 300, 150, 400, 100],
                backgroundColor: ["#e2d412"],
                hoverBackgroundColor: "#ffff00",
            },
        ],
    },
    options: {
        responsive: true,
        plugins: {
            legend: {
                display: false,
            },
        },
    },
});

new Chart(monthcons, {
    type: "bar",
    data: {
        labels: ["Jan", "Feb", "Mar", "Apr", "May", "June", "July"],
        datasets: [
            {
                label: "Monthly Consumption",
                data: [380, 200, 500, 300, 150,520,337],
                backgroundColor: "#e2d412",
                borderColor: "#e2d412", 
                hoverBackgroundColor: "#fff000",
                borderWidth: 1
            },
        ],
    },
    options: {
        responsive: true,
        plugins: {
            legend: {
                display: false,
            },
        },
        scales: {
            x: {
                stacked: false, // Not stacked bar chart
            },
            y: {
                stacked: false, // Not stacked bar chart
                beginAtZero: true
            }
        }
    },
});

// search bar controls start

const selected = document.querySelector(".selected");
const optionsContainer = document.querySelector(".options-container");

const optionsList = document.querySelectorAll(".option");
const searchBox = document.querySelector(".search-box input");

selected.addEventListener("click", () => {
    optionsContainer.classList.toggle("active");

    searchBox.value = "";
    filterList("");

    if (optionsContainer.classList.contains("active")) {
        searchBox.focus();
    }
});

optionsList.forEach((o) => {
    o.addEventListener("click", () => {
        selected.innerHTML = o.querySelector("label").innerHTML;
        optionsContainer.classList.remove("active");
    });
});

searchBox.addEventListener("keyup", function (e) {
    filterList(e.target.value);
});

const filterList = (searchTerm) => {
    searchTerm = searchTerm.toLowerCase();
    optionsList.forEach((option) => {
        let label = option.firstElementChild.nextElementSibling.innerText.toLowerCase();
        if (label.indexOf(searchTerm) !== -1) {
            option.style.display = "block";
        } else {
            option.style.display = "none";
        }
    });
}

// search bar controls end

new Chart(bud_act, {
    type: "bar",
    data: {
        labels: ["Jan", "Feb", "Mar", "Apr", "May", "June", "July"],
        datasets: [
            {
                label: "Budgeted",
                data: [400, 350, 450, 400, 300, 500, 350], 
                backgroundColor: "#b2ad0f", 
                borderColor: "#b2ad0f", 
                hoverBackgroundColor: "#fff000",
                borderWidth: 0.5
            },
            {
                label: "Actual",
                data: [380, 200, 500, 300, 150, 400, 100], 
                backgroundColor: "#e2d412", 
                borderColor: "#e2d412", 
                hoverBackgroundColor: "#fbffdac4",
                borderWidth: 0.5
            }
        ]
    },
    options: {
        responsive: true,
        indexAxis: 'x',
        plugins: {
            legend: {
                display: true,
                position: 'top'
            }
        },
        scales: {
            x: {
                stacked: false,
            },
            y: {
                stacked: false,
                beginAtZero: true
            }
        }
    }
});

new Chart(dailycons, {
    type: "line",
    data: {
        labels: ["Jan 1", "Jan 2", "Jan 3", "Jan 4", "Jan 5", "Jan 6", "Jan 7", "Jan 8", "Jan 9", "Jan 10", "Jan 11", "Jan 12", "Jan 13", "Jan 14", "Jan 15", "Jan 16", "Jan 17", "Jan 18", "Jan 19", "Jan 20", "Jan 21", "Jan 22", "Jan 23", "Jan 24", "Jan 25", "Jan 26", "Jan 27", "Jan 28", "Jan 29", "Jan 30", "Jan 31"],
        datasets: [
            {
                label: "Usage",
                data: [100, 50, 130, 75, 105, 115, 125, 130, 135, 140, 95, 80, 25, 122, 120, 118, 78, 71, 52, 105, 122, 100, 98, 95, 100, 105, 108, 112, 115, 118, 120],
                borderColor: "#b2ad0f",
                borderWidth: 1.5,
                fill: false
            }
        ]
    },
    options: {
        responsive: true,
        plugins: {
            legend: {
                display: true,
                position: 'top'
            }
        },
        scales: {
            x: {
                title: {
                    display: true,
                    text: 'Date'
                }
            },
            y: {
                title: {
                    display: true,
                    text: 'Consumption'
                },
                beginAtZero: true
            }
        }
    }
});

// calender code

const header = document.querySelector(".calendar h3");
const dates = document.querySelector(".dates");
const navs = document.querySelectorAll("#prev, #next");


const months = [
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December",
];

let date = new Date();
let month = date.getMonth();
let year = date.getFullYear();

function renderCalendar() {
    const start = new Date(year, month, 1).getDay();
    const endDate = new Date(year, month + 1, 0).getDate();
    const end = new Date(year, month, endDate).getDay();
    const endDatePrev = new Date(year, month, 0).getDate();

    let datesHtml = "";

    for (let i = start; i > 0; i--) {
        datesHtml += `<li class="inactive">${endDatePrev - i + 1}</li>`;
    }

    for (let i = 1; i <= endDate; i++) {
        let className =
            i === date.getDate() &&
            month === new Date().getMonth() &&
            year === new Date().getFullYear()
                ? ' class="today"'
                : "";
        let modalHtml =
            i === date.getDate() &&
            month === new Date().getMonth() &&
            year === new Date().getFullYear()
                ? '<div class="modal"><div class="modal-content"><p>This is today\'s date.</p></div></div>'
                : "";
        datesHtml += `<li${className}>${i}</li>${modalHtml}`;
    }

    for (let i = end; i < 6; i++) {
        datesHtml += `<li class="inactive">${i - end + 1}</li>`;
    }

    dates.innerHTML = datesHtml;
    header.textContent = `${months[month]} ${year}`;
}

navs.forEach((nav) => {
    nav.addEventListener("click", (e) => {
        const btnId = e.target.id;

        if (btnId === "prev" && month === 0) {
            year--;
            month = 11;
        } else if (btnId === "next" && month === 11) {
            year++;
            month = 0;
        } else {
            month = btnId === "next" ? month + 1 : month - 1;
        }

        date = new Date(year, month, new Date().getDate());
        year = date.getFullYear();
        month = date.getMonth();

        renderCalendar();
    });
});

renderCalendar();

document.querySelectorAll('.today').forEach(function(date) {
    date.addEventListener('click', function() {
        this.nextElementSibling.style.display = 'block';
    });
});

window.onclick = function(event) {
    setTimeout(function() {
        if (!event.target.matches('.today')) {
            document.querySelectorAll('.modal').forEach(function(modal) {
                modal.style.display = 'none';
            });
        }
    }, 1000);
};
// end of calendar code

function placeholderIsSupported() {
    test = document.createElement('input');
    return ('placeholder' in test);
}

$(document).ready(function(){
    placeholderSupport = placeholderIsSupported() ? 'placeholder' : 'no-placeholder';
    $('html').addClass(placeholderSupport);
});
