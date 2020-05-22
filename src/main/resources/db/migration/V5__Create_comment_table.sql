create table comment
(
	id BIGINT auto_increment,
	parant_id BIGINT not null,
	type int not null,
	creator BIGINT not null,
	gmt_create BIGINT not null,
	gmt_modified BIGINT,
	like_count BIGINT default 0,
	constraint comment_pk
		primary key (id)
);