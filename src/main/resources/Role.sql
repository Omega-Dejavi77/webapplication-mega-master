drop table Roles if EXISTS;
create table Roles(
    role varchar (32) constraint primary key ,
    user_name varchar (64) constraint foreign key references Users(username)
);