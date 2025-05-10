# vollMed API

This is a RESTful API built for a medical appointment system called **vollMed**, designed to manage doctors, patients, and appointments.

## 🔧 Technologies Used

- **Java 17** — Main programming language  
- **Spring Boot 3** — For building the REST API  
- **Spring Data JPA** — ORM for managing database entities  
- **Spring Security** — For authentication and authorization  
- **JWT (JSON Web Tokens)** — For secure user authentication  
- **MySQL** — Relational database for data persistence  
- **Hibernate** — JPA implementation for database access  
- **Maven** — Dependency management and project build  
- **Lombok** — To reduce boilerplate code  
- **Flyway** — Database version control and migrations  
- **Postman** — For testing API endpoints  

## ⚙️ Features

- Doctor and patient registration  
- Schedule and cancel appointments  
- Secure login system with JWT  
- Input validation and error handling  
- API documentation with Swagger  

## 🚀 Getting Started

1. **Clone the repository:**

   ```bash
   git clone https://github.com/victormreis/vollMed-Api.git
   ```

2. **Navigate into the project folder:**

   ```bash
   cd vollMed-Api
   ```

3. **Configure your database in the `application.properties` file:**

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/vollmed
   spring.datasource.username=root
   spring.datasource.password=your_password
   ```

4. **Run the application:**

   ```bash
   ./mvnw spring-boot:run
   ```

5. **Run Tests:**

   To run unit and integration tests:

   ```bash
   ./mvnw test
   ```

## 📌 Notes

- Make sure MySQL is installed and running.  
- Flyway will automatically run the database migrations on startup.
