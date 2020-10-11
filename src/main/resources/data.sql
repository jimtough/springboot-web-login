DROP TABLE IF EXISTS role;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS user_role;

CREATE TABLE role (
	rolename VARCHAR(16) NOT NULL PRIMARY KEY
);

CREATE TABLE user (
	id INT IDENTITY,
	username VARCHAR(24) NOT NULL,
	password VARCHAR(24) NOT NULL,
	enabled BOOLEAN NOT NULL
);

CREATE UNIQUE INDEX uidx_u_username ON user (username);

CREATE TABLE user_role (
	user_id INT,
	rolename VARCHAR(16) NOT NULL,
	FOREIGN KEY (user_id) REFERENCES user(id),
	FOREIGN KEY (rolename) REFERENCES role(rolename)
);

CREATE UNIQUE INDEX uidx_ur_id_rolename ON user_role (user_id, rolename);

INSERT INTO role (rolename) VALUES
	('USER'),
	('ADMIN')
;

INSERT INTO user (username, password, enabled) VALUES
	('scott', 'tiger', TRUE),
	('foo', 'bar', TRUE),
	('example', 'example', TRUE),
	('aaa', 'bbb', FALSE)
;

INSERT INTO user_role (user_id, rolename) VALUES
	(SELECT id FROM user WHERE username = 'scott', 'USER'),
	(SELECT id FROM user WHERE username = 'scott', 'ADMIN'),
	(SELECT id FROM user WHERE username = 'foo', 'USER'),
	(SELECT id FROM user WHERE username = 'example', 'USER'),
	(SELECT id FROM user WHERE username = 'aaa', 'USER')
;
