create table railway_stations(
    id serial primary key,
    railway_station varchar(255)
);

create table trains(
    id serial primary key,
    train_number integer,
    railway_station_id int references railway_stations(id)
);