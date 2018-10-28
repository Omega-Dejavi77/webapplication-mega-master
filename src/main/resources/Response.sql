drop table Responses if EXISTS;
create table Responses(
    response_ID varchar (36) constraint primary key,
    order number,
    text varchar (36),
    correct BIT,
    enable integer,
    question_ID varchar (36) constraint foreign key references Questions(question_ID)
);