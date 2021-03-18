DROP TABLE IF EXISTS RESERVATIONS;
DROP TABLE IF EXISTS USERS;

CREATE TABLE USERS (
    id BIGINT AUTO_INCREMENT  PRIMARY KEY,
    first_name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
    email VARCHAR(45) NOT NULL
);

CREATE TABLE RESERVATIONS (
    id BIGINT AUTO_INCREMENT  PRIMARY KEY,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    worksite_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,

    FOREIGN KEY (user_id)
        REFERENCES USERS(id)
        ON DELETE CASCADE
);

INSERT INTO USERS (first_name, last_name, phone_number, email)
VALUES
('Adam', 'Nowak', 505334111, 'adamnowak@wp.pl'),
('Adam', 'Kowalski', 505123111, 'adamkowalski@wp.pl'),
('Ola', 'Biernacka', 888123111, 'olabiernackai@wp.pl'),
('Michal', 'Kowal', 905123111, 'mk@wp.pl');

INSERT INTO RESERVATIONS (start_date, end_date, worksite_id, user_id)
VALUES
('2021-03-21', '2021-03-24', 2, 1),
('2021-03-20', '2021-03-21', 1, 2),
('2021-03-25', '2021-03-27', 2, 4),
('2021-03-22', '2021-03-25', 3, 3);


