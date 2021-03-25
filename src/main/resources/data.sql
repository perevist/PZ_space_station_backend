DROP ALL OBJECTS;

CREATE TABLE accounts (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    account_non_expired BOOLEAN NOT NULL,
    account_non_locked BOOLEAN NOT NULL,
    credentials_non_expired BOOLEAN NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE authorities (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(255) NOT NULL
);

CREATE TABLE account_authority (
	account_id BIGINT NOT NULL,
    authority_id BIGINT NOT NULL,

    PRIMARY KEY(account_id, authority_id),

    FOREIGN KEY (account_id)
        REFERENCES accounts (id)
        ON DELETE CASCADE,

    FOREIGN KEY (authority_id)
        REFERENCES authorities (id)
);

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    email VARCHAR(45) NOT NULL,
    account_id BIGINT NOT NULL,

    FOREIGN KEY (account_id)
        REFERENCES accounts (id)
        ON DELETE CASCADE
);

CREATE TABLE reservations (
    id BIGINT AUTO_INCREMENT  PRIMARY KEY,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    worksite_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,

    FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE
);

INSERT INTO accounts (username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES
('nowak1', '$2a$10$8KtMtOzTh18CQxGSFAo/9uuOYBsj3JObcIlzstM2LiOD1qfPWZw/G', TRUE, TRUE, TRUE, TRUE),
('kowalski1', '$2a$10$8KtMtOzTh18CQxGSFAo/9uuOYBsj3JObcIlzstM2LiOD1qfPWZw/G', TRUE, TRUE, TRUE, TRUE),
('biernacka1', '$2a$10$8KtMtOzTh18CQxGSFAo/9uuOYBsj3JObcIlzstM2LiOD1qfPWZw/G', TRUE, TRUE, TRUE, TRUE),
('kowal1', '$2a$10$8KtMtOzTh18CQxGSFAo/9uuOYBsj3JObcIlzstM2LiOD1qfPWZw/G', TRUE, TRUE, TRUE, TRUE);

INSERT INTO authorities (name)
VALUES
('ROLE_USER'),
('ROLE_ADMIN');

INSERT INTO account_authority (account_id, authority_id)
VALUES
(1,1),
(2,1),
(3,1),
(4,1);

INSERT INTO users (first_name, last_name, phone_number, email, account_id)
VALUES
('Adam', 'Nowak', 505334111, 'adamnowak@wp.pl', 1),
('Adam', 'Kowalski', 505123111, 'adamkowalski@wp.pl', 2),
('Ola', 'Biernacka', 888123111, 'olabiernackai@wp.pl', 3),
('Michal', 'Kowal', 905123111, 'mk@wp.pl', 4);

INSERT INTO reservations (start_date, end_date, worksite_id, user_id)
VALUES
('2021-03-21', '2021-03-24', 2, 1),
('2021-03-20', '2021-03-21', 1, 2),
('2021-03-25', '2021-03-27', 2, 4),
('2021-03-22', '2021-03-25', 3, 3);