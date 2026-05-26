# Sistema de Transferências Bancárias — API REST com Spring Boot

Evolução do projeto [TransferenciaBancaria](https://github.com/PedrooArthurdev/TransferenciaBancaria), que foi construído em Java puro. Nessa versão o sistema foi transformado em uma API REST real usando Spring Boot, permitindo que qualquer cliente — app mobile, site ou outro servidor — consuma as funcionalidades via HTTP.

A maior dificuldade foi entender onde cada peça do framework se encaixa, já que até então tudo era feito manualmente. O que surpreendeu foi o quanto o Spring Boot elimina trabalho repetitivo — coisas que levariam horas de configuração manual funcionam com algumas anotações.

---

## O que a API faz

- Processa transferências entre contas bancárias via requisição HTTP
- Valida regras de negócio antes de qualquer movimentação de saldo
- Retorna erros claros com os status HTTP corretos
- Mantém histórico das transações aprovadas em memória

---

## Endpoints

| Método | Rota | Descrição |
|--------|------|-----------|
| GET | /transferencias/status | Verifica se a API está no ar |
| POST | /transferencias | Realiza uma transferência |
| GET | /transferencias/historico | Lista todas as transações aprovadas |

### Exemplo de requisição

```json
POST /transferencias
Content-Type: application/json

{
    "contaOrigem": "4102-8",
    "contaDestino": "9487-8",
    "valor": 2500.51
}
```

### Exemplo de resposta de sucesso

```
Transferência realizada! Status: APROVADA | Valor: R$ 2500.51
```

### Exemplos de erro

```
400 Bad Request → Saldo insuficiente
400 Bad Request → Conta de origem está inativa
400 Bad Request → Valor deve ser positivo
```

---

## Regras de negócio

Antes de processar qualquer transferência o sistema valida três condições:

1. A conta de origem precisa estar ativa
2. O valor precisa ser positivo
3. O saldo da conta de origem precisa cobrir o valor da transferência

Se qualquer validação falhar, a transferência é rejeitada com uma mensagem de erro e nenhum saldo é alterado.

---

## Estrutura do projeto

```
src/main/java/com/transferencia/bancaria
├── controller
│   └── TransferenciaController.java
├── dto
│   └── TransferenciaRequest.java
├── exception
│   ├── ContaInativaException.java
│   └── SaldoInsuficienteException.java
├── model
│   ├── Cliente.java
│   ├── Conta.java
│   ├── StatusTransacao.java
│   └── Transacao.java
├── service
│   └── TransferenciaService.java
└── BancariaApplication.java
```

---

## Tecnologias

- Java 17
- Spring Boot 3.5.14
- Spring Web (API REST)
- Maven
- Postman para testes

---

## Conceitos aplicados

- API REST com Spring Boot
- Injeção de dependência com construtor
- Tratamento de exceções com `@ExceptionHandler` e status HTTP semânticos
- Separação de responsabilidades entre controller, service e model
- DTO para receber dados da requisição (`@RequestBody`)
- Anotações do Spring (`@RestController`, `@RequestMapping`, `@Service`)

---

## Como rodar

**Pré-requisitos:** Java 17 ou superior e Maven instalados.

```bash
git clone https://github.com/PedrooArthurdev/TransferenciaBancaria-SpringBoot.git
cd TransferenciaBancaria-SpringBoot/bancaria
./mvnw spring-boot:run
```

A API sobe na porta `8080`. Acesse `http://localhost:8080/transferencias/status` para confirmar.

---

## Próximos passos

- [ ] Persistência com JPA e PostgreSQL
- [ ] Validação de campos com Bean Validation
- [ ] Testes automatizados com JUnit e Mockito

---

## Autor

Pedro — desenvolvedor em formação, construindo projetos reais para aprender de verdade.

[![LinkedIn](https://img.shields.io/badge/LinkedIn-blue?style=flat&logo=linkedin)](https://linkedin.com/in/seu-perfil)
[![GitHub](https://img.shields.io/badge/GitHub-black?style=flat&logo=github)](https://github.com/PedrooArthurdev)
