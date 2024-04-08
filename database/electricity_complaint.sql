CREATE TABLE electricity_complaint(
    complaint_no varchar(255) PRIMARY KEY,
    complaint_category ENUM('Breakdown', 'Service Request') NOT NULL,
    complaint_type
        ENUM('Billing issues', 'Connection & Disconnection issues', 'Power Outages', 'Voltage & frequency problems', 'Smart meter problems', 'Quality Problem', 'Others')
        DEFAULT 'Others' NOT NULL,
    account_number varchar(50) NOT NULL,
    nic varchar(13) NOT NULL,
    email varchar(255) NOT NULL CHECK ( email LIKE  '%_@_%._%' AND CHAR_LENGTH(email) < 255),
    mobile varchar(20) NOT NULL CHECK (CHAR_LENGTH(mobile) = 10),
    complaint TEXT NOT NULL,
    FOREIGN KEY (nic) REFERENCES users(nic),
    FOREIGN KEY (account_number) REFERENCES eAccount_list(account_number)
);

ALTER TABLE electricity_complaint
ADD COLUMN complaint_status ENUM('ACTIVE', 'PENDING', 'DONE');
CREATE TRIGGER set_complaint_status_active
BEFORE INSERT ON electricity_complaint
FOR EACH ROW
BEGIN
    SET NEW.complaint_status = 'ACTIVE';
END;

