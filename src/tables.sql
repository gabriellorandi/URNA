CREATE TABLE Eleitor(

	id bigint not null,
	nome varchar(30) not null,
	cpf bigint not null

);

CREATE TABLE Chapa(

	id serial not null,
	sigla varchar(15) not null,
	nome varchar(30) not null

);

CREATE TABLE Grupo(

	id serial not null,
	nome varchar(30) not null

);