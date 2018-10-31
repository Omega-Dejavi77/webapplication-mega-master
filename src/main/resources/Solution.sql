drop table if EXISTS Solutions ;
create table Solutions(
    solution_ID varchar (36) constraint primary key,
    order number,
    text varchar (36) constraint not null ,
    correct BIT,
    enable integer constraint not null,
    question_ID varchar (36) constraint foreign key references Questions(question_ID)
);