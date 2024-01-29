
create table products
(
    id       serial primary key,
    product_name     varchar(50),
    producer varchar(50),
    count    integer default 0,
    price    integer
);

/*1)  Триггер должен срабатывать после вставки данных, для любого товара и просто насчитывать налог
на товар (нужно прибавить налог к цене товара). Действовать он должен не на каждый ряд, а на запрос (statement уровень)*/

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

/*2) Триггер должен срабатывать до вставки данных и насчитывать налог
 на товар (нужно прибавить налог к цене товара). Здесь используем row уровень.*/

 create
or replace function discount()
    returns trigger as
$$
    BEGIN
        new.price = new.price + new.price * 0.2;
        return NEW;
    END;
$$ LANGUAGE plpgsql;

create trigger discount_trigger
    before insert
    on products
    for each row
    execute function discount();

/*3) Создайте таблицу:
Нужно написать триггер на row уровне, который сразу после вставки продукта
 в таблицу products, будет заносить имя, цену и текущую дату в таблицу history_of_price. */

create table history_of_price
(
    id    serial primary key,
    history_of_price_name  varchar(50),
    price integer,
    change_date  timestamp
);

create
or replace function write_history()
    returns trigger as
$$
    BEGIN
        insert into history_of_price (history_of_price_name, price, change_date)
        values (new.product_name, new.price, current_timestamp);
        return new;
    END;
$$
LANGUAGE 'plpgsql';

create trigger history_trigger
    after insert
    on products
    for each row
    execute procedure write_history();


insert into products (product_name, producer, count, price)
VALUES ('product_3', 'producer_3', 8, 115);
insert into products (product_name, producer, count, price)
VALUES ('product_1', 'producer_1', 3, 50);

select * from products;
select * from history_of_price;








