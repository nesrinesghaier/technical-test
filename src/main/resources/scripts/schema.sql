drop table if exists delivery;
create table delivery
(
    id                 varchar(20)
        constraint delivery_pkey primary key,
    product            varchar(255) not null,
    supplier           varchar(255) not null,
    quantity           integer      not null,
    expected_date      varchar(255) not null,
    expected_warehouse varchar(255) not null
);