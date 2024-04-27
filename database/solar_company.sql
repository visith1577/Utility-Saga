CREATE TABLE solar_company
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    cname    VARCHAR(50)  NOT NULL,
    bnum     VARCHAR(25)  NOT NULL,
    ownernic VARCHAR(13) CHECK (LENGTH(ownernic) > 10),
    uname    VARCHAR(25)  NOT NULL,
    pwd      VARCHAR(255) NOT NULL CHECK (LENGTH(pwd) > 8),
    mobile   VARCHAR(20)  NOT NULL CHECK (mobile REGEXP '^[0-9]{10}$'),
    companytelnum VARCHAR(20) CHECK (companytelnum REGEXP '^[0-9]{10}$' OR companytelnum IS NULL),
    email VARCHAR(255) NOT NULL CHECK (email REGEXP '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$' AND LENGTH(email) < 255),
    address VARCHAR(255) NOT NULL,
    district VARCHAR(50) NOT NULL CHECK (district REGEXP '^[a-z].*[a-z]$' AND LENGTH(district) < 50),
    remarks TEXT(750),
    approval_status VARCHAR(20) DEFAULT 'Pending',
    CONSTRAINT unique_email_constraint UNIQUE (email)
);

ALTER TABLE solar_company
    MODIFY COLUMN approval_status ENUM('PENDING', 'REJECTED', 'APPROVED') DEFAULT 'PENDING';

UPDATE solar_company
SET approval_status = 'REJECTED'
WHERE bnum = '0001234cdfed';