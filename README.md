# Mesaup

Este projeto consiste no **BackEnd** de  **Controle de comandas**. A aplicação foi construída utilizando **Java** com o framework **Spring Boot** e o banco de dados relacional **PostregSQL** e com o versionamento do banco de dados utilizando o **FLYWAY**, com a conteinerização com docker com foco na facilidade de utilização do usuário.
> Projeto acadêmico construído para demonstrar competências e boas práticas de back-end aplicadas a um cenário de mercado.

## Stack

* Java 17
* Spring Boot 3
* Spring Web
* Spring Data JPA
* PostegreSQL
* Arquitetura em camadas (controller, services, model, repository, config, DTOs)
* Flyway
* Maven
* Docker
* Swagger

## Principais funcionalidades

* Cadastamento de funcionários do estabelecimento
* Cadastramento de produtos do estabelecimento
* Criação de mesas
* Controle completo das comandas

## Arquitetura e organização do projeto

* **Camadas:** Controller → Services → Repository
* **Enums:** LImitador de valores
* **Percistencia:** **JPA**
* **Banco de Dados:** **PostgreSQL**
* **Versionamento do banco:** **Flyway**

```text
src/main/java/Mesaup.controll
├── Config
├── Controller
├── Entity
├── Enums
├── Exception
├── Repository
├── Service
└── resources
    ├── db.migration
        ├──  V1__create_table_usuario.sql
        ├──  V2__create_table_mesas.sql
        ├──  V3__create_table_produtos.sql
        ├──  V4__create_table_comanda.sql
        ├──  V5__create_table_comanda_item.sql
        ├──  V6__insert_dados.sql               
    
```
