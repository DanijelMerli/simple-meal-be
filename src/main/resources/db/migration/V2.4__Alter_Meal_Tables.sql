alter table extras add constraint foreign key (id_extra) references meals (id);
alter table regular_meals add constraint foreign key (id_regular) references meals (id);
alter table fit_meals add constraint foreign key (id_fit) references meals (id);
