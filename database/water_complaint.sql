CREATE TABLE water_complaint(
    complaint_no varchar(255) PRIMARY KEY,
    complaint_category ENUM('Breakdown', 'Service Request') NOT NULL,
    complaint_type
        ENUM('Main Leak', 'Connection Leak', 'No Water', 'Low Pressure', 'Leak Near Meter', 'Quality Problem', 'Others')
        DEFAULT 'Others' NOT NULL,
    account_number varchar(50) NOT NULL,
    nic varchar(13) NOT NULL,
    email varchar(50) NOT NULL,
    complaint TEXT NOT NULL,
    FOREIGN KEY (nic) REFERENCES users(nic),
    FOREIGN KEY (account_number) REFERENCES Water_Bill(billId)
);
