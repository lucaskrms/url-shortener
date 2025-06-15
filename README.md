# 🔗 URL Shortener

A simple URL shortener built with **Java 21**, **Spring Boot 3.4.5**, and **MongoDB**. It allows creating temporary shortened URLs and redirecting to the original address using a unique identifier.

## ✨ Features

- Shortens URLs using random alphanumeric strings (length between 5 and 10).
- Automatic redirection from shortened URLs.
- URLs expire automatically after 1 minute.
- MongoDB storage.
- Docker Compose setup for MongoDB container.

---

## 📦 Technologies and Dependencies

- **Java 21**
- **Spring Boot 3.4.5**
  - `spring-boot-starter-web`: REST API creation.
  - `spring-boot-starter-data-mongodb`: MongoDB integration.
- **Apache Commons Lang 3.14**: For random string generation (`RandomStringUtils`).
- **Lombok 1.18.30**: Simplifies code with annotations for getters, setters, constructors, etc.
- **Docker & Docker Compose**: For database containerization.

---

## 🧱 API Endpoints

### `POST /shorten-url`

Generates a new shortened URL.

#### Request Body:
```json
{
  "url": "https://www.example.com"
}
```

#### Response:
```json
{
  "shortenedUrl": "http://localhost:8080/abc123"
}
```

---

### `GET /{id}`

Redirects to the original URL corresponding to the provided ID.

**Example:**  
`GET http://localhost:8080/abc123` → redirects to `https://www.example.com`

---

## 🔒 URL Expiration

Shortened URLs expire **automatically after 1 minute** from the time they are created. You can adjust this behavior in `UrlService.java`.

---

Developed by [Lucas Kermessi](https://github.com/lucaskrms) 🚀
