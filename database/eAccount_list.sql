CREATE TABLE eAccount_list(
                              account_number varchar(255) PRIMARY KEY,
                              nic varchar(13)
);

ALTER TABLE eAccount_list
    ADD COLUMN region VARCHAR(25) NOT NULL ;

ALTER TABLE eAccount_list
    ADD COLUMN user_status ENUM('ACTIVE', 'INACTIVE') NOT NULL DEFAULT 'ACTIVE';

ALTER TABLE eAccount_list
    ADD COLUMN meter_status ENUM('ACTIVE', 'INACTIVE') NOT NULL DEFAULT 'ACTIVE';

ALTER TABLE eAccount_list
    ADD COLUMN iot_meter ENUM('YES', 'NO') NOT NULL DEFAULT 'NO';

ALTER TABLE eAccount_list
    ADD COLUMN region VARCHAR(25) NOT NULL ;

ALTER TABLE eaccount_list
    ADD COLUMN sub_region VARCHAR(25),
    ADD COLUMN balance DECIMAL(10,2) NOT NULL DEFAULT 0;

ALTER TABLE eaccount_list
ADD CONSTRAINT fk_eaccount_request_id FOREIGN KEY (request_id) REFERENCES electricity_connection_request(id);

ALTER TABLE eaccount_list
ADD COLUMN request_id INT NULL;

INSERT INTO eAccount_list (account_number, nic) VALUES ('dummyAccount2', '200114400385');

ALTER TABLE other_table DROP FOREIGN KEY constraint_name;

INSERT INTO eAccount_list(account_number, nic, region) VALUES ('0001', '200123632412', 'KOTTAWA');
INSERT INTO eAccount_list(account_number, nic, region) VALUES ('0002', '200123632412', 'KOTTAWA');
INSERT INTO eAccount_list(account_number, nic, region) VALUES ('0003', '200123647812', 'KOTTAWA');
INSERT INTO eAccount_list(account_number, nic, region) VALUES ('0007', '200130704297', 'KOLONNAWA');
INSERT INTO eAccount_list(account_number, nic, region) VALUES ('0005', '200145632147', 'KOTTAWA');
INSERT INTO eAccount_list(account_number, nic, region) VALUES ('0006', '200145632147', 'KOTTAWA');
INSERT INTO eAccount_list(account_number, nic, region) VALUES ('0004', '200145632147', 'KOTTAWA');
INSERT INTO eAccount_list(account_number, nic, region) VALUES ('0008', '200145632147', 'KOTTAWA');
INSERT INTO eAccount_list(account_number, nic, region) VALUES ('0009', '200130704283', 'COLOM');
INSERT INTO eAccount_list(account_number, nic, region) VALUES ('0010', '200123632412', 'COLOM');
INSERT INTO eAccount_list(account_number, nic, region, subregion) VALUES ('0011', '200130704296', 'KOTTAWA', 'KOTTAWA1');



CREATE TABLE eaccount_list (
    account_number INT AUTO_INCREMENT PRIMARY KEY,
    nic VARCHAR(13),
    FOREIGN KEY (nic) REFERENCES users(nic),
    region VARCHAR(25) NOT NULL,
    user_status ENUM('ACTIVE', 'INACTIVE') NOT NULL DEFAULT 'ACTIVE',
    meter_status ENUM('ACTIVE', 'INACTIVE') NOT NULL DEFAULT 'ACTIVE',
    iot_meter ENUM('YES', 'NO') NOT NULL DEFAULT 'NO',
    sub_region VARCHAR(25),
    balance DECIMAL(10,2) NOT NULL DEFAULT 0
);

ALTER TABLE eAccount_list
    ADD COLUMN iot_id varchar(255) NOT NULL DEFAULT 'NO';

DELIMITER $$
CREATE TRIGGER update_electricity_meter_update
AFTER UPDATE ON utilitysaga.eaccount_list
FOR EACH ROW
BEGIN
    IF OLD.meter_status <> NEW.meter_status THEN
        INSERT INTO utilitysaga.electricity_regionaladmin_notification (title, recipientType, recipientId, `date`, subject, message)
        VALUES (
'Electricity Meter Status Update',
            'SPECIFIC',
            NEW.nic,
            CURRENT_TIMESTAMP,
            'IMPORTANT',
            CONCAT('Your meter status of account ', NEW.account_number , ' has been updated to status ', NEW.meter_status)
        );
    END IF;
END$$
DELIMITER ;



DELIMITER $$
CREATE TRIGGER update_eaccount_balance
    AFTER INSERT ON utilitysaga.electricity_manual_payment
    FOR EACH ROW
BEGIN
    UPDATE utilitysaga.eaccount_list
    SET balance = balance - NEW.amount
    WHERE account_number = NEW.account_number;
END$$
DELIMITER ;

DELIMITER $$
CREATE TRIGGER update_balance_notification
    AFTER UPDATE ON utilitysaga.eaccount_list
    FOR EACH ROW
BEGIN
    IF OLD.balance <> NEW.balance THEN
        INSERT INTO utilitysaga.electricity_regionaladmin_notification (title, recipientType, recipientId, `date`, subject, message)
        VALUES (
                   'Balance Update',
                   'SPECIFIC',
                   NEW.nic,
                   CURRENT_TIMESTAMP,
                   'Balance Updated',
                   CONCAT('Your account balance for account ', NEW.account_number , ' has been updated to ', NEW.balance)
               );
    END IF;
END$$
DELIMITER ;
