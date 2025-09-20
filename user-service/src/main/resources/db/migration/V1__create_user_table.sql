CREATE TABLE users (
    id UUID PRIMARY KEY,
    keycloak_id VARCHAR(255) NOT NULL UNIQUE,
    address_street VARCHAR(255),
    address_number VARCHAR(20),
    address_complement VARCHAR(255),
    address_neighborhood VARCHAR(100),
    address_city VARCHAR(100),
    address_state VARCHAR(100),
    address_zipcode VARCHAR(20),
    phone VARCHAR(20)
);
