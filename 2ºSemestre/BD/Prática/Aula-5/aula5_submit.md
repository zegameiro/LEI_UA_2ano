# BD: Guião 5


## ​Problema 5.1
 
### *a)*

```
Write here your answer e.g:
π Pname, Pnumber, Ssn, Fname, Minit, Lname (project ⨝ Pnumber=Pno (employee ⨝ Ssn=Essn works_on))
```


### *b)* 

```
π Fname, Minit, Lname (σ Super_ssn=21312332 (employee))
```


### *c)* 

```
π Pname, totalHours ((γ Pno; totalHours <- sum(Hours) (works_on)) ⨝ Pnumber=Pno (project))
```


### *d)* 

```
π Fname, Lname, Pname, Dno, Hours (σ works_on.Hours > 20 (employee ⨝ Ssn = works_on.Essn ((σ Pname = 'Aveiro Digital' project ) ⨝ Pnumber = Pno works_on)))
```


### *e)* 

```
π Fname, Minit, Lname (employee ⋉ (π Ssn (employee) - π Essn (works_on)))
```


### *f)* 

```
onlyF = (σ Sex='F' (employee))
fSalaryPerDepartment = γ Dno; averageSalary <- avg(Salary) onlyF

π Dname, averageSalary (department ⨝ Dno=Dnumber fSalaryPerDepartment)
```


### *g)* 

```
depCountByEssn = γ Essn; depCount <- count(*) (dependent)
depCountMoreThanTwo = σ depCount>2 (depCountByEssn)

EmpMoreThanTwoDeps = (employee) ⨝ Ssn=Essn (depCountMoreThanTwo)

π Fname, Minit, Lname (EmpMoreThanTwoDeps)
```


### *h)* 

```
managerDepartment =  employee ⨝ Ssn = Mgr_ssn (department)

π Fname, Minit, Lname managerDepartment - π Fname, Minit, Lname (managerDepartment ⨝ Ssn = Essn dependent)
```


### *i)* 

```
aveiroProj = σ Plocation='Aveiro' (project)

depsNotInAveiro = department ▷ σ Dlocation='Aveiro' (dept_location)
avProjWithDepNotInAv = aveiroProj ⨝ Dnumber=Dnum depsNotInAveiro

emp_WO_AvPrWDepNIAv = works_on ⨝ Pno=Pnumber (avProjWithDepNotInAv)
fullTable = employee ⨝ Ssn=Essn emp_WO_AvPrWDepNIAv

π Fname, Minit, Lname, Address (fullTable)
```


## ​Problema 5.2

### *a)*

```
forn_enc = fornecedor ⟕ nif=fornecedor (encomenda)
no_enc_forn = σ fornecedor=null (forn_enc)

π nif, nome, fax, endereco, condpag, tipo (no_enc_forn)
```

### *b)* 

```
γ produto.nome; avg(item.unidades) -> unidades
π item.numEnc, produto.codigo, produto.nome, item.unidades
(produto ⨝ codProd = codigo item)
```


### *c)* 

```
numProd_enc = γ numEnc; countProd <- count(*) (item)
γ ; average <- avg(countProd) (numProd_enc)
```


### *d)* 

```
π fornecedor.nif, fornecedor.nome, produto.codigo, produto.nome, item.unidades
(produto ⨝ item.codProd = codigo (fornecedor ⨝ encomenda.fornecedor = nif (item ⨝ numero = numEnc encomenda)))
```


## ​Problema 5.3

### *a)*

```
fullTable = (paciente ⟕ prescricao)
filtered = σ numPresc=null (fullTable)

π numUtente, nome, dataNasc, endereco (filtered)
```

### *b)* 

```
prescPorMedico = medico ⨝ numSNS=numMedico (prescricao)

γ especialidade; totalPresc <- count(*) (prescPorMedico)
```


### *c)* 

```
fullTable = farmacia ⨝ nome=farmacia prescricao

γ nome; countPresc <- count(*) (fullTable)
```


### *d)* 

```
farm_906 = π nome (σ numRegFarm=906 (farmaco))
farm_ja_presc = π nomeFarmaco (σ numRegFarm=906 (presc_farmaco))

farm_906 - farm_ja_presc
```

### *e)* 

```
γ farmacia.nome, farmaceutica.nome; count(presc_farmaco.nomeFarmaco) -> num_farmacos
π farmacia.nome, farmaceutica.nome, presc_farmaco.nomeFarmaco
(farmaceutica ⨝ presc_farmaco.numRegFarm = numReg
(farmacia ⨝ prescricao.farmacia = nome 
π prescricao.farmacia, presc_farmaco.numRegFarm, presc_farmaco.nomeFarmaco
(prescricao ⨝ numPrescX = numPresc (ρ numPrescX←numPresc (presc_farmaco)))))
```

### *f)* 

```
paciente ⨝ σ num_UU > 1 (γ numUtente; count(numUtente)->num_UU (γ numUtente, numMedico; count(numMedico)-> numPrescricoes prescricao))
```
