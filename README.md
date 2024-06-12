# Coupon Management API

This is a backend API built using Java and Spring Boot that allows users to manage coupons. The API supports creating, fetching, updating, and deleting coupons, as well as user authentication and authorization.

## Features

- Create new coupons (for admin users)
- Fetch all coupons (for admin and user roles)
- Fetch a coupon by code (for admin and user roles)
- Update an existing coupon (for admin users)
- Delete a coupon (for admin users)
- User authentication and authorization

## Prerequisites

- Java 8 or higher
- Maven
- MySQL database

## Getting Started

1. Clone the repository:

   ```
   git clone https://github.com/mugosimon/coupon.git
   ```

2. Navigate to the project directory:

   ```
   cd coupon
   ```

3. Update the database connection details in the `application.properties` file:

   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

4. Build the project using Maven:

   ```
   mvn clean install
   ```

5. Run the application:

   ```
   mvn spring-boot:run
   ```

The API will be available at `http://localhost:8080/api/v1/coupon`.

## Endpoints

- `POST /api/v1/coupon`: Create a new coupon (admin role)
- `GET /api/v1/coupon`: Fetch all coupons (admin and user roles)
- `GET /api/v1/coupon/{code}`: Fetch a coupon by code (admin and user roles)
- `PUT /api/v1/coupon/{id}`: Update an existing coupon (admin role)
- `DELETE /api/v1/coupon/{id}`: Delete a coupon (admin role)

## Authentication and Authorization

The API uses Spring Security for authentication and authorization. Users can log in using HTTP Basic authentication with their email and password.

The following roles are available:
- `ROLE_USER`: Allows users to fetch coupons
- `ROLE_ADMIN`: Allows users to create, update, and delete coupons

## Swagger Documentation

The API documentation is available at `http://localhost:8080/swagger-ui.html`.

## Dependencies

The project uses the following dependencies:

- `spring-boot-starter-data-jpa`: For database integration and ORM
- `spring-boot-starter-web`: For building the web application
- `spring-boot-starter-security`: For securing the application
- `mysql-connector-java`: For connecting to the MySQL database
- `javax.servlet-api`: For providing the servlet API
- `lombok`: For generating boilerplate code
- `spring-boot-starter-validation`: For input validation
- `springfox-boot-starter`: For generating Swagger documentation
- `springfox-swagger-ui`: For the Swagger UI

## License

This project is licensed under the [MIT License](LICENSE).
