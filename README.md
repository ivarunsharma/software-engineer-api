# Software Engineer API

A RESTful API built with Spring Boot 4 and PostgreSQL for managing software engineers.

## Tech Stack

- Java 21
- Spring Boot 4
- Spring Data JPA / Hibernate
- PostgreSQL
- Docker / Docker Compose
- Maven

## Project Structure

```
src/main/java/com/vsv/
├── Application.java               # Entry point
├── controller/
│   ├── HealthController.java      # GET /
│   └── SoftwareEngineerController.java  # CRUD endpoints
├── dto/
│   ├── SoftwareEngineerRequest.java
│   └── SoftwareEngineerResponse.java
├── entity/
│   └── SoftwareEngineer.java      # JPA entity
├── exception/
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
├── repository/
│   └── SoftwareEngineerRepository.java
└── service/
    └── SoftwareEngineerService.java
```

## Prerequisites

- Java 21+
- Maven 3.9+
- Docker & Docker Compose

## Setup

### 1. Configure environment variables

```bash
cp .env.example .env
```

Edit `.env` and set a strong `POSTGRES_PASSWORD` and matching `DB_PASSWORD`. **Never commit `.env` to version control.**

### 2. Start the database

```bash
docker compose up -d
```

### 3. Run the application

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/` | Health check |
| GET | `/api/v1/software-engineers` | List all engineers |
| GET | `/api/v1/software-engineers/{id}` | Get engineer by ID |
| POST | `/api/v1/software-engineers` | Create new engineer |
| PUT | `/api/v1/software-engineers/{id}` | Update engineer |
| DELETE | `/api/v1/software-engineers/{id}` | Delete engineer |
| GET | `/actuator/health` | Application health |

### Request body (POST / PUT)

```json
{
  "name": "Aavya Sharma",
  "techStack": "Java, Spring Boot, PostgreSQL",
  "email": "aavya@example.com"
}
```

### Example responses

**200 OK**
```json
{
  "id": 1,
  "name": "Aavya Sharma",
  "techStack": "Java, Spring Boot, PostgreSQL",
  "email": "aavya@example.com"
}
```

**404 Not Found**
```json
{
  "type": "about:blank",
  "title": "Not Found",
  "status": 404,
  "detail": "Software engineer not found with id: 99"
}
```

**400 Bad Request** (validation failure)
```json
{
  "type": "about:blank",
  "title": "Bad Request",
  "status": 400,
  "detail": "Validation failed",
  "errors": {
    "email": "Must be a valid email"
  }
}
```

## Running Tests

```bash
./mvnw test
```

Tests run against an in-memory H2 database — no external dependencies required.

## Environment Variables

| Variable | Description | Default |
|----------|-------------|---------|
| `DB_URL` | JDBC datasource URL | `jdbc:postgresql://localhost:5332/vsv` |
| `DB_USERNAME` | Database username | `vsv` |
| `DB_PASSWORD` | Database password | *(required)* |
| `POSTGRES_USER` | Docker Postgres user | `vsv` |
| `POSTGRES_PASSWORD` | Docker Postgres password | *(required)* |
| `DB_PORT` | Host port for Postgres | `5332` |
| `JPA_DDL_AUTO` | Hibernate DDL strategy | `update` |
| `SHOW_SQL` | Log SQL queries | `false` |

## Security Notes

- Credentials are loaded from environment variables — never hardcoded in source.
- `.env` is listed in `.gitignore` and must not be committed.
- Use `.env.example` as a template for onboarding new developers.
- For production, inject secrets via your CI/CD system or a secrets manager (AWS Secrets Manager, HashiCorp Vault, etc.).
