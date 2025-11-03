# 🎬 Cinema Management Web Application

![Java](https://img.shields.io/badge/Java-EE-orange)
![Servlets](https://img.shields.io/badge/Jakarta%20Servlets-4.0-red)
![JSP](https://img.shields.io/badge/JSP-2.3-blue)
![Tomcat](https://img.shields.io/badge/Tomcat-9.0-lightgrey)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)
![Status](https://img.shields.io/badge/Status-Completed-success)

A web-based Java EE application for managing the operations of a cinema — including movies, screenings, bookings, and user access.  
Developed as part of a university course project on *Web and Internet Programming* at the Department of Informatics, University of Piraeus.

---

## 📁 Project Structure

```
jakarta-servlet-cinema-app/
├── src/
│   └── com/...
├── database/
│   └── cinema_schema.sql
├── images/              # (screenshots to be added)
├── .gitignore
├── README.md
└── DBConnection.example.java
```

---

## 📋 Overview

This project demonstrates a multi-user **cinema management system** built using **Jakarta Servlets (Java EE)**, **JSP**, and **MySQL**.  
It implements full CRUD functionality and role-based access control, following the **Model–View–Controller (MVC)** architecture.

User roles:

- **Customer:** View movies, screenings, book tickets, see booking history  
- **Content Admin:** Manage movies & screenings  
- **Application Admin:** Manage user roles and privileges  

---

## 🧩 Features

### 🔐 Authentication & Session Management
- Salted + hashed passwords (MD5)
- `HttpSession`-based user login state
- Proper logout & cache clearing

### 👥 User Roles & Permissions
- Customers: browse + booking + history  
- Content Admins: CRUD movies & screenings  
- Admins: manage user roles  

### 🎞️ Movies & Screenings
- CRUD movie entries  
- Date filters & movie filters  
- Seat availability logic  

### 🎟️ Booking
- Reserve seats  
- Check seat availability  
- Prevent overbooking  

### 🗃️ Database
- Relational MySQL schema  
- DAO pattern for DB operations  

---

## 🧱 Tech Stack

| Component     | Technology |
|--------------|-----------|
| Backend      | Java EE (Jakarta Servlets, JSP) |
| Database     | MySQL |
| Architecture | MVC (Servlets + DAO + Model) |
| Server       | Apache Tomcat |
| Frontend     | JSP, HTML, CSS |
| Security     | MD5 + Salt, Session Validation |

---

## 🚀 Getting Started

### ✅ Prerequisites
| Tool | Version |
|------|--------|
| Java JDK | 8 / 11 |
| Apache Tomcat | 9.x |
| MySQL | 8.x |
| IDE | Eclipse (recommended) / IntelliJ |

### ✅ Setup

```bash
git clone https://github.com/kon-f/jakarta-servlet-cinema-app.git
```

1. Import project as **Dynamic Web Project**
2. Copy `DBConnection.example.java` → `DBConnection.java` and add your credentials
3. Create database `cinema_last`  
4. Import SQL: `database/cinema_schema.sql`
5. Run on Tomcat  
6. Open in browser:
```
http://localhost:8080/jakarta-servlet-cinema-app/
```

---

## 🧮 Database Schema

> Located at: `database/cinema_schema.sql`

```
USER ─┬─< CUSTOMERS
      ├─< CONTENT_ADMIN
      └─< ADMINS

CONTENT_ADMIN ──< MOVIES ──< PROVOLES
CINEMAS ────────< PROVOLES
CUSTOMERS ──────< RESERVATIONS
PROVOLES ───────< RESERVATIONS
```

---

## 🖼️ Screenshots (Coming Soon)

| Login | Dashboard | Admin Panel |
|------|-----------|------------|
| *(Image coming soon)* | *(Image coming soon)* | *(Image coming soon)* |

_Add your screenshots later in `images/` folder_

---

## 🧠 Lessons Learned

- Servlet lifecycle & HTTP handling  
- Session-based role auth  
- MVC architecture in Java  
- JDBC + DAO pattern  
- SQL schema design  

---

## 🔮 Future Improvements

- Replace MD5 → BCrypt/Argon2  
- JSP → modern frontend (React/Angular/Vue)  
- REST API endpoints  
- Docker deployment  
- Connection pooling  

---

## 🏫 Academic Context

Developed as part of the **"Web and Internet Programming"** course  
University of Piraeus — Department of Informatics

---

## 📄 License

Educational & portfolio use permitted.

---

👤 *See GitHub profile for author information*
