CREATE TABLE usuario (
                         id_usuario SERIAL PRIMARY KEY,
                         email VARCHAR(100) NOT NULL,
                         senha VARCHAR(50) NOT NULL,
                         ativo BOOLEAN NOT NULL
);

CREATE TABLE cliente (
                         id_cliente SERIAL PRIMARY KEY,
                         nome VARCHAR(80) NOT NULL,
                         telefone VARCHAR(20),
                         endereco VARCHAR(255),
                         cpf CHAR(11) UNIQUE,
                         id_usuario INT NOT NULL,
                         FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario) on delete cascade
);

CREATE TABLE dependente (
                            id_dependente SERIAL PRIMARY KEY,
                            nome VARCHAR(255) NOT NULL,
                            cpf CHAR(11) UNIQUE, -- Alterei para CHAR(11), para garantir que seja um campo fixo com 11 caracteres
                            telefone VARCHAR(20),
                            id_cliente INT NOT NULL,
                            FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente) on delete cascade
);


-- Inserir dados na tabela usuario
INSERT INTO usuario (email, senha, ativo) VALUES
                                              ('joao.silva@email.com', 'senha123', true),
                                              ('maria.oliveira@email.com', 'senha456', true),
                                              ('pedro.souza@email.com', 'senha789', false);

INSERT INTO cliente (nome, telefone, endereco, cpf, id_usuario) VALUES
                                                                    ('João Silva', '11987654321', 'Rua A, 123 - São Paulo', '12345678901', 1),
                                                                    ('Maria Oliveira', '11987654322', 'Rua B, 456 - Rio de Janeiro', '98765432100', 2),
                                                                    ('Pedro Souza', '11987654323', 'Rua C, 789 - Belo Horizonte', '11223344556', 3);

-- Inserir dados na tabela dependente
INSERT INTO dependente (nome, cpf, telefone, id_cliente) VALUES
                                                             ('Ana Silva', '98765432101', '11987654320', 4),
                                                             ('Lucas Oliveira', '12345678902', '11987654319', 5),
                                                             ('Carla Souza', '23456789012', '11987654318', 6);

SELECT * FROM usuario

SELECT * FROM cliente

SELECT * FROM dependente

