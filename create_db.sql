CREATE TABLE IF NOT EXISTS app_user (
	id_user serial PRIMARY KEY,
	first_name varchar(30) not null,
	last_name varchar(30) not null,
	phone varchar(30) not null,
	email varchar(100) not null,
	role varchar(20)
);

CREATE TABLE IF NOT EXISTS authentication (
	login varchar(20) not null,
	password varchar(20) not null,
	id_user integer not null,
	FOREIGN KEY (id_user) REFERENCES app_user (id_user)
		ON DELETE CASCADE ON UPDATE NO ACTION
);


CREATE TABLE IF NOT EXISTS administrator (
	id_admin serial PRIMARY KEY,
	id_user integer NOT NULL,
	FOREIGN KEY (id_user) REFERENCES app_user (id_user)
		ON DELETE CASCADE ON UPDATE NO ACTION
);


CREATE TABLE IF NOT EXISTS mentor (
	id_mentor serial PRIMARY KEY,
	id_user integer NOT NULL,
	FOREIGN KEY (id_user) REFERENCES app_user (id_user)
	    ON DELETE CASCADE ON UPDATE NO ACTION
);


CREATE TABLE IF NOT EXISTS cool_class (
	id_class serial PRIMARY KEY,
	class_name varchar(20) NOT NULL
);


CREATE TABLE IF NOT EXISTS student (
	id_student serial PRIMARY KEY,
	id_user integer NOT NULL,
	FOREIGN KEY (id_user) REFERENCES app_user (id_user)
		ON DELETE CASCADE ON UPDATE NO ACTION,
	id_class integer NOT NULL,
	FOREIGN KEY (id_class) REFERENCES cool_class (id_class)
		ON DELETE CASCADE ON UPDATE NO ACTION
);


CREATE TABLE IF NOT EXISTS mentor_2_class (
	id_mentor integer NOT NULL,
	id_class integer NOT NULL,
	PRIMARY KEY (id_mentor, id_class),
	FOREIGN KEY (id_mentor) REFERENCES mentor (id_mentor)
    	ON DELETE CASCADE ON UPDATE NO ACTION,
	FOREIGN KEY (id_class) REFERENCES cool_class (id_class)
		ON DELETE CASCADE ON UPDATE NO ACTION
);


CREATE TABLE IF NOT EXISTS artifact (
	id_artifact serial PRIMARY KEY,
	artifact_name varchar(50) NOT NULL,
	price integer NOT NULL,
	category varchar(50) NOT NULL,
	description varchar(255)
);


CREATE TABLE IF NOT EXISTS quest (
	id_quest serial PRIMARY KEY,
	quest_name varchar(50) NOT NULL,
	price integer NOT NULL,
	category varchar(50) NOT NULL,
	description varchar(255)
);


CREATE TABLE IF NOT EXISTS buyers_group (
	id_group serial PRIMARY KEY,
	id_artifact integer NOT NULL,
	FOREIGN KEY (id_artifact) REFERENCES artifact(id_artifact)
	    ON DELETE CASCADE ON UPDATE NO ACTION,
	group_size integer NOT NULL
);


CREATE TABLE IF NOT EXISTS student_2_buyers_group (
	id_student integer not null,
	id_group integer not null,
	current_money integer,
	is_leader boolean not null,
	PRIMARY KEY(id_student, id_group),
	FOREIGN KEY (id_student) REFERENCES student(id_student)
		ON DELETE CASCADE ON UPDATE NO ACTION,
	FOREIGN KEY (id_group) REFERENCES buyers_group(id_group)
		ON DELETE CASCADE ON UPDATE NO ACTION
);


CREATE TABLE IF NOT EXISTS student_transaction (
    id_transaction serial NOT NULL,
    id_student integer NOT NULL,
    id_artifact integer NOT NULL,
    FOREIGN KEY (id_student) REFERENCES student(id_student)
		ON DELETE CASCADE ON UPDATE NO ACTION,
	FOREIGN KEY (id_artifact) REFERENCES artifact(id_artifact)
		ON DELETE CASCADE ON UPDATE NO ACTION
);


CREATE TABLE IF NOT EXISTS group_transaction (
	id_transaction serial NOT NULL,
	id_group integer NOT NULL,
	id_artifact integer NOT NULL,
	FOREIGN KEY (id_group) REFERENCES buyers_group(id_group)
		ON DELETE CASCADE ON UPDATE NO ACTION,
	FOREIGN KEY (id_artifact) REFERENCES artifact(id_artifact)
		ON DELETE CASCADE ON UPDATE NO ACTION
);


CREATE TABLE IF NOT EXISTS student_artifact(
	id_student integer NOT NULL,
	 FOREIGN KEY (id_student) REFERENCES student(id_student)
		ON DELETE CASCADE ON UPDATE NO ACTION,
	id_artifact integer NOT NULL,
	FOREIGN KEY (id_artifact) REFERENCES artifact(id_artifact)
		ON DELETE CASCADE ON UPDATE NO ACTION,
	quantity integer
);


CREATE TABLE IF NOT EXISTS experience_level(
	id_level serial PRIMARY KEY NOT NULL,
	level_name varchar(20),
	achieve_money integer
);



