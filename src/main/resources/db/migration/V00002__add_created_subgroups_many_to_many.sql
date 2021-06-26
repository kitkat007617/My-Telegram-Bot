ALTER TABLE tg_users ADD PRIMARY KEY (chat_id);

DROP TABLE IF EXISTS group_sub;
DROP TABLE IF EXISTS group_x_user;

CREATE TABLE group_sub
(
    id INTEGER,
    title VARCHAR,
    last_article_id INTEGER,
    PRIMARY KEY (id)
);

CREATE TABLE group_x_user
(
    group_id INTEGER NOT NULL ,
    user_id varchar NOT NULL ,
    FOREIGN KEY (group_id) REFERENCES group_sub(id),
    FOREIGN KEY (user_id) REFERENCES tg_users(chat_id)
);
CREATE UNIQUE INDEX group_user_unique_index ON group_x_user(group_id, user_id);