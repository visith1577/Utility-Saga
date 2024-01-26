CREATE TABLE customer_bill (
    paymentId SERIAL PRIMARY KEY,
    nic varchar(13) NOT NULL,
    service_type ENUM('electricity', 'water', 'solar_store') NOT NULL,
    bill_id varchar(255) NOT NULL,
    amount_paid FLOAT NOT NULL,
    payment_date DATE NOT NULL,
    FOREIGN KEY (nic) REFERENCES users(nic),
    CHECK (service_type IN ('electricity', 'water', 'solar_store')),
    CHECK (
        (service_type = 'electricity' AND bill_id IN (SELECT billId FROM ebill_list)) OR
        (service_type = 'water' AND bill_id IN (SELECT billId FROM wbill_list)) OR
        (service_type = 'solar_store' AND bill_id IN (SELECT itemId FROM solar_store))
    )
);
