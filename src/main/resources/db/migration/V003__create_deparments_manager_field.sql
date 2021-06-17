ALTER TABLE department
    ADD manager_id BIGINT UNIQUE REFERENCES employee (employee_id);