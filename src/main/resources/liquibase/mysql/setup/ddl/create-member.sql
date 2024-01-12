CREATE TABLE tbl_member
(
    id               bigint unsigned not null AUTO_INCREMENT,
    first_name       varchar(100) not null,
    last_name        varchar(100) not null,
    department_id    bigint unsigned,
    academic_title   varchar(100) not null,
    education_title  varchar(100) not null,
    scientific_field varchar(100) not null,
    PRIMARY KEY (id),
    CONSTRAINT member_department_fk FOREIGN KEY (department_id) REFERENCES tbl_department (id) ON DELETE CASCADE
);
