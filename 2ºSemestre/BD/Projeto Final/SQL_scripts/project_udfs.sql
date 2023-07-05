
-- Filtrar os departamentos por id ao ser dado como argumento um id do departamento
GO
CREATE FUNCTION getDepartamentoById (@dep_id INT) RETURNS TABLE
AS
    RETURN (
        SELECT DEP.nome AS Departamento_nome,
               E.nome_proprio AS Emp_nome_proprio, E.apelido AS Emp_apelido, E.email AS Emp_email,
               S.id_S AS Serv_id, S.categoria AS Serv_categoria
        FROM EMPRESA_CONSTRUCAO.DEPARTAMENTO AS DEP
        JOIN EMPRESA_CONSTRUCAO.EMPREGADO AS E ON DEP.id = E.id_departamento
        JOIN EMPRESA_CONSTRUCAO.SERVICO AS S ON E.id_departamento = S.id_departamento
        WHERE id = @dep_id
    )
GO

-- Teste
SELECT * FROM getDepartamentoById(20041)



-- Filtrar empregados pelo primeiro nome e apelido
GO
CREATE FUNCTION getEmpregadoByName(@emp_name VARCHAR(40)) RETURNS TABLE
AS
    RETURN (
        SELECT *
        FROM EMPRESA_CONSTRUCAO.EMPREGADO
        WHERE (@emp_name IS NULL OR (nome_proprio + ' ' + apelido) LIKE @emp_name + '%')
    );
GO

-- Teste
SELECT * FROM getEmpregadoByName('João Silva')


-- Filtrar os empregados por género/data de nascimento/salário
GO
CREATE FUNCTION getEmpregadoBySexBirthSalary(@sex CHAR, @birth_date DATE, @salary DECIMAL(10,2)) RETURNS TABLE
AS
    RETURN (
        SELECT * FROM EMPRESA_CONSTRUCAO.EMPREGADO
        WHERE (@sex IS NULL OR genero = @sex) AND
        (@birth_date IS NULL OR data_nascimento >= @birth_date) AND
        (@salary IS NULL OR salario >= @salary)
    )
GO

-- Teste
SELECT * FROM getEmpregadoBySexBirthSalary('M' , '1955-08-11', 50000)



-- Filtrar clientes pelo primeiro nome e apelido
GO
CREATE FUNCTION getClientByName (@client_name VARCHAR(40)) RETURNS TABLE
AS
    RETURN (
        SELECT * FROM EMPRESA_CONSTRUCAO.CLIENTE
        WHERE (@client_name IS NULL OR (nome_proprio + ' ' + apelido) LIKE @client_name + '%')
    );
GO

-- Teste
SELECT * FROM getClientByName('Katelyn Solis')



-- Filtrar as obras por nif do cliente
GO
CREATE FUNCTION getObraByClientNif(@client_nif INT) RETURNS TABLE
AS
    RETURN (
        SELECT * FROM EMPRESA_CONSTRUCAO.OBRA
        WHERE nif_cliente = @client_nif
    );
GO

-- Teste
SELECT * FROM getObraByClientNif(111356719)



-- Filtrar as obras por data de início e/ou de fim
GO
CREATE FUNCTION getObraByDate(@start_date DATE, @end_date DATE) RETURNS TABLE
AS
    RETURN (
        SELECT * FROM EMPRESA_CONSTRUCAO.OBRA
        WHERE (@start_date IS NULL OR data_inicio >= @start_date)
        AND (@end_date IS NULL OR data_fim <= @end_date)
    );
GO

-- Teste
SELECT * FROM getObraByDate('2023-05-10', '2023-05-28')



-- Filtrar as obras e serviços por id da obra
GO
CREATE FUNCTION getObraServicoByObra(@obra_id INT) RETURNS TABLE
AS
    RETURN (
        SELECT REL_O_S.id_obra, S.id_S AS id_servico, S.categoria, S.id_departamento, DEP.nome
        FROM EMPRESA_CONSTRUCAO.REL_OBRA_SERVICO AS REL_O_S
        JOIN EMPRESA_CONSTRUCAO.SERVICO AS S ON REL_O_S.id_servico = S.id_S
        JOIN EMPRESA_CONSTRUCAO.DEPARTAMENTO AS DEP ON S.id_departamento = DEP.id
        WHERE id_obra = @obra_id
    );
GO

-- Teste
SELECT * FROM getObraServicoByObra(19940064)



-- Filtrar as obras e serviços por id do serviço
GO
CREATE FUNCTION getObraServicoByServico(@servico_id INT) RETURNS TABLE
AS
    RETURN (
        SELECT REL_O_S.id_servico, Ob.id AS id_obra, Ob.localizacao, Ob.data_inicio, Ob.data_fim, C.nome_proprio AS client_nome_proprio, C.apelido AS client_apelido
        FROM EMPRESA_CONSTRUCAO.REL_OBRA_SERVICO AS REL_O_S
        JOIN EMPRESA_CONSTRUCAO.OBRA AS Ob ON REL_O_S.id_obra = Ob.id
        JOIN EMPRESA_CONSTRUCAO.CLIENTE AS C ON Ob.nif_cliente = C.nif
        WHERE id_servico = @servico_id
    )
GO

-- Teste
SELECT * FROM getObraServicoByServico(201111)



-- Filtrar as obras e empregados por id de obra
GO
CREATE FUNCTION getObraEmpregadoByObra(@obra_id INT) RETURNS TABLE
AS
    RETURN (
        SELECT REL_O_E.id_obra, REL_O_E.nif_empregado, REL_O_E.dia, REL_O_E.horas, E.nome_proprio, E.apelido, E.email
        FROM EMPRESA_CONSTRUCAO.REL_OBRA_EMPREGADO AS REL_O_E
        JOIN EMPRESA_CONSTRUCAO.EMPREGADO AS E ON REL_O_E.nif_empregado = E.nif
        WHERE id_obra = @obra_id
    );
GO

-- Teste
SELECT * FROM getObraEmpregadoByObra(19940038)



-- Filtrar as obras e empregados por nif do empregado
GO
CREATE FUNCTION getObraEmpregadoByEmpregado(@empr_nif INT) RETURNS TABLE
AS
    RETURN (
        SELECT REL_O_E.nif_empregado, REL_O_E.id_obra, O.localizacao, O.data_inicio, O.data_fim
        FROM EMPRESA_CONSTRUCAO.REL_OBRA_EMPREGADO AS REL_O_E
        JOIN EMPRESA_CONSTRUCAO.OBRA AS O ON REL_O_E.id_obra = O.id
        WHERE nif_empregado = @empr_nif
    );
GO

-- Teste
SELECT * FROM getObraEmpregadoByEmpregado(567890321)



-- Retornar o número total de horas de trabalho de um empregado
GO
CREATE FUNCTION getTotalHoursByEmpregado(@empr_nif INT) RETURNS TABLE
AS
    RETURN (
        SELECT
          REL_O_E.nif_empregado,
          E.nome_proprio,
          E.apelido,
          SUM(DATEPART(HOUR, REL_O_E.horas)) AS total_horas
        FROM
          EMPRESA_CONSTRUCAO.REL_OBRA_EMPREGADO AS REL_O_E
          JOIN EMPRESA_CONSTRUCAO.EMPREGADO AS E ON REL_O_E.nif_empregado = E.nif
        WHERE
          nif_empregado = @empr_nif
        GROUP BY
          REL_O_E.nif_empregado,
          E.nome_proprio,
          E.apelido
    );
GO

-- Teste
SELECT * FROM getTotalHoursByEmpregado(280932739)



-- Retornar o número total de horas de todos os empregados
GO
CREATE FUNCTION getTotalHoursAllEmployees() RETURNS TABLE
AS
    RETURN (
        SELECT
          REL_O_E.nif_empregado,
          E.nome_proprio,
          E.apelido,
          SUM(DATEPART(HOUR, REL_O_E.horas)) AS total_horas
        FROM
          EMPRESA_CONSTRUCAO.REL_OBRA_EMPREGADO AS REL_O_E
          JOIN EMPRESA_CONSTRUCAO.EMPREGADO AS E ON REL_O_E.nif_empregado = E.nif
        GROUP BY
          REL_O_E.nif_empregado,
          E.nome_proprio,
          E.apelido
    );
GO

-- Teste
SELECT * FROM getTotalHoursAllEmployees()



-- ---------------------------------- UDF'S for EMPRESA_CONSTRUCAO.MATERIAL_CONSTRUCAO ----------------------------------

-- Filtrar os materiais de construção por categoria
GO
CREATE FUNCTION getMaterialByCategory(@category VARCHAR(50)) RETURNS TABLE
AS
    RETURN (
        SELECT * FROM EMPRESA_CONSTRUCAO.MATERIAL_CONSTRUCAO
        WHERE categoria = @category
    );
GO

-- Teste
SELECT * FROM getMaterialByCategory('Acabamentos')



-- Filtrar os materiais de construção por nome
GO
CREATE FUNCTION getMaterialByName(@mat_name VARCHAR(50)) RETURNS TABLE
AS
    RETURN (
        SELECT * FROM EMPRESA_CONSTRUCAO.MATERIAL_CONSTRUCAO
        WHERE nome = @mat_name
    );
GO

-- Teste
SELECT * FROM getMaterialByName('Capacetes')



-- Filtrar os materiais de construção por unidades em armazém
GO
CREATE FUNCTION getMaterialByQuantity(@quantity_mat INT) RETURNS TABLE
AS
    RETURN (
        SELECT * FROM EMPRESA_CONSTRUCAO.MATERIAL_CONSTRUCAO
        WHERE unidades_armazem >= @quantity_mat
    );
GO

-- Teste
SELECT * FROM getMaterialByQuantity(400)



-- Retornar os materiais usados numa obra
GO
CREATE FUNCTION getMateriaisByObra(@id_obra INT) RETURNS TABLE
AS
    RETURN (
        SELECT REL_O_MAT.id_obra, REL_O_MAT.id_material, MC.nome AS nome_material, MC.categoria AS categoria_material
        FROM EMPRESA_CONSTRUCAO.REL_OBRA_MATERIAL AS REL_O_MAT
        JOIN EMPRESA_CONSTRUCAO.MATERIAL_CONSTRUCAO AS MC ON MC.id = REL_O_MAT.id_material
        WHERE id_obra = @id_obra
    );
GO

-- Teste
SELECT * FROM getMateriaisByObra(19940089)



-- Filtrar os materiais por encomenda
GO
CREATE FUNCTION getMateriaisByEncomenda(@id_enc INT) RETURNS TABLE
AS
    RETURN (
        SELECT id_encomenda, id_material, nome AS nome_material, categoria AS categoria_material, custo
        FROM EMPRESA_CONSTRUCAO.REL_ENCOMENDA_MATERIAL AS REL_ENC_MAT
        JOIN EMPRESA_CONSTRUCAO.ENCOMENDA AS ENC ON ENC.id = REL_ENC_MAT.id_encomenda
        JOIN EMPRESA_CONSTRUCAO.MATERIAL_CONSTRUCAO AS MAT ON REL_ENC_MAT.id_material = MAT.id
        WHERE id_encomenda = @id_enc
    );
GO

-- Teste
SELECT * FROM getMateriaisByEncomenda(1991001)



-- Filtrar os fornecedores por nome
GO
CREATE FUNCTION getFornecedorByName(@forn_name VARCHAR(50)) RETURNS TABLE
AS
    RETURN (
        SELECT * FROM EMPRESA_CONSTRUCAO.FORNECEDOR
        WHERE nome = @forn_name
    );
GO

-- Teste
SELECT * FROM getFornecedorByName('MegaConstrução')



-- Filtrar as encomendas por data, id do fornecedor e id da obra
GO
CREATE FUNCTION getEncomendaByDateFornIdObraId(@del_date DATE, @forn_nif INT, @obra_id INT) RETURNS TABLE
AS
    RETURN (
        SELECT
            ENC.id AS id_encomenda,
            ENC.data AS data_encomenda,
            ENC.nif_fornecedor,
            F.nome AS nome_fornecedor,
            O.id AS id_obra
        FROM EMPRESA_CONSTRUCAO.ENCOMENDA AS ENC
        JOIN EMPRESA_CONSTRUCAO.FORNECEDOR F on F.nif = ENC.nif_fornecedor
        JOIN EMPRESA_CONSTRUCAO.OBRA AS O on O.id = ENC.id_obra
        WHERE (@del_date IS NULL OR data >= @del_date) AND
        (@forn_nif IS NULL OR nif_fornecedor = @forn_nif) AND
        (@obra_id IS NULL OR id_obra = @obra_id)
    );
GO

-- Teste
SELECT * FROM getEncomendaByDateFornIdObraId ('2023-05-10', 208576294, 19940036)



-- Retornar todas as encomendas
GO
CREATE FUNCTION getTotalEncomendas() RETURNS TABLE
AS
    RETURN (
        SELECT ENC.id AS id_encomenda, ENC.data AS data_encomenda, ENC.id_obra, ENC.nif_fornecedor, F.nome AS nome_fornecedor
        FROM EMPRESA_CONSTRUCAO.ENCOMENDA AS ENC
        JOIN EMPRESA_CONSTRUCAO.FORNECEDOR AS F on F.nif = ENC.nif_fornecedor
    );
GO

-- Teste
SELECT * FROM getTotalEncomendas()



-- index fragmentation and rows
GO
CREATE FUNCTION getTablesInfo() RETURNS TABLE
AS
    RETURN (
        SELECT t.name AS table_name,
               ROUND(avg_fragmentation_in_percent,2) AS fragmentation,
               record_count AS rows_count
        FROM sys.dm_db_index_physical_stats(DB_ID(), NULL, NULL, NULL, 'SAMPLED') AS ips
        INNER JOIN sys.tables t on t.object_id = ips.object_id
        INNER JOIN sys.schemas s on t.schema_id = s.schema_id
        INNER JOIN sys.indexes i ON (ips.object_id = i.object_id) AND (ips.index_id = i.index_id)
        WHERE s.name = 'EMPRESA_CONSTRUCAO' AND t.name NOT LIKE 'REL_%'
    );
GO

-- Teste
SELECT * FROM getTablesInfo()