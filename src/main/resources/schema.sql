DROP TABLE IF EXISTS `drone_medication`;
DROP TABLE IF EXISTS `medication`;
DROP TABLE IF EXISTS `drone`;
DROP TABLE IF EXISTS `drone_battery_status`;
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

create table medication
(
    medicine_id int auto_increment,
    name        varchar(200) not null,
    weight      float        not null,
    code        varchar(200) not null,
    image       varchar(255) not null,
    primary key (code)
);

create table drone_medication
(
    id                 int auto_increment,
    drone_id           int          not null,
    code               varchar(200) not null,
    loaded_date_time   datetime,
    unloaded_date_time datetime,
    foreign key (drone_id) REFERENCES drone (drone_id),
    foreign key (code) REFERENCES medication (code)
);

create table drone_battery_status
(
    log_id             int auto_increment,
    drone_id           int   not null,
    battery_percentage float not null
);