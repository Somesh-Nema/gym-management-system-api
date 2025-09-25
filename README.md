# Gym Management System - REST API

A secure and scalable REST API backend for a comprehensive gym management application. This project, built with **Spring Boot**, provides a complete set of endpoints for managing trainers, class schedules, and bookings, with a robust role-based security model.

## Features

* **Role-Based Access Control:** Secure endpoints with distinct permissions for `ADMIN`, `TRAINER`, and `MEMBER` roles using Spring Security.
* **Secure Authentication:** A custom authentication flow with password hashing via **BCrypt**.
* **Admin Dashboard Functionality:** Endpoints for admins to create and manage trainers, define class templates, and schedule classes.
* **Publicly Accessible Schedule:** A public endpoint for users and prospective members to view the upcoming class schedule.
* **Clean API Design:** Utilizes Data Transfer Objects (DTOs) to maintain a stable API contract and a global exception handler for professional error responses.

## Tech Stack

| Category             | Technology                                       |
| -------------------- | ------------------------------------------------ |
| **Language/Framework** | Java 17, Spring Boot 3.x                         |
| **Security** | Spring Security 6.x (Basic Auth)                 |
| **Data Persistence** | Spring Data JPA, Hibernate                       |
| **Database** | MySQL                                            |
| **Build Tool** | Maven                                            |

## Getting Started

Follow these instructions to get a local copy of the project up and running for development and testing purposes.

### Prerequisites

You will need the following tools installed on your machine:
* [Java JDK 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) or higher
* [Apache Maven](https://maven.apache.org/download.cgi)
* [MySQL Server](https://dev.mysql.com/downloads/mysql/)
* An API testing tool like [Postman](https://www.postman.com/downloads/)

### Installation

1.  **Clone the repository**
    ```sh
    git clone [https://github.com/YourUsername/gym-management-system-api.git](https://github.com/YourUsername/gym-management-system-api.git)
    cd gym-management-system-api
    ```

2.  **Create the MySQL Database**
    Log in to your MySQL client and create the database for the project.
    ```sql
    CREATE DATABASE gym_db;
    ```

3.  **Configure Application Properties**
    Open the `src/main/resources/application.properties` file and update the database credentials to match your local MySQL setup.
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/gym_db
    spring.datasource.username=your_mysql_username
    spring.datasource.password=your_mysql_password
    ```

4.  **Run the Application**
    Use Maven to run the application.
    ```sh
    mvn spring-boot:run
    ```
    The server will start on `http://localhost:8080`.

## API Endpoints

The following are the core endpoints implemented in this API.

| Method | Endpoint                       | Access | Description                                |
| :----- | :----------------------------- | :----- | :----------------------------------------- |
| `GET`  | `/api/schedule`                | Public | Fetches all scheduled classes.             |
| `POST` | `/api/bookings`                | Public | Books a member into a class.               |
| `POST` | `/api/admin/trainers`          | ADMIN  | Creates a new trainer user.                |
| `POST` | `/api/admin/class-templates`   | ADMIN  | Creates a new class template (e.g., Yoga). |
| `POST` | `/api/admin/schedule`          | ADMIN  | Schedules a new class on the calendar.     |

*Note: Admin endpoints require Basic Authentication.*

## Future Enhancements

This project provides a solid foundation. Future improvements could include:
* [ ] Implementing **JWT (JSON Web Tokens)** for stateless authentication.
* [ ] Writing **JUnit and Mockito tests** for the service layer.
* [ ] Adding endpoints for members to view their own bookings.
* [ ] Deploying the application to a cloud service like AWS or Heroku.

## Contact

Somesh Nema - [LinkedIn]([https://www.linkedin.com/in/somesh-nema-09a924256/])- [GitHub]([https://github.com/Somesh-Nema])
