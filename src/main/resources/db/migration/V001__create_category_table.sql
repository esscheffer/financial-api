CREATE TABLE category (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(20) NOT NULL UNIQUE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO category (name) values ('Recreation');
INSERT INTO category (name) values ('Food');
INSERT INTO category (name) values ('Market');
INSERT INTO category (name) values ('Pharmacy');
INSERT INTO category (name) values ('Other');