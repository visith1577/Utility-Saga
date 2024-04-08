CREATE TABLE eAccount_list(
    account_number varchar(255) PRIMARY KEY,
    nic varchar(13),
    FOREIGN KEY (nic) REFERENCES users(nic)
);

INSERT INTO eAccount_list (account_number, nic) VALUES ('dummyAccount2', '200114400385');