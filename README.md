# OAuth2 Authorization Server

This is a custom Spring Authorization Server configured to issue JWT tokens for the Resource Server.

## Configuration Details
- **Port**: 8091
- **Issuer URI**: `http://localhost:8091`
- **Default User**: `user` / `password`
- **Client ID**: `client`
- **Client Secret**: `secret`
- **Authorized Grant Types**: `authorization_code`, `refresh_token`, `client_credentials`
- **Scopes**: `openid`, `profile`, `read`, `admin`

## Token Expiry
- **Access Token**: 1 Hour
- **Refresh Token**: 1 Day

## Key Endpoints
- **Token Endpoint**: `POST http://localhost:8091/oauth2/token`
- **JWKS Endpoint**: `GET http://localhost:8091/oauth2/jwks`
- **Authorize Endpoint**: `GET http://localhost:8091/oauth2/authorize`

## How to Get a Token (Client Credentials)
```bash
curl --location --request POST 'http://localhost:8091/oauth2/token' \
--header 'Authorization: Basic Y2xpZW50OnNlY3JldA==' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=client_credentials' \
--data-urlencode 'scope=read admin'
```
