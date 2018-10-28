drop table Comments if EXISTS;
create table Comments(
    comment_id varchar (36) constraint primary key,
    comment varchar (2048),
    creation_day DATE,
    like integer,
    best BIT,
    enable BIT
);