package com.example.notesrepository.controllers;

import com.example.notesrepository.entities.Note;
import com.example.notesrepository.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(noteService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor, intente más tarde.\"}");
        }
    }

    @GetMapping("/status")
    public ResponseEntity<?> getAllActive() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(noteService
                    .findAll()
                    .stream()
                    .filter(Note::getStatus
                    ).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor, intente más tarde.\"}");
        }
    }

    @GetMapping("/active/{id}")
    public Optional<Note> getById(@PathVariable Long id) throws Exception {
        return noteService.findById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(noteService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor, intente más tarde.\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Note note) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(noteService.save(note));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor, intente más tarde.\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Note note) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(noteService.update(id, note));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor, intente más tarde.\"}");
        }
    }

    @PatchMapping("/delete/{id}")
    public ResponseEntity<?> deleteNote(Long id, Note note) {
        try {
            Optional<Note> existingNoteOptional = noteService.findById(id);

            if (existingNoteOptional.isPresent()) {
                Note existingNote = existingNoteOptional.get();
                existingNote.setTitle(note.getTitle());
                existingNote.setBody(note.getBody());
                existingNote.setStatus(false);
                existingNote.setRegisterUser(note.getRegisterUser());
                existingNote.setRegisterDate(note.getRegisterDate());
                existingNote.setCategory(note.getCategory());
                noteService.save(existingNote);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\":\"Error. Por favor, intente más tarde.\"}");
        }
        return null;
    }
}
