<div align="center">

# 🔐 Spring Boot OAuth2 Authorization Server

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.5-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Spring_Security-6.x-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white)](https://spring.io/projects/spring-security)
[![OAuth2](https://img.shields.io/badge/OAuth2-Authorization_Server-blue?style=for-the-badge&logo=auth0&logoColor=white)](https://spring.io/projects/spring-authorization-server)
![Author](https://img.shields.io/badge/Author-Naveed_Ali_Mohammed-purple?style=for-the-badge)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen?style=for-the-badge)](CONTRIBUTING.md)

A **production-ready** OAuth2 Authorization Server built with Spring Boot 3 and Spring Authorization Server.  
Supports **Authorization Code**, **Client Credentials**, and **Refresh Token** grant types with full **OpenID Connect 1.0** support.

[🚀 Quick Start](#-quick-start) • [📖 Documentation](#-how-it-works) • [🧪 Testing](#-testing-the-server) • [🤝 Contributing](CONTRIBUTING.md)

</div>

---

> [!NOTE]
> **📚 Learning Purpose Disclaimer**  
> This project is built and shared purely for **educational and learning purposes**.  
> You are free to **use, clone, and reference** it in your own projects.  
> The author is **not responsible** for any issues, damages, or misuse arising from the use of this code in any environment, including production.

---

## ✨ Features

- ✅ **Spring Authorization Server** (official Spring project)
- ✅ **Authorization Code Grant** with PKCE support
- ✅ **Client Credentials Grant**
- ✅ **Refresh Token Grant**
- ✅ **OpenID Connect 1.0** (OIDC) — UserInfo endpoint, ID Token
- ✅ **RSA-signed JWT** access tokens (2048-bit key pair, auto-generated)
- ✅ **JWKS endpoint** for token validation by resource servers
- ✅ **Configurable token TTL** (Access: 1 hour, Refresh: 1 day)
- ✅ **Custom scopes**: `openid`, `profile`, `read`, `admin`
- ✅ **Consent screen** support
- ✅ Form-based login for interactive flows
- ✅ Works seamlessly with the companion [OAuth2 Resource Server](https://github.com/naveedin2024/OAuth2ResourceServer)

---

## 🏗️ Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                     OAuth2 Flow Overview                     │
│                                                             │
│  ┌──────────┐   1. Auth Request    ┌─────────────────────┐  │
│  │  Client  │ ───────────────────► │  Authorization      │  │
│  │  App     │ ◄─────────────────── │  Server             │  │
│  │ :8080    │   2. JWT Token       │  :8091 (this repo)  │  │
│  └──────────┘                      └─────────────────────┘  │
│       │                                     ▲               │
│       │  3. Bearer Token                    │ JWKS          │
│       ▼                                     │ Validation    │
│  ┌──────────────────────┐                   │               │
│  │  Resource Server     │───────────────────┘               │
│  │  :8092               │                                   │
│  └──────────────────────┘                                   │
└─────────────────────────────────────────────────────────────┘
```

| Component | Port | Repository |
|---|---|---|
| **Authorization Server** ← **(this repo)** | `8091` | `OAuth2AuthorizationServer` |
| Resource Server | `8092` | [OAuth2ResourceServer](https://github.com/naveedin2024/OAuth2ResourceServer) |
| Example Client | `8080` | Your client app |

---

## ⚙️ Tech Stack

| Technology | Version | Purpose |
|---|---|---|
| Java | 17 | Runtime |
| Spring Boot | 3.2.5 | Application framework |
| Spring Authorization Server | 1.2.x | OAuth2 / OIDC implementation |
| Spring Security | 6.x | Security filter chain |
| Nimbus JOSE+JWT | bundled | RSA key pair & JWT signing |
| Maven | 3.x | Build tool |

---

## 🚀 Quick Start

### Prerequisites
- Java 17+
- Maven 3.6+  *(or Docker — see below)*

### Option A — Run with Maven

```bash
git clone https://github.com/naveedin2024/OAuth2AuthorizationServer.git
cd OAuth2AuthorizationServer
mvn spring-boot:run
```

### Option B — Run with Docker 🐳

```bash
git clone https://github.com/naveedin2024/OAuth2AuthorizationServer.git
cd OAuth2AuthorizationServer
docker compose up --build
```

The server starts on **http://localhost:8091**

### Verify It Is Running

```bash
curl http://localhost:8091/.well-known/oauth-authorization-server
```

You should see the OpenID Connect discovery document with all supported endpoints.

---

## 🔑 Default Credentials

> ⚠️ These are for **development/demo only**. Change before deploying.

| Type | Key | Value |
|---|---|---|
| User | Username | `user` |
| User | Password | `password` |
| Client | Client ID | `client` |
| Client | Client Secret | `secret` |
| Client | Redirect URI | `http://127.0.0.1:8080/login/oauth2/code/oidc-client` |

---

## 🧪 Testing the Server

### Get a Token via Client Credentials

```bash
curl -X POST http://localhost:8091/oauth2/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -u "client:secret" \
  -d "grant_type=client_credentials&scope=read"
```

**Response:**
```json
{
  "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6...",
  "token_type": "Bearer",
  "expires_in": 3599,
  "scope": "read"
}
```

### Authorization Code Flow

1. Open browser: `http://localhost:8091/oauth2/authorize?response_type=code&client_id=client&redirect_uri=http://127.0.0.1:8080/login/oauth2/code/oidc-client&scope=openid profile read&state=xyz`
2. Login with `user` / `password`
3. Approve consent screen
4. Exchange the `code` for a token:

```bash
curl -X POST http://localhost:8091/oauth2/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -u "client:secret" \
  -d "grant_type=authorization_code&code=YOUR_CODE&redirect_uri=http://127.0.0.1:8080/login/oauth2/code/oidc-client"
```

### Inspect JWKS (Public Keys)

```bash
curl http://localhost:8091/oauth2/jwks
```

---

## 📋 Available Endpoints

| Endpoint | Method | Description |
|---|---|---|
| `/.well-known/oauth-authorization-server` | GET | Discovery document |
| `/oauth2/authorize` | GET | Authorization endpoint |
| `/oauth2/token` | POST | Token endpoint |
| `/oauth2/jwks` | GET | JSON Web Key Set |
| `/oauth2/introspect` | POST | Token introspection |
| `/oauth2/revoke` | POST | Token revocation |
| `/connect/userinfo` | GET | OIDC UserInfo endpoint |
| `/login` | GET | Form login page |

---

## 🧰 Postman Collection

A ready-to-use Postman collection is included to test all endpoints:

📁 [`postman/OAuth2-Authorization-Server.postman_collection.json`](postman/OAuth2-Authorization-Server.postman_collection.json)

**Import steps:**
1. Open Postman → **Import** → select the file above
2. Run **"2. Client Credentials Grant → Get Token (read scope)"** — the token is auto-saved
3. Run downstream requests (Introspect, Revoke, etc.)

---

## 🔧 Configuration

Edit `src/main/resources/application.properties`:

```properties
# Server port
server.port=8091

# Logging (set to INFO in production)
logging.level.org.springframework.security=DEBUG
logging.level.org.springframework.security.oauth2=DEBUG
```

To use a custom client or user, edit `AuthorizationServerConfig.java`:
- `registeredClientRepository()` — register OAuth2 clients
- `userDetailsService()` — add in-memory users
- `TokenSettings` — configure token expiry

---

## 📁 Project Structure

```
OAuth2AuthorizationServer/
├── src/
│   └── main/
│       ├── java/com/naveed/oauth2/authorizationserver/
│       │   ├── OAuth2AuthorizationServerApplication.java  # Entry point
│       │   └── config/
│       │       └── AuthorizationServerConfig.java         # All OAuth2 config
│       └── resources/
│           └── application.properties                     # Server config
├── pom.xml                                                # Maven dependencies
└── README.md
```

---

## 🤝 Related Projects

| Project | Description |
|---|---|
| [OAuth2ResourceServer](https://github.com/naveedin2024/OAuth2ResourceServer) | Companion Resource Server that validates JWTs from this server |

---

## 🤝 Contributing

Contributions are welcome! Please read [CONTRIBUTING.md](CONTRIBUTING.md) for guidelines.

---

## 📄 License & Disclaimer

Copyright © 2026 **Naveed Ali Mohammed**. All rights reserved.

📚 **This project is intended for learning and educational purposes only.**  
You are free to use, reference, or adapt this code in your own projects.  
The author provides this project **as-is**, without warranty of any kind.  
The author is **not responsible** for any damages, data loss, or security issues arising from the use of this code.

---

<div align="center">

Made with ❤️ by **Naveed Ali Mohammed** · [GitHub](https://github.com/naveedin2024)

⭐ **Star this repo** if it helped you!

</div>

