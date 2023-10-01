package com.example.notesrepository.services;

import com.example.notesrepository.entities.Category;
import com.example.notesrepository.entities.Tag;
import com.example.notesrepository.repositories.TagRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {
    @Autowired
    private TagRepository tagRepository;

    @Transactional
    public List<Tag> findAll() throws Exception{
        try {
            return tagRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Optional<Tag> findById(Long id) throws Exception {
        try {
            return tagRepository.findById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Tag save(Tag tag) throws Exception{
        try {
            return tagRepository.save(tag);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Tag update(Long id, Tag tag) throws Exception {
        try {
            Optional<Tag>tagOptional = tagRepository.findById(id);
            Tag tagEntitie = tagOptional.get();
            tagEntitie = tagRepository.save(tag);
            return tagEntitie;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
