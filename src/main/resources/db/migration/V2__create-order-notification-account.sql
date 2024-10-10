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

CREATE TABLE IF NOT EXISTS micro."order"
(
    id      bigserial PRIMARY KEY,
    user_id bigint         NOT NULL,
    amount  numeric(19, 2) NOT NULL,
    status  varchar(20)    NOT NULL
);

CREATE TABLE IF NOT EXISTS micro.notification
(
    id      bigserial PRIMARY KEY,
    user_id bigint NOT NULL,
    message text   NOT NULL
);

CREATE TABLE IF NOT EXISTS micro.account
(
    id      bigserial PRIMARY KEY,
    user_id bigint         NOT NULL,
    balance numeric(19, 2) NOT NULL
);

CREATE INDEX idx_order_user_id ON micro."order" (user_id);
CREATE INDEX idx_notification_user_id ON micro.notification (user_id);
CREATE INDEX idx_account_user_id ON micro.account (user_id);

ALTER TABLE micro."order"
    ADD CONSTRAINT chk_order_amount_positive CHECK (amount > 0);
ALTER TABLE micro.account
    ADD CONSTRAINT chk_account_balance_non_negative CHECK (balance >= 0);
