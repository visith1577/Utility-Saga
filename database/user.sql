CREATE TABLE users(
    nic varchar(13) PRIMARY KEY CHECK (CHAR_LENGTH(nic) > 10),
    uname varchar(25) NOT NULL,
    firstname varchar(50) NOT NULL ,
    lastname varchar(50) NOT NULL ,
    pwd varchar(255) NOT NULL CHECK (CHAR_LENGTH(pwd) > 8),
    mobile varchar(20) NOT NULL ,
    home varchar(20) ,
    email varchar(255) NOT NULL ,
    address varchar(255) NOT NULL,
    provider ENUM('CEB', 'LECO'),
    region varchar(50) NOT NULL ,
    services SET('electricity', 'water', 'solar') NOT NULL
);

ALTER TABLE users
ADD CONSTRAINT unique_email_constraint UNIQUE (email);
