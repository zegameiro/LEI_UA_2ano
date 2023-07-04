# BD: Guião 6

## Problema 6.1

### *a)* Todos os tuplos da tabela autores (authors);

```
SELECT * FROM pubs.dbo.authors
```

### *b)* O primeiro nome, o último nome e o telefone dos autores;

```
SELECT au_fname, au_lname, phone FROM pubs.dbo.authors
```

### *c)* Consulta definida em b) mas ordenada pelo primeiro nome (ascendente) e depois o último nome (ascendente); 

```
SELECT au_fname, au_lname, phone FROM authors ORDER BY au_fname, au_lname ASC
```

### *d)* Consulta definida em c) mas renomeando os atributos para (first_name, last_name, telephone); 

```
SELECT au_fname AS first_name, au_lname AS last_name, phone AS telephone  FROM authors ORDER BY au_fname, au_lname ASC
```

### *e)* Consulta definida em d) mas só os autores da Califórnia (CA) cujo último nome é diferente de ‘Ringer’; 

```
SELECT au_fname AS first_name, au_lname AS last_name, phone AS telephone
FROM authors
WHERE au_lname!='Ringer' AND state='CA'
ORDER BY au_fname, au_lname
```

### *f)* Todas as editoras (publishers) que tenham ‘Bo’ em qualquer parte do nome; 

```
SELECT * FROM publishers WHERE pub_name LIKE '%Bo%'
```

### *g)* Nome das editoras que têm pelo menos uma publicação do tipo ‘Business’; 

```
SELECT *
FROM publishers JOIN titles
ON (publishers.pub_id=titles.pub_id AND type='business')
```

### *h)* Número total de vendas de cada editora; 

```
SELECT pub_name, SUM(ytd_sales)
FROM publishers JOIN titles
ON (publishers.pub_id=titles.pub_id)
GROUP BY pub_name
```

### *i)* Número total de vendas de cada editora agrupado por título; 

```
SELECT pub_name, title, SUM(ytd_sales)
FROM publishers JOIN titles
ON (publishers.pub_id=titles.pub_id)
GROUP BY pub_name, title
ORDER BY pub_name
```

### *j)* Nome dos títulos vendidos pela loja ‘Bookbeat’; 

```
SELECT title
FROM titles
INNER JOIN sales ON titles.title_id = sales.title_id
INNER JOIN stores ON sales.stor_id = stores.stor_id
WHERE stores.stor_name = 'Bookbeat'
ORDER BY titles.title
```

### *k)* Nome de autores que tenham publicações de tipos diferentes; 

```
SELECT DISTINCT authors.au_fname, authors.au_lname
FROM authors
INNER JOIN titleauthor ON authors.au_id = titleauthor.au_id
INNER JOIN titles ON titleauthor.title_id = titles.title_id
INNER JOIN (
    SELECT titleauthor.au_id
    FROM titleauthor
    INNER JOIN titles ON titleauthor.title_id = titles.title_id
    GROUP BY titleauthor.au_id
    HAVING COUNT(DISTINCT titles.type) > 1
) AS subquery ON authors.au_id = subquery.au_id
ORDER BY authors.au_lname, authors.au_fname;
```

### *l)* Para os títulos, obter o preço médio e o número total de vendas agrupado por tipo (type) e editora (pub_id);

```
SELECT type AS book_type, pub_name AS publisher, AVG(titles.price) AS price_avg, SUM(ytd_sales) AS total_sales
FROM titles JOIN publishers ON titles.pub_id = publishers.pub_id
GROUP BY type, pub_name
HAVING AVG(titles.price) IS NOT NULL;
```

### *m)* Obter o(s) tipo(s) de título(s) para o(s) qual(is) o máximo de dinheiro “à cabeça” (advance) é uma vez e meia superior à média do grupo (tipo);

```
SELECT title, advance, average
FROM titles JOIN (
                 SELECT titles.type, AVG(advance) AS average
		         FROM titles
			     WHERE advance IS NOT NULL
			     GROUP BY titles.type
			) AS grp
ON titles.type = grp.type AND advance > 1.5*average;
```

### *n)* Obter, para cada título, nome dos autores e valor arrecadado por estes com a sua venda;

```
SELECT title, au_fname AS first_name, au_lname AS last_name, sum(ytd_sales) AS money_made
FROM authors JOIN titleauthor ON authors.au_id = titleauthor.au_id
JOIN titles ON titles.title_id = titleauthor.title_id
JOIN sales ON titles.title_id = sales.title_id
GROUP BY title, au_fname, au_lname
ORDER BY title, au_fname, au_lname
```

### *o)* Obter uma lista que incluía o número de vendas de um título (ytd_sales), o seu nome, a faturação total, o valor da faturação relativa aos autores e o valor da faturação relativa à editora;

```
SELECT DISTINCT titles.title, ytd_sales,
       ytd_sales * price AS facturacao,
       ytd_sales * royalty * price / 100 AS auths_revenue,
       ytd_sales * price * (100 - royalty) / 100 AS pub_revenue
FROM titles
INNER JOIN sales ON titles.title_id = sales.title_id;
```

### *p)* Obter uma lista que incluía o número de vendas de um título (ytd_sales), o seu nome, o nome de cada autor, o valor da faturação de cada autor e o valor da faturação relativa à editora;

```
SELECT DISTINCT titles.title, au_fname + ' ' + au_lname AS name, ytd_sales,
				ytd_sales * price AS faturacao,
				ytd_sales * royalty * price/100 AS authors_revenue,
				ytd_sales * price * (100-royalty)/100 AS publisher_revenue
FROM titles
INNER JOIN sales ON titles.title_id = sales.title_id
INNER JOIN titleauthor ON titles.title_id = titleauthor.title_id
INNER JOIN authors a on titleauthor.au_id = a.au_id
```

### *q)* Lista de lojas que venderam pelo menos um exemplar de todos os livros;

```
SELECT stores.stor_name, COUNT(DISTINCT titles.title) AS different
FROM stores
INNER JOIN sales ON stores.stor_id = sales.stor_id
INNER JOIN titles ON sales.title_id = titles.title_id
GROUP BY stores.stor_name
HAVING COUNT(DISTINCT titles.title) = (SELECT COUNT(titles.title) FROM titles);
```

### *r)* Lista de lojas que venderam mais livros do que a média de todas as lojas;

```
SELECT stor_name, sum(qty) AS sum_qty
FROM sales JOIN stores
ON sales.stor_id=stores.stor_id
GROUP BY stor_name
HAVING sum(qty) > (	SELECT avg(sum_qty)
						FROM (	SELECT sum(qty) AS sum_qty, stor_id AS stid
								FROM sales
								GROUP BY stor_id) as T
					);
```

### *s)* Nome dos títulos que nunca foram vendidos na loja “Bookbeat”;

```
SELECT titles.title
FROM titles
LEFT JOIN (
    SELECT sales.title_id
    FROM sales
    INNER JOIN stores ON sales.stor_id = stores.stor_id
    WHERE stores.stor_name = 'Bookbeat'
) AS bookbeat_sales ON titles.title_id = bookbeat_sales.title_id
WHERE bookbeat_sales.title_id IS NULL;
```

### *t)* Para cada editora, a lista de todas as lojas que nunca venderam títulos dessa editora; 

```
(SELECT pub_name, stor_name
FROM stores, publishers )
EXCEPT
(SELECT pub_name, stor_name
FROM publishers JOIN (	SELECT pub_id AS ppid, sales.stor_id, stor_name
						FROM titles JOIN sales
						ON titles.title_id=sales.title_id
						JOIN stores
						ON sales.stor_id=stores.stor_id) AS T ON pub_id=ppid);
```

## Problema 6.2

### ​5.1

#### a) SQL DDL Script
 
[a) SQL DDL File](ex_6_2_1_ddl.sql "SQLFileQuestion")

#### b) Data Insertion Script

[b) SQL Data Insertion File](ex_6_2_1_data.sql "SQLFileQuestion")

#### c) Queries

##### *a)*

```
SELECT p_number, p_name, ssn, CONCAT(f_name, ' ', m_init, ' ', l_name) AS name
FROM COMPANY.EMPLOYEE AS E
    JOIN COMPANY.WORKS_ON WO ON E.ssn = WO.essn
    JOIN COMPANY.PROJECT P ON WO.pno = P.p_number
ORDER BY p_number
```

##### *b)* 

```
SELECT CONCAT(f_name, ' ', m_init, ' ', l_name) AS name
FROM COMPANY.EMPLOYEE AS E
    JOIN (SELECT ssn
          FROM COMPANY.EMPLOYEE
          WHERE f_name='Carlos' AND m_init='D' AND l_name='Gomes') as SE
    ON E.super_ssn=SE.ssn;
```

##### *c)* 

```
SELECT p_name, totalHours
FROM COMPANY.PROJECT
    JOIN (SELECT pno, sum(hours) AS totalHours
          FROM COMPANY.WORKS_ON
          GROUP BY pno) AS WO
    ON PROJECT.p_number = WO.pno
```

##### *d)* 

```
SELECT CONCAT(f_name, ' ', m_init, ' ', l_name) AS name
FROM COMPANY.EMPLOYEE AS E
    JOIN (SELECT essn
          FROM COMPANY.WORKS_ON AS WO
              JOIN (SELECT p_number
                  FROM COMPANY.PROJECT
                  WHERE p_name = 'Aveiro Digital') AS AP
              ON WO.pno = AP.p_number
          WHERE hours > 20) AS TWENTYPLUS
    ON E.ssn = TWENTYPLUS.essn
WHERE E.dno = 3
```

##### *e)* 

```
SELECT CONCAT(f_name, ' ', m_init, ' ', l_name) AS name
FROM COMPANY.EMPLOYEE AS E
    LEFT JOIN (SELECT essn
               FROM COMPANY.WORKS_ON) AS WO
    ON E.ssn = WO.essn
WHERE essn IS NULL
```

##### *f)* 

```
SELECT d_name, AVG(salary) AS avgSalary
FROM COMPANY.EMPLOYEE AS E
    JOIN (SELECT d_number, d_name FROM COMPANY.DEPARTMENT) AS D
    ON E.dno = D.d_number
WHERE sex='F'
GROUP BY dno, d_name
```

##### *g)* 

```
SELECT *
FROM COMPANY.EMPLOYEE AS E
    JOIN (SELECT essn, COUNT(*) AS depCount
        FROM COMPANY.DEPENDENT
        GROUP BY essn
        HAVING COUNT(*) > 2) AS MTTD
    ON E.ssn = MTTD.essn
```

##### *h)* 

```
SELECT DISTINCT CONCAT(f_name, ' ', m_init, ' ', l_name) AS name
FROM COMPANY.EMPLOYEE AS E
    JOIN (SELECT mgr_ssn FROM COMPANY.DEPARTMENT) AS D ON E.ssn = D.mgr_ssn
    LEFT OUTER JOIN (SELECT essn FROM COMPANY.DEPENDENT) AS DP ON E.ssn = DP.essn
WHERE DP.essn IS NULL
```

##### *i)* 

```
SELECT CONCAT(f_name, ' ', m_init, ' ', l_name) AS name, address
FROM COMPANY.EMPLOYEE AS E
    JOIN (
        -- Essn of employees working in projects located in Aveiro whose department is not located there
        SELECT essn
        FROM COMPANY.WORKS_ON AS WO
            JOIN (
                -- Projects in Aveiro whose department is not located there
                SELECT p_number
                FROM (SELECT * FROM COMPANY.PROJECT WHERE p_location = 'Aveiro') as P -- Projects in Aveiro
                    JOIN (
                        -- Departments not in Aveiro
                        SELECT d_name, D.d_number
                        FROM COMPANY.DEPARTMENT AS D
                            FULL OUTER JOIN (SELECT * FROM COMPANY.DEP_LOCATIONS WHERE d_location='Aveiro') AS DL
                            ON D.d_number = DL.d_number
                        WHERE d_location IS NULL) AS DEP_NOT_AV
                    ON DEP_NOT_AV.d_number=P.d_num) AS AV_PROJ_DEP_NOT_AV
            ON WO.pno=AV_PROJ_DEP_NOT_AV.p_number) AS ESSN_AV_PROJ_DEP_NOT_AV
    ON ESSN_AV_PROJ_DEP_NOT_AV.essn=E.ssn
```

### 5.2

#### a) SQL DDL Script
 
[a) SQL DDL File](ex_6_2_2_ddl.sql "SQLFileQuestion")

#### b) Data Insertion Script

[b) SQL Data Insertion File](ex_6_2_2_data.sql "SQLFileQuestion")

#### c) Queries

##### *a)*

```
SELECT nif, nome, fax, endereco, condpag, tipo
FROM GESTSTOCK.FORNECEDOR AS F
    LEFT OUTER JOIN GESTSTOCK.ENCOMENDA E ON F.nif = E.fornecedor
WHERE numero IS NULL
```

##### *b)* 

```
SELECT nome, AVG(I.unidades) AS sumUnidades
FROM GESTSTOCK.ITEM AS I
    JOIN (SELECT codigo, nome FROM GESTSTOCK.PRODUTO) as P
    ON I.codProd = P.codigo
GROUP BY codProd, nome
ORDER BY nome
```


##### *c)* 

```
SELECT AVG(CAST(countProd AS DECIMAL(10,2))) AS average
FROM (SELECT numEnc, count(*) AS countProd
    FROM GESTSTOCK.ITEM
    GROUP BY numEnc) AS CNT
```


##### *d)* 

```
SELECT F.nif, F.nome, I.codProd, P.nome, I.unidades
FROM GESTSTOCK.ITEM AS I
    JOIN (SELECT numero, fornecedor FROM GESTSTOCK.ENCOMENDA) AS E ON E.numero = I.numEnc
    JOIN (SELECT nif, nome FROM GESTSTOCK.FORNECEDOR) AS F ON E.fornecedor = F.nif
    JOIN (SELECT codigo, nome FROM GESTSTOCK.PRODUTO) AS P ON P.codigo=I.codProd
ORDER BY nif
```

### 5.3

#### a) SQL DDL Script
 
[a) SQL DDL File](ex_6_2_3_ddl.sql "SQLFileQuestion")

#### b) Data Insertion Script

[b) SQL Data Insertion File](ex_6_2_3_data.sql "SQLFileQuestion")

#### c) Queries

##### *a)*

```
SELECT PAC.numUtente, nome, dataNasc, endereco
FROM PRESCRICAO.PACIENTE AS PAC
    LEFT OUTER JOIN (SELECT numPresc, numUtente FROM PRESCRICAO.PRESCRICAO) AS PRESC
    ON PAC.numUtente = PRESC.numUtente
WHERE numPresc IS NULL
```

##### *b)* 

```
SELECT M.especialidade, COUNT(*) AS totalPresc
FROM PRESCRICAO.PRESCRICAO AS P
    JOIN PRESCRICAO.MEDICO M ON M.numSNS = P.numMedico
GROUP BY M.especialidade
```


##### *c)* 

```
SELECT nome, COUNT(*) AS countPresc
FROM PRESCRICAO.FARMACIA AS F
    JOIN (SELECT numPresc, farmacia FROM PRESCRICAO.PRESCRICAO) AS P
    ON F.nome = P.farmacia
GROUP BY nome
```


##### *d)* 

```
(SELECT nome
 FROM PRESCRICAO.FARMACO
 WHERE numRegFarm=906)
EXCEPT
(SELECT DISTINCT nomeFarmaco
 FROM PRESCRICAO.PRESC_FARMACO
 WHERE numRegFarm=906)
```

##### *e)* 

```
SELECT farmacia, nome, COUNT(nomeFarmaco) AS nFarmacos
FROM (SELECT numPresc, farmacia FROM PRESCRICAO.PRESCRICAO) AS P
    JOIN PRESCRICAO.PRESC_FARMACO AS PF ON P.numPresc = PF.numPresc
    JOIN (SELECT numReg, nome FROM PRESCRICAO.FARMACEUTICA) AS F ON PF.numRegFarm = F.numReg
WHERE farmacia IS NOT NULL
GROUP BY farmacia, nome
ORDER BY farmacia
```

##### *f)* 

```
SELECT P.numUtente, nome, dataNasc, endereco, numMedicos
FROM PRESCRICAO.PACIENTE AS P
    JOIN (SELECT numUtente, COUNT(DISTINCT numMedico) AS numMedicos
          FROM PRESCRICAO.PRESCRICAO
          GROUP BY numUtente) AS NUM_MEDICOS
    ON P.numUtente = NUM_MEDICOS.numUtente
WHERE numMedicos > 1
```
