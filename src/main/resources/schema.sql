DROP TABLE IF EXISTS `drone`;
create table drone
(
    drone_id           int auto_increment,
    serial_number      varchar(100),
    model              varchar(30) not null,
    weight             float,
    battery_percentage float       not null,
    state              varchar(30) not null,
    primary key (drone_id)
);

DROP TABLE IF EXISTS `medication`;
create table medication
(
    medicine_id int auto_increment,
    name varchar(200) not null,
    weight float not null,
    code varchar(200) not null,
    image varchar(255) not null,
    primary key (code)
);
