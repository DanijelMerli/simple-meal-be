alter table daily_menu add constraint foreign key (soup) references extra (id_extra);
alter table daily_menu add constraint foreign key (dessert) references extra (id_extra);

alter table daily_menu add constraint foreign key (regular) references meal (id_food_menu);
alter table daily_menu add constraint foreign key (fit) references meal (id_food_menu);

alter table daily_menu add constraint foreign key (weekly_menu) references weekly_menu (id_weekly_menu);