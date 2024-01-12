ALTER TABLE tbl_member
    ADD COLUMN member_role VARCHAR(50) DEFAULT 'OTHER',
    ADD COLUMN personal_no VARCHAR(120) not null;