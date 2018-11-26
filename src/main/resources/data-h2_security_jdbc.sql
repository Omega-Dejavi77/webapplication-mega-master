INSERT INTO Users(username, password, first_name, last_name, email, birthday, experience_points, level, enable)
VALUES('javi', '{noop}javi', 'Javier', 'de la Serna Martinez','jdelaserna@edu.tecnocampus.cat', '1996-11-18', 0, 0, TRUE);

INSERT INTO Users(username, password, first_name, last_name, email, birthday, experience_points, level, enable)
VALUES('erik', '{noop}erik', 'Erik', 'Espunes', 'eespunes@edu.tecnocampus.cat', '1996-11-18', 0, 0, TRUE);

INSERT INTO Users(username, password, first_name, last_name, email, birthday, experience_points, level, enable)
VALUES('jose', '{noop}jose', 'Jose', 'Oviedo Fernandez', 'joviedo@edu.tecnocampus.cat', '1998-08-18', 0, 0, TRUE);

INSERT INTO Users(username, password, first_name, last_name, email, birthday, experience_points, level, enable)
VALUES('sofian', '{noop}sofian', 'Sofian', 'Ben Ayata', 'sbenayata@edu.tecnocampus.cat', '1997-11-05', 0, 0, TRUE);

INSERT INTO Roles(role, username) VALUES('ROLE_ADMIN', 'javi');
INSERT INTO Roles(role, username) VALUES('ROLE_ADMIN', 'erik');
INSERT INTO Roles(role, username) VALUES('ROLE_ADMIN', 'jose');
INSERT INTO Roles(role, username) VALUES('ROLE_ADMIN', 'sofian');

INSERT INTO Category(category) VALUES('Java');
INSERT INTO Category(category) VALUES('C++');
INSERT INTO Category(category) VALUES('Python');
INSERT INTO Category(category) VALUES('C#');
INSERT INTO Category(category) VALUES('SQL');

INSERT INTO Posts  (post_id, title, description, creationDay, likes, enable, son_type, category) VALUES('01293c0f-bf77-42d7-9fa3-8b101f33ee5b','Tutorial','Tutorial Description',current_timestamp,0,1,'Tutorial','Java');
INSERT INTO Posts  (post_id, title, description, creationDay, likes, enable, son_type, category) VALUES ('01293c0f-bf77-42d7-9fa3-8b101f33ee6b','Challenge','Challenge Description',current_timestamp,0,1,'Tutorial','C++');
INSERT INTO Posts  (post_id, title, description, creationDay, likes, enable, son_type) VALUES ('MS50H2YIPu','Get familiar with online judge system','0',current_timestamp,0,1,'Challenge');
INSERT INTO Posts  (post_id, title, description, creationDay, likes, enable, son_type) VALUES ('V4kzsJCS9j','Print Input','0',current_timestamp,0,1,'Challenge');
INSERT INTO Posts  (post_id, title, description, creationDay, likes, enable, son_type) VALUES ('rwKwjNoJXb','Down with the comments!','1',current_timestamp,0,1,'Challenge');
INSERT INTO Posts  (post_id, title, description, creationDay, likes, enable, son_type) VALUES ('E6rsE0iITV','Lexicographical order','1',current_timestamp,0,1,'Challenge');
INSERT INTO Posts  (post_id, title, description, creationDay, likes, enable, son_type) VALUES ('GfLgED9u7T','Matrix transposition','4',current_timestamp,0,1,'Challenge');
INSERT INTO Posts (post_id, title, description, creationDay, likes, enable, hasBest,son_type,username) VALUES ('a', 'C++', 'Help for c++', current_timestamp ,0,1,0,'Discussion','erik');
INSERT INTO Posts (post_id, title, description, creationDay, likes, enable, hasBest,son_type,username) VALUES ('b', 'Java', 'Help for Java', current_timestamp ,0,1,0,'Discussion','erik');

INSERT INTO Comments (comment_id, best, comment, creation_day, likes, enable, post_id,username) VALUES (1, 0, '1Search it on the Internet', current_timestamp , 0,1,'a','erik');
INSERT INTO Comments (comment_id, best, comment, creation_day, likes, enable, post_id,username) VALUES (2, 0, '2Search it on the Internet', current_timestamp , 0,1,'a','erik');
INSERT INTO Comments (comment_id, best, comment, creation_day, likes, enable, post_id,username) VALUES (3, 0, '3Search it on the Internet', current_timestamp , 0,1,'b','erik');
INSERT INTO Comments (comment_id, best, comment, creation_day, likes, enable, post_id,username) VALUES (4, 0, '4Search it on the Internet', current_timestamp , 0,1,'b','erik');


INSERT INTO Exercises VALUES ('1', 'Test excercise for Tutorial', 1, 1,0,0,'Test','01293c0f-bf77-42d7-9fa3-8b101f33ee5b');
INSERT INTO Exercises VALUES ('2', 'Fill the gap excercise for Tutorial', 1, 1,0,0,'Fill','01293c0f-bf77-42d7-9fa3-8b101f33ee5b');
INSERT INTO Exercises VALUES ('3', 'Test excercise for Challenge', 1, 1,0,0,'Test','01293c0f-bf77-42d7-9fa3-8b101f33ee6b');
INSERT INTO Exercises VALUES ('4', 'Fill the gap excercise for Challenge', 1, 1,0,1,'Fill','01293c0f-bf77-42d7-9fa3-8b101f33ee6b');


INSERT INTO Questions VALUES ('1', '1 - In Java, the variable type that only has two possible values is:',1, '1');
INSERT INTO Questions VALUES ('5', '2 - In Java, the variable type that only has two possible values is:',1, '1');
INSERT INTO Questions VALUES ('2', 'In Java, the variable type that only has two possible values is:',1, '2');
INSERT INTO Questions VALUES ('6', 'In Java, the variable type that only has two possible values is:',1, '2');
INSERT INTO Questions VALUES ('3', 'In Java, the variable type that only has two possible values is:',1, '3');
INSERT INTO Questions VALUES ('4', 'In Java, the variable type that only has two possible values is:',1, '4');

INSERT INTO Solutions (solution_id,texts, correct, enable, question_id) VALUES ('1', 'The boolean', 1, 1,'1');
INSERT INTO Solutions (solution_id,texts, correct, enable, question_id) VALUES ('2', 'The int', 0, 1,'1');
INSERT INTO Solutions (solution_id,texts, correct, enable, question_id) VALUES ('3', 'The String', 0, 1,'1');
INSERT INTO Solutions (solution_id,texts, correct, enable, question_id) VALUES ('4', 'The double', 0, 1,'1');
INSERT INTO Solutions (solution_id,texts, correct, enable, question_id) VALUES ('5', 'The boolean', 1, 1,'2');
INSERT INTO Solutions (solution_id,texts, correct, enable, question_id) VALUES ('6', 'The boolean', 1, 1,'3');
INSERT INTO Solutions (solution_id,texts, correct, enable, question_id) VALUES ('7', 'The int', 0, 1,'3');
INSERT INTO Solutions (solution_id,texts, correct, enable, question_id) VALUES ('8', 'The String', 0, 1,'3');
INSERT INTO Solutions (solution_id,texts, correct, enable, question_id) VALUES ('9', 'The double', 0, 1,'3');
INSERT INTO Solutions (solution_id,texts, correct, enable, question_id) VALUES ('10', 'The boolean', 1, 1,'4');
INSERT INTO Solutions (solution_id,texts, correct, enable, question_id) VALUES ('11', 'The boolean', 1, 1,'5');
INSERT INTO Solutions (solution_id,texts, correct, enable, question_id) VALUES ('12', 'The int', 0, 1,'5');
INSERT INTO Solutions (solution_id,texts, correct, enable, question_id) VALUES ('13', 'The String', 0, 1,'5');
INSERT INTO Solutions (solution_id,texts, correct, enable, question_id) VALUES ('14', 'The double', 0, 1,'5');
INSERT INTO Solutions (solution_id,texts, correct, enable, question_id) VALUES ('15', 'The boolean', 1, 1,'6');


