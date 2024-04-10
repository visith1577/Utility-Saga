const w_ctx = document.getElementById('w-graph');
const e_ctx = document.getElementById('e-graph');

const labels = Array.from({length: 31}, (_, i) => (i+1).toString());

const dataset1 = {
    label: 'Jan',
    data: [10, 20, 30, 40, 50, 60,
        10, 20, 30, 40, 50, 60,
        10, 20, 30, 40, 50, 6,
        10, 20, 30, 40, 5, 60,
        1, 20, 3, 40, 50, 60,
        10, 20, 30, 4, 50, 60, 34
    ],
    borderColor: 'rgb(255, 99, 132)',
    backgroundColor: 'rgba(255, 99, 132, 0.2)',
};

const dataset2 = {
    label: 'Feb',
    data: [
        20, 15, 25, 35, 45, 55,
        20, 13, 25, 5, 5, 15,
        20, 13, 23, 35, 45, 5,
        0, 35, 35, 34, 45, 55,
        2, 5, 2, 3, 4
    ],
    borderColor: 'rgb(54, 162, 235)',
    backgroundColor: 'rgba(54, 162, 235, 0.2)',
};

const dataset3 = {
    label: 'Mar',
    data: [30, 25],
    borderColor: 'rgb(255, 206, 86)',
    backgroundColor: 'rgba(255, 206, 86, 0.2)',
};

const data = {
    labels: labels,
    datasets: [dataset1, dataset2, dataset3],
};

const config = {
    type: 'line',
    data: data,
    options: {
        responsive: true, // Makes the chart responsive
        title: { // Add a title
            display: true,
            text: 'Water consumption in past 3 months',
        },
        plugins: {
            // Add legend labels for each dataset
            legend: {
                display: true,
                labels: {
                    // Customize legend text color
                    fontColor: 'black',
                }
            }
        }
    },
};

function check_destroy_chart(ch) {
    if (ch != null){
        ch.destroy()
    }
}

function get_filename(pathname) {
    return pathname.split("/").pop();
}

function set_dashboard() {
    let chart = null;

    if (get_filename(window.location.pathname) === "electricity-dashboard") {
        check_destroy_chart(chart)
        new Chart(e_ctx, config);
    } else {
        check_destroy_chart(chart)
        new Chart(w_ctx, config);
    }
}

document.addEventListener('visibilitychange', () => {
    if (document.visibilityState === 'visible') {
        set_dashboard()
    }
})

window.onload = set_dashboard
