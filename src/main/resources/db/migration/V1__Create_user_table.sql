create table user
(
	id VARCHAR(36) auto_increment ,
	account_id VARCHAR(100),
	name VARCHAR(36),
	token VARCHAR(50),
	gmt_create BIGINT,
	gmt_modified BIGINT,
	constraint user_pk
		primary key (id)
);