create database trab;
use trab;

create table PARTIDA(
	idpartida int not null auto_increment,
    nomejogo varchar(255) not null,
    fornecedor varchar(255) not null,
    horainicio date,
    horafim date,
    numerojogadores int(255),
    primary key(idpartida)
);

create table JOGADOR(
	idjogador int not null auto_increment,
    nomejogador varchar(255),
    niveljogador int(255),
    primary key(idjogador)
);

create table EMPARTIDA(
	idregistro int not null auto_increment,
    idjogador int,
    idpartida int,
    horaentrada date,
    horasaida date,
    primary key (idregistro),
    CONSTRAINT FK_idjogador foreign key(idjogador) references JOGADOR(idjogador),
    CONSTRAINT FK_idpartida foreign key(idpartida) references PARTIDA(idpartida)
);

-- exercicios' --------------------------------------------------------------

DELIMITER $
CREATE PROCEDURE inserir_jogador_partida (
  IN idjogador INT,
  IN idpartida INT
)
BEGIN


  SET @datahoraatual = now();


  INSERT INTO EMPARTIDA (idjogador, idpartida, horaentrada)
  VALUES (idjogador, idpartida, @datahoraatual);

END$
DELIMITER ;

DELIMITER $ 
CREATE PROCEDURE remover_jogador_partida (
  IN idjogador INT,
  IN idpartida INT
)
BEGIN


  SET @datahoraatual = now();


  UPDATE EMPARTIDA
  SET horasaida = @datahoraatual
  WHERE idjogador = idjogador
  AND idpartida = idpartida;

END$

DELIMITER ;


DELIMITER $
CREATE TRIGGER atualizar_numero_jogadores_insert
AFTER INSERT ON EMPARTIDA
FOR EACH ROW
BEGIN
  UPDATE PARTIDA
  SET numerojogadores = numerojogadores + 1
  WHERE idpartida = NEW.idpartida;

END$
DELIMITER ;

DELIMITER $

CREATE TRIGGER atualizar_numero_jogadores_update
AFTER UPDATE ON EMPARTIDA
FOR EACH ROW
BEGIN
    IF NEW.horasaida IS NOT NULL AND OLD.horasaida IS NULL THEN

        UPDATE PARTIDA
        SET numerojogadores = numerojogadores - 1
        WHERE idpartida = NEW.idpartida;
    END IF;
END $

DELIMITER ;




