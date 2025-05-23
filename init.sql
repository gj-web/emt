create type category_enum as ENUM ('ROOM', 'HOUSE', 'FLAT', 'APARTMENT', 'HOTEL', 'MOTEL');

create table country
(
    id bigint generated by default as identity primary key,
    name      varchar(255),
    continent varchar(255)
);

create table host
(
    id bigint  generated by default as identity primary key,
    name       varchar(255),
    surname    varchar(255),
    country_id bigint references country
);

create table housing
(
    id bigint generated by default as identity primary key,
    name      varchar(255),
    category  varchar(255),
    host_id   bigint references host,
    num_rooms integer,
    is_rented boolean default false
);

create table users
(
    username                   varchar(255) primary key,
    password                   varchar(255),
    name                       varchar(255),
    surname                    varchar(255),
    is_account_non_expired     boolean default true,
    is_account_non_locked      boolean default true,
    is_credentials_non_expired boolean default true,
    is_enabled                 boolean default true,
    role                       varchar(50)
);

create table review
(
    id      bigint generated by default as identity primary key,
    comment varchar(255),
    rate    integer
);



CREATE TABLE temporary_reservation
(
    id            bigint generated by default as identity primary key,
    user_username varchar(255) references users
);

CREATE TABLE temporary_reservation_housing
(
    temp_acc_id bigint references temporary_reservation on delete cascade,
    acc_id      bigint references housing on delete cascade,
    primary key (temp_acc_id, acc_id)
);

create index idx_user_username_password on users (username, password);
create index idx_user_username on users (username);
create index idx_user_role on users (role);