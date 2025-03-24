# Projeto - Backend API REST (Spring Boot)

## 📌 Visão Geral
Este projeto consiste em uma API REST desenvolvida com **Java e Spring Boot** para armazenar dados inseridos pelo usuário e implementar regras de negócio.

---

## 🏗 Estrutura do Projeto
- `controllers/` - Contém os controladores responsáveis por expor as rotas da API.
- `daos/` - Gerencia a comunicação com o banco de dados.
- `facades/` - Implementa a lógica de negócio para centralizar as regras da aplicação.
- `models/` - Define as entidades do banco de dados.
- `strategies/` - Contém regras de validação ou processamento de dados.
- `utils/` - Classes utilitárias auxiliares.

---

## 📦 Dependências
As principais dependências do backend estão no `pom.xml` e incluem:
- **Spring Boot** - Framework principal para construção da API REST.
- **Hibernate** - ORM para manipulação do banco de dados PostgreSQL.
- **PostgreSQL Driver** - Conexão com o banco de dados.

Para instalar as dependências, utilize o Maven:
```sh
mvn clean install
```

---

## ⚙️ Configuração do Ambiente
O backend requer um banco de dados PostgreSQL configurado corretamente. Para isso, ajuste o arquivo `hibernate.properties`:
```properties
hibernate.connection.url=jdbc:postgresql://localhost:5432/postgres
hibernate.connection.username=postgres
hibernate.connection.password=1234
```
Além disso, ao iniciar o projeto, o Hibernate cria automaticamente o banco de dados. Caso necessário, crie um schema chamado `agenda`.

---

## 🚀 Como Executar
Para iniciar o servidor, execute:
```sh
mvn spring-boot:run
```
A API estará disponível em `http://localhost:8080`.

---

## 📡 Endpoints da API
Os endpoints podem ser testados usando **Postman**:
- `GET /customers/` - Lista todos os clientes.
- `POST /customers/` - Cria um novo cliente.
- `PUT /customers/:id` - Atualiza um cliente.
- `DELETE /customers/:id` - Remove um cliente.

- `GET /contacts/` - Lista todos os contatos.
- `POST /contacts/` - Cria um novo contato.
- `PUT /contacts/:id` - Atualiza um contato.
- `DELETE /contacts/:id` - Remove um contato.

---

## 🔐 Segurança
- Implementado **CORS** para permitir requisições entre diferentes origens. No ambiente atual, o acesso está liberado para todos (`*`).
  
---

## 📄 Scripts Disponíveis
- `mvn clean install` - Instala as dependências.
- `mvn spring-boot:run` - Inicia o servidor.

