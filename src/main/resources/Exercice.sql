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