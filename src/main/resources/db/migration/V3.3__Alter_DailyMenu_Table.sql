alter table daily_menus add constraint foreign key (soup) references extras (id_extra);
alter table daily_menus add constraint foreign key (dessert) references extras (id_extra);

alter table daily_menus add constraint foreign key (regular) references regular_meals (id_regular);
alter table daily_menus add constraint foreign key (fit) references fit_meals (id_fit);

alter table daily_menus add constraint foreign key (weekly_menu) references weekly_menus (id);