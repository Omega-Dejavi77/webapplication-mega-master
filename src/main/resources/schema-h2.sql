drop table IF EXISTS Posts;
CREATE TABLE Posts (
    post_id varchar(36) PRIMARY KEY,
    title varchar(64)  NOT NULL,
    description varchar (1024) not null,
    creationDay DATE not null,
    likes INTEGER,
    enable BIT not NULL,
    son_type varchar (36),
    starting_date date,
    deadline date,
    post_id_ref varchar (36),
    foreign key (post_id_ref) references Posts(post_id)
);

drop table if exists Exercises;
create table Exercises(
  exercise_id varchar (36)primary key,
  description varchar (1024) not null,
  enable integer not null,
  difficulty integer not null,
  experience_points integer,
  son_type varchar (36),
  post_id varchar (36),
  foreign key (post_id) references Posts(post_ID)
);
drop table IF EXISTS  Questions;
CREATE table Questions (
    question_id varchar (36) primary key,
    texts varchar (1024) not null,
    exercise_id varchar (36),
  foreign key (exercise_id) references Exercises(exercise_id)
);
drop table if EXISTS Solutions ;
create table Solutions(
    solution_id varchar (36) primary key,
    position number,
    texts varchar (1024) not null ,
    correct BIT,
    enable integer not null,
    question_id varchar (36),
  foreign key (question_id) references Questions(question_id)
);
drop table if EXISTS Comments;
create table Comments(
    comment_id varchar (36) primary key,
    comment varchar (2048) not null,
    creation_day DATE not null,
    likes integer,
    best BIT,
    enable BIT  not NULL ,
    post_id varchar (36),
    foreign key (post_id) references Posts(post_id)
);

drop table if EXISTS Users;
create TABLE Users (
    username varchar (64) primary key,
    password varchar (32) not null,
    first_name varchar (128) not null,
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
    username varchar (64),
    foreign key (username) references Users(username)
);
drop table if exists Category;
create table Category(
    category varchar (32) primary key,
);
drop table if exists messages;
create table messages(
    message_id varchar (36)primary key,
    content varchar (1024) not null,
    user_sender varchar (64),
    user_receiver varchar (64),
  foreign key (user_sender) references Users(username),
  foreign key (user_receiver) references Users(username)
);
drop table if exists Submissions;
create table Submissions(
    mark float not null,
    username varchar (36),
    exercise varchar (36),
  foreign key (username) references Users(username),
  foreign key (exercise) references Exercises(exercise_ID)
);