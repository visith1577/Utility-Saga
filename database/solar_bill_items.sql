CREATE TABLE solar_bill_items (
    billItemId SERIAL PRIMARY KEY,
    billId INT NOT NULL,
    itemId INT NOT NULL,
    quantity INT NOT NULL,
    unitPrice FLOAT NOT NULL,
    FOREIGN KEY (billId) REFERENCES solar_bill(billId),
    FOREIGN KEY (itemId) REFERENCES solar_products(productId)
);