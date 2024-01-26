CREATE TABLE solar_company_products (
    compId INT,
    productId INT,
    PRIMARY KEY (compId, productId),
    FOREIGN KEY (compId) REFERENCES solar_company(compId),
    FOREIGN KEY (productId) REFERENCES products(productId)
);