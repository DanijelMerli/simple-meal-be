CREATE TABLE Meal (
    id_Food_Menu INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(300) NOT NULL,
    type_Menu INT NOT NULL,
    large_Price DOUBLE NOT NULL,
    small_Price DOUBLE NOT NULL,
    description VARCHAR(300) NOT NULL,
    should_Order_Early BOOLEAN NOT NULL
);