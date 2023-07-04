# BD: Guião 7


## ​7.2 
 
### *a)*

```
A relação apresentada encontra-se na primeira forma normal, pois todos os atributos são atómicos e não existem relações dentro de relações.

A relação apresentada não se encontra na segunda forma normal, visto que existem dependências parciais, como:

- Na última dependência funcional apresentada (Editor -> Endereco_Editor) 

It is possible to <u>underline</u> -> {A, B,.. }
-> R1 (_A_, B, C)
-> R2 (B,F)
```

### *b)* 

```
Para que a relação apresentada fique normalizada na forma normal 2 é necessário remover as dependências parciais:

R1 (_Titulo_Livro_, _Nome_Autor_, Editor, Tipo_Livro, NoPaginas, Ano_Publicacai, Enereco_Editor, Ano_Publicacao)
    - Titulo_Livro, Nome_Autor -> Editor, Tipo_livro, NoPaginas, Ano_Publicacao
    - Tipo_Livro, NoPaginas -> Preco
    - Editor -> Endereco_Editor

R2 (_Nome_Autor_, Afiliacao_Autor)
    - Nome_Autor -> Afiliacao_Autor

Encontra-se agora na 2FN, no entanto esta não se encontra na 3FN pois esta contém dependências transitivas, removendo-as ficamos com:

R1 (_Titulo_Livro_, _Nome_Autor_, Tipo_Livro, NoPaginas, Editor, Ano_Publicacao)
    - Titulo_Livro, Nome_Autor -> Tipo_Livro, NoPaginas, Editor, Ano_Publicacao

R2 (_Tipo_Livro_,_No_Paginas_,Preco)
    - Tipo_Livro, NoPaginas -> Preco

R3 (_Editor_, Endereco_Editor)
    - Editor -> Endereco_Editor

R4 (_Nome_Autor_, Afiliacao_Autor)
    - Nome_Autor -> Afiliacao_Autor
```




## ​7.3
 
### *a)*

```
As chaves de R são A e B
```


### *b)* 

```
R1 (_A_, _B_, C)
    - A, B -> C

R2 (_A_, D, E, I, J)
    - A -> D, E
    - D -> I, J

R3 (_B_, F, G, H)
    - B -> F
    - F -> G, H
```


### *c)* 

```

R1 (_A_, _B_, C)
    - A, B -> C

R2 (_A_, D, E)
    - A -> D, E

R3 (_D_, I, J)
    - D -> I, J

R4 (_B_, F)
    - B -> F

R5 (_F_, G, H)
    - F -> G, H
```


## ​7.4
 
### *a)*

```
As chaves de R são A e B
```


### *b)* 

```
A relação já se encontra na 2FN e 3FN, pois a sua chave já define todos os outros atributos, pelo que não é preciso fazer decomposições
```


### *c)* 

```
Para converter para a BCNF, todos os atributos têm de ser funcionalmente dependentes da chave da relação de toda a chave e nada mais, assim obtém-se:

R1 (_B_, C, D)
    - B -> C, D

R2 (_D_, E)
    - D -> E

R3 (_C_, A)
    - C -> A
```



## ​7.5
 
### *a)*

```
As chaves da relação R são A e B
```

### *b)* 

```
A relação já se encontra na 2FN, visto que não existem relações parciais
```


### *c)* 

```
A relação já se encontra na 3FN, pois a sua chave define todos os outros atributos, ou seja, não existem dependências transitivas
```

### *d)* 

```
Para converter para a BCNF, todos os atributos têm de ser funcionalmente dependentes da chave da relação de toda a chave e nada mais, assim obtém-se:

R1 (_A_, B, E)
    - A -> B, E

R2 (_A_, C)
    - A -> C

R3 (_C_, D)
    - C -> D

As relações R2 e R3, podem ser transformadas na seguinte R4:

R4 (A, D)
    - A -> D
```
