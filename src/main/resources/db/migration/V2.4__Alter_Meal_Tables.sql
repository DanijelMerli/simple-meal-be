alter table extras add constraint foreign key (extra_id) references meals (id);
alter table main_courses add constraint foreign key (main_course_id) references meals (id);