-- Exercise 6.2 referent to 5.1

DROP TABLE IF EXISTS COMPANY.DEPENDENT
DROP TABLE IF EXISTS COMPANY.WORKS_ON;
DROP TABLE IF EXISTS COMPANY.PROJECT;
DROP TABLE IF EXISTS COMPANY.DEP_LOCATIONS;

IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'DEPARTMENT')
    BEGIN
        IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS WHERE CONSTRAINT_NAME = 'mgr_ssn')
            BEGIN
                ALTER TABLE COMPANY.DEPARTMENT DROP CONSTRAINT mgr_ssn
            END
    END

DROP TABLE IF EXISTS COMPANY.EMPLOYEE;
DROP TABLE IF EXISTS COMPANY.DEPARTMENT;
DROP SCHEMA IF EXISTS COMPANY;
GO

CREATE SCHEMA COMPANY;
GO;

CREATE TABLE COMPANY.DEPARTMENT (
    d_name          VARCHAR(50)     NOT NULL,
    d_number        INT             NOT NULL        PRIMARY KEY,
    mgr_ssn         INT,
    mgr_start_date  DATE,
);

CREATE TABLE COMPANY.EMPLOYEE (
    ssn             INT             NOT NULL        PRIMARY KEY,
    f_name          VARCHAR(15)     NOT NULL,
    m_init          CHAR,
    l_name          VARCHAR(15)     NOT NULL,
    birth_date      DATE,
    address         VARCHAR(50),
    sex             CHAR,
    salary          DECIMAL(10,2)   DEFAULT 0       CHECK(salary > 12),
    super_ssn       INT,
    dno             INT             NOT NULL,

    FOREIGN KEY (super_ssn) REFERENCES COMPANY.EMPLOYEE(ssn),
    FOREIGN KEY (dno) REFERENCES COMPANY.DEPARTMENT(d_number),
);

ALTER TABLE COMPANY.DEPARTMENT ADD CONSTRAINT mgr_ssn FOREIGN KEY (mgr_ssn) REFERENCES COMPANY.EMPLOYEE(ssn);

CREATE TABLE COMPANY.DEP_LOCATIONS (
    d_number        INT             NOT NULL,
    d_location      VARCHAR(15)     NOT NULL,

    PRIMARY KEY (d_number, d_location),
    FOREIGN KEY (d_number) REFERENCES COMPANY.DEPARTMENT(d_number)
);

CREATE TABLE COMPANY.PROJECT (
    p_number        INT             NOT NULL        PRIMARY KEY,
    p_name          VARCHAR(15)     NOT NULL        UNIQUE,
    p_location      VARCHAR(15),
    d_num           INT             NOT NULL,

    FOREIGN KEY (d_num) REFERENCES COMPANY.DEPARTMENT(d_number)
);

CREATE TABLE COMPANY.WORKS_ON (
    essn            INT             NOT NULL,
    pno             INT             NOT NULL,
    hours           DECIMAL(3,1)    NOT NULL,

    PRIMARY KEY (essn,pno),
    FOREIGN KEY (essn) REFERENCES COMPANY.EMPLOYEE(ssn),
    FOREIGN KEY (pno) REFERENCES COMPANY.PROJECT(p_number)
);

CREATE TABLE COMPANY.DEPENDENT (
    essn            INT             NOT NULL,
    dependent_name  VARCHAR(15)     NOT NULL,
    sex             CHAR,
    birth_date      DATE,
    relationship    VARCHAR(8),

    PRIMARY KEY (essn, dependent_name),
    FOREIGN KEY (essn) REFERENCES COMPANY.EMPLOYEE(ssn)
);