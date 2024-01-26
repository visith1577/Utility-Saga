CREATE TABLE Electricity_Bill (
    billId varchar(255) PRIMARY KEY,
    amount FLOAT NOT NULL,
    billedDate DATE NOT NULL,
    dueDate DATE DEFAULT (DATE_ADD(billedDate, INTERVAL 1 MONTH) ) NOT NULL,
    CHECK (dueDate >= billedDate)
);
