
create table author(
    id serial primary key,
    name varchar(255)
);

create table book(
    id serial primary key,
    name varchar(255),
    author_id int references author(id)
);

insert into author(name) values ('Pushkin');
insert into book(name, author_id) VALUES ('Skazka', 1);

select * from book;

select * from author where id in (select id from book);

