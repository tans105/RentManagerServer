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

insert into login values ('ADM0000000001','SSHHYDTEL0001','5f4dcc3b5aa765d61d8327deb882cf99','','',1,true);

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
last_name text,
dob date,
city character varying(30),
state character varying(30),
email character varying(100),
mobile character varying(20),
alt_mobile character varying(20),
primary key(user_id)
);

insert into personal_details values ('ADM0000000001','Tanmay','','Awasthi');


create table state_mst(
name text not null
);

INSERT INTO state_mst (name)
VALUES ('DAMAN DIU'),('GUJARAT'),('JAMMU AND KASHMIR') ,('MADHYA PRADESH'), ('WEST BENGAL'), ('UTTAR PRADESH'), ('ORISSA'), ('CHHATTISGARH'), ('HIMACHAL PRADESH'), ('DADRA NAGAR HAVELI'), ('CHANDIGARH'), ('JHARKHAND'), ('BIHAR'), ('ASSAM'), ('UTTARANCHAL'), ('GOA'), ('DELHI'), ('RAJASTHAN'), ('PONDICHERRY'), ('PUNJAB'), ('HARYANA'), ('TAMILNADU'), ('KARNATAKA'), ('KERALA'), ('ANDHRA PRADESH'), ('MAHARASHTRA'), ('MEGHALAYA'), ('TELANGANA');

create table idproof_mst(
name text not null
);

insert into idproof_mst (name) values
('Driving License'),('Aadhar Card'),('Pan Card');