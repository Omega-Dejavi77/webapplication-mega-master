drop table if EXISTS Users;
create TABLE Users (
    username varchar (64) primary key,
    password varchar (32) not null,
    firs_name varchar (128) not null,
    last_name varchar (128) not null,
    email varchar (256) not null,
    birthday DATE,
    experience_points integer,
    level integer,
    enable BIT not null
);