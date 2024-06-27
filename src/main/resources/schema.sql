drop table gift if exists;
create table gift(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name varchar(255),
    price INT,
    imageUrl varchar(255)

);