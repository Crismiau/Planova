
INSERT INTO venues (name, direction, city) VALUES
('Estadio Atanasio Girardot', 'Carrera 74 # 48 - 210', 'Medellín'),
('Movistar Arena', 'Diagonal 61C # 26 - 36', 'Bogotá'),
('Plaza de Toros de Cañaveralejo', 'Avenida Guadalupe # 3 - 150', 'Cali');

-- Eventos de ejemplo
INSERT INTO events (name, description, capacity, category, city, start_date, venue_id) VALUES
('Concierto de Rock', 'Festival con bandas nacionales e internacionales.', 40000, 'Música', 'Medellín', '2025-12-15T20:00:00', 1),
('Partido de Fútbol', 'Final del campeonato local.', 45000, 'Deportes', 'Medellín', '2025-11-30T18:00:00', 1),
('Concierto Pop', 'Gira internacional de artista famoso.', 14000, 'Música', 'Bogotá', '2026-02-10T21:00:00', 2),
('Feria Taurina', 'Corrida de toros de la feria de la ciudad.', 16000, 'Cultura', 'Cali', '2025-12-28T16:00:00', 3),
('Conferencia de Tecnología', 'Evento sobre las últimas tendencias en IA.', 10000, 'Tecnología', 'Bogotá', '2026-05-20T09:00:00', 2);
