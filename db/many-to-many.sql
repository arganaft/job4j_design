create table restaurants(
    id serial primary key,
    restaurant varchar(255)
);

create table providers(
    id serial primary key,
    provider varchar(255)
);

create table supply_agreements(
    id serial primary key,
    restaurant_id int references restaurants(id),
    provider_id int references providers(id)
);