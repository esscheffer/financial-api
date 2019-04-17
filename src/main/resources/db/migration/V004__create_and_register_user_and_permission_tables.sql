CREATE TABLE user
(
    id       BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name     VARCHAR(50)  NOT NULL,
    email    VARCHAR(50)  NOT NULL,
    password VARCHAR(150) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE permission
(
    id          BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    description VARCHAR(50) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE user_permission
(
    id_user        BIGINT(20) NOT NULL,
    id_permission BIGINT(20) NOT NULL,
    PRIMARY KEY (id_user, id_permission),
    FOREIGN KEY (id_user) REFERENCES user (id),
    FOREIGN KEY (id_permission) REFERENCES permission (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO user
values (1, 'Administrator', 'admin@test.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.'),
       (2, 'ApiUser One', 'user@test.com', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');

INSERT INTO permission
values (1, 'ROLE_REGISTER_CATEGORY'),
       (2, 'ROLE_SEARCH_CATEGORY');

INSERT INTO permission
values (3, 'ROLE_REGISTER_PERSON'),
       (4, 'ROLE_REMOVE_PERSON'),
       (5, 'ROLE_SEARCH_PERSON');

INSERT INTO permission
values (6, 'ROLE_REGISTER_FINANCIAL_ENTRY'),
       (7, 'ROLE_REMOVE_FINANCIAL_ENTRY'),
       (8, 'ROLE_SEARCH_FINANCIAL_ENTRY');

-- admin
INSERT INTO user_permission
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (1, 6),
       (1, 7),
       (1, 8);

-- ApiUser
INSERT INTO user_permission
values (2, 2),
       (2, 5),
       (2, 8);