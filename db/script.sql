create table leads(
    id serial primary key,
    customer_name text,
	order_date date,
    order_time time,
	IP_host_address inet
);

insert into leads
(customer_name,
 order_date,
 order_time,
 ip_host_address)
values
('Катя',
 '17.09.2023',
 '16:05:39',
 '255.128.128.7/24');

select * from leads;

update leads set customer_name = 'Екатерина';

select * from leads;

delete from leads;

select * from leads;