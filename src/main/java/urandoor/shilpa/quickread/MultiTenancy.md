# **🔹 Key Considerations for Multi-Tenancy in a Microservice Architecture**
Multi-tenancy in a microservice-based system requires careful design across **database, authentication, authorization, and scalability**. Below are the critical aspects to consider:

---

## **1️⃣ Tenant Identification & Management**
### ✅ **How to Identify a Tenant?**
- **JWT Token (XSUAA, Keycloak, Auth0, etc.)** → Extract `tenant_id` from claims.
- **Subdomain-Based Routing** → `tenant1.example.com`, `tenant2.example.com`.
- **Header-Based Identification** → `X-Tenant-ID` in HTTP headers.
- **Separate Authentication Systems** → Each tenant has its own IdP (Identity Provider).

### 🔹 **Best Practice:**
Use **JWT tokens** for security and **subdomains or headers** for routing.

---

## **2️⃣ Database Strategies for Multi-Tenancy**
Choosing the right database strategy depends on the level of data isolation required.

| **Strategy**  | **Description** | **Pros** | **Cons** |
|--------------|----------------|----------|----------|
| **Shared Database, Shared Schema** | Single DB, tenants identified via `tenant_id` column | ✅ Cost-effective ✅ Easy to scale | ❌ Risk of data leaks ❌ Complex query isolation |
| **Shared Database, Separate Schema** | Single DB, but each tenant has its own schema (`tenant1.users`, `tenant2.users`) | ✅ Better isolation ✅ Easy backup per tenant | ❌ Schema management overhead ❌ Harder migrations |
| **Separate Database per Tenant** | Each tenant has its own independent database | ✅ Best security ✅ Performance isolation | ❌ Expensive ❌ Harder to scale dynamically |

### 🔹 **Best Practice:**
Use **"Shared DB, Separate Schema"** for **B2B SaaS** and **"Separate DB per Tenant"** for **high-security use cases**.

---

## **3️⃣ Multi-Tenant Database Implementation in Spring Boot**
### **📌 Dynamic DataSource Routing (Separate Schema Approach)**
1️⃣ **Tenant Resolver:** Extracts `tenant_id` from the JWT.  
2️⃣ **Dynamic DataSource Routing:** Switches schemas dynamically based on `tenant_id`.  
3️⃣ **Interceptor for Each Request:** Ensures the correct tenant is used.

#### **Step 1: Extract Tenant from JWT (`TenantResolver.java`)**
```java
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class TenantResolver {

    public String getTenantId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt.getClaimAsString("tenant_id"); // Extract tenant_id from JWT
        }
        return "default"; // Default tenant (fallback)
    }
}
```
---

#### **Step 2: Implement Dynamic DataSource Routing (`TenantDataSourceRouter.java`)**
```java
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class TenantDataSourceRouter extends AbstractRoutingDataSource {

    private final TenantResolver tenantResolver;

    public TenantDataSourceRouter(TenantResolver tenantResolver) {
        this.tenantResolver = tenantResolver;
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return tenantResolver.getTenantId(); // Set tenant_id dynamically
    }
}
```
---

#### **Step 3: Configure Multiple DataSources (`DataSourceConfig.java`)**
```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Autowired
    private TenantResolver tenantResolver;

    @Bean
    public DataSource dataSource() {
        Map<Object, Object> dataSources = new HashMap<>();
        
        // Define tenant-specific DataSources
        dataSources.put("tenant1", createDataSource("jdbc:mysql://localhost:3306/tenant1db"));
        dataSources.put("tenant2", createDataSource("jdbc:mysql://localhost:3306/tenant2db"));

        TenantDataSourceRouter router = new TenantDataSourceRouter(tenantResolver);
        router.setTargetDataSources(dataSources);
        router.setDefaultTargetDataSource(dataSources.get("default"));

        return router;
    }

    private DataSource createDataSource(String url) {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(url);
        ds.setUsername("root");
        ds.setPassword("password");
        return ds;
    }
}
```
---

## **4️⃣ Tenant-Based Authorization in Spring Security**
### **📌 Define Role-Based Access (`SecurityConfig.java`)**
```java
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/public").permitAll()
                .requestMatchers("/api/tenant/**").authenticated()
                .anyRequest().hasAuthority("ROLE_Admin") // Only admins can access other APIs
            )
            .oauth2ResourceServer(oauth2 -> oauth2.jwt());
        
        return http.build();
    }
}
```
**🔹 Explanation:**  
✅ Allows multi-tenant authentication with `tenant_id`.  
✅ Restricts access based on roles.

---

## **5️⃣ Managing Schema Migration & Data Isolation**
### **📌 Handling Schema Migrations for Each Tenant**
Use **Flyway or Liquibase** to manage schema migrations for each tenant.

#### **Step 1: Enable Multi-Tenant Migrations (`application.yml`)**
```yaml
spring:
  flyway:
    enabled: true
    schemas: tenant1, tenant2  # List of tenant schemas
```

#### **Step 2: Flyway Configuration (`FlywayConfig.java`)**
```java
@Configuration
public class FlywayConfig {

    @Bean
    public Flyway flyway(DataSource dataSource) {
        return Flyway.configure()
            .dataSource(dataSource)
            .schemas("tenant1", "tenant2") // Ensure migration for each tenant
            .load();
    }
}
```

---

## **6️⃣ Monitoring & Scaling Multi-Tenant Systems**
### ✅ **Scalability Considerations**
- **Horizontal Scaling** → Deploy multiple instances, each handling different tenants.
- **Read Replicas** → Optimize database reads for high traffic tenants.
- **Sharding Strategy** → Distribute tenants across multiple databases dynamically.

### ✅ **Monitoring Multi-Tenant Systems**
- **Log Aggregation** → Use **Elastic Stack** (ELK) to separate logs by `tenant_id`.
- **Tenant-Specific Metrics** → Integrate **Prometheus + Grafana** for tenant-level dashboards.

---

## **7️⃣ Summary**
✅ **Tenant Identification** → Use JWT, subdomains, or headers.  
✅ **Database Strategy** → Use shared DB with separate schemas or separate DBs per tenant.  
✅ **Dynamic DataSource Routing** → Switch schemas dynamically using `TenantDataSourceRouter`.  
✅ **Authorization** → Use Spring Security with role-based access control (RBAC).  
✅ **Schema Migrations** → Use Flyway/Liquibase for tenant-specific database updates.  
✅ **Scalability** → Ensure horizontal scaling, read replicas, and sharding for performance.

Would you like a **tenant creation API to onboard new tenants dynamically?** 🚀