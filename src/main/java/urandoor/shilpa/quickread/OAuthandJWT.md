Sure, Shilpa! Let’s break down **OAuth** and **JWT (JSON Web Token)** in a **clear, structured, interview-friendly format** with real-world context and **code examples** in Java (Spring Security).

---

## 🔐 **OAuth and JWT Explained**

---

### ✅ **1. What is OAuth?**

> **OAuth (Open Authorization)** is a **delegation protocol**. It allows a user to grant a **third-party application** access to their resources **without sharing their credentials**.

#### 📌 Key Roles:
| Role | Description |
|------|-------------|
| **Resource Owner** | The user who owns the data |
| **Client** | Application requesting access |
| **Authorization Server** | Issues access tokens |
| **Resource Server** | Hosts the protected resources (APIs) |

#### 🔁 **Flow Example (OAuth 2.0 Authorization Code Grant)**:
1. Client redirects user to Authorization Server (e.g., Google Login)
2. User authenticates → Grants access
3. Authorization Server sends back an **Authorization Code**
4. Client exchanges this code for an **Access Token**
5. Access Token is used to access protected APIs

---

### ✅ **2. What is JWT (JSON Web Token)?**

> **JWT** is a **compact, self-contained token format** used for securely transmitting information between parties as a JSON object.

- It's often used **with OAuth** for **token-based authentication**.
- The token is **digitally signed** (using HMAC or RSA).
- It is **stateless** – no session needs to be stored on server.

#### 📦 JWT Structure:
```
HEADER.PAYLOAD.SIGNATURE
```
- **Header** → Type & algorithm (e.g., HS256)
- **Payload** → Claims (e.g., `sub`, `exp`, `role`)
- **Signature** → To ensure token integrity

#### 🧾 Example JWT:
```json
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9. 
eyJzdWIiOiIxMjM0IiwibmFtZSI6IlNoaWxwYSIsInJvbGUiOiJBRE1JTiJ9. 
dBjftJeZ4CVP-mB92K27uhbUJU1p1r_wW1gFWFOEjXk
```

---

## 🔄 How OAuth and JWT Work Together

| OAuth Provides | JWT Is |
|----------------|--------|
| **Access delegation** (get a token) | **Format** of that token |
| Token lifecycle, refresh, scopes | Encoded payload used in Authorization headers |
| Login via Facebook, Google | Carry identity/roles in API calls |

---

## ✅ Spring Boot Implementation (JWT with OAuth 2)

### 🔹 Step 1: Add Dependencies
```xml
<!-- Spring Security + OAuth2 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-oauth2-resource-server</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

---

### 🔹 Step 2: Configure Resource Server to Use JWT

```yaml
# application.yml
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          jwk-set-uri: https://auth-server.com/.well-known/jwks.json
```

---

### 🔹 Step 3: Secure Your REST API

```java
@RestController
public class ProductController {

    @GetMapping("/api/products")
    public String getProducts(@AuthenticationPrincipal Jwt jwt) {
        // Access claims from token
        String username = jwt.getClaim("sub");
        String role = jwt.getClaim("role");
        return "Hello " + username + "! Role: " + role;
    }
}
```

---

### 🔹 Step 4: Customize JWT Security Configuration

```java
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
          .authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/admin/**").hasRole("ADMIN")
            .anyRequest().authenticated()
          )
          .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

        return http.build();
    }
}
```

---

## 🔐 OAuth vs JWT - Comparison

| Feature | OAuth | JWT |
|--------|------|-----|
| **Purpose** | Access delegation | Token format |
| **Used for** | Login via third-party (e.g., Google) | API security, identity claims |
| **Stateful?** | Depends (OAuth server tracks sessions) | Stateless |
| **Token format** | Can be JWT, Opaque | JWT only |
| **Includes roles, claims?** | Not always | Yes |
| **Expiration** | Supports refresh tokens | Yes, `exp` claim |

---

## ✅ Real-world Example

- **User logs in via Google (OAuth 2.0)**
- Google issues an **Access Token** in **JWT format**
- Your app uses that JWT to call protected microservices
- Each microservice **verifies JWT** to allow/deny access (no need to check Google again)

---

## 💡 Summary
| Concept | Purpose |
|--------|---------|
| **OAuth** | Delegates access using tokens |
| **JWT** | Encoded, signed format for transmitting claims securely |
| **Used together** | OAuth issues JWT as access token |

---

Let me know if you'd like a **working project** setup or need to cover **refresh tokens, scopes, custom claims**, or **integration with Keycloak, XSUAA, or Auth0** next!