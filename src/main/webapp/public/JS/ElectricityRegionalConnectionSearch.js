function search() {
    var nic = document.getElementById("nic").value;

    fetch(contextPath + '/electricity/connection/search?nic=' + nic)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            displayResults(data);
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
            alert('Error: ' + error.message);
        });
}

function displayResults(data) {
    console.log("Data: "+data);
    const resultsDiv = document.getElementById("results");
    resultsDiv.innerHTML = "";
    if (data.length === 0) {
        resultsDiv.innerHTML = "No results found.";
    } else {
        let table = "<table class='table'><tr><th>Requester Name</th><th>Account Number</th><th>NIC</th><th>Email</th><th>Mobile</th><th>Region</th><th>Current Address</th><th>New Address</th><th>Nearest Account/th><th>Connection Requirement</th><th>Connection Type</th><th>Account Status</th><th>Submit</th></tr>";
        for (let i = 0; i < data.length; i++) {
            table += `<tr data-bnum=${data[i].accountNumber}><td>` + data[i].requesterName +
                "</td><td>" + data[i].accountNumber +
                "</td><td>" + data[i].nic +
                "</td><td>" + data[i].email +
                "</td><td>" + data[i].mobile +
                "</td><td>" + data[i].region +
                "</td><td>" + data[i].currentAddress +
                "</td><td>" + data[i].newAddress +
                "</td><td>" + data[i].nearestAccount +
                "</td><td>" + data[i].connectionRequirement +
                "</td><td>" + data[i].connectionType +
                "</td><td>" + data[i].accountStatus +
                "</td><td><button class=\"submit-btn\" data-bnum=\"<%= connection.getAccountNumber() %>\">Submit</button>"  +
                "</td></tr>";
        }
        table += "</table>";
        resultsDiv.innerHTML = table;
    }
}

function filterRows(showPendingCheckbox, showRejectedCheckbox, tableRows) {
    const showPending = showPendingCheckbox.checked;
    console.log("Show Pending: ",showPending);
    const showRejected = showRejectedCheckbox.checked;
    console.log("Show Rejected: ",showRejected);

    tableRows.forEach(row => {
        const status = row.querySelector('td:nth-child(13)').textContent.trim();
        console.log(status)
        const showRow = (showPending && status === 'PENDING') || (showRejected && status === 'REJECTED') || (showPending && showRejected);
        row.style.display = showRow ? '' : 'none';
    });
}

document.addEventListener('DOMContentLoaded', function() {
    const showPendingCheckbox = document.getElementById('showPending');
    const showRejectedCheckbox = document.getElementById('showRejected');
    const tableRows = document.querySelectorAll('.table tbody tr');

    showPendingCheckbox.addEventListener('change', () => filterRows(showPendingCheckbox, showRejectedCheckbox, tableRows));
    showRejectedCheckbox.addEventListener('change', () => filterRows(showPendingCheckbox, showRejectedCheckbox, tableRows));

    filterRows(showPendingCheckbox, showRejectedCheckbox, tableRows);
});
