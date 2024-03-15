create table regular_meals
(
    id_regular     int not null primary key,
    large_price        float(53),
    small_price        float(53)
);

create table fit_meals
(
    id_fit     int not null primary key,
    price        float(53),
    should_Order_Early bit
);