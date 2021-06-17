CREATE SEQUENCE emp_seq START 2 INCREMENT 1;

CREATE TABLE employee
(
    employee_id         BIGINT DEFAULT nextval('emp_seq') PRIMARY KEY,
    employee_first_name VARCHAR(20),
    employee_last_name  VARCHAR(20) NOT NULL,
    employee_job        VARCHAR(20),
    employee_email      VARCHAR(30) NOT NULL UNIQUE,
    employee_salary     DECIMAL(8, 2),
    employee_hire_date  TIMESTAMP,
    department_id       BIGINT REFERENCES department (department_id)
);