window.addEventListener("load", () => {
    const adjustHeaderPadding = () => {
        const tblContentWidth = document.querySelector('.tbl-content').offsetWidth;
        const tblTableWidth = document.querySelector('.tbl-content table').offsetWidth;
        let scrollWidth = tblContentWidth - tblTableWidth;

        document.querySelector('.tbl-header').style.paddingRight = `${scrollWidth}px`;
    };

    // Initial adjustment on load
    adjustHeaderPadding();

    // Bind the adjustment function to the window resize event
    window.addEventListener("resize", adjustHeaderPadding);
});


const edit_button = document.getElementById("editButton");

edit_button.addEventListener('click', onToggle);

function onToggle() {
    let form = document.getElementById("user-profile");
    let inputs = form.getElementsByTagName('input');
    let file = document.getElementById("imageInput");
    let editButton = document.getElementById('editButton');
    let saveButton = document.getElementById('saveButton');
    let labels = form.querySelectorAll('#user-profile > label');

    for (let i = 0; i < inputs.length; i++) {
        inputs[i].readOnly = !inputs[i].readOnly;
    }



    if (editButton.textContent === 'Edit') {
        editButton.textContent = 'Cancel';
        file.style.display = 'block';
        saveButton.style.display = 'inline-block';

        for (let i = 0; i < labels.length; i++) {
            labels[i].style.display = 'block';
        }
    } else {
        editButton.textContent = 'Edit';
        file.style.display = 'none';
        saveButton.style.display = 'none';
        for (let i = 0; i < labels.length; i++) {
            labels[i].style.display = 'none';
        }
        window.location.reload();
    }
}

// temp function
document.getElementById('user-profile').addEventListener('submit', (event) => {
    event.preventDefault();

    console.log('Data Saved.');
});


const list_item = document.getElementById("prof-id");

function hideListItem() {
    list_item.classList.add("hidden");
}

// Use setTimeout to delay hiding
setTimeout(hideListItem, 5000);
