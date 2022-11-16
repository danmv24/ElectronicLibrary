CREATE TABLE "books" (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    author_id BIGINT,
    FOREIGN KEY (author_id) REFERENCES authors (id)
);