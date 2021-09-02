CREATE TABLE users
(
    id   uuid         not null default uuid_generate_v4()
        constraint pk_users primary key,
    name varchar(255) not null
        constraint uc_users__name unique
) WITHOUT OIDS;
