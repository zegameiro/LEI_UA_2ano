# **Rascunho do exercício 3.3b** | 2.2, aula 2
## **Relações**
MÉDICO(Número, Nome, Especialidade)

PRESCRIÇÃO(Número, Data_criação, Data_aviamento, Número_médico, Número_utente_paciente, NIF_Farmácia)

PACIENTE(Número_utente, Nome, Data_nascimento, Endereço)

FÁRMACO(Fórmula)

REL_PRESC_FARMACO(Número_prescrição, Fórmula_fármaco)

NOME_COMERCIAL(nome, Fórmula_fármaco)

FARMACÊUTICA(Número_registo, Nome, Telefone, Endereço)

REL_FARMACO_FARMACEUTICA(Fórmula_fármaco, Número_registo_farmacêutica)

FARMÁCIA(NIF, Nome, Telefone, Endereço)

REL_FARMACO_FARMACIA(Fórmula_fármaco, NIF_Farmácia)


## **Chaves**
### MÉDICO
- Chaves candidatas: Número
- Chave primária: Número
- Chaves estrangeiras: ---

### PRESCRIÇÃO
- Chaves candidatas: Número, Número(Médico), Número_utente(Paciente)
- Chaves primárias: Número, Número(Médico), Número_utente(Paciente)
- Chaves estrangeiras: Número(Médico), Número_utente(Paciente)

### PACIENTE
- Chaves candidatas: Número_utente
- Chave primária: Número_utente
- Chaves estrangeiras: ---

### FÁRMACO
- Chaves candidatas: Fórmula
- Chave primária: Fórmula
- Chaves estrangeiras: ---

### REL_PRESC_FARMACO
<!-- relação N:M entre PRESCRIÇÃO e FÁRMACO -->
- Chaves candidatas: Número(Prescrição), Fórmula(Fármaco)
- Chaves primárias: Número(Prescrição), Fórmula(Fármaco)
- Chaves estrangeiras: Número(Prescrição), Fórmula(Fármaco)

### NOME_COMERCIAL
- Chaves candidatas: Nome
- Chave primária: Nome
- Chaves estrangeiras: ---

### FARMACÊUTICA
- Chaves candidatas: Número_registo, telefone
- Chave primária: Número_registo
- Chaves estrangeiras: ---

### REL_FARMACO_FARMACEUTICA
<!-- relação N:M entre FÁRMACO e FARMACÊUTICA -->
- Chaves candidatas: Fórmula(Fármaco), Número_registo(Farmacêutica)
- Chaves primárias: Fórmula(Fármaco), Número_registo(Farmacêutica)
- Chaves estrangeiras: Fórmula(Fármaco), Número_registo(Farmacêutica)

### FARMÁCIA
- Chaves candidatas: NIF, telefone
- Chave primária: NIF
- Chaves estrangeiras: ---

### REL_FARMACO_FARMACIA
<!-- relação N:M entre FÁRMACO e FARMÁCIA -->
- Chaves candidatas: Fórmula(Fármaco), NIF(Farmácia)
- Chaves primárias: Fórmula(Fármaco), NIF(Farmácia)
- Chaves estrangeiras: Fórmula(Fármaco), NIF(Farmácia)