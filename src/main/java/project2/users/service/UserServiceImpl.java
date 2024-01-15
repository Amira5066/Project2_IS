package project2.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project2.users.model.Role;
import project2.users.model.User;
import project2.users.repository.RoleRepository;
import project2.users.repository.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean saveUser(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            return false;
        }
        User user = new User();
        user.setUsername(username);
        user.encodeAndSetPassword(password);
        Optional<Role> userRole = roleRepository.findByRole(Role.USER);
        user.setRole(userRole.get());
        userRepository.save(user);
        return true;
    }

    @Override
    public Iterable<User> findAllByRole(Role role) {
        return userRepository.findAllByRole(role);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    public void editUser(String oldUsername, User newUser, User existingUser) {
        if(newUser.getUsername().isEmpty()) {
            newUser.setUsername(oldUsername);
        }
        if(newUser.getPassword().isEmpty()) {
            newUser.setPassword(existingUser.getPassword());
        } else {
            newUser.encodeAndSetPassword(newUser.getPassword());
        }
        userRepository.delete(existingUser);
        userRepository.save(newUser);
    }
}
