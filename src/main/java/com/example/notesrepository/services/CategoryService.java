package com.example.notesrepository.services;

import com.example.notesrepository.entities.Category;
import com.example.notesrepository.repositories.CategoryRepositoy;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepositoy categoryRepositoy;

    @Transactional
    public List<Category> findAll() throws Exception{
        try {
            return categoryRepositoy.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Optional<Category> findById(Long id) throws Exception {
        try {
            return categoryRepositoy.findById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Category save(Category category) throws Exception{
        try {
            return categoryRepositoy.save(category);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Category update(Long id, Category category) throws Exception {
        try {
            Optional<Category>categoryOptional = categoryRepositoy.findById(id);
            Category categoryEntitie = categoryOptional.get();
            categoryEntitie = categoryRepositoy.save(category);
            return categoryEntitie;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
