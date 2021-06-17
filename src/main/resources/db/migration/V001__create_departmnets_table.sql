CREATE SEQUENCE dep_seq START 30 INCREMENT 10;

CREATE TABLE department
(
    department_id   BIGINT DEFAULT nextval('dep_seq') PRIMARY KEY,
    department_name VARCHAR(30) NOT NULL UNIQUE
);