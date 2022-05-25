CREATE TABLE condominos
(
    id                 varchar(40) primary key,
    nombre             varchar(120),
    calle              varchar(120),
    numero_casa        varchar(10),
    numero_telefono    varchar(40),
    direccion          varchar(120),
    correo_electronico varchar(140),
    numero_emergencia  varchar(40)
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
    calle            varchar(120)
);

CREATE TABLE visitantes
(
    id                 varchar(40) primary key,
    nombre_visitante   varchar(140),
    tarjeton_visitante varchar(140),
    vehiculo_visitante varchar(140),
    placas_vehiculo    varchar(120),
    fecha_llegada      date,
    fecha_salida       date,
    hora_llegada       varchar(40),
    hora_salida        varchar(40),
    autorizacion       varchar(120),
    caseta_llegada     varchar(120),
    caseta_salida      varchar(120),
    id_condomino       varchar(40) references condominos
);

CREATE TABLE usuarios
(
    id     varchar(40) primary key,
    email  varchar(100),
    nombre varchar(40)
);

CREATE TABLE roles_usuario
(
    id         varchar(40) primary key,
    role       varchar(40),
    id_usuario varchar(40) references usuarios
);

CREATE TABLE logins
(
    id      varchar(40) primary key,
    email   varchar(200) unique,
    user_id varchar(40) references usuarios
);