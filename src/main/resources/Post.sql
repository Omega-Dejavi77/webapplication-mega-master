drop table IF EXISTS Posts;
CREATE TABLE Posts (
    post_ID varchar(36) PRIMARY KEY,
    Name varchar(64)  NOT NULL,
    description varchar (1024) not null,
    creationDay DATE not null,
    likes INTEGER,
    enable integer not NULL,
    son_TYPE varchar (36),
    startingDate date,
    deadline date,
    post_ID_ref varchar (36) foreign key references Posts(post_ID)
);
