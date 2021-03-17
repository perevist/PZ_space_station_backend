CREATE TABLE USERS (
           id INT AUTO_INCREMENT  PRIMARY KEY,
           first_name VARCHAR(45) NOT NULL,
           last_name VARCHAR(45) NOT NULL,
           phone_number VARCHAR(20) NOT NULL,
           email VARCHAR(45) NOT NULL
);

CREATE TABLE RESERVATIONS (
          id INT AUTO_INCREMENT  PRIMARY KEY,
          date DATE NOT NULL,
          worksite_id INT NOT NULL,
          user_id INT NOT NULL,
          FOREIGN KEY (user_id)
              REFERENCES USERS(id)
              ON DELETE CASCADE
);

