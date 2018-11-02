drop table if EXISTS Comments;
create table Comments(
    comment_id varchar (36) primary key,
    comment varchar (2048) not null,
    creation_day DATE collation_schema not null,
    like integer,
    best BIT,
    enable BIT  not NULL ,
    post_ID varchar (36) foreign key references Posts(post_ID)
);