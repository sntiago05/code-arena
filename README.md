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


## Deuda técnica

- **Contraseña de base de datos por defecto:** `application.yaml` usa `${DB_PASSWORD:123}`. El valor por defecto debe eliminarse para que la aplicación falle si no se configura la variable.
- **Utilidad dentro de `src/main`:** `SecreteGenerator.java` genera la clave JWT y está en el código de la aplicación. Debería moverse a una herramienta aparte o eliminarse.
- **Pruebas:** hay pruebas unitarias de dominio, de mappers y de comandos, pero solo una prueba de carga de contexto de Spring (`CodeArenaApplicationTests`). Faltan pruebas de integración contra PostgreSQL real (por ejemplo con Testcontainers) y de los endpoints REST.
- **Cobertura:** no está medida.
