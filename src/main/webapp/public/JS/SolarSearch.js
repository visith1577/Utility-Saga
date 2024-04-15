function search() {
    var nic = document.getElementById("nic").value;

    fetch('/UtilitySaga_war_exploded/solar/search?nic=' + nic)
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
    var resultsDiv = document.getElementById("results");
    resultsDiv.innerHTML = "";
    if (data.length === 0) {
        resultsDiv.innerHTML = "No results found.";
    } else {
        var table = "<table class='table'><tr><th>Company Name</th><th>BRN</th><th>Owner-NIC</th><th>UserName</th><th>Password</th><th>Mobile</th><th>Company Phone</th><th>Email</th><th>Address</th><th>District</th><th>Remarks</th><th>Approved Status</th><th>Submit</th></tr>";
        for (var i = 0; i < data.length; i++) {
            table += "<tr><td>" + data[i].CompanyName +
                "</td><td>" + data[i].BNum +
                "</td><td>" + data[i].OwnerNIC +
                "</td><td>" + data[i].username +
                "</td><td>" + data[i].pwd +
                "</td><td>" + data[i].mobile +
                "</td><td>" + data[i].landNo +
                "</td><td>" + data[i].email +
                "</td><td>" + data[i].address +
                "</td><td>" + data[i].district +
                "</td><td>" + data[i].remarks +
                "</td><td>" + data[i].approvalStatus +
                "</td><td><button class=\"submit-btn\" data-bnum=\"<%= company.getBNum() %>\">Submit</button>"  +
                "</td></tr>";
        }
        table += "</table>";
        resultsDiv.innerHTML = table;
    }
}
