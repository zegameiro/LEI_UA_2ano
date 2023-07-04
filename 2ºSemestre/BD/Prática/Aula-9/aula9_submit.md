# BD: Guião 9


## ​9.1
 
### *a)*

```
DROP PROC IF EXISTS remove_employee
GO

GO
CREATE PROC remove_employee @ssn_employee INT
AS
BEGIN
    DELETE FROM COMPANY.WORKS_ON WHERE essn = @ssn_employee;
    UPDATE COMPANY.DEPARTMENT SET mgr_ssn = NULL WHERE mgr_ssn = @ssn_employee;
    UPDATE COMPANY.EMPLOYEE SET super_ssn = NULL WHERE super_ssn = @ssn_employee;
    DELETE FROM COMPANY.DEPENDENT WHERE essn = @ssn_employee;
    DELETE FROM COMPANY.EMPLOYEE WHERE ssn = @ssn_employee;
END
GO

-- Test Exercise a)
EXEC remove_employee 12652121;
```

### *b)* 

```
DROP PROC IF EXISTS Managers

GO
CREATE PROC Managers @OldMgrSsn INT OUTPUT, @OldMgrYear INT OUTPUT
AS
BEGIN
    SELECT E.f_name, E.m_init, E.l_name, E.ssn, E.birth_date, E.address, E.sex, E.salary, E.dno, D.Mgr_start_date
    FROM COMPANY.EMPLOYEE AS E
        JOIN COMPANY.DEPARTMENT AS D ON E.ssn=D.mgr_ssn

    SELECT TOP(1) @OldMgrSsn = ssn, @OldMgrYear = DATEDIFF(YEAR, Mgr_start_date, GETDATE())
    FROM COMPANY.EMPLOYEE AS E
        JOIN COMPANY.DEPARTMENT AS D ON E.ssn=D.mgr_ssn
    ORDER BY Mgr_start_date
END
GO

-- Test
DECLARE @OldMgrSsn INT, @OldMgrYear INT
EXEC Managers @OldMgrSsn OUTPUT, @OldMgrYear OUTPUT
PRINT @OldMgrSsn
PRINT @OldMgrYear
```

### *c)* 

```
DROP TRIGGER IF EXISTS manager_trigger;

GO
CREATE TRIGGER manager_trigger ON COMPANY.DEPARTMENT
AFTER INSERT, UPDATE
AS
BEGIN
    DECLARE @manager_ssn INT;
    SELECT @manager_ssn=mgr_ssn FROM inserted;

    IF @manager_ssn IN (SELECT mgr_ssn FROM COMPANY.DEPARTMENT)
    BEGIN
        RAISERROR('Employee cannot manage more than one department',16,1)
    END
    ELSE
    BEGIN
        INSERT INTO COMPANY.DEPARTMENT SELECT * FROM inserted
        PRINT 'Inserted successfully'
    END
END
GO

-- Test exercise c)
SELECT * FROM COMPANY.DEPARTMENT;
SELECT * FROM COMPANY.EMPLOYEE;
INSERT INTO COMPANY.DEPARTMENT VALUES ('Test1', 6, 21312332,'2013-05-16');
INSERT INTO COMPANY.DEPARTMENT VALUES ('Test2', 7, 345355435 ,'2013-05-16');
```

### *d)* 

```
DROP TRIGGER IF EXISTS LowerSalaryThanMgr
GO

CREATE TRIGGER LowerSalaryThanMgr ON COMPANY.EMPLOYEE
    AFTER INSERT, UPDATE
    AS
    DECLARE @EmpSSN     INT
    DECLARE @EmpSalary  INT
    DECLARE @MgrSalary  INT
    SELECT @EmpSSN = I.ssn, @EmpSalary = I.salary, @MgrSalary = S.salary FROM inserted AS I
                                                                                  JOIN COMPANY.EMPLOYEE AS S ON I.super_ssn = S.ssn

    IF (@EmpSalary > @MgrSalary)
        BEGIN
            UPDATE COMPANY.EMPLOYEE SET salary = @MgrSalary - 1 WHERE ssn = @EmpSSN
        END
```

### *e)* 

```
DROP FUNCTION IF EXISTS exercise_e;
GO

CREATE FUNCTION exercise_e (@emp_ssn INT) RETURNS @table_info TABLE
    (p_name     VARCHAR(15)     NOT NULL    UNIQUE ,
     p_location VARCHAR(15))
AS
BEGIN
    INSERT @table_info
        SELECT p_name, p_location
        FROM COMPANY.WORKS_ON
        JOIN COMPANY.PROJECT ON COMPANY.WORKS_ON.pno= COMPANY.PROJECT.p_number
        WHERE COMPANY.WORKS_ON.essn =@emp_ssn;
    RETURN;
END
GO

-- Text exercise e)
SELECT * FROM exercise_e(183623612)
```

### *f)* 

```
DROP FUNCTION IF EXISTS exercise_f;
GO

CREATE FUNCTION exercise_f (@dno INT) RETURNS @table_info TABLE
    (ssn             INT             NOT NULL   UNIQUE,
     f_name          VARCHAR(15)     NOT NULL,
     m_init          CHAR,
     l_name          VARCHAR(15)     NOT NULL,
     birth_date      DATE,
     address         VARCHAR(50),
     sex             CHAR,
     salary          DECIMAL(10,2))
AS
BEGIN
    DECLARE @salaryAvg DECIMAL(10,2)

    SELECT @salaryAvg = AVG(salary)
    FROM COMPANY.EMPLOYEE
    WHERE dno=@dno

    INSERT @table_info
    SELECT ssn, f_name, m_init, l_name, birth_date, address, sex, salary
    FROM COMPANY.EMPLOYEE AS E
    WHERE E.salary > @salaryAvg AND dno=@dno;
    RETURN;
END
GO

-- Test exercise f)
SELECT * FROM exercise_f(2)
```

### *g)* 

```
DROP FUNCTION IF EXISTS COMPANY.employeeDeptHighAverage;
GO

CREATE FUNCTION COMPANY.employeeDeptHighAverage (@dept_num INT) RETURNS @table_info TABLE (
    p_number        INT,
    p_name          VARCHAR(15),
    p_location      VARCHAR(15),
    d_num           INT             NOT NULL,
    budget          DECIMAL(10,2),
    totalBudget     DECIMAL(10,2)
)
AS
BEGIN
    DECLARE @p_number INT, @p_name VARCHAR(15), @p_location VARCHAR(15), @d_num INT;
    DECLARE @essn INT, @pno INT, @hours DECIMAL(3,1), @hourly_rate DECIMAL(10,2);
    DECLARE @budget DECIMAL(10,2), @totalBudget DECIMAL(10,2);
    SET @totalBudget = 0;

    DECLARE project_cursor CURSOR FOR
        SELECT p_number, p_name, p_location, d_num
        FROM COMPANY.PROJECT
        WHERE d_num = @dept_num;

    OPEN project_cursor;

    FETCH NEXT FROM project_cursor INTO @p_number, @p_name, @p_location, @d_num;

    WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @budget = 0;

            DECLARE works_on_cursor CURSOR FOR
                SELECT essn, pno, hours
                FROM COMPANY.WORKS_ON
                WHERE pno = @p_number;

            OPEN works_on_cursor;

            FETCH NEXT FROM works_on_cursor INTO @essn, @pno, @hours;

            WHILE @@FETCH_STATUS = 0
                BEGIN
                    SELECT @hourly_rate = salary / 40 FROM COMPANY.EMPLOYEE WHERE ssn = @essn;
                    SET @budget += @hours * @hourly_rate;
                    FETCH NEXT FROM works_on_cursor INTO @essn, @pno, @hours;
                END

            CLOSE works_on_cursor;
            DEALLOCATE works_on_cursor;

            SET @totalBudget += @budget;

            INSERT INTO @table_info VALUES (@p_number, @p_name, @p_location, @d_num, @budget, @totalBudget);

            FETCH NEXT FROM project_cursor INTO @p_number, @p_name, @p_location, @d_num;
        END

    CLOSE project_cursor;
    DEALLOCATE project_cursor;

    RETURN;
END
GO

-- Test exercise g)
SELECT * FROM COMPANY.employeeDeptHighAverage(3)
```

### *h)* 

```
DROP TRIGGER IF EXISTS COMPANY.exerciseHAfter;
DROP TRIGGER IF EXISTS COMPANY.exerciseHInsteadOf;
GO

-- After
CREATE TRIGGER COMPANY.exerciseHAfter ON COMPANY.DEPARTMENT
AFTER DELETE
AS
BEGIN
    IF (EXISTS(SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA ='COMPANY' AND TABLE_NAME ='DEPARTMENT_DELETED'))
    BEGIN
        INSERT INTO COMPANY.DEPARTMENT_DELETED SELECT * FROM deleted
    END
    ELSE
    BEGIN
        CREATE TABLE COMPANY.DEPARTMENT_DELETED (
            d_name          VARCHAR(50)     NOT NULL,
            d_number        INT             NOT NULL        PRIMARY KEY,
            mgr_ssn         INT,
            mgr_start_date  DATE,
        )
        INSERT INTO COMPANY.DEPARTMENT_DELETED SELECT * FROM deleted
    END
END
GO

-- Test
DELETE FROM COMPANY.DEPARTMENT WHERE d_number = 2;
SELECT * FROM COMPANY.DEPARTMENT_DELETED;

-- Instead of
DROP TRIGGER IF EXISTS COMPANY.exerciseHAfter;
GO

CREATE TRIGGER COMPANY.exerciseHInsteadOf ON COMPANY.DEPARTMENT
INSTEAD OF DELETE
AS
BEGIN
    DECLARE @d_number_to_delete INT;
    SELECT @d_number_to_delete = d_number FROM deleted;

    IF (EXISTS(SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA ='COMPANY' AND TABLE_NAME ='DEPARTMENT_DELETED'))
        BEGIN
            INSERT INTO COMPANY.DEPARTMENT_DELETED SELECT * FROM deleted
            DELETE FROM DEPARTMENT WHERE d_number = @d_number_to_delete;
        END
    ELSE
        BEGIN
            CREATE TABLE COMPANY.DEPARTMENT_DELETED (
                                                        d_name          VARCHAR(50)     NOT NULL,
                                                        d_number        INT             NOT NULL        PRIMARY KEY,
                                                        mgr_ssn         INT,
                                                        mgr_start_date  DATE,
            )
            INSERT INTO COMPANY.DEPARTMENT_DELETED SELECT * FROM deleted
            DELETE FROM DEPARTMENT WHERE d_number = @d_number_to_delete;
        END
END
GO

-- Test
DELETE FROM COMPANY.DEPARTMENT WHERE d_number = 3;
SELECT * FROM COMPANY.DEPARTMENT_DELETED;
```

### *i)* 

```
Os stored procedures são blocos de código que podem ser executados várias vezes com diferentes parâmetros de entrada.
Para além disso, permitem retornar mais do que um valor, lidar com exceções e receber argumentos de saída.
Estes são mais indicados para uma quantidade avultada de dados, já que o plano de execução fica armazenado em cache, sendo reutilizado para chamadas subsequentes.
Exemplo: Atualizar o stock de um produto quando um pedido é efetuado.

As UDFs são funções que permitem retornar uma tabela criada ou um valor único.
Estas são mais indicadas para o cálculo de valores, formatação de strings ou tranformações de dados.
Face aos stored procedures, permitem utilizar "WHERE" ou "HAVING".
Exemplo: Devolver o preço de um produto, com base no seu ID e numa percentagem de desconto.
```
