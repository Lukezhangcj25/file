create table ad
(
    id bigint auto_increment primary  key not null,
    title varchar(256) default null,
    url varchar(512) default null,
    image varchar(256) default null,
    gmt_start bigint,
    gmt_end bigint,
    gmt_create bigint,
    gmt_modified bigint,
    status int,
    pos varchar(100) default null
)default character set = utf8;