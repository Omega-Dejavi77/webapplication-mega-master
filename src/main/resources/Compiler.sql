drop table Compilers if exists;
create table Compilers(
    compiler_ID varchar (36)constraint primary key,
    exercise_ID varchar (36) constraint foreign key references Exercises(exercise_ID),
    challenge_ID varchar (36) constraint foreign key references Challenges(Challenge_ID)
);