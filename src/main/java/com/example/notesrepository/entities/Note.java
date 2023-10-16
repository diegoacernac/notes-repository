package com.example.notesrepository.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
//import java.util.HashSet;
//import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String body;
    private Boolean status;
    private Date registerDate;
    private String registerUser;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;

    /*@ManyToMany
    @JoinTable(
            name = "note_tag",
            joinColumns = @JoinColumn(name = "noteId"),
            inverseJoinColumns = @JoinColumn(name = "tagId")
    )
    private Set<Tag> tags = new HashSet<>();*/
}
