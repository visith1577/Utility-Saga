CREATE TABLE ebill_list(
    bill_id varchar(255) PRIMARY KEY,
    nic varchar(13),
    ebill_no varchar(255) NOT NULL UNIQUE,
    FOREIGN KEY (nic) REFERENCES users(nic),
    FOREIGN KEY (ebill_no) REFERENCES Electricity_Bill(billId)
)