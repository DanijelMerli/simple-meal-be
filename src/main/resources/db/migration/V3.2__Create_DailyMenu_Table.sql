create table daily_menus (
    soup integer,
    dessert integer,
    fit integer,
    id integer not null primary key auto_increment,
    regular integer,
    weekly_menu integer,
    date_menu datetime(6)
);