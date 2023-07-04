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