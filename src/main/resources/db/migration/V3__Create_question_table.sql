create table question
(
	id int auto_increment,
	title VARCHAR(50),
	description TEXT,
	comment_count int default 0,
	view_count int default 0,
	like_count int default 0,
	tag VARCHAR(256),
	gmt_create BIGINT,
	creator BIGINT not null,
	gmt_modified BIGINT,
	modifier BIGINT not null,
	constraint question_pk
		primary key (id)
);