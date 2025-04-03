# **🔹 SAP XSUAA (XSUAA - Xero Security User Account and Authentication) & Its Usage in Spring Boot**
SAP XSUAA (**Extended Services for User Authentication and Authorization**) is an **OAuth 2.0-based identity provider** used in SAP Business Technology Platform (SAP BTP). It is a key component in securing applications deployed on SAP BTP.

---

## **1️⃣ What is XSUAA?**
SAP XSUAA is a **managed authentication service** that provides:  
✅ **OAuth 2.0 support** for securing microservices.  
✅ **User authentication** using SAP ID service or custom identity providers.  
✅ **Role-based authorization** to restrict API access.  
✅ **JWT-based token validation** for secure communication.  
✅ **Integration with Spring Security** via `spring-xsuaa` library.

---

## **2️⃣ How XSUAA Works in Spring Boot?**
1️⃣ **User requests authentication** → SAP XSUAA verifies credentials.  
2️⃣ **XSUAA issues an OAuth 2.0 JWT token** with user roles.  
3️⃣ **Client sends the JWT token** in the `Authorization` header.  
4️⃣ **Spring Security validates the JWT** and enforces authorization rules.

---

## **3️⃣ Setting Up XSUAA in a Spring Boot Application**
### **📌 Step 1: Add Dependencies (`pom.xml`)**
```xml
<dependencies>
    <!-- Spring Boot Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- XSUAA Spring Security Adapter -->
    <dependency>
        <groupId>com.sap.cloud.security.xsuaa</groupId>
        <artifactId>spring-xsuaa</artifactId>
        <version>3.7.3</version>
    </dependency>

    <!-- OAuth 2.0 JWT Library -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
    </dependency>
</dependencies>
```
**🔹 Explanation:**  
✅ `spring-xsuaa` → Enables XSUAA authentication in Spring Boot.  
✅ `spring-boot-starter-oauth2-resource-server` → Validates JWT tokens.

---

## **4️⃣ Configuring XSUAA in Spring Boot**
### **📌 Step 2: Configure XSUAA Properties (`application.yml`)**
```yaml
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: https://your-xsuaa-domain/oauth/token
  xsuaa:
    url: https://your-xsuaa-domain
    clientid: your-client-id
    clientsecret: your-client-secret
    uaadomain: your-uaa-domain
    xsappname: your-app-name
    tenantid: your-tenant-id
```
**🔹 Explanation:**
- **issuer-uri** → URL of XSUAA token service.
- **clientid/clientsecret** → Used to authenticate the application.
- **uaadomain/xsappname/tenantid** → Required for multi-tenant scenarios.

---

## **5️⃣ Securing API Endpoints with XSUAA**
### **📌 Step 3: Create a Secure REST Controller (`UserController.java`)**
```java
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public API.";
    }

    @PreAuthorize("hasAuthority('ROLE_User')")
    @GetMapping("/user")
    public String userEndpoint() {
        return "Hello, USER! You are authenticated.";
    }

    @PreAuthorize("hasAuthority('ROLE_Admin')")
    @GetMapping("/admin")
    public String adminEndpoint() {
        return "Hello, ADMIN! You have full access.";
    }
}
```
**🔹 Explanation:**  
✅ **`/api/public`** → No authentication needed.  
✅ **`/api/user`** → Requires `ROLE_User`.  
✅ **`/api/admin`** → Requires `ROLE_Admin`.

---

## **6️⃣ Configure Security with XSUAA**
### **📌 Step 4: Security Configuration (`SecurityConfig.java`)**
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthorityPrefix(""); // Remove default "SCOPE_"

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/public").permitAll()
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
            );

        return http.build();
    }
}
```
**🔹 Explanation:**  
✅ **OAuth2 resource server** enables JWT authentication.  
✅ **JwtAuthenticationConverter** extracts roles from JWT.  
✅ **All requests require authentication except `/api/public`**.

---

## **7️⃣ Testing the Authentication**
### **📌 Step 5: Obtain JWT Token**
Use **SAP BTP CLI** or Postman to get a token:
```bash
curl -X POST https://your-xsuaa-domain/oauth/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "grant_type=password&client_id=your-client-id&client_secret=your-client-secret&username=shilpa&password=pass123"
```
**✅ Output:**
```json
{
    "access_token": "eyJhbGciOiJIUzI1NiIsInR..."
}
```

---

### **📌 Step 6: Call Secured APIs with JWT**
#### **1️⃣ Access Public API (No Authentication)**
```bash
curl -X GET http://localhost:8080/api/public
```
**✅ Output:** `"This is a public API."`

#### **2️⃣ Access User API with Token**
```bash
curl -X GET http://localhost:8080/api/user -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR..."
```
**✅ Output:** `"Hello, USER! You are authenticated."`

#### **3️⃣ Access Admin API with Token**
```bash
curl -X GET http://localhost:8080/api/admin -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR..."
```
**✅ Output:** `"Hello, ADMIN! You have full access."`

---

## **8️⃣ Summary**
✅ **XSUAA provides OAuth 2.0 authentication in SAP BTP.**  
✅ **Integrates with Spring Boot via `spring-xsuaa`.**  
✅ **Uses JWT tokens to secure APIs.**  
✅ **Supports role-based access control.**  
✅ **Works with SAP Identity Provider & other OAuth providers.**

Would you like a **multi-tenant XSUAA setup or refresh token example?** 🚀


I'll provide a **multi-tenant XSUAA setup** and a **refresh token example** in Spring Security. Stay tuned! 🚀

---

# **🔹 Multi-Tenant XSUAA Setup & Refresh Token in Spring Boot**
SAP XSUAA supports **multi-tenancy**, allowing different tenants (organizations) to access the same application with isolated authentication and authorization.

---

## **1️⃣ Multi-Tenant XSUAA Overview**
### **📌 How Multi-Tenancy Works in XSUAA?**
1️⃣ **User logs in** → XSUAA authenticates user based on the tenant.  
2️⃣ **XSUAA issues a tenant-specific JWT** → Contains `tenant_id` claim.  
3️⃣ **Spring Security extracts `tenant_id`** from JWT.  
4️⃣ **Authorization is applied** based on tenant roles.

---

## **2️⃣ Updating `application.yml` for Multi-Tenant Support**
```yaml
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          issuer-uri: https://your-xsuaa-domain/oauth/token
  xsuaa:
    url: https://your-xsuaa-domain
    clientid: your-client-id
    clientsecret: your-client-secret
    uaadomain: your-uaa-domain
    xsappname: your-app-name
    tenantmode: shared
    tenantid: "${TENANT_ID}"  # Tenant-specific authentication
```
**🔹 Explanation:**  
✅ **`tenantmode: shared`** → Enables multi-tenancy.  
✅ **`tenantid: ${TENANT_ID}`** → Fetches tenant dynamically.

---

## **3️⃣ Extracting `tenant_id` from JWT in Spring Security**
### **📌 Custom Tenant Resolver (`TenantResolver.java`)**
```java
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class TenantResolver {

    public String getTenantId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt.getClaimAsString("tenant_id");
        }
        return null; // No tenant found
    }
}
```
**🔹 Explanation:**  
✅ Extracts `tenant_id` from the JWT token.  
✅ Used to enforce tenant-based authorization.

---

## **4️⃣ Securing APIs Based on Tenants**
### **📌 Tenant-Based Security Configuration (`SecurityConfig.java`)**
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Configuration
public class SecurityConfig {

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        grantedAuthoritiesConverter.setAuthorityPrefix(""); // No prefix for roles

        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return converter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/public").permitAll()
                .requestMatchers("/api/tenant/**").authenticated()
                .anyRequest().hasAuthority("ROLE_Admin") // Only admins can access other APIs
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
            );

        return http.build();
    }
}
```
**🔹 Explanation:**  
✅ **Allows multi-tenant authentication** with `tenant_id`.  
✅ **Restricts access based on roles.**

---

## **5️⃣ Multi-Tenant Endpoint Example**
### **📌 Tenant-Specific API (`TenantController.java`)**
```java
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tenant")
public class TenantController {

    private final TenantResolver tenantResolver;

    public TenantController(TenantResolver tenantResolver) {
        this.tenantResolver = tenantResolver;
    }

    @GetMapping("/info")
    public String getTenantInfo() {
        String tenantId = tenantResolver.getTenantId();
        return "Tenant ID: " + (tenantId != null ? tenantId : "Unknown");
    }
}
```
**🔹 Explanation:**  
✅ **`/api/tenant/info`** → Returns `tenant_id` of authenticated user.

---

## **6️⃣ Implementing Refresh Token Mechanism**
### **📌 Why Use Refresh Tokens?**
✅ **Extends session without re-login**.  
✅ **Minimizes exposure of access tokens**.  
✅ **Improves security & performance**.

---

### **📌 Enable Refresh Token in `application.yml`**
```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          xsuaa:
            authorization-grant-type: refresh_token
```
---

### **📌 Custom Refresh Token Service (`TokenService.java`)**
```java
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class TokenService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String tokenUrl = "https://your-xsuaa-domain/oauth/token";

    public String refreshToken(String refreshToken) {
        Map<String, String> body = Map.of(
            "grant_type", "refresh_token",
            "client_id", "your-client-id",
            "client_secret", "your-client-secret",
            "refresh_token", refreshToken
        );

        return restTemplate.postForObject(tokenUrl, body, String.class);
    }
}
```
**🔹 Explanation:**  
✅ Sends a `refresh_token` request to XSUAA.  
✅ Returns a new `access_token`.

---

### **📌 Refresh Token Endpoint (`AuthController.java`)**
```java
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/refresh")
    public String refreshAccessToken(@RequestParam String refreshToken) {
        return tokenService.refreshToken(refreshToken);
    }
}
```
**🔹 Explanation:**  
✅ **Exposes `/auth/refresh`** for refreshing access tokens.

---

## **7️⃣ Testing Multi-Tenant & Refresh Token**
### **📌 Step 1: Get JWT Token for a Tenant**
```bash
curl -X POST https://your-xsuaa-domain/oauth/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "grant_type=password&client_id=your-client-id&client_secret=your-client-secret&username=shilpa&password=pass123"
```
**✅ Response:**
```json
{
    "access_token": "eyJhbGciOiJIUz...",
    "refresh_token": "dGhpcy1pcy1yZWZyZXNoLXRva2Vu"
}
```
---

### **📌 Step 2: Call a Tenant-Specific API**
```bash
curl -X GET http://localhost:8080/api/tenant/info -H "Authorization: Bearer eyJhbGciOiJIUz..."
```
**✅ Output:**
```json
{
    "Tenant ID": "12345"
}
```
---

### **📌 Step 3: Use Refresh Token to Get a New Access Token**
```bash
curl -X POST http://localhost:8080/auth/refresh \
  -d "refreshToken=dGhpcy1pcy1yZWZyZXNoLXRva2Vu"
```
**✅ Response:**
```json
{
    "access_token": "eyJhbGciOiJIUz...",
    "expires_in": 3600
}
```
---

## **8️⃣ Summary**
✅ **Multi-Tenant XSUAA** → Supports multiple tenants with `tenant_id`.  
✅ **Extract `tenant_id` from JWT** → Enforces tenant-specific authorization.  
✅ **Role-Based Access Control** → Restricts access based on roles.  
✅ **Refresh Token Support** → Extends session without re-login.

Would you like a **multi-tenant database setup with XSUAA?** 🚀

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