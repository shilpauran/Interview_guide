# **🔹 Spring Security - Complete Guide**
Spring Security is a powerful **authentication and authorization framework** used to secure Spring applications. It helps protect APIs, manage user roles, and integrate with OAuth, JWT, and custom authentication mechanisms.

---

## **1️⃣ Key Features of Spring Security**
✅ **Authentication & Authorization** – Validates users and controls access.  
✅ **Password Encoding** – Securely hashes and stores passwords.  
✅ **CSRF Protection** – Prevents cross-site request forgery attacks.  
✅ **Session Management** – Handles concurrent sessions, timeouts, and session hijacking.  
✅ **JWT & OAuth Support** – Works with **JSON Web Tokens (JWT)** and **OAuth2** for secure authentication.  
✅ **Method-Level Security** – Restricts access using `@PreAuthorize`, `@Secured`, etc.

---

## **2️⃣ Setting Up Spring Security in a Spring Boot Application**
### **📌 Add Dependencies (`pom.xml`)**
```xml
<dependencies>
    <!-- Spring Boot Security Starter -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- Spring Boot Web Starter -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Spring Boot Starter Data JPA (for user authentication with DB) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- H2 Database (for testing user authentication) -->
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- Password Encoder (BCrypt) -->
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-crypto</artifactId>
    </dependency>
</dependencies>
```
- **`spring-boot-starter-security`** enables Spring Security.
- **`spring-boot-starter-data-jpa`** helps in storing users in a database.
- **`h2`** is an in-memory database for testing.
- **`spring-security-crypto`** provides a **BCrypt password encoder**.

---

## **3️⃣ Default Security in Spring Boot**
By default, Spring Security:
- Secures all endpoints (`/`) with **Basic Authentication**.
- Provides a **default user** with the username `user`.
- Generates a **random password** (shown in logs).

**Start the application and check logs:**
```bash
Using generated security password: e8d5b9f1-xxxx-xxxx-xxxx-xxxxxxxxxxxx
```
- The default credentials:
    - **Username:** `user`
    - **Password:** *Generated from logs*

- Open Postman or a browser and access **`http://localhost:8080/`** → It will ask for credentials.

---

## **4️⃣ Customizing Spring Security**
### **📌 Disable Default Security and Configure Custom Authentication (`SecurityConfig.java`)**
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin").hasRole("ADMIN")  // Only ADMIN can access /admin
                .requestMatchers("/user").hasAnyRole("USER", "ADMIN") // USER and ADMIN can access /user
                .anyRequest().authenticated()) // All other requests need authentication
            .formLogin(withDefaults()) // Enable form-based login
            .httpBasic(withDefaults()); // Enable basic authentication

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user123")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin123")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
```
**🔹 Explanation:**
- **Spring Security is enabled (`@EnableWebSecurity`).**
- **Restricts endpoints:**
    - `/admin` → **Only ADMIN users can access**
    - `/user` → **USER & ADMIN can access**
- **Users are stored in memory (`InMemoryUserDetailsManager`).**

---

## **5️⃣ Securing REST APIs**
### **📌 Create a Simple REST Controller (`UserController.java`)**
```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint. No authentication required.";
    }

    @GetMapping("/user")
    public String userEndpoint() {
        return "Hello, USER! You are authenticated.";
    }

    @GetMapping("/admin")
    public String adminEndpoint() {
        return "Hello, ADMIN! You have full access.";
    }
}
```
- **`/api/public`** → No authentication required.
- **`/api/user`** → Requires `USER` or `ADMIN` role.
- **`/api/admin`** → Requires `ADMIN` role.

### **📌 Test API Using Postman**
#### **1️⃣ Public API (No Authentication)**
```bash
curl -X GET http://localhost:8080/api/public
```
**✅ Output:** `"This is a public endpoint. No authentication required."`

#### **2️⃣ User API (Authentication Required)**
```bash
curl -u user:user123 -X GET http://localhost:8080/api/user
```
**✅ Output:** `"Hello, USER! You are authenticated."`

#### **3️⃣ Admin API (Only for Admin)**
```bash
curl -u admin:admin123 -X GET http://localhost:8080/api/admin
```
**✅ Output:** `"Hello, ADMIN! You have full access."`

---

## **6️⃣ Securing Passwords with BCrypt**
### **📌 Store Passwords Securely**
Modify `SecurityConfig.java`:
```java
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```
Now, **hash passwords** before saving them in the database:
```java
String rawPassword = "admin123";
String hashedPassword = passwordEncoder().encode(rawPassword);
System.out.println("Encoded Password: " + hashedPassword);
```
- **Stored Password:** `$2a$10$7bX...` (BCrypt hashed)

---

## **7️⃣ Method-Level Security (`@PreAuthorize`)**
Enable method security:
```java
@EnableMethodSecurity
public class SecurityConfig { ... }
```
Restrict access inside controllers:
```java
@PreAuthorize("hasRole('ADMIN')")
@GetMapping("/secure-data")
public String secureData() {
    return "This data is for ADMINs only.";
}
```
- **Only users with ADMIN role can call this method.**

---

## **8️⃣ Summary**
✅ **Spring Security protects APIs and web applications.**  
✅ **Uses roles (`ADMIN`, `USER`) for authorization.**  
✅ **Supports Basic Auth, JWT, OAuth, and OAuth2.**  
✅ **Encrypts passwords using BCrypt.**  
✅ **Secures REST APIs and allows method-level access control.**

Would you like a **JWT authentication example** next? 🚀


# **🔹 JWT Authentication in Spring Security - Complete Guide**
JSON Web Token (**JWT**) is a secure way to authenticate users in **Spring Boot applications** without storing session data on the server.

### **🔹 Why Use JWT?**
✅ **Stateless Authentication** – No session storage required.  
✅ **Secure** – Uses cryptographic signatures for verification.  
✅ **Scalable** – Suitable for microservices architecture.  
✅ **Decentralized Authentication** – Can be used across multiple applications.

---

## **1️⃣ How JWT Authentication Works?**

1️⃣ **User logs in** with username & password → Server verifies credentials.  
2️⃣ **Server generates a JWT Token** with user details.  
3️⃣ **Client stores JWT Token** in local storage or cookies.  
4️⃣ **For each request**, client sends the **JWT in the Authorization header**.  
5️⃣ **Server validates JWT** → Grants access if valid.

---

## **2️⃣ Project Setup**
### **📌 Add Dependencies (`pom.xml`)**
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

    <!-- Spring Boot JPA (for storing users in DB) -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- JWT Library -->
    <dependency>
        <groupId>io.jsonwebtoken</groupId>
        <artifactId>jjwt</artifactId>
        <version>0.11.5</version>
    </dependency>

    <!-- Lombok (to reduce boilerplate code) -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <scope>provided</scope>
    </dependency>

    <!-- H2 Database (for testing) -->
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <scope>runtime</scope>
    </dependency>
</dependencies>
```
---

## **3️⃣ Creating JWT Utility Class**
### **📌 JWT Utility Class (`JwtUtil.java`)**
```java
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final String SECRET_KEY = "MySecretKeyForJWTThatIsAtLeast32CharsLong";

    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // Generate JWT Token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Extract Username from JWT
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Validate JWT Token
    public boolean validateToken(String token, String username) {
        String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    // Check if Token Expired
    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}
```
**🔹 Explanation:**  
✅ **Generates JWT Token** with expiration.  
✅ **Extracts username from token**.  
✅ **Validates token expiry & authenticity**.

---

## **4️⃣ User Authentication**
### **📌 User Entity (`User.java`)**
```java
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role;
}
```

---

### **📌 User Repository (`UserRepository.java`)**
```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
```
- Finds users by **username** in the database.

---

### **📌 Authentication Request & Response DTOs**
```java
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AuthRequest {
    private String username;
    private String password;
}

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AuthResponse {
    private String token;
}
```
- **AuthRequest** → Receives username & password.
- **AuthResponse** → Sends JWT token.

---

## **5️⃣ Implementing Authentication**
### **📌 Auth Controller (`AuthController.java`)**
```java
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());

        String token = jwtUtil.generateToken(userDetails.getUsername());
        return new AuthResponse(token);
    }
}
```
**🔹 Explanation:**  
✅ **Authenticates user credentials.**  
✅ **Generates a JWT Token** on successful login.

---

## **6️⃣ Secure Endpoints with JWT**
### **📌 Secure Controller (`UserController.java`)**
```java
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint.";
    }

    @GetMapping("/secure")
    public String secureEndpoint() {
        return "This is a secure endpoint. You must be authenticated.";
    }
}
```

---

## **7️⃣ Configuring Spring Security**
### **📌 Security Configuration (`SecurityConfig.java`)**
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManager.class);
    }
}
```
**🔹 Explanation:**  
✅ **Uses BCrypt to store passwords securely.**  
✅ **Enables JWT-based authentication.**

---

## **8️⃣ Testing JWT Authentication**
### **📌 Step 1: Login and Get Token**
```bash
curl -X POST http://localhost:8080/auth/login -H "Content-Type: application/json" -d '{"username": "shilpa", "password": "pass123"}'
```
**✅ Output:**
```json
{
    "token": "eyJhbGciOiJIUzI1NiIsInR..."
}
```

### **📌 Step 2: Access Secure API with Token**
```bash
curl -X GET http://localhost:8080/api/secure -H "Authorization: Bearer eyJhbGciOiJIUz..."
```

---

## **9️⃣ Summary**
✅ **JWT provides stateless authentication**  
✅ **Spring Security ensures secure access**  
✅ **Uses `BCrypt` to hash passwords**  
✅ **JWT token is stored in `Authorization` header**

Would you like to see **refresh tokens or role-based access?** 🚀

