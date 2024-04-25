--CREATE TABLE electricity_admin(
--    id varchar(50) PRIMARY KEY CHECK (id REGEXP '[a-zA-Z]+(_[a-zA-Z]+.*)?'),
--    uname varchar(25) NOT NULL ,
--    firstname varchar(50) NOT NULL ,
--    lastname varchar(50) NOT NULL ,
--    pwd varchar(255) NOT NULL CHECK (CHAR_LENGTH(pwd) > 8),
--    tel varchar(20) NOT NULL,
--    email varchar(255) NOT NULL UNIQUE CHECK ( email LIKE  '%_@_%._%' AND CHAR_LENGTH(email) < 255),
--    address varchar(255) NOT NULL,
--    region varchar(50) NOT NULL CHECK (CHAR_LENGTH(region) < 50)
--);
--
--ALTER TABLE electricity_admin
--ADD column role ENUM('MAIN', 'REGIONAL') NOT NULL;

CREATE TABLE electricity_admin (
  id INT AUTO_INCREMENT PRIMARY KEY,
  region VARCHAR(255) NOT NULL,
  contact_number VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL,
  utilityType VARCHAR(255) NOT NULL,
  empid VARCHAR(50) NOT NULL UNIQUE,
  uname VARCHAR(25) NOT NULL,
  firstname VARCHAR(50) NOT NULL,
  lastname VARCHAR(50) NOT NULL,
  role ENUM('MAIN', 'REGIONAL') NOT NULL,
  mobile VARCHAR(20) NOT NULL,
  CONSTRAINT check_email CHECK (email REGEXP '^[\\w-]+@[\\w-]+\\.[\\w-]{2,}$'),
  CONSTRAINT check_role CHECK (role IN ('MAIN', 'REGIONAL'))
);

ALTER TABLE electricity_admin
DROP CONSTRAINT check_email;

ALTER TABLE electricity_admin
ADD CONSTRAINT check_email
CHECK (email REGEXP '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$');

ALTER TABLE electricity_admin
ALTER COLUMN region SET DEFAULT 'HEADOFFICE';

ALTER TABLE electricity_admin
ADD CONSTRAINT unique_region
UNIQUE (region);

ALTER TABLE utilitysaga.electricity_admin
ADD COLUMN activate_status ENUM('ACTIVE', 'INACTIVE') NOT NULL DEFAULT 'ACTIVE';
