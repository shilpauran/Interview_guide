# **🔹 Webhooks in Java – Implementation Guide**

## **📌 What is a Webhook?**
A **webhook** is a **callback mechanism** where a server notifies another system **in real-time** when an event occurs. Unlike polling, webhooks send data only when needed, improving efficiency.

✅ **Example Use Cases:**
- Payment processing notifications (e.g., Stripe, PayPal)
- CI/CD pipeline triggers (e.g., GitHub, Jenkins)
- Event-driven microservices communication

---

## **📌 Step-by-Step Implementation of Webhooks in Java (Spring Boot)**
### **1️⃣ Create a Spring Boot Application**
First, create a Spring Boot project with dependencies:

📌 **Add dependencies in `pom.xml`**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

---

### **2️⃣ Create a Webhook Endpoint**
The webhook endpoint **listens for incoming events** from external services.

📌 **Example: Webhook Controller (`WebhookController.java`)**
```java
package com.example.webhook;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    @PostMapping("/event")
    public ResponseEntity<String> handleWebhook(@RequestBody Map<String, Object> payload) {
        System.out.println("Received Webhook: " + payload);

        // Process the webhook data
        if (payload.containsKey("event") && "order_created".equals(payload.get("event"))) {
            System.out.println("Processing order...");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Webhook received successfully");
    }
}
```
✅ **Listens for incoming webhook events at `/webhook/event`**
✅ **Processes payload and logs received data**

---

### **3️⃣ Secure the Webhook using Signature Validation**
To prevent unauthorized calls, webhooks should include **a secret key for verification**.

📌 **Example: Verify Webhook Signature (`WebhookController.java`)**
```java
package com.example.webhook;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Map;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    private static final String SECRET_KEY = "your-webhook-secret";

    @PostMapping("/event")
    public ResponseEntity<String> handleWebhook(
            @RequestHeader("X-Signature") String signature,
            @RequestBody String payload) {

        if (!verifySignature(payload, signature)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid signature");
        }

        System.out.println("Valid Webhook received: " + payload);
        return ResponseEntity.status(HttpStatus.OK).body("Webhook processed");
    }

    private boolean verifySignature(String payload, String signature) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(SECRET_KEY.getBytes(), "HmacSHA256"));
            byte[] hash = mac.doFinal(payload.getBytes());
            String expectedSignature = Base64.getEncoder().encodeToString(hash);
            return expectedSignature.equals(signature);
        } catch (Exception e) {
            return false;
        }
    }
}
```
✅ **Validates webhook requests using HMAC-SHA256 signatures**
✅ **Prevents unauthorized access**

---

### **4️⃣ Send a Webhook Event from a Java Service**
A service can **trigger a webhook event** using **RestTemplate** or **WebClient**.

📌 **Example: Send Webhook Event (`WebhookSender.java`)**
```java
package com.example.webhook;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

public class WebhookSender {

    public static void sendWebhook() {
        RestTemplate restTemplate = new RestTemplate();
        String webhookUrl = "http://localhost:8080/webhook/event";

        Map<String, Object> payload = new HashMap<>();
        payload.put("event", "order_created");
        payload.put("order_id", 12345);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(webhookUrl, request, String.class);

        System.out.println("Webhook Response: " + response.getBody());
    }

    public static void main(String[] args) {
        sendWebhook();
    }
}
```
✅ **Triggers a webhook event (`order_created`) to an external service**

---

## **📌 Step 5: Deploy and Test**
### **🔹 Start the Webhook Listener**
```sh
mvn spring-boot:run
```
The webhook will listen on `http://localhost:8080/webhook/event`.

### **🔹 Send a Test Webhook**
Using **cURL**:
```sh
curl -X POST http://localhost:8080/webhook/event \
     -H "Content-Type: application/json" \
     -d '{"event": "order_created", "order_id": 12345}'
```
Output:
```
Received Webhook: {event=order_created, order_id=12345}
```
✅ **Webhook successfully received and processed**

---

## **📌 Key Considerations for Webhooks in Java**
| 🔹 Feature         | 🔹 Description |
|-------------------|--------------|
| **Security**      | Use **HMAC signature verification** to prevent unauthorized requests. |
| **Retries**       | Implement **retry logic** for failed webhook delivery. |
| **Logging**       | Log incoming webhooks for debugging and tracking. |
| **Asynchronous Processing** | Process webhook events **asynchronously using Kafka or RabbitMQ**. |

---

## **✅ Summary**
🚀 **Webhooks enable real-time event-driven communication**
⚡ **Spring Boot provides a simple way to build webhook listeners**
🔒 **Secure webhooks using HMAC signatures**
🌍 **Use webhooks for notifications, payments, and microservices integration**

Let me know if you need more details! 🚀