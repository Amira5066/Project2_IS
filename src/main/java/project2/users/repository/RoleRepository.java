package project2.users.repository;

import org.springframework.data.repository.CrudRepository;
import project2.users.model.Role;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByRole(String role);
}
