CREATE TABLE tbl_secretary_history
(
    id            bigint unsigned not null AUTO_INCREMENT,
    start_date    DATE,
    end_date      DATE,
    secretary_id  bigint unsigned,
    department_id bigint unsigned,
    PRIMARY KEY (id),
    CONSTRAINT secretary_member_fk FOREIGN KEY (secretary_id) REFERENCES tbl_member (id) ON DELETE CASCADE,
    CONSTRAINT secretary_department_fk FOREIGN KEY (department_id) REFERENCES tbl_department (id) ON DELETE CASCADE
);

