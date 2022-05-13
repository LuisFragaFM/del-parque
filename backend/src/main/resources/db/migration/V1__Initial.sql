CREATE TABLE calles
(
    id     varchar(40) primary key,
    nombre varchar(40)
);

CREATE TABLE direcciones
(
    id              varchar(40) primary key,
    numero_casa     varchar(10),
    id_calle        varchar(40) references calles,
    nombre_colonia  varchar(40),
    codigo_postal   varchar(10),
    numero_interior varchar(10)
);

CREATE TABLE contactos
(
    id                  varchar(40) primary key,
    email               varchar(120),
    numero_telefono     varchar(20),
    telefono_emergencia varchar(20)
);

CREATE TABLE usuarios
(
    id          varchar(40) primary key,
    id_contacto varchar(40) references contactos,
    username      varchar(120) unique,
    email       varchar(120) unique,
    password    varchar(120)
);

CREATE TABLE roles_by_user
(
    id      varchar(40) primary key,
    user_id varchar(40) references usuarios,
    role    varchar(40)
);

CREATE TABLE automoviles
(
    id      varchar(40) primary key,
    placas  varchar(40),
    color   varchar(40),
    modelo  varchar(40),
    tarjeta varchar(40)
);

CREATE TABLE trabajadores
(
    id     varchar(40) primary key,
    nombre varchar(120),
    fecha  date not null,
    caseta varchar(40),
    hora   date not null
);

CREATE TABLE condominos
(
    id            varchar(40) primary key,
    id_usuario    varchar(40) references usuarios,
    id_direccion  varchar(40) references direcciones,
    id_trabajador varchar(40) references trabajadores
);

CREATE TABLE visitantes
(
    id                 varchar(40) primary key,
    id_automovil       varchar(40) references automoviles,
    id_condominio      varchar(40) references condominos,
    nombre             varchar(40),
    fecha              date not null,
    vigilante_autorizo varchar(120),
    residente          varchar(120),
    caseta_entrada     varchar(40),
    caseta_salida      varchar(40)
);

CREATE TABLE paquetes
(
    id            varchar(40) primary key,
    emisor        varchar(120),
    receptor      varchar(120),
    caseta        varchar(120),
    entregado     boolean,
    id_condominio varchar(40) references condominos
);

CREATE TABLE entregas
(
    id            varchar(40) primary key,
    quien_entrega varchar(120),
    id_paquete    varchar(40) references paquetes
);

CREATE TABLE pagos
(
    id            varchar(40) primary key,
    pagado        boolean,
    id_condominio varchar(40) references condominos
);