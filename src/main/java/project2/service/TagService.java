package project2.service;

import project2.models.Tag;

public interface TagService {
    Iterable<Tag> findAll();
    void save(Tag tag);
}
