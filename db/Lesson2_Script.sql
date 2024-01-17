
select avg(price) from devices;

select p.people_name as "Имя", avg(d.price) as "Средняя стоимость"
from devices_people dp
join people p on dp.people_id = p."id"
join devices d on dp.device_id = d."id"
group by p.people_name
order by p.people_name asc

select p.people_name as "Имя", avg(d.price) as "Средняя стоимость больше 5000"
from devices_people dp
join people p on dp.people_id = p."id"
join devices d on dp.device_id = d."id"
group by p.people_name
having avg(d.price) > 5000
order by p.people_name asc


