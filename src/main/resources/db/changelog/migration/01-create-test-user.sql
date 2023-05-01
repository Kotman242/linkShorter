-- liquibase formatter sql

-- changeset andrewZ:1
INSERT INTO users(login, password)
VALUES ('user1', '$2a$10$RnijWxIPCTiwTzJkEhoygeQmsf08VKA/FPGxEquN6ffLkg2/O6TsK');
--rollback DELETE FROM users WHERE login='user1'