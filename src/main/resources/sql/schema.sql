drop table if exists product;
create table customer (
                          id bigint auto_increment,
                          name varchar(255),
                          price int,
                          imageUrl varchar(255)
                          primary key id;
)
