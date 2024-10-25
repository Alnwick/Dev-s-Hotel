create table reservas(
    id bigint not null auto_increment,
    huesped_id bigint not null,
    check_In datetime not null,
    check_Out datetime not null,
    payment varchar(100) not null,
    price varchar(100) not null,
    active tinyint,

    primary key(id),
    constraint fk_reservas_huesped_id foreign key(huesped_id) references huespedes(id)
);