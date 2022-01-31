create table patients(
     id serial primary key,
     name varchar(255)
 );
 
 create table doctors(
     id serial primary key,
     name varchar(255)
 );
 
 create table patients_doctors(
     id serial primary key,
     patient_id int references patients(id),
     doctor_id int references doctors(id)
 );
 
insert into patients(name) values ('Ivan');
insert into patients(name) values ('Kirill');
insert into patients(name) values ('Roman');

insert into doctors(name) values ('Surgeon');
insert into doctors(name) values ('Therapist');
insert into doctors(name) values ('Otolaryngologist');

insert into patients_doctors(patient_id, doctor_id) values (1, 1);
insert into patients_doctors(patient_id, doctor_id) values (1, 2);
insert into patients_doctors(patient_id, doctor_id) values (1, 3);
insert into patients_doctors(patient_id, doctor_id) values (2, 1);
insert into patients_doctors(patient_id, doctor_id) values (2, 2);
insert into patients_doctors(patient_id, doctor_id) values (3, 3);