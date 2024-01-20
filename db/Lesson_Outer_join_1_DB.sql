
create table car_bodies
(
    id    serial primary key,
    bodie_name  text
);
create table car_engines
(
    id    serial primary key,
    engine_name  text
);

create table car_transmissions
(
    id    serial primary key,
    transmission_name  text
);

create table cars
(
    id    serial primary key,
    car_name  text,
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

insert into car_bodies(bodie_name)
    values ('Седан'), ('Хэтчбек'), ('Лифтбек'), ('Универсал'), ('Внедорожник'), ('Кроссовер'), ('Минивэн');

insert into car_engines(engine_name)
    values ('V образный двигатель'), ('Линейный двигатель'), ('Прямой двигатель'),
           ('Плоский двигатель'), ('Ротационный двигатель');

insert into car_transmissions(transmission_name)
    values ('Механическая'), ('Автоматическая'), ('Роботизированная'), ('Вариативная (бесступенчатая)');

insert into cars(car_name, body_id, engine_id, transmission_id)
    values ('CADILLAC', 1, 1, 1), ('BENTLEY', 2, 3, 2),  ('FIAT', 7, 3, 1),  ('GEELY', 6, 2, 1),  ('JAGUAR', 3, 1, 1),
           ('LAMBORGHINI', null, 1, null), ('PONTIAC', 1, null, 1), ('PORSCHE', 6, null, null);