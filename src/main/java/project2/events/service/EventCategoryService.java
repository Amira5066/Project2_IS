package project2.events.service;

import project2.events.model.EventCategory;

import java.util.Optional;

public interface EventCategoryService {

    void save(EventCategory eventCategory);

    Iterable<EventCategory> findAll();
    Optional<EventCategory> findById(Integer id);
}
