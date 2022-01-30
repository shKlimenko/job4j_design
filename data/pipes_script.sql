create table pipes (
	id serial primary key,
	name varchar(255),
	isPlastic boolean,
	color Integer
);
insert into pipes (name, isPlastic, color) values('варенец', true, 754420);
select * from pipes;
update pipes set name = 'Канализационная';
select * from pipes;
delete from pipes;
select * from pipes;
