CREATE TABLE recipe (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    making_time VARCHAR(255) NOT NULL,
    serves VARCHAR(255) NOT NULL,
    ingredients VARCHAR(1000) NOT NULL,
    cost VARCHAR(50) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);