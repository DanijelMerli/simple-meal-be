drop table daily_menus;
truncate extras;

create table daily_menus (
             soup integer,
             dessert integer,
             fit integer,
             id integer not null primary key auto_increment,
             regular integer,
             weekly_menu integer,
             date_menu datetime(6)
);

INSERT INTO extras (id_extra, extra_type, price) VALUES
                     (6, 0, 2.0),
                     (7, 1, 1.5),
                     (8, 0, 2.0),
                     (9, 1, 3.0),
                     (10, 0, 3.5);

INSERT INTO daily_menus (fit, dessert, regular, weekly_menu, date_menu)
VALUES (2, 6, 5, 1, '2024-03-11 00:00:00');
INSERT INTO daily_menus (soup, dessert, fit, regular, weekly_menu, date_menu)
VALUES (7, 8, 2, 1, 1, '2024-03-12 00:00:00');
INSERT INTO daily_menus (soup, fit, regular, weekly_menu, date_menu)
VALUES (9, 4, 3, 1, '2024-03-13 00:00:00');
INSERT INTO daily_menus (soup, dessert, regular, weekly_menu, date_menu)
VALUES (9, 10, 1, 1, '2024-03-14 00:00:00');
INSERT INTO daily_menus (soup, fit, dessert, weekly_menu, date_menu)
VALUES (7, 4, 8, 1, '2024-03-15 00:00:00');

INSERT INTO daily_menus (fit, dessert, regular, weekly_menu, date_menu)
VALUES (2, 6, 5, 2, '2024-03-18 00:00:00');
INSERT INTO daily_menus (soup, dessert, fit, regular, weekly_menu, date_menu)
VALUES (7, 8, 2, 1, 2, '2024-03-19 00:00:00');
INSERT INTO daily_menus (soup, fit, regular, weekly_menu, date_menu)
VALUES (9, 4, 3, 2, '2024-03-20 00:00:00');
INSERT INTO daily_menus (soup, dessert, regular, weekly_menu, date_menu)
VALUES (9, 10, 1, 2, '2024-03-21 00:00:00');
INSERT INTO daily_menus (soup, fit, dessert, weekly_menu, date_menu)
VALUES (7, 4, 8, 2, '2024-03-22 00:00:00');

INSERT INTO daily_menus (fit, dessert, regular, weekly_menu, date_menu)
VALUES (2, 6, 5, 3, '2024-03-25 00:00:00');
INSERT INTO daily_menus (soup, dessert, fit, regular, weekly_menu, date_menu)
VALUES (7, 8, 2, 1, 3, '2024-03-26 00:00:00');
INSERT INTO daily_menus (soup, fit, regular, weekly_menu, date_menu)
VALUES (9, 4, 3, 3, '2024-03-27 00:00:00');
INSERT INTO daily_menus (soup, dessert, regular, weekly_menu, date_menu)
VALUES (9, 10, 1, 3, '2024-03-28 00:00:00');
INSERT INTO daily_menus (soup, fit, dessert, weekly_menu, date_menu)
VALUES (7, 4, 8, 3, '2024-03-29 00:00:00');