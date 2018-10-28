drop table Exercises if exists;
create table Exercises(
  exercise_ID varchar (36) constraint primary key,
  description varchar (1024) constraint not null,
  enable integer constraint not null,
  dificullty integer constraint not null,
  experience_points integer
);