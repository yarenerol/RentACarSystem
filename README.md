# Rent A Car System

Terminal-based **Car Rental System** built with Java and PostgreSQL, designed following layered architecture principles for maintainability, scalability, and flexibility.

---

## Features

### Authentication & Roles
- Login/Register using **email + password**.
- Passwords hashed with **SHA-256**.
- **ADMIN:** Add, update, delete, list vehicles.
- **CUSTOMER (INDIVIDUAL/CORPORATE):** Search, rent and list vehicles.
- Only logged-in users can start rentals.

### Vehicles & Pricing
- Vehicle types: **Car, Motorcycle, Helicopter**.
- Each type has separate pricing: hourly, daily, weekly, monthly.
- Corporate users must rent at least **1 month**.
- Vehicles over **2,000,000 TL** and users ≥30 years old require **10% deposit**.

### Rental & Payment
- Prevents overlapping rentals.
- Tracks current and past rentals.
- **Rentals and payments executed in a single transaction** (atomicity guaranteed).

### Search & Pagination
- Filter by type, brand, and price range.
- Pagination support for listing vehicles.

### Error Handling
- Clear, user-friendly messages for success or errors.
- Expected and unexpected errors handled gracefully.

---

## Technical Details

- **Java 21** & **PostgreSQL 16**
- JDBC for database interaction
- Layered architecture: `DAO`, `Service`, `Model`, `UI`, `Utils`, `Exceptions`, `Main`
- Proper transaction management with commit/rollback
- Seed data: 5 users (ADMIN, CORPORATE, INDIVIDUAL) + 10 vehicles

---

## Database Structure (Simplified)

- **users**: user info
- **vehicles**: vehicle info
- **vehicle_type**: vehicle types & pricing
- **orders**: rental records
- **payment**: payment records

---

## Running the Application

1. Create database & tables using provided SQL scripts.
2. Load seed data (users and vehicles).
3. Compile and run `Main.java`.
4. Use terminal menu for all operations.

---