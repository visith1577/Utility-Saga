use utilitysaga;
CREATE TABLE Electricity_Bill (
    billId INT PRIMARY KEY AUTO_INCREMENT,
    amount FLOAT NOT NULL,
    billedDate DATE NOT NULL,
    dueDate DATE DEFAULT (DATE_ADD(billedDate, INTERVAL 1 MONTH) ) NOT NULL,
    CHECK (dueDate >= billedDate)
);

ALTER TABLE Electricity_Bill
ADD COLUMN userID INT NOT NULL,
ADD CONSTRAINT fk_electricity_user FOREIGN KEY (userId) REFERENCES users(id);