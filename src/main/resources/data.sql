INSERT INTO countries (name) VALUES ('USA');
INSERT INTO countries (name) VALUES ('France');
INSERT INTO countries (name) VALUES ('Brazil');
INSERT INTO countries (name) VALUES ('Italy');
INSERT INTO countries (name) VALUES ('Canada');

insert into tipo_movimiento(id, nombre, descripcion) values(1, 'debito', 'movimiento debito');
insert into tipo_movimiento(id, nombre, descripcion) values(2, 'credito', 'movimiento credito');

insert into movimiento (fecha_mov, descripcion_mov, documento, info_adicional, tipo_mov, valor_mov) values ('2024-01-01', 'descripcion deb', 'abc123', 'info', 1, -700);
insert into movimiento (fecha_mov, descripcion_mov, documento, info_adicional, tipo_mov, valor_mov) values ('2024-01-10', 'descripcion cred', 'abc123', 'info', 1, -700);


  