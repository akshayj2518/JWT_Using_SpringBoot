# 🔐 Spring Boot JWT Authentication Example

This is a simple Spring Boot application that demonstrates **JWT-based authentication** using Spring Security. It includes:

- Login endpoint that generates JWT tokens
- Secure REST endpoint protected by JWT
- In-memory user store
- Stateless authentication using HTTP Bearer tokens

---

## 📁 Project Structure

src/main/java/com/test/security/ ├── AuthController.java # Handles login and token generation ├── AuthRequest.java # DTO for login request ├── JWTUtil.java # Utility for token generation and validation ├── JwtAuthFilter.java # Custom filter to extract and validate JWT ├── SecurityConfig.java # Spring Security configuration ├── TestController.java # Example protected API endpoint

yaml
--

## 🚀 How It Works

### 🔐 1. Login and Get JWT Token

**Endpoint:** `POST /auth/login`  
**Body:**
json - 
{
  "username": "john",
  "password": "password"
}
Response:

{
  "token": "<jwt-token>"
}
🔒 2. Access Protected Endpoint
Endpoint: GET /api/hello
Header:

makefile

Authorization: Bearer <jwt-token>
Response:
Hello, authenticated user!
If you omit the token or send an invalid one, you get a 401 Unauthorized.

🔧 Configuration
📦 application.properties
properties

jwt.secret={YOUR SECRET KEY}
jwt.expiration=3600000
server.port=8080
⚠️ Never hardcode real secrets in production. Use environment variables or a secrets manager.

👤 In-Memory User

Username	Password	Role
john	password	USER
🛠 Tech Stack
Java 17+

Spring Boot 3

Spring Security

JJWT (JSON Web Token)

✅ To Run
Clone the repo

Run mvn spring-boot:run or your IDE's run command

Use Postman or curl to test

📬 API Summary

Method	Endpoint	Auth Required	Description
POST	/auth/login	❌ No	Login and get JWT token
GET	/api/hello	✅ Yes	Protected hello message
🧪 Testing
✅ Login:
bash

POST /auth/login
Content-Type: application/json
Body:
{
  "username": "john",
  "password": "password"
}
✅ Use Token:
vbnet

GET /api/hello
Header:
Authorization: Bearer <token>
