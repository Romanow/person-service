-- v1.0 create person table
CREATE TABLE persons
(
    id      SERIAL PRIMARY KEY,
    name    VARCHAR(80) NOT NULL,
    age     INT,
    address VARCHAR,
    work    VARCHAR
);
