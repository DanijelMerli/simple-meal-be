create table order_items (
     id integer not null primary key auto_increment,
     orderer integer not null ,
     meal integer not null ,
     regular_Meal_Size integer not null,
     from_order integer,
     meal_count integer
);