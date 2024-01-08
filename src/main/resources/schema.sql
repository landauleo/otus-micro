CREATE TABLE IF NOT EXISTS postgres.user
(
    id        integer NOT NULL PRIMARY KEY,
    username  varchar(100),
    firstname varchar(100),
    lastname  varchar(100),
    email     varchar(100),
    phone     varchar(100)
);