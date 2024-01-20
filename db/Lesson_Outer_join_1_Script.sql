
select c.id, c.car_name, b.bodie_name, e.engine_name, t.transmission_name
from cars c
left join car_bodies b on c.body_id = b.id
left join car_engines e on c.engine_id = e.id
left join car_transmissions t on c.transmission_id = t.id;

select b.bodie_name from car_bodies b
left join cars c on c.body_id = b.id
where c.body_id is null;

select e.engine_name from car_engines e
left join cars c on c.engine_id = e.id
where c.engine_id is null;

select t.transmission_name from car_transmissions t
left join cars c on c.transmission_id = t.id
where c.transmission_id is null;
