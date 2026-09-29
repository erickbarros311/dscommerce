# 🛒 DSCormmerce

> Projeto desenvolvido durante o curso **Java Spring Professional** da **DevSuperior**.

![Status](https://img.shields.io/badge/status-em%20sucess-blue)
![Java](https://img.shields.io/badge/Java-21-red)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)

---

## 📖 Sobre o projeto

O **DSCormmerce** é uma API REST desenvolvida com **Spring Boot** cujo objetivo é consolidar os principais conceitos do ecossistema Spring, como arquitetura em camadas, persistência com JPA, tratamento de exceções e utilização de DTOs.

Este projeto faz parte do curso **Java Spring Professional**, da DevSuperior, e está sendo desenvolvido de forma incremental conforme o avanço das aulas.

---

## 🚀 Tecnologias

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Maven
- REST API

---

## 📂 Estrutura do Projeto

```text
src
└── main
    ├── java
    │   └── com.devsuperior.dscormmerce
    │       ├── controllers
    │       ├── dto
    │       ├── entities
    │       ├── exceptions
    │       ├── handlers
    │       ├── repositories
    │       ├── services
    │       └── DscormmerceApplication.java
    └── resources
```

---

## 🏛 Arquitetura

```text
        Cliente
           │
           ▼
   ProductController
           │
           ▼
     ProductService
           │
           ▼
  ProductRepository
           │
           ▼
      Banco de Dados
```

| Camada | Responsabilidade |
|--------|-------------------|
| Controller | Recebe requisições HTTP |
| Service | Implementa regras de negócio |
| Repository | Acessa o banco de dados |
| Entity | Representa as entidades persistidas |
| DTO | Transporta dados entre as camadas |
| Exception Handler | Padroniza respostas de erro |

---

## ✨ Funcionalidades

- CRUD de produtos
- Conversão Entity ⇄ DTO
- Tratamento personalizado de exceções
- Transações com `@Transactional`

---

## 📚 Conceitos praticados

- Spring Boot
- REST
- Spring Data JPA
- Hibernate
- Injeção de Dependências
- DTO Pattern
- Repository Pattern
- Service Layer
- Exception Handling
- Bean Validation

---

## 📌 Status

🚧 **Projeto em desenvolvimento**.

Novas funcionalidades serão adicionadas conforme o andamento do curso.

---

## 👨‍💻 Autor - Erick B. Nascimento

Projeto desenvolvido para fins de estudo durante o curso **Java Spring Professional**, da **DevSuperior**.

---

## 📄 Licença

Projeto destinado exclusivamente para fins educacionais.
