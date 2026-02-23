# Desafio Cora - Backend (Conta Service)

Este é um componente backend desenvolvido em **Java 21** e **Spring Boot 3.5.11**, para criar e listar contas. O projeto utiliza **Spring Data JPA** para persistência e **H2 Database** como banco de dados em memória.

---

## 🚀 Como Rodar o Backend

Certifique-se de ter o **JDK 21** instalado e configurado no seu PATH.

1. **Clone o repositório:**
   ```bash
   git clone <url-do-repositório>
   cd cora-teste
   
2. Compilar e rodar a aplicação via Gradle:

    ```bash
    ./gradlew bootRun

3. Rodar os testes (MockMvc):

    ```bash
    ./gradlew test

Configurações da Aplicação
- Porta do Servidor: 8080

- Endpoint Base: http://localhost:8080/accounts

- Java 21

- Banco de Dados: H2

🛠️ Acesso ao Banco de Dados

O console do H2 está habilitado para facilitar a visualização dos dados em tempo real enquanto a aplicação está rodando.

- URL de Acesso: http://localhost:8080/h2-console

- JDBC URL: jdbc:h2:mem:testdb

- User: sa

- Password: (deixe em branco)