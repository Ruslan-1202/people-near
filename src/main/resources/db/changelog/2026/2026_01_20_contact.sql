CREATE TABLE IF NOT EXISTS contact
(
    id        BIGSERIAL PRIMARY KEY,
    person_id BIGINT NOT NULL REFERENCES person (id) ON DELETE CASCADE,
    type_     INT    NOT NULL,
    value_    TEXT   NOT NULL
);