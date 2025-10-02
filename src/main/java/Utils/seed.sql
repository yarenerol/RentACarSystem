-- SQL SEED COMMANDS


-- USERS TABLE SEEDS

INSERT INTO users (first_name, last_name, email, passwrd, user_role, age, company_name)
VALUES
    ('Ali', 'Potur', 'ali@example.com', 'password123', 'INDIVIDUAL', 28, NULL),
    ('Ayşe', 'Demir', 'ayse@example.com', 'secret456', 'INDIVIDUAL', 32, NULL),
    ('Mehmet', 'Can', 'mehmet@example.com', 'pass789', 'CORPORATE', NULL, 'TechCorp'),
    ('Elif', 'Yılmaz', 'elif@example.com', 'mypassword', 'INDIVIDUAL', 25, NULL),
    ('Ahmet', 'Kara', 'ahmet@example.com', 'ahmet2025', 'ADMIN', NULL, NULL);


-- VEHICLE TABLE SEEDS

INSERT INTO vehicles (brand, model, price, vehicle_type_id)
VALUES
    ('BMW', 'X5', 2400000.00, 1),
    ('BMW', 'R1200', 450000.00, 2),
    ('BMW', 'HeliX', 10500000.00, 3),
    ('Toyota', 'Corolla', 1900000.00, 1),
    ('Honda', 'CB500', 300000.00, 2),
    ('Airbus', 'H125', 9000000.00, 3),
    ('Audi', 'A4', 1800000.00, 1),
    ('Yamaha', 'YZF-R3', 250000.00, 2),
    ('Bell', '407', 8500000.00, 3),
    ('Mercedes', 'C200', 2500000.00, 1);


-- ORDER TABLE SEED

INSERT INTO orders (user_id, vehicle_id, total_price, status, deposit, rental_type, duration, start_date, end_date)
VALUES

    (1, 4, 1500, 'confirmed', 500, 'Daily', 3, '2025-10-05 10:00:00', '2025-10-08 10:00:00'),

    (2, 1, 10000, 'confirmed', 1000, 'Monthly', 1, '2025-10-10 09:00:00', '2025-11-10 09:00:00'),

    (3, 6, 20000, 'confirmed', 2000, 'Monthly', 1, '2025-10-15 08:00:00', '2025-11-15 08:00:00');


-- PAYMENT TABLE SEED

INSERT INTO payment (order_id, amount, deposit, status)
VALUES

    (1, 1500, 500, 'Paid'),

    (2, 10000, 1000, 'Paid'),

    (3, 20000, 2000, 'Paid');

-- VEHICLE TYPE TABLE SEED

INSERT INTO vehicle_type (vehicle_type)
VALUES

    ('Car', 100, 500, 3000, 10000),
    ('Motorcycle', 50, 250, 1500, 5000),
    ('Helıcopter', 200, 1000, 6000, 20000);