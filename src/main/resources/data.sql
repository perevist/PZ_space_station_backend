DROP ALL OBJECTS;

-- create tables --
CREATE TABLE rooms (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(45),
    floor INT NOT NULL,
    number_of_worksites BIGINT NOT NULL,
    dimension_x BIGINT NOT NULL,
    dimension_y BIGINT NOT NULL
);

CREATE TABLE worksites (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    room_id BIGINT NOT NULL,
    worksite_in_room_id BIGINT NOT NULL,
    coordinate_x BIGINT NOT NULL,
    coordinate_y BIGINT NOT NULL,

    FOREIGN KEY (room_id)
        REFERENCES rooms(id)
        ON DELETE CASCADE
);

CREATE TABLE reservations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    worksite_id BIGINT NOT NULL,
    reservation_maker_id VARCHAR(255) NOT NULL,
    owner_id VARCHAR(255) NOT NULL,

    FOREIGN KEY (worksite_id)
        REFERENCES worksites(id)
        ON DELETE CASCADE
);

-- insert data --
INSERT INTO rooms (name, floor, number_of_worksites, dimension_x, dimension_y)
VALUES
('Pokój 1', 1, 2, 2, 1),
('Pokój 2', 1, 4, 2, 2),
('Pokój 3', 1, 3, 1, 4),
('Pokój 4', 1, 6, 3, 2),
('Pokój 5', 2, 6, 2, 3),
('Pokój 6', 2, 3, 1, 3),
('Pokój 7', 2, 2, 3, 1);

INSERT INTO worksites (room_id, worksite_in_room_id, coordinate_x, coordinate_y)
VALUES
(1, 1, 1, 1), (1, 2, 2, 1),
(2, 1, 1, 1), (2, 2, 1, 2), (2, 3, 2, 1), (2, 4, 2, 2),
(3, 1, 1, 1), (3, 2, 1, 2), (3, 3, 1, 3),
(4, 1, 1, 1), (4, 2, 1, 2), (4, 3, 2, 1), (4, 4, 2, 2), (4, 5, 3, 1), (4, 6, 3, 2),
(5, 1, 1, 1), (5, 2, 1, 2), (5, 3, 2, 1), (5, 4, 2, 2), (5, 5, 3, 1), (5, 6, 3, 2),
(6, 1, 1, 1), (6, 2, 1, 2), (6, 3, 1, 3),
(7, 1, 1, 1), (7, 2, 2, 1);

INSERT INTO reservations (start_date, end_date, worksite_id, reservation_maker_id, owner_id)
VALUES
('2021-02-20', '2021-02-22', 1, 'e09f65d2-3186-4fee-9d4a-3d0dca09e072', 'e65b0ee2-11a1-4f1d-9bdd-7989a5305bb7'),
('2021-02-20', '2021-02-22', 2, 'e65b0ee2-11a1-4f1d-9bdd-7989a5305bb7', 'e65b0ee2-11a1-4f1d-9bdd-7989a5305bb7'),
('2021-02-20', '2021-02-22', 3, 'e65b0ee2-11a1-4f1d-9bdd-7989a5305bb7', 'e09f65d2-3186-4fee-9d4a-3d0dca09e072'),
('2021-03-21', '2021-03-24', 2, 'e65b0ee2-11a1-4f1d-9bdd-7989a5305bb7', 'e65b0ee2-11a1-4f1d-9bdd-7989a5305bb7'),
('2021-03-20', '2021-03-21', 1, 'e09f65d2-3186-4fee-9d4a-3d0dca09e072', 'e09f65d2-3186-4fee-9d4a-3d0dca09e072'),
('2021-03-26', '2021-03-30', 1, 'e09f65d2-3186-4fee-9d4a-3d0dca09e072', 'e09f65d2-3186-4fee-9d4a-3d0dca09e072'),
('2021-03-27', '2021-03-29', 2, 'e09f65d2-3186-4fee-9d4a-3d0dca09e072', 'e65b0ee2-11a1-4f1d-9bdd-7989a5305bb7');






