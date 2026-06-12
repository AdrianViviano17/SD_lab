CREATE DATABASE bank_arequipa;

-- Conéctate a bank_arequipa y ejecuta:
CREATE TABLE accounts (
    id SERIAL PRIMARY KEY,
    client_name VARCHAR(100),
    balance NUMERIC(10, 2)
);

INSERT INTO accounts (client_name, balance) VALUES ('Cliente Corporativo', 50000.00);


CREATE DATABASE bank_cusco;

-- Conéctate a bank_cusco y ejecuta:
CREATE TABLE accounts (
    id SERIAL PRIMARY KEY,
    client_name VARCHAR(100),
    balance NUMERIC(10, 2)
);

INSERT INTO accounts (client_name, balance) VALUES ('Cliente Corporativo', 10000.00);
