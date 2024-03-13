alter table order_items add constraint foreign key (from_order) references orders (id);
alter table order_items add constraint foreign key (orderer) references users (id);
alter table order_items add constraint foreign key (meal) references meals (id);