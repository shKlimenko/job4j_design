create table departments (

	id serial primary key,

    	name varchar(255)

);



create table emploers (

	id serial primary key,

    	name varchar(255),

	department_id int references departments(id)

);


insert into departments (name) values ('������������'), ('�����������'), ('����� ������'), ('�������');

insert into emploers (name, department_id) values ('���������� �.�.', 1);
insert into emploers (name, department_id) values ('������� �.�.', 2);
insert into emploers (name, department_id) values ('�������� �.�.', 3);
insert into emploers (name, department_id) values ('�������� �.�.', null);
insert into emploers (name, department_id) values ('����� �.�.', null);
insert into emploers (name, department_id) values ('���������� �.�.', 1);


/* 1 */
select * 
from departments d
left join emploers  e
on d.id = e.department_id;

select * 
from departments d
right join emploers  e
on d.id = e.department_id;

select * 
from departments d
full join emploers  e
on d.id = e.department_id;

select * 
from departments d
cross join emploers  e;

/* 2 */
select * 
from departments d
left join emploers  e
on d.id = e.department_id
where e.id is null;

/* 4 */
select * 
from emploers e 
left join departments d  
on  e.department_id = d.id;

select * 
from departments d 
right join emploers e 
on d.id = e.department_id;

/* 5 */
create table teens(
	id serial primary key,
	name varchar(255),
	gender char
);

insert into teens (name, gender) 
	values ('������', 'f'), ('�����', 'f'), ('�������', 'm'), ('������', 'm'), ('������', 'm');

select t1.name as Person1, t2.name as Person2
from (select * from teens where gender = 'm') as t1
cross join (select * from teens where gender = 'f') as t2;