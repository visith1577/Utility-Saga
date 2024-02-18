CREATE TABLE electricity_admin(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nic varchar(13) CHECK (CHAR_LENGTH(nic) > 10),
    uname varchar(25) NOT NULL ,
    firstname varchar(50) NOT NULL ,
    lastname varchar(50) NOT NULL ,
    title varchar(50) NOT NULL,
    pwd varchar(255) NOT NULL CHECK (CHAR_LENGTH(pwd) = 60),
    mobile varchar(20) NOT NULL CHECK ( mobile LIKE '[0-9]%[0-9]' AND CHAR_LENGTH(mobile) = 10),
    email varchar(255) NOT NULL UNIQUE CHECK ( email LIKE  '%_@_%._%' AND CHAR_LENGTH(email) < 255),
    address varchar(255) NOT NULL,
    gender ENUM('male', 'female', 'other') NOT NULL ,
    employer ENUM('CEB', 'LECO')
);