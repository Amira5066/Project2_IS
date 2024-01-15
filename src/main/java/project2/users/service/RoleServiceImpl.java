package project2.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project2.users.model.Role;
import project2.users.repository.RoleRepository;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Role> findByRole(String role) {
        return roleRepository.findByRole(role);
    }

    @Override
    public Iterable<Role> findAll() {
        return roleRepository.findAll();
    }
}
