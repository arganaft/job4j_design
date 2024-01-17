select t.type_name as тип, p.product_name
from product p
join type t on p.type_id = t.id
where p.type_id = 1;

select product_name as наименование
from product
where product_name like '%мороженное%';

select product_name as "Испорченные продукты", expired_date as "Дата истечения срока"
from product
where expired_date < current_date;

select product_name as "самые дорогие продукты", price as цена
from product
where price = (select max(price) from product);

select t.type_name тип, (select count(p.product_name) from product p where p.type_id = t.id) as количество
from type t;

select t.type_name тип, p.product_name продукт
from type t
join product p on p.type_id = t.id
where t.type_name like '%сыр' or t.type_name like '%молоко';

select t.type_name тип, (select count(p.product_name) from product p where p.type_id = t.id) as количество
from type t
where (select count(p.product_name) from product p where p.type_id = t.id) < 10;

select t.type_name тип, p.product_name продукт
from type t
join product p on p.type_id = t.id;