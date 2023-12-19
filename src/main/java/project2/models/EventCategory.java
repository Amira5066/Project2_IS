package project2.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;

@Entity
public class EventCategory extends AbstractEntity{
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters.")
    private String name;

    public EventCategory(String name) {
        this.name = name;
    }
    public EventCategory(){}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
