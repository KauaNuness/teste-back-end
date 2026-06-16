# Teste Técnico Backend - CRUD de Usuários e Endereços

## Sobre o Projeto

Aplicação desenvolvida em Java Spring Boot para gerenciamento de usuários e seus respectivos endereços.

O sistema realiza validação de CEP através da API pública ViaCEP durante operações de criação e atualização, impedindo a persistência de dados com CEPs inválidos.

---

## Tecnologias Utilizadas

* Java 21
* Spring Boot
* Spring Data JPA
* PostgreSQL
* H2 Database
* Maven
* Lombok
* Swagger / OpenAPI
* JUnit 5
* Mockito

---

## Funcionalidades

### Usuários

* Listar todos os usuários
* Buscar usuário por ID
* Criar usuário
* Atualizar usuário
* Remover usuário

### Endereços

* Associação de um ou mais endereços por usuário
* Exclusão automática dos endereços vinculados ao remover um usuário

### Validação de CEP

* Integração com a API ViaCEP
* Validação automática durante criação e atualização
* Bloqueio de CEPs inválidos
* Tratamento centralizado de exceções

---

## Arquitetura do Projeto

```text
src/main/java/com/kaua/testebackend

├── client
├── config
├── controller
├── dto
├── entity
├── exception
├── repository
└── service
```

### Camadas

* **Controller:** exposição dos endpoints REST
* **Service:** regras de negócio e integração com ViaCEP
* **Repository:** acesso aos dados utilizando Spring Data JPA
* **Entity:** representação das entidades persistidas
* **DTO:** transferência de dados externos
* **Exception:** tratamento centralizado de erros
* **Config:** configurações da aplicação

---

## Banco de Dados

### Ambiente de Desenvolvimento

Banco em memória utilizando H2 Database.

Arquivo de configuração:

```text
application-dev.yml
```

### Ambiente de Produção

Banco PostgreSQL.

Arquivo de configuração:

```text
application-prod.yml
```

---

## Executando o Projeto

### Clonar o Repositório

```bash
git clone https://github.com/KauaNuness/teste-back-end.git
```

### Entrar no Diretório

```bash
cd teste-back-end
```

### Executar a Aplicação

```bash
mvn spring-boot:run
```

A aplicação estará disponível em:

```text
http://localhost:8080
```

---

## Documentação da API (Swagger)

Após iniciar a aplicação, acesse:

```text
http://localhost:8080/swagger-ui/index.html
```

Especificação OpenAPI:

```text
http://localhost:8080/v3/api-docs
```

---

## Endpoints Disponíveis

### Listar Usuários

```http
GET /api/users
```

### Buscar Usuário por ID

```http
GET /api/users/{id}
```

### Criar Usuário

```http
POST /api/users
```

### Atualizar Usuário

```http
PUT /api/users/{id}
```

### Remover Usuário

```http
DELETE /api/users/{id}
```

---

## Exemplo de Requisição

### Criar Usuário

```json
{
  "name": "Kaua Nunes",
  "email": "kaua@email.com",
  "phone": "11999999999",
  "addresses": [
    {
      "cep": "01001000",
      "street": "Praça da Sé",
      "number": "100",
      "state": "SP",
      "city": "São Paulo",
      "neighborhood": "Sé"
    }
  ]
}
```

---

## Tratamento de Erros

### CEP Inválido

```json
{
  "timestamp": "2026-06-16T10:00:00",
  "status": 400,
  "error": "CEP_INVALID",
  "message": "CEP Inválido"
}
```

### Usuário Não Encontrado

```json
{
  "timestamp": "2026-06-16T10:00:00",
  "status": 404,
  "error": "USER_NOT_FOUND",
  "message": "Usuário não encontrado"
}
```

---

## Testes

Para executar os testes:

```bash
mvn test
```

### Cobertura Implementada

#### ViaCepServiceTest

* CEP válido
* CEP inválido
* Resposta nula da API

#### UserRepositoryTest

* Persistência de usuário
* Busca por ID
* Exclusão de usuário

---

## Boas Práticas Aplicadas

* Arquitetura em camadas
* Tratamento global de exceções
* Validação com Bean Validation
* Integração externa desacoplada
* Relacionamento JPA entre Usuário e Endereço
* Uso de branches por funcionalidade
* Commits semânticos
* Testes automatizados
* Documentação OpenAPI

---

## Autor

**Kauã Nunes**
