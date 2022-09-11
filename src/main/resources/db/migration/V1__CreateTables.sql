CREATE TABLE number
(
    ordinal_id          BIGINT          NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    value               BIGINT          NOT NULL
);
