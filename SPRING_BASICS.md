## SPRING FRAMEWORK
### HISTORY OF SPRING FRAMEWORK
#### **Spring's Origin and Purpose**
- **Spring was created in 2003** as a response to the complexities in J2EE (Java 2 Platform, Enterprise Edition), the precursor to Java EE (now Jakarta EE). At the time, J2EE was seen as overly complicated, requiring developers to follow strict rules and complex configurations for enterprise applications.
- **Spring's goal** was to simplify application development by focusing on **dependency injection** and **aspect-oriented programming**, which enabled cleaner, more modular code.
---
### 1. What is Jakarta EE and Spring and how they are related
- **Jakarta EE** is a **set of specifications** (standards) for building enterprise Java applications. It defines APIs like Servlet, JPA, JAX-RS, CDI, EJB, etc., but **does not provide implementations**.
- **Application servers** (GlassFish, WildFly, WebLogic) provide **implementations of Jakarta EE specifications**.
- **Spring** is a **framework**, not a specification. It provides its **own implementations and abstractions**, while selectively **integrating with some Jakarta EE specifications** instead of implementing the full Jakarta EE platform.
- Spring **replaced the need for heavy EJB-based Java EE development**, while still being able to **run on top of Jakarta EE infrastructure** when required.
---

#### **Spring and Jakarta EE Relationship**
- **Spring and Jakarta EE** aren’t direct competitors but work **complementarily**.
- Spring **uses some Jakarta EE APIs** (Servlet, JPA, Bean Validation, etc.).
- Spring **reimplements many enterprise concerns itself** (DI, transactions, security).

##### **Jakarta EE Specifications Integrated by Spring**
- **Servlet API (JSR 340 / Jakarta Servlet)**  
- **WebSocket API (JSR 356)**  
- **Bean Validation (JSR 303 / 380)**  
- **JPA (JSR 338)**  
- **JMS (JSR 914)**  
- **JTA (Transactions)**  
- **Common Annotations (JSR 250)**  
- **Dependency Injection (JSR 330)**  
---

### 2. Jakarta EE Specifications vs Spring (Comparison Table)
| Jakarta EE Spec | JSR | Purpose of JSR | Common Implementations | Spring Alternative | Spring Relation |
|-----------------|-----|----------------|------------------------|-------------------|-----------------|
| Servlet | JSR 340 | HTTP request handling | Tomcat, Jetty, Undertow | Spring MVC | Uses Servlet API |
| JAX-RS | JSR 339 | REST APIs | Jersey, RESTEasy | Spring MVC / WebFlux | Spring does not use JAX-RS |
| JPA | JSR 338 | ORM | Hibernate, EclipseLink | Spring Data JPA | Uses JPA underneath |
| CDI | JSR 365 | Dependency Injection | Weld | Spring Core | Own DI container |
| EJB | JSR 345 | Business components | WildFly, GlassFish | Spring | Replaced by Spring |
| JMS | JSR 914 | Messaging | ActiveMQ, Artemis | Spring Kafka / JMS | Integrates, not implements |
| JTA | JSR 907 | Transactions | Narayana | Spring Tx | Can delegate to JTA |
| Bean Validation | JSR 303 | Validation | Hibernate Validator | Spring Validation | Uses implementation |

---
### 3. Deployment Comparison
| Feature | Jakarta EE App | Spring App | Spring Boot App |
|-------|----------------|------------|------------------|
| Packaging | WAR/EAR | WAR | JAR / WAR |
| Server Required | Application Server | Servlet Container or App Server | Embedded server or Servlet Container or App Server |
| Deployment | Mandatory | Optional | Not required |
| Startup | Slower | Faster | Fastest |
| EJB Support | Yes | No | No |
| Cloud Ready | Medium | High | Very High |

---
### 4. Spring Projects vs Jakarta EE

| Spring Project | What it does | Jakarta EE Equivalent | Jakarta EE Implementation? |
|---------------|-------------|------------------------|----------------------------|
| Spring boot | Production ready application | Servlet + JAX-RS | ❌ No |
| Spring MVC | Web MVC / REST | Servlet + JAX-RS | ❌ No |
| Spring WebFlux | Reactive Web | None | ❌ No |
| Spring Security | AuthN/AuthZ | Jakarta Security | ❌ No |
| Spring Data JPA | Data access | JPA | ⚠️ Uses JPA |
| Spring Kafka | Messaging | JMS | ❌ No |
| Spring Cloud | Distributed systems | None | ❌ No |
| Spring Tx | Transactions | JTA | ⚠️ Integrates |
| Spring Core (DI) | Dependency Injection | CDI | ❌ No |

---
### 5. Spring Primary Design Principles & Features
- **Loose Coupling**
- **Dependency Injection**
- **Aspect-Oriented Programming**
- **POJO-based development**
- **Modular architecture**
- **Backward compatibility**
- **Convention over configuration (Spring Boot)**
---

### 6. IoC Container Deep Dive in Spring
#### IoC Packages
- `org.springframework.beans`
- `org.springframework.context`

#### BeanFactory
- Basic IoC container
- Lazy initialization
- Low-level API

#### ApplicationContext
- Superset of BeanFactory
- Eager initialization
- AOP support
- Event publishing
- Internationalization
- Web support (`WebApplicationContext`)
---

### 8. Bean Lifecycle Management in Spring
1. Bean Instantiation
2. Dependency Injection
3. Aware Interfaces
4. BeanPostProcessor (before init)
5. Initialization (`@PostConstruct`, `afterPropertiesSet`)
6. BeanPostProcessor (after init)
7. Bean Ready for Use
8. Destruction (`@PreDestroy`, `destroy`)
---

### Core Technologies in Spring
- Aspect-Oriented Programming (AspectJ)
- Ahead-of-Time Compilation (AOT)
- Native Image support via GraalVM
---

### NOTE: Standalone vs Web Applications, Spring & Spring Boot
#### Standalone vs Web Application

| Type | Description | Example |
|-----|-------------|---------|
| Standalone | No server needed | EX: Desktop apps (Excel) |
| Web App | Needs server | Ex: Banking web app |
---

## Summary
- Jakarta EE = Specifications
- **Application Server** = Implementations (Websphere, Wildfly, Glassfish)
- Spring = Framework
- Spring Boot = Opinionated Spring + Embedded Server
- Enterprise web app ≠ **Application Server** mandatory anymore
