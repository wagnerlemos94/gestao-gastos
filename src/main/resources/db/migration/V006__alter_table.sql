alter table grupo add COLUMN usuario_id bigint;
ALTER TABLE grupo ADD FOREIGN KEY (usuario_id) REFERENCES usuario(id);