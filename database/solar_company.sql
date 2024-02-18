CREATE TABLE solar_company (
    compId INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    uname varchar(25) NOT NULL ,
    pwd varchar(255) NOT NULL CHECK (CHAR_LENGTH(pwd) = 60),
    businessRegNo VARCHAR(15) NOT NULL,
    address TEXT NOT NULL,
    district VARCHAR(20) NOT NULL,
    landPhone VARCHAR(20) CHECK (landPhone LIKE '[0-9]%[0-9]' AND CHAR_LENGTH(landPhone) = 10) NOT NULL,
    mobilePhone VARCHAR(20) CHECK (mobilePhone LIKE '[0-9]%[0-9]' AND CHAR_LENGTH(mobilePhone) = 10) NOT NULL,
    email VARCHAR(255) NOT NULL CHECK (email LIKE '%_@_%._%' AND CHAR_LENGTH(email) < 255)
);
