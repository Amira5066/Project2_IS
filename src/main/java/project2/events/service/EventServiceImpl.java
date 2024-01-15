package project2.events.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project2.events.model.Event;
import project2.events.repository.EventRepository;

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
