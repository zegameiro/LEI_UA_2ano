# Aula07 - Notes

## Exercício 1
Este programa em Java implementa o padrão Decorator e tem como objetivo exemplificar a criação de objetos com funcionalidades adicionais, sem alterar a sua estrutura base. São criadas classes para diferentes tipos de funcionários, que podem ter funções adicionais através da composição com outras classes decoradoras. O programa inclui um teste com diferentes instâncias de funcionários, onde são executadas várias ações, como iniciar e terminar trabalhos, colaborar em equipa e planear tarefas.

### Instruções de compilação
Para compilar o programa basta, na pasta practical_g2_02 executar o seguinte comando:
``` 
javac lab07/Ex1/*.java
``` 

### Instruções de execução
Para executar o programa basta, na pasta practical_g2_02 executar o seguinte comando:
```
java lab07.Ex1.Ex1
``` 

## Exercício 2
Este exercício propõe a construção de uma solução geral usando o padrão decorator para ler documentos de qualquer formato e aplicar filtros sobre o texto lido. A implementação é restrita a arquivos TXT e as entidades criadas incluem TextReader para ler o arquivo, TermFilter para separar em palavras, NormalizationFilter para remover acentuação e pontuação, VowelFilter para remover vogais e CapitalizationFilter para capitalizar o texto. 

### Instruções de compilação
Para compilar o programa basta, na pasta practical_g2_02 executar o seguinte comando:
``` 
javac lab07/Ex2/*.java
``` 

### Instruções de execução
Para executar o programa basta, na pasta practical_g2_02 executar o seguinte comando:
```
java lab07.Ex2.Ex2
``` 

## Exercício 3
Neste exercício, é proposto construir uma solução usando o padrão composite para criar produtos, como Bebida, Doce e Conserva, e Caixas que podem conter zero ou mais produtos. A classe Caixa é composta de outras Caixas ou produtos. O programa de teste inclui a criação de diversas Caixas e produtos, adicionando uns aos outros, e o output mostra o nome e o peso total de cada Caixa, além dos produtos que contêm. O objetivo é criar uma estrutura hierárquica para produtos que possam conter outros produtos ou Caixas.

### Instruções de compilação
Para compilar o programa basta, na pasta practical_g2_02 executar o seguinte comando:
``` 
javac lab07/Ex3/*.java
``` 

### Instruções de execução
Para executar o programa basta, na pasta practical_g2_02 executar o seguinte comando:
```
java lab07.Ex3.Cabazes
``` 