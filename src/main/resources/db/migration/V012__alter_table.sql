ALTER TABLE categoria DROP FOREIGN KEY categoria_ibfk_2;
ALTER TABLE categoria ADD FOREIGN KEY (usuario_id) REFERENCES usuario(id);
