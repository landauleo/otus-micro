CREATE TABLE IF NOT EXISTS micro.delivery
(
    id       bigint PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS micro.item
(
    id       bigserial PRIMARY KEY,
    quantity bigint NOT NULL
);


INSERT INTO micro.item( quantity )
VALUES ( 10 )
     , ( 5 )
     , ( 1 );

ALTER TABLE micro."order"
    ADD COLUMN IF NOT EXISTS item_id bigint;
ALTER TABLE micro."order"
    ADD COLUMN IF NOT EXISTS courier_id bigint;
ALTER TABLE micro."order"
    ADD COLUMN IF NOT EXISTS quantity smallint;
