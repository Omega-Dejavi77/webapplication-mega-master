drop table  Questions IF EXISTS;
CREATE table Questions (
    question_ID varchar (36)constraint primary key,
    text varchar (36) constraint not null,
    exercise_ID varchar (36) constraint foreign key references Exercises(exercise_ID),
    solution_ID varchar (36) constraint foreign key references Solutions(solution_ID)
);
