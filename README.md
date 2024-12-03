# SecurePro

## Project Description
SecurePro is a full-stack web application designed to streamline product and category management while ensuring robust security and scalable deployment solutions. This application supports a secure REST API with Spring Security, multi-profile configuration (dev/test and prod), and CI/CD pipeline implementation using Docker and Jenkins. It aims to deliver a reliable and efficient platform for managing products, categories, and user roles.

## General Objective
The primary goal of SecurePro is to provide:
- Secure authentication and authorization mechanisms for APIs.
- Efficient product and category management with advanced querying and filtering options.
- Seamless user role management for enhanced system control.
- A robust CI/CD pipeline for deployment with Docker and Jenkins.

## Technologies Used
- **Java 17**: Core programming language for the application.
- **Spring Boot**: Framework for developing the backend.
- **Spring Security**: Ensures authentication and authorization.
- **Spring Data JPA**: Facilitates database interactions.
- **MariaDB/OracleXE**: Database management systems.
- **Docker**: Containerization for deployment.
- **Jenkins**: Continuous integration and deployment.
- **JUnit and Mockito**: For unit and integration testing.
- **Lombok**: Reduces boilerplate code in Java.
- **Git**: Version control system for tracking changes.
- **Swagger**: API documentation.

## Project Structure
- **Controller/**: Handles HTTP requests and maps them to service methods.
- **Service/**: Contains business logic and service methods.
- **Repository/**: Manages data retrieval and interaction with the database.
- **Model/**: Defines entities such as Product, Category, User, and Role.
- **DTO/**: Contains data transfer objects for cleaner communication between layers.
- **Mapper/**: Maps entities to DTOs and vice versa.
- **Exception/**: Handles custom exceptions and global error handling.
- **Utils/**: Helper classes and utilities.
- **Tests/**: Contains unit and integration tests.

## Features

### Product Management
- List products with pagination (`/api/user/products`).
- Search products by designation with pagination and sorting.
- Filter products by category with pagination and sorting.
- Add, update, and delete products (Admin only).

### Category Management
- List categories with pagination (`/api/user/categories`).
- Search categories by name with pagination and sorting.
- Manage products within categories with advanced filtering.
- Add, update, and delete categories (Admin only).

### User Management
- User registration and authentication.
- Manage user roles (Admin only).

### Security
- Stateful authentication using **Spring Security** and **JdbcAuthentication**.
- Role-based access control:
    - `/api/admin/*` requires the **ADMIN** role.
    - `/api/user/*` requires the **USER** role.
- Password encryption with **BCryptPasswordEncoder**.
- Multi-profile configuration:
    - **Dev**: Simplified testing with optional security bypass.
    - **Prod**: Full security implementation.

### CI/CD Pipeline
- **Jenkins**: Used for continuous integration and deployment.
- **Docker**: Enables containerized application deployment.

### Testing
- **Unit Tests**: Implemented using JUnit and Mockito.
- **Security Tests**: Validate authentication and authorization.

## Diagrams
- **Use Case Diagram**: ![Use Case Diagram](/resources/UML/classDiagram.png)

## Installation and Usage

### Prerequisites
- **Java 17** or later.
- **MariaDB** or **OracleXE** installed and running.
- **Maven** for dependency management and project builds.
- **Docker** and **Jenkins** for deployment.

### Running the Application
1. Clone this repository:  
   ```bash
   git clone https://github.com/MesVortex/SecurePro.git
   ```
   
2. Navigate to the project directory:
   ```bash
      cd SecurePro
   ```

3. Build the project using Maven: 
   ```bash
      mvn clean install
   ```

4. Run the application:
   ```bash
      java -jar target/SecurePro.jar
   ```

[//]: # (### Docker Deployment)

[//]: # (1. Build the Docker image:)

[//]: # (   ```bash)

[//]: # (      docker build -t SecurePro .)

[//]: # (   ```)

[//]: # ()
[//]: # (2. Run the application in a container:)

[//]: # (   ```bash)

[//]: # (      docker run -p 8080:8080 SecurePro)

[//]: # (   ```)

## Author
- **Meskine Mostafa**
    - Email: meskinemostafa4@gmail.com
    - GitHub: [Meskine Mostafa](https://github.com/MesVortex)
