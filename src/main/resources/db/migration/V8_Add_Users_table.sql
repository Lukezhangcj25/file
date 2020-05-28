create table Users
(
	ID LONG not null,
	username VARCHAR(50) not null,
	userpassword VARCHAR(50) not null,
	gmt_create BIGINT not null,
	avatar_url VARCHAR(100) not null
);

create unique index Users_ID_uindex
	on Users (ID);

alter table Users
	add constraint Users_pk
		primary key (ID);

