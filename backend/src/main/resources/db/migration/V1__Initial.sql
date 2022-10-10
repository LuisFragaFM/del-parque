CREATE TABLE users
(
    id                   varchar(40) primary key,
    email                varchar(100) unique,
    password             varchar(100),
    name                 varchar(40),
    telephone_number     varchar(40),
    emergency_number     varchar(40),
    reset_password_token varchar(40)
);

CREATE TABLE roles_by_user
(
    id      varchar(40) primary key,
    role    varchar(40),
    user_id varchar(40),
    foreign key (user_id) references users (id) on delete cascade on update cascade
);

CREATE TABLE user_image
(
    id       varchar(40) primary key,
    uri      varchar(140),
    filename varchar(140),
    user_id  varchar(40),
    foreign key (user_id) references users (id) on delete cascade on update cascade
);

CREATE TABLE condominos
(
    id           varchar(40) primary key,
    street       varchar(120),
    house_number varchar(10),
    paid_status  boolean,
    user_id      varchar(40),
    foreign key (user_id) references users (id) on delete cascade on update cascade
);

CREATE TABLE trabajadores
(
    id               varchar(40) primary key,
    type             varchar(40),
    name             varchar(40),
    schedule         varchar(40),
    telephone_number varchar(40),
    condomino_id     varchar(40),
    foreign key (condomino_id) references condominos (id) on delete cascade on update cascade
);

CREATE TABLE work_days
(
    id            varchar(40) primary key,
    day_name      varchar(40),
    trabajador_id varchar(40),
    foreign key (trabajador_id) references trabajadores (id) on delete cascade on update cascade
);

CREATE TABLE guardias
(
    id      varchar(40) primary key,
    user_id varchar(40),
    foreign key (user_id) references users (id) on delete cascade on update cascade
);

CREATE TABLE paquetes
(
    id                varchar(40) primary key,
    company_name      varchar(120),
    guide_number      varchar(120),
    receives_guard    varchar(120),
    arrival_date      date,
    arrival_time      varchar(40),
    receives_booth    varchar(120),
    receives_resident varchar(120),
    delivery_guard    varchar(120),
    delivery_date     date,
    delivery_time     varchar(40),
    delivery_booth    varchar(120),
    delivery          boolean,
    condomino_id      varchar(40),
    foreign key (condomino_id) references condominos (id) on delete cascade on update cascade
);

CREATE TABLE visitantes
(
    id              varchar(40) primary key,
    name            varchar(140),
    card            varchar(140),
    vehicle         varchar(140),
    license_plates  varchar(120),
    arrival_date    date,
    departure_date  date,
    check_in        varchar(40),
    departure_time  varchar(40),
    authorization   varchar(120),
    arrival_booth   varchar(120),
    departure_booth varchar(120),
    authorized      boolean,
    gone_out        boolean,
    condomino_id    varchar(40),
    foreign key (authorization) references guardias (id) on delete cascade on update cascade,
    foreign key (condomino_id) references condominos (id) on delete cascade on update cascade
);