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