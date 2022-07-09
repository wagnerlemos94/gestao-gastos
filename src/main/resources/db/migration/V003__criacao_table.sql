create table grupo (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB;
alter table categoria add COLUMN grupo_id bigint;