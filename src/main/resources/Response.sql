drop table Responses if EXISTS;
create table Responses(
    response_ID varchar (36) constraint primary key,
    order number,
    text varchar (36) constraint not null ,
    correct BIT,
    enable integer constraint not null,
    question_ID varchar (36) constraint foreign key references Questions(question_ID)
);