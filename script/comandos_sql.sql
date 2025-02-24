/* =============================================== 
COMANDOS DDL (Data Definition Language)
    Comandos para definição de dados:
	- CREATE: criar uma estrutura
    - ALTER: alterar uma estrutura
    - DROP: excluir uma estrutura
    - TRUNCATE: recriar uma estrutura
    - RENAME: renomear uma estrutura

COMANDOS DML (Data Manipulation Language)
    Comandos para manipulação de dados:
	- INSERT: inserir dados em uma tabela
    - UPDATE: atualizar dados de uma tabela
    - SELECT: consultar dados de uma tabela
    - DELETE: remover os dados de uma tabela
=============================================== */

-- Comando para criar uma BD/schema
CREATE DATABASE unifacear;

-- Comando para se conectar ao BD/schema
USE unifacear;

-- Comando para criar uma tabela
CREATE TABLE TB_Usuario (
	id_usuario int not null,
	nome varchar(100) not null,
    cpf char(11) not null,
    CONSTRAINT PK_Usuario
		PRIMARY KEY (id_usuario),
	CONSTRAINT UN_Usuario
		UNIQUE (cpf)
);

-- Comando para adicionar uma nova coluna à tabela
ALTER TABLE TB_Usuario ADD sexo char(1);

-- Comando para remover uma coluna da tabela
ALTER TABLE TB_Usuario DROP COLUMN sexo;

-- Comando para excluir uma tabela
DROP TABLE TB_Usuario;

-- Comando para inserir um registro na tabela
INSERT INTO TB_Usuario (
	id_usuario, nome, cpf
) VALUES (
	1, 'Rafael', '00000000000'
);

-- Comando para inserir um registro na tabela
INSERT INTO TB_Usuario (
	id_usuario, nome, cpf
) VALUES (
	2, 'Ana', '11111111111'
);

-- Comandos para consultar os dados de uma tabela
SELECT id_usuario, nome, cpf FROM TB_Usuario;
SELECT nome FROM TB_Usuario ORDER BY nome asc;
SELECT nome FROM TB_Usuario ORDER BY nome desc;
SELECT * FROM TB_Usuario;

-- Comando para reconstruir a tabela (algo semelhante a dar um drop e create na tabela)
TRUNCATE TABLE TB_Usuario;

-- Comando para adicionar uma chave primária na coluna id_usuario da tabela TB_Usuario 
ALTER TABLE TB_Usuario
	ADD CONSTRAINT PK_Usuario
		PRIMARY KEY (id_usuario);

-- Comando para adicionar uma chave candidata na tabela TB_Usuario (a chave candidata funciona de forma semelhante a uma PK)       
ALTER TABLE TB_Usuario
	ADD CONSTRAINT UN_Usuario
		UNIQUE (cpf);

-- Comando para alterar a estrutura da coluna Nome para obrigatória (not null)
ALTER TABLE TB_Usuario
	MODIFY nome varchar(110) not null;

-- Comando para alterar a estrutura da coluna CPF para obrigatória (not null)
ALTER TABLE TB_Usuario
	MODIFY cpf char(11) not null;

-- Comando para desabilitar o modo de segurança do MySQL
SET SQL_SAFE_UPDATES = 0;

-- Comando para atualizar o CPF e o nome do registro da tabela TB_Usuario cujo id_usuario é igual a 1
UPDATE TB_Usuario
SET cpf = '12345678901', nome = 'Rafael Veiga de Moraes'
WHERE id_usuario = 1;

-- Comando para remover o registro da tabela TB_Usuario cujo id_usuario é igual a 1
DELETE FROM TB_Usuario
WHERE id_usuario = 1;

-- Comando para remover os registros da tabela TB_Usuario cujo nome seja Rafael E que o CPF seja 1231231231
DELETE FROM TB_Usuario
WHERE nome = 'Rafael' AND cpf = '1231231231';

-- Comando para remover os registros da tabela TB_Usuario cujo nome seja Ana OU que o CPF seja 1231231231
DELETE FROM TB_Usuario
WHERE nome = 'Ana' OR cpf = '1231231231';

-- Comando para remover os registros da tabela TB_Usuario cujo id_usuario seja igual a 1, 2 ou 3
DELETE FROM TB_Usuario
WHERE id_usuario IN (1,2,3)



