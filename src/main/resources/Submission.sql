drop table if exists Submissions;
create table Submissions(
    mark float constraint not null,
    username varchar (36) foreign key references Users(username),
    exercise varchar (36) foreign key references Exercises(exercise_ID)
);