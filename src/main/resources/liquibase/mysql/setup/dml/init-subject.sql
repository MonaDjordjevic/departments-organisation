insert into tbl_subject(name,espb,department_id)
values ("subject-3", 5, (select (id) from tbl_department WHERE name="department-1"));
insert into tbl_subject(name,espb,department_id)
values ("subject-4", 5, (select (id) from tbl_department WHERE name="department-2"));
insert into tbl_subject(name,espb,department_id)
values ("subject-5", 5, (select (id) from tbl_department WHERE name="department-1"));
