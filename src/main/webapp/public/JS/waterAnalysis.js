//  Graphs
const bills = document.getElementById("bills");
const fluct = document.getElementById("fluct");
Chart.defaults.color = "black";
Chart.defaults.borderColor = "rgba(255, 196, 0, 1)";


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
        const selectedAccount = o.querySelector("label").innerHTML;
        const isActive = !o.querySelector("label").classList.contains("INACTIVE");

        if(!isActive) {
            swal("Account Disconnected", "Please select an active account to view analytics", "warning");
            return;
        }

        selected.innerHTML = selectedAccount;
        optionsContainer.classList.remove("active");

        fetch(contextPath + `/user/water-analytics?account=${encodeURIComponent(selectedAccount)}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                // Handle the response data here

                new Chart(bills, {
                    type: "bar",
                    data: {
                        labels: data.data_list_monthly.map(d => d.date),
                        datasets: [
                            {
                                label: "Amount",
                                data: data.data_list_monthly.map(d => d.monthlyBill),
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

                let arr = Object.entries(data.budget);
                const custom_sort = (a, b) => {
                    const months = [
                        "Jan", "Feb", "Mar", "Apr", "May", "June",
                        "July", "Aug", "Sep", "Oct", "Nov", "Dec"
                    ];
                    return months.indexOf(a[0]) - months.indexOf(b[0]);
                }

                arr.sort(custom_sort);

                let sortedObject = Object.fromEntries(arr);

                new Chart(bud_act, {
                    type: "bar",
                    data: {
                        labels: Object.keys(sortedObject),
                        datasets: [
                            {
                                label: "Budgeted",
                                data: Object.values(sortedObject),
                                backgroundColor: "#b2ad0f",
                                borderColor: "#b2ad0f",
                                hoverBackgroundColor: "#fff000",
                                borderWidth: 0.5
                            },
                            {
                                label: "Actual",
                                data: data.data_list_monthly.map(d => d.monthlyBill),
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
                        labels:  data.data_list_daily.map(d => d.date),
                        datasets: [
                            {
                                label: "Usage in units",
                                data: data.data_list_daily.map((d) => d.data),
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

                new Chart(monthcons, {
                    type: "bar",
                    data: {
                        labels: data.data_list_monthly.slice(1).map(d => d.date),
                        datasets: [
                            {
                                label: "Bill Fluctuation Compared to previous Month -> ",
                                data: data.data_list_monthly.slice(1).map((d, i) => d.monthlyBill - data.data_list_monthly[i].monthlyBill),
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

                let table = document.getElementById("budget-table");
                let row = table.insertRow(-1);

                let cell1 = row.insertCell(0);
                let cell2 = row.insertCell(1);
                let cell3 = row.insertCell(2);

                cell1.innerHTML = data.budgetLatest.month;
                cell2.innerHTML = data.budgetLatest.data;
                cell3.innerHTML = data.budgetLatest.date;


                document.getElementById('bill-val').textContent += data.data_list_monthly[data.data_list_monthly.length - 1].monthlyBill;
                document.getElementById('kwh-val').textContent += data.data_list_daily[data.data_list_daily.length - 1].data;
                document.getElementById('reading-val').textContent += data.data_list_daily[data.data_list_daily.length - 1].date;


            })
            .catch(error => {
                // Handle error
                console.error('Error:', error.message);
            }).finally(() => {
            // Hide the loader
            document.querySelectorAll('.item').forEach(function(item) {
                try {
                    item.classList.remove('item')
                } catch (e) {
                    console.log(e);
                } finally {
                    console.log('new req');
                }
            });
        });
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