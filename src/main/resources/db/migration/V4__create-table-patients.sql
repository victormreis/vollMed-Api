CREATE TABLE patient (
    id BIGINT not null AUTO_INCREMENT,
    name varchar(100) not null,
    phone varchar(10) not null unique,
    health_number VARCHAR(50),
    adress varchar(100) not null,
    postal_code varchar(7) not null,
    city varchar(100) not null,
    province char(2) not null,

     primary key(id)
     );