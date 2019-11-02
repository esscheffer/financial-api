CREATE TABLE state
(
    id        BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    state_name VARCHAR(50) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO state (id, state_name)
VALUES (1, 'Acre'),
       (2, 'Alagoas'),
       (3, 'Amazonas'),
       (4, 'Amapá'),
       (5, 'Bahia'),
       (6, 'Ceará'),
       (7, 'Distrito Federal'),
       (8, 'Espírito Santo'),
       (9, 'Goiás'),
       (10, 'Maranhão'),
       (11, 'Minas Gerais'),
       (12, 'Mato Grosso do Sul'),
       (13, 'Mato Grosso'),
       (14, 'Pará'),
       (15, 'Paraíba'),
       (16, 'Pernambuco'),
       (17, 'Piauí'),
       (18, 'Paraná'),
       (19, 'Rio de Janeiro'),
       (20, 'Rio Grande do Norte'),
       (21, 'Rondônia'),
       (22, 'Roraima'),
       (23, 'Rio Grande do Sul'),
       (24, 'Santa Catarina'),
       (25, 'Sergipe'),
       (26, 'São Paulo'),
       (27, 'Tocantins');

CREATE TABLE city
(
    id       BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    city_name VARCHAR(50) NOT NULL,
    id_state BIGINT(20)  NOT NULL,
    FOREIGN KEY (id_state) REFERENCES state (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

INSERT INTO city (id, city_name, id_state)
VALUES (1, 'Belo Horizonte', 11),
       (2, 'Uberlândia', 11),
       (3, 'Uberaba', 11),
       (4, 'São Paulo', 26),
       (5, 'Campinas', 26),
       (6, 'Rio de Janeiro', 19),
       (7, 'Angra dos Reis', 19),
       (8, 'Goiânia', 9),
       (9, 'Caldas Novas', 9);

ALTER TABLE person
    DROP COLUMN city;
ALTER TABLE person
    DROP COLUMN state;
ALTER TABLE person
    ADD COLUMN id_city BIGINT(20);
ALTER TABLE person
    ADD CONSTRAINT fk_person_city FOREIGN KEY (id_city) REFERENCES city (id);

UPDATE person
SET id_city = 2;
