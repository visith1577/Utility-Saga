CREATE TABLE water_bill (
    billId varchar(255) PRIMARY KEY,
    amount FLOAT NOT NULL,
    billedDate DATE NOT NULL,
    dueDate DATE DEFAULT (DATE_ADD(billedDate, INTERVAL 1 MONTH)),
    CHECK (dueDate >= billedDate)
);

ALTER TABLE  water_bill
ADD COLUMN status ENUM('PAID', 'PENDING', 'OVERDUE') NOT NULL;
ALTER TABLE water_bill
ADD COLUMN account_number varchar(255) NOT NULL;
ALTER TABLE water_bill
ADD CONSTRAINT fw_key FOREIGN KEY (account_number) REFERENCES wAccount_list(account_number);

DELIMITER $$
CREATE EVENT update_bill_status_water
ON SCHEDULE EVERY 1 DAY
STARTS CURRENT_TIMESTAMP
DO
BEGIN
    UPDATE water_bill
    SET status = 'OVERDUE'
    WHERE status = 'PENDING'
      AND DATE_ADD(billedDate, INTERVAL 30 DAY) < CURRENT_DATE();
END$$
DELIMITER ;

GRANT EVENT ON *.* TO 'root'@'localhost';

SET GLOBAL event_scheduler = ON;


DELIMITER $$
CREATE TRIGGER update_meter_water
AFTER UPDATE ON water_bill
FOR EACH ROW
BEGIN
    IF OLD.status = 'OVERDUE' AND NEW.status != 'OVERDUE' THEN
        UPDATE waccount_list
        SET meter_status = 'ACTIVE'
        WHERE account_number = NEW.account_number
          AND meter_status = 'INACTIVE';
    END IF;
END$$
DELIMITER ;