//  Graphs

const bills = document.getElementById("bills");
const fluct = document.getElementById("fluct");
const iot = document.getElementById("iot");
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


new Chart(iot, {
    type: "doughnut",
    data: {
        labels: ["AC", "Fan1", "Fan2"],
        datasets: [
            {
                label: "My Revenue",
                data: [25, 5, 10],
                backgroundColor: [
                    "rgba(155,128,151,1)",
                    "rgba(254,111,162,1)",
                    "rgba(244,164,111, 1)",
                ],
                hoverBackgroundColor: "#e2d412",
            },
        ],
    },
    options: {
        responsive: true,
    },
});

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


function placeholderIsSupported() {
    test = document.createElement('input');
    return ('placeholder' in test);
}

$(document).ready(function(){
    placeholderSupport = placeholderIsSupported() ? 'placeholder' : 'no-placeholder';
    $('html').addClass(placeholderSupport);
});
