CREATE TABLE tbl_head_history
(
    id            bigint unsigned not null AUTO_INCREMENT,
    start_date     DATE,
    end_date       DATE,
    head_id        bigint unsigned,
    department_id  bigint unsigned,
    PRIMARY KEY (id),
    CONSTRAINT head_member_fk FOREIGN KEY (head_id) REFERENCES tbl_member (id) ON DELETE CASCADE,
    CONSTRAINT head_department_fk FOREIGN KEY (department_id) REFERENCES tbl_department (id) ON DELETE CASCADE
);
