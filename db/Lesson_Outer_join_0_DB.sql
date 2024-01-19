
create table departments
(
    id    serial primary key,
    department_name  text
);

create table employees
(
    id    serial primary key,
    department_id int references departments(id),
    employee_name  text
);

create table teens
(
    id    serial primary key,
    teen_name  text,
    gender  text
);

insert into departments(department_name)
 values ('разработка'), ('транспорт'), ('развитие'), ('иследования'), ('производство'), ('реклама');

insert into employees(department_id, employee_name)
 values (1, 'Вася'), (1, 'Дима'), (1, 'Оля'), (1, 'Даня');
insert into employees(department_id, employee_name)
 values (2, 'Олег'), (2, 'Саша'), (2, 'Дэн'), (2, 'Миша');
insert into employees(department_id, employee_name)
 values (null, 'Тим'), (null, 'Витя'), (null, 'Антон'), (null, 'Никита');
 insert into employees(department_id, employee_name)
 values (4, 'Лена'), (4, 'Оля'), (4, 'Алина'), (4, 'Дима');
 insert into employees(department_id, employee_name)
 values (5, 'Лина'), (5, 'Сева'), (5, 'Альбина'), (5, 'Леша');

insert  into teens (teen_name, gender)
    values ('Лена', 'female'), ('Коля', 'male'), ('Дима', 'male'), ('Лика', 'female'),
           ('Женя', 'male'), ('Ира', 'female'), ('Вика', 'female'), ('Катя', 'female'),