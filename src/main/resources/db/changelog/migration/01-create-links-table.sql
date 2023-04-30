-- liquibase formatter sql

-- changeset andrewZ:1
CREATE TABLE IF NOT EXISTS links
(
    id          int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    short_link  varchar NOT NULL UNIQUE,
    long_link   varchar NOT NULL UNIQUE,
    create_date date,
    id_user     int
);
-- rollback DROP TABLE links;

-- changeset andrewZ:10
CREATE TABLE IF NOT EXISTS users
(
    id       int GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    login    varchar(100) NOT NULL UNIQUE,
    password varchar(30)  NOT NULL
);
-- rollback DROP TABLE users;
