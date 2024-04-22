ALTER TABLE electricity_connection_request ADD COLUMN account_status ENUM('ADDED', 'REJECTED', 'PENDING') NOT NULL DEFAULT 'PENDING';

ALTER TABLE water_connection_request ADD COLUMN account_status ENUM('ADDED', 'REJECTED', 'PENDING') NOT NULL DEFAULT 'PENDING';

DELIMITER $$
CREATE TRIGGER update_electricity_connection_request
AFTER UPDATE ON utilitysaga.electricity_connection_request
FOR EACH ROW
BEGIN
    IF OLD.account_status <> NEW.account_status THEN
        IF NEW.account_status = 'ADDED' THEN
            INSERT INTO utilitysaga.electricity_regionaladmin_notification (title, recipientType, recipientId, `date`, subject, message)
            VALUES (
                CONCAT('Electricity Connection Request Update ID: ', NEW.id),
                'SPECIFIC',
                NEW.nic,
                CURRENT_TIMESTAMP,
                'IMPORTANT',
                CONCAT('Your request status has been updated to ', NEW.account_status, '. New account number: ', NEW.account_number)
            );
        ELSE
            INSERT INTO utilitysaga.electricity_regionaladmin_notification (title, recipientType, recipientId, `date`, subject, message)
            VALUES (
                CONCAT('Electricity Connection Request Update ID: ', NEW.id),
                'SPECIFIC',
                NEW.nic,
                CURRENT_TIMESTAMP,
                'IMPORTANT',
                CONCAT('Your request status has been updated to ', NEW.account_status)
            );
        END IF;
    END IF;
END$$
DELIMITER ;


DELIMITER $$
CREATE TRIGGER update_water_connection_request
AFTER UPDATE ON utilitysaga.water_connection_request
FOR EACH ROW
BEGIN
    IF OLD.account_status <> NEW.account_status THEN
        IF NEW.account_status = 'ADDED' THEN
            INSERT INTO utilitysaga.water_regionaladmin_notification (title, recipientType, recipientId, `date`, subject, message)
            VALUES (
                CONCAT('Water Connection Request Update ID: ', NEW.id),
                'SPECIFIC',
                NEW.nic,
                CURRENT_TIMESTAMP,
                'IMPORTANT',
                CONCAT('Your request status has been updated to ', NEW.account_status, '. New account number: ', NEW.account_number)
            );
        ELSE
            INSERT INTO utilitysaga.water_regionaladmin_notification (title, recipientType, recipientId, `date`, subject, message)
            VALUES (
                CONCAT('Water Connection Request Update ID: ', NEW.id),
                'SPECIFIC',
                NEW.nic,
                CURRENT_TIMESTAMP,
                'IMPORTANT',
                CONCAT('Your request status has been updated to ', NEW.account_status)
            );
        END IF;
    END IF;
END$$
DELIMITER ;


ALTER TABLE electricity_connection_request
ADD COLUMN date TIMESTAMP;

ALTER TABLE water_connection_request
ADD COLUMN date TIMESTAMP;