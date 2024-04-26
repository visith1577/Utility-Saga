CREATE TABLE wAccount_list(
                              account_number varchar(255) PRIMARY KEY,
                              nic varchar(13),
                              FOREIGN KEY (nic) REFERENCES users(nic)
);

INSERT INTO wAccount_list (account_number, nic) VALUES ('dummyAccount1', '200114400385');


ALTER TABLE wAccount_list
    ADD COLUMN user_status ENUM('ACTIVE', 'INACTIVE') NOT NULL DEFAULT 'ACTIVE';

ALTER TABLE wAccount_list
    ADD COLUMN meter_status ENUM('ACTIVE', 'INACTIVE') NOT NULL DEFAULT 'ACTIVE';

ALTER TABLE wAccount_list
    ADD COLUMN iot_meter ENUM('YES', 'NO') NOT NULL DEFAULT 'NO';

ALTER TABLE wAccount_list
    ADD COLUMN region VARCHAR(25) NOT NULL ;

ALTER TABLE wAccount_list
    ADD COLUMN sub_region VARCHAR(25),
    ADD COLUMN balance DECIMAL(10,2) NOT NULL DEFAULT 0;

ALTER TABLE waccount_list
    ADD COLUMN iot_id varchar(255) NOT NULL DEFAULT 'NO';


ALTER TABLE wAccount_list
    ADD COLUMN request_id INT,
    ADD CONSTRAINT fk_waccount_request_id FOREIGN KEY (request_id) REFERENCES water_connection_request(id);

DELIMITER $$
CREATE TRIGGER update_water_meter_update
AFTER UPDATE ON waccount_list
FOR EACH ROW
BEGIN
    IF OLD.meter_status <> NEW.meter_status THEN
        INSERT INTO water_regionaladmin_notification (title, recipientType, recipientId, `date`, subject, message)
        VALUES (
'Water Meter Status Update',
            'SPECIFIC',
            NEW.nic,
            CURRENT_TIMESTAMP,
            'IMPORTANT',
            CONCAT('Your meter status of account ', NEW.account_number , ' has been updated to status ', NEW.meter_status)
        );
    END IF;
END$$
DELIMITER ;


ALTER TABLE waccount_list ADD COLUMN address VARCHAR(255) NOT NULL;
ALTER TABLE waccount_list
ALTER COLUMN address SET DEFAULT '';

DELIMITER $$
CREATE TRIGGER update_waccount_balance
AFTER INSERT ON water_manual_payment
FOR EACH ROW
BEGIN
    UPDATE waccount_list
    SET balance = balance - NEW.amount
    WHERE account_number = NEW.account_number;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER update_wbalance_notification
    AFTER UPDATE ON waccount_list
    FOR EACH ROW
BEGIN
    IF OLD.balance <> NEW.balance THEN
        INSERT INTO water_regionaladmin_notification(title, recipienttype, recipientid, date, subject, message)
        VALUES (
                   'Water Account Balance Updated',
                   'SPECIFIC',
                   NEW.nic,
                   CURRENT_TIMESTAMP,
                   'Your Water Account Balance Updated from Manual Payment',
                   CONCAT('Your account balance for account ', NEW.account_number, ' has been updated to ', NEW.balance)
               );
    END IF;
END $$
DELIMITER ;