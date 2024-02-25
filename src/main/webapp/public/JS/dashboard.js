function getCookieValue(cookieName) {
    const cookies = document.cookie.split(';');
    for (let i = 0; i < cookies.length; i++) {
        const [name, value] = cookies[i].trim().split('=');
        if (name === cookieName) {
            return value;
        }
    }
    return null; // Cookie not found
}

let nxt = getCookieValue('nxt');

if (!getCookieValue('nxt')) {
    let nxt = document.querySelector(".nxt-page").textContent.trim();
    let expires = new Date();
    expires.setTime(expires.getTime() + 2 * 60 * 60 * 1000);
    document.cookie = 'nxt='+ nxt + ';expires=' + expires.toUTCString();
}

function toggle() {
    if (nxt === "Electricity") {
        nxt = "Water"
        document.cookie = 'nxt=Water' + ';path=/UtilitySaga_war_exploded/public/HTML/dashboard';
    } else {
        nxt = "Electricity"
        document.cookie = 'nxt=Electricity' + ';path=/UtilitySaga_war_exploded/public/HTML/dashboard';
    }
    window.location.reload()
}

const nxt_page = document.querySelectorAll('.nxt-page')

nxt_page.forEach(button => {
    button.onclick = toggle
})

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

function set_dashboard() {
    let chart = null;
    const water_list = Array.from(document.getElementsByClassName("water"));
    const electricity_list = Array.from(document.getElementsByClassName("electricity"));

    if (nxt === "Electricity") {
        water_list.forEach(element => element.style.display = 'none');
        electricity_list.forEach(element => {
            if (element.style.display === 'none' || element.style.display === '') {
                element.style.display = 'block';
            }
        });
        check_destroy_chart(chart)
        chart = new Chart(e_ctx, config);
    } else {
        water_list.forEach(element => {
            if (element.style.display === 'none' || element.style.display === '') {
                element.style.display = 'block';
            }
        });
        check_destroy_chart(chart)
        chart = new Chart(w_ctx, config);
        electricity_list.forEach(element => element.style.display = 'none');
    }
}

document.addEventListener('visibilitychange', () => {
    if (document.visibilityState === 'visible') {
        set_dashboard(nxt)
    }
})

window.onload = set_dashboard
