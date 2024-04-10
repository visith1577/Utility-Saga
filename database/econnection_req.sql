CREATE TABLE electricity_connection_request(
    id INT AUTO_INCREMENT PRIMARY KEY,
    requester_name varchar(20) NOT NULL,
    account_number varchar(50) DEFAULT 'No Account',
    nic varchar(13) NOT NULL,
    email varchar(255) NOT NULL CHECK ( email LIKE  '%_@_%._%' AND CHAR_LENGTH(email) < 255),
    mobile varchar(20) NOT NULL CHECK (CHAR_LENGTH(mobile) = 10),
    region varchar(50) NOT NULL CHECK ( region NOT LIKE '%[^A-Z]%' AND  CHAR_LENGTH(region) < 50),
    current_address varchar(255) NOT NULL,
    new_address varchar(255) NOT NULL,
    nearest_account varchar(50) default 'No Account',
    connection_requirement ENUM('CONNECTION', 'DISCONNECTION') NOT NULL,
    connection_type
        ENUM('Single phase 30A', 'Three phase 30A', 'Three Phase 60A', 'Single phase 15A', 'Three Phase 15A')
        DEFAULT 'Single phase 30A'
);