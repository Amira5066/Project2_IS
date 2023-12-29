package project2.models;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.mindrot.jbcrypt.BCrypt;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity{
    private String username;
    private String password;

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
        String salt = BCrypt.gensalt();
        this.password = BCrypt.hashpw(password, salt);
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
