create table question
(
	id int auto_increment primary key,
	title varchar(50),
	description text,
	comment_count int default 0,
	view_count int default 0,
	like_count int default 0,
	tag varchar(256),
	gmt_create bigint,
	creator bigint not null,
	gmt_modified bigint,
	modifier bigint not null
);