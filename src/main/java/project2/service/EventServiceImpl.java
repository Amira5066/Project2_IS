package project2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project2.models.Event;
import project2.repository.EventRepository;

import java.util.Optional;

@Service
public class EventServiceImpl implements EventService{
    @Autowired
    private EventRepository eventRepository;

    public void save(Event event) {
        eventRepository.save(event);
    }
    public void deleteById(Integer id) {
        eventRepository.deleteById(id);
    }
    public Optional<Event> findById(Integer id) {
        return eventRepository.findById(id);
    }
    public Iterable<Event> findAll() {
        return eventRepository.findAll();
    }
}
