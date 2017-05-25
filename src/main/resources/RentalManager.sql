--DB PATCH v1.0

create database rental_manager;


create table login(
user_id text not null,
hostel_id text not null,
password text not null default '5f4dcc3b5aa765d61d8327deb882cf99',
token text,
last_login_dtm timestamp without time zone default now(),
role_id integer not null,
active boolean default true
);


create table role_mst(
role_id integer not null,
role text not null,
role_abbr text not null
);


create table hostel_mst(
hostel_id integer not null,
hostel_name text not null,
registered_on timestamp without time zone not null default now(),
active boolean not null default 'true'
);


insert into role_mst values (1,'Admin','ADM');
insert into role_mst values (2,'Tenant','TEN');


--insert into users values('tanmayawasthi105@gmail.com','5f4dcc3b5aa765d61d8327deb882cf99',1,true);
--insert into users values('mojha2701@gmail.com','5f4dcc3b5aa765d61d8327deb882cf99',2,true);
