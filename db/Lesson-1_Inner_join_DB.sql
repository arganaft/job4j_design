
create table engine(
    id serial primary key,
    engine_capacity int,
    engine_name text
)

create table car(
    id serial primary key,
    car_name text,
    engine_id int references engine(id)
)

insert into engine(engine_capacity, engine_name)
    values (7, 'V7');
insert into engine(engine_capacity, engine_name)
    values (12, 'V12');
insert into engine(engine_capacity, engine_name)
    values (17, 'V17');
insert into engine(engine_capacity, engine_name)
    values (23, 'V23');
insert into engine(engine_capacity, engine_name)
    values (34, 'V34');

insert into car(car_name, engine_id)
    values ('carV7', 1);
insert into car(car_name, engine_id)
    values ('carV12', 2);
insert into car(car_name, engine_id)
    values ('carV17', 3);
insert into car(car_name, engine_id)
    values ('carV17mini', 3);
insert into car(car_name, engine_id)
    values ('carV23', 4);
insert into car(car_name, engine_id)
    values ('carV34', 5);
insert into car(car_name, engine_id)
    values ('carV34mini', 5);
insert into car(car_name)
    values ('carNew');
insert into car(car_name)
    values ('NotCar');