create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices (name, price) values ('Sumsung', 20000.0), ('Nokia', 7500.0), ('Apple', 100500.0), ('Xiaomi', 70000);;
insert into people (name) values ('Youriy'), ('Zvezdoslav'), ('Avanesyan');
insert into devices_people (device_id, people_id) values (1, 1), (2, 2), (3, 3), (1, 3), (4, 1);

select avg(price) from devices;

select p.name, avg(dev.price) 
from people as p 
inner join devices_people as dp on p.id= dp.people_id 
inner join devices as dev on dev.id = dp.device_id
group by p.name;

select p.name, avg(dev.price) 
from people as p 
inner join devices_people as dp on p.id= dp.people_id 
inner join devices as dev on dev.id = dp.device_id
group by p.name
having avg(dev.price) > 10000