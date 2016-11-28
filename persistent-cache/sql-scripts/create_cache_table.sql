CREATE TABLE IF NOT EXISTS cache (
    cache_id IDENTITY,
    operation BLOB NOT NULL,
    result BLOB NOT NULL,

    CONSTRAINT uq_operation UNIQUE (operation)
);