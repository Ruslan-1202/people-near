ALTER TABLE person
    ALTER COLUMN name TYPE TEXT;
ALTER TABLE person
    ALTER COLUMN name DROP NOT NULL;
ALTER TABLE person
    ADD COLUMN last_name TEXT NULL;
ALTER TABLE person
    ADD COLUMN middle_name TEXT NULL;
ALTER TABLE person
    ADD COLUMN nick_name TEXT NULL;
ALTER TABLE person
    ADD CONSTRAINT person_c1_names CHECK ( name IS NOT NULL OR last_name IS NOT NULL OR nick_name IS NOT NULL );