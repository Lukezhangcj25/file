create table users
(
	id long auto_increment primary key not null,
	username varchar(50) not null,
	userpassword varchar(50) not null,
	gmt_create bigint not null,
	avatar_url varchar(100) not null
);

