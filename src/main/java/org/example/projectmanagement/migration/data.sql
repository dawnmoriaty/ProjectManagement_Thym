-- Seed data for Categories
INSERT INTO categories (category_id, name_category) VALUES
                                                        (1, 'Car'),
                                                        (2, 'Motorcycle'),
                                                        (3, 'Truck'),
                                                        (4, 'SUV'),
                                                        (5, 'Van');

-- Seed data for Roles
INSERT INTO roles (id, role_name) VALUES
                                      (1, 'ADMIN'),
                                      (2, 'USER'),
                                      (3, 'EMPLOYEE'),
                                      (4, 'CUSTOMER');


-- Seed data for Users
INSERT INTO users (id, username, password, full_name, email, phone, address, idvn, avatar, active) VALUES
                                                                                                       (1, 'admin', 'adminPass', 'Admin User', 'admin@example.com', '123456789', '123 Admin St', 'IDVN001', 'admin.png', true),
                                                                                                       (2, 'john_doe', 'johnPass', 'John Doe', 'john@example.com', '987654321', '456 User St', 'IDVN002', 'john.png', true),
                                                                                                       (3, 'jane_doe', 'janePass', 'Jane Doe', 'jane@example.com', '456123789', '789 User St', 'IDVN003', 'jane.png', true),
                                                                                                       (4, 'manager', 'managerPass', 'Manager User', 'manager@example.com', '789456123', '321 Manager St', 'IDVN004', 'manager.png', true),
                                                                                                       (5, 'guest', 'guestPass', 'Guest User', 'guest@example.com', '321654987', '654 Guest St', 'IDVN005', 'guest.png', false);

-- Seed data for Vehicles
INSERT INTO vehicles (id, name, license_plate, manufacturer, model, image_vehicle, price, status, description, category_id) VALUES
                                                                                                                                (1, 'Toyota Corolla', '123-ABC', 'Toyota', 'Corolla', 'toyota.png', 20000, 'AVAILABLE', 'A compact car', 1),
                                                                                                                                (2, 'Honda Civic', '456-DEF', 'Honda', 'Civic', 'honda.png', 22000, 'AVAILABLE', 'A stylish sedan', 1),
                                                                                                                                (3, 'Yamaha R15', '789-GHI', 'Yamaha', 'R15', 'yamaha.png', 5000, 'AVAILABLE', 'A powerful motorcycle', 2),
                                                                                                                                (4, 'Ford F150', '101-JKL', 'Ford', 'F150', 'ford.png', 30000, 'AVAILABLE', 'A reliable truck', 3),
                                                                                                                                (5, 'Tesla Model X', '202-MNO', 'Tesla', 'Model X', 'tesla.png', 80000, 'OUT_OF_SERVICE', 'An electric SUV', 4);

-- Seed data for Rentals
INSERT INTO rentals (rental_id, vehicle_id, user_id, rental_date, return_date, rental_price, status, deposit, note) VALUES
                                                                                                                        (1, 1, 2, '2025-01-01', '2025-01-10', 500, 'COMPLETED', 100, 'No issues'),
                                                                                                                        (2, 3, 3, '2025-01-05', '2025-01-15', 150, 'PENDING', 50, 'Check tires'),
                                                                                                                        (3, 4, 4, '2025-01-10', '2025-01-20', 700, 'IN_PROGRESS', 200, 'Extra insurance needed'),
                                                                                                                        (4, 2, 5, '2025-01-15', '2025-01-25', 600, 'CANCELLED', 0, 'User cancelled'),
                                                                                                                        (5, 5, 1, '2025-01-20', '2025-01-30', 1000, 'COMPLETED', 300, 'Battery replaced');