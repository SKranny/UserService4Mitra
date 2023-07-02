CREATE TABLE IF NOT EXISTS person (
    id                  BIGSERIAL PRIMARY KEY,
    email               VARCHAR(255) UNIQUE NOT NULL,
    first_name          VARCHAR(255),
    last_name           VARCHAR(255),
    birth_day           DATE,
    password            VARCHAR(255)
 );

CREATE TABLE IF NOT EXISTS role (
    id                  BIGSERIAL PRIMARY KEY,
    role                VARCHAR(255) NOT NULL
);

INSERT INTO role (role) VALUES ('ROLE_USER'), ('ROLE_ADMIN');

CREATE TABLE IF NOT EXISTS person2role (
    person_id           BIGINT NOT NULL REFERENCES person(id),
    role_id             BIGINT NOT NULL REFERENCES role(id)
);