CREATE TABLE user (
    id_User INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_Name VARCHAR(50) NOT NULL,
    last_Name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    password VARCHAR(200) NOT NULL,
    role_id integer
);