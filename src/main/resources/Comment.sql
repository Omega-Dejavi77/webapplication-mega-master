drop table Comments if EXISTS;
create table Comments(
    comment_id varchar (36) constraint primary key,
    comment varchar (2048) constraint not null,
    creation_day DATE collation_schema not null,
    like integer,
    best BIT,
    enable BIT constraint not NULL ,
    post_ID varchar (36) constraint foreign key references Posts(post_ID)
);