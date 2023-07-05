# BD: Trabalho Prático APFE

**Grupo**: P5G3
- José Gameiro, MEC: 108840
- Rúben Garrido, MEC: 107927

## Introdução / Introduction
 
Base de dados relativa à gestão de uma empresa de construção civil. Esta foca-se, sobretudo, nos empregados, nas obras e no seu material respetivo. Para além disso, existe uma gestão de encomendas aos fornecedores, caso não exista material suficiente em armazém.

## ​Análise de Requisitos / Requirements

- Um empregado possui NIF, nome (composto por nome próprio e apelido), e-mail, número de telefone, morada, género, data de nascimento e salário. A este encontra-se igualmente associado um departamento, que possui um ID e um nome. Um CEO é um empregado da empresa.
- Numa obra, caracterizada por ID, localização, data de início e data de fim, e onde trabalham um ou mais empregados (e cujo número de horas efetuadas num dia é guardado), são executados serviços, com ID, nome e categoria. Esta obra está associada a um cliente, que possui NIF, nome (composto por nome próprio e apelido), e-mail, número de telefone e morada.
- Para cada obra, podem ser utilizados materiais de construção, que possuem atibutos como ID, nome, categoria e número de unidades em armazém. Estes materiais podem estar associados a uma ou mais encomendas (com um custo associado por material), caracterizadas por ID e data.
- Cada encomenda está relacionada a uma obra e a um fornecedor, composto por NIF, nome, e-mail, número de telefone e morada.

## DER

![DER Diagram!](/DER_ER/der.png "AnImage")

## ER

![ER Diagram!](/DER_ER/er.png "AnImage")