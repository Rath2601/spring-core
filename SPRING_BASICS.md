# SPRING FRAMEWORK

## HISTORY OF SPRING FRAMEWORK

### **Spring's Origin and Purpose**
- **Spring was created in 2003** as a response to the complexities in J2EE (Java 2 Platform, Enterprise Edition), the precursor to Java EE (now Jakarta EE). At the time, J2EE was seen as overly complicated, requiring developers to follow strict rules and complex configurations for enterprise applications.
- **Spring's goal** was to simplify application development by focusing on **dependency injection** and **aspect-oriented programming**, which enabled cleaner, more modular code.
  
---

### **Spring and Jakarta EE Relationship**
- **Spring and Jakarta EE** aren’t direct competitors but work **complementarily**. Spring doesn't adopt all Jakarta EE specifications but selectively integrates those that enhance its programming model. This means that Spring allows you to use some Jakarta EE technologies without being locked into the full Jakarta EE framework.

#### **Jakarta EE Specifications Integrated by Spring**
   - **Servlet API (JSR 340)**: Used to build web applications with Java servlets.
   - **WebSocket API (JSR 356)**: Supports real-time, two-way communication between clients and servers.
   - **Concurrency Utilities (JSR 236)**: Provides tools for handling concurrency and multithreading in applications.
   - **JSON Binding API (JSR 367)**: Allows Java objects to be converted to and from JSON.
   - **Bean Validation (JSR 303)**: Ensures that Java Beans (components) follow specific validation constraints.
   - **JPA (JSR 338)**: Provides a standard for Java object-relational mapping to manage database persistence.
   - **JMS (JSR 914)**: Java Messaging Service for handling messages between applications.
   - **JTA/JCA**: For transaction management, ensuring that multiple operations complete successfully or roll back if any part fails.

---

### **Dependency Injection (DI) and Annotations in Spring**
- **Dependency Injection** (JSR 330) and **Common Annotations** (JSR 250) are essential tools in Spring for managing how objects depend on each other. These are **industry-standard annotations** that Spring supports, giving developers the option to use a **consistent API** instead of Spring-specific tools.
  
---

### **Namespace Transition: javax to jakarta**
- **Spring Framework 6.0** is now aligned with **Jakarta EE 9** and beyond. This update means Spring is compatible with modern Jakarta EE specifications, using the **`jakarta` namespace** instead of the old `javax` namespace.
- **Supported Servers**: Spring 6.0 can run on newer server versions, like **Tomcat 10.1**, **Jetty 11**, and **Undertow 2.3**.
- **Hibernate ORM 6.1** compatibility means Spring can continue using this popular ORM tool for database interactions.

---

### **Deployment Shift: Application Server to Embedded Containers**
- **In the early days**, both Spring and J2EE applications were designed for deployment in **application servers** (like JBoss, WebSphere), which manage the application's lifecycle, security, and database connections.
- **Today, Spring Boot** has changed this model by embedding a **Servlet container directly into the application**, allowing for easier deployment, particularly in **DevOps** and **cloud** environments. With Spring Boot, you don’t need a separate application server like JBoss to run the app.

---

### **WebFlux: Moving Beyond the Servlet API**
- **Spring WebFlux** (introduced in Spring 5) allows developers to build **non-blocking, reactive applications** that can run on servers like **Netty**, which aren’t traditional Servlet containers.
- This approach is optimized for modern, high-throughput applications that need to handle a lot of concurrent requests without being limited by traditional synchronous programming.

---

### **Spring Ecosystem**
- **Beyond Spring Framework**, there’s an entire **Spring ecosystem** with projects tailored for various tasks:
   - **Spring Boot**: Simplifies configuration and deployment by embedding necessary dependencies and a web server.
   - **Spring Security**: Manages application security.
   - **Spring Data**: Simplifies data access, focusing on databases.
   - **Spring Cloud**: Helps in building distributed, cloud-based applications.
   - **Spring Batch**: Manages batch processing, useful in tasks like data migration.

Each Spring project has **its own repository, issue tracker, and release cycle**, which means they’re developed independently but can work together to build robust applications.

---

### **Spring Design Principle**

- **Flexibility**: Offers choices for switching components (e.g., persistence providers) without code changes.
- **Inclusivity**: Supports various development approaches; not opinionated.
- **Compatibility**: Maintains strong backward compatibility across versions.
- **API Design**: Focuses on intuitive, stable APIs.
- **Code Quality**: Emphasizes clean code, detailed javadocs, and no circular dependencies.

---

## Core Technologies in Spring

* Coverage of Spring’s integration with AspectJ (currently the richest — in terms of features — and certainly most mature AOP implementation in the Java enterprise space) is also provided.

* **Ahead-of-Time Compilation**: Unlike traditional Just-in-Time (JIT) compilation, where the code is compiled as it runs, AOT compiles the code during the build phase. This reduces runtime processing, which can improve application startup and overall speed.

* **Native Image Deployment with GraalVM**: GraalVM is a tool that allows Java applications to be compiled into native executables, meaning they don’t need a JVM to run. Using AOT with GraalVM, your application can be converted into a lightweight, standalone binary, which is faster to start and uses less memory—ideal for cloud-native or containerized environments.

---

## Implementation of the Inversion of Control (IoC)

Basic packages responsible for IoC in Spring, 
 * org.springframework.beans
 * org.springframework.context

### IoC Container

The **BeanFactory** interface (spring IOC container) provides an advanced configuration mechanism capable of managing any type of object.

**ApplicationContext** (superset of the BeanFactory) is a sub-interface of **BeanFactory**. It adds more enterprise-specific functionality:
  * Easier integration with Spring’s AOP features
  * Message resource handling (for use in internationalization)
  * Event publication
  * Application-layer specific contexts such as the WebApplicationContext for use in web applications.

### Bean

1. A bean is an object that is instantiated, assembled, and managed by a Spring IoC container.
2. Beans, and the dependencies among them, are reflected in the **configuration metadata** used by a container.

### Configuration Metadata

The configuration metadata can be represented as **annotated component classes**, configuration classes with factory methods, or external XML files or Groovy scripts.

In spring (stand-alone application) we can use either xml based configuration or annotation based configuration.it is common to create an instance of 
   * AnnotationConfigApplicationContext
   * ClassPathXmlApplicationContext

In case of spring web application we'll use the below two approaches, 

* Using  ``WebInitializer `` with annotation-based configuration: This is a programmatic approach using classes like `` AppConfig and RootConfig ``. [JAVA BASED CONFIGURATION]
* Using `` web.xml `` with XML configuration: This is the traditional method where you define the  ``DispatcherServlet and other configurations `` in the web.xml file and configure the  ``application context `` in XML files like  ``spring-servlet.xml ``. [TRADITIONAL XML BASED CONFIGURATION]

* webInitializer -> Initializes dispatcher servlet
* web.xml -> maps servlet to URL pattern
* spring-servlet.xml -> bean definition and spring application context


In a Spring Boot scenario, the application context is **implicitly bootstrapped** for you based on common setup conventions. [ **Auto-configuration** ]

---

## NOTE: Standalone vs Web Applications, Web Server vs Application Server, Spring & Spring Boot

### 1. Standalone vs Web Application

| **Type**               | **Description**                                                                                                                                          | **Example**                     |
|------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------|---------------------------------|
| **Standalone Application** | Does not require a web server or web interface.                                                                                                          | Excel (can be downloaded and used without a web browser or server). |
| **Web Application**     | Requires a web server and must be used with a web browser.                                                                                               | HDFC Netbanking (can only be used with the internet).                |

---

### 2. Web Server vs Application Server

| **Feature**                        | **Web Server**                                                   | **Application Server**                                                                                                      |
|------------------------------------|-------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------|
| **Primary Function**               | Serves static content (HTML, images).                             | Runs server-side programming, supports transactions, dependency injection, security, concurrency, and database access.      |
| **Handles Dynamic Content**        | Can serve dynamic content using plugins (e.g., PHP, Python).      | Natively supports dynamic content through servlets, JSP, EJB, etc.                                                          |
| **Database Interaction**           | Does not interact with databases.                                 | Can directly interact with databases.                                                                                       |
| **Java E-Space**                   | N/A                                                               | Divided into different containers:                                                                                          |
|                                    |                                                                   | - **Servlet Container** (e.g., Apache Tomcat): Runs servlets, JSP, JSF.                                                     |
|                                    |                                                                   | - **EJB Container**: Manages Enterprise Java Beans and transaction lifecycles.                                               |
|                                    |                                                                   | - **Application Client Container**: Handles dependency injection and security.                                               |
| **Load Balancing**                 | Performs load balancing between application servers.              | May also provide load balancing but has more advanced features for clustering and failover.                                  |
| **Examples**                       | Apache HTTP Server, Nginx                                         | WebSphere, WebLogic, JBoss (WildFly).                                                                                        |

---

### 3. Spring & Spring Boot with Servlet Containers

- **Spring**:
  - Spring does not require a full **application server** (like JBoss or GlassFish).
  - It runs perfectly well on a **servlet container** (like Tomcat or Jetty).
  - Only needs a **web server** (for deployment purposes) to work as a web application.
  
  **Why?**
  - Spring has its own ecosystem (IoC, DI, transaction management) to handle most of the same tasks that Jakarta EE (Java EE) does, making heavy application servers unnecessary.

- **Spring Boot**:
  - Spring Boot embeds a **servlet container** (like Tomcat or Jetty) within the application itself.
  - No need to install an external web server or servlet container. The application can run as a standalone jar.
  - In almost all cases, Spring Boot **does not require a full application server** and only needs a web server for deployment.

#### When to Use a Full Application Server with Spring or Spring Boot?

- **Enterprise Features**: If the project needs advanced Java EE features (e.g., JMS, JNDI, distributed transactions).
- **Infrastructure or Policy Reasons**: Legacy systems or internal policies might dictate the use of a full application server.
- **Clustering & Load Balancing**: If your application requires clustering, high availability, or load balancing features, a full application server might be preferred.

| **Scenario**                        | **Use a Full Application Server?**                                                                                                            |
|-------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------|
| Spring/Spring Boot web applications | No, unless the project requires specific Jakarta EE features (like EJB, JMS, or distributed transactions).                                     |
| Lightweight web applications        | No, a servlet container (like Tomcat) is sufficient.                                                                                           |
| Enterprise systems with EJB         | Yes, full application servers like JBoss or WebLogic should be used.                                                                           |
| Legacy or complex infrastructure    | Yes, especially when the organization has policies that require a full application server or if the application interacts with Java EE components. |

---

### 4. Summary of Deployment Options for Spring Applications

| **Feature**                        | **Spring MVC + Tomcat**                              | **Spring Boot + Embedded Tomcat**                      | **Full Application Server (e.g., JBoss)**                       |
|------------------------------------|-----------------------------------------------------|-------------------------------------------------------|----------------------------------------------------------------|
| **Need External Server**           | Yes, requires a servlet container like Tomcat.       | No, embedded server is included within the application. | Yes, but only if using advanced EE features (EJB, JMS, etc.).    |
| **Clustering & Load Balancing**    | Managed externally by web servers.                   | Handled by load balancer setup.                        | Built-in support in full application servers (e.g., JBoss).     |
| **Enterprise Features**            | No EJB support. Spring manages transactions, DI, etc.| No EJB support. Uses Spring’s ecosystem.               | Supports EJB, JNDI, JMS, and other Java EE services.             |

---

## NOTE : Common in context of Spring, Springboot, Java

* The Java Virtual Machine is the runtime environment that allows Java bytecode to be executed. Languages like Groovy and Kotlin compile down to Java bytecode, which can then run on the JVM.




