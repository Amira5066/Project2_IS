package project2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project2.models.EventCategory;

@Repository
public interface EventCategoryRepository extends CrudRepository<EventCategory, Integer> {
}
