# **Rascunho do exercício 3.3** | 2.1, aula 2
## **Relações**
EMPRESA(NIF)

PRODUTO(Código, Preço, Taxa_de_IVA, Unidades_em_armazém, NIF_empresa)

ENCOMENDA(Número, Data, NIF_fornecedor)

<!-- relação N:M entre PRODUTO e ENCOMENDA -->
REL_PROD_ENC(Código_produto, Número_encomenda)

FORNECEDOR(NIF, Nome, Fax, Código_do_tipo, Condições_de_pagamento)

<!-- relação N:M entre ENCOMENDA e FORNECEDOR -->
REL_ENC_FORN(Número_encomenda, NIF_fornecedor)

## **Chaves**
### EMPRESA
- Chaves candidatas: NIF
- Chave primária: NIF
- Chaves estrangeiras: ---

### PRODUTO
- Chaves candidatas: Código
- Chave primária: Código
- Chaves estrangeiras: NIF(Empresa)

### ENCOMENDA
- Chaves candidatas: Número
- Chave primária: Número
- Chaves estrangeiras: NIF(Fornecedor)

### FORNECEDOR
- Chaves candidatas: NIF, Nome, Fax
- Chave primária: NIF
- Chaves estrangeiras: ---

### REL_PROD_ENC
<!-- relação N:M entre PRODUTO e ENCOMENDA -->
- Chaves candidatas: Código(Produto), Número(Encomenda)
- Chaves primárias: Código(Produto), Número(Encomenda)
- Chaves estrangeiras: Código(Produto), Número(Encomenda)

### REL_ENC_FORN
<!-- relação N:M entre ENCOMENDA e FORNECEDOR -->
- Chaves candidatas: Número(Encomenda), NIF(Fornecedor)
- Chaves primárias: Número(Encomenda), NIF(Fornecedor)
- Chaves estrangeiras: Número(Encomenda), NIF(Fornecedor)