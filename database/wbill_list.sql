CREATE TABLE wbill_list(
    bill_id varchar(255) PRIMARY KEY,
    nic varchar(13),
    wbill_no varchar(255) NOT NULL UNIQUE,
    FOREIGN KEY (nic) REFERENCES users(nic),
    FOREIGN KEY (wbill_no) REFERENCES Water_Bill(billId)
)