CREATE TABLE item
(
    id              int NOT NULL AUTO_INCREMENT,
    name            varchar(45)    DEFAULT NULL,
    description     varchar(45)    DEFAULT NULL,
    cost            decimal(10, 0) DEFAULT NULL,
    profit_margin   decimal(10, 0) DEFAULT NULL,
    price           decimal(10, 0) DEFAULT NULL,
    warranty_period int            DEFAULT NULL,
    quantity        int            DEFAULT NULL,
    supplier_id     int            DEFAULT NULL,
    PRIMARY KEY (id),
    KEY             company_fk_idx (supplier_id),
    CONSTRAINT company_fk FOREIGN KEY (supplier_id) REFERENCES solar_company (id)
)