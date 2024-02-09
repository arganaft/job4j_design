
CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

INSERT INTO customers (first_name, last_name, age, country)
VALUES
    ('Иван', 'Иванов', 22, 'Россия'),
    ('Петр', 'Петров', 25, 'США'),
    ('Мария', 'Сидорова', 22, 'Франция'),
    ('Елена', 'Павлова', 40, 'Италия'),
    ('Алексей', 'Смирнов', 28, 'Германия'),
    ('Ольга', 'Козлова', 33, 'Испания'),
    ('Сергей', 'Морозов', 45, 'Канада'),
    ('Анна', 'Новикова', 22, 'Австралия'),
    ('Дмитрий', 'Лебедев', 29, 'Япония'),
    ('Евгений', 'Зайцев', 38, 'Великобритания');


INSERT INTO orders (amount, customer_id)
VALUES
    (1, 1),
    (20, 2),
    (0, 4),
    (25, 5),
    (2, 6),
    (22, 8),
    (0, 9),
    (7, 10);

--Выполните запрос, который вернет список клиентов, возраст которых является минимальным.
    select * from customers where age = (select min(age) from customers);
--Необходимо выполнить запрос, который вернет список пользователей, которые еще не выполнили
--ни одного заказа. Используйте подзапрос, для реализации Вам понадобится NOT IN.
    select * from customers
    join orders on orders.customer_id = customers.id
    where orders.amount = 0;

    select * from customers
    where customers.id not in (select customer_id from orders);

