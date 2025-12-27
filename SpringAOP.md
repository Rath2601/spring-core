### Spring AOP, Proxies, and AspectJ — Corrected & Precise Notes
---
#### 1. Java Features as Foundation for Spring Proxies
##### Interface
- Defines a **contract** that allows multiple implementations.
- Enables swapping implementations **without the caller knowing**.
- Callers depend on the **interface type**, not the concrete class.
- Forms the **basis for JDK Dynamic Proxies**.
---
##### Reflection
- Allows Java code to **inspect classes, methods, fields, and annotations** at runtime.
- Enables **dynamic method invocation**.
- Heavily used by frameworks (including Spring) for:
  - Annotation scanning
  - Method discovery
  - Dependency injection
  - Runtime invocation
---
##### Dynamic Class Generation (Proxy-related)
- Refers to **runtime generation of new classes or subclasses** using JVM bytecode.
- Used for **proxy creation**, not a standalone Java language feature.
- Implemented via:
  - **JDK Dynamic Proxy** → interface-based (Java SE)
  - **CGLIB / ByteBuddy** → class-based (external libraries)
> Note: Dynamic class generation is **not limited to interfaces** and is **not automatic**.
---
#### 2. Proxy in Java / Spring
##### What is a Proxy?
A **proxy** is an object that:
- Stands in front of a target object.
- Has the **same interface or method shape**.
- Intercepts method calls.
- Adds behavior **before, after, or around** the real method (AOP-style).

---
##### Proxy Types Used by Spring
1. **JDK Dynamic Proxy**
   - Interface-based
   - Part of Java SE
   - Proxy implements the interface
   - Uses `InvocationHandler`
2. **CGLIB / ByteBuddy Proxy**
   - Class-based
   - Creates a **runtime subclass** of the target class
   - Overrides eligible methods
   - Used when:
     - No interface exists
     - `proxyTargetClass = true`
---
##### Proxy Interception Rules (JVM-level)
- Only **non-final, non-private** methods can be intercepted.
- Methods must be **invoked via the proxy reference**.
- **Self-invocation bypasses the proxy**.
- `protected` and package-private methods are interceptable **only with class-based proxies (CGLIB)**.
- `private` and `final` methods are **never interceptable** by proxies.

> These limitations come from **JVM dispatch rules**, not Spring.
---
#### 3. How Spring AOP Works
- Spring AOP is **proxy-based**.
- Spring:
  1. Scans beans
  2. Finds applicable advisors (aspects)
  3. Creates a proxy (JDK or CGLIB)
  4. Registers the proxy as the bean
- Works **only when method calls go through the proxy**.
- @Transactional, @Cacheable, etc. apply only when called externally.
- Cross-bean calls go through proxies → aspects are applied.
- Uses **AspectJ annotations**, not AspectJ weaving
- Enabled via: **@EnableAspectJAutoProxy** (Auto-enabled in Spring Boot)
- **Limitations**: All the JVM proxy interception rules apply here
- Important: Helper methods inside aspects are irrelevant (that doesn't have limitations)

```
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
Target Method
```
---
#### 4. AspectJ (Bytecode Weaving)
- What AspectJ Does : Modifies compiled `.class` bytecode, not source code.
- Weaving happens at:
   **Compile-time (CTW)**
   **Load-time (LTW) — most common with Spring**
   **Post-compile**
- Enabled via **@EnableLoadTimeWeaving**
- Requires java agent enabled in JVM argument with `-javaagent:/path/to/aspectjweaver.jar`
- AspectJ Capabilities : Self-invocation works, private and final methods can be intercepted, Constructors can be intercepted, Works on non-Spring-managed objects, True bytecode-level AOP
- trade-off : Higher complexity, Harder debugging, Slower builds / startup, Less common in typical Spring applications
