## Java Runtime & Deployment Overview

### JVM vs Servlet Container vs Application Server

#### JVM
- **Runs**: Java bytecode
- **Does not provide**: HTTP handling, session management, servlet lifecycle

---

#### Servlet Container (Tomcat, Jetty, Undertow)
- **Handles**: HTTP requests, sessions, thread management, servlet lifecycle
- **Lightweight**: No EJB, JMS, or distributed transactions

---

#### Application Server (WildFly, WebLogic, GlassFish)
- **Includes**: Servlet container + full Jakarta EE (EJB, JPA, JMS, etc.)
- **Heavy**: For enterprise apps with distributed transactions, messaging, and clustering

---

### Runtime Environment Comparison

| Environment        | JVM? | Servlet Container? | App Server? | Notes               |
|--------------------|------|--------------------|-------------|---------------------|
| Desktop Apps       | ✔️   | ❌                 | ❌          | Run directly on JVM |
| Android Apps       | ❌   | ❌                 | ❌          | Uses ART            |
| Simple Web App     | ✔️   | ✔️                 | ❌          | e.g., Tomcat        |
| Enterprise Web App | ✔️   | ✔️                 | ✔️ Sometimes| e.g., WildFly       |
| Spring Boot        | ✔️   | ✔️ (Embedded)      | ❌          | Self-contained JAR  |

---

### Deployment Styles

| Style        | Package | Server Required?      | Cloud Examples           |
|--------------|---------|-----------------------|--------------------------|
| Classic WAR  | WAR     | Tomcat (external)     | AWS Elastic Beanstalk    |
| Classic EAR  | EAR     | App Server (e.g., WebLogic) | WebLogic Cloud       |
| Modern JAR   | Fat JAR | Embedded Tomcat       | Kubernetes, Cloud Run    |
| Serverless   | JAR/Docker | No dedicated server | AWS Lambda              |

---

### Packaging Formats

#### JAR
- **Use**: Spring Boot, microservices
- **Contains**: classes + libs + embedded Tomcat
- **Requires**: Embedded server (Tomcat, Jetty)
- **Pros**: Self-contained, easy deployment
- **Cons**: Not for traditional enterprise apps

#### WAR
- **Use**: Spring MVC, Jakarta EE
- **Contains**: /webapp, WEB-INF, classes, libs
- **Requires**: Requires external servlet container or app server (e.g., Tomcat)
- **Pros**: Portable, standardized
- **Cons**: Needs external setup, heavier

#### EAR
- **Use**: Enterprise apps (EJB, JMS)
- **Contains**: multiple WARs/JARs + shared libs
- **Requires**: Full app server (e.g., WebLogic)
- **Pros**: Supports enterprise services
- **Cons**: Heavier, slower deployments

---

### How Servlet Container Works
- **JVM → Tomcat → Listens on Port** (e.g., 8080)
- **Threads**: One per request
- **Responsibilities**:
  - HTTP handling
  - Session management
  - Deploy WAR files
  - Thread management
  - Request → Servlet → Response flow
  - Basic security
  - WAR deployment

---

### How Application Server Works
- **JVM → App Server → Starts Internal Services** (EJB container, JMS queue listeners, Transaction manager, Security/auth modules)
- **Responsibilities**: Manages enterprise services + runs Servlets

