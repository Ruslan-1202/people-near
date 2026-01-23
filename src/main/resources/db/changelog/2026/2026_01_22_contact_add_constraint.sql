ALTER TABLE contact
    DROP CONSTRAINT IF EXISTS contact_person_id_fkey;
ALTER TABLE contact
    ADD CONSTRAINT contact_person_id_fkey FOREIGN KEY (person_id) REFERENCES person (id) ON DELETE CASCADE;