package project2.events.model;

import jakarta.validation.constraints.NotNull;

public class EventTagDTO {

    @NotNull
    private Event event;

    @NotNull
    private Tag tag;

    public EventTagDTO() {}

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Tag getTag() {
        return tag;
    }
}
