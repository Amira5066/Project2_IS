package project2.data;

import org.springframework.data.repository.CrudRepository;
import project2.models.Role;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {
    Optional<Role> findByRole(String role);
}
