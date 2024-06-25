CREATE TABLE IF NOT EXISTS Pauta (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(1000)
    );

CREATE TABLE IF NOT EXISTS Sessao (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      pauta_id BIGINT NOT NULL,
                                      data_inicio TIMESTAMP NOT NULL,
                                      data_fim TIMESTAMP NOT NULL,
                                      FOREIGN KEY (pauta_id) REFERENCES Pauta(id)
    );

CREATE TABLE IF NOT EXISTS Associado (
                                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         cpf VARCHAR(14) NOT NULL UNIQUE,
    nome VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS Voto (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    associado_id BIGINT NOT NULL,
                                    pauta_id BIGINT NOT NULL,
                                    voto BOOLEAN NOT NULL,
                                    FOREIGN KEY (associado_id) REFERENCES Associado(id),
    FOREIGN KEY (pauta_id) REFERENCES Pauta(id),
    UNIQUE (associado_id, pauta_id)
    );
ALTER TABLE Pauta ADD COLUMN votacao_aberta BOOLEAN;
