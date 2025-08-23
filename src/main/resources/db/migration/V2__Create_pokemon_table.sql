CREATE TABLE pokemon (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name VARCHAR(30) NOT NULL,
    specie VARCHAR(20) NOT NULL,
    level INTEGER NOT NULL,
    status VARCHAR(20) NOT NULL,
    data_entry TIMESTAMP NOT NULL,
    data_exit TIMESTAMP,
    trainer_id VARCHAR(50) NOT NULL,
    CONSTRAINT valid_level CHECK (level >= 1 AND level <= 100),
    CONSTRAINT valid_status CHECK (status IN ('HEALTHY', 'FAINTED', 'POISONED', 'BURNED', 'PARALYZED', 'FROZEN', 'SLEEPING'))
);