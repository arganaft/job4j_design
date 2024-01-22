
create view car_design
as
select c.id, c.car_name, b.bodie_name, e.engine_name, t.transmission_name
from cars c
left join car_bodies b on c.body_id = b.id
left join car_engines e on c.engine_id = e.id
left join car_transmissions t on c.transmission_id = t.id

select * from car_design
where transmission_name like '%Механическая';

select * from car_design
where transmission_name is null;
