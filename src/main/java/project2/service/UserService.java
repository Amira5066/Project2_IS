package project2.service;

import project2.models.Role;
import project2.models.User;

import java.util.Optional;

public interface UserService {
    void save(User user);
    Optional<User> findByUsername(String username);
    boolean saveUser(String username, String password);
    Iterable<User> findAllByRole(Role role);
    void delete(User user);
    Optional<User> findById(Integer id);
    void deleteById(Integer id);
    void editUser(String oldUsername, User newUser, User existingUser);
}
