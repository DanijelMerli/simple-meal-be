alter table weekly_menu_daily_menu add constraint  foreign key (daily_menu_id_daily_menu) references daily_menu (id_daily_menu);