create table INN(
    id serial primary key,
    INN_number char(10)
);

create table companys(
    id serial primary key,
    company varchar(255),
    INN_id int references INN(id) unique
);