CREATE TABLE products(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(80) NOT NULL,
    price NUMERIC(12,2) NOT NULL,
    available BOOL NOT NULL DEFAULT true,
    description VARCHAR(200) NOT NULL,
    image_url VARCHAR(255) NOT NULL
);