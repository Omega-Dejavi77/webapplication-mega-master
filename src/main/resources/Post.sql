drop table Posts IF EXISTS ;
CREATE TABLE Posts (
    post_ID varchar(36) constraint PRIMARY KEY,
    Name varchar(64) CONSTRAINT NOT NULL,
    description varchar (1024),
    creationDay DATE,
    like INTEGER,
    enable integer
);
