alter table question modify creator bigint not null;
alter table comment modify creator bigint not null;
-- alter table question alter column creator bigint default not null;
-- alter table comment alter column creator bigint default not null;