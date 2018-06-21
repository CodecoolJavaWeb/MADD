--1
INSERT INTO app_user(first_name, last_name, login, user_role)
VALUES('krk', 'krkrk', 'login', 'mentor');

INSERT INTO mentor(id_user)
VALUES((SELECT id_user FROM app_user WHERE id_user = 2));

--2

INSERT INTO cool_class(class_name)
VALUES('Nazwa klasy');

INSERT INTO mentor_2_class(id_mentor, id_class)
VALUES((SELECT id_mentor FROM mentor WHERE id_mentor = id;), (SELECT id_class FROM cool_class WHERE id_class = id));

--3

SELECT first_name, class_name, email, last_name FROM app_user
LEFT JOIN mentor
ON app_user.id_user = mentor.id_user
LEFT JOIN mentor_2_class
ON mentor.id_mentor = mentor_2_class.id_mentor
LEFT JOIN cool_class
ON  mentor_2_class.id_class = cool_class.id_class

UPDATE app_user
SET email = 'new mail'
WHERE app_user.id = ?;

UPDATE 

--4

SELECT * FROM student
LEFT JOIN mentor_2_class
ON student.id_class = mentor_2_class.id_class
WHERE id_mentor = mentor.getid

--5

INSERT INTO experience_level (description, achieve_money)
VALUES('level 2', 100), ('level 3', 200);
