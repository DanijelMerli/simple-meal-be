CREATE TABLE Meal (
    id_Food_Menu INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(300) NOT NULL,
    type_Menu INT NOT NULL,
    size INT NULL,
    largePrice DOUBLE NOT NULL,
    smallPrice DOUBLE NOT NULL,
    description VARCHAR(300) NOT NULL,
    shouldOrderEarly BOOLEAN NOT NULL
);