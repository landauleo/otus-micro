CREATE SCHEMA IF NOT EXISTS micro;

CREATE TABLE IF NOT EXISTS micro.user
(
    id              serial PRIMARY KEY,
    username        varchar(50) NOT NULL,
    firstname       varchar(50),
    lastname        varchar(50),
    email           varchar(100),
    phone           varchar(20),
    hashed_password varchar(100)
);
