CREATE TABLE super_admin(
    nic varchar(13) PRIMARY KEY CHECK (CHAR_LENGTH(nic) > 10),
    uname varchar(25) NOT NULL,
    pwd varchar(255) NOT NULL CHECK (CHAR_LENGTH(pwd) > 8)
);

INSERT INTO super_admin (nic, uname, pwd)
VALUES ('200130704297', 'admin', 'Admin@utilitysaga');
