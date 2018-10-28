drop table Jams if EXISTS;
create table Jams(
    jam_ID varchar (36) constraint primary key,
    startingDate DATE,
    Deadline DATE,
    challenge_ID varchar (36) constraint foreign key references Challenges(challenge_ID)
);