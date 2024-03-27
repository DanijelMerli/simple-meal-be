create table  image (
    id INT AUTO_INCREMENT PRIMARY KEY,
    weeky_menus_id INT NOT NULL UNIQUE,
    data BLOB,
    FOREIGN KEY (weeky_menus_id) REFERENCES weekly_menus(id)
);
