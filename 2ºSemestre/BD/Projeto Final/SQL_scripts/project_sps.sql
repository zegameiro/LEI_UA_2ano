-- Store Procedure's

-- Criar um novo Departamento
GO
CREATE PROCEDURE create_department(@dep_id INT, @dep_name VARCHAR(100))
AS
BEGIN
    BEGIN TRY
        INSERT INTO EMPRESA_CONSTRUCAO.DEPARTAMENTO(id, nome)
            VALUES (@dep_id, @dep_name)
        PRINT 'Success on the insertion in the table EMPRESA_CONSTRUCAO.DEPARTAMENTO'
    END TRY
    BEGIN CATCH
        PRINT ERROR_MESSAGE()
    END CATCH
END
GO

-- Teste
EXEC create_department 200411, 'Departamento de Redes'


-- Adicionar um empregado novo
GO
CREATE PROCEDURE add_employee(@nif INT, @first_name VARCHAR(25), @last_name VARCHAR(25), @email VARCHAR(50), @phone_number INT,
                              @address VARCHAR(200), @gender CHAR, @birth_date DATE, @salary DECIMAL(10,2), @id_dep INT)
AS
BEGIN
    BEGIN TRY
        BEGIN TRANSACTION
            INSERT INTO EMPRESA_CONSTRUCAO.EMPREGADO(nif, nome_proprio, apelido, email, telefone, morada, genero, data_nascimento, salario,id_departamento)
                VALUES (@nif, @first_name, @last_name, @email, @phone_number, @address, @gender, @birth_date, @salary, @id_dep)
            PRINT 'Sucess on the insertion in the table EMPRESA_CONSTRUCAO.EMPREGADO'
        COMMIT
    END TRY
    BEGIN CATCH
        PRINT ERROR_MESSAGE()
        ROLLBACK
    END CATCH
END
GO

-- Teste
EXEC add_employee 101010101, 'T', 't', 't@t.pt', 910000000, 'teste', 'O', '1970-01-01', 750.43, 20041



-- Criar uma obra nova
GO
CREATE PROCEDURE create_obra(@id_obra INT, @location VARCHAR(200), @begin_date DATE, @end_date DATE, @client_nif INT)
AS
BEGIN
    BEGIN TRY
        BEGIN TRANSACTION
            INSERT INTO EMPRESA_CONSTRUCAO.OBRA(id, localizacao, data_inicio, data_fim, nif_cliente)
                VALUES (@id_obra, @location, @begin_date, @end_date, @client_nif)
            PRINT 'Success on the insertion in the table EMPRESA_CONSTRUCAO.OBRA'
        COMMIT
    END TRY

    BEGIN CATCH
        PRINT ERROR_MESSAGE()
        ROLLBACK
    END CATCH
END
GO

-- Teste
EXEC create_obra 19940091, 'Rua São Miguel Açores, BAA', '2023-01-01', '2023-01-22', 287944069


-- Criar um cliente novo
GO
CREATE PROCEDURE create_client(@nif_client INT, @first_name VARCHAR(25), @last_name VARCHAR(25), @email VARCHAR(100), @phone_number INT, @address VARCHAR(200))
AS
BEGIN
   BEGIN TRY
       INSERT INTO EMPRESA_CONSTRUCAO.CLIENTE(nif, nome_proprio, apelido, email, telefone, morada)
            VALUES (@nif_client, @first_name, @last_name, @email, @phone_number, @address)

        PRINT 'Success on the insertion in the table EMPRESA_CONSTRUCAO.CLIENTE'
   END TRY
   BEGIN CATCH
       PRINT ERROR_MESSAGE()
   END CATCH
END
GO

-- Teste
EXEC create_client 123741849, 'Maria', 'Beatriz', 'maria.beatriz@ua.pt', 234712984, 'Rua Desportiva de Alvas, ABC'


-- Adicionar um novo material de construção
GO
CREATE PROCEDURE add_constr_material(@mat_id INT, @category_mat VARCHAR(50), @name_mat VARCHAR(60), @quantity_mat INT)
AS
BEGIN
   BEGIN TRY
       INSERT INTO EMPRESA_CONSTRUCAO.MATERIAL_CONSTRUCAO(id, categoria, nome, unidades_armazem)
            VALUES (@mat_id, @category_mat, @name_mat, @quantity_mat)

       PRINT 'Success on the insertion on the table EMPRESA_CONSTRUCAO.MATERIAL_CONSTRUCAO'
   END TRY

   BEGIN CATCH
        PRINT ERROR_MESSAGE()
   END CATCH

END

-- Teste
EXEC add_constr_material '1997016', 'Fundações e estruturas', 'Tijolos', 640


-- Adicionar um fornecedor novo
GO
CREATE PROCEDURE create_fornecedor(@nif_forn INT, @name_forn VARCHAR(40), @phone_number_forn INT, @email_fonr VARCHAR(70), @address_forn VARCHAR(300))
AS
BEGIN
   BEGIN TRY
       INSERT INTO EMPRESA_CONSTRUCAO.FORNECEDOR(nif, nome, telefone, email, morada)
            VALUES (@nif_forn, @name_forn, @phone_number_forn, @email_fonr, @address_forn)

       PRINT 'Success on the insertion on the table EMPRESA_CONSTRUCAO.FORNECEDOR'
   END TRY

   BEGIN CATCH
       PRINT ERROR_MESSAGE()
   END CATCH
END
GO

-- Teste
EXEC create_fornecedor 491723946, 'Construção Domingues', 295814065, 'domingues.constr@ua.pt', 'Rua da Padaria 2039, ACHS'


-- Adiconar uma nova encomenda
GO
CREATE PROCEDURE add_encomenda(@id_enc INT, @data_enc DATE, @forn_nif INT, @obra_id INT)
AS
BEGIN
    BEGIN TRY
        BEGIN TRANSACTION
            INSERT INTO EMPRESA_CONSTRUCAO.ENCOMENDA(id, data, nif_fornecedor, id_obra)
                VALUES (@id_enc, @data_enc, @forn_nif, @obra_id)

            PRINT 'Success on the insertion in the table EMPRESA_CONSTRUCAO.ENCOMENDA'
        COMMIT
    END TRY

    BEGIN CATCH
        PRINT ERROR_MESSAGE()
        ROLLBACK
    END CATCH
END
GO

-- Teste
EXEC add_encomenda 1991021 ,'2023-05-03', 965781420, 19940051


-- Adicionar um empregado a uma obra
GO
CREATE PROCEDURE add_empregado_obra(@id_obra INT, @nif_empr INT, @work_day DATE, @work_hours TIME)
AS
BEGIN
    BEGIN TRY
        BEGIN TRANSACTION
            INSERT INTO EMPRESA_CONSTRUCAO.REL_OBRA_EMPREGADO(id_obra, nif_empregado, dia, horas)
                VALUES (@id_obra, @nif_empr, @work_day, @work_hours)
        COMMIT
    END TRY

    BEGIN CATCH
        PRINT ERROR_MESSAGE()
        ROLLBACK
    END CATCH
END
GO

-- Teste
EXEC add_empregado_obra 19940001, 280932739, '2023-04-15', '05:48:12'


-- Adicionar um material de construção a uma encomenda
GO
CREATE PROCEDURE add_material_enc(@id_enc INT, @id_mat INT, @total_cost DECIMAL(10,2))
AS
BEGIN
    BEGIN TRY
        BEGIN TRANSACTION
            INSERT INTO EMPRESA_CONSTRUCAO.REL_ENCOMENDA_MATERIAL(id_encomenda, id_material, custo)
                VALUES (@id_enc, @id_mat, @total_cost)
            PRINT 'Added material to the encomenda with success'
        COMMIT
    END TRY

    BEGIN CATCH
        PRINT ERROR_MESSAGE()
        ROLLBACK
    END CATCH
END
GO

-- Test
EXEC add_material_enc 1991020, 1997010, 478.30


-- Adicionar um serviço a uma obra
GO
CREATE PROCEDURE add_service_obra(@id_obra INT, @id_servico INT)
AS
BEGIN
    BEGIN TRY
        BEGIN TRANSACTION
            INSERT INTO EMPRESA_CONSTRUCAO.REL_OBRA_SERVICO(id_obra, id_servico)
                VALUES (@id_obra, @id_servico)
            PRINT 'Added serviço to obra with success'
        COMMIT
    END TRY

    BEGIN CATCH
        PRINT ERROR_MESSAGE()
        ROLLBACK
    END CATCH
END
GO

-- Teste
EXEC add_service_obra 19940001, 201101



-- Alterar os dados de um departamento
GO
CREATE PROCEDURE update_department(@id_dep INT, @name_dep VARCHAR(100))
AS
BEGIN
    BEGIN TRY
        DECLARE @id_dep_old AS INT;
        DECLARE @name_dep_old AS VARCHAR(100);

        SELECT
            @id_dep_old = DEP.id,
            @name_dep_old = DEP.nome
        FROM EMPRESA_CONSTRUCAO.DEPARTAMENTO AS DEP
        WHERE id = @id_dep OR nome = @name_dep

        IF @id_dep_old != @id_dep
            BEGIN
                UPDATE EMPRESA_CONSTRUCAO.DEPARTAMENTO SET id = @id_dep WHERE id = @id_dep_old
                PRINT 'Updated id from department with success'
            END

        IF @name_dep_old != @name_dep
            BEGIN
                UPDATE EMPRESA_CONSTRUCAO.DEPARTAMENTO SET nome = @name_dep WHERE id = @id_dep_old
                PRINT 'Updated name from department with success'
            END

    END TRY

    BEGIN CATCH
        PRINT ERROR_MESSAGE()
    END CATCH
END
GO

-- Teste
EXEC update_department 200411, 'Departamento de Segurança'


-- Alterar os dados de um empregado
GO
CREATE PROCEDURE update_employee (
        @nif_empr INT,
        @first_name_empr VARCHAR(25),
        @last_name_empr VARCHAR(25),
        @email_empr VARCHAR(50),
        @phone_number_empr INT,
        @address_empr VARCHAR(200),
        @gender_empr CHAR,
        @birth_date_empr DATE,
        @salary_empr DECIMAL(10,2)
)
AS
BEGIN
    BEGIN TRY
        BEGIN TRANSACTION
            DECLARE @nif_empr_old AS INT;
            DECLARE @first_name_old AS VARCHAR(25);
            DECLARE @last_name_old AS VARCHAR(25);
            DECLARE @email_empr_old AS VARCHAR(50);
            DECLARE @phone_numer_old AS INT;
            DECLARE @address_empr_old AS VARCHAR(200);
            DECLARE @gender_empr_old AS CHAR;
            DECLARE @birth_date_old AS DATE;
            DECLARE @salary_old AS DECIMAL(10,2);

            SELECT @nif_empr_old = E.nif,
                   @first_name_old = E.nome_proprio,
                   @last_name_old = E.apelido,
                   @email_empr_old = E.email,
                   @phone_numer_old = E.telefone,
                   @address_empr_old = E.morada,
                   @gender_empr_old = E.genero,
                   @birth_date_old = E.data_nascimento,
                   @salary_old = E.salario
            FROM EMPRESA_CONSTRUCAO.EMPREGADO AS E
            WHERE E.nif = @nif_empr OR E.nome_proprio = @first_name_empr OR E.apelido = @last_name_empr OR E.email = @email_empr OR E.telefone = @phone_number_empr OR E.morada = @address_empr

            IF @nif_empr_old != @nif_empr
            BEGIN
                UPDATE EMPRESA_CONSTRUCAO.EMPREGADO SET nif = @nif_empr WHERE nif = @nif_empr_old;
                PRINT 'Updated empregado nif with success'
            END

            IF @first_name_old != @first_name_empr
            BEGIN
                UPDATE EMPRESA_CONSTRUCAO.EMPREGADO SET nome_proprio = @first_name_empr WHERE nif = @nif_empr_old
                PRINT 'Updated empregado first name with success'
            END

            IF @last_name_old != @last_name_empr
            BEGIN
                UPDATE EMPRESA_CONSTRUCAO.EMPREGADO SET apelido = @last_name_empr WHERE nif = @nif_empr_old
                PRINT 'Updated empregado last name with success'
            END

            IF @email_empr_old != @email_empr
            BEGIN
                UPDATE EMPRESA_CONSTRUCAO.EMPREGADO SET email = @email_empr WHERE nif = @nif_empr_old
                PRINT 'Updated empregado email with success'
            END

            IF @phone_numer_old != @phone_number_empr
            BEGIN
                UPDATE EMPRESA_CONSTRUCAO.EMPREGADO SET telefone = @phone_number_empr WHERE nif = @nif_empr_old
                PRINT 'Updated empregado phone number with success'
            END

            IF @address_empr_old != @address_empr
            BEGIN
                UPDATE EMPRESA_CONSTRUCAO.EMPREGADO SET morada = @address_empr WHERE nif = @nif_empr_old
                PRINT 'Updated empregado address with success'
            END

            IF @gender_empr_old != @gender_empr
            BEGIN
                UPDATE EMPRESA_CONSTRUCAO.EMPREGADO SET genero = @gender_empr WHERE nif = @nif_empr_old
                PRINT 'Updated empregado gender with success'
            END

            IF @birth_date_old != @birth_date_empr
            BEGIN
                UPDATE EMPRESA_CONSTRUCAO.EMPREGADO SET data_nascimento = @birth_date_empr WHERE nif = @nif_empr_old
                PRINT 'Updated empregado birth date with success'
            END

            IF @salary_old != @salary_empr
            BEGIN
                UPDATE EMPRESA_CONSTRUCAO.EMPREGADO SET salario = @salary_empr WHERE nif = @nif_empr_old
                PRINT 'Updated empregado salary with success'
            END
        COMMIT
    END TRY

    BEGIN CATCH
        PRINT ERROR_MESSAGE()
        ROLLBACK
    END CATCH
END
GO

-- Teste
EXEC update_employee 91543871, 'Tiago', 'Parreira', 'tiago.parreira@ua.pt', 914381023, NULL, 'M', '1973-11-14', 4568.12


-- Alterar os dados de um cliente
GO
CREATE PROCEDURE update_client(
    @nif_cl INT,
    @first_name_cl VARCHAR(20),
    @last_name_cl VARCHAR(20),
    @email_cl VARCHAR(40),
    @phone_number_cl INT,
    @address_cl VARCHAR(200)
) AS
BEGIN
    BEGIN TRY
        DECLARE @nif_cl_old AS INT;
        DECLARE @first_name_cl_old AS VARCHAR(20);
        DECLARE @last_name_cl_old AS VARCHAR(20);
        DECLARE @email_cl_old AS VARCHAR(40);
        DECLARE @phone_number_cl_old AS INT;
        DECLARE @address_cl_old AS VARCHAR(200);

        SELECT
           @nif_cl_old = C.nif,
           @first_name_cl_old = C.nome_proprio,
           @last_name_cl_old = C.apelido,
           @email_cl_old = C.email,
           @phone_number_cl_old = C.telefone,
           @address_cl_old = C.morada
        FROM EMPRESA_CONSTRUCAO.CLIENTE AS C
        WHERE nif = @nif_cl OR nome_proprio = @first_name_cl OR apelido = @last_name_cl OR email = @email_cl OR telefone = @phone_number_cl OR morada = @address_cl

        IF @nif_cl_old != @nif_cl
            BEGIN
               UPDATE EMPRESA_CONSTRUCAO.CLIENTE SET nif = @nif_cl WHERE nif = @nif_cl_old
               PRINT 'Updated client nif with success'
            END

        IF @first_name_cl_old != @first_name_cl
            BEGIN
               UPDATE EMPRESA_CONSTRUCAO.CLIENTE SET nome_proprio = @first_name_cl WHERE nif = @nif_cl_old
               PRINT 'Updated client first name with success'
            END

        IF @last_name_cl_old != @last_name_cl
            BEGIN
               UPDATE EMPRESA_CONSTRUCAO.CLIENTE SET apelido = @last_name_cl WHERE nif = @nif_cl_old
               PRINT 'Updated client last name with success'
            END

        IF @email_cl_old != @email_cl
            BEGIN
               UPDATE EMPRESA_CONSTRUCAO.CLIENTE SET email = @email_cl WHERE nif = @nif_cl_old
               PRINT 'Updated client email with success'
            END

        IF @phone_number_cl_old != @phone_number_cl
            BEGIN
               UPDATE EMPRESA_CONSTRUCAO.CLIENTE SET telefone = @phone_number_cl WHERE nif = @nif_cl_old
               PRINT 'Updated client phone number with success'
            END

        IF @address_cl_old != @address_cl
            BEGIN
               UPDATE EMPRESA_CONSTRUCAO.CLIENTE SET morada = @address_cl WHERE nif = @nif_cl_old
               PRINT 'Updated client address with success'
            END
    END TRY

    BEGIN CATCH
        PRINT ERROR_MESSAGE()
    END CATCH
END
GO

-- Teste
EXEC update_client 991972284, 'Jessica', 'Carlson', 'jessica.carlson@ua.pt', 853042464, '753 Jackson River, Nicholasborough, DC 49673'


-- Alterar os dados de uma obra
GO
CREATE PROCEDURE update_obra(@id_obra INT, @location_obra VARCHAR(200), @begin_date_obra DATE, @end_date_obra DATE)
AS
BEGIN
    BEGIN TRY
        BEGIN TRANSACTION
            DECLARE @id_obra_old AS INT;
            DECLARE @location_obra_old AS VARCHAR(200);
            DECLARE @begin_date_obra_old AS DATE;
            DECLARE @end_date_obra_old AS DATE;

            SELECT
                @id_obra_old = O.id,
                @location_obra_old = O.localizacao,
                @begin_date_obra_old = O.data_inicio,
                @end_date_obra_old = O.data_fim
            FROM EMPRESA_CONSTRUCAO.OBRA AS O
            WHERE O.id = @id_obra OR O.localizacao = @location_obra

            IF @id_obra_old != @id_obra
                BEGIN
                    UPDATE EMPRESA_CONSTRUCAO.OBRA SET id = @id_obra WHERE id = @id_obra_old
                    PRINT 'Updated id from obra with success'
                END

            IF @location_obra_old != @location_obra
                BEGIN
                    UPDATE EMPRESA_CONSTRUCAO.OBRA SET localizacao = @location_obra WHERE id = @id_obra_old
                    PRINT 'Updated location from obra with success'
                END

            IF @begin_date_obra_old != @begin_date_obra
                BEGIN
                    UPDATE EMPRESA_CONSTRUCAO.OBRA SET data_inicio = @begin_date_obra WHERE id = @id_obra_old
                    PRINT 'Updated start date from obra with success'
                END

            IF @end_date_obra_old != @end_date_obra
                BEGIN
                    UPDATE EMPRESA_CONSTRUCAO.OBRA SET data_fim = @end_date_obra WHERE id = @id_obra_old
                    PRINT 'Updated end date from obra with success'
                END
        COMMIT
    END TRY

    BEGIN CATCH
        PRINT ERROR_MESSAGE()
        ROLLBACK
    END CATCH
END
GO

-- Teste
EXEC update_obra 19940090, '347 Dunn Island, Daughertyshire, OH 57189', '2023-05-11', '2023-06-07'


-- Alterar os dados de um fornecedor
GO
CREATE PROCEDURE update_fornecedor(@nif_fornecedor INT, @name_fornecedor VARCHAR(50), @phone_number_fornecedor INT, @email_fornecedor VARCHAR(100), @address_fornecedor VARCHAR(200))
AS
BEGIN
    BEGIN TRY
        DECLARE @nif_forn_old AS INT;
        DECLARE @name_forn_old AS VARCHAR(50);
        DECLARE @phone_number_forn_old AS INT;
        DECLARE @email_forn_old AS VARCHAR(100);
        DECLARE @address_forn_old AS VARCHAR(200);

        SELECT
            @nif_forn_old = FORN.nif,
            @name_forn_old = FORN.nome,
            @phone_number_forn_old = FORN.telefone,
            @email_forn_old = FORN.email,
            @address_forn_old = FORN.morada
        FROM EMPRESA_CONSTRUCAO.FORNECEDOR AS FORN
        WHERE nif = @nif_fornecedor OR nome = @name_fornecedor OR telefone = @phone_number_fornecedor OR email = @email_fornecedor OR morada = @address_fornecedor

        IF @nif_forn_old != @nif_fornecedor
            BEGIN
                UPDATE EMPRESA_CONSTRUCAO.FORNECEDOR SET nif = @nif_fornecedor WHERE nif = @nif_forn_old
                PRINT 'Updated nif from fornecedor with success'
            END

        IF @name_forn_old != @name_fornecedor
            BEGIN
                UPDATE EMPRESA_CONSTRUCAO.FORNECEDOR SET nome = @name_fornecedor WHERE nif = @nif_forn_old
                PRINT 'Updated name from fornecedor with success'
            END

        IF @phone_number_forn_old != @phone_number_fornecedor
            BEGIN
                UPDATE EMPRESA_CONSTRUCAO.FORNECEDOR SET telefone = @phone_number_fornecedor WHERE nif = @nif_forn_old
                PRINT 'Updated phone number from fornecedor with success'
            END

        IF @email_forn_old != @email_fornecedor
            BEGIN
                UPDATE EMPRESA_CONSTRUCAO.FORNECEDOR SET email = @email_fornecedor WHERE nif = @nif_forn_old
                PRINT 'Updated email from fornecedor with success'
            END

        IF @address_forn_old != @address_fornecedor
            BEGIN
                UPDATE EMPRESA_CONSTRUCAO.FORNECEDOR SET morada = @address_fornecedor WHERE nif = @nif_forn_old
                PRINT 'Updated address from fornecedor with success'
            END
    END TRY

    BEGIN CATCH
        PRINT ERROR_MESSAGE()
    END CATCH
END
GO

-- Teste
EXEC update_fornecedor 491723945,'Construção Domingos', 295814066, 'domingos.constr@ua.pt', 'Rua da Pradaria 2039, ACHS'


-- Alterar os dados de um material de construção
GO
CREATE PROCEDURE update_mat_constr(@id_mat INT, @category_mat VARCHAR(100), @name_mat VARCHAR(50), @quantity INT)
AS
BEGIN
    BEGIN TRY
        DECLARE @id_mat_old AS INT;
        DECLARE @category_mat_old AS VARCHAR(100);
        DECLARE @name_mat_old AS VARCHAR(50);
        DECLARE @quantity_old AS INT;

        SELECT
            @id_mat_old = MAT.id,
            @category_mat_old = MAT.categoria,
            @name_mat_old = MAT.nome,
            @quantity_old = MAT.unidades_armazem
        FROM EMPRESA_CONSTRUCAO.MATERIAL_CONSTRUCAO AS MAT
        WHERE @id_mat = id OR @name_mat = nome

        IF  @id_mat_old != @id_mat
            BEGIN
                UPDATE EMPRESA_CONSTRUCAO.MATERIAL_CONSTRUCAO SET id = @id_mat WHERE id = @id_mat_old
                PRINT 'Updated material id with success'
            END

        IF  @category_mat_old != @category_mat
            BEGIN
                UPDATE EMPRESA_CONSTRUCAO.MATERIAL_CONSTRUCAO SET categoria = @category_mat WHERE id = @id_mat_old
                PRINT 'Updated material category with success'
            END

        IF  @name_mat_old != @name_mat
            BEGIN
                UPDATE EMPRESA_CONSTRUCAO.MATERIAL_CONSTRUCAO SET nome = @name_mat WHERE id = @id_mat_old
                PRINT 'Updated material name with success'
            END

        IF  @quantity_old != @quantity
            BEGIN
                UPDATE EMPRESA_CONSTRUCAO.MATERIAL_CONSTRUCAO SET unidades_armazem = @quantity WHERE id = @id_mat_old
                PRINT 'Updated material storage units with success'
            END
    END TRY

    BEGIN CATCH
        PRINT ERROR_MESSAGE()
    END CATCH
END
GO

-- Teste
EXEC update_mat_constr 1997017, 'Equipamentos de Proteção','Luvas', 1250


-- Eliminar um departamento
GO
CREATE PROCEDURE delete_department(@id_dep INT)
AS
BEGIN
    BEGIN TRY
        BEGIN TRANSACTION
            DELETE FROM EMPRESA_CONSTRUCAO.DEPARTAMENTO WHERE id = @id_dep
            PRINT 'Deleted department with success'
        COMMIT
    END TRY

    BEGIN CATCH
        PRINT ERROR_MESSAGE()
        ROLLBACK
    END CATCH
END
GO

-- Teste
EXEC delete_department 200411


-- Eliminar um empregado
GO
CREATE PROCEDURE delete_employee(@employee_nif INT)
AS
BEGIN
    BEGIN TRY
        BEGIN TRANSACTION
            DELETE FROM EMPRESA_CONSTRUCAO.EMPREGADO WHERE nif = @employee_nif
            PRINT 'Deleted empregado with success'
        COMMIT
    END TRY

    BEGIN CATCH
        PRINT ERROR_MESSAGE()
        ROLLBACK
    END CATCH
END
GO

-- Teste
EXEC delete_employee 624521804


-- Eliminar uma obra
GO
CREATE PROCEDURE delete_obra(@id_obra INT)
AS
BEGIN
    BEGIN TRY
        BEGIN TRANSACTION
            DELETE FROM EMPRESA_CONSTRUCAO.OBRA WHERE id = @id_obra
            PRINT 'Deleted obra with success'
        COMMIT
    END TRY

    BEGIN CATCH
        PRINT ERROR_MESSAGE()
        ROLLBACK
    END CATCH
END
GO

-- Teste
EXEC delete_obra 19940021


-- Eliminar um cliente
GO
CREATE PROCEDURE delete_client(@nif_client INT)
AS
BEGIN
    BEGIN TRY
        BEGIN TRANSACTION
            DELETE FROM EMPRESA_CONSTRUCAO.CLIENTE WHERE nif = @nif_client
            PRINT 'Deleted client with success'
        COMMIT
    END TRY

    BEGIN CATCH
        PRINT ERROR_MESSAGE()
        ROLLBACK
    END CATCH
END
GO

-- Teste
EXEC delete_client 245475150


-- Eliminar um fornecedor
GO
CREATE PROCEDURE delete_fornecedor(@nif_fornecedor INT)
AS
BEGIN
    BEGIN TRY
        DELETE FROM EMPRESA_CONSTRUCAO.FORNECEDOR WHERE nif = @nif_fornecedor
        PRINT 'Deleted fornecedor with success'
    END TRY

    BEGIN CATCH
        PRINT ERROR_MESSAGE()
    END CATCH
END
GO

-- Teste
EXEC delete_fornecedor 491723945


-- Eliminar um material de uma encomenda
GO
CREATE PROCEDURE delete_material_enc(@id_material INT)
AS
BEGIN
    BEGIN TRY
        BEGIN TRANSACTION
            DELETE FROM EMPRESA_CONSTRUCAO.REL_ENCOMENDA_MATERIAL WHERE id_material = @id_material
            PRINT 'Removed material from encomenda with success'
        COMMIT
    END TRY

    BEGIN CATCH
        PRINT ERROR_MESSAGE()
        ROLLBACK
    END CATCH
END
GO

-- Teste
SELECT * FROM EMPRESA_CONSTRUCAO.REL_ENCOMENDA_MATERIAL


-- Apagar um material de construção
GO
CREATE PROCEDURE delete_mat_constr(@id_mat INT)
AS
BEGIN
    BEGIN TRY
        DELETE FROM EMPRESA_CONSTRUCAO.MATERIAL_CONSTRUCAO WHERE id = @id_mat
        PRINT 'Deleted material from materiais de construção with success'
    END TRY

    BEGIN CATCH
        PRINT ERROR_MESSAGE()
    END CATCH
END
GO

-- Teste
EXEC delete_mat_constr 1997017