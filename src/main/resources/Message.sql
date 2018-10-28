drop table messages if exists;
create table messages(
    message_ID varchar (36) constraint primary key,
    content varchar (1024) constraint not null,
    user_sender varchar (64) constraint foreign key references Users(username),
    user_receiver varchar (64) constraint foreign key references Users(username)
)