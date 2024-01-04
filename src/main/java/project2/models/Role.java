package project2.models;

import jakarta.persistence.Entity;

@Entity
public class Role extends AbstractEntity{
    public static final String USER = "USER";
    public static final String ADMIN = "ADMIN";

    private String role;

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    @Override
    public String toString() {
        return role;
    }
}
