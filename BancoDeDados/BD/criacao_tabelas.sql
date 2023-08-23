CREATE DATABASE IF NOT EXISTS db_corretora_investimentos;

USE db_corretora_investimentos;

CREATE TABLE IF NOT EXISTS cliente (
	cpf CHAR (11) PRIMARY KEY NOT NULL,
    rg VARCHAR (14),
	nome VARCHAR (100),
    data_nasc DATE,
    endereco VARCHAR (200),
    cep CHAR(8),
    telefone VARCHAR (14),
    email VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS carteira (
	id INTEGER auto_increment PRIMARY KEY,
	data_criacao DATE,
    cpf_cliente CHAR (11),
    id_tipo INTEGER
);

CREATE TABLE IF NOT EXISTS tipo_carteira (
	id INTEGER auto_increment PRIMARY KEY,
    descricao VARCHAR (50)
);

CREATE TABLE IF NOT EXISTS carteira_acao (
	id INTEGER auto_increment PRIMARY KEY,
    id_carteira INTEGER,
    id_acao INTEGER,
    num_acoes INTEGER
);

CREATE TABLE IF NOT EXISTS acao (
	id INTEGER auto_increment PRIMARY KEY,
    codigo VARCHAR (5),
    id_tipo INTEGER,
    cnpj_empresa VARCHAR (14)
);

CREATE TABLE IF NOT EXISTS tipo_acao (
	id INTEGER auto_increment PRIMARY KEY,
    descricao VARCHAR (50)
);

CREATE TABLE IF NOT EXISTS historico_acao (
	id INTEGER auto_increment PRIMARY KEY,
    id_acao INTEGER,
    marca_temporal DATETIME,
    preco NUMERIC(10,2)
);

CREATE TABLE IF NOT EXISTS empresa (
	cnpj VARCHAR (14) PRIMARY KEY NOT NULL,
    nome VARCHAR(100),
    receita NUMERIC(14,2),
    lucro NUMERIC(14,2),
    roic NUMERIC(7,4)
);