ALTER TABLE person
    ADD COLUMN IF NOT EXISTS edit_ts timestamp with time zone;