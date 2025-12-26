### Spring Aware Interfaces
#### What are Aware Interfaces?

**Aware interfaces** are a callback mechanism where the Spring container automatically calls specific methods of a bean to provide it with required framework objects or notify it of lifecycle events.

When a bean implements an `*Aware` interface, Spring injects the corresponding framework dependency through the interface's setter method during bean initialization, before other initialization callbacks like `@PostConstruct`.

#### Important Consideration

**Note:** We should avoid using Aware interfaces because our beans/classes become tightly coupled with Spring framework interfaces. This creates dependencies that require more changes if we switch to another framework or when Spring itself upgrades.

**Better Alternatives:**
- Use dependency injection via constructor or setter injection
- Use `@Autowired` to inject ApplicationContext instead of ApplicationContextAware
- Use `@Value` for environment properties instead of EnvironmentAware
- @Autowired Environment instead of EnvironmentAware
---

#### Available Aware Interfaces

| Aware Interface | What Spring Provides | Why the Bean Needs It | Execution Order |
|----------------|---------------------|----------------------|----------------|
| **BeanNameAware** | The bean's own name | To know its own identity for logging or custom tracking | 1 |
| **BeanFactoryAware** | The basic BeanFactory | To manually find or create other beans in simple setups | 2 |
| **ApplicationContextAware** | The full ApplicationContext | To search for any bean, load files, or listen to system events | 3 |
| **EnvironmentAware** | System Properties & Profiles | To check if it's running in "Dev" or "Prod" and read config values | 4 |
| **ResourceLoaderAware** | A File/Resource Loader | To read external files (like .txt or .xml) from the classpath | 5 |
| **MessageSourceAware** | Translation Tools | To show messages in different languages (Internationalization) | 6 |

---

## Detailed Interface Descriptions

### 1. BeanNameAware

**Purpose:** Provides the bean with its own name in the Spring container.

**Method Signature:**
void setBeanName(String name)**Use Case:**
- Logging with bean identity
- Custom tracking or monitoring
- Conditional logic based on bean name

**Example:**
```
public class MyBean implements BeanNameAware {
    private String beanName;
    
    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
        System.out.println("Bean name: " + beanName);
    }
 }
```
---

### 2. BeanFactoryAware

**Purpose:** Provides access to the underlying `BeanFactory` for programmatic bean lookups.

**Method Signature:**a
void setBeanFactory(BeanFactory beanFactory) throws BeansException**Use Case:**
- Manual bean lookups
- Conditional bean retrieval
- Access to low-level bean factory operations

**Example:**
```
public class MyBean implements BeanFactoryAware {
    private BeanFactory beanFactory;
    
    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
        // Check if bean exists
        boolean exists = beanFactory.containsBean("someBean");
    }
}
```
---

### 3. ApplicationContextAware

**Purpose:** Provides access to the full `ApplicationContext` with all its capabilities.

**Method Signature:**a
void setApplicationContext(ApplicationContext applicationContext) throws BeansException**Use Case:**
- Access to all beans in the container
- Publishing application events
- Access to resource loading
- Profile and environment information
- Parent/child context access

**Example:**
```
public class MyBean implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    
    @Override
    public void setApplicationContext(ApplicationContext context) {
        this.applicationContext = context;
        // Get all beans of a type
        Map<String, MyService> services = context.getBeansOfType(MyService.class);
        // Publish event
        context.publishEvent(new CustomEvent(this));
    }
}
```
**Note:** ApplicationContext extends BeanFactory, so it provides more functionality than BeanFactoryAware.
---

### 4. EnvironmentAware

**Purpose:** Provides access to the `Environment` for reading properties and profiles.

**Method Signature:**
void setEnvironment(Environment environment)**Use Case:**
- Reading application properties
- Checking active profiles (dev, prod, test)
- Accessing system properties
- Environment-specific configuration

**Example:**
```
public class MyBean implements EnvironmentAware {
    private Environment environment;
    
    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        // Check active profiles
        String[] profiles = environment.getActiveProfiles();
        // Read property
        String dbUrl = environment.getProperty("database.url");
    }
}
```
**Alternative:** Use `@Value("${property.name}")` or `@Autowired Environment` instead.
---

### 5. ResourceLoaderAware

**Purpose:** Provides a `ResourceLoader` to load files and resources from various locations.

**Method Signature:**
void setResourceLoader(ResourceLoader resourceLoader)**Use Case:**
- Loading files from classpath
- Loading files from filesystem
- Loading files from URLs
- Reading configuration files programmatically

**Example:**
```
public class MyBean implements ResourceLoaderAware {
    private ResourceLoader resourceLoader;
    
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
        // Load resource
        Resource resource = resourceLoader.getResource("classpath:config.properties");
        boolean exists = resource.exists();
    }
}
```
---

### 6. MessageSourceAware

**Purpose:** Provides a `MessageSource` for internationalization (i18n) and message resolution.

**Method Signature:**ava
void setMessageSource(MessageSource messageSource)**Use Case:**
- Displaying messages in different languages
- Resolving message codes to localized strings
- Internationalization support

**Example:**
```
public class MyBean implements MessageSourceAware {
    private MessageSource messageSource;
    
    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
        // Resolve message
        String message = messageSource.getMessage(
            "greeting.message",
            new Object[]{"John"},
            "Default greeting",
            Locale.getDefault()
        );
    }
}
```
---

## Execution Order

When multiple Aware interfaces are implemented, Spring calls them in a specific order:

1. **BeanNameAware** - Provides bean name
2. **BeanFactoryAware** - Provides BeanFactory
3. **ApplicationContextAware** - Provides ApplicationContext
4. **EnvironmentAware** - Provides Environment
5. **ResourceLoaderAware** - Provides ResourceLoader
6. **MessageSourceAware** - Provides MessageSource

All Aware callbacks happen **after** dependency injection but **before** `@PostConstruct` and initialization methods.
