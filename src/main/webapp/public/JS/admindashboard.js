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


const tableBodyUsers = document.querySelector('.table tbody');
const tableBodyComplaints = document.querySelector('.complaints tbody');
const adminDistrict = document.getElementById('adminDistrict').textContent;

fetchUsersAndComplaints(adminDistrict)
    .then(data => {
        populateUserTable(data.users);
        populateComplaintTable(data.complaints);
    })
    .catch(error => {
        console.error('Error fetching data:', error);
    });

async function fetchUsersAndComplaints(district) {
    const usersResponse = await fetch('/api/users?district=' + district);
    const users = await usersResponse.json();

    const complaintsResponse = await fetch('/api/complaints?district=' + district);
    const complaints = await complaintsResponse.json();

    return { users, complaints };
}

function populateUserTable(users) {
    // Clear existing rows
    tableBodyUsers.innerHTML = '';

    // Loop through users and create rows
    users.forEach(user => {
        const row = document.createElement('tr');

        row.innerHTML = `
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.address}</td>
            <td>${user.phone}</td>
            <td>${user.connectionType}</td>
            <td>${user.connectionStatus}</td>
        `;

        tableBodyUsers.appendChild(row);
    });
}

function populateComplaintTable(complaints) {
    tableBodyComplaints.innerHTML = '';

    complaints.forEach(complaint => {
        const row = document.createElement('tr');

        row.innerHTML = `
            <td>${complaint.complaintNumber}</td>
            <td>${complaint.category}</td>
            <td>${complaint.complaintType}</td>
            <td>${complaint.user.name}</td>
            <td>${complaint.user.accountNumber}</td>
            <td>${complaint.user.mobile}</td>
            <td>${complaint.complaintStatus}</td>
            <td></td>
        `;

        tableBodyComplaints.appendChild(row);
    });
}
