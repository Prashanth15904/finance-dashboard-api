[README.md](https://github.com/user-attachments/files/26490578/README.md)

# Finance Dashboard API

This project is a backend REST API built using Spring Boot for managing financial records and generating dashboard insights. It supports role-based access control and secure authentication.

## Tech Stack
- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- Maven
## Authentication & Authorization

- Basic Authentication is implemented using Spring Security.
- Passwords are securely stored using BCrypt encryption.

## Roles

- VIEWER → Can view dashboard data only
- ANALYST → Can view records and dashboard summary
- ADMIN → Full access (Create, Update, Delete users & records)

## User Management
- Create User
- Get All Users
- Get User by ID
- Update User
- Delete User

## User Management
- Create User
- Get All Users
- Get User by ID
- Update User
- Delete User

## Filtering
- Filter records by:
- Type (INCOME / EXPENSE)
- Category
- Date
- User ID

## Dashboard Summary
- Total Income
- Total Expense
- Net Balance
- Category-wise totals
- Recent Transactions
- Monthly Trends

# API Endpoints
## User APIs
- POST /addUser
- GET /users
- GET /users/{id}
- PUT /users/{id}
- DELETE /users/{id}

## Record APIs
- POST /addFinancialRecords
- GET /fetchAllRecords
- GET /filterRecords

## Example:
-/filterRecords?type=INCOME&category=Salary&date=2026-04-05&userId=1

## Dashboard API
- GET /dashboard/summary/{userId}

# Sample JSON
##  Create User
{
  "name": "Prashanth",
  "email": "prasha@gmail.com",
  "password": "1234",
  "role": "ADMIN"
}

##  Add Financial Record
{
  "amount": 1500.50,
  "type": "INCOME",
  "category": "Salary",
  "date": "2026-04-05",
  "notes": "Monthly salary",
  "userId": 1
}

# ⚙️ How to Run
- Clone the repository
- Open in IntelliJ / Eclipse
- Configure MySQL in application.properties
- Run the Spring Boot application

## Future Enhancements
- JWT Authentication
- Pagination & Sorting
- Frontend Integration

# Author

## Prashanth G
