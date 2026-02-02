# URL Shortener API

A simple public URL Shortener Service using Spring Boot and Postgres.

## Features
- Convert long URLs into 6-character short codes.
- Set validity/expiration date for short URLs.
- Prevent duplicate entries (returns the existing short code).
- "url expired" message for expired links.

## Prerequisites
- Java 21
- Postgres Database
- Gradle

## Setup
1. Configure database in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your_db
spring.datasource.username=your_user
spring.datasource.password=your_pass
```
2. Run the application:
```bash
./gradlew bootRun
```

## API Usage

### 1. Shorten URL
**Endpoint:** `POST /api/shorten`

**Request Body:**
```json
{
  "originalUrl": "https://example.com/very/long/url",
  "validity": "2026-12-31T23:59:59"
}
```

**Response:**
```json
{
  "shortUrl": "http://localhost:8080/r/AbCd12",
  "originalUrl": "https://example.com/very/long/url",
  "expiresAt": "2026-12-31T23:59:59"
}
```

### 2. Get Original URL
**Endpoint:** `GET /r/{shortCode}`

**Response:**
```json
{
  "originalUrl": "https://example.com/very/long/url",
  "expiresAt": "2026-12-31T23:59:59"
}
```

**Expired or Not Found:**
- If the URL is expired, returns status `410 GONE` with body: `url expired`.
