package project2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project2.models.EventCategory;
import project2.repository.EventCategoryRepository;

import java.util.Optional;

@Service
public class EventCategoryServiceImpl implements EventCategoryService{
    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    public void save(EventCategory eventCategory) {
        eventCategoryRepository.save(eventCategory);
    }

    public Iterable<EventCategory> findAll() {
        return eventCategoryRepository.findAll();
    }

    public Optional<EventCategory> findById(Integer id) {
        return eventCategoryRepository.findById(id);
    }
}
