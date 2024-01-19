select * from employees e
left join departments d on e.department_id = d.id;
select * from employees e
right join departments d on e.department_id = d.id;
select * from employees e
full join departments d on e.department_id = d.id;
select * from employees e
cross join departments d;

select * from departments d
left join employees e on e.department_id = d.id
where e.department_id is null;

select * from employees e
right join departments d on e.department_id = d.id;
select e.id, e.department_id, e.employee_name, d.id, d.department_name from departments d
left join employees e on d.id = e.department_id;

select (female.teen_name || ' + ' || male.teen_name) as "разнополые пары"
 from (select teen_name from teens where gender like '%female') as female
 cross join (select teen_name from teens where gender not like '%female') as male;

