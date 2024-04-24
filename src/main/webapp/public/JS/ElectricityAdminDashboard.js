let popup = document.getElementById('popup');
let popupcontainer = document.getElementById('popupcontainer');


let style = document.createElement('style');
style.innerHTML = `
  .popup .detail h2:nth-child(2) {
    margin-left: 20px;
  }
`;
document.head.appendChild(style);



function openPopup() {
      popup.classList.add("open-popup");
      popupcontainer.classList.add("open-popupcontainer");
}

function closePopup() {
    // Remove the 'selected' class from the selected row
    let selectedRow = document.querySelector('table tr.selected');
    if (selectedRow) {
        selectedRow.classList.remove('selected');
    }

    popup.classList.remove("open-popup");
    popupcontainer.classList.remove("open-popupcontainer");

    window.location.reload();
}

let rows = document.querySelectorAll('table tr');

rows.forEach(function(row) {
    row.addEventListener('click', function() {

        let selectedRow = document.querySelector('table tr.selected');
        if (selectedRow) {
            selectedRow.classList.remove('selected');
        }

        this.classList.add('selected');
    });
});

document.getElementById('open-popup-btn').addEventListener('click', function() {
    // Get the selected row
    let selectedRow = document.querySelector('table tr.selected');
    let isInactive;
    if (selectedRow) {
        let accountNumber = selectedRow.cells[0].innerText;
        let customerNic = selectedRow.cells[1].innerText;
        let deviceId = selectedRow.querySelector('td:nth-child(9) input').value;
        let connectionStatus = selectedRow.cells[6].innerText;

        document.getElementById('account-number').innerText = accountNumber;
        document.getElementById('customer-nic').innerText = customerNic;
        document.getElementById('device-id-input').value = deviceId;
        document.getElementById('connection-status').innerText = connectionStatus;
        document.getElementById("hiddenAcc").value = accountNumber;
        document.getElementById("delete-device-id").value = deviceId;
        document.getElementById("delete-account-no").value = accountNumber;


        isInactive = connectionStatus === 'INACTIVE';

        openPopup();

        document.getElementById('update-device-btn').addEventListener('click', function() {
            if (isInactive) {

                if(document.getElementById('device-id-input').readOnly) {
                    document.getElementById('device-id-input').readOnly = false;
                    this.innerText = 'Save';
                    document.getElementById('device-id-input').focus();
                }

                if (document.getElementById('device-id-input').value !== deviceId) {

                    fetch(contextPath + '/electricity/regional-admin/api/validate-add-account?iotId=' + encodeURIComponent(document.getElementById('device-id-input').value))
                        .then(response => response.json())
                        .then(data => {
                            if (data.IotIdExists) {
                                Swal.fire({
                                    icon: 'error',
                                    title: 'Oops...',
                                    text: 'Device ID already exists!'
                                });
                            } else {
                                fetch(contextPath + '/electricity/regional-admin/api/update-device', {
                                    method: 'POST',
                                    body: new URLSearchParams({
                                        'deviceId': document.getElementById('device-id-input').value,
                                        'accountNo': accountNumber
                                    })
                                })
                                    .then(response => response.url)
                                    .then(data => {
                                        let url = new URL(data);
                                        let params = new URLSearchParams(url.search);
                                        let success = params.get('success');
                                        if (success) {
                                            Swal.fire({
                                                icon: 'success',
                                                title: 'Success',
                                                text: 'Device ID updated successfully!'
                                            }).then(_ => {
                                                closePopup();
                                            });
                                        } else {
                                            Swal.fire({
                                                icon: 'error',
                                                title: 'Oops...',
                                                text: 'Something went wrong!'
                                            });
                                        }
                                    });
                            }
                        });
                } else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: 'Please change the device ID first!'
                    });
                }

            } else {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Cannot update an active device!'
                });
            }
        });

        document.getElementById('delete-device-btn').addEventListener('click', _ =>  {
            if (isInactive) {
                Swal.fire({
                    title: 'Are you sure?',
                    text: 'You will not be able to recover this device!',
                    icon: 'warning',
                    showCancelButton: true,
                    confirmButtonText: 'Yes, delete it!',
                    cancelButtonText: 'No, keep it'
                }).then((result) => {
                    if (result.isConfirmed) {
                        fetch(contextPath + '/electricity/regional-admin/api/delete-device', {
                            method: 'POST',
                            body: new URLSearchParams({
                                'deviceId': document.getElementById('delete-device-id').value,
                                'accountNo': accountNumber
                            })
                        })
                            .then(response => response.url)
                            .then(data => {
                                let url = new URL(data);
                                let params = new URLSearchParams(url.search);
                                let success = params.get('success');
                                if (success) {
                                    Swal.fire({
                                        icon: 'success',
                                        title: 'Success',
                                        text: 'Device deleted successfully!'
                                    }).then(_ => {
                                        closePopup();
                                    });
                                } else {
                                    Swal.fire({
                                        icon: 'error',
                                        title: 'Oops...',
                                        text: 'Something went wrong!'
                                    });
                                }
                            });
                    }
                });
            } else {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Cannot disconnect an active device!'
                });

            }
        });
    } else {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Please select a row from the table first!'
        });
    }
});