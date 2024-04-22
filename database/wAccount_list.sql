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

ALTER TABLE eAccount_list
    ADD COLUMN region VARCHAR(25) NOT NULL ;

ALTER TABLE utilitysaga.eaccount_list
    ADD COLUMN sub_region VARCHAR(25),
    ADD COLUMN balance DECIMAL(10,2) NOT NULL DEFAULT 0;

ALTER TABLE eaccount_list
ADD COLUMN request_id VARCHAR(50),
ADD CONSTRAINT fk_eaccount_request_id FOREIGN KEY (request_id) REFERENCES electricity_connection_request(id);


DELIMITER $$
CREATE TRIGGER update_water_meter_update
AFTER UPDATE ON utilitysaga.waccount_list
FOR EACH ROW
BEGIN
    IF OLD.meter_status <> NEW.meter_status THEN
        INSERT INTO utilitysaga.water_regionaladmin_notification (title, recipientType, recipientId, `date`, subject, message)
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