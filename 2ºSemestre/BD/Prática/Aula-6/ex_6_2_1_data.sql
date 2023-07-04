INSERT INTO COMPANY.DEPARTMENT(d_name, d_number, mgr_ssn, mgr_start_date)
    VALUES ('Investigacao',1,NULL,'2010/08/02'),
           ('Comercial',2,NULL,'2013/05/16'),
           ('Logistica',3,NULL,'2013/05/16'),
           ('Recursos Humanos',4,NULL,'2014/04/02'),
           ('Desporto',5,NULL,NULL);

INSERT INTO COMPANY.EMPLOYEE (ssn, f_name, m_init, l_name, birth_date, address, sex, salary, super_ssn, dno)
    VALUES (183623612,'Paula','A','Sousa','2001/08/11','Rua da FRENTE','F',1450.00,NULL,3),
           (21312332,'Carlos','D','Gomes','2000/01/01','Rua XPTO','M',1200.00,NULL,1),
           (321233765,'Juliana','A','Amaral','1980/08/11','Rua BZZZZ','F',1350.00,NULL,3),
           (342343434,'Maria','I','Pereira','2001/05/01','Rua JANOTA','F',1250.00,21312332,2),
           (41124234,'Joao','G','Costa','2001/01/01','Rua YGZ','M',1300.00,21312332,2),
           (12652121,'Ana','L','Silva','1990/03/03','Rua ZIG ZAG','F',1400.00,21312332,2);
SELECT * FROM COMPANY.EMPLOYEE;

UPDATE COMPANY.DEPARTMENT SET mgr_ssn=21312332 WHERE d_number=1;
UPDATE COMPANY.DEPARTMENT SET mgr_ssn=321233765 WHERE d_number=2;
UPDATE COMPANY.DEPARTMENT SET mgr_ssn=41124234 WHERE d_number=3;
UPDATE COMPANY.DEPARTMENT SET mgr_ssn=12652121 WHERE d_number=4;
SELECT * FROM COMPANY.DEPARTMENT;

INSERT INTO COMPANY.DEPENDENT(essn, dependent_name, sex, birth_date, relationship)
    VALUES (21312332,'Joana Costa','F','2008/04/01','Filho'),
           (21312332,'Maria Costa','F','1990/10/05','Neto'),
           (21312332,'Rui Costa','M','2000/08/04','Neto'),
           (321233765,'Filho Lindo','M','2001/02/22','Filho'),
           (342343434,'Rosa Lima','F','2006/03/11','Filho'),
           (41124234,'Ana Sousa','F','2007/04/13','Neto'),
           (41124234,'Gaspar Pinto','M','2006/02/08','Sobrinho');
SELECT * FROM COMPANY.DEPENDENT;

INSERT INTO COMPANY.DEP_LOCATIONS(d_number, d_location)
    VALUES (2,'Aveiro'),
           (3,'Coimbra');
SELECT * FROM COMPANY.DEP_LOCATIONS;

INSERT INTO COMPANY.PROJECT(p_number, p_name, p_location, d_num)
    VALUES (1,'Aveiro Digital','Aveiro',3),
           (2,'BD Open Day','Espinho',2),
           (3,'Dicoogle','Aveiro',3),
           (4,'GOPACS','Aveiro',3);
SELECT * FROM COMPANY.PROJECT

INSERT INTO COMPANY.WORKS_ON(essn, pno, hours)
    VALUES (183623612,1,20.0),
           (183623612,3,10.0),
           (21312332 ,1,20.0),
           (321233765,1,25.0),
           (342343434,1,20.0),
           (342343434,4,25.0),
           (41124234 ,2,20.0),
           (41124234 ,3,30.0);
SELECT * FROM COMPANY.WORKS_ON