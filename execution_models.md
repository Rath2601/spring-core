### Code Execution Models, JVM, GraalVM, and Spring AOT (Validated Notes)
---
#### 1. C Language (Compiled Language)
**Flow**
```
.c file
  ↓ (Compiler: gcc/clang)
Native Binary (ELF / EXE)
  ↓
OS Loader
  ↓
Process executed on CPU
```
**Key Points**
* Fully **Ahead-Of-Time (AOT) compiled**.
* No VM or interpreter at runtime.
* Fast startup and execution.
* Platform-dependent binaries.
---
#### 2. Classic Python (Interpreted Language)
**Flow (CPython)**
```
.py file
  ↓
Compiled to bytecode (.pyc) at runtime
  ↓
Python Virtual Machine (Interpreter loop)
  ↓
OS
  ↓
Process on CPU
```
**Clarification**
* Python is **not purely interpreted**.
* Source code is first compiled into **bytecode**, then interpreted.

**Characteristics**
* Slower than Java/C for CPU-bound tasks(Pure computation, Cryptography & hashing, Image / video processing, Machine learning, Data & Algorithm, Scientific simulations).
* Runtime errors possible.
* Excellent for scripting and rapid development.
---
#### 3. Java (Hybrid: Compile-time + Runtime Optimization)
**Flow**
```
.java
  ↓ (javac)
.class (Bytecode)
  ↓
JVM
  ├─ Interpreter (initial execution)
  └─ JIT Compiler (hot code → native machine code)
  ↓
OS
  ↓
Process on CPU
```

**Key Concepts**
* **Write Once, Run Anywhere** (bytecode).
* JIT compiles frequently used paths for performance.
* JVM performs:
  * Garbage Collection
  * Runtime profiling
  * Adaptive optimization

**Usage**
* Ideal for **long-running services** since Machine code compiled by JIT compiler is cached (microservices, servers).
---

#### 4. GraalVM (JVM + Native Image AOT)
##### 4.1 GraalVM JVM Mode
* Drop-in replacement for HotSpot JVM.
* Faster JIT and better polyglot support.

##### 4.2 GraalVM Native Image Mode(True AOT)
**Flow**
```
Java Application
  ↓
GraalVM Native Image (Build Time)
  ↓
Native Binary
  ↓
OS
  ↓
Process on CPU
```

**What Happens**
* Entire application compiled to **native machine code at build time**.
* JVM is **not required at runtime**.

**Benefits**
* Extremely fast startup (milliseconds).
* Low memory footprint.

**Trade-offs**
* Limited reflection & dynamic class loading (class loaded during runtime even if it is not available during compile time).
* Longer build time.
* Requires explicit configuration.

**Why Suitable for Short-Lived Apps**
* Serverless / FaaS
* Batch jobs
---

#### 5. Spring AOT (Spring Framework Optimization)
### Important Distinction

| Feature      | JVM JIT              | GraalVM AOT   | Spring AOT           |
| ------------ | -------------------- | ------------- | -------------------- |
| Level        | JVM                  | JVM / Native  | Framework            |
| Purpose      | Runtime optimization | Native binary | Startup optimization |
| Removes JVM? | ❌                    | ✅             | ❌                    |

---

#### 6. Spring Application Startup (Without Spring AOT)
**Runtime Flow**
```
Build → Bytecode
Run → JVM starts
  ↓
Classpath scanning (@Component, @Configuration, @Bean,..)
Annotation processing (@ConditionalOnClass, @Profile, @AutoConfiguration,...)
Reflection-based wiring (discover constructors and resolve dependencies)
Proxy generation ((@Transactional, @Async, @Lazy)
Context initialization
  ↓
JIT compiles hot paths
```

**Issues**
* Slow startup
* Heavy reflection
* Costly for cloud-native apps (Spring AOT is optimized for speed and efficiency at startup with less memory and quick startup time ~50–200 ms)
* reason : **Large heap, Large metaspace (class metadata at runtime), Reflection caches, JIT compiler memory**
---

#### 7. Spring Application Startup (With Spring AOT)
##### Build Time
```
Spring AOT Engine
  ↓
Analyzes application
Generates:
  - Bean definitions
  - Proxy classes
  - Reflection metadata
```

##### Runtime
```
JVM starts
Spring uses generated code
Minimal reflection
Faster context initialization
JIT still applies
```

**Key Point**
* JVM is still used.
* Only **Spring's dynamic behavior** is optimized.
---

#### 8. Spring AOT Limitations
##### 8.1 Reflection
* Dynamic method/field access unknown at build time.
* Must be explicitly registered.

##### 8.2 Property Files
* Values may change per environment.
* AOT cannot assume runtime values.

##### 8.3 Proxies
* Dynamic proxy creation is hard to predict.
* Especially for AOP and transactional logic.

##### 8.4 Serialization
* Classes involved may not be known upfront.
* Requires configuration.
---
