drop table if exists messages;
create table messages(
    message_ID varchar (36)primary key,
    content varchar (1024) not null,
    user_sender varchar (64) foreign key references Users(username),
    user_receiver varchar (64) foreign key references Users(username)
);