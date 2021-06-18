DROP TABLE IF EXISTS tg_users;

CREATE TABLE tg_users
(
    chat_id VARCHAR(100),
    active BOOL DEFAULT TRUE
);