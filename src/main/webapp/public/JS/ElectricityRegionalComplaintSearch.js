function search() {
    var searchValue = document.getElementById("nic").value;

    fetch(contextPath + '/electricity/complaint/search?nic=' + searchValue)
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
        let table = "<table class='table'><tr><th>Complaint Number</th><th>Category</th><th>Complaint Type</th><th>NIC</th><th>Account Number</th><th>Mobile</th><th>Complaint Status</th><th>Description</th><th>Submit</th></tr>";
        for (let i = 0; i < data.length; i++) {
            table += `<tr data-bnum=${data[i].ComplaintNo}><td>` + data[i].CompanyNo +
                "</td><td>" + data[i].complaintCategoty +
                "</td><td>" + data[i].complaintType +
                "</td><td>" + data[i].nic +
                "</td><td>" + data[i].accountNumber +
                "</td><td>" + data[i].mobile +
                "</td><td>" + data[i].complaintStatus +
                "</td><td>" + data[i].complaintDescription +
                "</td><td><button class=\"submit-btn\" data-bnum=\"<%= complaint.getComplaintNo() %>\">Submit</button>"  +
                "</td></tr>";
        }
        table += "</table>";
        resultsDiv.innerHTML = table;
    }
}

document.addEventListener('DOMContentLoaded', function() {
    console.log("Event Listner triggered");
    const showActiveCheckbox = document.getElementById('showActive');
    const showPendingCheckbox = document.getElementById('showPending');
    const showDoneCheckbox = document.getElementById('showDone');
    const tableRows = document.querySelectorAll('table tbody tr');

    showActiveCheckbox.addEventListener('change', filterRows);
    showPendingCheckbox.addEventListener('change', filterRows);
    showDoneCheckbox.addEventListener('change', filterRows);

    filterRows();

    function filterRows() {
        const showActive = showActiveCheckbox.checked;
        console.log("Show Active: ",showActive);
        const showPending = showPendingCheckbox.checked;
        const showDone = showDoneCheckbox.checked;
        console.log("Show Pending: ",showPending);
        console.log("Show Done: ",showDone);

        tableRows.forEach(row => {
            const status = row.querySelector('td:nth-child(7)').textContent.trim();
            const isActiveStatus = status === 'ACTIVE';
            const isDoneStatus = status === 'DONE';
            const isPendingStatus = status === 'PENDING';
            const showRow = (showActive && isActiveStatus) || (showDone && isDoneStatus) || (showPending && isPendingStatus);
            row.style.display = showRow ? '' : 'none';
        });
    }
});
