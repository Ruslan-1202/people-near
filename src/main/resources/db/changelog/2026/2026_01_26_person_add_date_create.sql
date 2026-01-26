ALTER TABLE person
    ADD COLUMN IF NOT EXISTS creation_ts timestamp with time zone DEFAULT CURRENT_TIMESTAMP NOT NULL;
UPDATE person
SET creation_ts = now()
WHERE creation_ts IS NULL;

