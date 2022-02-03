create table diplom(
    id serial primary key,
    seria int,
    number int
);

create table people(
    id serial primary key,
    name varchar(255),
    diplom_id int references diplom(id) unique
);