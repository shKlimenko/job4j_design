insert into role(name) values('User');
insert into role(name) values('Admin');

insert into users(name, role_id) values('Ivan', 1);
insert into users(name, role_id) values('Mattway', 2);

insert into rules(name) values('Can add users');
insert into rules(name) values('Can delete users');
insert into rules(name) values('Can add items');
insert into rules(name) values('Can delete items');

insert into role_rules(role_id, rules_id) values(1,3);
insert into role_rules(role_id, rules_id) values(2,1);
insert into role_rules(role_id, rules_id) values(2,2);
insert into role_rules(role_id, rules_id) values(2,3);
insert into role_rules(role_id, rules_id) values(2,4);

insert into category(name) values('Emergency');
insert into category(name) values('Regular');
insert into category(name) values('Feature');

insert into state(complete) values(true);
insert into state(complete) values(false);

insert into item(name, users_id, category_id, state_id) values('Server is down', 2, 1, 1);
insert into item(name, users_id, category_id, state_id) values('Create customer search', 1, 3, 2);

insert into attachs(name, item_id) values('File1', 1);
insert into attachs(name, item_id) values('File2', 1);
insert into attachs(name, item_id) values('File3', 2);
insert into attachs(name, item_id) values('File4', 2);

insert into comments(name, item_id) values('The server must be working by the end of the day', 1);
insert into comments(name, item_id) values('Search must be available to all customers', 2);
