INSERT INTO usuario (nome, email, senha) VALUES ('wagner lemos', 'wagner@email.com', '$2a$10$NYFZ/8WaQ3Qb6FCs.00jce4nxX9w7AkgWVsQCG6oUwTAcZqP9Flqu');
INSERT INTO usuario (nome, email, senha) VALUES ('Leia Red', 'leia@gmail.com', '$2a$10$NYFZ/8WaQ3Qb6FCs.00jce4nxX9w7AkgWVsQCG6oUwTAcZqP9Flqu');

INSERT INTO role (role_nome) VALUES ('ROLE_OPERATOR');
INSERT INTO role (role_nome) VALUES ('ROLE_ADMIN');

INSERT INTO usuario_roles (usuario_id, roles_id) VALUES (1, 1);
INSERT INTO usuario_roles (usuario_id, roles_id) VALUES (2, 1);
INSERT INTO usuario_roles (usuario_id, roles_id) VALUES (2, 2);

insert into categoria (id, nome) values (1,'casa');
insert into categoria (id, nome) values (2,'carro');
insert into categoria (id, nome) values (3,'moto');
insert into categoria (id, nome) values (4,'catão de crédito');
insert into categoria (id, nome) values (5,'Salário');

insert into lancamento (id, tipo, descricao, valor, mes, categoria_id, usuario_id) values (1,1,'4 Pneus',1680.00,1,2,1);
insert into lancamento (id, tipo, descricao, valor, mes, categoria_id, usuario_id) values (2,0,'Salário',2200.00,2,5,1);

