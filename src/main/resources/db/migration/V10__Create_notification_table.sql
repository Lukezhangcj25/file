create table notification
(
	id BIGINT auto_increment,
	notifier BIGINT not null,
	receiver BIGINT not null,
	outerid BIGINT not null,
	type int not null,
	gmt_create BIGINT not null,
	status int default 0 not null
);

create unique index notification_id_uindex
	on notification (id);

alter table notification
	add constraint notification_pk
		primary key (id);
