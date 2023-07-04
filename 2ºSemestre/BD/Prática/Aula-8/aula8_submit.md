# BD: Guião 8


## ​8.1. Complete a seguinte tabela.
Complete the following table.

| #    | Query                                                                                                      | Rows  | Cost  | Pag. Reads | Time (ms) |       Index used                 |       Index Op.          |                                                                             Discussion                                                                                |
| :--- | :--------------------------------------------------------------------------------------------------------- | :---- | :---- | :--------- | :-------- | :------------------------------- | :----------------------- | :-------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 1    | SELECT * from Production.WorkOrder                                                                         | 72591 | 0.488 | 552        | 1104      | WorkOrderID                      | Clustered Index Scan     | Como necessita de percorrer as linhas todas, os tempos obtidos são altos.                                                                                             |
| 2    | SELECT * from Production.WorkOrder where WorkOrderID=1234                                                  | 1     | 0.003 | 26         | 162       | WorkOrderID                      | Clustered Index Seek     | Como não existe cache, tem de percorrer até encontrar o valor.                                                                                                        |
| 3.1  | SELECT * FROM Production.WorkOrder WHERE WorkOrderID between 10000 and 10010                               | 11    | 0.003 | 2          | 149       | WorkOrderID                      | Clustered Index Seek     | Como a cache foi limpa, é necessário percorrer até encontrar as páginas com o valor dentro do intervalo                                                               |
| 3.2  | SELECT * FROM Production.WorkOrder WHERE WorkOrderID between 1 and 72591                                   | 72591 | 0.488 | 744        | 1230      | WorkOrderID                      | Clustered Index Seek     | Visto que a query inclui um intervalo que inclui todas as páginas é necessário percorrê-as todas.                                                                     |
| 4    | SELECT * FROM Production.WorkOrder WHERE StartDate = '2007-06-25'                                          | 41    | 0.523 | 748        | 209       | WorkOrderID                      | Clustered Index Scan     | Uma vez que efetuada uma pesquisa por string, é necessário percorrer um grsnde número de páginas.                                                                     |
| 5    | SELECT * FROM Production.WorkOrder WHERE ProductID = 757                                                   | 11    | 0.037 | 238        | 259       | ProductID                        | Non Clustered Index Seek | Como o número de linhas retornar é pequeno, é utilizado um non clustered index de forma eficiente embora o número de páginas lidas seja elevado                       |
| 6.1  | SELECT WorkOrderID, StartDate FROM Production.WorkOrder WHERE ProductID = 757                              | 9     | 0.037 | 20         | 102       | ProductID Covered                | Non Clustered Index Seek |                                                                                                                                                                       |
| 6.2  | SELECT WorkOrderID, StartDate FROM Production.WorkOrder WHERE ProductID = 945                              | 1105  | 0.474 | 554        | 110       | ProductID Covered                | Clustered Index Scan     | Apesar de a query ser a mesma, o número de linhas influencia o tipo de operação. Num número superior de linhas, é mais vantajoso para o SGBD fazer um Clustered Scan. |
| 6.3  | SELECT WorkOrderID FROM Production.WorkOrder WHERE ProductID = 945 AND StartDate = '2006-01-04'            | 1     | 0.474 | 1160       | 11        | ProductID Covered                | Clustered Index Scan     | Como é necessário procurar por uma string, é imperativo percorrer todo o índice.                                                                                      |
| 7    | SELECT WorkOrderID, StartDate FROM Production.WorkOrder WHERE ProductID = 945 AND StartDate = '2006-01-04' | 1     | 0.474 | 556        | 139       | ProductID e StartDate            | Clustered Index Scan     |                                                                                                                                                                       |
| 8    | SELECT WorkOrderID, StartDate FROM Production.WorkOrder WHERE ProductID = 945 AND StartDate = '2006-01-04' | 1     | 0.474 | 556        | 138       | Composite (ProductID, StartDate) | Clustered Index Scan     |                                                                                                                                                                       |

## ​8.2.

### a)

```
DROP TABLE IF EXISTS mytemp;

CREATE TABLE mytemp ( 
	rid BIGINT /*IDENTITY (1, 1)*/ PRIMARY KEY, 
	at1 INT NULL, 
	at2 INT NULL, 
	at3 INT NULL, 
	lixo varchar(100) NULL 
);
```

### b)

```
46526 milisegundos.
99,17% de fragmentação do índice.
68,40% de ocupação das páginas.
```

### c)

```
Fillfactor 65: 45790 milisegundos.
Fillfactor 80: 47054 milisegundos.
Fillfactor 90: 46060 milisegundos.

Não se encontrou nenhuma tendência nos dados, uma vez que as diferenças existentes são pouco significativas e dependem das condições de teste.
```

### d)

```
40046 milisegundos.
```

### e)

```
95393 milisegundos.
Conclui-se que o aumento do número de índices influencia negativamente o tempo de execução das queries. Assim, é importante apenas criar índices para os autributos que sejam realmente importantes de indexar.
```

## ​8.3.

```
1. CREATE CLUSTERED INDEX IdxEmployeeSsn ON EMPLOYEE(Ssn)
2. CREATE INDEX IdxEmployeeName ON EMPLOYEE(Fname, Lname)
3. CREATE CLUSTERED INDEX IdxEmployeeDepartment ON EMPLOYEE(Dno)
4. CREATE INDEX IdxProjectEmployees ON WORKS_ON(Pnumber)
5. CREATE INDEX IdxEmployeeDependents ON DEPENDENT(Essn)
6. CREATE CLUSTERED INDEX IdxDepartmentProjects ON PROJECT(Dnum)
```
