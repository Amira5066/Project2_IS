package project2.service;

import project2.models.Event;

import java.util.Optional;

public interface EventService {
    void save(Event event);
    void deleteById(Integer id);
    Optional<Event> findById(Integer id);
    Iterable<Event> findAll();
}
