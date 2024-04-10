document.addEventListener("DOMContentLoaded", function () {
    // Get the canvas element
    var ctx = document.getElementById('lineChart').getContext('2d');

    // Define the data for the chart
    var data = {
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'Novemeber', 'December'],
        datasets: [{
            label: 'No. of new users',
            data: [12, 19, 3, 5, 2, 3, 4, 5, 6, 7, 8, 9],
            backgroundColor: 'rgb(220, 233, 0)',
            borderColor: 'rgb(220, 233, 0)',
            borderWidth: 1
        }]
    };

    // Create the line chart
    var lineChart = new Chart(ctx, {
        type: 'line',
        data: data,
        options: {
            responsive: true,
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
});

let popup = document.getElementById('popup');
let popupcontainer = document.getElementById('popupcontainer');

function openPopup() {
      popup.classList.add("open-popup");
      popupcontainer.classList.add("open-popupcontainer");
}

function closePopup() {
      popup.classList.remove("open-popup");
      popupcontainer.classList.remove("open-popupcontainer");
}