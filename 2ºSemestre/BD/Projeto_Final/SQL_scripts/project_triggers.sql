-- Trigger's

-- Um empregado não pode inserir que trabalhou numa obra caso esta não tenha sido iniciada ou já tenha terminado
GO
CREATE TRIGGER work_day_check ON EMPRESA_CONSTRUCAO.REL_OBRA_EMPREGADO
INSTEAD OF INSERT
AS
BEGIN
    DECLARE @work_day AS DATE;
    DECLARE @obra_id AS INT;
    DECLARE @empr_nif AS INT;
    DECLARE @begin_date_obra AS DATE;
    DECLARE @end_date_obra AS DATE;

    SELECT @obra_id = inserted.id_obra, @empr_nif = inserted.nif_empregado, @work_day = inserted.dia FROM inserted;

    SELECT @begin_date_obra = data_inicio, @end_date_obra = data_fim
    FROM EMPRESA_CONSTRUCAO.OBRA WHERE id = @obra_id

    BEGIN
        IF(@work_day < @begin_date_obra OR @work_day > @end_date_obra)
            BEGIN
                RAISERROR('ERROR: Work day inputed out of range between the begin and end date of the construction', 16, 1);
                RETURN;
            END
    END

    INSERT INTO EMPRESA_CONSTRUCAO.REL_OBRA_EMPREGADO SELECT * FROM inserted;
END
GO

-- Teste
EXEC add_empregado_obra 19940020, 461092846, '2023-05-01', '05:34:39'


-- O salário de um empregado deve de ser sempre maior ou igual ao salário minimo português (740.83) e não pode ser maior do que os salários dos CEO's
GO
CREATE TRIGGER salary_check ON EMPRESA_CONSTRUCAO.EMPREGADO
AFTER INSERT, UPDATE
AS
BEGIN
    DECLARE @salary_in AS DECIMAL(10,2);
    DECLARE @empr_nif AS INT;
    DECLARE @ceo_avg_salary AS DECIMAL(10,2)

    SELECT @ceo_avg_salary = AVG(E.salario)
    FROM EMPRESA_CONSTRUCAO.CEO AS CEO
    JOIN EMPRESA_CONSTRUCAO.EMPREGADO AS E ON E.nif = CEO.nif_empregado

    SELECT @salary_in = inserted.salario, @empr_nif = inserted.nif FROM inserted;

    BEGIN
        IF (@salary_in < 740.83)
            BEGIN
                RAISERROR('ERROR: Employee salary must be equal or greater to 740.83', 16, 1);
                ROLLBACK
                RETURN;
            END
        ELSE IF (@salary_in >= @ceo_avg_salary)
            BEGIN
                RAISERROR('ERROR: Employee salary cannot be bigger or equal to the CEOs average salary', 16, 1);
                ROLLBACK
                RETURN;
            END
    END

    INSERT INTO EMPRESA_CONSTRUCAO.EMPREGADO SELECT * FROM inserted;
END
GO

-- Teste
EXEC add_employee 564833845, 'Luís', 'Sebastião', 'luis.seb@ua.pt', 475924749, 'Rua de Espanha, KRAL', 'M', '1946-03-13', 60000.00, 20041



