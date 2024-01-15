package project2.users.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project2.users.model.Role;
import project2.users.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);
    Iterable<User> findAllByRole(Role role);
    void deleteByUsername(String username);
}
