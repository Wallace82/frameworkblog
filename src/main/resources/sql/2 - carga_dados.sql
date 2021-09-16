-- Database: blog

-- DROP DATABASE blog;

CREATE DATABASE blog
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'C'
    LC_CTYPE = 'C'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

INSERT INTO usuario(
	 usu_datacadastro, usu_email, usu_nome, usu_senha)
	VALUES ( current_date, 'gomesw@gmail.com', 'Wallace Gomes da Silva', '12345678');