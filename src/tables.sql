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

CREATE TABLE Mesario(

  id serial not null primary key  ,
  login varchar(30) unique not null,
  senha varchar(50) not null,
  admin boolean not null,
  nome varchar(30),
  cpf varchar(11)


);

INSERT INTO Mesario(login,senha,admin) VALUES ('admin','1234',true);

CREATE TABLE Candidato(

  id bigint not null primary key ,
  nome varchar(30) not null,
  cpf varchar(30) not null,

  chapa_id bigint not null,
  cargo_id bigint not null,
  grupo_id bigint not null,

  FOREIGN KEY (chapa_id) REFERENCES Chapa(id),
FOREIGN KEY (cargo_id) REFERENCES Cargo(id),
FOREIGN KEY (grupo_id) REFERENCES Grupo(id)

);

CREATE TABLE Secao(

  id serial not null primary key ,
  logradouro varchar(50) not null,
  numero bigint not null,

  mesario_id bigint not null,

  FOREIGN KEY (mesario_id) REFERENCES Mesario(id)
);

CREATE TABLE Eleicao(

  id serial not null primary key ,
  dia date not null

);

CREATE TABLE Eleicao_Candidato(

    candidato_id bigint not null,
    eleicao_id bigint not null,

    PRIMARY KEY (eleicao_id,candidato_id),
FOREIGN KEY (candidato_id) REFERENCES Candidato(id),
FOREIGN KEY (eleicao_id) REFERENCES Eleicao(id)

);

CREATE TABLE Eleicao_Secao(

    secao_id bigint not null,
    eleicao_id bigint not null,

    PRIMARY KEY (eleicao_id,secao_id),
FOREIGN KEY (secao_id) REFERENCES Secao(id),
FOREIGN KEY (eleicao_id) REFERENCES Eleicao(id)

);

CREATE TABLE Secao_Eleitor(

    secao_id bigint not null,
    eleitor_id bigint not null,

    PRIMARY KEY (secao_id,eleitor_id),
FOREIGN KEY (secao_id) REFERENCES Secao(id),
FOREIGN KEY (eleitor_id) REFERENCES Eleitor(id)

);

CREATE TABLE Voto(

  id serial not null,
  data date not null,

  candidato_id bigint,
  eleicao_id bigint not null,

  FOREIGN KEY (candidato_id) REFERENCES Candidato(id),
FOREIGN KEY (eleicao_id) REFERENCES Eleicao(id)

);

CREATE TABLE Comprovante(

  id serial not null,

  eleicao_id bigint not null,
  eleitor_id bigint not null,

  FOREIGN KEY (eleitor_id) REFERENCES Eleitor(id),
FOREIGN KEY (eleicao_id) REFERENCES Eleicao(id)

);