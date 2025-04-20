create table doctors(

    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    phone varchar(10) not null unique,
    mra varchar(12) not null unique,
    specialty varchar(100) not null,
    adress varchar(100) not null,
    postal_code varchar(7) not null,
    city varchar(100) not null,
    province char(2) not null,

    primary key(id)

);