document.addEventListener("DOMContentLoaded", function () {
    // Get the canvas element
    var ctx = document.getElementById('lineChart').getContext('2d');

    // Define the data for the chart
    var data = {
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'Novemeber', 'December'],
        datasets: [{
            label: 'No. of new users',
            data: [12, 19, 3, 5, 2, 3, 4, 5, 6, 7, 8, 9],
            backgroundColor: 'rgba(0, 123, 255, 0.5)',
            borderColor: 'rgba(0, 123, 255, 1)',
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
