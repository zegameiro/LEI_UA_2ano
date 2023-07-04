DROP FUNCTION IF EXISTS exercise_e;

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





