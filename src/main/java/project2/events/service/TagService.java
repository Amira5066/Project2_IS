package project2.events.service;

import project2.events.model.Tag;

public interface TagService {
    Iterable<Tag> findAll();
    void save(Tag tag);
}
