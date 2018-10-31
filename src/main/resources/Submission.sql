drop table Submission if exists;
create table Submission(
    mark float constraint not null,
    username varchar (36) constraint foreign key references Users(username),
    exercise varchar (36)) constraint foreign key references Exercises(exercise_ID)
);