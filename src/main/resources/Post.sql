drop table IF EXISTS Posts;
CREATE TABLE Posts (
    post_ID varchar(36) constraint PRIMARY KEY,
    Name varchar(64) CONSTRAINT NOT NULL,
    description varchar (1024) constraint not null,
    creationDay DATE constraint not null,
    like INTEGER,
    enable integer constraint not NULL,
    son_TYPE varchar (36),
    startingDate date,
    deadline date,
    post_ID_ref varchar (36) constraint foreign key references Posts(post_ID)

);
