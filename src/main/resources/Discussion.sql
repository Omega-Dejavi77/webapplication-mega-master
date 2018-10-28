drop table Discussions if EXISTS;
create table Discussions(
    discussion_ID varchar (36) constraint primary key,
    post_ID varchar (36) constraint foreign key references Posts(post_ID),
    comment_ID varchar (36) constraint foreign key references Comments(comment_ID)
);