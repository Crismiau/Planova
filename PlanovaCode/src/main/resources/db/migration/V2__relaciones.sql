ALTER TABLE events
    ADD CONSTRAINT fk_events_venue
        FOREIGN KEY (venue_id) REFERENCES venues(id)
        ON DELETE SET NULL
        ON UPDATE CASCADE;

CREATE INDEX idx_events_name ON events(name);
CREATE INDEX idx_events_start_date ON events(start_date);
CREATE INDEX idx_venue_name ON venues(name);