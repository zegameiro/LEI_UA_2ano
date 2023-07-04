DROP TABLE IF EXISTS GESTSTOCK.ITEM;
DROP TABLE IF EXISTS GESTSTOCK.ENCOMENDA;
DROP TABLE IF EXISTS GESTSTOCK.PRODUTO;
DROP TABLE IF EXISTS GESTSTOCK.FORNECEDOR;
DROP TABLE IF EXISTS GESTSTOCK.TIPO_FORNECEDOR;
DROP SCHEMA IF EXISTS GESTSTOCK;
GO;

CREATE SCHEMA GESTSTOCK;
GO

CREATE TABLE GESTSTOCK.TIPO_FORNECEDOR (
    codigo      INT             PRIMARY KEY,
    designacao  VARCHAR(50)     NOT NULL
)

CREATE TABLE GESTSTOCK.FORNECEDOR (
    nif         INT             PRIMARY KEY,
    nome        VARCHAR(100)    NOT NULL,
    fax         INT             NOT NULL,
    endereco    VARCHAR(150),
    condpag     INT             NOT NULL,
    tipo        INT             NOT NULL,

    FOREIGN KEY (tipo) REFERENCES GESTSTOCK.TIPO_FORNECEDOR (codigo)
)

CREATE TABLE GESTSTOCK.PRODUTO (
    codigo      INT             PRIMARY KEY,
    nome        VARCHAR(50)     NOT NULL,
    preco       INT             NOT NULL,
    iva         INT             NOT NULL        DEFAULT 23,
    unidades    INT             NOT NULL        DEFAULT 0
)

CREATE TABLE GESTSTOCK.ENCOMENDA (
    numero      INT             PRIMARY KEY,
    data        DATE            NOT NULL,
    fornecedor  INT             NOT NULL,

    FOREIGN KEY (fornecedor) REFERENCES GESTSTOCK.FORNECEDOR (nif)
)

CREATE TABLE GESTSTOCK.ITEM (
    numEnc      INT             NOT NULL,
    codProd     INT             NOT NULL,
    unidades    INT             NOT NULL,

    PRIMARY KEY (numEnc, codProd),
    FOREIGN KEY (numEnc) REFERENCES GESTSTOCK.ENCOMENDA (numero),
    FOREIGN KEY (codProd) REFERENCES GESTSTOCK.PRODUTO (codigo)
)