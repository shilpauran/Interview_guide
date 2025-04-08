# **🔹 Spring Cache – In-Depth Guide**

## **📌 What is Spring Cache?**
Spring Cache **optimizes performance** by reducing redundant database queries and computation by storing frequently accessed data in memory.

✅ **Use Cases**
- **Reduce DB calls** for frequently accessed data
- **Improve performance** by caching results
- **Avoid redundant calculations**
- **Store API responses temporarily**

---

# **🔹 Step-by-Step Implementation of Spring Cache in Java**

## **📌 Step 1: Add Dependencies**
Include the necessary dependencies in `pom.xml`.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
</dependency>

<!-- Use Caffeine for in-memory caching -->
<dependency>
    <groupId>com.github.ben-manes.caffeine</groupId>
    <artifactId>caffeine</artifactId>
    <version>3.1.2</version>
</dependency>
```
✅ **`spring-boot-starter-cache`** enables Spring Cache  
✅ **Caffeine** is used for high-performance in-memory caching

---

## **📌 Step 2: Enable Caching in Spring Boot**
Annotate the main application class with `@EnableCaching`.

```java
package com.example.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching  // Enables Spring Cache
public class CacheApplication {
    public static void main(String[] args) {
        SpringApplication.run(CacheApplication.class, args);
    }
}
```
✅ **Activates caching in the application**

---

## **📌 Step 3: Implement Caching in a Service Layer**
Use `@Cacheable`, `@CachePut`, and `@CacheEvict` annotations.

```java
package com.example.cache.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Cacheable(value = "products", key = "#id")
    public String getProductById(Long id) {
        System.out.println("Fetching product from database...");
        return "Product-" + id;
    }
}
```
✅ **`@Cacheable` caches method results**  
✅ **If the data is cached, it skips database access**

---

## **📌 Step 4: Define Custom Cache Configuration**
You can configure caching with **Caffeine**.

```java
package com.example.cache.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("products");
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterWrite(10, TimeUnit.MINUTES)  // Cache expires in 10 min
                .maximumSize(100)  // Store max 100 items
        );
        return cacheManager;
    }
}
```
✅ **Customizes cache expiration and size**

---

## **📌 Step 5: Evict Cache When Data Changes**
When data is updated, we must clear outdated cache entries.

```java
package com.example.cache.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Cacheable(value = "products", key = "#id")
    public String getProductById(Long id) {
        System.out.println("Fetching product from database...");
        return "Product-" + id;
    }

    @CachePut(value = "products", key = "#id")
    public String updateProduct(Long id, String productName) {
        System.out.println("Updating product...");
        return productName;
    }

    @CacheEvict(value = "products", key = "#id")
    public void deleteProduct(Long id) {
        System.out.println("Deleting product from cache...");
    }
}
```
✅ **`@CachePut` updates cache when data is updated**  
✅ **`@CacheEvict` removes outdated cache data**

---

## **📌 Step 6: Test Caching**
Test the caching behavior using a simple `@RestController`.

```java
package com.example.cache.controller;

import com.example.cache.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable Long id, @RequestBody String name) {
        return productService.updateProduct(id, name);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
```
✅ **`GET /products/{id}` – Fetches from cache after first request**  
✅ **`PUT /products/{id}` – Updates cache**  
✅ **`DELETE /products/{id}` – Clears cache**

---

# **🔹 Advanced Spring Cache Features**
| Feature  | Description |
|----------|------------|
| **Custom Cache Manager** | Define expiration, eviction policies. |
| **Multi-Level Caching** | Combine **in-memory + Redis** caching. |
| **Conditional Caching** | Use `condition` in `@Cacheable` to cache only when needed. |
| **Distributed Cache** | Use **Redis** or **Hazelcast** for distributed caching. |

---

# **✅ Summary**
🚀 **Spring Cache boosts performance by reducing redundant DB queries**  
⚡ **`@Cacheable`, `@CachePut`, `@CacheEvict` manage caching efficiently**  
🔹 **Supports in-memory (Caffeine) and distributed caching (Redis, Hazelcast)**  
🌍 **Highly configurable for custom cache policies**

Let me know if you need more details! 🚀

# **🔹 Multi-Level Caching: In-Memory + Redis Caching**

## **📌 What is Multi-Level Caching?**
Multi-level caching **combines different caching layers** to optimize performance.
- **Level 1 (L1)** – **In-Memory Cache (Caffeine, EhCache, Guava, etc.)** (Fastest)
- **Level 2 (L2)** – **Distributed Cache (Redis, Hazelcast, Memcached, etc.)**

This ensures:  
✅ **Faster access** for frequently used data (**L1 memory cache first**)  
✅ **Consistency** using a shared **L2 distributed cache**  
✅ **Reduced DB hits**, even across multiple services

---

## **🔹 Step-by-Step Multi-Level Caching (Caffeine + Redis)**

### **📌 Step 1: Add Dependencies**
Include **Caffeine (L1) & Redis (L2)** in `pom.xml`.

```xml
<!-- Spring Boot Cache -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
</dependency>

<!-- Caffeine for L1 Cache -->
<dependency>
    <groupId>com.github.ben-manes.caffeine</groupId>
    <artifactId>caffeine</artifactId>
    <version>3.1.2</version>
</dependency>

<!-- Redis for L2 Cache -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```
✅ **Caffeine (L1)** stores data in local memory.  
✅ **Redis (L2)** stores cache at the distributed level.

---

### **📌 Step 2: Configure Multi-Level Caching**
Define a **custom `CacheManager`** to use both L1 (Caffeine) & L2 (Redis).

```java
package com.example.cache.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.cache.support.CompositeCacheManager;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableCaching
public class MultiLevelCacheConfig {

    // L1 - In-Memory Cache (Caffeine)
    @Bean
    public CaffeineCacheManager caffeineCacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("products");
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofMinutes(5))  // In-memory expires after 5 min
                .maximumSize(100)  // Store max 100 items
        );
        return cacheManager;
    }

    // L2 - Redis Cache
    @Bean
    public RedisCacheManager redisCacheManager(LettuceConnectionFactory redisConnectionFactory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofHours(1))  // Redis cache expires in 1 hour
                .disableCachingNullValues();
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(config)
                .build();
    }

    // Composite Cache Manager (L1 + L2)
    @Bean
    public CacheManager multiLevelCacheManager(CaffeineCacheManager caffeineCacheManager, RedisCacheManager redisCacheManager) {
        CompositeCacheManager compositeCacheManager = new CompositeCacheManager(
                caffeineCacheManager,  // L1 - Fastest
                redisCacheManager  // L2 - Persistent
        );
        compositeCacheManager.setFallbackToNoOpCache(true);
        return compositeCacheManager;
    }
}
```
✅ **L1 (Caffeine)** – Fastest, in-memory cache with 5 min expiry.  
✅ **L2 (Redis)** – Shared cache with 1-hour expiry.  
✅ **CompositeCacheManager** ensures L1 is used first, then L2.

---

### **📌 Step 3: Use Multi-Level Cache in Service**
Modify the service to use caching.

```java
package com.example.cache.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Cacheable(value = "products", key = "#id", sync = true)
    public String getProductById(Long id) {
        System.out.println("Fetching from DB...");
        return "Product-" + id;
    }
}
```
✅ **Checks L1 (Caffeine) first**  
✅ **If not found, checks L2 (Redis)**  
✅ **If not in L2, fetches from DB and caches it**

---

### **📌 Step 4: Test the Multi-Level Cache**
Call the endpoint twice and observe the logs.

```java
package com.example.cache.controller;

import com.example.cache.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public String getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }
}
```

### **✅ Test Calls**
```sh
# First Request (DB Call)
curl http://localhost:8080/products/1
# Output: Fetching from DB...

# Second Request (Cache Hit - L1)
curl http://localhost:8080/products/1
# Output: (No DB Call, served from Caffeine)

# After 5 minutes (Cache Hit - L2)
curl http://localhost:8080/products/1
# Output: (Served from Redis)
```
✅ **First call fetches from DB**  
✅ **Second call serves from L1 cache (Caffeine) – Fastest**  
✅ **After 5 minutes, L1 expires, fetches from L2 (Redis)**

---

# **🔹 Multi-Level Caching Workflow**
### **1️⃣ Request Flow**
1. **Check L1 (Caffeine) Cache**
    - ✅ If **found** → return immediately (fastest access)
    - ❌ If **not found** → check L2

2. **Check L2 (Redis) Cache**
    - ✅ If **found** → store in L1 for faster access next time
    - ❌ If **not found** → fetch from DB

3. **Fetch from Database**
    - Store result in **L1 (Caffeine)** for **5 min**
    - Store result in **L2 (Redis)** for **1 hour**

---

# **🔹 Why Use Multi-Level Caching?**
| Feature  | L1 (Caffeine) | L2 (Redis) |
|----------|-------------|-----------|
| **Speed** | Ultra-fast (in-memory) | Fast (network-based) |
| **Persistence** | Lost on restart | Persistent across services |
| **Scalability** | Local only | Shared across instances |
| **Eviction** | Short-term cache | Long-term storage |

✅ **Fast access** from **L1 (Caffeine)**  
✅ **Consistent data** across services via **L2 (Redis)**  
✅ **Reduced DB load** with caching at **multiple levels**

---

# **✅ Summary**
🚀 **Multi-Level Caching combines in-memory (L1) and distributed (L2) caches**  
🔹 **L1 (Caffeine) is fastest, L2 (Redis) ensures persistence**  
⚡ **Optimized DB queries → Faster response times**  
🌍 **Scales efficiently in Microservices & Kubernetes**

Let me know if you need more details! 🚀
