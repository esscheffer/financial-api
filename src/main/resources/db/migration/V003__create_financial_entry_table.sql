CREATE TABLE financial_entry
(
  id                BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  entry_description VARCHAR(50)    NOT NULL,
  due_date          DATE           NOT NULL,
  payment_date      DATE,
  entry_value       DECIMAL(10, 2) NOT NULL,
  observation       VARCHAR(100),
  type              VARCHAR(20)    NOT NULL,
  id_category       BIGINT(20)     NOT NULL,
  id_person         BIGINT(20)     NOT NULL,
  FOREIGN KEY (id_category) REFERENCES category (id),
  FOREIGN KEY (id_person) REFERENCES person (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;