alter table daily_menus add constraint foreign key (soup) references extras (extra_id);
alter table daily_menus add constraint foreign key (dessert) references extras (extra_id);

alter table daily_menus add constraint foreign key (regular) references main_courses (main_course_id);
alter table daily_menus add constraint foreign key (fit) references main_courses (main_course_id);

alter table daily_menus add constraint foreign key (weekly_menu) references weekly_menus (id);