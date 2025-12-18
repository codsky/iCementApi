# iCementApi

iCementApi is a RESTful API service for handling ordering and delivery of cement bags from various branches of a cement manufacturer (like GHACEM) to all kinds of retailers in Ghana, using the services of branch agents and delivery drivers.

# What The Service Does
* Register and authenticate users(Retailers, Branch Agents, and Drivers).
* Accept and manage cement orders.
* Track and validates order state transitions.
* Assign drivers to orders.
* Confirm delivery completion

# Out of Scope
* Financial processes (invoicing, payments)
* Communication systems (chat, messaging)
* Real-time logistics (live tracking)
* Product catalog management (products are predefined and immutable).

# Core Entities
* User
* Product 
* Order

# Order Lifecycle States
Order state transitions are controlled and validated. Some transitions allow regression under defined conditions (e.g., ON_HOLD), while others are terminal (e.g., DELIVERED, CANCELLED).An order can go through various states. Not all states must be travered sequentially. The states includes:

* PENDING
* IN_PRODUCTION
* ASSIGNED_TO_DRIVER
* OUT_FOR_DELIVERY
* DELIVERED
* CANCELLED
* ON_HOLD

# Technology Stack
* Java 21 Spring Boot with Maven
* PostgreSQL
* Docker (Containerised for easy set up)
* OpenApi (Swagger docs).

