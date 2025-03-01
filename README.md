##  Prerequisites

Ensure you have the following installed:

- **JDK 17+**
- **Maven 3.8+**
- **Docker**

## ⚙️ Build and Run

###  1. Build the JAR
```
mvn clean package
```
### 2. Build the Docker Image
```
docker build -t shift-service-image .
```
### 3. Run the Container
```
docker run -d -p 8080:8080 --name shift-service shift-service-image
```
## API Documentation

Once the service is running, you can access the Swagger UI at:

📌 http://localhost:8080/swagger-ui.html

🔧 Configuration  
•	Application Port: 8080