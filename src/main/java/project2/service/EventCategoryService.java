package project2.service;

import project2.models.EventCategory;

import java.util.Optional;

public interface EventCategoryService {

    void save(EventCategory eventCategory);

    Iterable<EventCategory> findAll();
    Optional<EventCategory> findById(Integer id);
}
