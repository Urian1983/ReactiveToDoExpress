# 📝 ReactiveToDoExpress

Este proyecto de porfolio es una versión híbrida y reactiva de ToDoExpress. Consiste en una API REST completamente **reactiva y no bloqueante** para la gestión de tareas con auditoría integrada, construida sobre **Spring Boot 3**, **Java 21** y **Spring Data R2DBC**. Además, incluye una interfaz web construida con **Thymeleaf**, íntegramente reactiva también (sin bloquear ningún hilo en ningún punto del flujo), con soporte de documentación interactiva mediante **Swagger** y **Postman**.

El backend está diseñado para demostrar un núcleo asíncrono y reactivo (`Mono`/`Flux`) de punta a punta: tanto la API REST como las vistas HTML comparten la misma capa de servicio reactiva, y ninguna de las dos bloquea el pool de hilos de Netty en ningún momento.

---

## 🚀 Tecnologías

| Tecnología | Versión | Tipo de flujo |
|---|---|---|
| Java | 21 | — |
| Spring Boot | 3.5.14 | — |
| Spring Data R2DBC | — | Reactivo (No bloqueante) |
| Spring WebFlux (API) | — | Reactivo (No bloqueante) |
| Spring WebFlux + Thymeleaf (Web y API) | — | Reactivo (No bloqueante) |
| Springdoc OpenAPI (Swagger UI) | 2.x | — |
| MapStruct | 1.5.5.Final | — |
| Lombok | — | — |
| H2 Database R2DBC (dev) | — | Reactivo (No bloqueante) |
| MySQL / R2DBC Driver (prod) | — | Reactivo (No bloqueante) |
| Gradle | 8.14.4 | — |

---

## 🖥️ Interfaz web (Reactiva, no bloqueante)

La interfaz web usa **Thymeleaf** exactamente igual que la API REST: de forma completamente reactiva. Los controladores de vista (`@Controller`) devuelven `Mono<String>` y encadenan la capa de servicio reactiva (`.map()`, `.thenReturn()`...) en vez de bloquear con `.block()` — así ningún hilo del pool de Netty queda parado esperando a la base de datos, ni siquiera al renderizar HTML.

| Página | Ruta |
|---|---|
| Listado de tareas | `/tasks` |
| Nueva tarea | `/tasks/new` |
| Editar tarea | `/tasks/{id}/edit` |
| Listado de auditoría | `/audits` |
| Nuevo registro de auditoría | `/audits/new` |

La interfaz web y la API REST comparten la misma capa de servicio (`TaskService`, `AuditService`): los controladores de vista y los `@RestController` son solo dos puertas de entrada distintas a la misma lógica de negocio reactiva.

---

## 📁 Estructura del proyecto
