
create table products
(
    id       serial primary key,
    product_name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

create
or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id = (select id from inserted)
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger tax_trigger
    after insert
    on products
    referencing new table as
                    inserted
    for each statement
    execute procedure tax();

insert into products (product_name, producer, count, price)
VALUES ('product_3', 'producer_3', 8, 115);
insert into products (product_name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 50);

select * from products;








