package project2.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project2.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
