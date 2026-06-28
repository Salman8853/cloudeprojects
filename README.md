# Application Guide

## Overview
This application is built with Spring Boot and provides RESTful API endpoints with an in-memory H2 database and Swagger UI documentation.

---

## Application Endpoints

### H2 Database Console
Access the in-memory H2 database management interface:

```
http://localhost:8080/h2-console/
```

**Features:**
- View and manage H2 database tables
- Execute SQL queries
- Monitor database structure

---

### Swagger UI (API Documentation)
Access the interactive API documentation:

```
http://localhost:8080/swagger-ui/index.html#/
```

**Features:**
- Browse all available API endpoints
- View request/response schemas
- Test API endpoints directly from the UI

---

## Running the Application

### Prerequisites
- Java JDK 11 or higher
- Maven

### Build the Application
```bash
mvn clean install
```

### Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080/`

---

## Project Structure

- **`src/`** - Source code
- **`target/`** - Compiled output (ignored in git)
- **`.idea/`** - IDE configuration (ignored in git)
- **`pom.xml`** - Maven configuration file

---

## Notes

- The H2 database is in-memory and will be reset on application restart
- All API endpoints are documented in Swagger UI for easy reference
