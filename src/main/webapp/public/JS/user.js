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