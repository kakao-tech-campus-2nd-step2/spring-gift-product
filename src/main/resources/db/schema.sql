CREATE TABLE Product
(
    id       BIGINT PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    imageUrl VARCHAR(255) NOT NULL
);

CREATE TABLE Customer
(
    id      BIGINT PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    email   VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    phone   VARCHAR(255) NOT NULL
);
