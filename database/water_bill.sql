CREATE TABLE Water_Bill (
    billId varchar(255) PRIMARY KEY,
    amount FLOAT NOT NULL,
    billedDate DATE NOT NULL,
    dueDate DATE DEFAULT (DATE_ADD(billedDate, INTERVAL 1 MONTH)),
    CHECK (dueDate >= billedDate)
);