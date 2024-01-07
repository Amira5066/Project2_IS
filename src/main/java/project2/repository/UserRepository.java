package project2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project2.models.Role;
import project2.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Iterable<User> findAllByRole(Role role);
    void deleteByUsername(String username);
}
