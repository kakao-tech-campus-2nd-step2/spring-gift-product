CREATE TABLE product (
                          id BIGINT PRIMARY KEY,
                          category VARCHAR(255),
                          name VARCHAR(255) NOT NULL,
                          price BIGINT NOT NULL
);