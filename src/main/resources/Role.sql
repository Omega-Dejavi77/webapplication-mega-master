drop table if EXISTS Roles;
create table Roles(
    role varchar (32) primary key ,
    user_name varchar (64) foreign key references Users(username)
);