# Urna Virtual

## Descrição

O projeto **Urna Virtual** é uma aplicação educativa que simula um sistema de votação online. Ele foi desenvolvido com o objetivo de implementar uma API RESTful para gerenciar a votação e realizar testes automatizados de integração e unitários, cobrindo regras de negócio complexas relacionadas ao processo eleitoral.

O foco principal é o backend, utilizando **Spring Boot**, com persistência de dados em um banco de dados H2, além de implementação de apuração de votos e gerenciamento de eleitores e candidatos. Este projeto também serve como base para futuras integrações com uma interface frontend.

## Funcionalidades

- Cadastro de **Eleitores** e **Candidatos**
- Registro de **Votos** para prefeitos e vereadores
- Apuração dos votos com geração de resultados
- Regras de status de eleitores (Apto, Inativo, Bloqueado, etc.)
- Regras de status de candidatos (Ativo, Inativo)
- Testes automatizados de integração e unitários
- Persistência de enums como inteiros para otimizar a apuração de votos

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
  - Spring Web
  - Spring Data JPA
  - Spring Boot DevTools
  - Spring Boot Actuator
- **H2 Database** (Banco de dados em memória)
- **Lombok** (para simplificação de código)
- **JUnit** (para testes automatizados)
- **Maven** (para gerenciamento de dependências)

## Requisitos do Projeto

Este projeto tem como foco educacional a implementação de testes automatizados e a criação de um backend robusto com regras de negócio bem definidas.

- Eleitores devem ser cadastrados com informações como nome, CPF (opcional), profissão, e status (definido pelo sistema).
- Candidatos devem ser cadastrados com nome, CPF, número único e função (Prefeito ou Vereador).
- Votos devem registrar a data e hora da votação, além dos candidatos escolhidos.
- A apuração deve calcular o total de votos e gerar os resultados sem persistir os dados de apuração.

## Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/urna-virtual.git
   ```

2. Navegue até a pasta do projeto:
   ```bash
   cd urna-virtual
   ```

3. Compile e execute o projeto:
   ```bash
   ./mvnw spring-boot:run
   ```

4. Acesse a aplicação via `http://localhost:8080`.

## Endpoints Principais

- `POST /eleitores` - Cadastra um novo eleitor
- `POST /candidatos` - Cadastra um novo candidato
- `POST /votos` - Registra um voto
- `GET /apuracao` - Realiza a apuração dos votos

## Testes

Este projeto inclui testes unitários e de integração para garantir a consistência das regras de negócio. Para rodar os testes, utilize o comando:

```bash
./mvnw test
```

Os testes garantem que as exceções corretas são lançadas em cenários como tentativas de voto de eleitores inativos, apuração correta dos votos, entre outros.

## Apuração

A apuração dos votos é realizada em tempo de execução e utiliza as seguintes regras:

- Apenas eleitores com status **Apto** podem votar.
- A função de **Prefeito** e **Vereador** é identificada por valores inteiros.
- O resultado da apuração não é persistido no banco de dados.

## Futuras Expansões

- Integração com um frontend para exibição dos resultados de votação.
- Autenticação e autorização com **Spring Security** para proteger a API.
- Persistência em banco de dados relacional externo, como PostgreSQL.

## Licença

Este projeto foi desenvolvido para fins educativos e não possui uma licença definida.
