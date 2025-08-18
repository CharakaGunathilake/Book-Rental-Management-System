# **Book Rental Management System**

## **Project Overview**

The **Book Rental Management System** is designed for a small community library to manage books, authors, genres, users, and rentals. The system allows:

* Managing books (add, update, delete, filter by genre/author/availability/quality/year)
* Managing authors and genres
* Managing users and their rental activities
* Tracking rental history and calculating rental fees

**Technologies Used:**

* Backend: Java 21, Spring Boot
* Frontend: React.js + TypeScript + Vite
* Database: PostgreSQL
* Version Control: Git

---

## **Setup Instructions**

### **1. Backend Setup**

1. Clone the repository:

   ```bash
   git clone https://github.com/CharakaGunathilake/Book-Rental-Management-System
   cd backend
   ```
2. Configure **PostgreSQL database** and environment variables in `application.properties` or `application.yml`:

   ```properties
   spring.datasource.url=jdbc:postgresql://<DB_HOST>:<DB_PORT>/<DB_NAME>
   spring.datasource.username=<DB_USERNAME>
   spring.datasource.password=<DB_PASSWORD>
   spring.jpa.hibernate.ddl-auto=update
   ```
3. Build and run backend:

   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

---

### **2. Database Setup**

* Create a PostgreSQL database (example: `book_rental_db`).
* Tables will be automatically created and managed by JPA based on entities:
  * `books`
  * `authors`
  * `genres`
  * `users`
  * `rentals`

---

### **3. Frontend Setup**

1. Navigate to the frontend folder:

   ```bash
   cd frontend
   ```
2. Install dependencies:

   ```bash
   npm install
   ```
3. Start frontend:

   ```bash
   npm run dev
   ```
4. The frontend will run on `http://localhost:5173` (Vite default port).

---

## **API Endpoints**

### **1. Author**

| Method | Endpoint                 | Description            |
| ------ | ------------------------ | ---------------------- |
| POST   | `/api/v1/author`         | Add a new author       |
| PUT    | `/api/v1/author?id={id}` | Update existing author |
| DELETE | `/api/v1/author?id={id}` | Delete author by id    |
| GET    | `/api/v1/author?id={id}` | Retrieve author by id  |
| GET    | `/api/v1/author/all`     | Retrieve all authors   |

---

### **2. Book**

| Method | Endpoint                                            | Description                 |
| ------ | --------------------------------------------------- | --------------------------- |
| POST   | `/api/v1/book`                                      | Add new book                |
| PUT    | `/api/v1/book?id={id}`                              | Update book                 |
| PATCH  | `/api/v1/book/availability?id={id}&status={status}` | Update book availability    |
| PATCH  | `/api/v1/book/quality?id={id}&quality={quality}`    | Update book quality         |
| DELETE | `/api/v1/book?id={id}`                              | Delete book                 |
| GET    | `/api/v1/book?id={id}`                              | Get book by id              |
| GET    | `/api/v1/book/name?name={name}`                     | Get book by name            |
| GET    | `/api/v1/book/author?id={authorId}`                 | Get books by author id      |
| GET    | `/api/v1/book/availability?status={status}`         | Get books by availability   |
| GET    | `/api/v1/book/quality?quality={quality}`            | Get books by quality        |
| GET    | `/api/v1/book/genre?id={genreId}`                   | Get books by genre          |
| GET    | `/api/v1/book/published?publishedYear={year}`       | Get books by published year |
| GET    | `/api/v1/book/all`                                  | Get all books               |

---

### **3. Genre**

| Method | Endpoint                | Description    |
| ------ | ----------------------- | -------------- |
| POST   | `/api/v1/genre`         | Add new genre  |
| PUT    | `/api/v1/genre?id={id}` | Update genre   |
| DELETE | `/api/v1/genre?id={id}` | Delete genre   |
| GET    | `/api/v1/genre/all`     | Get all genres |

---

### **4. Rental**

| Method | Endpoint                                             | Description                |
| ------ | ---------------------------------------------------- | -------------------------- |
| POST   | `/api/v1/rental`                                     | Create a new rental        |
| PUT    | `/api/v1/rental?id={id}`                             | Update rental              |
| PATCH  | `/api/v1/rental/cancel?id={id}`                      | Cancel rental              |
| PATCH  | `/api/v1/rental/return?id={id}`                      | Return rental              |
| DELETE | `/api/v1/rental?id={id}`                             | Delete rental              |
| GET    | `/api/v1/rental/calculate?id={id}&date={returnDate}` | Calculate rental fee       |
| GET    | `/api/v1/rental?id={id}`                             | Get rental by id           |
| GET    | `/api/v1/rental/user?id={userId}`                    | Get all rentals by user id |
| GET    | `/api/v1/rental/book?id={bookId}`                    | Get all rentals by book id |
| GET    | `/api/v1/rental/status?status={status}`              | Get all rentals by status  |
| GET    | `/api/v1/rental/rented_date?date={yyyy-MM-dd}`       | Get rentals by rented date |
| GET    | `/api/v1/rental/return_date?date={yyyy-MM-dd}`       | Get rentals by return date |
| GET    | `/api/v1/rental/all`                                 | Get all rentals            |

---

### **5. User**

| Method | Endpoint                                      | Description         |
| ------ | --------------------------------------------- | ------------------- |
| POST   | `/api/v1/user`                                | Add new user        |
| PUT    | `/api/v1/user?id={id}`                        | Update user         |
| PATCH  | `/api/v1/user/address?id={id}`                | Update user address |
| PATCH  | `/api/v1/user/status?id={id}&status={status}` | Update user status  |
| DELETE | `/api/v1/user?id={id}`                        | Delete user         |
| GET    | `/api/v1/user?id={id}`                        | Get user by id      |
| GET    | `/api/v1/user/by_email?email={email}`         | Get user by email   |
| GET    | `/api/v1/user/by_status?status={status}`      | Get users by status |
| GET    | `/api/v1/user/by_role?role={role}`            | Get users by role   |
| GET    | `/api/v1/user/all`                            | Get all users       |

---

## **Assumptions & Additional Features**

* Only available books can be rented.
* Rental history tracks both user and book details.
* Books can be filtered by availability, quality, author, genre, or year.
* Added endpoints for cancelling and returning rentals.
* User status and address can be updated independently.

---

## **Running Locally**

1. Start PostgreSQL and configure credentials in `application.properties`.
2. Run backend: `./mvnw spring-boot:run`
3. Run frontend: `npm run dev` in the frontend folder
4. Access app at `http://localhost:5173`

---
