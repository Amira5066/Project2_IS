package project2.events.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project2.events.model.Event;

@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
}
