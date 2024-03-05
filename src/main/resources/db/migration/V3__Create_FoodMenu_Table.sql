CREATE TABLE Food_Menu (
    id_Food_Menu INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    type_Menu INT NOT NULL,
    size INT NULL,
    price DOUBLE NOT NULL,
    description VARCHAR(300) NOT NULL,
    is_Special BOOLEAN NOT NULL
);