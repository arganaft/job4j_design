
select car.car_name as "Техническое название модели авто", motor.engine_capacity  as "Обьем двигателя", motor.engine_name Название
from car car join engine motor on car.engine_id = motor.id;

select car.car_name as "Прожорливые Авто", motor.engine_capacity  as "Обьем двигателя", motor.engine_name Название
from car car join engine motor on car.engine_id = motor.id and motor.engine_capacity > 12;

select car.car_name as "Модели - mini", motor.engine_capacity  as "Обьем двигателя", motor.engine_name Название
from car car join engine motor on car.engine_id = motor.id where car.car_name like '%mini%';