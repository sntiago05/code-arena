# codeArena

API REST con **Spring Boot, Spring Data JPA y PostgreSQL** para una plataforma de retos de programación: usuarios con niveles, retos con dificultad, participaciones y logros.

## Funcionalidades
- Registro, autenticación con **JWT** y **refresh tokens**
- Gestión de usuarios con estado (activo/inactivo) y roles
- Retos (`Challenge`) con dificultad y experiencia calculada
- Participaciones de usuarios en retos
- Logros (`Achievement`) según condiciones configurables

## Arquitectura
Separación por capas: `domain` (entidades y reglas de negocio), `application` (servicios y casos de uso) y excepciones propias por capa.

## Tecnologías
Java, Spring Boot, Spring Data JPA, PostgreSQL, Docker Compose, Maven Wrapper.

## Cómo ejecutar
1. Levanta la base de datos:
   ```bash
   docker compose up -d
   ```
2. Arranca la aplicación:
   ```bash
   ./mvnw spring-boot:run
   ```

La conexión usa las variables `DB_HOST`, `DB_PORT` y `DB_NAME` (valores por defecto en `application.yaml`).
