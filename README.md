# Ticketera - Core Domain

Ticketera is an event ticketing system. This repository contains the **Pure Domain Core**,
completely isolated from any frameworks, databases, or external interfaces, following
the principles of Clean Architecture.

## Architecture Highlights
- **Pure Java**: No Spring, JPA, or web annotations. The domain depends only on itself.
- **Dependency Inversion**: All external interactions are modeled as interfaces
  (`NotificationService`, `EventRepository`) and injected via the constructor.
- **English Nomenclature**: Clean, modular code entirely in English.

## Testing & Quality Assurance
This project uses **JUnit 5** and **Mockito** to ensure the highest standards of quality.
- **Rigorous AAA Pattern**: All tests are strictly structured using Arrange, Act, and Assert phases.
- **Business Exceptions**: Custom exceptions are verified thoroughly using `assertThrows`.
- **100% Coverage**: The test suite guarantees 100% Line and Branch coverage.

## How to Verify
To run the automated tests and generate the JaCoCo coverage report:

```bash
mvn clean test jacoco:report
```

After running the command, view the coverage evidence: target/site/jacoco/index.html

### Resumen de coverage por clase

| Clase | Tests | Ramas cubiertas |
|---|---|---|
| `Event` | 4 | `hasAvailability()` true + false |
| `TicketPool` | 4 | `quantity ≤ 0`, `quantity > available`, éxito |
| `OrderValidator` | 3 | `quantity ≤ 0`, `price ≤ 0`, éxito |
<!-- | `OrderService` | 3 | `eventId null`, `eventId empty`, éxito | -->
<!-- | `BookingConfirmation` | 3 | `email null`, `email empty`, éxito | -->
<!-- | **Total** | **17 tests** | **100% branch coverage** | -->
