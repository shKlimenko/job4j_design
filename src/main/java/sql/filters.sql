create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
	type_id int references type(id),
	expired_date date,
    price float
);

insert into type (name) values ('СЫР'), ('МОЛОКО'), ('КОЛБАСА'), ('ХЛЕБ'), ('МОРОЖЕНОЕ');

insert into product (name, type_id, expired_date, price) 
	values ('Хлеб Бородинский', 4, '2022-02-15', 45.50);
insert into product (name, type_id, expired_date, price) 
	values ('Хлеб Пшеничный ВС', 4, '2022-02-12', 29.0);
insert into product (name, type_id, expired_date, price) 
	values ('Хлеб Пшеничный 1С', 4, '2022-02-15', 26.0);
insert into product (name, type_id, expired_date, price) 
	values ('Молоко 2,5%', 2, '2022-02-14', 38.70);
insert into product (name, type_id, expired_date, price) 
	values ('Молоко 3,5%', 2, '2022-02-05', 42.20);
insert into product (name, type_id, expired_date, price) 
	values ('Сыр Пошехонский', 1, '2022-03-20', 470.0);
insert into product (name, type_id, expired_date, price) 
	values ('Сыр Сметанковый', 1, '2022-03-22', 550.0);
insert into product (name, type_id, expired_date, price) 
	values ('Сыр Плавленный', 1, '2022-03-12', 54.0);
insert into product (name, type_id, expired_date, price) 
	values ('Колбаса Финская', 3, '2022-04-10', 870.0);	
insert into product (name, type_id, expired_date, price) 
	values ('Сыр Плавленный', 1, '2022-03-12', 54.0);
insert into product (name, type_id, expired_date, price) 
	values ('Мороженое в стаканчике', 5, '2022-09-30', 25.0);
insert into product (name, type_id, expired_date, price) 
	values ('Мороженое Большой ПАПА', 5, '2022-08-20', 79.90);


/* 1 */
select * from product as p
inner join type as t
on p.type_id = t.id
where t.name = 'СЫР';

/* 2 */
select * from product 
where name ilike '%мороженое%';

/* 3 */
select * from product 
where expired_date < current_date;

/* 4 */
select *
from product
where price = (select max(price) from product);

/* 5 */
select t.name, count(p.type_id)
from type as t
inner join product as p
on t.id = p.type_id
group by t.name;

/* 6 */
select * 
from product as p
inner join type as t
on p.type_id = t.id
where t.name = 'СЫР' or t.name = 'МОЛОКО';

/* 7 */
select t.name, count(p.type_id)
from type as t
inner join product as p
on t.id = p.type_id
group by t.name
having count(p.type_id) < 10;

/* 8 */
select p.name as "Наименование", t.name as "Тип"
from product as p
inner join type as t
on p.type_id = t.id;