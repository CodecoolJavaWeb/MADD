AS A MENTOR

6.

BEGIN;
INSERT INTO app_user (name, last_name, phone, email, role) 
VALUES ('Ania', 'ffidjfid', '123123132', 'mdsims@mids', 'student')

INSERT INTO student(id_user, id_class, money, total_money)
VALUES ((SELECT id_user form app_user
	WHERE email = 'mdsims@mids'), ? , 0, 0)
COMMIT;

7,8

INSERT INTO quest (name, price, category, description)
VALUES ('si', 20, 'basic', 'si finished blabla')

9.

INSERT INTO artifact(category, description, price)
VALUES ('magic', ' 60 min workshop', 1000)


10.

SELECT * FROM quest

UPDATE quest
SET price = ???
WHERE
 id_quest = ?;


11. 

INSERT INTO artifact (category, price, description)
VALUES ('magic', 40, 'eefsfdsgis')

12.

SELECT * FROM quest

SELECT price * FROM quest 
WHERE id_quest = ???


UPDATE student
SET money = money + price


13.

SELECT * FROM students

SELECT * FROM student_artifact
WHERE id_student = ?

DELETE FROM student_artifact
WHERE id_artifact = ?  

14.

SELECT * FROM student

SELECT money FROM student
WHERE id_student = ?

SELECT * FROM student_transaction
WHERE id_stundet ??









	


