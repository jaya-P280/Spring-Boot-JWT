# Spring Boot JWT Authentication

![Java](https://img.shields.io/badge/Java-17%2B-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![License](https://img.shields.io/badge/license-MIT-blue)

A robust, secure backend REST API boilerplate demonstrating **JSON Web Token (JWT)** authentication and authorization using **Spring Boot** and **Spring Security**.

## 📖 Overview

This project provides a secure foundation for user management and authentication. It implements stateless session management using JWTs, allowing clients (like React, Angular, or Mobile Apps) to authenticate once and access protected resources securely.

### Key Features
* **User Registration**: Create new user accounts with encrypted passwords.
* **User Login**: Authenticate credentials and generate a JWT Access Token.
* **Role-Based Authorization**: Protect endpoints based on user roles (e.g., `USER`, `ADMIN`).
* **JWT Filter**: Custom filter to validate tokens on incoming requests.
* **Exception Handling**: Global exception handling for unauthorized access and bad credentials.

## 🛠️ Technologies Used

* **Java 17+**
* **Spring Boot 3.x**
* **Spring Security** (Authentication & Authorization)
* **JJWT** (JWT creation and validation)
* **Spring Data JPA** (Database interaction)
* **MySQL / PostgreSQL / H2** (Database - configurable)
* **Lombok** (Boilerplate code reduction)
* **Maven** (Build tool)

## 🚀 Getting Started

Follow these instructions to set up and run the project locally.

### Prerequisites
* [Java Development Kit (JDK) 17+](https://www.oracle.com/java/technologies/downloads/)
* [Maven](https://maven.apache.org/)
* A database (MySQL, PostgreSQL) or use H2 In-Memory.

### Installation

1.  **Clone the repository**
    ```bash
    git clone [https://github.com/jaya-P280/Spring-Boot-JWT.git](https://github.com/jaya-P280/Spring-Boot-JWT.git)
    cd Spring-Boot-JWT
    ```

2.  **Configure the Database**
    Open `src/main/resources/application.properties` (or `.yml`) and configure your database credentials.
    
    *Example for MySQL:*
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name
    spring.datasource.username=root
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update
    
    # JWT Configuration
    application.security.jwt.secret-key=YourSecretKeyMustBeVeryLongAndSecureBase64String
    application.security.jwt.expiration=86400000
    ```

3.  **Build the Project**
    ```bash
    mvn clean install
    ```

4.  **Run the Application**
    ```bash
    mvn spring-boot:run
    ```
    The server will start on `http://localhost:8080`.

## 🔌 API Endpoints

Use tools like **Postman** or **cURL** to test the API.

### Authentication Controller
| Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :--- |
| `POST` | `/auth/register` | Register a new user | ❌ No |
| `POST` | `/auth/login` | Login & receive JWT Token | ❌ No |

### Demo/Test Controller
| Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :--- |
| `GET` | `/demo/public` | Accessible by anyone | ❌ No |
| `GET` | `/demo/user` | Accessible by authenticated users | ✅ Yes |
| `GET` | `/demo/admin` | Accessible by ADMIN only | ✅ Yes |

> **Note:** For protected endpoints, you must include the token in the `Authorization` header:
> `Authorization: Bearer <your_jwt_token>`

## 📂 Project Structure

```bash
src/main/java/com/jaya/jwt
├── config/             # Security configs (SecurityFilterChain, JwtAuthFilter)
├── controller/         # REST Controllers (AuthController, DemoController)
├── model/              # JPA Entities (User, Role)
├── repository/         # Data Access Layers
├── service/            # Business Logic (AuthenticationService, JwtService)
└── SpringBootJwtApplication.java
