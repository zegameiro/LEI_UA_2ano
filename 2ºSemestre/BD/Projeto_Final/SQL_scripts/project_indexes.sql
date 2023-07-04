-- Indíces para o projeto final
CREATE INDEX idxEmpName ON EMPRESA_CONSTRUCAO.EMPREGADO(nome_proprio,apelido);
GO

CREATE INDEX idxClientName ON EMPRESA_CONSTRUCAO.CLIENTE(nome_proprio,apelido);
GO