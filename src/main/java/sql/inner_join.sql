create table diplom(
    id serial primary key,
    seria int,
    number int
);
create table student(
    id serial primary key,
    name varchar(255),
    diplom_id int references diplom(id) unique
);

insert into diplom(seria, number) values (2222, 123456);
insert into diplom(seria, number) values (7777, 123456);
insert into diplom(seria, number) values (5555, 123456);

insert into student(name, diplom_id) values ('Alexey', 1);
insert into student(name, diplom_id) values ('Stepan', 2);
insert into student(name, diplom_id) values ('Inokentiy', 3);
insert into student(name) values ('Anton');
insert into student(name) values ('Fedya');

select pp.name, p.seria, p.number 
from student as pp 
join diplom as p 
on pp.diplom_id = p.id;

select zz.name, p.seria, p.number 
from student as zz 
join diplom as p 
on zz.diplom_id = p.id
where p.seria > 3333;

select d.seria, d.number, s.name
from diplom as d
join student as s
on d.id = s.diplom_id where d.seria != 5555;