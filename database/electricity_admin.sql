CREATE TABLE electricity_admin(
    id varchar(50) PRIMARY KEY CHECK (id REGEXP '[a-zA-Z]+(_[a-zA-Z]+.*)?'),
    uname varchar(25) NOT NULL ,
    firstname varchar(50) NOT NULL ,
    lastname varchar(50) NOT NULL ,
    pwd varchar(255) NOT NULL CHECK (CHAR_LENGTH(pwd) > 8),
    tel varchar(20) NOT NULL,
    email varchar(255) NOT NULL UNIQUE CHECK ( email LIKE  '%_@_%._%' AND CHAR_LENGTH(email) < 255),
    address varchar(255) NOT NULL,
    region varchar(50) NOT NULL CHECK (CHAR_LENGTH(region) < 50)
);

ALTER TABLE electricity_admin
ADD column role ENUM('MAIN', 'REGIONAL') NOT NULL;

CREATE TABLE electricity_admin (
  id INT AUTO_INCREMENT PRIMARY KEY,
  region VARCHAR(255) NOT NULL,
  contact_number VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  CONSTRAINT check_email CHECK (email REGEXP '^[\\w-]+@[\\w-]+\\.[\\w-]{2,}$')
);

