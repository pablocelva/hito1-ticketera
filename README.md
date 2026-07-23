# Ticketera - Core de Dominio Puro

Ticketera es un sistema de venta de entradas para eventos. Este repositorio contiene el **Core de Dominio Puro**, completamente aislado de frameworks, bases de datos o interfaces externas, siguiendo los principios de Clean Architecture.

## Arquitectura

El proyecto implementa un dominio puro en Java sin dependencias de producción. Toda la lógica de negocio vive en el paquete `com.ticketera.domain`, y las interacciones externas se modelan como interfaces inyectadas por constructor.

### Estructura del directorio

```
hito1-ticketera/
├── pom.xml
├── README.md
└── src/
    ├── main/java/com/ticketera/domain/
    │   ├── Event.java
    │   ├── TicketPool.java
    │   ├── OrderValidator.java
    │   ├── OrderService.java
    │   ├── BookingConfirmation.java
    │   ├── NotificationService.java
    │   ├── EventRepository.java
    │   └── exception/
    │       ├── SoldOutException.java
    │       └── InvalidOrderException.java
    └── test/java/com/ticketera/domain/
        ├── EventTest.java
        ├── TicketPoolTest.java
        ├── OrderValidatorTest.java
        ├── OrderServiceTest.java
        └── BookingConfirmationTest.java
```

### Descripción de archivos

**Clases de dominio:**

| Archivo | Responsabilidad |
|---|---|
| `Event.java` | Entidad central que representa un evento. Contiene nombre, venue, capacidad y entradas vendidas. Define `hasAvailability()` y `getAvailableTickets()`. |
| `TicketPool.java` | Gestiona el stock de entradas disponibles. Valida que la cantidad sea positiva y que haya stock suficiente antes de reservar. |
| `OrderValidator.java` | Valida las reglas de negocio de una orden: cantidad mayor a cero y precio mayor a cero. |
| `OrderService.java` | Servicio que procesa órdenes. Valida el ID del evento, verifica que el evento exista en el repositorio y envía una notificación con el nombre del evento. Depende de `EventRepository` y `NotificationService` (inyectados por constructor). |
| `BookingConfirmation.java` | Servicio que envía confirmaciones de reserva. Depende de `NotificationService` (inyectado por constructor). |

**Interfaces:**

| Archivo | Responsabilidad |
|---|---|
| `NotificationService.java` | Contrato para envío de notificaciones. Permite cambiar la implementación (SMS, email, push) sin modificar el dominio. |
| `EventRepository.java` | Contrato para acceso a datos de eventos. Permite cambiar la fuente de datos (base de datos, API, archivo) sin modificar el dominio. |

**Excepciones personalizadas:**

| Archivo | Responsabilidad |
|---|---|
| `SoldOutException.java` | Se lanza cuando no hay entradas disponibles para un evento. |
| `InvalidOrderException.java` | Se lanza cuando una orden tiene datos inválidos (cantidad o precio). |

## Tecnologías y dependencias

### Lenguaje y plataforma
- **Java 17** (compilador source/target 17)

### Build
- **Apache Maven** — Sistema de construcción y gestión de dependencias

### Dependencias de testing (scope: test)

| Dependencia | Versión | Propósito |
|---|---|---|
| `junit-jupiter-api` | 5.11.0 | Anotaciones y asserts de JUnit 5 (`@Test`, `@DisplayName`, `assertEquals`, `assertThrows`, etc.) |
| `junit-jupiter-engine` | 5.11.0 | Motor de ejecución de tests de JUnit 5 |
| `junit-jupiter-params` | 5.10.2 | Soporte para tests parametrizados (`@ParameterizedTest`, `@ValueSource`) |
| `mockito-core` | 5.11.0 | Framework de mocking para crear objetos simulados y verificar interacciones (`mock()`, `verify()`, `when()`) |

### Plugins de Maven

| Plugin | Versión | Propósito |
|---|---|---|
| `maven-surefire-plugin` | 3.2.5 | Ejecuta la suite de tests con soporte para nombres legibles de JUnit 5 |
| `jacoco-maven-plugin` | 0.8.11 | Instrumenta el código y genera reportes de cobertura (líneas, branches, métodos) |
| `jacoco-console-reporter` | 1.3.2 | Imprime un resumen de cobertura directamente en la consola |

## Testing y Garantía de Calidad

Este proyecto utiliza **JUnit 5** y **Mockito** para asegurar los más altos estándares de calidad.

- **Patrón AAA Estricto**: Todos los tests están estructurados rigurosamente usando las fases Arrange, Act y Assert.
- **Excepciones de Negocio**: Las excepciones personalizadas se verifican exhaustivamente usando `assertThrows`.
- **Cobertura 100%**: La suite de tests garantiza 100% de cobertura de Líneas, Ramas y Métodos.

### Resumen de cobertura por clase

| Clase | Tests | Ramas cubiertas |
|---|---|---|
| `Event` | 4 | `hasAvailability()` true + false |
| `TicketPool` | 4 | `quantity ≤ 0`, `quantity > available`, éxito |
| `OrderValidator` | 3 | `quantity ≤ 0`, `price ≤ 0`, éxito |
| `OrderService` | 4 | `eventId null`, `eventId empty`, `event not found`, éxito |
| `BookingConfirmation` | 3 | `email null`, `email empty`, éxito |
| **Total** | **18 tests** | **100% branch coverage** |

## Cómo verificar

Para ejecutar la suite de tests y generar el reporte de cobertura JaCoCo:

```bash
mvn clean test jacoco:report
```

Después de ejecutar el comando, ver la evidencia de cobertura en:
`target/site/jacoco/index.html`
