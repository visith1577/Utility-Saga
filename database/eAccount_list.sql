CREATE TABLE eAccount_list(
                              account_number varchar(255) PRIMARY KEY,
                              nic varchar(13),
                              FOREIGN KEY (nic) REFERENCES users(nic)
);

ALTER TABLE eAccount_list
    ADD COLUMN region VARCHAR(25) NOT NULL ;

ALTER TABLE eAccount_list
    ADD COLUMN user_status ENUM('ACTIVE', 'INACTIVE') NOT NULL DEFAULT 'ACTIVE';

ALTER TABLE eAccount_list
    ADD COLUMN meter_status ENUM('ACTIVE', 'INACTIVE') NOT NULL DEFAULT 'ACTIVE';

ALTER TABLE eAccount_list
    ADD COLUMN iot_meter ENUM('YES', 'NO') NOT NULL DEFAULT 'NO';

ALTER TABLE eAccount_list
    ADD COLUMN region VARCHAR(25) NOT NULL ;

INSERT INTO eAccount_list (account_number, nic) VALUES ('dummyAccount2', '200114400385');

ALTER TABLE other_table DROP FOREIGN KEY constraint_name;

INSERT INTO eAccount_list(account_number, nic, region) VALUES ('0001', '200123632412', 'KOTTAWA');
INSERT INTO eAccount_list(account_number, nic, region) VALUES ('0002', '200123632412', 'KOTTAWA');
INSERT INTO eAccount_list(account_number, nic, region) VALUES ('0003', '200123647812', 'KOTTAWA');
INSERT INTO eAccount_list(account_number, nic, region) VALUES ('0007', '200130704297', 'KOLONNAWA');
INSERT INTO eAccount_list(account_number, nic, region) VALUES ('0005', '200145632147', 'KOTTAWA');
INSERT INTO eAccount_list(account_number, nic, region) VALUES ('0006', '200145632147', 'KOTTAWA');
INSERT INTO eAccount_list(account_number, nic, region) VALUES ('0004', '200145632147', 'KOTTAWA');
INSERT INTO eAccount_list(account_number, nic, region) VALUES ('0008', '200145632147', 'KOTTAWA');
INSERT INTO eAccount_list(account_number, nic, region) VALUES ('0009', '200130704283', 'COLOM');
INSERT INTO eAccount_list(account_number, nic, region) VALUES ('0010', '200123632412', 'COLOM');