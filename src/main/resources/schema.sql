drop table if exists product cascade;

create table product (
    id bigint generated by default as identity,
    name varchar(255),
    price int,
    imageUrl varchar(255),
    primary key (id)
)

