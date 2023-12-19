--liquibase formatted sql

--changeset agusev:1
CREATE TABLE IF NOT EXISTS movie (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL UNIQUE,
    position INT,
    genre VARCHAR(255),
    year INT,
    country VARCHAR(255),
    rating FLOAT
);