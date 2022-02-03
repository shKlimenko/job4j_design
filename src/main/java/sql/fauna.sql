create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);
insert into fauna(name, avg_age, discovery_date)
	values('Sea fish', '500', '1925-05-15');
insert into fauna(name, avg_age)
	values('Sea horse', '50000');
insert into fauna(name, avg_age, discovery_date)
	values('Ugulava Diega', '15000', '1935-05-15');
insert into fauna(name, avg_age, discovery_date)
	values('Some fish', '17000', '1985-05-15');
insert into fauna(name, avg_age)
	values('UNAGI', '20000000');
insert into fauna(name, avg_age, discovery_date)
	values('Skate', '100', '1000-05-15');
insert into fauna(name, avg_age, discovery_date)
	values('Salsa', '50', '2020-05-15');
insert into fauna(name, avg_age, discovery_date)
	values('Fish with legs', '19000', '1235-05-15');

select * from fauna where name like '%fish%'
select * from fauna where avg_age > 10000 AND avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '1950-01-01';