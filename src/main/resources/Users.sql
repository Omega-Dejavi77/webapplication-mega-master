drop table if EXISTS Users;
create TABLE Users (
    username varchar (64) constraint primary key,
    password varchar (32) constraint not null,
    firs_name varchar (128) constraint not null,
    last_name varchar (128) constraint not null,
    email varchar (256) constraint not null,
    birthday DATE,
    experience_points integer,
    level integer,
    enable BIT constraint not null
);