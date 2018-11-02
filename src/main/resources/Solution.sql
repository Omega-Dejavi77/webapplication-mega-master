drop table if EXISTS Solutions ;
create table Solutions(
    solution_ID varchar (36) primary key,
    order number,
    text varchar (36 not null ,
    correct BIT,
    enable integer not null,
    question_ID varchar (36) foreign key references Questions(question_ID)
);