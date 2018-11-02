DROP TABLE if EXISTS users;
CREATE TABLE users
(
  nickname VARCHAR(55) PRIMARY KEY,
  password VARCHAR(55),
  email VARCHAR(55)
);