CREATE SCHEMA EMPRESA_CONSTRUCAO;
GO;

CREATE TABLE EMPRESA_CONSTRUCAO.DEPARTAMENTO (
    id              INT             NOT NULL        PRIMARY KEY,
    nome            VARCHAR(100)
);

CREATE TABLE EMPRESA_CONSTRUCAO.EMPREGADO (
    nif                 INT             NOT NULL        PRIMARY KEY,
    nome_proprio        VARCHAR(25)     NOT NULL,
    apelido             VARCHAR(25)     NOT NULL,
    email               VARCHAR(50)     NOT NULL,
    telefone            INT             NOT NULL,
    morada              VARCHAR(200),
    genero              CHAR,
    data_nascimento     DATE,
    salario             DECIMAL(10,2)   DEFAULT 0,
    id_departamento     INT,

    FOREIGN KEY (id_departamento) REFERENCES EMPRESA_CONSTRUCAO.DEPARTAMENTO(id)
        ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE EMPRESA_CONSTRUCAO.CEO (
    nif_empregado       INT             PRIMARY KEY,

    FOREIGN KEY (nif_empregado) REFERENCES EMPRESA_CONSTRUCAO.EMPREGADO(nif)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE EMPRESA_CONSTRUCAO.SERVICO (
    id_S                INT             NOT NULL        PRIMARY KEY,
    categoria           VARCHAR(100)     NOT NULL,
    id_departamento     INT,

    FOREIGN KEY (id_departamento) REFERENCES EMPRESA_CONSTRUCAO.DEPARTAMENTO(id)
        ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE EMPRESA_CONSTRUCAO.CLIENTE (
    nif                 INT             NOT NULL        PRIMARY KEY,
    nome_proprio        VARCHAR(25)     NOT NULL,
    apelido             VARCHAR(25)     NOT NULL,
    email               VARCHAR(100)     NOT NULL,
    telefone            INT             NOT NULL,
    morada              VARCHAR(200)
);

CREATE TABLE EMPRESA_CONSTRUCAO.OBRA (
    id                  INT             NOT NULL        PRIMARY KEY,
    localizacao         VARCHAR(200)     NOT NULL,
    data_inicio         DATE            NOT NULL,
    data_fim            DATE,
    nif_cliente         INT,

    FOREIGN KEY (nif_cliente) REFERENCES EMPRESA_CONSTRUCAO.CLIENTE(nif)
        ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE EMPRESA_CONSTRUCAO.REL_OBRA_SERVICO (
    id_obra             INT,
    id_servico          INT,

    PRIMARY KEY (id_obra,id_servico),

    FOREIGN KEY (id_obra) REFERENCES EMPRESA_CONSTRUCAO.OBRA(id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_servico) REFERENCES EMPRESA_CONSTRUCAO.SERVICO(id_S)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE EMPRESA_CONSTRUCAO.REL_OBRA_EMPREGADO (
    id_obra             INT,
    nif_empregado       INT,
    dia                 DATE            NOT NULL,
    horas               TIME            NOT NULL,

    PRIMARY KEY (id_obra, nif_empregado),

    FOREIGN KEY (id_obra) REFERENCES EMPRESA_CONSTRUCAO.OBRA(id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (nif_empregado) REFERENCES EMPRESA_CONSTRUCAO.EMPREGADO(nif)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE EMPRESA_CONSTRUCAO.MATERIAL_CONSTRUCAO (
    id                  INT             NOT NULL        PRIMARY KEY,
    categoria           VARCHAR(50)     NOT NULL,
    nome                VARCHAR(60)     NOT NULL,
    unidades_armazem    INT             DEFAULT 0
);

CREATE TABLE EMPRESA_CONSTRUCAO.FORNECEDOR (
    nif                 INT             NOT NULL        PRIMARY KEY,
    nome                VARCHAR(40)     NOT NULL,
    telefone            INT             NOT NULL,
    email               VARCHAR(70)     NOT NULL,
    morada              VARCHAR(300),
);

CREATE TABLE EMPRESA_CONSTRUCAO.ENCOMENDA (
    id                  INT             NOT NULL        PRIMARY KEY,
    data                DATE            NOT NULL,
    nif_fornecedor      INT,
    id_obra             INT,

    FOREIGN KEY (nif_fornecedor) REFERENCES EMPRESA_CONSTRUCAO.FORNECEDOR(nif)
        ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (id_obra) REFERENCES EMPRESA_CONSTRUCAO.OBRA(id)
        ON DELETE SET NULL ON UPDATE CASCADE
)

CREATE TABLE EMPRESA_CONSTRUCAO.REL_ENCOMENDA_MATERIAL (
    id_encomenda        INT,
    id_material         INT,
    custo               DECIMAL(10,2)   NOT NULL,

    PRIMARY KEY(id_encomenda, id_material),

    FOREIGN KEY (id_encomenda) REFERENCES EMPRESA_CONSTRUCAO.ENCOMENDA(id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_material) REFERENCES EMPRESA_CONSTRUCAO.MATERIAL_CONSTRUCAO(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE EMPRESA_CONSTRUCAO.REL_OBRA_MATERIAL (
    id_obra             INT,
    id_material         INT,

    PRIMARY KEY (id_obra, id_material),

    FOREIGN KEY (id_obra) REFERENCES EMPRESA_CONSTRUCAO.OBRA(id)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_material) REFERENCES EMPRESA_CONSTRUCAO.MATERIAL_CONSTRUCAO(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);
