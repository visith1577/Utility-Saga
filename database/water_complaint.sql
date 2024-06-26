CREATE TABLE water_complaint(
    complaint_no varchar(255) PRIMARY KEY,
    complaint_category ENUM('Breakdown', 'Service Request') NOT NULL,
    complaint_type
        ENUM('Main Leak', 'Connection Leak', 'No Water', 'Low Pressure', 'Leak Near Meter', 'Quality Problem', 'Others')
        DEFAULT 'Others' NOT NULL,
    account_number varchar(50) NOT NULL,
    nic varchar(13) NOT NULL,
    email varchar(255) NOT NULL CHECK ( email LIKE  '%_@_%._%' AND CHAR_LENGTH(email) < 255),
    mobile varchar(20) NOT NULL CHECK (CHAR_LENGTH(mobile) = 10),
    complaint TEXT NOT NULL,
    FOREIGN KEY (nic) REFERENCES users(nic),
    FOREIGN KEY (account_number) REFERENCES wAccount_list(account_number)
);

ALTER TABLE water_complaint
ADD COLUMN complaint_status ENUM('ACTIVE', 'PENDING', 'DONE');
CREATE TRIGGER set_water_complaint_status_active
BEFORE INSERT ON water_complaint
FOR EACH ROW
BEGIN
    SET NEW.complaint_status = 'ACTIVE';
END;

DELIMITER $$
CREATE TRIGGER update_wran_after_ec_update
AFTER UPDATE ON utilitysaga.water_complaint
FOR EACH ROW
BEGIN
    IF OLD.complaint_status <> NEW.complaint_status THEN
        INSERT INTO utilitysaga.water_regionaladmin_notification (title, recipientType, recipientId, `date`, subject, message)
        VALUES (
'Water Complaint',
            'SPECIFIC',
            NEW.nic,
            CURRENT_TIMESTAMP,
            'Status Update Electricity- Complaint',
            CONCAT('The complaint is in ', NEW.complaint_status, ' status')
        );
    END IF;
END$$
DELIMITER ;

ALTER TABLE water_complaint
ADD COLUMN date TIMESTAMP;


