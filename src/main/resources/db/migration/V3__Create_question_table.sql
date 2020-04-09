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
	creater int,
	gmt_modified BIGINT,
	modifier int,
	constraint question_pk
		primary key (id)
);