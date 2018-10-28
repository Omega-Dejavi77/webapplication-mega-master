drop table  Questions IF EXISTS;
CREATE table Questions (
    question_ID varchar (36)constraint primary key,
    text varchar (36),
    exercise_ID varchar (36) constraint foreign key references Exercises(exercise_ID),
    response_ID varchar (36) constraint foreign key references Response(response_ID)
);
