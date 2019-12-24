
create table if not exists store(
    id bigint(20), -- is synthetic PK needed ?
    region char(3) not null,
    district char(3) not null,
    store_code char(5) not null,
    store_name varchar(25) not null,
    address_line_1 varchar(25), -- is the length enough?
    address_line_2 varchar(25),
    city varchar(50) not null,
    state char(3) not null,
    postal_code varchar(10) not null,
    brand_number int(2) not null,

    PRIMARY KEY (id)
);


