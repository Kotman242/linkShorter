-- liquibase formatter sql

-- changeset andrewZ:1
INSERT INTO users(login, password)
VALUES ('user1', 'pass1');
INSERT INTO users(login, password)
VALUES ('user2', 'pass2');
-- rollback DELETE FROM users;

