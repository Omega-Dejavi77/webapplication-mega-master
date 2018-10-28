drop table Challenges if exists ;
create table Challenges(
    challenge_ID varchar (36) primary key,
    post_ID varchar (36) foreign key references Posts(post_id),
    compiler_ID varchar (36) foreign key references Compilers(compiler_ID),
    jam_ID varchar (36) foreign key references Jams(jam_ID)
);