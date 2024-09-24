create table v1_users(
	id varchar(127) primary key,
    first_name varchar(255) not null,
    middle_name varchar(255),
    last_name varchar(255) not null,
    birth_date date not null,
    ssn varchar(9) not null,
    email varchar(255) not null,
    phone_number varchar(10) not null,
    gender varchar(255) not null,
	created_at datetime not null,
    update_at datetime
);

create table v1_customers(
	id varchar(127) primary key,
    address varchar(255) not null,
    user_id varchar(127) not null,
	created_at datetime not null,
    updated_at datetime,
    foreign key (user_id) references v1_users(id)
);

create table v1_doctors(
	id varchar(127) primary key,
    license_number varchar(7) not null unique,
    user_id varchar(127) not null,
	created_at datetime not null,
    updated_at datetime,
    foreign key (user_id) references v1_users(id)
);

create table v1_appointments(
	id varchar(127) primary key,
    order_number bigint not null,
    doctor_id varchar(127) not null,
    customer_id varchar(127) not null,
    booked_date datetime not null,
	scheduled_at datetime not null,
    canceledAt datetime,
    finishedAt datetime,
    foreign key (doctor_id) references v1_doctors(id),
    foreign key (customer_id) references v1_customers(id)
);