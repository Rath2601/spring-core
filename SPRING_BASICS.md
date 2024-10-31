# SPRING FRAMEWORK

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
* Using  ``WebInitializer `` with annotation-based configuration: This is a programmatic approach using classes like `` AppConfig and RootConfig ``.
* Using `` web.xml `` with XML configuration: This is the traditional method where you define the  ``DispatcherServlet and other configurations `` in the web.xml file and configure the  ``application context `` in XML files like  ``spring-servlet.xml ``.

In a Spring Boot scenario, the application context is **implicitly bootstrapped** for you based on common setup conventions. [ **Auto-configuration** ]





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


## NOTE : Common in context of Spring, Springboot, Java

* The Java Virtual Machine is the runtime environment that allows Java bytecode to be executed. Languages like Groovy and Kotlin compile down to Java bytecode, which can then run on the JVM.




