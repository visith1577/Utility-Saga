    function getCurrentDateAndTime() {
    const dateTime = new Date();
    const hour = dateTime.getHours();
    let greeting;

    if (hour >= 0 && hour < 12) {
    greeting = "Good morning !";
} else if (hour >= 12 && hour < 17) {
    greeting = "Good afternoon !";
} else {
    greeting = "Good evening !";
}

    const timeString = dateTime.toLocaleTimeString();
    return `${greeting}`;
}

    // Target an HTML element to display the current date and time
    const dateDisplay = document.getElementById("date-container");

    // Set the innerHTML of the element to the current date and time returned by the function
    dateDisplay.innerHTML = getCurrentDateAndTime();

    document.addEventListener("DOMContentLoaded", function() {
    const cartLink = document.querySelector(".cart-link");
    const viewSection = document.getElementById("view");
    const cutIcon = document.getElementById("cut");

    cartLink.addEventListener("click", function(event) {
    event.preventDefault(); // Prevent the default behavior of the link

    // Show the view section
    viewSection.style.display = "flex";
});

    cutIcon.addEventListener("click", function() {
    // Close the view section
    viewSection.style.display = "none";
});
});