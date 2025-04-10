# 📦 Servidores API
Projeto desenvolvido com Spring Boot para manter dados de servidores efetivos e temporários

---

## 👤 Informações de incrição

- **Nome:** Caio Oliveira Braz
- **Fone/Cel:** (65) 99922-5041
- **GitHub:** [@caiobrazpl](https://github.com/caiobrazpl)

---

## ⚙️ Tecnologias

- Java 21
- Spring Boot 3.5.0
- Spring Web
- Spring Data JPA
- Spring Security + OAuth2 Resource Server
- JSON Web Token (JJWT)
- PostgreSQL
- Lombok
- MinIO (armazenamento de arquivos)
- SpringDoc OpenAPI (Swagger UI)
- Flyway (migração de banco de dados)

---

## 🚀 Como executar o projeto

### ✅ Pré-requisitos

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

### 🔧 Subindo com Docker Compose

Este projeto já inclui um `docker-compose.yml` que sobe os seguintes serviços:

- A aplicação Spring Boot
- Um banco de dados PostgreSQL
- Um serviço MinIO para armazenamento de arquivos

#### ▶️ Passos para execução

1. Clone o repositório:

```bash
git clone https://github.com/caiobrazpl/servidor-api.git
cd servidor-api
```

2. Compile o projeto localmente:

```bash
./mvnw clean package -DskipTests
```

3. Suba os containers com:

```bash
docker-compose up --build
```

#### Acesse os serviços:

- **API:** http://localhost:8080
- **Swagger UI:** http://localhost:8080/swagger-ui.html
- **MinIO:** http://localhost:9000
    - **Console Admin:** http://localhost:9001
    - **Usuário/Senha:** `minioadmin` / `minioadmin`
- **PostgreSQL:**
    - Porta: `5432`
    - Banco: `minhadatabase`
    - Usuário: `meuusuario`
    - Senha: `minhasenha`

---


## 📑 Documentação da API

Acesse a documentação interativa via Swagger:

```
http://localhost:8080/swagger-ui.html
```

---

## 📮 Criação de tabelas

Todas tabelas serão criação via migração (Flyway)

---

## 📮 Usuário para teste

Usuário inicial criado via migração para testes: 
- **usuário:** admin
- **senha:** admin123

---
