package project2.events.service;

import project2.events.model.Event;

import java.util.Optional;

public interface EventService {
    void save(Event event);
    void deleteById(Integer id);
    Optional<Event> findById(Integer id);
    Iterable<Event> findAll();
}
