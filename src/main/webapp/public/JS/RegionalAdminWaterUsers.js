function search() {
    var searchValue = document.getElementById("nic").value;

    fetch(contextPath + '/water/user/search?nic=' + searchValue)
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
    console.log(data);
    const resultsDiv = document.getElementById("results");
    resultsDiv.innerHTML = "";
    if (data.length === 0) {
        resultsDiv.innerHTML = "No results found.";
    } else {
        let table = "<table class='table'><tr><th>Account Number</th><th>Customer NIC</th><th>Customer Name</th><th>Mobile Number</th><th>Email</th><th>Address</th><th>Status</th><th>Change Status</th></tr>";
        for (let i = 0; i < data.length; i++) {
            table += "<tr><td>" + data[i].accountNumber +
                "</td><td>" + data[i].nic +
                "</td><td>" + data[i].firstName +" "+data[i].lastName+
                "</td><td>" + data[i].mobile +
                "</td><td>" + data[i].email +
                "</td><td>" + data[i].address +
                "</td><td>" + data[i].connectionStatus +
                "</td><td><button class=\"change-status-btn\">Submit</button>"  +
                "</td></tr>";
        }
        table += "</table>";
        resultsDiv.innerHTML = table;
    }
}

document.addEventListener('DOMContentLoaded', function() {
    console.log("Event Listner triggered");
    const showActiveCheckbox = document.getElementById('showActive');
    const showInactiveCheckbox = document.getElementById('showInactive');
    const tableRows = document.querySelectorAll('table tbody tr');

    showActiveCheckbox.addEventListener('change', filterRows);
    showInactiveCheckbox.addEventListener('change', filterRows);

    filterRows();

    function filterRows() {
        const showActive = showActiveCheckbox.checked;
        console.log("Show Active: ",showActive);
        const showInactive = showInactiveCheckbox.checked;
        console.log("Show Inactive: ",showInactive);

        tableRows.forEach(row => {
            const status = row.querySelector('td:nth-child(7)').textContent.trim();
            const showRow = (showActive && status === 'ACTIVE') || (showInactive && status === 'INACTIVE') || (showActive && showInactive);
            row.style.display = showRow ? '' : 'none';
        });
    }
});