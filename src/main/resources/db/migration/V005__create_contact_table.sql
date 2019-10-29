CREATE TABLE contact
(
    id           BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    name         VARCHAR(50)  NOT NULL,
    email        VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20)  NOT NULL,
    id_person    BIGINT(20)   NOT NULL,
    FOREIGN KEY (id_person) REFERENCES person (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
