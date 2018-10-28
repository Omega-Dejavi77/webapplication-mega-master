DROP TABLE TUTORIALS IF EXISTS ;
CREATE TABLE TUTORIALS (
      Tutorial_ID varchar (36) constraint PRIMARY KEY,
      post_ID varchar (36)constraint FOREIGN KEY REFERENCES Posts(post_ID),
      exercice_ID varchar (36) constraint foreign key references Exercises(exercise_ID)
);