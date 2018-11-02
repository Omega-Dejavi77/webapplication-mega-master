drop table IF EXISTS  Questions;
CREATE table Questions (
    question_ID varchar (36) primary key,
    text varchar (36) not null,
    exercise_ID varchar (36) foreign key references Exercises(exercise_ID),
    solution_ID varchar (36) foreign key references Solutions(solution_ID)
);
