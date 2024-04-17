function search(identifier) {
    var nic = document.getElementById(identifier).value;

    if (identifier === 'nic') {
        fetch(contextPath + '/superadmin/electricity/search?nic=' + nic)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                displayResultsElectricity(data);
            })
            .catch(error => {
                console.error('There was a problem with the fetch operation:', error);
                alert('Error: ' + error.message);
            });
    } else if (identifier === 'nic2') {
        fetch(contextPath + '/superadmin/water/search?nic=' + nic)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                displayResultsWater(data);
            })
            .catch(error => {
                console.error('There was a problem with the fetch operation:', error);
                alert('Error: ' + error.message);
            });
    }

}

function displayResultsElectricity(data) {
    console.log(data);
    const resultsDiv = document.getElementById("results");
    resultsDiv.innerHTML = "";
    if (data.length === 0) {
        resultsDiv.innerHTML = "No results found.";
    } else {
        let table = "<table class='table'><tr><th>Region</th><th>Contact Number</th><th>Email</th><th>Password</th><th>CEB/LECO</th><th>Employee ID</th><th>Username</th><th>First Name</th><th>Last Name</th><th>Main/Regional</th><th>Mobile</th></tr>";
        for (let i = 0; i < data.length; i++) {
            table += "<tr><td>" + data[i].region +
                "</td><td>" + data[i].contactNumber +
                "</td><td>" + data[i].email +
                "</td><td>" + data[i].password +
                "</td><td>" + data[i].utilityType +
                "</td><td>" + data[i].empId +
                "</td><td>" + data[i].uname +
                "</td><td>" + data[i].firstname +
                "</td><td>" + data[i].lastname +
                "</td><td>" + data[i].role +
                "</td><td>" + data[i].mobile +
                "</td></tr>";
        }
        table += "</table>";
        resultsDiv.innerHTML = table;
    }
}

function displayResultsWater(data) {
    var resultsDiv = document.getElementById("results2");
    resultsDiv.innerHTML = "";
    if (data.length === 0) {
        resultsDiv.innerHTML = "No results found.";
    } else {
        var table = "<table class='table'><tr><th>Region</th><th>Contact Number</th><th>Email</th><th>Password</th><th>Employee ID</th><th>Username</th><th>First Name</th><th>Last Name</th><th>Main/Regional</th><th>Mobile</th></tr>";
        for (var i = 0; i < data.length; i++) {
            table += "<tr><td>" + data[i].region +
                "</td><td>" + data[i].contactNumber +
                "</td><td>" + data[i].email +
                "</td><td>" + data[i].password +
                "</td><td>" + data[i].empId +
                "</td><td>" + data[i].uname +
                "</td><td>" + data[i].firstname +
                "</td><td>" + data[i].lastname +
                "</td><td>" + data[i].role +
                "</td><td>" + data[i].mobile +
                "</td></tr>";
                "</td></tr>";
        }
        table += "</table>";
        resultsDiv.innerHTML = table;
    }
}
