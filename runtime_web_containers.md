## Java Runtime & Deployment Overview

### JVM vs Servlet Container vs Application Server

#### JVM
- **Runs**: Java bytecode
- **Does not provide**: HTTP handling, session management, servlet lifecycle

---

#### Servlet Container (Tomcat, Jetty, Undertow)
- **Handles**: HTTP requests, sessions, thread management, servlet lifecycle
- **Lightweight**: No EJB, JMS, or distributed transactions
- **Example**: Tomcat

---

#### Application Server (WildFly, WebLogic, GlassFish)
- **Includes**: Servlet container + full Jakarta EE (EJB, JPA, JMS, etc.)
- **Heavy**: For enterprise apps with distributed transactions, messaging, and clustering
- **Example**: WebLogic, WildFly

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
- **Requires**: Embedded server (Tomcat, Jetty)
- **Pros**: Self-contained, easy deployment
- **Cons**: Not for traditional enterprise apps

#### WAR
- **Use**: Spring MVC, Jakarta EE
- **Requires**: External server (e.g., Tomcat)
- **Pros**: Portable, standardized
- **Cons**: Needs external setup, heavier

#### EAR
- **Use**: Enterprise apps (EJB, JMS)
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

---

### How Application Server Works
- **JVM → App Server → Starts Internal Services** (EJB, JMS, transactions)
- **Responsibilities**: Manages enterprise services + runs Servlets

