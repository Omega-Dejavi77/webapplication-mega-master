drop table IF EXISTS Posts;
CREATE TABLE Posts (
    post_ID varchar(36) PRIMARY KEY,
    Name varchar(64)  NOT NULL,
    description varchar (1024) not null,
    creationDay DATE not null,
    likes INTEGER,
    enable integer not NULL,
    son_TYPE varchar (36),
    startingDate date,
    deadline date,
    post_ID_ref varchar (36) foreign key references Posts(post_ID)
);

drop table if exists Exercises;
create table Exercises(
  exercise_ID varchar (36)primary key,
  description varchar (1024) not null,
  enable integer not null,
  dificullty integer not null,
  experience_points integer,
  son_TYPE varchar (36),
  post_ID varchar (36)  foreign key references Post(post_ID)
);
drop table IF EXISTS  Questions;
CREATE table Questions (
    question_ID varchar (36) primary key,
    text varchar (36) not null,
    exercise_ID varchar (36) foreign key references Exercises(exercise_ID),
    solution_ID varchar (36) foreign key references Solutions(solution_ID)
);
drop table if EXISTS Solutions ;
create table Solutions(
    solution_ID varchar (36) primary key,
    order number,
    text varchar (36 not null ,
    correct BIT,
    enable integer not null,
    question_ID varchar (36) foreign key references Questions(question_ID)
);
drop table if EXISTS Comments;
create table Comments(
    comment_id varchar (36) primary key,
    comment varchar (2048) not null,
    creation_day DATE collation_schema not null,
    like integer,
    best BIT,
    enable BIT  not NULL ,
    post_ID varchar (36) foreign key references Posts(post_ID)
);

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
drop table if EXISTS Roles;
create table Roles(
    role varchar (32) primary key ,
    user_name varchar (64) foreign key references Users(username)
);
drop table if exists Category;
create table Category(
    category varchar (32) primary key,
);
drop table if exists messages;
create table messages(
    message_ID varchar (36)primary key,
    content varchar (1024) not null,
    user_sender varchar (64) foreign key references Users(username),
    user_receiver varchar (64) foreign key references Users(username)
);
drop table if exists Submissions;
create table Submissions(
    mark float constraint not null,
    username varchar (36) foreign key references Users(username),
    exercise varchar (36) foreign key references Exercises(exercise_ID)
);