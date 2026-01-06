# WARP.md

This file provides guidance to WARP (warp.dev) when working with code in this repository.

## Project Overview

iCementApi is a Spring Boot 3.4 REST API (Java 21) for cement ordering and delivery management. It handles user authentication (JWT), orders with state-machine transitions, and product catalog for a cement manufacturer's distribution network in Ghana.

## Common Commands

```bash
# Build
./mvnw clean package

# Run application (requires PostgreSQL on localhost:5432)
./mvnw spring-boot:run

# Run all tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=OrderControllerTest

# Run a single test method
./mvnw test -Dtest=OrderControllerTest#testMethodName

# Skip tests during build
./mvnw package -DskipTests

# Commit and push changes
git add -A && git commit -m "Your commit message" && git push
```

## Architecture

### Package Structure (`com.icement.api.iCement`)

- **auth/** - JWT authentication (JwtUtil, JwtAuthenticationFilter, AuthService, AuthController)
- **user/** - User entity, enums (UserRole: ADMIN/AGENT/RETAILER/DRIVER, UserStatus), repository, service
- **order/** - Order entity with state machine logic, OrderItem, OrderStatus enum, repository, service, controller
- **product/** - Product entity, repository, service, controller
- **common/** - Shared code: BaseEntity (id, timestamps, soft delete), Address embeddable, BaseRepository, GlobalExceptionHandler, custom exceptions
- **config/** - ApplicationConfiguration (auth beans), AuthorizationConfiguration (security filter chain)
- **health/** - Health check endpoint

### Key Patterns

**Order State Machine**: `Order.updateStatus()` enforces valid transitions between states (PENDING → CONFIRMED → IN_PRODUCTION → ASSIGNED_TO_DRIVER → OUT_FOR_DELIVERY → DELIVERED). ON_HOLD can transition to most states; CANCELLED and DELIVERED are terminal.

**Base Entity**: All entities extend `BaseEntity` which provides auto-generated Integer ID, createdAt/updatedAt timestamps, and soft delete fields (deletedAt, deletedBy).

**Security**: JWT-based stateless auth. Public endpoints: `/api/auth/**`, `/api/health/**`. All other endpoints require authentication.

### Database

PostgreSQL with JPA. Connection configured in `application.properties`. Tests use Testcontainers with MongoDB (note: there's a mismatch - prod uses PostgreSQL, tests configure MongoDB).

### Testing

Integration tests extend `BaseIntegrationTest` which sets up a MongoDB Testcontainer. Test helpers exist in `Integration/User/` and `Integration/Order/` for creating test fixtures.
