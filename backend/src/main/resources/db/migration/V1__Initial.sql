CREATE TABLE condominos
(
    id               varchar(40) primary key,
    name             varchar(120),
    street           varchar(120),
    house_number     varchar(10),
    telephone_number varchar(40),
    direction        varchar(120),
    emergency_number varchar(40),
    paid_status      boolean,
    user_id          varchar(40) references users
);

CREATE TABLE trabajadores
(
    id                varchar(40) primary key,
    tipo              varchar(40),
    nombre_trabajador varchar(40),
    dias_trabajo      varchar(150),
    nombre_condomino  varchar(40),
    horario           varchar(40),
    telefono          varchar(40)
);

CREATE TABLE paquetes
(
    id               varchar(40) primary key,
    nombre_empresa   varchar(120),
    numero_guia      varchar(120),
    recibe_guardia   varchar(120),
    fecha_llegada    date,
    hora_llegada     varchar(40),
    caseta_recibida  varchar(120),
    recibe_inquilino varchar(120),
    entrega_guardia  varchar(120),
    fecha_entrega    date,
    hora_entrega     varchar(40),
    caseta_entrega   varchar(120),
    nombre_condomino varchar(40),
    numero_casa      varchar(15),
    calle            varchar(120),
    entregado        boolean
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
    condomino_id    varchar(40) references condominos
);

CREATE TABLE users
(
    id                   varchar(40) primary key,
    email                varchar(100) unique,
    password             varchar(100),
    name                 varchar(40),
    reset_password_token varchar(40)
);

CREATE TABLE roles_usuario
(
    id      varchar(40) primary key,
    role    varchar(40),
    user_id varchar(40) references users
);

CREATE TABLE user_image
(
    id       varchar(40) primary key,
    user_id  varchar(40) references users,
    uri      varchar(140),
    filename varchar(140)
);