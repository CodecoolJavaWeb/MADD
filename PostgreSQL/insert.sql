INSERT INTO app_user(first_name, last_name, phone, email, role)
VALUES ('Jurek', 'Kowalski', '123456789', 'jurek@kowalski.pl', 'admin'),
	('Konrad', 'Gadzina', '111222333', 'konrad@gadzina.pl', 'mentor'),
	('Marek', 'Grzybek', '33445566', 'marek@grzybek.pl', 'mentor'),
	('Michal', 'Grzegorzyk', '000999888', 'michal@grz.pl', 'student'),
	('Anna', 'Idzi', '111000111', 'anna@idzi.pl', 'student');

INSERT INTO authentication (login, password, id_user) 
VALUES ('admin', 'admin', 1), 
	('mentork', 'mentork', 2), 
	('mentorm', 'mentorm', 3), 
	('studentm', 'studentm', 4), 
	('studenta', 'studenta', 5);

INSERT INTO administrator (id_user)
VALUES (1);
	
	
INSERT INTO mentor (id_user)
VALUES (2),(3);


INSERT INTO cool_class (class_name)
VALUES ('2017.3'), ('2018.1');

INSERT INTO student (id_user)
VALUES (4,1,0,0),(5,2,0,0);


INSERT INTO mentor_2_class
VALUES (1,1), (2,1);


INSERT INTO artifact
VALUES ('Combat training', 50, 'basic', 'Private mentoring'),
	('Sanctuary', 300, 'basic', 'You can spend a day in home office'),
	('Time Travel', 500, 'basic', 'extend SI week assignment deadline by one day'),
	('Circle of Sorcery', 1000, 'magic', '60 min workshop by a mentor(s) of the chosen topic '),
	('Summon Code Elemental', 1000, 'magic', 'mentor joins a students team for a one hour'),
	('Tome', 500, 'magic', 'Extra material for the current topic ');


INSERT INTO quest
VALUES ('Exploring a dungeon', 100, 'basic', ' Finishing a Teamwork week'),
	('Solving the magic puzzle', 100, 'basic', 'Finishing an SI assignment'),
	('Slaying a dragon', 500, 'basic', 'Passing a Checkpoint'),
	('Spot trap', 50, 'magic', 'Spot a major mistake in the assignment'),
	('Taming a pet', 100, 'magic', 'Doing a demo about a pet project'),
	('Recruiting some n00bs', 100, 'magic', 'Taking part in the student screening process');


INSERT INTO buyers_group
VALUES (5,2), (4,2);





