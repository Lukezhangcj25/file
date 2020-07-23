create table comment
(
	id int auto_increment primary key,
	parant_id bigint not null,
	type int not null,
	creator bigint not null,
	gmt_create bigint not null,
	gmt_modified bigint,
	like_count bigint default 0
);