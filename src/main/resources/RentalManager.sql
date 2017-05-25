--DB PATCH v1.0

create database rental_manager;


create table login(
user_id text not null,
hostel_id text not null,
password text not null default '5f4dcc3b5aa765d61d8327deb882cf99',
token text,
last_login_dtm timestamp without time zone default now(),
role_id integer not null,
active boolean default true,
primary key (user_id)
);


create table role_mst(
role_id integer not null,
role text not null,
role_abbr text not null,
primary key (role_id)
);


create table hostel_mst(
hostel_id text not null,
hostel_name text not null,
registered_on timestamp without time zone not null default now(),
active boolean not null default 'true'
primary key (hostel_id)
);


insert into role_mst values (1,'Admin','ADM');
insert into role_mst values (2,'Tenant','TEN');

insert into hostel_mst values ('SSHHYDTEL0001','Sri Sai Hostel',now(),true);
insert into hostel_mst values ('VMHHYDTEL0001','Vajra Mens Hostel',now(),true);


create table personal_details(
user_id text not null,
first_name text not null,
middle_name text,
last_name text
);

insert into personal_details values ('ADM0000000001','Tanmay','','Awasthi');
insert into personal_details values ('ADM0000000002','Manisha','','Ojha');
--insert into users values('tanmayawasthi105@gmail.com','5f4dcc3b5aa765d61d8327deb882cf99',1,true);
--insert into users values('mojha2701@gmail.com','5f4dcc3b5aa765d61d8327deb882cf99',2,true);
