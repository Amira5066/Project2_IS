package project2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project2.models.Tag;
import project2.repository.TagRepository;

@Service
public class TagServiceImpl implements TagService{
    @Autowired
    private TagRepository tagRepository;

    public Iterable<Tag> findAll() {
        return tagRepository.findAll();
    }

    public void save(Tag tag) {
        tagRepository.save(tag);
    }
}
