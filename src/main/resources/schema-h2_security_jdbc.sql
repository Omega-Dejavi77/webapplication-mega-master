DROP TABLE IF EXISTS Users;
CREATE TABLE Users (
    username VARCHAR (64) PRIMARY KEY,
    password VARCHAR (32) NOT NULL,
    first_name VARCHAR (128) NOT NULL,
    last_name VARCHAR (128) NOT NULL,
    email VARCHAR (256) NOT NULL,
    birthday DATE,
    experience_points INTEGER,
    level INTEGER,
    enable BIT NOT NULL DEFAULT 1
);
DROP TABLE IF EXISTS Roles;
CREATE TABLE Roles(
    role VARCHAR (32) NOT NULL ,
    username VARCHAR (64) NOT NULL,
    FOREIGN KEY (username) REFERENCES Users(username)
);
