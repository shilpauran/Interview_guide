# **🔹 Customer Onboarding via Subscription in Cloud Foundry (SAP BTP SaaS Model)**

In **Cloud Foundry (SAP BTP SaaS applications)**, **customer onboarding** is handled through the **SaaS Subscription Model** using the **SaaS Registry service**. This process allows customers (tenants) to subscribe to a multi-tenant application, triggering tenant provisioning, authentication setup, and routing configuration.

---

## **📌 Key Steps for Customer Onboarding via Subscription**
1️⃣ **SaaS Provider Application registers with SaaS Registry**  
2️⃣ **Customers (tenants) subscribe to the application** via Cloud Foundry Marketplace  
3️⃣ **Subscription callback API triggers** the onboarding process  
4️⃣ **Tenant-specific resources are provisioned** (e.g., DB schema, configurations)  
5️⃣ **Users authenticate via XSUAA** for tenant isolation  
6️⃣ **Requests are dynamically routed** to the correct tenant workspace

---

## **📌 Step-by-Step Implementation**
### **1️⃣ Define XSUAA Security Configuration (`xs-security.json`)**
XSUAA (SAP Authorization and Authentication Service) ensures **tenant isolation** and secure access.
```json
{
  "xsappname": "my-saas-app",
  "tenant-mode": "shared",
  "scopes": [
    {
      "name": "$XSAPPNAME.subscriber",
      "description": "Access for subscriber tenants"
    }
  ],
  "oauth2-configuration": {
    "grant-types": ["client_credentials"]
  }
}
```
📌 **Explanation**:
- `"tenant-mode": "shared"` → Enables **multi-tenancy**
- `"scopes"` → Defines **subscriber access**
- `"oauth2-configuration"` → Uses **OAuth 2.0 authentication**

---

### **2️⃣ Configure `mta.yaml` for SaaS Registry & XSUAA**
Modify the **Multi-Target Application (MTA) descriptor** to integrate **SaaS Registry** and **XSUAA**.

```yaml
modules:
  - name: my-saas-app
    type: nodejs
    path: .
    requires:
      - name: xsuaa
      - name: saas-registry
resources:
  - name: xsuaa
    type: org.cloudfoundry.managed-service
    parameters:
      service: xsuaa
      service-plan: application
  - name: saas-registry
    type: org.cloudfoundry.managed-service
    parameters:
      service: saas-registry
      service-plan: application
```
📌 **Explanation**:  
✔ **`xsuaa`** → Enables authentication and tenant isolation  
✔ **`saas-registry`** → Registers the app as a **multi-tenant SaaS provider**

---

### **3️⃣ Implement Subscription Handling (Tenant Onboarding API)**
The SaaS application **must handle subscription requests** when new customers subscribe.

#### **🔹 Define Subscription API (`subscription.js`)**
```javascript
const express = require("express");
const app = express();

// API for handling tenant subscription
app.post("/callback/v1.0/tenants/:tenantId", (req, res) => {
    const tenantId = req.params.tenantId;
    console.log(`New tenant subscribed: ${tenantId}`);

    // Provision tenant-specific resources (e.g., database schema, configurations)
    provisionTenantResources(tenantId);

    res.status(200).send({ message: "Subscription successful" });
});

// API for handling tenant unsubscription
app.delete("/callback/v1.0/tenants/:tenantId", (req, res) => {
    const tenantId = req.params.tenantId;
    console.log(`Tenant unsubscribed: ${tenantId}`);

    // Clean up tenant-specific resources
    removeTenantResources(tenantId);

    res.status(200).send({ message: "Unsubscription successful" });
});

function provisionTenantResources(tenantId) {
    console.log(`Provisioning resources for tenant: ${tenantId}`);
    // Create DB schema, configure settings, etc.
}

function removeTenantResources(tenantId) {
    console.log(`Removing resources for tenant: ${tenantId}`);
    // Drop DB schema, delete configurations, etc.
}

app.listen(3000, () => console.log("SaaS app running on port 3000"));
```
📌 **Explanation**:
- `POST /callback/v1.0/tenants/:tenantId` → Called when a **new tenant subscribes**
- `DELETE /callback/v1.0/tenants/:tenantId` → Called when a **tenant unsubscribes**
- **`provisionTenantResources(tenantId)`** → Creates tenant-specific DB/schema
- **`removeTenantResources(tenantId)`** → Cleans up tenant data when unsubscribed

---

### **4️⃣ Deploy & Register SaaS Application**
#### **Deploy to Cloud Foundry**
```sh
cf push my-saas-app
```
#### **Register Application with SaaS Registry**
```sh
cf create-service saas-registry application my-saas-registry
cf bind-service my-saas-app my-saas-registry
cf restage my-saas-app
```
📌 **Explanation**:  
✔ `cf push` → Deploys the SaaS application  
✔ `cf create-service saas-registry` → Registers the application for **multi-tenancy**  
✔ `cf bind-service` → Connects the application to SaaS Registry

---

### **5️⃣ Tenant Subscription via Cloud Foundry Marketplace**
Once deployed, customers **subscribe** to the SaaS app using **Cloud Foundry marketplace**:
```sh
cf create-service-key my-saas-registry tenant1
```
📌 **Explanation**:
- **Tenant 1 subscribes** to the SaaS app
- **SaaS Registry triggers** the subscription callback

---

## **📌 SaaS Subscription Workflow in Cloud Foundry**
### **🔹 Step-by-Step Tenant Onboarding Process**
1️⃣ **SaaS Provider registers** with SaaS Registry  
2️⃣ **Customer subscribes** via Cloud Foundry Marketplace  
3️⃣ **SaaS Registry triggers the subscription callback API**  
4️⃣ **The provider application provisions tenant resources**  
5️⃣ **Authentication via XSUAA ensures tenant security**  
6️⃣ **Requests are routed dynamically** based on tenant ID

---

## **✅ Summary**
| **Feature** | **SaaS Registry Role** |
|------------|------------------------|
| **Multi-Tenancy** | Manages tenant subscriptions and onboarding. |
| **Authentication** | Uses XSUAA for secure multi-tenant authentication. |
| **Routing** | Directs each tenant’s requests to the correct backend instance. |
| **Lifecycle Management** | Handles provisioning and cleanup of tenant-specific resources. |
| **Security & Isolation** | Ensures **separate database/schema for each tenant**. |

🚀 **Cloud Foundry SaaS Registry simplifies tenant onboarding for multi-tenant applications!** 🚀  
Let me know if you need a deeper dive into any step! 🎯