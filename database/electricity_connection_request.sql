ALTER TABLE electricity_connection_request ADD COLUMN account_status ENUM('ADDED', 'REJECTED', 'PENDING') NOT NULL DEFAULT 'PENDING';

ALTER TABLE water_connection_request ADD COLUMN account_status ENUM('ADDED', 'REJECTED', 'PENDING') NOT NULL DEFAULT 'PENDING';