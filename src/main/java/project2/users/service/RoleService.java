package project2.users.service;

import project2.users.model.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByRole(String role);
    Iterable<Role> findAll();
}
