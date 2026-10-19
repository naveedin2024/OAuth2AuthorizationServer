# Contributing to OAuth2 Authorization Server

Thank you for your interest in contributing! 🎉

## How to Contribute

### 🐛 Reporting Bugs
1. Check [existing issues](https://github.com/naveedin2024/OAuth2AuthorizationServer/issues) first
2. Use the **Bug Report** issue template
3. Include: Java version, Spring Boot version, steps to reproduce, expected vs actual behavior

### 💡 Suggesting Features
1. Open a [Feature Request](https://github.com/naveedin2024/OAuth2AuthorizationServer/issues/new?template=feature_request.md)
2. Describe the use case and motivation clearly

### 🔧 Submitting Pull Requests

1. **Fork** the repository
2. **Create a branch**: `git checkout -b feature/your-feature-name`
3. **Make your changes** following the coding standards below
4. **Test** your changes locally
5. **Commit** using conventional commits: `git commit -m "feat: add database-backed client repository"`
6. **Push** to your fork: `git push origin feature/your-feature-name`
7. **Open a Pull Request** targeting the `main` branch

## Coding Standards

- Follow standard Java naming conventions
- Add Javadoc to public classes and methods
- Keep changes focused — one feature/fix per PR
- Do not commit secrets or credentials

## Conventional Commit Types

| Type | Description |
|---|---|
| `feat` | New feature |
| `fix` | Bug fix |
| `docs` | Documentation only |
| `refactor` | Code refactor (no new feature, no fix) |
| `test` | Adding tests |
| `chore` | Build, dependency updates |

## Ideas for Contributions

- [x] Docker & Docker Compose setup *(done!)*
- [x] GitHub Actions CI pipeline *(done!)*
- [ ] Database-backed `RegisteredClientRepository` (JPA/H2/PostgreSQL)
- [ ] Database-backed `OAuth2AuthorizationService`
- [ ] Integration tests with `@SpringBootTest`
- [ ] Custom login page (Thymeleaf)
- [ ] Rate limiting on token endpoint
- [ ] Redis-backed token store
- [ ] PKCE demo client app

## Code of Conduct

Be respectful and constructive. All contributors are expected to follow basic open-source etiquette.

---

Questions? Open a [Discussion](https://github.com/naveedin2024/OAuth2AuthorizationServer/discussions).

© 2026 **Naveed Ali Mohammed**. All rights reserved.

