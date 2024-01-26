CREATE TABLE solar_bill (
    billId SERIAL PRIMARY KEY,
    nic varchar(13) NOT NULL,
    itemId INT NOT NULL,
    quantity INT NOT NULL,
    totalAmount FLOAT NOT NULL,
    purchaseDate DATE NOT NULL,
    FOREIGN KEY (nic) REFERENCES users(nic),
    FOREIGN KEY (itemId) REFERENCES solar_store(itemId)
);