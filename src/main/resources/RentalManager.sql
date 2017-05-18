create database rental_manager;

create table users(
email text not null unique,
password text not null default ,
token text
first_name text not null,
middle_name text,
last_name text,
mobile_number text not null unique,
role_id integer not null,
active boolean not null,
PRIMARY KEY (email)  
);

insert into users values('tanmayawasthi105@gmail.com','5f4dcc3b5aa765d61d8327deb882cf99',1,true);
insert into users values('mojha2701@gmail.com','5f4dcc3b5aa765d61d8327deb882cf99',2,true);

create table role_mst(
role_id integer not null,
role text not null
);

insert into role_mst values (1,'Admin');
insert into role_mst values (2,'Tenant');

