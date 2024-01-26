CREATE TABLE solar_company (
    compId SERIAL PRIMARY KEY,
    businessRegNo VARCHAR(15) NOT NULL,
    address TEXT NOT NULL,
    district VARCHAR(20) NOT NULL,
    landPhone VARCHAR(20) CHECK (landPhone LIKE '[0-9]%[0-9]' AND CHAR_LENGTH(landPhone) = 10) NOT NULL,
    mobilePhone VARCHAR(20) CHECK (mobilePhone LIKE '[0-9]%[0-9]' AND CHAR_LENGTH(mobilePhone) = 10) NOT NULL,
    email VARCHAR(255) NOT NULL CHECK (email LIKE '%_@_%._%' AND CHAR_LENGTH(email) < 255),
);
