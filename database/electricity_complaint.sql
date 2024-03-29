CREATE TABLE electricity_complaint(
    complaint_no varchar(255) PRIMARY KEY,
    complaint_category ENUM('Breakdown', 'Service Request') NOT NULL,
    account_number varchar(50) NOT NULL UNIQUE,
    nic varchar(13) NOT NULL,
    complaint TEXT NOT NULL,
    FOREIGN KEY (nic) REFERENCES users(nic),
    FOREIGN KEY (account_number) REFERENCES Electricity_Bill(billId)
)

ALTER TABLE electricity_complaint
ADD COLUMN complaint_type ENUM('Electricity Breakdown', 'Meter malfunction', 'Solar System malfunction') NOT NULL,
ADD COLUMN complaint_status ENUM('Open', 'Closed', 'Pending') NOT NULL;