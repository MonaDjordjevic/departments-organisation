CREATE TABLE tbl_subject
(
    id            bigint unsigned not null AUTO_INCREMENT,
    name          varchar(100) not null,
    espb          int,
    department_id bigint unsigned,
    PRIMARY KEY (id),
    CONSTRAINT department_fk FOREIGN KEY (department_id) REFERENCES tbl_department (id) ON DELETE CASCADE
);

