

### NOTE : 
- Beans that implement BeanFactoryPostProcessor / BeanPostProcessor are infrastructure beans, not lifecycle-managed application beans.
  
``` 
 SpringApplication.run() ↓ Load BeanDefinitions(XML → @Configuration → @ComponentScan → @Bean methods) ↓ BeanFactoryPostProcessor ↓ Constructor ↓ Dependency Injection (Constructor → Field DI → Setter DI       )↓ postProcessBeforeInitialization ↓ @PostConstruct ↓ afterPropertiesSet ↓ customInit ↓ postProcessAfterInitialization (AOP) ↓ SmartInitializingSingleton ↓ APPLICATION READY ↓ @PreDestroy ↓ DisposableBean.destroy ↓ customDestroy

refresh() of container happens after the bean instantiation and before the ready phase. Other phases happens during the refresh
```

### SPRING BEAN LIFECYCLE IMPORTANT HOOKS
### LOAD PHASE  
**BeanFactoryPostProcessor (Interface)**

| Aspect | Explanation |
|------|-------------|
| Why this hook exists | Spring needs a phase where it can change bean blueprints (BeanDefinitions) before any object is created. This enables secrets injection (property values), conditional registration, scopes |
| Advantage: Runs earliest | Because it executes before instantiation, it can modify property values, scopes, and even register new beans. Later hooks cannot do this safely. |
| Advantage: Full control over configuration | Since beans don’t exist yet, Spring can freely reshape how they will be created without worrying about object state. |
| Drawback: No @PostConstruct / lifecycle callbacks | This is intentional. If lifecycle callbacks ran here, Spring would have to partially create the bean, which breaks the guarantee that no beans exist yet. |
| Why this drawback matters | You cannot rely on DI, environment binding, or external services. Any logic requiring injected dependencies becomes impossible here. |
| When this becomes a problem | If developers try to put business logic here , startup becomes fragile and error-prone. |
| Design tradeoff | Spring sacrifices convenience (no lifecycle callbacks) to preserve startup determinism and safety. |
| Used in | Spring Boot Auto-configurations , Spring Cloud Config / Vault / Consul, Security-heavy enterprise platforms |

---
### INSTANTIATION and DEPENDENCY INJECTION (BETWEEN THESE)
### INITIALIZATION PHASE (REFRESH STARTS HERE)  
**BeanPostProcessor (interface)**

| Aspect | Explanation |
|------|-------------|
| Why this hook exists | customize or enhance beans around initialization Some implementations register infrastructure (Kafka listeners), some implementations create proxies for cross-cutting concerns. postProcessBeforeInitialization: Allows frameworks to prepare beans before user init logic postProcessAfterInitialization: Allows frameworks to safely replace the bean instance |
| Advantage: Enables proxies & cross-cutting concerns | Beans are fully instantiated, so Spring can wrap them with proxies without changing business code. |
| Advantage: Keeps business code clean | Developers don’t need to manually wire Kafka consumers, transactions, or metrics. |
| Drawback: Bean identity confusion (proxy vs target) | Because Spring may replace the original bean with a proxy, == checks and type assumptions may break. Ex : if (bean instanceof MyConcreteClass) { ... }. Use Spring's AopUtils or check the actual target |
| Why this drawback exists | Proxies are separate objects. This is unavoidable if Spring wants to intercept method calls. |
| When it becomes a problem | Debugging issues where logic executes but breakpoints don’t hit expected classes. |
| Design tradeoff | Spring accepts proxy complexity to gain powerful cross-cutting extensibility. |
| Used in | Event-driven systems (Kafka, RabbitMQ), Transactional systems, Observability-heavy systems (metrics, tracing), other CCC |

---

### @PostConstruct (JSR-250 (Java standard) annotation)

| Aspect | Explanation |
|------|-------------|
| Why this hook exists | All configs & beans are available (after DI),Startup fails if client setup is wrong (validation),Single-time computation / derivation , Initialize external, we can load data once DI happened in repository layer |
| Advantage: Clean & simple | It’s a standard Java annotation, not Spring-specific, so code remains portable and readable. |
| Advantage: All dependencies are ready | Spring guarantees DI is complete before invoking it. |
| Drawback: No ordering guarantee across beans | Spring cannot safely enforce ordering without explicit dependency declarations. |
| Why this drawback exists | Enforcing ordering globally would slow startup and introduce hidden coupling. |
| When it becomes a problem | If one bean assumes another bean’s @PostConstruct has already run. |
| Design tradeoff | Spring prefers loose coupling over strict ordering guarantees. |
| Used in | Almost all spring boot apps |

---

### InitializingBean.afterPropertiesSet()

| Aspect | Explanation |
|------|-------------|
| Why this hook exists | Frameworks need a strong lifecycle contract independent of annotations. |
| Advantage: Deterministic invocation | Spring guarantees it is called exactly once after properties are set. |
| Advantage: Suitable for libraries | Library authors can rely on this hook without annotations or reflection. |
| Drawback: Spring coupling | Your class must implement a Spring interface, making it harder to reuse outside Spring. |
| Why this drawback exists | Interfaces create compile-time coupling by design. Not suitable for normal business logic and suitable to be used only within spring framework |
| When it becomes a problem | When you want to unit-test or reuse the class without Spring. |
| Design tradeoff | Spring favors explicit contracts for frameworks over portability. |
| Used in | Spring libraries, infrastructure modules |

---

### Custom Init / destroy method

| Aspect | Explanation |
|------|-------------|
| Why this hook exists | Third-party libraries cannot depend on Spring annotations or interfaces. |
| Advantage: Zero modification to third-party code | Spring can control lifecycle externally via configuration. @Bean(initMethod = "customInit", destroyMethod = "customDestroy") |
| Advantage: Clean separation | Infrastructure logic stays in configuration, not in library classes. |
| Drawback: No compile-time safety | Method names are strings — typos fail only at runtime. Refactoring won’t be caught by compiler |
| When it becomes a problem | Refactoring method names silently breaks startup. |
| Design tradeoff | Spring trades type safety for maximum flexibility. |
| Used in | Messaging systems (Kafka, JMS) Infrastructure heavy apps |

| Mechanism | When it runs |
|---------|--------------|
| Custom init / destroy | During bean lifecycle (startup / shutdown) |
| AOP | During method invocation at runtime |

---

### SmartInitializingSingleton

| Aspect | Explanation |
|------|-------------|
| Why this hook exists | used when a bean must run some logic only after all other singleton beans are completely ready |
| Advantage: Global stability guarantee | All beans, proxies, and configs are finalized. validate that all payment handlers are registered. start message processing only after everything is ready. |
| Advantage: Safe cross-bean logic | No race conditions during startup. SmartInitializingSingleton “Everything is ready.” Other beans: definitely ready, definitely final, stable state 👉 No race condition. |
| Drawback: Late execution | Startup-heavy logic here increases application boot time. |
| Why this drawback exists | Waiting for all beans necessarily delays execution. |
| When it becomes a problem | If misused for heavy business logic. |
| Design tradeoff | Spring delays execution to ensure correctness over speed. |
| Used in | Batch systems Security & retry frameworks |

---

### @PreDestroy (JSR-250 (Java standard) annotation)

| Aspect | Explanation |
|------|-------------|
| Why this hook exists | Applications must release resources and deregister gracefully before shutdown. DESTROY : Close connections, Flush buffers, Deregister resources |
| Advantage: Predictable cleanup | Spring calls it in reverse initialization order. |
| Advantage: Works with annotations | Easy to use for developers. |
| Drawback: Not guaranteed on SIGKILL | JVM cannot intercept forceful termination. |
| Why this drawback exists | OS-level process kill bypasses Java shutdown hooks. |
| When it becomes a problem | Abrupt container kills (OOM, kill -9). |
| Design tradeoff | Best-effort cleanup within JVM limits. |
| Used in | Cloud-native microservices Service discovery environments |

---

### DisposableBean

| Aspect | Explanation |
|------|-------------|
| Why this hook exists | Frameworks need mandatory cleanup guarantees. Just like InitializingBean.afterPropertiesSet() but for shutdown. |
| Advantage: Enforced destruction contract | Spring ensures cleanup even without annotations. Interface-based → strict contract |
| Advantage: Suitable for infra beans | Critical resources are always released. |
| Drawback: Strong Spring coupling | Code cannot run without Spring easily. |
| Why this drawback exists | Interface-based contracts require framework presence. |
| When it becomes a problem | Testing and portability outside Spring. |
| Design tradeoff | Spring prioritizes resource safety over portability. |
| Used in | Reactive systems Session-heavy platforms |

---

### Constructor vs Field vs Setter Injection

| Constructor | Field | Setter |
|------------|-------|--------|
| Enforces mandatory Dependencies. Optional also available @Autowired(required = false) | Optional also available @Autowired(required = false) | Optional also available @Autowired(required = false) |
| Doesn’t allow circular dependency | Allows circular dependency | Allows circular dependency |
| Ensure immutability (through final). If it’s final it has to be instantiated via constructor. If its instantiated via constructor it needs not to be final (optional) | Doesn’t ensure immutability | Doesn’t ensure immutability |
| Uses constructor | Uses reflection | Spring directly calls setter |
