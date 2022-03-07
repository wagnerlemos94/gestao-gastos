insert into usuario (id, nome, email, login, senha) values (1,'wagner cupertino lemos', 'wagner@email.com','12345678901', '123456');
insert into usuario (id, nome, email, login, senha) values (2,'aline cupertino lemos', 'aline@email.com','10987654321', '123456');

insert into categoria (id, nome) values (1,'casa');
insert into categoria (id, nome) values (2,'carro');
insert into categoria (id, nome) values (3,'moto');
insert into categoria (id, nome) values (4,'catão de crédito');
insert into categoria (id, nome) values (5,'Salário');

insert into lancamento (id, tipo, descricao, valor, mes, categoria_id, usuario_id) values (1,1,'4 Pneus',1680.00,1,2,1);
insert into lancamento (id, tipo, descricao, valor, mes, categoria_id, usuario_id) values (2,0,'Salário',2200.00,2,5,1);

