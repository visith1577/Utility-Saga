function search() {
    var nic = document.getElementById("nic").value;

    fetch(contextPath + '/electricity/user/search?nic=' + nic)
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