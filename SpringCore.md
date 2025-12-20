## SPRING IOC
* DI is specialized form of IoC
* `org.springframework.bean` & `org.springframework.context`  packages are the basis for Spring Framework’s IoC container

**ApplicationContext** manages the application lifecycle and runtime infrastructure  
**BeanFactory** only creates beans and injects dependencies.  

Features of ApplicationContext:
* **Event Publishing**: Notifies beans about startup, shutdown, or custom events for decoupled communication.
* **Internationalization (i18n)**: Resolves messages based on locale for user-friendly apps.
* **Web Support**: Supports WebApplicationContext, request/session scopes, and integrates with the servlet environment.
* **AOP Integration**: Automatically applies proxies and aspects to beans for cross-cutting concerns.

| ApplicationContext | BeanFactory |
|-------------------|------------|
| Eager loading (loads all the beans at app startup) | Lazy loading(loads beans on-demand) |
| Supports all types of bean scopes | supports two scopes (Singleton and Prototype) |
| automatically registers BeanFactoryPostProcessor(works on definitions (metadata) before bean exists) and BeanPostProcessor (works on actual bean instances after they exist, used to wrap beans with proxies for AOP advice) | Manually Creation is required |
| ApplicationContext extends ApplicationEventPublisher Which has event publishing feature (ex: Spring publishes a ContextRefreshedEvent when the ApplicationContext is refreshed and fully initialized) (Coordinating work) | Doesn’t have that feature |
| automatically provides a MessageSource in Spring Boot, so you can safely resolve locale-specific messages without manually registering (support Internationalization) | must manually define a MessageSource bean before you can resolve locale-specific messages (doesn’t support Internationalization by default) |
|the ApplicationContext becomes a WebApplicationContext, which is aware of the Servlet environment and web-specific scopes.DispatcherServlet acts as the front controller, handling HTTP requests and responses by using web infrastructure beans from the WebApplicationContext (such as controllers, handler mappings, and message converters), while business logic is executed through services and repositories invoked by controllers | |

---

#### 1. Spring Context Startup
* Spring context begins initialization.
* Bean definitions are loaded from:
    * Configuration classes (@Configuration)
    * XML
    * Component scanning (@Component, @Service, etc.)

#### 2. BeanFactoryPostProcessors Run 
* Operate on bean definitions/metadata before any bean instances exist.
* Examples:
    * ConfigurationClassPostProcessor
    * PropertySourcesPlaceholderConfigurer
* For AOP:
    * Metadata preparation happens here (e.g., registering AnnotationAwareAspectJAutoProxyCreator)

#### 3. Bean Instantiation
* Spring creates bean instances:
    * Constructor is called
    * Dependencies injected
* At this point, beans exist but are not yet initialized

#### 4. BeanPostProcessors Run (Spring uses this most)
##### Step A: postProcessBeforeInitialization
* Called before init callbacks (@PostConstruct, afterPropertiesSet)
* Can modify the bean instance

##### Step B: Initialization Callbacks
* Spring calls:
    * @PostConstruct methods
    * afterPropertiesSet() if the bean implements InitializingBean
* Beans perform custom initialization logic here
⚠ Note: AOP proxies are not yet applied during @PostConstruct. Method calls to proxied methods inside @PostConstruct will bypass advice.

##### Step C: postProcessAfterInitialization
* Called after init callbacks
* AOP Proxy wrapping happens here:
    * If a bean matches an Advisor (@Transactional, @Aspect), it is wrapped in a proxy
    * All references to the bean now point to the proxy

#### 5. Beans Ready
* Proxied beans are injected wherever needed
* Method calls now go through proxies → interceptors → advice applied

#### 6. Bean Destruction (Shutdown)
* Spring context closes:
* Beans are destroyed in reverse order of creation
* Spring calls:
    * @PreDestroy methods
    * destroy() if the bean implements DisposableBean
* Allows cleanup of resources before shutdown
---
### Spring Bean Lifecycle Flow

**Load Bean Definitions** (Parsing `@Component`, `@Bean`, XML)  
│  
**Instantiate** (Memory allocation)  
├── **Constructor Execution** └── **Constructor Injection** (`@Autowired` on constructor)  
│  
**Populate Properties** (Dependency Injection)  
├── **Field Injection** (`@Autowired` on fields)  
└── **Setter Injection** (`@Autowired` on setters)  
│  
**Initialize** (Setup & Configuration)  
├── **Aware Interfaces** (`BeanNameAware`, `BeanFactoryAware`, etc.)  
├── **postProcessBeforeInitialization** (Handles `@ApplicationContextAware`)  
├── **@PostConstruct** (Processed by `CommonAnnotationBeanPostProcessor`)  
├── **InitializingBean.afterPropertiesSet()** └── **Custom init-method** (`@Bean(initMethod=...)`)  
│  
**Post-Initialization** (Modifications)  
└── **postProcessAfterInitialization** (**AOP Proxy Creation** / Wrapping)  
│  
**Ready** (Bean is in singleton pool; if AOP used, reference is the Proxy)  
│  
**Destroy** (Container Shutdown)  
├── **@PreDestroy** ├── **DisposableBean.destroy()** └── **Custom destroy-method**

---

### Type | How container is created

| Type | How container is created |
|------|-------------------------|
| Stand-alone | AnnotationConfigApplicationContext / ClassPathXmlApplicationContext manually |
| Spring Boot Web App | SpringApplication.run() automatically creates an ApplicationContext (ServletWebServerApplicationContext) |

