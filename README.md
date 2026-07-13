# 📝 ReactiveToDoExpress

Este proyecto de porfolio es una versión híbrida y reactiva de ToDoExpress. Consiste en una API REST completamente **reactiva y no bloqueante** para la gestión de tareas con auditoría integrada, construida sobre **Spring Boot 3**, **Java 21** y **Spring Data R2DBC**. Además, incluye una interfaz web clásica construida con **Thymeleaf (implementado de forma tradicional/bloqueante)** para facilitar el binding de formularios y la renderización de plantillas, y soporte de documentación interactiva con **Swagger** y **Postman**.

El backend está diseñado para demostrar cómo convive un núcleo asíncrono y reactivo (`Mono`/`Flux`) con consumidores tanto reactivos (la API REST) como síncronos/bloqueantes (las vistas de usuario).

---

## 🚀 Tecnologías

| Tecnología | Versión | Tipo de flujo |
|---|---|---|
| Java | 21 | — |
| Spring Boot | 3.5.14 | — |
| Spring Data R2DBC | — | Reactivo (No bloqueante) |
| Spring WebFlux (API) | — | Reactivo (No bloqueante) |
| Spring MVC + Thymeleaf (Web) | — | Tradicional (Bloqueante) |
| Springdoc OpenAPI (Swagger UI) | 2.x | — |
| MapStruct | 1.5.5.Final | — |
| Lombok | — | — |
| H2 Database R2DBC (dev) | — | Reactivo (No bloqueante) |
| MySQL / R2DBC Driver (prod) | — | Reactivo (No bloqueante) |
| Gradle | 8.14.4 | — |

---

## 🖥️ Interfaz web (Síncrona/Bloqueante)

A diferencia de la API REST, la interfaz web está construida utilizando **Thymeleaf de forma síncrona y bloqueante**. Esto permite simplificar el ciclo de vida de las vistas HTML, facilitando el binding directo de los formularios (`TaskFormDTO`, `AuditFormDTO`) y manteniendo una arquitectura web robusta y familiar.

| Página | Ruta |
|---|---|
| Listado de tareas | `/tasks` |
| Nueva tarea | `/tasks/new` |
| Editar tarea | `/tasks/{id}/edit` |
| Listado de auditoría | `/audits` |
| Nuevo registro de auditoría | `/audits/new` |

Los controladores de vista (`@Controller`) consumen la capa de servicio reactiva bloqueando o adaptando los flujos mediante `.block()` (o resolviéndolos síncronamente) para poder renderizar las plantillas de Thymeleaf antes de enviar la respuesta al navegador.

---

## 📁 Estructura del proyecto
