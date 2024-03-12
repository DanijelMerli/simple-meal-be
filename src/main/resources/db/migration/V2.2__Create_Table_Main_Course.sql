create table main_courses
(
    main_course_id     int not null primary key,
    large_price        float(53),
    should_Order_Early bit,
    small_price        float(53),
    type_menu          int not null
);