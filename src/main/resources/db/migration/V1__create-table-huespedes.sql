create table huespedes(
    id bigint not null auto_increment,
    firstName varchar(100) not null unique ,
    lastName varchar(100) not null unique ,
    email varchar(100) not null,
    phone varchar(100) not null,
    street varchar(100) not null,
    zCode varchar(5) not null,
    city varchar(100) not null,
    number varchar(10) not null,
    complement varchar(100),

    primary key(id));