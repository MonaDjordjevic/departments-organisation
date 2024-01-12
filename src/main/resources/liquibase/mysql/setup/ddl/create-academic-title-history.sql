CREATE TABLE tbl_academic_title_history
(
    id               bigint unsigned not null AUTO_INCREMENT,
    start_date       DATE,
    end_date         DATE,
    member_id        bigint unsigned,
    scientific_field varchar(100) not null,
    academic_title   varchar(100) not null,
    PRIMARY KEY (id),
    CONSTRAINT member_fk FOREIGN KEY (member_id) REFERENCES tbl_member (id) ON DELETE CASCADE
);

