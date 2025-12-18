### Spring AOP, Proxies, and AspectJ — Detailed Notes
#### 1. Java Features as Foundation for Spring Proxies
##### Interface
- Defines a **contract** that allows multiple implementations.
- Enables swapping implementations **without the caller knowing**.
- Callers depend on the **interface type**, not the concrete class.
##### Reflection
- Allows Java code to **inspect classes, methods, and annotations** at runtime.
- Enables **dynamic method invocation** at runtime.
- Heavily used by frameworks (including Spring) for:
  - Annotation processing
  - Method discovery
  - Runtime invocation
##### Dynamic Class Generation (PROXY)
- Creates **new classes at runtime** by generating **JVM bytecode**, without modifying the original class.
- Libraries like **CGLIB** and **ByteBuddy** are used.
- (JIT compilation to native code happens later but is not part of proxy creation.)

---

#### 2. Proxy in Java / Spring

- A **proxy** is an object that:
  - Stands in front of another object.
  - Intercepts method calls.
  - Adds behavior **before, after, or around** the real method (AOP features).
  
- **Spring uses two proxy types:**
  1. **JDK Dynamic Proxy** — Interface-based (requires an interface)
  2. **CGLIB(Code Generation Library) Proxy** — Class-based (subclasses the target class)

##### Proxy Interception Rules
- Can intercept **non-final, non-private methods** only.
- Methods must be **invoked via the proxy**.
- Package-private and protected **methods** are interceptable **only with CGLIB**.

---

#### 3. How Spring AOP Works
- Works **only when method calls go through the proxy**.
- **Self-invocation (`this.method()`) bypasses the proxy** → AOP is skipped.
- `@Transactional` works **only when called from outside the bean**.
- **Cross-bean calls** go through proxies → aspects are applied.

##### Aspect Execution Flow (Example)
```text
Caller
  ↓
CGLIB Proxy
  ↓
RateLimiterInterceptor
  ↓
CircuitBreakerInterceptor
  ↓
TimedInterceptor
  ↓
SentryInterceptor
  ↓
Real Method
```
Each aspect = one wrapper layer (Caller -> CGLIB Proxy -> RateLimiterAspect -> CircuitBreakerAspect -> TimedAspect -> SentryAspect -> Real Method)

##### Spring AOP Summary: 
* Uses proxy-based interception instead of bytecode weaving but uses AspectJ.
* Enabled via: `@EnableAspectJAutoProxy`
* Limitations: Self-invocation does not work, Private and final methods cannot be intercepted.
* Important Note: AOP limitations apply to target methods, not private helper methods inside the aspect.
##### AspectJ (Bytecode Weaving)
* Modifies class bytecode at: **Compile-time**, Load-time (in JVM memory)
* Enabled via: `@EnableLoadTimeWeaving`
* Capabilities: Self-invocation works, Private and final methods can be intercepted, Constructors can be intercepted, Works on non-Spring-managed objects
* Java agent also needs to be added in startup `-javaagent:/path/to/aspectjweaver.jar`

| Feature                       | Spring AOP (Proxy) | AspectJ (Bytecode Weaving) |
| ----------------------------- | ------------------ | -------------------------- |
| Interception mechanism        | Proxy              | Bytecode weaving           |
| Modifies `.java` files        | ❌ No               | ❌ No                       |
| Modifies `.class` bytecode    | ❌ No               | ✅ Yes                      |
| Self-invocation               | ❌ No               | ✅ Yes                      |
| Private / final methods       | ❌ No               | ✅ Yes                      |
| Applies to non-Spring objects | ❌ No               | ✅ Yes                      |
| Complexity                    | Low                | High                       |

**Proxy** = wrapper around the bean
**Bytecode weaving** = modifies the actual method implementations in the class

