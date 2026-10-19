# Security Policy

## Supported Versions

| Version | Supported |
|---------|-----------|
| Latest (`main`) | ✅ |
| Older releases  | ❌ |

## Reporting a Vulnerability

**Please do NOT open a public GitHub issue for security vulnerabilities.**

To report a security issue, please use one of the following methods:

1. **GitHub Private Vulnerability Reporting** *(preferred)*  
   Go to the [Security tab](../../security/advisories/new) of this repository and click **"Report a vulnerability"**.

2. **Email**  
   Send details to: `naveedin2024@users.noreply.github.com`  
   Include:
   - Description of the vulnerability
   - Steps to reproduce
   - Potential impact
   - Suggested fix (optional)

## Response Timeline

| Stage | Time |
|---|---|
| Initial acknowledgement | Within 48 hours |
| Triage & assessment | Within 7 days |
| Fix & release | Depends on severity |

## Scope

This project is a **development/learning reference**. The default configuration uses
in-memory credentials (`user`/`password`, `client`/`secret`) that are intentionally
insecure for ease of use. **Never deploy the default configuration to production.**

Security issues in the following areas are in scope:
- Incorrect OAuth2 grant type handling
- JWT validation bypass
- Missing scope enforcement
- Insecure default configurations that may mislead users

## Acknowledgements

We appreciate responsible disclosure. Security reporters will be credited in the
release notes (with their permission).
