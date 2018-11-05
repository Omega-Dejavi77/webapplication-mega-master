INSERT INTO Posts  (post_id, title, description, creationDay, likes, enable, son_type) VALUES('01293c0f-bf77-42d7-9fa3-8b101f33ee5b','Tutorial','Tutorial Description',current_timestamp,0,1,'Tutorial');
INSERT INTO Posts  (post_id, title, description, creationDay, likes, enable, son_type) VALUES ('01293c0f-bf77-42d7-9fa3-8b101f33ee6b','Challenge','Challenge Description',current_timestamp,0,1,'Challenge');


INSERT INTO Exercises VALUES ('1', 'Test excercise for Tutorial', 1, 1,0,'Test','01293c0f-bf77-42d7-9fa3-8b101f33ee5b');
INSERT INTO Exercises VALUES ('2', 'Fill the gap excercise for Tutorial', 1, 1,0,'Fill','01293c0f-bf77-42d7-9fa3-8b101f33ee5b');
INSERT INTO Exercises VALUES ('3', 'Test excercise for Challenge', 1, 1,0,'Test','01293c0f-bf77-42d7-9fa3-8b101f33ee6b');
INSERT INTO Exercises VALUES ('4', 'Fill the gap excercise for Challenge', 1, 1,0,'Fill','01293c0f-bf77-42d7-9fa3-8b101f33ee6b');

INSERT INTO Questions VALUES ('1', 'In Java, the variable type that only has two possible values is:',1, '1');
INSERT INTO Questions VALUES ('2', 'In Java, the variable type that only has two possible values is:',1, '2');
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

