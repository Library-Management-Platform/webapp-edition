/* -- =========================
-- LIBRARIES
-- =========================
INSERT INTO libraries (id, name, address, created_at, updated_at) VALUES
(1, 'Central Library', 'Downtown City Center', NOW(), NOW()),
(2, 'Science Library', 'University Campus', NOW(), NOW());

-- =========================
-- USERS (BASE TABLE)
-- =========================
INSERT INTO users (id, username, email, password, created_at, updated_at, user_type) VALUES
(1, 'client1', 'client1@test.com', 'password', NOW(), NOW(), 'CLIENT'),
(2, 'client2', 'client2@test.com', 'password', NOW(), NOW(), 'CLIENT'),
(3, 'librarian1', 'librarian1@test.com', 'password', NOW(), NOW(), 'LIBRARIAN'),
(4, 'admin1', 'admin@test.com', 'password', NOW(), NOW(), 'ADMIN');

-- =========================
-- CLIENTS (SUBCLASS TABLE)
-- =========================
INSERT INTO clients (id) VALUES
(1),
(2);

-- =========================
-- LIBRARIANS (SUBCLASS TABLE)
-- =========================
INSERT INTO librarians (id, library_id) VALUES
(3, 1);

-- =========================
-- ADMINS (SUBCLASS TABLE)
-- =========================
INSERT INTO admins (id) VALUES
(4);

-- =========================
-- RESOURCES
-- =========================
INSERT INTO resources (
    id,
    library_id,
    title,
    author,
    category,
    type,
    status,
    created_at,
    updated_at
) VALUES
(1, 1, 'Clean Code', 'Robert C. Martin', 'SCIENCE', 'BOOK', 'AVAILABLE', NOW(), NOW()),
(2, 1, 'Spring in Action', 'Craig Walls', 'SCIENCE', 'BOOK', 'AVAILABLE', NOW(), NOW()),
(3, 2, 'Design Patterns', 'Gang of Four', 'SCIENCE', 'BOOK', 'AVAILABLE', NOW(), NOW()),
(4, 2, 'Effective Java', 'Joshua Bloch', 'SCIENCE', 'BOOK', 'AVAILABLE', NOW(), NOW());

-- =========================
-- LOANS
-- =========================
INSERT INTO loans (
    id,
    client_id,
    resource_id,
    library_id,
    reservation_date,
    borrow_date,
    due_date,
    return_date,
    status,
    rating,
    comment
) VALUES
(
    1,
    1,
    1,
    1,
    NOW(),
    NOW(),
    DATE_ADD(NOW(), INTERVAL 14 DAY),
    NULL,
    'IN_PROGRESS',
    NULL,
    NULL
);
 */