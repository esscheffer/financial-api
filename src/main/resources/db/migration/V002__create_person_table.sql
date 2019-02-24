CREATE TABLE person
(
  id            BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  name          VARCHAR(50) NOT NULL,
  street_address VARCHAR(50),
  number        VARCHAR(30),
  complement    VARCHAR(30),
  neighborhood  VARCHAR(30),
  zip_code       VARCHAR(30),
  city          VARCHAR(30),
  state         VARCHAR(30),
  active        BOOLEAN     NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;