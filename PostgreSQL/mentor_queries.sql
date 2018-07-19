--AS A MENTOR

--6. (id_class = 1 podane opcjonalnie, podaje to mentor po wyświetleniu klas i sprawdzeniu id)
BEGIN TRANSACTION;
INSERT INTO app_user (name, last_name, phone, email, role) 
VALUES ('Ania', 'ffidjfid', '123123132', 'dupa@mids', 'student');

INSERT INTO student(id_user, id_class, current_money, total_money)
VALUES ((SELECT id_user FROM app_user WHERE email = 'dupa@mids'), 1, 0, 0);
COMMIT;


--7,8
INSERT INTO quest (quest_name, price, category, description)
VALUES ('si', 20, 'basic', 'si finished blabla');

--9.
INSERT INTO studentArtifact(artifact_name, category, description, price)
VALUES ('nazwaArtefaktuHEHE', 'magic', '60 min workshop', 1000);


--10. (podajemy price przez input, opcjonalnie 40
--id_quest wybiera mentor po uprzednim wyświetleniu i wybraniu, opcjonalnie 1
SELECT * FROM quest

UPDATE quest
SET price = 40
WHERE id_quest = 1;


--11. 
INSERT INTO studentArtifact (artifact_name, category, price, description)
VALUES ('artifactName', 'magic', 40, 'eefsfdsgis');


--12. (ktoś tam podaje id_quest, nie pamiętam kto)
UPDATE student
SET money = money + (SELECT price FROM quest WHERE id_quest = 1);


--13. TRZEBA SPRAWZDZIĆ QUANTITY JAK WIEKSZE NIŻ 1 TO ODEJMIJ JEDNO JEŚLI =1 TO DELETE:)

SELECT * FROM student

SELECT * FROM student_artifact
WHERE id_student = ?

DELETE FROM student_artifact
WHERE id_artifact = ?  

14.

SELECT * FROM student

SELECT money FROM student
WHERE id_student = ?

SELECT * FROM student_transaction
WHERE id_student ??


------ PRZYPISYWANIE NOWEGO ITEMU DO STUDENTA (jeśli ma to insert jeśli nie to update quantity +1)
UPDATE student_artifact SET quantity = quantity + 1 WHERE id_student = 1;
INSERT INTO student_artifact (id_student, id_artifact, quantity)
       SELECT 1, 2, 1
       WHERE NOT EXISTS (SELECT 1 FROM student_artifact WHERE id_student = 1);








	


