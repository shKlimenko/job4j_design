create table carcase (
	id serial primary key,
   	name varchar(255)
);

create table engine (
	id serial primary key,
   	name varchar(255)
);

create table transmission (
	id serial primary key,
   	name varchar(255)
);

create table car (
	id serial primary key,
   	name varchar(255),
	carcase_id int references carcase(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);

insert into carcase (name) values ('Hatchback'), ('Station wagon'), ('Sedan'), ('Pickup');
insert into engine (name) values ('Engine 1.0l TURBO'), ('Engine 1.6l ATMO'), ('Engine 2.0l ATMO'), ('Engine 8.0l TURBO SUPER POWER 16v BOOST');
insert into transmission (name) values ('Manual transmission'), ('Automatic transmission'), ('Robotic gearbox');

insert into car (name, carcase_id, engine_id, transmission_id) 
	values ('BMW', 1, 2, 2), ('Mercedes', 3, 1, 1), ('KIA', 2, 3, 2), ('Huindai', 1, 3, 2), ('Lada', 1, 1, 1), ('Vortex', 2, 2, 1);

/* 2.1 */
select car.id, car.name "Car", c.name "CarCase", e.name "Engine", t.name "Transmission"
from car
left join carcase as c on car.carcase_id = c.id
left join engine as e on car.engine_id = e.id
left join transmission as t on car.transmission_id = t.id;

/* 2.2 */
select carcase.id, carcase.name
from carcase
left join car on car.carcase_id = carcase.id
where car.name is null;

select engine.id, engine.name
from engine
left join car on car.engine_id = engine.id
where car.name is null;

select transmission.id, transmission.name
from transmission
left join car on car.transmission_id = transmission.id
where car.name is null;
