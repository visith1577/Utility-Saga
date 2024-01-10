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
                label: "My Revenue",
                data: [380, 200, 500, 300, 150, 400, 100],
                backgroundColor: ["rgba(255, 104, 0, 1)"],
                hoverBackgroundColor: "#FF90B8",
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

new Chart(fluct, {
    type: "line",
    data: {
        labels: ["Jan", "Feb", "Mar", "Apr", "May"],
        datasets: [
            {
                label: "My Revenue",
                data: [380, 200, 500, 300, 150],
                backgroundColor: ["rgba(255, 104, 0, 1)"],
                hoverBackgroundColor: "#FF90B8",
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

new Chart(iot, {
    type: "doughnut",
    data: {
        labels: ["Fashion", "Gadjet", "Other"],
        datasets: [
            {
                label: "My Revenue",
                data: [380, 200, 500],
                backgroundColor: [
                    "rgba(155,128,151,1)",
                    "rgba(254,111,162,1)",
                    "rgba(244,164,111, 1)",
                ],
                hoverBackgroundColor: "#FF90B8",
            },
        ],
    },
    options: {
        responsive: true,
    },
});
