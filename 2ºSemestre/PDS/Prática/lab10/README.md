# Aula10 - Notes

## Exercício 3

### Enunciado:

Enunciado:
Foste contratado como programador Java por uma empresa de transporte de passageiros que opera nas cidades de Leiria e Castelo Branco. A empresa pretende melhorar a comunicação entre estas duas cidades e decidiu implementar um sistema de mensagens utilizando o padrão de design Mediator.

A tua tarefa consiste em desenvolver um programa em Java que demonstre a utilização do padrão Mediator para a troca de mensagens entre as entidades "Leiria" e "Castelo Branco".

O sistema deve permitir que cada entidade envie mensagens para o Mediator, que, por sua vez, reencaminhará as mensagens para todas as outras entidades registadas. Cada entidade deve ser capaz de receber as mensagens enviadas pelos outros participantes.

Implementa as classes necessárias seguindo o padrão de design Mediator e cria uma classe de teste onde deverás instanciar o Mediator e realizar a troca de mensagens entre elas.

### Solução:

Para este exercício, criámos uma interface "Mediator" com os métodos "sendMessage(String message, Local l)" e "addLocal(Local local)". As duas localidades podem enviar e receber mensagens, por isso cria-se a classe abstrata "ChatMediator" que implementa a interface "Mediator". As localidades são do tipo Cidade e, após criadas na main, estas poderão (depois de ser adicionadas), enviar e receber mensagens.

### Referências:

- [Mediator Design Pattern in Java](https://www.digitalocean.com/community/tutorials/mediator-design-pattern-java?_x_tr_hist=true)

- [The Mediator Pattern in Java](https://www.baeldung.com/java-mediator-pattern)