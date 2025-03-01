# 🐳 Run with Docker

1️⃣ Build the Docker Image
```
docker build -t shift-service-image .
```
2️⃣ Run the Container
```
docker run -d -p 8080:8080 --name shift-service shift-service-image
```
---
# PORT: 8080

---

# API Documentation

Swagger UI is available at: http://localhost:8080/swagger-ui.html

