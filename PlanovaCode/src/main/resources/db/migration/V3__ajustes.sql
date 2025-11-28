ALTER TABLE events
    ADD COLUMN status VARCHAR(20) DEFAULT 'ACTIVE';

-- Datos iniciales (opcionales, útiles en dev)
INSERT INTO venues (name, direction, city) VALUES ('Principal Stadium', 'Calle 16 #104-43', 'Bogota');

INSERT INTO events (name, description, capacity, category, city, start_date, venue_id)
VALUES ('Music Festival', 'Outdoor event', 500, 'Music', 'Bogota', '2026-01-10 18:00:00', 1);