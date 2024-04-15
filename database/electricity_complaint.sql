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

INSERT INTO electricity_complaint (complaint_no, complaint_category, complaint_type, account_number, nic, email, mobile, complaint)
VALUES
('CMP0001', 'Breakdown', 'Power Outages', '0001', '200123632412', 'example@email.com', '1234567890', 'The power is out in our area and we need immediate assistance.'),
('CMP0002', 'Service Request', 'Connection & Disconnection issues', '0003', '200123647812', 'another@example.com', '9876543210', 'We need to disconnect our service temporarily due to renovations.'),
('CMP0003', 'Breakdown', 'Voltage & frequency problems', '0005', '200145632147', 'third@example.com', '2468013579', 'The voltage in our building seems unstable, causing flickering lights.');



