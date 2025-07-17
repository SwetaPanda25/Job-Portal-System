# Job Portal System

A complete Job Portal System built using Spring Boot and Thymeleaf. It supports role-based access for Employers and Applicants.

## 🚀 Features

### 👤 Applicant
- Register/Login
- Search/Filter available jobs
- Apply for jobs
- View applied jobs with status

### 🧑‍💼 Employer
- Register/Login
- Post new jobs
- View jobs posted
- View applicants who applied to specific jobs
- Change status of applicants, that will be reflected in applivant portal

---

## 🛠 Tech Stack

| Layer        | Technology           |
|--------------|----------------------|
| Backend      | Java, Spring Boot    |
| Frontend     | Thymeleaf (HTML, CSS)|
| Database     | MySQL                |
| ORM          | Spring Data JPA      |
| Security     | Spring Security      |
| Build Tool   | Maven                |

---

## 📁 Project Structure
```bash
├── src/main/java/com/example/JobPortal
│   ├── controller
│   ├── entity
│   ├── repository
│   ├── service
│   └── security
├── src/main/resources/templates
│   └── *.html (Thymeleaf views)
├── src/main/resources/static/css
│   └── style.css
├── src/main/resources/application.properties
└── pom.xml
```

