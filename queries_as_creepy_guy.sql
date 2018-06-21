CREATE TABLE IF NOT EXISTS app_user(
	id_user serial PRIMARY KEY,
	first_name varchar(30) not null,
	last_name varchar(30) not null,
	login varchar(20)not null unique,
	user_role varchar(20)
);


CREATE TABLE IF NOT EXISTS user_data (
	login varchar(20),
	password varchar(20),
	id_user integer,
	FOREIGN KEY (id_user) REFERENCES app_user (id_user)
	ON DELETE CASCADE ON UPDATE NO ACTION
);


CREATE TABLE IF NOT EXISTS administrator (
	id_admin serial PRIMARY KEY,
	id_user integer,
	FOREIGN KEY (id_user) REFERENCES app_user (id_user)
	ON DELETE CASCADE ON UPDATE NO ACTION
);


CREATE TABLE IF NOT EXISTS mentor (
	id_mentor serial PRIMARY KEY,
	id_user integer,
	FOREIGN KEY (id_user) REFERENCES app_user (id_user)
	ON DELETE CASCADE ON UPDATE NO ACTION
);


CREATE TABLE IF NOT EXISTS student (
	id_student serial PRIMARY KEY,
	id_user integer,
	FOREIGN KEY (id_user) REFERENCES app_user (id_user),
	id_class integer,
	FOREIGN KEY (id_class) REFERENCES cool_class (id_class)
);


CREATE TABLE IF NOT EXISTS cool_class (
	id_class serial PRIMARY KEY,
	class_name varchar(20)
);


CREATE TABLE IF NOT EXISTS mentor_2_class (
	id_mentor integer,
	PRIMARY KEY (id_mentor, id_class),
	FOREIGN KEY (id_mentor) REFERENCES mentor (id_mentor)
    ON DELETE CASCADE ON UPDATE NO ACTION,
	id_class integer,
	FOREIGN KEY (id_class) REFERENCES cool_class (id_class)
);



CREATE TABLE IF NOT EXISTS artifact (
	id_artifact serial PRIMARY KEY,
	artifact_name varchar(50),
	price integer,
	category integer
);



CREATE TABLE IF NOT EXISTS quest (
    id_quest serial PRIMARY KEY,
    quest_name varchar(50),
    price integer
);

CREATE TABLE IF NOT EXISTS buyers_group (
	id_group serial PRIMARY KEY,
	id_artifact integer,
	FOREIGN KEY (id_artifact) REFERENCES artifact(id_artifact)
	ON DELETE CASCADE ON UPDATE NO ACTION,
        group_size integer NOT NULL
);


CREATE TABLE IF NOT EXISTS student_2_buyers_group (
    id_student integer,
    id_group integer,
    money_box integer,
    PRIMARY KEY(id_student, id_group),
    FOREIGN KEY (id_student) REFERENCES student(id_student)
	ON DELETE CASCADE ON UPDATE NO ACTION,
	FOREIGN KEY (id_group) REFERENCES buyers_group(id_group)
	ON DELETE CASCADE ON UPDATE NO ACTION
);


CREATE TABLE IF NOT EXISTS student_transaction (
    id_transaction serial NOT NULL,
    id_student integer,
    id_artifact integer,
    FOREIGN KEY (id_student) REFERENCES student(id_student)
	ON DELETE CASCADE ON UPDATE NO ACTION,
	FOREIGN KEY (id_artifact) REFERENCES artifact(id_artifact)
	ON DELETE CASCADE ON UPDATE NO ACTION
);


CREATE TABLE IF NOT EXISTS group_transaction (
    id_transaction serial NOT NULL,
    id_group integer,
    id_artifact integer,
    FOREIGN KEY (id_group) REFERENCES buyers_group(id_group)
	ON DELETE CASCADE ON UPDATE NO ACTION,
	FOREIGN KEY (id_artifact) REFERENCES artifact(id_artifact)
	ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE IF NOT EXISTS student_artifact(
	id_student integer,
	 FOREIGN KEY (id_student) REFERENCES student(id_student)
		ON DELETE CASCADE ON UPDATE NO ACTION,
	id_artifact integer,
	FOREIGN KEY (id_artifact) REFERENCES artifact(id_artifact)
		ON DELETE CASCADE ON UPDATE NO ACTION,
	quantity integer);
	


INSERT INTO app_user(first_name, last_name, login, user_role)
VALUES('MAREK', 'Kowalski', 'marek123', 2),
('Jan', 'Krzynówek', 'krzynu', 2);
('Seba', 'Adamczyk', 'sebek', 3)
('Adam', 'Bury', 'burek', 1);



INSERT INTO mentor(id_user, id_class)
VALUES(2, 1)


INSERT INTO quest(quest_name , price)
VALUES('quest', 100);


INSERT INTO artifact(artifact_name, price, category)
VALUES('dragon', 20, 1);

INSERT INTO student(id_user, id_class)
VALUES(1,2)


INSERT INTO student_artifact (
id_student,id_artifact, quantity)
VALUES (1, 1, 4);
	




--1
SELECT * FROM mentor;

--2
INSERT INTO mentor(id_user)
VALUES(1);

--3
INSERT INTO cool_class(class_name)
VALUES('Nazwa klasy');

INSERT INTO mentor_2_class(id_mentor,id_class)
VALUES (SELECT * FROM mentor WHERE id_mentor = 1), (SELECT * FROM cool_class WHERE id_class = 1);

--4
BEGIN;
	INSERT INTO app_user(first_name, last_name, login, user_role)
	VALUES('krk', 'krkrk', 'login', 2);
	
	INSERT INTO mentor(id_class)
	VALUES(1)
	WHERE app_user = 1;
COMMIT;
