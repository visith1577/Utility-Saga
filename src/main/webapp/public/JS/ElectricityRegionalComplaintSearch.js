function search() {
    var nic = document.getElementById("nic").value;

    fetch(contextPath + '/electricity/complaint/search?nic=' + nic)
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
    console.log("Data"+data);
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
