# 🎬 Cinema Management Web Application

A web-based Java EE application for managing the operations of a cinema
--- including movies, screenings, bookings, and user access.\
Developed as part of a university course project on *Web and Internet
Programming* at the Department of Informatics, University of Piraeus.

------------------------------------------------------------------------

## 📋 Overview

This project demonstrates a multi-user **cinema management system**
built using **Jakarta Servlets (Java EE)**, **JSP**, and **MySQL**.\
It implements full CRUD functionality and role-based access control,
following the **Model--View--Controller (MVC)** architecture.

The system provides three main user roles:

-   **Customer:** View movies and screenings, make bookings, and review
    booking history.\
-   **Content Administrator:** Manage movie and screening data (add,
    edit, delete).\
-   **Application Administrator:** Manage user privileges
    (promote/demote roles).

------------------------------------------------------------------------

## 🧩 Features

### 🔐 Authentication & Session Management

-   Secure login system with **salted and hashed passwords** (MD5-based
    hashing).\
-   Session handling using `HttpSession` (user information and role
    tracking).\
-   Secure logout process that invalidates sessions and clears cached
    data.

### 👥 User Roles & Permissions

-   **Customers:**
    -   View available movies and screenings.\
    -   Filter screenings by date or movie.\
    -   Book tickets and view their booking history.
-   **Content Admins:**
    -   Add, edit, and delete movies or screenings.\
    -   Manage all cinema program data via JSP-based forms.
-   **Application Admins:**
    -   Manage user privileges (promote/demote between Customer and
        Content Admin).\
    -   Restricted access --- cannot directly alter the user database to
        prevent data loss.

### 🎞️ Movie & Screening Management

-   Full CRUD operations via dedicated DAO classes (`MovieDao`,
    `ScreeningDao`).\
-   Display pages for movie listings and specific screenings.\
-   Filtering screenings by date range.

### 🎟️ Booking Management

-   Customers can reserve seats for screenings.\
-   Availability checks prevent overbooking.\
-   Automatic ID generation for new bookings.

### 🗃️ Database Integration

-   MySQL backend connected via `DBConnection.java`.\
-   Multiple relational tables with proper foreign key relationships.\
-   Parameterized queries and prepared statements for security.

------------------------------------------------------------------------

## 🧱 Tech Stack

  Component      Technology
  -------------- -------------------------------------------
  Backend        Java EE (Jakarta Servlets, JSP)
  Database       MySQL
  Architecture   MVC (Servlet + DAO + Model)
  Web Server     Apache Tomcat
  Frontend       JSP, HTML, CSS
  Security       MD5 hashing with salt, session validation

------------------------------------------------------------------------

## 🚀 How to Run

1.  **Clone the repository**

    ``` bash
    git clone https://github.com/yourusername/cinema-management-system.git
    ```

2.  **Import the project** into your IDE (e.g. Eclipse, IntelliJ IDEA)
    as a *Dynamic Web Project*.

3.  **Configure the database**

    -   Create a MySQL database named `cinema_last`.
    -   Import the SQL schema from
        [`database/cinema_schema.sql`](./database/cinema_schema.sql).
    -   Update `DBConnection.java` with your local database credentials.

4.  **Deploy on Apache Tomcat**

    -   Set Tomcat as your server in the IDE.
    -   Run the project and navigate to:\
        👉 `http://localhost:8080/cinema-management/`

5.  **Default Roles (example)** \| Role \| Access \|
    \|------\|--------\| \| Admin \| Manage users \| \| Content Admin \|
    Manage movies/screenings \| \| Customer \| Browse and book \|

------------------------------------------------------------------------

## 🧮 Database Schema

The database follows a relational model with foreign key constraints to
maintain referential integrity.

**Main Entities and Relationships:** - `user` --- base table for
authentication (contains username, hashed password, salt, and role) -
`CUSTOMERS`, `CONTENT_ADMIN`, `ADMINS` --- extend the `user` entity with
role-specific info - `MOVIES` --- managed by `CONTENT_ADMIN` -
`PROVOLES` (Screenings) --- link `MOVIES`, `CINEMAS`, and
`CONTENT_ADMIN` - `RESERVATIONS` --- connect `CUSTOMERS` with `PROVOLES`

**Simplified ER Diagram (text form):**

    USER (username) ─┬─< CUSTOMERS (user_username)
                      ├─< CONTENT_ADMIN (user_username)
                      └─< ADMINS (user_username)

    CONTENT_ADMIN (ID) ──< MOVIES (CONTENT_ADMIN_ID) ──< PROVOLES (MOVIES_ID)
    CINEMAS (ID) ──< PROVOLES (CINEMAS_ID)
    CUSTOMERS (ID) ──< RESERVATIONS (CUSTOMERS_ID)
    PROVOLES (Screening_Id) ──< RESERVATIONS (PROVOLES_Screening_Id)

The SQL schema is available at:\
📄 `database/cinema_schema.sql`

------------------------------------------------------------------------

## 🖼️ Screenshots

*(Add screenshots here to make the project visually appealing)*

  --------------------------------------------------------------------------------------------
  Login Page                   Customer Dashboard                 Admin Management
  ---------------------------- ---------------------------------- ----------------------------
  ![Login](images/login.png)   ![Customer](images/customer.png)   ![Admin](images/admin.png)

  --------------------------------------------------------------------------------------------

------------------------------------------------------------------------

## 🧠 Lessons Learned

-   Understanding of **servlet lifecycle** and **HTTP request/response
    handling**.\
-   Implementation of **role-based access control** using sessions.\
-   Working with **JDBC** and **DAO patterns** for clean data access.\
-   Designing JSP pages that dynamically render data from backend logic.

------------------------------------------------------------------------

## 🔮 Possible Improvements

-   Replace MD5 with stronger hashing (e.g., BCrypt or Argon2).\
-   Add RESTful endpoints and a modern frontend (React, Vue, or
    Angular).\
-   Use connection pooling for better performance.\
-   Dockerize the application for easier deployment.

------------------------------------------------------------------------

## 🏫 Acknowledgment

This project was developed as part of the **"Web and Internet
Programming"** course\
at the **Department of Informatics, University of Piraeus**.

------------------------------------------------------------------------

## 📄 License

This project is released for educational and portfolio purposes.\
Feel free to explore, learn, and build upon it.

------------------------------------------------------------------------

👨‍💻 **Author:** (See GitHub Profile)
