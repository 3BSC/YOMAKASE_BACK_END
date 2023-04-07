CREATE TABLE IF NOT EXISTS user
(
    id              BIGINT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    is_deleted      TINYINT(1)      NOT NULL DEFAULT 0,
    email           VARCHAR(255)    NOT NULL,
    password        VARCHAR(60),
    nickname        VARCHAR(100),
    birth_day       TIMESTAMP,
    is_owner        TINYINT(1)      NOT NULL DEFAULT 0
) default character set utf8mb4
  collate utf8mb4_bin;

CREATE TABLE IF NOT EXISTS restaurant
(
    id              BIGINT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    is_deleted      TINYINT(1)      NOT NULL DEFAULT 0,
    registration    VARCHAR(255),
    category        VARCHAR(10),
    summary         VARCHAR(1000),
    phone_number    VARCHAR(10)
) default character set utf8mb4
  collate utf8mb4_bin;

CREATE TABLE IF NOT EXISTS price
(
    id              BIGINT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    is_deleted      TINYINT(1)      NOT NULL DEFAULT 0,
    is_weekday      TINYINT(1)      NOT NULL DEFAULT 0,
    is_lunch        TINYINT(1)      NOT NULL DEFAULT 0,
    price           INT             NOT NULL,
    restaurant_id   BIGINT          NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
) default character set utf8mb4
  collate utf8mb4_bin;

CREATE TABLE IF NOT EXISTS closed_days
(
    id              BIGINT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    is_deleted      TINYINT(1)      NOT NULL DEFAULT 0,
    day             VARCHAR(10)     NOT NULL,
    restaurant_id   BIGINT          NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
) default character set utf8mb4
  collate utf8mb4_bin;

CREATE TABLE IF NOT EXISTS facilities
(
    id              BIGINT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    facilitie       VARCHAR(10)     NOT NULL,
    restaurant_id   BIGINT          NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
) default character set utf8mb4
  collate utf8mb4_bin;

CREATE TABLE IF NOT EXISTS address
(
    id              BIGINT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    1depth          VARCHAR(15),
    2depth          VARCHAR(15),
    3depth          VARCHAR(15),
    name            VARCHAR(50)     NOT NULL,
    restaurant_id   BIGINT          NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
) default character set utf8mb4
  collate utf8mb4_bin;

CREATE TABLE IF NOT EXISTS reservation
(
    id              BIGINT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    created_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    updated_at      TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP(),
    is_deleted      TINYINT(1)      NOT NULL DEFAULT 0,
    user_id         BIGINT          NOT NULL,
    restaurant_id   BIGINT          NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id),
    FOREIGN KEY (user_id) REFERENCES user (id)
) default character set utf8mb4
  collate utf8mb4_bin;

CREATE TABLE IF NOT EXISTS today_line_up
(
    id              BIGINT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
    menu            VARCHAR(50)     NOT NULL,
    restaurant_id   BIGINT          NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant (id) ON DELETE CASCADE
) default character set utf8mb4
  collate utf8mb4_bin;