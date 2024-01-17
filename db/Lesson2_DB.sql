
create table devices
(
    id    serial primary key,
    device_name  varchar(255),
    price decimal
);

create table people
(
    id   serial primary key,
    people_name varchar(255)
);

create table devices_people
(
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into devices(device_name, price)
    values ('Iphone', 149499.99), ('Samsung', 123499.99), ('Nokia', 93499.99), ('Xiaomi', 63499.99), ('Huawei', 223499.99);
insert into people(people_name)
    values ('Вася'), ('Дима'), ('Вера'), ('Оля'), ('Витя'), ('Коля'), ('Надя'), ('Женя'), ('Даша'), ('Лена');
insert into devices_people(device_id, people_id)
    values (3, 1), (1, 1), (5, 2), (4, 2), (3, 3), (2, 3), (3, 4), (1, 4), (4, 5), (5, 5), (1, 6), (2, 6),
           (3, 7), (4, 7), (4, 8), (1, 8), (2, 9), (1, 9), (1, 10), (4, 10);