CREATE TABLE userr(
	id_user serial PRIMARY KEY,
	user_role integer
);


CREATE TABLE IF NOT EXISTS user_data (
	login text,
	password text,
	id_user integer,
	FOREIGN KEY (id_user) REFERENCES userr (id_user)
	ON DELETE CASCADE ON UPDATE NO ACTION
);


CREATE TABLE IF NOT EXISTS class (
	id_class serial PRIMARY KEY
);


CREATE TABLE IF NOT EXISTS mentor (
	id_mentor serial PRIMARY KEY,
	id_user integer,
	FOREIGN KEY (id_user) REFERENCES userr (id_user)
	ON DELETE CASCADE ON UPDATE NO ACTION,
	id_class integer,
	FOREIGN KEY (id_class) REFERENCES class (id_class)
	ON DELETE CASCADE ON UPDATE NO ACTION
);


CREATE TABLE mentor_class (
	id_mentor integer,
	FOREIGN KEY (id_mentor) REFERENCES mentor (id_mentor)
    ON DELETE CASCADE ON UPDATE NO ACTION,
	id_class integer,
	FOREIGN KEY (id_class) REFERENCES class (id_class)
);


CREATE TABLE student (
	id_student serial PRIMARY KEY,
	id_user integer,
	FOREIGN KEY (id_user) REFERENCES userr (id_user),
	id_class integer,
	FOREIGN KEY (id_class) REFERENCES class(id_class)
);


CREATE TABLE admin (
	id_admin serial PRIMARY KEY,
	id_user integer,
	FOREIGN KEY (id_user) REFERENCES userr (id_user)
	ON DELETE CASCADE ON UPDATE NO ACTION
);


CREATE TABLE backpack (
	id_backpack serial PRIMARY KEY,
	id_student integer,
 	FOREIGN KEY (id_student) REFERENCES student (id_student)
	ON DELETE CASCADE ON UPDATE NO ACTION,
	current_coins integer,
	total_coins integer
);


CREATE TABLE artifact (
	id_artifact serial PRIMARY KEY,
	category integer
	);

CREATE TABLE buyers_group (
	id_group serial PRIMARY KEY,
	id_artifact integer,
	FOREIGN KEY (id_artifact) REFERENCES artifact(id_artifact)
	ON DELETE CASCADE ON UPDATE NO ACTION
);

CREATE TABLE student_group (
	id_group integer,
	foreign KEY (id_group) REFERENCES buyers_group (id_group)
	ON DELETE CASCADE ON UPDATE NO ACTION,
	id_student integer,
	FOREIGN KEY (id_student) REFERENCES student(id_student)
	ON DELETE CASCADE ON UPDATE NO ACTION
	);

	


	





