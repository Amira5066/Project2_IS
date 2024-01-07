package project2.service;

import project2.models.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByRole(String role);
    Iterable<Role> findAll();
}
