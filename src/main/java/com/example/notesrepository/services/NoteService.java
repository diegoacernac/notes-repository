package com.example.notesrepository.services;

import com.example.notesrepository.entities.Note;
import com.example.notesrepository.repositories.NoteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    @Autowired
    private NoteRepository noteRepository;

    @Transactional
    public List<Note> findAll() throws Exception{
        try {
            return noteRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Optional<Note> findById(Long id) throws Exception {
        try {
            return noteRepository.findById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Note save(Note note) throws Exception{
        try {
            return noteRepository.save(note);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public Note update(Long id, Note note) throws Exception {
        try {
            Optional<Note>noteOptional = noteRepository.findById(id);
            Note noteEntitie = noteOptional.get();
            noteEntitie = noteRepository.save(note);
            return noteEntitie;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
