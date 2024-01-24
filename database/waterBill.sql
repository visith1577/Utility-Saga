use utilitysaga;
CREATE TABLE Water_Bill (
    billId INT PRIMARY KEY AUTO_INCREMENT,
    amount FLOAT NOT NULL,
    billedDate DATE NOT NULL,
    dueDate DATE DEFAULT (DATE_ADD(billedDate, INTERVAL 1 MONTH)),
    userID INT NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (userID) REFERENCES users(id),
    CHECK (dueDate >= billedDate)
);

ALTER TABLE Water_Bill DROP FOREIGN KEY fk_user;

ALTER TABLE Water_Bill ADD CONSTRAINT fk_water_user FOREIGN KEY (userID) REFERENCES users(id);