CREATE TABLE wAccount_list(
    account_number varchar(255) PRIMARY KEY,
    nic varchar(13),
    FOREIGN KEY (nic) REFERENCES users(nic)
);

INSERT INTO wAccount_list (account_number, nic) VALUES ('dummyAccount1', '200114400385');