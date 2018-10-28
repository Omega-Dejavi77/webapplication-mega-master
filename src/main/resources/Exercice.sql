drop table Exercises if exists;
create table Exercises(
  exercise_ID varchar (36) constraint primary key,
  description varchar (1024),
  enable integer,
  dificullty integer,
  experience_points integer
);