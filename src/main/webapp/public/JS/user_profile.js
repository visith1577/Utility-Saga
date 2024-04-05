const water_thresh = document.getElementById("waterThreshold");
const elec_thresh = document.getElementById("electricityThreshold");

function prevent_non_numeric(event) {
    const allowedKeys = [8, 9, 13, 27, 46, 37, 38, 39, 40];
    const decimalAllowed = event.target.value.includes(".") && event.key === ".";

    if (!allowedKeys.includes(event.keyCode) && !/^[0-9.]$/.test(event.key) || decimalAllowed) {
        event.preventDefault();
    }
}

water_thresh.addEventListener("keydown", prevent_non_numeric);
elec_thresh.addEventListener("keydown", prevent_non_numeric);


const add_wacc = document.getElementById("add-btn_water");
const edit_wacc = document.getElementById("edit-btn_water");
const del_wacc = document.getElementById("del-btn_water");


