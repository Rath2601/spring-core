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





