CREATE TABLE Eleitor(

	id bigint not null primary key,
	nome varchar(30) not null,
	cpf bigint not null

);

CREATE TABLE Chapa(

	id serial not null primary key,
	sigla varchar(15) not null,
	nome varchar(30) not null

);

CREATE TABLE Grupo(

	id serial not null primary key,
	nome varchar(30) not null

);

CREATE TABLE Cargo(

    id serial not null primary key,
    nome varchar(30) not null

);

CREATE TABLE Candidato(

	id bigint not null,
	nome varchar(30) not null,
	cpf varchar(30) not null,

	chapa_id bigint not null,
	cargo_id bigint not null,
	grupo_id bigint not null,

	FOREIGN KEY (chapa_id) REFERENCES Chapa(id),
	FOREIGN KEY (cargo_id) REFERENCES Cargo(id),
	FOREIGN KEY (grupo_id) REFERENCES Grupo(id)

);