// load data on scroll
let page_number = 1;
let is_fetching_data = false;

window.addEventListener('scroll', function() {
    const tblContent = document.querySelector('.tbl-content');
    const table = tblContent.querySelector('table');
    const lastRow = table.querySelector('tbody tr:last-child');

    // Check if the last row is visible and near the bottom
    if (lastRow.getBoundingClientRect().bottom <= window.innerHeight + tblContent.offsetTop + 100 && !is_fetching_data) {
        // Fetch more data from the database
        is_fetching_data = true;
        fetchMoreData();
    }
});

function set_loader(content) {
    const tableBody = document.querySelector('#dataTable');
    let row = tableBody.insertRow();
    row.id = "loader-row"
    let cell = row.insertCell();
    cell.innerHTML = content;
}

function fetchMoreData() {

    set_loader('<div id="loader" class="lds-facebook"><div></div><div></div><div></div></div>');

    // Increment page number for next page
    page_number++;

    // Fetch more data from the servlet with the updated page number
    fetch(contextPath + '/user/user-profile?page=' + page_number)
        .then(response => {
            return response.json();
        })
        .then(data => {
            // console.log(data)
            if (data.hasOwnProperty('electricity_accounts')) {
                data.electricity_accounts.forEach(item => {
                    const row = document.createElement('tr');
                    // Populate table cells with item properties
                    row.innerHTML = `
                    <td>${item.account_number}</td>
                    <td>${item.amount}</td>
                    <td>${item.dueDate}</td>
                    <td>${item.status}</td>
                `;
                    // Append row to table body
                    document.querySelector('#dataTable').appendChild(row);
                })
            }

            if (data.hasOwnProperty('water_accounts')) {
                data.water_accounts.forEach(item => {
                    const row = document.createElement('tr');
                    // Populate table cells with item properties
                    row.innerHTML = `
                    <td>${item.account_number}</td>
                    <td>${item.amount}</td>
                    <td>${item.dueDate}</td>
                    <td>${item.status}</td>
                `;
                    // Append row to table body
                    document.querySelector('#dataTable').appendChild(row);
                })
            }
        })
        .catch(error => {
            console.error('Error fetching more data:', error);
        })
        .finally(() => {
            // Hide loading animation
            document.getElementById('loader-row').remove();
        });
}