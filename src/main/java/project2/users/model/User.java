package project2.users.model;

import jakarta.persistence.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import project2.model.AbstractEntity;

@Entity
public class User extends AbstractEntity {
    private String username;
    private String password;

    @ManyToOne()
    private Role role;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void encodeAndSetPassword(String password) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
